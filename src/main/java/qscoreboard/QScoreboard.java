package qscoreboard;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import qscoreboard.listeners.PlayerJoin;
import qscoreboard.utils.PlaceholderUtil;

public class QScoreboard extends JavaPlugin {

    private static FileConfiguration config;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        config = getConfig();

        PluginManager pluginManager = this.getServer().getPluginManager();

        PlaceholderUtil.startUtil(pluginManager);

        pluginManager.registerEvents(new PlayerJoin(), this);
        
    }

    public static FileConfiguration getConfiguration() {
        return config;
    }
}