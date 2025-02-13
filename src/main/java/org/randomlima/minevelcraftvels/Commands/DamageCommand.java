package org.randomlima.minevelcraftvels.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.Character;
import org.randomlima.minevelcraftvels.Characters.CharacterManager;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class DamageCommand implements CommandExecutor {

    private final MinevelCraftvels minevelCraftvels;
    private final CharacterManager characterManager;

    public DamageCommand(MinevelCraftvels plugin) {
        this.minevelCraftvels = plugin;
        this.characterManager = new CharacterManager(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return false;
        }

        Player player = (Player) sender;
        Character character = characterManager.getCharacter(player);

        if (character == null) {
            player.sendMessage("Character not found!");
            return false;
        }

        if (args.length == 0) {
            player.sendMessage("Please provide the amount of damage to apply.");
            return false;
        }

        int damageAmount;
        try {
            damageAmount = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            player.sendMessage("Invalid number! Please provide a valid integer for damage.");
            return false;
        }

        character.damage(damageAmount);

        player.sendMessage("You have taken " + damageAmount + " damage.");

        return true;
    }
}
