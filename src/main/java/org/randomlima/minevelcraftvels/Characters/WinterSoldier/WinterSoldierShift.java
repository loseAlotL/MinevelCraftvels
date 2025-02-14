package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

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
    }
}
