package me.gameisntover.iranmcreportsspigot.ticket;

import me.gameisntover.iranmcreportsspigot.player.PlayerInfo;

import java.sql.Date;
import java.sql.Time;

public interface TicketInfo {
    Date getDateCreated();
    Time getTimeCreated();
    PlayerInfo getSupport();
    PlayerInfo getCreator();
    String getTitle();
    String getStatus();
    Ticket.Type getType();
    long getTicketId();

}
