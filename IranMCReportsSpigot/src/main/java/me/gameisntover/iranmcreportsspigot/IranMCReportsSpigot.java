package me.gameisntover.iranmcreportsspigot;

import me.gameisntover.iranmcreportsspigot.database.TicketDatabase;
import org.bukkit.plugin.java.JavaPlugin;

public final class IranMCReportsSpigot extends JavaPlugin {

    private static IranMCReportsSpigot instance;
    private static TicketDatabase ticketDB;

    public static IranMCReportsSpigot getInstance() {
    return instance;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        ticketDB = new TicketDatabase();
    }


    public static TicketDatabase getTicketsDataBase() {
        return ticketDB;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
