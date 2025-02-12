package org.randomlima.minevelcraftvels;

import org.bukkit.plugin.java.JavaPlugin;
import org.randomlima.minevelcraftvels.Abilities.AbilityListener;
import org.randomlima.minevelcraftvels.Characters.WinterSoldier.UltWinterSoldier;
import org.randomlima.minevelcraftvels.Commands.SetCharacterCommand;
import org.randomlima.minevelcraftvels.Commands.SetCharacterTabComplete;
import org.randomlima.minevelcraftvels.Utils.NametagManager;

public final class MinevelCraftvels extends JavaPlugin {
    private NametagManager nametagManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.nametagManager = new NametagManager(this);

        getServer().getPluginManager().registerEvents(new UltWinterSoldier(this), this);
        getServer().getPluginManager().registerEvents(new AbilityListener(this), this);

        this.getCommand("setcharacter").setExecutor(new SetCharacterCommand(this));
        this.getCommand("setcharacter").setTabCompleter(new SetCharacterTabComplete());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        nametagManager.clean();
    }
}
