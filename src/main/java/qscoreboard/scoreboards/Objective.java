package qscoreboard.scoreboards;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;

public class Objective extends ScoreboardObjective {

    private String e;

    public Objective(Scoreboard scoreboard, String objective, IScoreboardCriteria criteria, String line) {
        super(scoreboard, objective, criteria);
        this.e = line; 
    }

    @Override
    public String getDisplayName() {
        return e;
    }

    @Override
    public void setDisplayName(String line) {
        e = line;
    }
}
