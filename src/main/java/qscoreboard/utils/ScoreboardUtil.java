package qscoreboard.utils;

import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PlayerConnection;

public interface ScoreboardUtil {

    public void execute(EntityPlayer entity);
    public void setScoreboard(PlayerConnection connection, Player player);

}
