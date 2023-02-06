package me.gameisntover.iranmcreportsspigot.ui.guis;

import com.cryptomorin.xseries.XMaterial;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ui.InventoryButton;
import me.gameisntover.iranmcreportsspigot.ui.InventoryGUI;
import me.gameisntover.iranmcreportsspigot.ui.InventoryManager;
import org.bukkit.ChatColor;

import static me.gameisntover.iranmcreportsspigot.util.StringUtil.config;

public class ReportPlayerGUI extends InventoryGUI {
    public ReportPlayerGUI(PlayerImpl sender, PlayerImpl player) {
        super("&cChoose a reason to report " + player.getName(), 5);
        inventoryPackCreateConsumer = pack -> {
            pack.setItem(pack.getSize()-1, InventoryButton.nextButton(this));
            pack.setItem(pack.getSize()-10,InventoryButton.previousButton(this));
        };
        fillEmpties();
        for (String reason :
                config().getStringList("report.reasons")) {
            addItem(InventoryButton.builder().mat(XMaterial.RED_TERRACOTTA).name(ChatColor.RED + reason).lores("&7Click to report " + player.getName()).build());
        }
    }
}
