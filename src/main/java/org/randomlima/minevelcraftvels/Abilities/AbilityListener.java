package org.randomlima.minevelcraftvels.Abilities;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;
import org.randomlima.minevelcraftvels.Utils.NametagManager;

import java.util.ArrayList;
import java.util.List;

public class AbilityListener implements Listener {
    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;
    private List<Player> dropTickList = new ArrayList<>();
    public AbilityListener(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        characterManager = new CharacterManager(plugin);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setAllowFlight(false);
        if(!characterManager.hasTag(player))return;
        player.setAllowFlight(true);
    }
    @EventHandler
    public void onPlayerUlt(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        dropTickList.add(player);
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used ULT"));
        event.setCancelled(true);

        new BukkitRunnable() {
            @Override
            public void run() {
                dropTickList.remove(player);
            }
        }.runTaskLater(minevelCraftvels, 2);
    }
//    @EventHandler
//    public void onPlayerE(Pla event) {
//        System.out.println("eeeee");
//        Player player = event.getPlayer();
//        if(!characterManager.hasTag(player))return;
//        if (player.getOpenInventory().getTopInventory().getType() != InventoryType.CRAFTING)return;
//        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used E"));
//        event.setCancelled(true);
//    }
    @EventHandler
    public void onPlayerF(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used F"));
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(!event.isSneaking())return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used SHIFT"));
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerSprint(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        player.setSprinting(false);
        if(!characterManager.hasTag(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used CTRL"));
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used JUMP"));

    }
    @EventHandler
    public void onFlightAttempt(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(player.getGameMode() == GameMode.CREATIVE)return;
        event.setCancelled(true);
        player.setFlying(false);

        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used DOUBLE-JUMP"));

        Vector direction = player.getLocation().getDirection().multiply(0.9);
        direction.setY(0.5);
        player.setVelocity(direction);

    }
    @EventHandler
    public void onPlayerPrimary(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(!event.getAction().isLeftClick())return;
        if(dropTickList.contains(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used PRIMARY"));
        event.setCancelled(true);
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(!event.getAction().isRightClick())return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used RIGHT-CLICK"));
        event.setCancelled(true);
    }

}
