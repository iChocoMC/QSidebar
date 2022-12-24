package qscoreboard.types.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.ScoreboardScore;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import qscoreboard.types.SideBar;
import qscoreboard.utils.PlaceholderUtil;

public class Update extends SideBar {

    public Update() {
        super();
        Thread thread = new UpdateThread(this);
        thread.start();
    }

    @Override
    public void execute(EntityPlayer entity) {
        setScoreboard(entity.playerConnection, entity.getBukkitEntity().getPlayer());
    }

    @Override
    public void setScoreboard(PlayerConnection connection, Player player) {
        int count = LINES.length;

        for (String line : LINES) {
            ScoreboardScore score = new ScoreboardScore(
                SCOREBOARD, objective, PlaceholderUtil.setPlaceholders(player, line));

            score.setScore(count);

            connection.sendPacket(new PacketPlayOutScoreboardScore(score));
            count--;
        }
    }

    protected void runnableTask() {
        SCOREBOARD.unregisterObjective(objective);
        objective = startObjective();

        for (Player player : Bukkit.getOnlinePlayers()) {
            setScoreboard(((CraftPlayer)player).getHandle().playerConnection, player);
        }
    }
}