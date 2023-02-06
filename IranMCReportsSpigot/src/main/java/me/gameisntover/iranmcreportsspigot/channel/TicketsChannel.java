package me.gameisntover.iranmcreportsspigot.channel;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.gameisntover.iranmcreportsspigot.player.PlayerImpl;
import me.gameisntover.iranmcreportsspigot.ticket.Ticket;
import me.gameisntover.iranmcreportsspigot.util.StringUtil;
import org.bukkit.entity.Player;

import static com.google.common.io.ByteStreams.newDataInput;
import static com.google.common.io.ByteStreams.newDataOutput;
import static me.gameisntover.iranmcreportsspigot.util.StringUtil.config;

public class TicketsChannel extends IRChannel {
    private static TicketsChannel instance;

    public TicketsChannel() {
        super("tickets", Type.BOTH);
        register();
        instance = this;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equalsIgnoreCase(getName())) return;
        PlayerImpl p = PlayerImpl.of(player);
        ByteArrayDataInput in = newDataInput(message);
        Ticket ticket = p.getCurrentTicket();
        switch (in.readUTF()){
            case "teleport":
                if (!p.isOnline()) return;
                p.teleport(p.getCurrentTicket().getCreator());
                p.sendMessage(config().getMessage("teleport-success"));
                break;

        }

    }

    public static TicketsChannel getInstance() {
        return instance;
    }

    public void sendData(String ticketId,byte[] b , PlayerImpl player){
        ByteArrayDataOutput out = newDataOutput();
        out.writeUTF(ticketId);
        out.write(b);
        player.sendData(out.toByteArray(),this);
    }
}
