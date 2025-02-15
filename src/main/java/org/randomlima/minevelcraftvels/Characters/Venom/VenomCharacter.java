package org.randomlima.minevelcraftvels.Characters.Venom;

import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.CharacterInterface;

public class VenomCharacter implements CharacterInterface {
    @Override
    public void clearInventory(Player player) {
        player.getInventory().clear();
    }

    @Override
    public void setInventory(Player player) {
        player.sendMessage("set venom inven");
    }
}
