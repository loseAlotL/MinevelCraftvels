package org.randomlima.minevelcraftvels.Utils.Projectiles;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.Characters.Character;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public abstract class Projectile {
    protected Player player;
    protected double metersPerTick;
    protected double range;
    protected boolean bulletDrop;
    protected double dropMultiplier;
    protected int damage;
    protected int headShotDamage;
    protected final MinevelCraftvels plugin;
    protected final CharacterManager characterManager;

    public Projectile(Player player, int damage, int headShotDamage, MinevelCraftvels plugin) {
        this.player = player;
        this.damage = damage;
        this.headShotDamage = headShotDamage;
        this.plugin = plugin;
        characterManager = new CharacterManager(plugin);

        this.range = 50;
        this.metersPerTick = 1;
    }

    public void setMetersPerTick(double metersPerTick) {
        this.metersPerTick = metersPerTick;
    }

    public void setBulletDrop(boolean bulletDrop) {
        this.bulletDrop = bulletDrop;
    }

    public void setBulletDropMultiplier(double dropMultiplier) {
        this.dropMultiplier = dropMultiplier;
    }

    public void setRange(double range) {
        this.range = range;
    }
    protected void rayDamage(RayTraceResult result) {
        if (result == null) return;
        onHit((LivingEntity) result.getHitEntity(), result.getHitBlock(), result.getHitPosition().toLocation(player.getWorld()));

        // Case 1: block
        if (result.getHitBlock() != null) {
            return;
        }

        // Case 2: LivingEntity
        if (result.getHitEntity() == null) return;

        Entity entity = result.getHitEntity();
        if (!(entity instanceof LivingEntity)) return;

        LivingEntity hit = (LivingEntity) entity;
        boolean headshot = isHeadshot(hit, result.getHitPosition().toLocation(hit.getWorld()));

        int finalDamage = headshot ? headShotDamage : damage;

        // Case 3: Character
        if (hit instanceof Player) {
            Player hitPlayer = (Player) hit;
            if (characterManager.hasTag(hitPlayer)) {
                Character character = characterManager.getCharacter(hitPlayer);
                if (character != null) {
                    character.damage(finalDamage);
                    return;
                }
            }
        }

        // Case 4: Player
        hit.setKiller(player);
        hit.damage(finalDamage);
    }

    private boolean isHeadshot(Entity entity, Location hitLocation) {
        if (!(entity instanceof LivingEntity)) return false;

        LivingEntity living = (LivingEntity) entity;
        Location eyeLocation = living.getEyeLocation();
        Location entityBottom = living.getLocation(); // Feet position

        double entityHeight = eyeLocation.getY() - entityBottom.getY();
        double headThreshold = entityBottom.getY() + (entityHeight * 0.75); // Upper 25% is head

        boolean headShot = hitLocation.getY() >= headThreshold;

        // Play sounds based on headshot or body shot
        if (headShot) {
            player.playSound(player.getLocation(), "damage.head", 100, 1);
        } else {
            player.playSound(player.getLocation(), "damage.body", 100, 1);
        }

        return headShot;
    }

    public abstract void fire();
    protected abstract void onHit(LivingEntity hitEntity, Block block, Location hitLocation);
}
