package org.randomlima.minevelcraftvels;

import org.bukkit.plugin.java.JavaPlugin;
import org.randomlima.minevelcraftvels.Characters.WinterSoldier.UltWinterSoldier;
import org.randomlima.minevelcraftvels.Commands.SetCharacterCommand;
import org.randomlima.minevelcraftvels.Commands.SetCharacterTabComplete;

public final class MinevelCraftvels extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new UltWinterSoldier(this), this);
        this.getCommand("setcharacter").setExecutor(new SetCharacterCommand(this));
        this.getCommand("setcharacter").setTabCompleter(new SetCharacterTabComplete());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
