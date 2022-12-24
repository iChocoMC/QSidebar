package qscoreboard.types;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;

import qscoreboard.QScoreboard;
import qscoreboard.utils.ScoreboardUtil;

public abstract class SideBar implements ScoreboardUtil {

    protected final String[] LINES;
    protected final FileConfiguration CONFIG;
    protected final Scoreboard SCOREBOARD;
    protected ScoreboardObjective objective;

    public SideBar() {
        CONFIG = QScoreboard.getConfiguration();
        SCOREBOARD = MinecraftServer.getServer().getWorld().getScoreboard();
        LINES = startLines();
        objective = startObjective();
    }

    private String[] startLines() {
        int count = 0;
        List<String> scoreLines = CONFIG.getStringList("sidebar.lines");
        String[] newLines = new String[scoreLines.size()];

        for (String line : scoreLines) {
            newLines[count] = line.replace('&', '§');
            count++;
        }
        return newLines;
    }

    public ScoreboardObjective startObjective() {
        ScoreboardObjective newObjective = new ScoreboardObjective(SCOREBOARD, "sidebar", IScoreboardCriteria.b);

        try {
            SCOREBOARD.registerObjective("sidebar", IScoreboardCriteria.b);
        } catch (Exception e) {
            System.out.println("Scoreboard currently setted, using them...");
        }

        SCOREBOARD.setDisplaySlot(1, newObjective);
        newObjective.setDisplayName(CONFIG.getString("sidebar.title").replace('&', '§'));

        return newObjective;
    }
}
