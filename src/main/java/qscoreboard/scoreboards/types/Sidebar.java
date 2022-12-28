package qscoreboard.scoreboards.types;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;
import qscoreboard.scoreboards.TypeScoreboard;
import qscoreboard.utils.PlaceholderUtil;

public class Sidebar extends TypeScoreboard {

    private ScoreboardObjective[] objectives = new ScoreboardObjective[3];
    private boolean change = false;

    private final String[] LINES;
    private final String TITLE;
    private final int LENGTH;

    public Sidebar(FileConfiguration config, boolean update) {
        super();

        TITLE = config.getString("sidebar.title").replace('&', '§');
        LINES = createLines(config);
        LENGTH = LINES.length;

        if (update) { 
            objectives[1] = createObjective("sidebar2");
            objectives[2] = createObjective("sidebar3");
        } else {
            objectives = new ScoreboardObjective[1];
        }

        objectives[0] = createObjective("sidebar");
        this.SCOREBOARD.setDisplaySlot(1, objectives[0]);

        try {
            this.SCOREBOARD.registerObjective("sidebar", IScoreboardCriteria.b);
            this.SCOREBOARD.registerObjective("sidebar2", IScoreboardCriteria.b);
            this.SCOREBOARD.registerObjective("sidebar3", IScoreboardCriteria.b);
        } catch (Exception e) {
            System.out.println("The sidebar already exists, so the plugin will use it");
        }
    }

    @Override
    public void join(PlayerConnection connection, Player player) {
        setScoreboard(connection, player, LENGTH);
    }

    @Override
    public void runnableTask() {
        if ( (change = !change) ) {
            objectives[0] = objectives[1];
            this.SCOREBOARD.setDisplaySlot(1, objectives[1]);
        } else {
            objectives[0] = objectives[2];
            this.SCOREBOARD.setDisplaySlot(1, objectives[2]);
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            setScoreboard(((CraftPlayer)player).getHandle().playerConnection, player, LENGTH); 
        }
    }
    
    private void setScoreboard(PlayerConnection connection, Player player, int count) {
        for (String line : LINES) {
            ScoreboardScore score = new ScoreboardScore(SCOREBOARD, objectives[0], PlaceholderUtil.setPlaceholders(player, line));
            score.setScore(--count);
            connection.networkManager.handle(new PacketPlayOutScoreboardScore(score));
        }
    }

    private ScoreboardObjective createObjective(String sidebar) {
        ScoreboardObjective objective = new ScoreboardObjective(this.SCOREBOARD, sidebar, IScoreboardCriteria.b);
        objective.setDisplayName(TITLE);
        return objective;
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