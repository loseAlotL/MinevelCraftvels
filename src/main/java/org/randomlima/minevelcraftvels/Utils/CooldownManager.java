package org.randomlima.minevelcraftvels.Utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.randomlima.minevelcraftvels.MinevelCraftvels;

public class CooldownManager {
    private final MinevelCraftvels minevelCraftvels;
    private double cooldown;
    private double cooldownLeft = 0;
    private int maxCharges;
    private int currentCharges;
    private final BukkitScheduler scheduler;
    private boolean isTaskRunning = false;
    private final ItemStack item;

    public CooldownManager(MinevelCraftvels plugin, double cooldown, int maxCharges, ItemStack item) {
        minevelCraftvels = plugin;
        this.cooldown = cooldown;
        this.maxCharges = maxCharges;
        this.currentCharges = maxCharges;
        this.scheduler = plugin.getServer().getScheduler();
        this.item = item;
    }

    public void useCharge() {
        if (currentCharges > 0) {
            currentCharges--;
            if (currentCharges == maxCharges - 1) {
                startCooldown();
            }
        }
    }

    public boolean hasCharges() {
        return currentCharges > 0;
    }

    public int getCurrentCharges() {
        return currentCharges;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    public void startCooldown() {
        if (cooldownLeft > 0) return;
        this.cooldownLeft = cooldown;
        run();
    }

    public void setCooldownLeft(double cooldownLeft) {
        this.cooldownLeft = cooldownLeft;
    }

    public boolean isOnCooldown() {
        return cooldownLeft > 0;
    }

    public int getCooldownLeftInt() {
        return (int) cooldownLeft;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void changeCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public void stopTask() {
        isTaskRunning = false;
    }

    public boolean checkAndStartCooldown() {
        if (isOnCooldown()) return true;
        startCooldown();
        return false;
    }

    private void run() {
        if (isTaskRunning) return;
        isTaskRunning = true;
        scheduler.runTaskTimer(minevelCraftvels, task -> {
            if (!isTaskRunning) task.cancel();
            if (cooldownLeft > 0) {
                cooldownLeft -= 0.25;
                updateItemDurability();
            }
            if (cooldownLeft <= 0) {
                if (currentCharges < maxCharges) {
                    currentCharges++;
                    if (currentCharges < maxCharges) {
                        cooldownLeft = cooldown;
                    }
                }
                stopTask();
                task.cancel();
                resetItemDurability();
            }
        }, 5L, 5L);
    }

    private void updateItemDurability() {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        short maxDurability = item.getType().getMaxDurability(); // Directly get the max durability
        int durability = (int) ((cooldownLeft / cooldown) * maxDurability);
        meta.setCustomModelData(durability); // Simulating durability change
        item.setItemMeta(meta);
    }

    private void resetItemDurability() {
        if (item == null) return;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;
        meta.setCustomModelData(0); // Reset durability when cooldown is complete
        item.setItemMeta(meta);
    }

}

