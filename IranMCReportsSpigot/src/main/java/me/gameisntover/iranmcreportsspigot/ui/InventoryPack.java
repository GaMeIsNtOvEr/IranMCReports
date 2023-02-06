package me.gameisntover.iranmcreportsspigot.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

import static me.gameisntover.iranmcreportsspigot.util.StringUtil.color;

public class InventoryPack {
    private final Inventory inventory;
    private final InventoryGUI base;
    public final Map<Integer, InventoryButton> buttonMap = new HashMap<>();

    public InventoryPack(String name, int slots, InventoryGUI zuper) {
        this.inventory = Bukkit.createInventory(null, slots, color(name));
        this.base = zuper;
        InventoryManager.guis.put(inventory, this);

    }


    public void setItem(Integer index, InventoryButton button) {
        this.inventory.setItem(index, button);
        this.buttonMap.put(index, button);

    }

    public void addItem(InventoryButton button) {
        for (int i = 0; i < inventory.getSize() - 1; i++) {
            if (!contains(i)) {
                setItem(i, button);
                break;
            }
        }
    }
    public int firstEmpty() {
        for (int i = 0; i < inventory.getSize() - 1; i++) {
            if (!contains(i)) {
                return i;
            }
        }
        return -1;
    }
    public void fillEmpties() {
        for (int i = 0; i < getSize(); i++) {
            if (!contains(i)) setItem(i, InventoryButton.empty());
        }
    }

    public InventoryButton get(Integer index) {
        return buttonMap.get(index);
    }

    public boolean contains(Integer i) {
        return buttonMap.containsKey(i) || get(i).getType().equals(Material.AIR);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getSize() {
        return getInventory().getSize();
    }

    public InventoryGUI getBase() {
        return base;
    }

    public String getTitle() {
        return getInventory().getTitle();
    }
}
