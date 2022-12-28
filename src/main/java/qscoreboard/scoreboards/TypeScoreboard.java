package qscoreboard.scoreboards;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;

public abstract class TypeScoreboard {

    protected final Scoreboard SCOREBOARD;

    protected TypeScoreboard() {
        this.SCOREBOARD = MinecraftServer.getServer().getWorld().getScoreboard();
    }

    public abstract void join(PlayerConnection connection, Player player);
    public abstract void runnableTask();
}
