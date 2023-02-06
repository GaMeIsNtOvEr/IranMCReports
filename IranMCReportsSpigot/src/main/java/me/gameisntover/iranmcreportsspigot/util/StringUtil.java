package me.gameisntover.iranmcreportsspigot.util;

import me.gameisntover.iranmcreportsspigot.config.Config;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    public static String color(String mes){
        return ChatColor.translateAlternateColorCodes('&',mes);
    }
    public static List<String> color(List<String> mes){
        List<String> strings = new ArrayList<>();
        for (String me : mes) strings.add(color(me));
        return strings;
    }
    public static Config config() {
       return new Config("config.yml");
    }


}
