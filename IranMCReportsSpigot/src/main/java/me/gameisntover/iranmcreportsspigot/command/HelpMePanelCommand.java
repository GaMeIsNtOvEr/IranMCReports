package me.gameisntover.iranmcreportsspigot.command;

import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ui.guis.HelperPanelGUI;
import me.gameisntover.iranmcreportsspigot.ui.guis.ReportPanelGUI;
import org.bukkit.permissions.PermissionDefault;

public class HelpMePanelCommand extends IRCommand{
    protected HelpMePanelCommand() {
        super("helpmepanel","baraye baz kardan panel helpme va moshahedeye anha" , PermissionDefault.OP);
    }


    @Override
    public boolean perform(PlayerImpl sender, String[] args) {
        sender.openGUI(new HelperPanelGUI(sender));
        return true;
    }
}
