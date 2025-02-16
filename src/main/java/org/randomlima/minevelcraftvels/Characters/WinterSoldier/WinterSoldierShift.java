package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

import java.util.List;

public class WinterSoldierShift {
    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;
    public WinterSoldierShift(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        characterManager = new CharacterManager(plugin);
    }
    public void use(Player player){
        player.playSound(player.getLocation(), "wintersoldier.uppercut", 2, 1);
        player.sendMessage(Colorize.format("&6Used Shift -"));

        Vector direction = player.getEyeLocation().getDirection().normalize();

        player.setVelocity(direction.multiply(2));

        new BukkitRunnable() {
            @Override
            public void run() {
                player.setVelocity(new Vector(0,0,0));
                player.playSound(player, "damage.body",1,1);
                for(Entity e : player.getNearbyEntities(1.5,1.5,1.5)){
                    if(e instanceof LivingEntity){
                        LivingEntity target = (LivingEntity) e;
                        target.damage(10);
                        target.setKiller(player);
                        target.setVelocity(e.getVelocity().setY(1));
                    }
                }
                player.setVelocity(player.getVelocity().setY(1));
            }
        }.runTaskLater(minevelCraftvels, 10L);
    }
    private void damageEntity(LivingEntity entity) {
        int damage = 10;
        entity.damage(damage);
    }
}
