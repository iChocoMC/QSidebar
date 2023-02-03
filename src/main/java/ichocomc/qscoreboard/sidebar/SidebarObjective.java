package ichocomc.qscoreboard.sidebar;

import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;

public class SidebarObjective extends ScoreboardObjective {

	private String e; // Scoreboard title

	public SidebarObjective(Scoreboard serverScoreboard, String title) {
		// Scoreboard, objectiveName, IScoreboardCriteria
		super(serverScoreboard, "sidebar", IScoreboardCriteria.b);

		this.e = title;
    }

	@Override
	public String getDisplayName() {
		return this.e;
	}

	@Override
	public void setDisplayName(String line) {
		e = line;
	}
}