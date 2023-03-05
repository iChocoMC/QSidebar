package com.github.ichocomc.qsidebar;

import com.github.ichocomc.qsidebar.listeners.PlayerJoinListener;

import com.github.ichocomc.qsidebar.sidebar.Sidebar;
import com.github.ichocomc.qsidebar.sidebar.SidebarUpdateTask;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class QSidebar extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        PluginManager pluginManager = getServer().getPluginManager();
        FileConfiguration config = getConfig();

        Sidebar sidebar = new Sidebar(config, this);

        if (config.getBoolean("lines-update.enable")) {
            int time = config.getInt("lines-update.seconds") * 20; // Transform to ticks
            getServer().getScheduler().runTaskTimer(
                this, new SidebarUpdateTask(sidebar), time, time);
            return;
        }

        pluginManager.registerEvents(new PlayerJoinListener(sidebar), this);
        Bukkit.getLogger().warning("Sidebar update is disable, using player join listener");
    }
}