package org.randomlima.minevelcraftvels.Utils;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.Characters.Character;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class Projectile {
    private Player player;
    private ProjectileType projectileType;
    private double metersPerTick;
    private boolean bulletDrop;
    private double dropMultiplier;
    private int damage;
    private int headShotDamage;
    private double range;
    private final CharacterManager characterManager;
    private final MinevelCraftvels minevelCraftvels;
    public Projectile(Player player, ProjectileType projectileType, int damage, int headShotDamage, MinevelCraftvels plugin){
        this.player = player;
        this.projectileType = projectileType;
        this.damage = damage;
        this.headShotDamage = headShotDamage;

        range = 50; // default to 50
        metersPerTick = 1; // default to 1

        minevelCraftvels = plugin;
        characterManager = new CharacterManager(plugin);
    }
    public void setMetersPerTick(double metersPerTick){
        this.metersPerTick = metersPerTick;
    }
    public void setBulletDrop(boolean bulletDrop){
        this.bulletDrop = bulletDrop;
    }
    public void setBulletDropMultiplier(double drop){
        dropMultiplier = drop;
    }
    public void fire(){
        if(projectileType.name().equals("HITSCAN"))fireHitScan();
        if(projectileType.name().equals("PROJECTILE"))fireProjectile();
    }
    public void setRange(double range){
        this.range = range;
    }
    public void fireHitScan(){
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection().normalize();
        RayTraceResult result = start.getWorld().rayTrace(start, direction, range, FluidCollisionMode.NEVER, true, 0.2, entity -> !entity.equals(player));
        rayDamage(result);
    }
    public void fireProjectile() {
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection().normalize();

        new BukkitRunnable() {
            double traveled = 0;
            Location currentLoc = start.clone();
            Vector velocity = direction.clone().multiply(metersPerTick);

            @Override
            public void run() {
                if (traveled >= range) {
                    cancel();
                    return;
                }

                // Spawn a particle at the current position
                currentLoc.getWorld().spawnParticle(Particle.CRIT, currentLoc, 1, 0, 0, 0, 0);

                // Apply bullet drop
                if (bulletDrop) {
                    velocity.setY(velocity.getY() - dropMultiplier);
                }

                // Move the projectile
                currentLoc.add(velocity);

                // Check for collisions
                RayTraceResult result = currentLoc.getWorld().rayTrace(currentLoc, velocity, metersPerTick,
                        FluidCollisionMode.NEVER, true, 0.2, entity -> !entity.equals(player));

                if (result != null) {
                    rayDamage(result); // Apply damage or break block
                    cancel();
                }

                traveled += metersPerTick;
            }
        }.runTaskTimer(minevelCraftvels, 1L, 1L); // Runs every tick (20 times per second)
    }

    private void rayDamage(RayTraceResult result) {
        if (result == null) return;

        // Case 1: block
        if (result.getHitBlock() != null) {
            //result.getHitBlock().breakNaturally();
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
}
