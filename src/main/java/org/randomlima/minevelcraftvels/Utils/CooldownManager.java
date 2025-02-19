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
    private ItemStack item;

    public CooldownManager(MinevelCraftvels plugin, double cooldown, int maxCharges) {
        minevelCraftvels = plugin;
        this.cooldown = cooldown;
        this.maxCharges = maxCharges;
        this.currentCharges = maxCharges;
        this.scheduler = plugin.getServer().getScheduler();
    }

    public void useCharge(ItemStack item) {
        if (currentCharges > 0) {
            currentCharges--;
            if (currentCharges == maxCharges - 1) {
                startCooldown(item);
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

    public void startCooldown(ItemStack item) {
        if (cooldownLeft > 0) return;
        this.cooldownLeft = cooldown;
        this.item = item;
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
        startCooldown(item);
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
                task.cancel();
                endCDLogic();
            }
        }, 5L, 5L);
    }

    private void updateItemDurability() {
        if (item == null || cooldown <= 0) return;
        ItemMeta meta = item.getItemMeta();
        org.bukkit.inventory.meta.Damageable dMeta = (org.bukkit.inventory.meta.Damageable) meta;
        short maxDurability = item.getType().getMaxDurability();
        int durability = (int) ((cooldownLeft / cooldown) * maxDurability);
        durability = Math.max(0, Math.min(maxDurability, durability));
        dMeta.setDamage(durability);
        item.setItemMeta(dMeta);
    }
    private void endCDLogic(){
        stopTask();
        resetItemDurability();
    }

    private void resetItemDurability() {
        ItemMeta meta = item.getItemMeta();
        org.bukkit.inventory.meta.Damageable dMeta = (org.bukkit.inventory.meta.Damageable) meta;
        dMeta.resetDamage();
        item.setItemMeta(dMeta);
    }

}

