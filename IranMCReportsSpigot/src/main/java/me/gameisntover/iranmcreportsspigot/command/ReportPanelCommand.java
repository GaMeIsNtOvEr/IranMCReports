package me.gameisntover.iranmcreportsspigot.command;

import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ui.guis.ReportPanelGUI;
import org.bukkit.permissions.PermissionDefault;

public class ReportPanelCommand extends IRCommand{
    public ReportPanelCommand(){
        super("reportpanel","baraye baz kardan panel report ha va moshahedeye anha", PermissionDefault.OP);
    }

    @Override
    public boolean perform(PlayerImpl sender, String[] args) {
        sender.openGUI(new ReportPanelGUI(sender));
        return true;
    }
}
