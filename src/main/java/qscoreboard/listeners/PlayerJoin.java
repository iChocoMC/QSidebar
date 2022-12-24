package qscoreboard.listeners;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import qscoreboard.QScoreboard;
import qscoreboard.types.sidebar.Update;
import qscoreboard.types.sidebar.WithoutUpdate;
import qscoreboard.utils.ScoreboardUtil;

public class PlayerJoin implements Listener {
    
    private final ScoreboardUtil scoreboard;

    public PlayerJoin() {
        if (QScoreboard.getConfiguration().getBoolean("update-sidebar.enable")) {
            scoreboard = new Update();
            return;
        }
        scoreboard = new WithoutUpdate();
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        scoreboard.setScoreboard(((CraftPlayer)player).getHandle().playerConnection, player);
    }
}
