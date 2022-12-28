package qscoreboard.scoreboards.types;

import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class Score extends ScoreboardScore {

    private int score;

    public Score(Scoreboard arg0, ScoreboardObjective arg1, String arg2) {
        super(arg0, arg1, arg2);
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
