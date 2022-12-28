package qscoreboard.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import qscoreboard.QScoreboard;
import qscoreboard.scoreboards.TypeScoreboard;
import qscoreboard.scoreboards.types.*;
import qscoreboard.utils.RunnableUtil;

public class PlayerJoin implements Listener {

    private final TypeScoreboard sidebar;

    public PlayerJoin(QScoreboard plugin) {
        FileConfiguration config = plugin.getConfig();

        boolean update = config.getBoolean("update-sidebar.enable");
        sidebar = new Sidebar(config, update);

        if (update) {
            int ticks = config.getInt("update-sidebar.seconds") * 20;
            plugin.getServer().getScheduler().runTaskTimer(plugin, new RunnableUtil(sidebar), ticks, ticks);
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        sidebar.join(((CraftPlayer)player).getHandle().playerConnection, player);
    }
}
