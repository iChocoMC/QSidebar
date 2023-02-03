package ichocomc.qscoreboard.placeholders;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import ichocomc.qscoreboard.placeholders.methods.WithPlaceholders;
import ichocomc.qscoreboard.placeholders.types.QPlayer;
import ichocomc.qscoreboard.placeholders.types.QServer;

public class PlaceholderMethod {

    private static PlaceholderMethod method;

    public static String setPlaceHolders(Player player, String text) {
        return method.transform(player, text);
    }

    public void startMethod(PluginManager pluginManager, FileConfiguration config) {
        if (!pluginManager.isPluginEnabled("PlaceHolderAPI") 
            && !config.getBoolean("placeholder-api-support")) {

            method = this;
            return;
        }
        String p = "enable-custom-placeholders.";

        if (config.getBoolean(p + "qserver")) {
            new QServer().register();
        }

        if (config.getBoolean(p + "qplayer")) {
            new QPlayer().register();
        }

        method = new WithPlaceholders();
    }

    public String transform(Player player, String text) {
        return text;
    }
}