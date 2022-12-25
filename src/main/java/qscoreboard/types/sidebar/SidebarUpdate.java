package qscoreboard.types.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.ScoreboardScore;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import qscoreboard.types.Sidebar;
import qscoreboard.utils.PlaceholderUtil;

public class SidebarUpdate extends Sidebar {

    public SidebarUpdate(FileConfiguration config) {
        super(config);
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

    @Override
    protected void runnableTask() {
        this.objective = startObjective();

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
            setScoreboard(connection, player); 
        }
    }
}