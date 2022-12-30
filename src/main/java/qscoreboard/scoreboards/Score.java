package qscoreboard.scoreboards;

import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class Score extends ScoreboardScore {

    private int score;

    public Score(Scoreboard scoreboard, ScoreboardObjective objective, String line, int score) {
        super(scoreboard, objective, line);
        this.score = score;
    }
    
    @Override
    public int getScore() {
        return this.score;
    }
    
    @Override
    public void setScore(int paramInt) {
        this.score = paramInt;
    }
}
