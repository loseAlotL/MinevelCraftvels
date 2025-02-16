package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

public class WinterSoldierRightClick {
    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;
    public WinterSoldierRightClick(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        characterManager = new CharacterManager(plugin);
    }
    public void use(Player player) {
        player.playSound(player.getLocation(), "wintersoldier.grab", 2, 1);
        player.sendMessage(Colorize.format("&6Used Right-Click -"));

        new BukkitRunnable() {
            @Override
            public void run() {
                Location start = player.getEyeLocation();
                Vector direction = start.getDirection().normalize();
                RayTraceResult result = start.getWorld().rayTrace(start, direction, 15, FluidCollisionMode.NEVER, true, 0.2, entity -> entity instanceof LivingEntity && !entity.equals(player));

                if (result != null && result.getHitEntity() instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) result.getHitEntity();
                    Vector directionToPlayer = player.getLocation().toVector().subtract(target.getLocation().toVector()).normalize();
                    directionToPlayer.multiply(2);
                    target.setKiller(player);
                    target.damage(10);

                    target.setVelocity(directionToPlayer);
                }
            }
        }.runTaskLater(minevelCraftvels, 15L);
    }

}
