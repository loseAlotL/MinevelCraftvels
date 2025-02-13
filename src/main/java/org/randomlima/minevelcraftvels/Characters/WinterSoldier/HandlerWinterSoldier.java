package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Abilities.AbilityHandler;
import org.randomlima.minevelcraftvels.Abilities.AbilityList;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

public class HandlerWinterSoldier implements AbilityHandler {
    private final MinevelCraftvels minevelCraftvels;
    private WinterSoldierPrimary winterSoldierPrimary;
    private WinterSoldierRightClick winterSoldierRightClick;
    private  WinterSoldierShift winterSoldierShift;
    private WinterSoldierE winterSoldierE;
    private WinterSoldierUltimate winterSoldierUltimate;
    public HandlerWinterSoldier(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
        winterSoldierPrimary = new WinterSoldierPrimary(minevelCraftvels);
        winterSoldierRightClick = new WinterSoldierRightClick(minevelCraftvels);
        winterSoldierShift = new WinterSoldierShift(minevelCraftvels);
        winterSoldierE = new WinterSoldierE(minevelCraftvels);
        winterSoldierUltimate = new WinterSoldierUltimate(minevelCraftvels);
    }
    @Override
    public void onUse(Player player, AbilityList ability){
        switch (ability) {
            case PRIMARY -> winterSoldierPrimary.use(player);
            case RIGHT_CLICK -> winterSoldierRightClick.use(player);
            case SHIFT -> winterSoldierShift.use(player);
            case E -> winterSoldierE.use(player );
            case ULTIMATE ->winterSoldierUltimate.use(player);
            default -> player.sendMessage(Colorize.format("&cThis ability is not assigned! -"));
        }
    }
}
