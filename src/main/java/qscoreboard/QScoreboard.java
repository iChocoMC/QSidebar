package qscoreboard;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import qscoreboard.listeners.PlayerJoin;
import qscoreboard.types.sidebar.SidebarUpdate;
import qscoreboard.utils.PlaceholderUtil;
import qscoreboard.utils.RunnableUtil;
import qscoreboard.utils.ScoreboardUtil;

public class QScoreboard extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        startFeatures(this, this.getConfig());
    }

    private void startFeatures(QScoreboard plugin, FileConfiguration config) {

        PluginManager pluginManager = plugin.getServer().getPluginManager();
        ScoreboardUtil sidebar = new SidebarUpdate(config);

        PlaceholderUtil.startUtil(pluginManager);

        if (config.getBoolean("update-sidebar.enable")) {
            int ticks = config.getInt("update-sidebar.seconds") * 20;
            plugin.getServer().getScheduler().runTaskTimer(plugin, new RunnableUtil(sidebar), ticks, ticks);
        }

        pluginManager.registerEvents(new PlayerJoin(sidebar), plugin);
    }
}