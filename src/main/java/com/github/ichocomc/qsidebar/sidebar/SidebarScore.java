package com.github.ichocomc.qsidebar.sidebar;

import com.github.ichocomc.qsidebar.interfaces.PlaceholderMethod;
import com.github.ichocomc.qsidebar.placeholders.PlaceholderLine;
import com.github.ichocomc.qsidebar.score.CustomScore;
import com.github.ichocomc.qsidebar.utils.StartSidebarUtil;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class SidebarScore extends CustomScore {

    private final List<PlaceholderLine> customLines;
    private final PlaceholderMethod placeholder;
    private final PacketPlayOutScoreboardScore[] loadedPackets;

    public SidebarScore(Scoreboard serverScoreboard, ScoreboardObjective objective,
                        FileConfiguration config, PlaceholderMethod method) {
        // Scoreboard, objective, playerName
        super(serverScoreboard, objective, "");

        // Util for start the sidebar
        StartSidebarUtil start = new StartSidebarUtil(serverScoreboard);

        this.customLines = start.startLines(config);
        this.loadedPackets = start.loadPackets(serverScoreboard, objective);
        this.placeholder = method;
    }

    public void setLines(final PlayerConnection connection, final Player player) {

        // Send lines with placeholders
        for (PlaceholderLine line : customLines) {
            setScore(line.getScore());
            setPlayerName(placeholder.transform(line.getLine(), player));
            connection.sendPacket(new PacketPlayOutScoreboardScore(this));
        }

        //Send lines without placeholders
        for (PacketPlayOutScoreboardScore packet : loadedPackets) {
            connection.sendPacket(packet);
        }
    }
}