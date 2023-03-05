package com.github.ichocomc.qsidebar.listeners;

import com.github.ichocomc.qsidebar.sidebar.Sidebar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final Sidebar sidebar;

    public PlayerJoinListener(Sidebar sidebar) {
        this.sidebar = sidebar;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void playerJoin(PlayerJoinEvent event) {
        sidebar.setSidebar(event.getPlayer());
    }
}