package me.gameisntover.iranmcreportsspigot.ticket;

import com.google.common.io.ByteArrayDataOutput;
import lombok.ToString;
import me.gameisntover.iranmcreportsspigot.channel.TicketsChannel;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.io.ByteStreams.newDataOutput;
import static me.gameisntover.iranmcreportsspigot.util.StringUtil.color;
import static me.gameisntover.iranmcreportsspigot.util.StringUtil.config;

@ToString
public class Ticket implements TicketInfo {
    private final Type type;
    private final PlayerImpl ticketCreator;
    private PlayerImpl support;

    private final String title;
    private long ticketId;

    public static Map<Long, Ticket> ticketMap = new HashMap<>();
    public static final String CLOSED = "CLOSED";
    public static final String OPEN = "OPEN";
    private String status = OPEN;
    private Date date;
    private Time time;

    public Ticket(Type type, PlayerImpl ticketCreator, String title) {
        this.type = type;
        this.ticketCreator = ticketCreator;
        this.title = color(title);
        this.date = Date.valueOf(LocalDate.now());
        this.time = Time.valueOf(LocalTime.now());
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            if (!ticketMap.containsKey(i)) {
                ticketId = i;
                break;
            }
        }
        ticketMap.put(ticketId, this);

    }

    protected Ticket(Type type, PlayerImpl ticketCreator, String title, String status) {
        this(type, ticketCreator, title);
        this.status = status;
    }

    public Ticket(long ticketId, Type type, PlayerImpl creator, String title, String status, Date dateCreated, Time timeCreated) {
        this.ticketId = ticketId;
        this.type = type;
        this.ticketCreator = creator;
        this.title = title;
        this.status = status;
        this.time = timeCreated;
        this.date = dateCreated;
        ticketMap.put(ticketId, this);
    }

    @Override
    public PlayerImpl getSupport() {
        return support;
    }

    @Override
    public PlayerImpl getCreator() {
        return ticketCreator;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Type getType() {
        return type;
    }



    @Override
    public long getTicketId() {
        return ticketId;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public Date getDateCreated() {
        return date;
    }


    public void setSupport(PlayerImpl support) {
        this.support = support;
        if (ticketCreator.isOnline()){
            ticketCreator.sendMessage(config().getMessage("ticket.answered").replace("%support%",support.getName()));
        }
    }

    @Override
    public Time getTimeCreated() {
        return time;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void endTicket() {
        status = CLOSED;

    }


    public static enum Type {
        HELPME,
        REPORT;
    }
}
