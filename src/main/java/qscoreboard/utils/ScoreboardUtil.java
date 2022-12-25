package qscoreboard.utils;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;

public abstract class ScoreboardUtil {

    protected final Scoreboard SCOREBOARD;

    protected ScoreboardUtil() {
        this.SCOREBOARD = MinecraftServer.getServer().getWorld().getScoreboard();
    }

    public abstract void setScoreboard(PlayerConnection connection, Player player);
    protected abstract void runnableTask();
}
