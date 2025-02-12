package org.randomlima.minevelcraftvels.Characters;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class CharacterManager {
    private final MinevelCraftvels minevelCraftvels;
    private static final NamespacedKey CHARACTER_KEY = new NamespacedKey("minevelcraftvels", "character");
    public CharacterManager(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
    }
    public void setCharacterTag(Player player, Characters character) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        container.set(CHARACTER_KEY, PersistentDataType.STRING, character.name());
        player.sendMessage("You are now set to the character: " + character.name());
    }
    public void clearCharacterTag(Player player) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        if (container.has(CHARACTER_KEY, PersistentDataType.STRING)) {
            container.remove(CHARACTER_KEY);
            player.sendMessage("Your character has been cleared.");
        } else {
            player.sendMessage("You don't have a character set.");
        }
    }
    public boolean hasCharacterTag(Player player, Characters character) {
        PersistentDataContainer container = player.getPersistentDataContainer();
        return container.has(CHARACTER_KEY, PersistentDataType.STRING) &&
                container.get(CHARACTER_KEY, PersistentDataType.STRING).equals(character.name());
    }
    public boolean hasTag(Player player){
        PersistentDataContainer container = player.getPersistentDataContainer();
        return container.has(CHARACTER_KEY, PersistentDataType.STRING);
    }
    public String getName(Player player){
        PersistentDataContainer container = player.getPersistentDataContainer();
        return container.get(CHARACTER_KEY, PersistentDataType.STRING);
    }
}
