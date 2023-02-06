package me.gameisntover.iranmcreportsspigot.ui;

import me.gameisntover.iranmcreportsspigot.IranMCReportsSpigot;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.*;

public class InventoryManager implements Listener {
    private static InventoryManager instance;
    public static Map<Inventory, InventoryPack> guis = new HashMap<>();
    private InventoryManager(){
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, IranMCReportsSpigot.getInstance());
    }


    public static InventoryManager getInstance(){
        if (instance == null) instance = new InventoryManager();
        return instance;
    }

    public InventoryGUI createGUI(String title, int slots){
        return new InventoryGUI(title,slots);
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent e){
        if (!guis.containsKey(e.getClickedInventory())) return;
        InventoryPack gui = guis.get(e.getClickedInventory());
        if (!gui.contains(e.getSlot())) return;
        e.setCancelled(true);
        InventoryButton button = gui.get(e.getSlot());
        button.onClick(e);
    }

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent e){
        if (!guis.containsKey(e.getInventory())) return;
        InventoryPack gui = guis.get(e.getInventory());
        gui.getBase().openConsumer.accept(e);
    }
    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent e){
        if (!guis.containsKey(e.getInventory())) return;
        InventoryPack gui = guis.get(e.getInventory());
        gui.getBase().closeConsumer.accept(e);
    }

    public void removeInventory(InventoryPack gui){
        guis.remove(gui.getInventory());
    }
}
