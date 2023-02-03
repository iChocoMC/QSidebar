package ichocomc.qscoreboard.score;

import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import net.minecraft.server.v1_8_R3.ScoreboardScore;

public class CustomScore extends ScoreboardScore {

    private int score; // Sidebar score
    private String playerName; // Sidebar line

	public CustomScore(Scoreboard serverScoreboard, ScoreboardObjective objective, String line) {
		super(serverScoreboard, objective, "");
		this.playerName = line;
	}

	@Override
	public void setScore(int newScore) {
		this.score = newScore;
	}

	public void setPlayerName(String newLine) {
		playerName = newLine;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public int getScore() {
		return score;
	}
}