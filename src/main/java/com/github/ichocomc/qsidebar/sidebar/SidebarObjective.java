package com.github.ichocomc.qsidebar.sidebar;

import com.github.ichocomc.qsidebar.interfaces.SidebarTitle;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import net.minecraft.server.v1_8_R3.Scoreboard;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;

public class SidebarObjective extends ScoreboardObjective {

	private static SidebarTitle title;

	public SidebarObjective(Scoreboard serverScoreboard) {
		// Scoreboard, objectiveName, IScoreboardCriteria
		super(serverScoreboard, "sidebar", IScoreboardCriteria.b);
    }

	public SidebarObjective(Scoreboard serverScoreboard, SidebarTitle newTitle) {
		this(serverScoreboard);
		title = newTitle;
	}

	@Override
	public String getDisplayName() {
		return title.getTitle();
	}
}