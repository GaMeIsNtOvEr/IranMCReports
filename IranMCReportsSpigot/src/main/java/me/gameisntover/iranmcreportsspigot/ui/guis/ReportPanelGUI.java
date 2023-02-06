package me.gameisntover.iranmcreportsspigot.ui.guis;

import com.cryptomorin.xseries.XMaterial;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ticket.Ticket;
import me.gameisntover.iranmcreportsspigot.ui.InventoryButton;
import me.gameisntover.iranmcreportsspigot.ui.InventoryGUI;

import static me.gameisntover.iranmcreportsspigot.ticket.Ticket.OPEN;
import static me.gameisntover.iranmcreportsspigot.ticket.Ticket.ticketMap;
import static me.gameisntover.iranmcreportsspigot.util.StringUtil.config;

public class ReportPanelGUI extends InventoryGUI {
    public ReportPanelGUI(PlayerImpl viewer) {
        super(config().getString("&cReport Panel"), 5);
        inventoryPackCreateConsumer = pack -> {
            pack.setItem(pack.getSize() - 1, InventoryButton.nextButton(this));
            pack.setItem(pack.getSize() - 10, InventoryButton.previousButton(this));
        };
        fillEmpties();
        for (Ticket ticket : ticketMap.values()) {
            if (ticket.getStatus().equalsIgnoreCase(OPEN) && ticket.getType().equals(Ticket.Type.REPORT)) {
                InventoryButton button = InventoryButton.builder().mat(XMaterial.GREEN_TERRACOTTA).name("&aTicket " + ticket.getTicketId())
                        .lores("&7Ticket Description:", "&6&u-----------------", "&7" + ticket.getTitle(), "&7" + ticket.getDateCreated().toString(), "&7" + ticket.getTimeCreated().toString(), "&6&u-----------------", "&7Click to accept!").clickEvent(event -> {
                            if (ticket.getSupport() == null)
                                ticket.setSupport(viewer);
                            else viewer.sendMessage(config().getMessage("ticket.already-being-answered"));
                        }).build();

                addItem(button);
            }
        }
    }
}
