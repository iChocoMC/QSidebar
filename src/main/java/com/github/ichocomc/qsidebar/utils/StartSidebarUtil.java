package com.github.ichocomc.qsidebar.utils;

import com.github.ichocomc.qsidebar.placeholders.PlaceholderLine;
import com.github.ichocomc.qsidebar.score.CustomScore;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class StartSidebarUtil {

    private final Map<String, Integer> noPlaceholderLines = new HashMap<>();

    public StartSidebarUtil(Scoreboard serverScoreboard) {
        try {
            serverScoreboard.registerObjective("sidebar", IScoreboardCriteria.b);
        } catch (Exception e) {
            System.out.println("The sidebar already exists");
        }
    }

    public LinkedList<PlaceholderLine> startLines(FileConfiguration config) {
        final List<String> listLines = config.getStringList("lines");
        final LinkedList<PlaceholderLine> customLines = new LinkedList<>();

        int count = listLines.size();

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
        int count = -1;

        for (Map.Entry<String, Integer> lines : noPlaceholderLines.entrySet()) {
            ScoreboardScore sidebarScore = new CustomScore(scoreboard, objective, lines.getKey());
            sidebarScore.setScore(lines.getValue());

            packets[++count] = new PacketPlayOutScoreboardScore(sidebarScore);
        }

        return packets;
    }
}