package org.randomlima.minevelcraftvels;

import org.bukkit.plugin.java.JavaPlugin;
import org.randomlima.minevelcraftvels.Abilities.AbilityListener;
import org.randomlima.minevelcraftvels.Characters.WinterSoldier.WinterSoldierUltimate;
import org.randomlima.minevelcraftvels.Commands.DamageCommand;
import org.randomlima.minevelcraftvels.Commands.HealCommand;
import org.randomlima.minevelcraftvels.Commands.SetCharacterCommand;
import org.randomlima.minevelcraftvels.Commands.SetCharacterTabComplete;
import org.randomlima.minevelcraftvels.Utils.MiscListeners;
import org.randomlima.minevelcraftvels.Utils.NametagManager;

public final class MinevelCraftvels extends JavaPlugin {
    private NametagManager nametagManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.nametagManager = new NametagManager(this);

        getServer().getPluginManager().registerEvents(new AbilityListener(this), this);
        getServer().getPluginManager().registerEvents(new MiscListeners(this), this);

        this.getCommand("setcharacter").setExecutor(new SetCharacterCommand(this));
        this.getCommand("setcharacter").setTabCompleter(new SetCharacterTabComplete());

        this.getCommand("heal").setExecutor(new HealCommand(this));
        this.getCommand("damage").setExecutor(new DamageCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        nametagManager.clean();
    }
}
