package qscoreboard.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import me.clip.placeholderapi.PlaceholderAPI;

public abstract class PlaceholderUtil {
    public static PlaceholderUtil util;
    
    public static String setPlaceholders(Player player, String line) {
        return util.transform(player, line);
    }

    public static void startUtil(PluginManager pluginManager) {
        if (!pluginManager.isPluginEnabled("PlaceHolderAPI")) {

            System.out.println(
                "Please download placeholderapi for better experience: \n" +
                "https://www.spigotmc.org/resources/placeholderapi.6245/");

            util = new PlaceholderUtil() {
                @Override
                public String transform(Player player, String line) {
                    return line;
                }
            };
            return;
        }

        util = new PlaceholderUtil() {
            @Override
            public String transform(Player player, String line) {
                return PlaceholderAPI.setPlaceholders(player, line);
            }
        };
    }

    public abstract String transform(Player player, String line);
}
