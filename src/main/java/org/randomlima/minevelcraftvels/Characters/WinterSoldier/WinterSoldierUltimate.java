package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.Characters.CharacterList;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;
import org.randomlima.minevelcraftvels.Utils.NametagManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinterSoldierUltimate implements Listener {

    private final MinevelCraftvels minevelCraftvels;
    private CharacterManager characterManager;
    private NametagManager nametagManager;
    private List<Player> ultPlayers = new ArrayList<>();
    private List<Player> airPlayers = new ArrayList<>();

    public WinterSoldierUltimate(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        characterManager = new CharacterManager(minevelCraftvels);
        nametagManager = new NametagManager(minevelCraftvels);
    }
    public void use(Player player){
        player.sendMessage(Colorize.format("&6Used Ultimate -"));

        if(ultPlayers.contains(player)){
            player.playSound(player.getLocation(), "wintersoldier.ultimate1", 1, 1);
        } else {
            player.playSound(player.getLocation(), "wintersoldier.ultimate", 1, 1);
            ultPlayers.add(player);
        }
    }

//    private void launchPlayer(Player player) {
//        Vector velocity = new Vector(0, 1.7, 0);
//        player.setVelocity(velocity);
//        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 100, 75, false, false, false));
//        airPlayers.add(player);
//    }
//
//    @EventHandler
//    public void onLeftClick(PlayerInteractEvent event) {
//        Player player = event.getPlayer();
//        if (airPlayers.contains(player) && !player.isOnGround()) {
//            if (event.getAction().isLeftClick()) {
//                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
//                airPlayers.remove(player);
//                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
//
//                Vector dashDirection = player.getLocation().getDirection().multiply(3);
//                player.setVelocity(dashDirection);
//
//                new BukkitRunnable() {
//                    @Override
//                    public void run() {
//                        if (player.isOnGround()) {
//                            slam(player);
//                            airPlayers.remove(player);
//                            this.cancel();
//                        }
//                    }
//                }.runTaskTimer(minevelCraftvels, 0, 1);
//            }
//        }
//    }
//
//    private void slam(Player player){
//        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
//
//        for (Entity entity : player.getLocation().getNearbyEntities(5.0, 5.0, 5.0)) {
//            if (entity instanceof LivingEntity && entity != player) {
//                LivingEntity target = (LivingEntity) entity;
//                List<String> lines = Arrays.asList("&l&6[⚔]");
//                nametagManager.setNametag(target, lines, 1);
//                target.damage(10.0);
//            }
//        }
//    }
}
