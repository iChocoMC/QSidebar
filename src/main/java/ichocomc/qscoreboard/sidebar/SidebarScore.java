package ichocomc.qscoreboard.sidebar;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import ichocomc.qscoreboard.placeholders.PlaceholderLine;
import ichocomc.qscoreboard.placeholders.PlaceholderMethod;
import ichocomc.qscoreboard.score.CustomScore;
import ichocomc.qscoreboard.utils.StartSidebarUtil;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;

public class SidebarScore extends CustomScore {

    private final List<PlaceholderLine> customLines;
    private final PacketPlayOutScoreboardScore[] preLoadedPackets;

    public SidebarScore(Scoreboard serverScoreboard, ScoreboardObjective objective, FileConfiguration config) {
        // Scoreboard, objective, playerName
        super(serverScoreboard, objective, "");

        // Util for start the sidebar
        StartSidebarUtil start = new StartSidebarUtil(serverScoreboard);

        this.customLines = start.startLines(config);
        this.preLoadedPackets = start.loadPackets(serverScoreboard, objective);
    }

    public void setLines(final PlayerConnection connection, final Player player) {

        // Send lines with placeholders
        for (PlaceholderLine line : customLines) {
            setScore(line.getScore());
            setPlayerName(PlaceholderMethod.setPlaceHolders(player, line.getLine()));
            connection.sendPacket(new PacketPlayOutScoreboardScore(this));
        }

        //Send lines without placeholders
        for (PacketPlayOutScoreboardScore packet : preLoadedPackets) {
            connection.sendPacket(packet);
        }
    }
}