package me.gameisntover.iranmcreportsspigot.channel;

import me.gameisntover.iranmcreportsspigot.IranMCReportsSpigot;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import org.bukkit.Bukkit;
import org.bukkit.plugin.messaging.PluginMessageListener;

public abstract class IRChannel implements PluginMessageListener {
    private final Type type;
    private final String name;

    private boolean registered;

    public IRChannel(String channelName, Type side) {
        this.name = channelName;
        this.type = side;
    }

    public IRChannel(String channelName) {
        this(channelName, Type.BOTH);
    }

    public void register() {
        switch (type) {
            case BOTH:
                Bukkit.getMessenger().registerIncomingPluginChannel(IranMCReportsSpigot.getInstance(), name, this);
                Bukkit.getMessenger().registerOutgoingPluginChannel(IranMCReportsSpigot.getInstance(), name);
                break;
            case INCOMING:
                Bukkit.getMessenger().registerIncomingPluginChannel(IranMCReportsSpigot.getInstance(), name, this);
                break;
            case OUTGOING:
                Bukkit.getMessenger().registerOutgoingPluginChannel(IranMCReportsSpigot.getInstance(), name);
                break;
        }
        registered = true;
    }

    public void unRegister() {
        switch (type) {
            case BOTH:
                Bukkit.getMessenger().unregisterIncomingPluginChannel(IranMCReportsSpigot.getInstance(), name, this);
                Bukkit.getMessenger().unregisterOutgoingPluginChannel(IranMCReportsSpigot.getInstance(), name);
                break;
            case INCOMING:
                Bukkit.getMessenger().unregisterIncomingPluginChannel(IranMCReportsSpigot.getInstance(), name, this);
                break;
            case OUTGOING:
                Bukkit.getMessenger().unregisterOutgoingPluginChannel(IranMCReportsSpigot.getInstance(), name);
                break;
        }
        registered = true;
    }


    public void sendData(byte[] b, PlayerImpl player) {
        player.sendData(b,this);
    }

    public boolean isRegistered() {
        return registered;
    }

    public String getName() {
        return name;
    }

    public static enum Type {
        INCOMING,
        OUTGOING,
        BOTH
    }

    public Type getType() {
        return type;

    }
}
