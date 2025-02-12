package org.randomlima.minevelcraftvels.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.Characters.Characters;

import java.util.ArrayList;
import java.util.List;

public class SetCharacterTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> suggestions = new ArrayList<>();

        if (sender instanceof Player && args.length == 1) {
            String input = args[0].toLowerCase();
            suggestions.add("none");
            for (Characters character : Characters.values()) {
                if (character.name().toLowerCase().startsWith(input)) {
                    suggestions.add(character.name().toLowerCase());
                }
            }
        }

        return suggestions;
    }
}
