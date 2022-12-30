package qscoreboard.scoreboards;

import org.bukkit.entity.Player;
import me.clip.placeholderapi.PlaceholderAPI;

import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;
import qscoreboard.utils.PlaceholderUtil;

public class Score extends ScoreboardScore {

    private int score;
    private String playerName;

    private final String[] LINES;
    
    public Score(Scoreboard scoreboard, ScoreboardObjective objective, String[] lines) {
        super(scoreboard, objective, "");
        this.LINES = lines;
    }
    
    @Override
    public int getScore() {
        return this.score;
    }
    
    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setScore(int paramInt) {
        this.score = paramInt;
    }

    public void setLine(PlayerConnection connection, Player player, int count) {
        for (String line : LINES) {
            score = --count;
            playerName = PlaceholderUtil.setPlaceholders(player, line);
            connection.sendPacket(new PacketPlayOutScoreboardScore(this));
        }
    }
}
