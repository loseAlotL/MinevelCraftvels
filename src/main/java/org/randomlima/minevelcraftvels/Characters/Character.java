package org.randomlima.minevelcraftvels.Characters;

import org.bukkit.entity.Player;
import org.randomlima.minevelcraftvels.MinevelCraftvels;
import org.randomlima.minevelcraftvels.Utils.Colorize;

public class Character {
    private Player player;
    private static int maxHealth;
    private int health;
    private boolean alive;
    private int lifetimeKills, lifetimeDeaths, lifetimeAssists;
    private int kills, deaths, assists;
    private CharacterList character;
    private final CharacterManager characterManager;
    private final MinevelCraftvels minevelCraftvels;

    public Character(Player player, CharacterList character, MinevelCraftvels plugin){
        player.sendActionBar(Colorize.format("&6Character Instantiated - &c"+character.name()));
        minevelCraftvels = plugin;
        characterManager = new CharacterManager(plugin);
        this.character = character;

        characterManager.addCharacterList(player,this);

        health = characterManager.getHealth(character);
        maxHealth = health;
        this.player = player;
        alive = true;
        updateHealth();
    }
    public String getName(){
        return player.getName() + character.name();
    }
    public void damage(int i){
        health -= i;
        updateHealth();
    }
    public void heal(int i){
        health += i;
        health = Math.min(health, maxHealth);
        updateHealth();
    }
    public void setHealth(int i){
        health = i;
        updateHealth();
    }
    public int getHealth(){
        return health;
    }
    public void updateHealth(){
        alive = health > 0;
        if(health <= 0){
            player.damage(Integer.MAX_VALUE);
            return;
        }
        float hp = (float) health / maxHealth;
        hp = Math.min(hp, 1.0f);

        player.setExp(hp);
    }
}
