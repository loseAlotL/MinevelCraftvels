package org.randomlima.minevelcraftvels.Characters.WinterSoldier;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.randomlima.minevelcraftvels.Characters.CharacterInterface;
import org.randomlima.minevelcraftvels.Utils.Colorize;

import java.util.UUID;

public class WinterSoldierCharacter implements CharacterInterface {

    @Override
    public void clearInventory(Player player) {
        player.getInventory().clear();
    }

    @Override
    public void setInventory(Player player) {
        player.sendMessage("set bucky inven");

        clear(player);
        giveItem(player, Material.DIAMOND_SWORD, "winter_soldier_icon", 0);
        giveItem(player, Material.GOLDEN_SWORD, "ultimate", 8);
        giveItem(player, Material.IRON_SWORD, "winter_soldier_hook", 7);
        giveItem(player, Material.IRON_SWORD, "winter_soldier_up", 6);
        giveItem(player, Material.IRON_SWORD, "winter_soldier_goku", 5);
    }
    public void giveItem(Player player, Material material, String cmd, int slot){
        String command = "item replace entity @p container."+slot +" with minecraft:" + material.name().toLowerCase()+ "[minecraft:custom_model_data={strings:['" + cmd + "']}]";
        player.sendMessage(command);
        player.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }
    public void clear(Player player){
        String command = "clear "+player.getName();
        player.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
    }
}
