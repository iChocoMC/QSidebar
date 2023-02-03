package ichocomc.qscoreboard.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import ichocomc.qscoreboard.placeholders.PlaceholderLine;
import ichocomc.qscoreboard.score.CustomScore;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class StartSidebar {

    private final Map<String, Integer> noPlaceholderLines = new HashMap<>();
    private int count;

    public StartSidebar(Scoreboard serverScoreboard) {
        try {
            serverScoreboard.registerObjective("sidebar", IScoreboardCriteria.b);
        } catch (Exception e) {
            System.out.println("The sidebar already exists");
        }
    }

    public LinkedList<PlaceholderLine> startLines(FileConfiguration config) {
        final List<String> listLines = config.getStringList("lines");
        final LinkedList<PlaceholderLine> customLines = new LinkedList<>();

        count = listLines.size();

        for (String line : listLines) {  
            line = ChatColor.translateAlternateColorCodes('&', line);      

            if (line.contains("%")) {
                customLines.add(new PlaceholderLine(line, --count));
                continue;
            }
            noPlaceholderLines.put(line, --count);
        }

        return customLines;
    }

    public PacketPlayOutScoreboardScore[] loadPackets(Scoreboard scoreboard, ScoreboardObjective objective) {
        final PacketPlayOutScoreboardScore[] packets = new PacketPlayOutScoreboardScore[noPlaceholderLines.size()];
        count = -1;

        noPlaceholderLines.forEach((line, score) -> {
            ScoreboardScore sidebarScore = new CustomScore(scoreboard, objective, line);
            sidebarScore.setScore(score);

            packets[++count] = new PacketPlayOutScoreboardScore(sidebarScore);
        });

        return packets;
    }
}