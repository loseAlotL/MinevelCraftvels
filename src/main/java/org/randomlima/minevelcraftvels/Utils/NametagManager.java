package org.randomlima.minevelcraftvels.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TextDisplay;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

import java.util.*;

public class NametagManager {

    private final Map<UUID, TextDisplay> nameTags = new HashMap<>();
    private final Map<UUID, Integer> tasks = new HashMap<>();
    private final MinevelCraftvels plugin;

    public NametagManager(MinevelCraftvels plugin) {
        this.plugin = plugin;
    }

    public void setNametag(LivingEntity entity, List<String> lines, int interval) {
        removeNametag(entity);
        TextDisplay nametag = createNametag(entity, lines);
        startTrackingTask(entity, nametag, interval);
    }

    private TextDisplay createNametag(LivingEntity entity, List<String> lines) {
        Location loc = entity.getLocation().add(0, entity.getHeight() + 0.3, 0);
        TextDisplay nametag = (TextDisplay) entity.getWorld().spawnEntity(loc, EntityType.TEXT_DISPLAY);

        nametag.setText(Colorize.format(String.join("\n", lines)));
                nametag.setBillboard(org.bukkit.entity.TextDisplay.Billboard.CENTER);
        nametag.setSeeThrough(true);

        nameTags.put(entity.getUniqueId(), nametag);
        return nametag;
    }

    private void startTrackingTask(LivingEntity entity, TextDisplay nametag, int interval) {
        int taskID = new BukkitRunnable() {
            @Override
            public void run() {
                if (!entity.isValid() || nametag.isDead()) {
                    removeNametag(entity);
                    cancel();
                    return;
                }
                nametag.teleport(entity.getLocation().add(0, entity.getHeight() + 0.3, 0));
            }
        }.runTaskTimer(plugin, 0L, interval).getTaskId();

        tasks.put(entity.getUniqueId(), taskID);
    }

    public void removeNametag(LivingEntity entity) {
        UUID uuid = entity.getUniqueId();
        Optional.ofNullable(nameTags.remove(uuid)).ifPresent(Entity::remove);
        Optional.ofNullable(tasks.remove(uuid)).ifPresent(Bukkit.getScheduler()::cancelTask);
    }

    public void clean() {
        nameTags.values().forEach(Entity::remove);
        tasks.values().forEach(Bukkit.getScheduler()::cancelTask);
        nameTags.clear();
        tasks.clear();
    }
}
