package me.gameisntover.iranmcreportsspigot.player;

import me.gameisntover.iranmcreportsspigot.IranMCReportsSpigot;
import me.gameisntover.iranmcreportsspigot.channel.IRChannel;
import me.gameisntover.iranmcreportsspigot.response.TextResponse;
import me.gameisntover.iranmcreportsspigot.ticket.Ticket;
import me.gameisntover.iranmcreportsspigot.ui.InventoryGUI;
import me.gameisntover.iranmcreportsspigot.ui.InventoryPack;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static me.gameisntover.iranmcreportsspigot.util.StringUtil.color;

public class PlayerImpl implements PlayerInfo{
    private static Map<UUID, PlayerImpl> playerMap = new HashMap<>();
    private final UUID uuid;


    private boolean staff;
    private Ticket ticket;
    private boolean online;

    protected PlayerImpl(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean canOpenTicket() {
        return getPlayer().hasPermission("iranminecraft.ticket.open");
    }

    @Override
    public boolean isStaff() {
        return staff;
    }

    @Override
    public boolean canAnswerTicket() {
        return getPlayer().hasPermission("iranminecraft.ticket.answer");
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public void sendMessage(String message) {

    }
    @Override
    public void sendData(byte[] b, IRChannel channel) {
        getPlayer().sendPluginMessage(IranMCReportsSpigot.getInstance(), channel.getName(), b);
    }

    @Override
    public Location getLocation() {
        return getPlayer().getLocation();
    }

    @Override
    public Ticket createTicket(Ticket.Type type, String title) {
        if (ticket == null) {
            Ticket ticket = new Ticket(type, this, title);
            this.ticket = ticket;
            return ticket;
        } else return null;
    }

    public static PlayerImpl of(UUID uuid){
        if (!playerMap.containsKey(uuid)) playerMap.put(uuid,new PlayerImpl(uuid));
        return playerMap.get(uuid);
    }

    public static PlayerImpl of(Player player){
        return of(player.getUniqueId());
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public String getName() {
        return getPlayer().getName();
    }

    @Override
    public Ticket getCurrentTicket() {
        return ticket;
    }

    public void teleport(PlayerImpl player) {
        getPlayer().teleport(player.getPlayer());
    }

    public void sendMessage(TextResponse response){
        String text = color(response.getValue());
        response.getSound().ifPresent(sound -> playSound(sound,1,1));
        BaseComponent[] component = TextComponent.fromLegacyText(text);
        for (BaseComponent baseComponent : component) {
            response.getClickEvent().ifPresent(clickEvent -> baseComponent.setClickEvent(clickEvent));
            response.getHoverEvent().ifPresent(hoverEvent -> baseComponent.setHoverEvent(hoverEvent));
        }
        getPlayer().spigot().sendMessage(component);
    }

    public void playSound(Sound sound,float vol,float pitch) {
    getPlayer().playSound(getLocation(),sound,vol,pitch);
    }

    public void openGUI(InventoryPack inventoryPack) {
        getPlayer().openInventory(inventoryPack.getInventory());
    }
    public void openGUI(InventoryGUI gui) {
        openGUI(gui.getPacks().get(0));
    }
}
