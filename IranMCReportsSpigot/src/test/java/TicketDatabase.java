import lombok.NonNull;
import lombok.SneakyThrows;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.sql.DBObject;
import me.gameisntover.iranmcreportsspigot.sql.Database;
import me.gameisntover.iranmcreportsspigot.sql.DatabaseInfo;
import me.gameisntover.iranmcreportsspigot.sql.SQLResult;
import me.gameisntover.iranmcreportsspigot.ticket.Ticket;

import java.sql.ResultSet;
import java.util.UUID;

public class TicketDatabase extends Database {
    public final DBObject<String> ticketId = DBObject.of("id", "INT8", true, true);
    public final DBObject<String> lore = DBObject.of("lore", "TEXT");
    public final DBObject<String> creator = DBObject.of("creator", "VARCHAR(36)");
    public final DBObject<String> status = DBObject.of("status", "TEXT");
    public final DBObject<String> dateCreated = DBObject.of("dateCreated", "Date");
    private final DBObject<String> time = DBObject.of("timeCreated", "Time");
    private final DBObject<String> type = DBObject.of("type", "TEXT");
    public final String tableName = "tickets";

    public TicketDatabase() {
        super(new DatabaseInfo("testdb", "localhost", 3306, "root", ""));
        createNewTable(tableName, ticketId, type, creator, lore, status, dateCreated, time);
    }

    public void updateStatus(Ticket ticket) {
        updateData(tableName, ticketId.clone(ticket.getTicketId()), status.clone(ticket.getStatus()), type.clone(ticket.getType().toString()), lore.clone(ticket.getTitle()), dateCreated.clone(ticket.getDateCreated().toString()), time.clone(ticket.getTimeCreated().toString()), creator.clone(ticket.getCreator().getUniqueId().toString()));
    }

    public void insertData(Ticket ticket) {
        insertData(tableName, ticketId.clone(ticket.getTicketId()), type.clone(ticket.getType().toString()), lore.clone(ticket.getTitle()), dateCreated.clone(ticket.getDateCreated().toString()), time.clone(ticket.getTimeCreated().toString()), status.clone(ticket.getStatus()), creator.clone(ticket.getCreator().getUniqueId().toString()));
    }

    @SneakyThrows
    @NonNull
    public Ticket syncData(long ticketId) {
        SQLResult sql = selectData(tableName, this.ticketId.clone(ticketId));
        if (sql.getResultSet() != null) {
            ResultSet rs = sql.getResultSet();
            if (rs.next()) {
                Ticket ticket = new Ticket(ticketId, Ticket.Type.valueOf(rs.getString(type.name)), PlayerImpl.of(UUID.fromString(rs.getString(creator.name))),
                        rs.getString(lore.name), rs.getString(status.name), rs.getDate(dateCreated.name), rs.getTime(time.name));
                sql.close();
                return ticket;
            }
        }
        return null;
    }


    @SneakyThrows
    public void getOpenHelpMeList() {
        SQLResult sqlResult = selectData(tableName, status.clone(Ticket.OPEN), type.clone(Ticket.Type.HELPME));
        ResultSet rs = sqlResult.getResultSet();
        do {
            Ticket ticket = new Ticket(rs.getLong(ticketId.name), Ticket.Type.valueOf(rs.getString(type.name)), PlayerImpl.of(UUID.fromString(rs.getString(creator.name))),
                    rs.getString(lore.name), rs.getString(status.name), rs.getDate(dateCreated.name), rs.getTime(time.name));
        } while (rs.next());
        sqlResult.close();
    }

    @SneakyThrows
    public void loadOpenReportList() {
        SQLResult sqlResult = selectData(tableName, status.clone(Ticket.OPEN), type.clone(Ticket.Type.REPORT));
        ResultSet rs = sqlResult.getResultSet();
        do {
            if (!Ticket.ticketMap.containsKey(rs.getLong(ticketId.name)))
                new Ticket(rs.getLong(ticketId.name), Ticket.Type.valueOf(rs.getString(type.name)), PlayerImpl.of(UUID.fromString(rs.getString(creator.name))),
                        rs.getString(lore.name), rs.getString(status.name), rs.getDate(dateCreated.name), rs.getTime(time.name));

        } while (rs.next());
        sqlResult.close();

    }

    @SneakyThrows
    public void loadOpenList() {
        SQLResult sqlResult = selectData(tableName, status.clone(Ticket.OPEN));
        ResultSet rs = sqlResult.getResultSet();
        while (rs.next()) {
            if (!Ticket.ticketMap.containsKey(rs.getLong(ticketId.name)))
                new Ticket(rs.getLong(ticketId.name), Ticket.Type.valueOf(rs.getString(type.name)), PlayerImpl.of(UUID.fromString(rs.getString(creator.name))),
                        rs.getString(lore.name), rs.getString(status.name), rs.getDate(dateCreated.name), rs.getTime(time.name));

        }
        sqlResult.close();
    }


}

