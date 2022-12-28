package qscoreboard;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import qscoreboard.listeners.PlayerJoin;
import qscoreboard.utils.PlaceholderUtil;

public class QScoreboard extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        PluginManager pluginManager = this.getServer().getPluginManager();

        PlaceholderUtil.startUtil(pluginManager);
        pluginManager.registerEvents(new PlayerJoin(this), this);
    }
}