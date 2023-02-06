package me.gameisntover.iranmcreportsspigot.ui;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class InventoryGUI {
    protected Consumer<InventoryOpenEvent> openConsumer = inventoryOpenEvent -> {
    };
    protected Consumer<InventoryCloseEvent> closeConsumer = inventoryOpenEvent -> {
    };
    protected Consumer<InventoryPack> inventoryPackCreateConsumer = inventoryPack -> {
    };
    private final List<InventoryPack> packs = new ArrayList<>();

    protected InventoryGUI(String name, int slots) {
        packs.add(new InventoryPack(name, slots, this));
    }


    public void setItem(Integer index, InventoryButton button) {
        for (InventoryPack pack : packs) {
            searchForIndex(index, inventoryPack -> {
                pack.setItem(index, button);
            });
        }
    }

    public void addItem(InventoryButton button) {
        for (InventoryPack pack : packs)
            for (int i = 0; i < pack.getSize() - 1; i++) {
                if (!pack.contains(i)) {
                    pack.setItem(i, button);
                    break;
                } else if (packs.indexOf(pack) == packs.size() - 1) {
                    createPack(packs.get(0).getSize());
                    packs.get(packs.size() - 1).setItem(0, button);
                    break;
                }
            }
    }

    public void createPack(int size) {
        packs.add(new InventoryPack(packs.get(0).getTitle(), size, this));

    }

    public void fillEmpties() {
        for (InventoryPack pack : packs)
            for (int i = 0; i < pack.getSize(); i++) {
                if (!pack.contains(i)) pack.setItem(i, InventoryButton.empty());
            }
    }


    public InventoryButton get(int index) {
        final InventoryButton[] btn = new InventoryButton[]{null};
        for (InventoryPack pack : packs) {
            searchForIndex(index, inventoryPack -> {
                btn[0] = pack.get(index);
            });
        }
        return null;
    }

    public boolean contains(int index) {
        final boolean[] t = {false};
        for (InventoryPack pack : packs) searchForIndex(index, iPack -> t[0] = pack.contains(index));
        return t[0];
    }

    public void searchForIndex(int index, Consumer<InventoryPack> con) {
        for (InventoryPack pack : packs) {
            if (index > pack.getSize() - 1) {
                index -= pack.getSize() - 1;
            } else con.accept(pack);
        }
    }

    public void onOpenInventoryEvent(Consumer<InventoryOpenEvent> e) {
        this.openConsumer = e;
    }

    public void onCloseInventoryEvent(Consumer<InventoryCloseEvent> e) {
        this.closeConsumer = e;
    }

    public List<InventoryPack> getPacks() {
        return packs;
    }


}
