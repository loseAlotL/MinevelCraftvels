package org.randomlima.minevelcraftvels.Commands;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.Character;
import org.randomlima.minevelcraftvels.Characters.CharacterInterface;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.Characters.CharacterList;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class SetCharacterCommand implements CommandExecutor {

    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;

    public SetCharacterCommand(MinevelCraftvels plugin) {
        characterManager = new CharacterManager(plugin);
        minevelCraftvels = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            return false;
        }

        try {
            if(args[0].equals("none")){
                characterManager.clearCharacterTag(player);
                return true;
            }
            CharacterList character = CharacterList.valueOf(args[0].toUpperCase());
            CharacterInterface cInterface = character.getCharacterClass();

            cInterface.clearInventory(player);
            cInterface.setInventory(player);

            characterManager.setCharacterTag(player, character);
            Character guy = new Character(player, CharacterList.valueOf(args[0].toUpperCase()), minevelCraftvels);
            return true;

        } catch (IllegalArgumentException e) {
            player.sendMessage("Invalid character name.");
            return false;
        }
    }
}
