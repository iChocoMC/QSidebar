package ichocomc.qscoreboard.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ichocomc.qscoreboard.sidebar.Sidebar;

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