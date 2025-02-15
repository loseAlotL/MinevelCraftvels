package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.Location;
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
                // Launch player
                player.setVelocity(new Vector(0,0,0));
                player.setVelocity(player.getVelocity().setY(1)); // Adjust upward force as needed
            }
        }.runTaskLater(minevelCraftvels, 10L);
    }
    private void damageEntity(LivingEntity entity) {
        int damage = 10; // Adjust base damage as needed
        entity.damage(damage); // Apply damage
    }
}
