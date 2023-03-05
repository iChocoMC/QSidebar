package com.github.ichocomc.qsidebar.animatedtitle;

import com.github.ichocomc.qsidebar.QSidebar;
import com.github.ichocomc.qsidebar.interfaces.SidebarTitle;
import com.github.ichocomc.qsidebar.sidebar.Sidebar;

import org.bukkit.ChatColor;

import java.util.List;

public class WithAnimation implements SidebarTitle, Runnable {

    private final Sidebar sidebar;
    private final String[] titles;
    private int currentTitle = -1;
    private boolean update = true;

    public WithAnimation(List<String> newTitles, QSidebar plugin, Sidebar newSidebar) {
        sidebar = newSidebar;
        titles = new String[newTitles.size()];
        int time = plugin.getConfig().getInt("animated-title.seconds");

        for (String title : newTitles) {
            titles[++currentTitle] = ChatColor.translateAlternateColorCodes('&', title);
        }

        currentTitle = 0;
        plugin.getServer().getScheduler().runTaskTimer(plugin, this, time, time);
    }

    @Override
    public String getTitle() {
        return titles[currentTitle];
    }

    @Override
    public void run() {
        if ( (update = !update) ) { // Prevent title skip for people with lag
            return;
        }
        sidebar.updateTitle();
        if (++currentTitle == titles.length) {
            currentTitle = 0;
        }
    }
}