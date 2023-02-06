package me.gameisntover.iranmcreportsspigot.player;

import me.gameisntover.iranmcreportsspigot.channel.IRChannel;
import me.gameisntover.iranmcreportsspigot.ticket.Ticket;
import org.bukkit.Location;

import java.util.UUID;

public interface PlayerInfo {
    boolean canOpenTicket();

    boolean isStaff();

    boolean canAnswerTicket();

    boolean isOnline();

    void sendMessage(String message);

    void sendData(byte[] bytes, IRChannel channel);

    Location getLocation();

    Ticket createTicket(Ticket.Type type, String title);

    UUID getUniqueId();

    String getName();

    Ticket getCurrentTicket();
}
