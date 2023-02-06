package me.gameisntover.iranmcreportsspigot.ui;

import com.cryptomorin.xseries.XMaterial;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ui.guis.HelperPanelGUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import static me.gameisntover.iranmcreportsspigot.util.StringUtil.color;
import static me.gameisntover.iranmcreportsspigot.util.StringUtil.config;

public abstract class InventoryButton extends ItemStack {
    protected InventoryButton(XMaterial mat, String name) {
        super(mat.parseMaterial());
        setData(new MaterialData(mat.getId(), mat.getData()));

    }

    protected InventoryButton() {
        this(XMaterial.GRAY_STAINED_GLASS_PANE, " ");
    }

    public static InventoryButton nextButton(InventoryGUI gui) {
        return builder().mat(XMaterial.PLAYER_HEAD).skullSkin(config().getString("buttons.next.url")).lores((String[]) config().getStringList("buttons.next.lore").toArray()).clickEvent(e -> {
            PlayerImpl player = PlayerImpl.of(e.getWhoClicked().getUniqueId());
            InventoryPack current = InventoryManager.guis.get(e.getClickedInventory());
            int currentIndex = gui.getPacks().indexOf(current);
            if (gui.getPacks().size() != currentIndex + 1)
                player.openGUI(gui.getPacks().get(currentIndex + 1));
        }).name(config().getString("buttons.next.name")).build();
    }

    public static InventoryButton previousButton(InventoryGUI gui) {
        return builder().mat(XMaterial.PLAYER_HEAD).skullSkin(config().getString("buttons.previous.url")).lores((String[]) config().getStringList("buttons.previous.lore").toArray()).clickEvent(e -> {
            PlayerImpl player = PlayerImpl.of(e.getWhoClicked().getUniqueId());
            InventoryPack current = InventoryManager.guis.get(e.getClickedInventory());
            int currentIndex = gui.getPacks().indexOf(current);
            if (gui.getPacks().size() != currentIndex + 1)
                player.openGUI(gui.getPacks().get(currentIndex + 1));
        }).name(config().getString("buttons.previous.name")).build();
    }

    public abstract void onClick(InventoryClickEvent e);

    public void setName(String name) {
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(color(name));
        setItemMeta(meta);
    }

    public void setType(XMaterial material) {
        setType(material.parseMaterial());
        setData(new MaterialData(material.getId(), material.getData()));
    }

    public void addItemFlags(ItemFlag... fLags) {
        ItemMeta meta = getItemMeta();
        meta.addItemFlags(fLags);
        setItemMeta(meta);
    }

    public static ButtonBuilder builder() {
        return new ButtonBuilder();
    }

    public static InventoryButton empty() {
        return builder().build();
    }
}
