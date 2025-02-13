package org.randomlima.minevelcraftvels.Characters.CloakAndDagger;

import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Abilities.AbilityHandler;
import org.randomlima.minevelcraftvels.Abilities.AbilityList;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

public class HandlerCloakAndDagger implements AbilityHandler {
    private final MinevelCraftvels minevelCraftvels;
    public HandlerCloakAndDagger(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
    }
    @Override
    public void onUse(Player player, AbilityList ability){
        switch (ability) {
//            case PRIMARY -> winterSoldierPrimary.use(player);
//            case RIGHT_CLICK -> winterSoldierRightClick.use(player);
//            case SHIFT -> winterSoldierShift.use(player);
//            case E -> winterSoldierE.use(player );
//            case ULTIMATE ->winterSoldierUltimate.use(player);
            default -> player.sendMessage(Colorize.format("&cThis ability is not assigned! -"));
        }
    }
}
