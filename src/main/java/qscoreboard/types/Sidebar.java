package qscoreboard.types;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;

import qscoreboard.utils.ScoreboardUtil;

public abstract class Sidebar extends ScoreboardUtil {

    protected final String[] LINES;
    protected final String TITLE;
    protected ScoreboardObjective objective;

    public Sidebar(FileConfiguration config) {
        super();

        TITLE = config.getString("sidebar.title").replace('&', '§');
        LINES = startLines(config);
        objective = startObjective();

        try {
            SCOREBOARD.registerObjective("sidebar", IScoreboardCriteria.b);
        } catch (Exception e) {
            System.out.println("Scoreboard currently setted, using them...");
        }
    }

    private String[] startLines(FileConfiguration config) {
        int count = 0;
        List<String> scoreLines = config.getStringList("sidebar.lines");
        String[] newLines = new String[scoreLines.size()];

        for (String line : scoreLines) {
            newLines[count] = line.replace('&', '§');
            count++;
        }
        return newLines;
    }

    public ScoreboardObjective startObjective() {
        ScoreboardObjective newObjective = new ScoreboardObjective(SCOREBOARD, "sidebar", IScoreboardCriteria.b);

        SCOREBOARD.setDisplaySlot(1, newObjective);
        newObjective.setDisplayName(TITLE);

        return newObjective;
    }
}
