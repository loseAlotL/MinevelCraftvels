package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;
import org.randomlima.minevelcraftvels.Utils.CooldownManager;
import org.randomlima.minevelcraftvels.Utils.Projectiles.BlockProjectile;
import org.randomlima.minevelcraftvels.Utils.Projectiles.Projectile;

public class WinterSoldierE {
    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;
    private final CooldownManager cooldownManager;
    public WinterSoldierE(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        cooldownManager = new CooldownManager(minevelCraftvels, 5, 1);
        characterManager = new CharacterManager(plugin);
    }
    public void use(Player player){
        if(cooldownManager.isOnCooldown())return;
        player.playSound(player.getLocation(), "wintersoldier.goku", 100, 1);
        player.sendMessage(Colorize.format("&6Used E -"));
        Projectile projectile = new BlockProjectile(player, 5,10,minevelCraftvels, Material.RED_STAINED_GLASS, Material.REDSTONE_BLOCK);
        projectile.fire();
        cooldownManager.useCharge(player.getInventory().getItemInMainHand());
    }
}
