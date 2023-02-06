import lombok.SneakyThrows;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ticket.Ticket;
import org.junit.Test;


import java.util.UUID;

import static java.lang.System.*;

public class DatabaseTest {
    @Test
    public void select(){
        long ticketId = 0L;
        TicketDatabase ticketDatabase = new TicketDatabase();
        Ticket ticket = ticketDatabase.syncData(ticketId);
        out.println(ticket);
    }

    @Test
    public void selectAllOpen(){
        TicketDatabase ticketDatabase = new TicketDatabase();
        ticketDatabase.loadOpenList();
        out.println(Ticket.ticketMap);
    }

    @Test
    public void insertData(){
        TicketDatabase database = new TicketDatabase();
        database.insertData(new Ticket(Ticket.Type.HELPME, PlayerImpl.of(UUID.randomUUID()),"help im gay"));
    }

    @Test
    public void updateData(){
        TicketDatabase database = new TicketDatabase();
        database.updateStatus(new Ticket(Ticket.Type.REPORT, PlayerImpl.of(UUID.randomUUID()),"help im gay"));
    }
}
