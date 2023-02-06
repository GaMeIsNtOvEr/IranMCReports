package me.gameisntover.iranmcreportsspigot.ticket;

import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;

import java.util.HashMap;
import java.util.Map;

public class ReportTicket extends Ticket{
    public static Map<Long,ReportTicket> ticketMap = new HashMap<>();
    public ReportTicket(PlayerImpl ticketCreator, String reason) {
        super(Type.REPORT, ticketCreator, reason);
        ticketMap.put(getTicketId(),this);
    }


}
