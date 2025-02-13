package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

public class WinterSoldierE {
    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;
    public WinterSoldierE(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        characterManager = new CharacterManager(plugin);
    }
    public void use(Player player){
        player.sendMessage(Colorize.format("&6Used E -"));
    }
}
