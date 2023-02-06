package me.gameisntover.iranmcreportsspigot.ticket;

import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;

import java.util.HashMap;
import java.util.Map;

public class HelpMeReport extends Ticket{
    public static Map<Long,HelpMeReport> ticketMap = new HashMap<>();
    public HelpMeReport(PlayerImpl ticketCreator, String title) {
        super(Type.HELPME, ticketCreator, title);
        ticketMap.put(getTicketId(),this);
    }
}
