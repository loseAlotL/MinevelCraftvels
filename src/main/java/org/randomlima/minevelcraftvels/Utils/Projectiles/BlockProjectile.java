package org.randomlima.minevelcraftvels.Utils.Projectiles;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.bukkit.entity.BlockDisplay;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class BlockProjectile extends Projectile {
    private final MinevelCraftvels minevelCraftvels;
    private final Material mat1;
    private final Material mat2;
    private BlockDisplay eblockDisplay1;
    private BlockDisplay eblockDisplay2;

    public BlockProjectile(Player player, int damage, int headShotDamage, MinevelCraftvels plugin, Material mat1, Material mat2) {
        super(player, damage, headShotDamage, plugin);
        minevelCraftvels = plugin;
        this.mat1 = mat1;
        this.mat2 = mat2;
    }

    @Override
    public void fire() {
        Location start = player.getEyeLocation();
        Vector direction = start.getDirection().normalize();
        new BukkitRunnable() {
            double traveled = 0;
            Location currentLoc = start.clone();
            Vector velocity = direction.clone().multiply(metersPerTick);
            BlockDisplay lastBlockDisplay1 = null;  // Track the first BlockDisplay
            BlockDisplay lastBlockDisplay2 = null;  // Track the second BlockDisplay

            @Override
            public void run() {
                if (traveled >= range) {
                    cancel();
                    eblockDisplay1.remove();
                    eblockDisplay2.remove();
                    return;
                }



                if (bulletDrop) {
                    Double dist = currentLoc.distance(start);
                    currentLoc.setY(currentLoc.getY() - (dist/200)*dropMultiplier);
                }

                BlockDisplay blockDisplay1 = currentLoc.getWorld().spawn(currentLoc, BlockDisplay.class);
                blockDisplay1.setBlock(mat1.createBlockData());
                Transformation transformation1 = new Transformation(
                        new Vector3f(), // Translation. This constructor uses 0 for all components
                        new AxisAngle4f(), // Left rotation. This constructor is 0 rotation around the z axis (i.e. no rotation)
                        new Vector3f(1, 1, 1), // Here's your scale, 2 on x and z axis, 5 on y
                        new AxisAngle4f() // Right rotation
                );
                blockDisplay1.setTransformation(transformation1);

                BlockDisplay blockDisplay2 = currentLoc.getWorld().spawn(currentLoc, BlockDisplay.class);
                blockDisplay2.setBlock(mat2.createBlockData());
                Transformation transformation2 = new Transformation(
                        new Vector3f(0.125f,0.125f,0.125f), // Translation. This constructor uses 0 for all components
                        new AxisAngle4f(), // Left rotation. This constructor is 0 rotation around the z axis (i.e. no rotation)
                        new Vector3f(0.75f, 0.75f, 0.75f), // Here's your scale, 2 on x and z axis, 5 on y
                        new AxisAngle4f() // Right rotation
                );
                blockDisplay2.setTransformation(transformation2);

                if (lastBlockDisplay1 != null) {
                    lastBlockDisplay1.remove();
                }
                if (lastBlockDisplay2 != null) {
                    lastBlockDisplay2.remove();
                }

                lastBlockDisplay1 = blockDisplay1;
                lastBlockDisplay2 = blockDisplay2;
                eblockDisplay1 = blockDisplay1;
                eblockDisplay2 = blockDisplay2;


                currentLoc.add(velocity);
                RayTraceResult result = currentLoc.getWorld().rayTrace(currentLoc, velocity, metersPerTick, FluidCollisionMode.NEVER, true, 0.2, entity -> !entity.equals(player));

                if (result != null) {
                    cancel();
                    rayDamage(result);
                    eblockDisplay1.remove();
                    eblockDisplay2.remove();
                    return;
                }

                traveled += metersPerTick;
            }
        }.runTaskTimer(minevelCraftvels, 1L, 1L);
    }

    @Override
    protected void onHit(LivingEntity hitEntity, Block block, Location hitLocation) {

        player.sendMessage("you hit: " + hitEntity + block + hitLocation);
        player.getWorld().createExplosion(hitLocation, 3,false,false,player);
    }
}
