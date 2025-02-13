package org.randomlima.minevelcraftvels.Abilities;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

import java.util.ArrayList;
import java.util.List;

public class AbilityListener implements Listener {
    private final MinevelCraftvels minevelCraftvels;
    private final AbilityManager abilityManager;
    private final CharacterManager characterManager;
    private List<Player> dropTickList = new ArrayList<>();
    public AbilityListener(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        abilityManager = new AbilityManager(plugin);
        characterManager = new CharacterManager(plugin);
    }
    private void triggerAbility(Player player, AbilityList ability) {
        if (!characterManager.hasTag(player)) return;

        String characterName = characterManager.getName(player);
        AbilityHandler handler = abilityManager.getHandler(characterName);

        if (handler != null) {
            handler.onUse(player, ability);
        }
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setAllowFlight(false);
        if(!characterManager.hasTag(player))return;
        player.setAllowFlight(true);
        player.sendActionBar();
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

        triggerAbility(player, AbilityList.ULTIMATE);
    }
    @EventHandler
    public void onPlayerF(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used F"));
        event.setCancelled(true);

        triggerAbility(player, AbilityList.F);
    }

    @EventHandler
    public void onPlayerShift(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(!event.isSneaking())return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used SHIFT"));
        event.setCancelled(true);

        triggerAbility(player, AbilityList.SHIFT);
    }
    @EventHandler
    public void onPlayerSprint(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        player.setSprinting(false);
        if(!characterManager.hasTag(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used CTRL"));
        event.setCancelled(true);

        triggerAbility(player, AbilityList.CTRL);
    }
    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used JUMP"));

        triggerAbility(player, AbilityList.SPACE);
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

        triggerAbility(player, AbilityList.DOUBLE_JUMP);
    }
    @EventHandler
    public void onPlayerPrimary(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(!event.getAction().isLeftClick())return;
        if(dropTickList.contains(player))return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used PRIMARY"));
        event.setCancelled(true);

        triggerAbility(player, AbilityList.PRIMARY);
    }
    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(!event.getAction().isRightClick())return;
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used RIGHT-CLICK"));
        event.setCancelled(true);

        triggerAbility(player, AbilityList.RIGHT_CLICK);
    }
    @EventHandler
    public void onPlayerE(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if(!characterManager.hasTag(player))return;
        if(event.getNewSlot() != 8)return;
        player.getInventory().setHeldItemSlot(0);
        player.sendMessage(Colorize.format(characterManager.getName(player) + " has used E"));

        triggerAbility(player, AbilityList.E);
    }
}
