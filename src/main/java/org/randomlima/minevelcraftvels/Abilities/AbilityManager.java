package org.randomlima.minevelcraftvels.Abilities;

import org.randomlima.minevelcraftvels.Characters.WinterSoldier.HandlerWinterSoldier;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

import java.util.HashMap;
import java.util.Map;

public class AbilityManager {
    private final Map<String, AbilityHandler> abilityHandlers = new HashMap<>();
    private final MinevelCraftvels minevelCraftvels;

    public AbilityManager(MinevelCraftvels plugin) {
        this.minevelCraftvels = plugin;
        registerAbilities();
    }

    private void registerAbilities() {
        abilityHandlers.put("WINTER_SOLDIER", new HandlerWinterSoldier(minevelCraftvels));
        // Add more characters...
    }

    public AbilityHandler getHandler(String characterName) {
        return abilityHandlers.get(characterName.toUpperCase());
    }
}
