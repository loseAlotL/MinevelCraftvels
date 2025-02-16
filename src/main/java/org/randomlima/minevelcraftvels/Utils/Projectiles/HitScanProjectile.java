package org.randomlima.minevelcraftvels.Utils.Projectiles;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class HitScanProjectile extends Projectile{
    public HitScanProjectile(Player player, int damage, int headShotDamage, MinevelCraftvels plugin) {
        super(player, damage, headShotDamage, plugin);
    }
    @Override
    public void fire() {
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection().normalize();
        RayTraceResult result = start.getWorld().rayTrace(start, direction, range, FluidCollisionMode.NEVER, true, 0.2, entity -> !entity.equals(player));
        rayDamage(result);
    }

    @Override
    protected void onHit(LivingEntity hitEntity, Block block, Location hitLocation) {

    }
}
