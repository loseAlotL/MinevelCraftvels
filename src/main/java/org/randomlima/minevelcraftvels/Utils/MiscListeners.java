package org.randomlima.minevelcraftvels.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class MiscListeners implements Listener {
    private final MinevelCraftvels minevelCraftvels;
    public MiscListeners(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
    }
    @EventHandler
    public void onDeath(EntityDeathEvent event){
        LivingEntity entity = event.getEntity();
        Player killer = event.getEntity().getKiller();
        if(killer != null){
            Player player = killer;
            killer.playSound(player, "damage.ko", 100, 1);
            killer.getInventory().setHelmet(new ItemStack(Material.CARVED_PUMPKIN));

            new BukkitRunnable() {
                @Override
                public void run() {
                    killer.getInventory().setHelmet(new ItemStack(Material.AIR));
                }
            }.runTaskLater(minevelCraftvels, 40);
        }
    }

}
