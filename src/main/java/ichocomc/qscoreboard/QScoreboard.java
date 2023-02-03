package ichocomc.qscoreboard;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ichocomc.qscoreboard.listeners.PlayerJoin;
import ichocomc.qscoreboard.placeholders.PlaceholderMethod;
import ichocomc.qscoreboard.sidebar.Sidebar;
import ichocomc.qscoreboard.sidebar.UpdateSidebar;
public class QScoreboard extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();
        FileConfiguration config = getConfig();
        Sidebar sidebar = new Sidebar(config);

        new PlaceholderMethod().startMethod(pluginManager, config);

        if (config.getBoolean("update.enable")) {
            int time = config.getInt("update.seconds") * 20; // Transform to ticks
            getServer().getScheduler().runTaskTimer(
                this, new UpdateSidebar(sidebar), time, time);

            return;
        }
        pluginManager.registerEvents(new PlayerJoin(sidebar), this);
        Bukkit.getLogger().warning("Sidebar update is disable, using player join listener");
    }
}