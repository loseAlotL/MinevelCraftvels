package org.randomlima.minevelcraftvels.Characters;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

import java.util.HashMap;
import java.util.Map;

public class CharacterManager {
    private final MinevelCraftvels minevelCraftvels;
    private static final NamespacedKey CHARACTER_KEY = new NamespacedKey("minevelcraftvels", "character");
    private static Map<Player, Character> characters = new HashMap<>();
    public CharacterManager(MinevelCraftvels plugin){
        minevelCraftvels = plugin;
    }
    public Character getCharacter(Player player){
        return characters.get(player);
    }
    public void addCharacterList(Player player, Character character){
        characters.put(player, character);
        System.out.println("Character list: ");
        System.out.println(characters.values());
    }
    public void removeCharacterList(Player player){
        characters.remove(player);
    }
    public void setCharacterTag(Player player, CharacterList character) {
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
    public boolean hasCharacterTag(Player player, CharacterList character) {
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
    public int getHealth(CharacterList character){
        int health = 0;
        switch (character.name()) {
            case "ADAM_WARLOCK":
                health = 250;
                break;
            case "BLACK_PANTHER":
                health = 300;
                break;
            case "BLACK_WIDOW":
                health = 250;
                break;
            case "BRUCE_BANNER":
                health = 200;
                break;
            case "CAPTAIN_AMERICA":
                health = 675;
                break;
            case "CLOAK_AND_DAGGER":
                health = 250;
                break;
            case "DOCTOR_STRANGE":
                health = 650;
                break;
            case "GROOT":
                health = 700;
                break;
            case "HAWKEYE":
                health = 250;
                break;
            case "HELA":
                health = 250;
                break;
            case "HULK":
                health = 650;
                break;
            case "HULK_MONSTER":
                health = 1400;
                break;
            case "INVISIBLE_WOMAN":
                health = 275;
                break;
            case "IRON_FIST":
                health = 250;
                break;
            case "IRON_MAN":
                health = 250;
                break;
            case "JEFF_THE_LAND_SHARK":
                health = 250;
                break;
            case "LOKI":
                health = 250;
                break;
            case "LUNA_SNOW":
                health = 275;
                break;
            case "MAGIK":
                health = 250;
                break;
            case "MAGNETO":
                health = 650;
                break;
            case "MANTIS":
                health = 275;
                break;
            case "MISTER_FANTASTIC":
                health = 350;
                break;
            case "MOON_KNIGHT":
                health = 250;
                break;
            case "NAMOR":
                health = 275;
                break;
            case "PENI_PARKER":
                health = 650;
                break;
            case "PSYLOCKE":
                health = 250;
                break;
            case "ROCKET_RACCOON":
                health = 250;
                break;
            case "SCARLET_WITCH":
                health = 250;
                break;
            case "SPIDER_MAN":
                health = 250;
                break;
            case "SQUIRREL_GIRL":
                health = 275;
                break;
            case "STAR_LORD":
                health = 250;
                break;
            case "STORM":
                health = 250;
                break;
            case "THE_PUNISHER":
                health = 275;
                break;
            case "THOR":
                health = 525;
                break;
            case "VENOM":
                health = 650;
                break;
            case "WINTER_SOLDIER":
                health = 275;
                break;
            case "WOLVERINE":
                health = 350;
                break;
        }
        return health;
    }
}
