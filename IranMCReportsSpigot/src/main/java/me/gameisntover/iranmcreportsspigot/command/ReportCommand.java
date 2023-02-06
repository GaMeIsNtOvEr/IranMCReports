package me.gameisntover.iranmcreportsspigot.command;

import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ui.guis.ReportPlayerGUI;
import org.bukkit.Bukkit;
import org.bukkit.permissions.PermissionDefault;

import static me.gameisntover.iranmcreportsspigot.util.StringUtil.config;

public class ReportCommand extends IRCommand {
    protected ReportCommand() {
        super("report", "command baraye shekayat kardan az yek player", PermissionDefault.TRUE);

    }

    @Override
    public boolean perform(PlayerImpl sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(config().getMessage("more-arguements").replace("%usage%", "/report <playername>"));
            return false;
        }
        if (!Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {
            sender.sendMessage(config().getMessage("player-not-exists"));
            return false;
        }

        if (sender.getCurrentTicket() != null) {
            sender.sendMessage(config().getMessage("ticket.already-ticket-opened"));
            return false;
        } else {
            sender.openGUI(new ReportPlayerGUI(sender, PlayerImpl.of(Bukkit.getPlayer(args[0]))));
            return true;
        }

    }
}
