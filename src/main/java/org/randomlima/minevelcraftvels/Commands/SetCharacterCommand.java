package org.randomlima.minevelcraftvels.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.Characters.Characters;
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
            Characters character = Characters.valueOf(args[0].toUpperCase());
            characterManager.setCharacterTag(player, character);
            return true;

        } catch (IllegalArgumentException e) {
            player.sendMessage("Invalid character name.");
            return false;
        }
    }
}
