package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.Character;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;
import org.randomlima.minevelcraftvels.Utils.Projectile;
import org.randomlima.minevelcraftvels.Utils.ProjectileType;

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
//        Projectile projectile = new Projectile(player, ProjectileType.HITSCAN, 10, 20, minevelCraftvels);
//        projectile.setRange(50);
//        projectile.fire();
        Projectile projectile = new Projectile(player, ProjectileType.PROJECTILE, 75, 150, minevelCraftvels);
        projectile.setBulletDrop(false);
        projectile.setMetersPerTick(3);
        projectile.fire();
    }
}
