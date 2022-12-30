package qscoreboard.scoreboards.types;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.PlayerConnection;

import qscoreboard.scoreboards.Objective;
import qscoreboard.scoreboards.Score;
import qscoreboard.scoreboards.TypeScoreboard;

public class Sidebar extends TypeScoreboard {

    private Objective objective;
    private final Score SCORE;

    private final String TITLE;
    private final int LENGTH;

    public Sidebar(FileConfiguration config, boolean update) {
        super();

        TITLE = config.getString("sidebar.title").replace('&', '§');
        String[] LINES = createLines(config);
        LENGTH = LINES.length;

        createObjective();
        SCORE = new Score(SCOREBOARD, objective, LINES);

        try {
            this.SCOREBOARD.registerObjective("sidebar", IScoreboardCriteria.b);
        } catch (Exception e) {
            System.out.println("The sidebar already exists, so the plugin will use it");
        }
    }

    @Override
    public void join(PlayerConnection connection, Player player) {
        SCORE.setLine(((CraftPlayer)player).getHandle().playerConnection, player, LENGTH);
    }

    @Override
    public void runnableTask() {
        createObjective();
        for (Player player : Bukkit.getOnlinePlayers()) {
            SCORE.setLine(((CraftPlayer)player).getHandle().playerConnection, player, LENGTH);
        }
    }

    private void createObjective() {
        objective = new Objective(SCOREBOARD, "sidebar", IScoreboardCriteria.b, TITLE);
        SCOREBOARD.setDisplaySlot(1, objective);
    }

    private String[] createLines(FileConfiguration config) {
        List<String> scoreLines = config.getStringList("sidebar.lines");
        String[] newLines = new String[scoreLines.size()];

        for (int i = 0; i < scoreLines.size(); i++) {
            newLines[i] = scoreLines.get(i).replace('&', '§');
        }

        return newLines;
    }
}