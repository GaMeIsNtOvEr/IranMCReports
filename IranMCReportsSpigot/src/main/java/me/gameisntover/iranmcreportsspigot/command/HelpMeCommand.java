package me.gameisntover.iranmcreportsspigot.command;

import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ticket.Ticket;
import org.bukkit.permissions.PermissionDefault;

import static me.gameisntover.iranmcreportsspigot.util.StringUtil.config;

public class HelpMeCommand extends IRCommand {
    protected HelpMeCommand() {
        super("helpme", "command baraye darkhaste komak gereftan az helper", PermissionDefault.TRUE);

    }

    @Override
    public boolean perform(PlayerImpl sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(config().getMessage("more-arguements").replace("%usage%", "/helpme <matn help>"));
            return false;
        }
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            if (!builder.toString().isEmpty()) builder.append(" ");
            builder.append(arg);
        }
        if (sender.createTicket(Ticket.Type.HELPME, builder.toString()) != null) {
            sender.sendMessage(config().getMessage("ticket.opened"));
            return true;
        } else{
            sender.sendMessage(config().getMessage("ticket.already-ticket-opened"));
            return false;
        }

    }
}
