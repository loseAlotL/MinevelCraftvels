package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;
import org.randomlima.minevelcraftvels.Utils.Projectiles.BulletProjectile;
import org.randomlima.minevelcraftvels.Utils.Projectiles.HitScanProjectile;
import org.randomlima.minevelcraftvels.Utils.Projectiles.Projectile;
import org.randomlima.minevelcraftvels.Utils.Projectiles.ProjectileType;

public class WinterSoldierPrimary {
    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;
    public WinterSoldierPrimary(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        characterManager = new CharacterManager(plugin);
    }
    public void use(Player player){
        player.playSound(player.getLocation(), "wintersoldier.primary", 100, 1);
        player.sendMessage(Colorize.format("&6Used Primary -"));
        Projectile projectile = new BulletProjectile(player, 5, 10, minevelCraftvels);
        projectile.setBulletDrop(false);
        projectile.setMetersPerTick(4);
        projectile.fire();
    }
}
