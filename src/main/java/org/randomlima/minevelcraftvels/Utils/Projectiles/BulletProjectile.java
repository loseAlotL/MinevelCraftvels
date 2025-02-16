package org.randomlima.minevelcraftvels.Utils.Projectiles;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class BulletProjectile extends Projectile{
    private final MinevelCraftvels minevelCraftvels;
    public BulletProjectile(Player player, int damage, int headShotDamage, MinevelCraftvels plugin) {
        super(player, damage, headShotDamage, plugin);
        minevelCraftvels = plugin;
    }

    @Override
    public void fire() {
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

                if (bulletDrop) {
                    Double dist = currentLoc.distance(start);
                    currentLoc.setY(currentLoc.getY() - (dist/200)*dropMultiplier);
                }
                currentLoc.getWorld().spawnParticle(Particle.CRIT, currentLoc, 1, 0, 0, 0, 0);
                currentLoc.add(velocity);
                RayTraceResult result = currentLoc.getWorld().rayTrace(currentLoc, velocity, metersPerTick, FluidCollisionMode.NEVER, true, 0.2, entity -> !entity.equals(player));

                if (result != null) {
                    rayDamage(result);
                    cancel();
                }

                traveled += metersPerTick;
            }
        }.runTaskTimer(minevelCraftvels, 1L, 1L);
    }

    @Override
    protected void onHit(LivingEntity hitEntity, Block block, Location hitLocation) {
        player.sendMessage("you hit: "+hitEntity + block + hitLocation);
    }
}
