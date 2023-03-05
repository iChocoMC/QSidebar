package com.github.ichocomc.qsidebar.animatedtitle;

import com.github.ichocomc.qsidebar.QSidebar;
import com.github.ichocomc.qsidebar.interfaces.SidebarTitle;
import org.bukkit.ChatColor;

import java.util.List;

public class WithAnimation implements SidebarTitle, Runnable {

    private final String[] titles;
    private int currentTitle = -1;
    private boolean update = true;

    public WithAnimation(List<String> newTitles, QSidebar plugin) {
        titles = new String[newTitles.size()];
        int time = plugin.getConfig().getInt("animated-title.seconds");
        for (String title : newTitles) {
            titles[++currentTitle] = ChatColor.translateAlternateColorCodes('&', title);
        }
        currentTitle = -1;
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
        if (++currentTitle == titles.length) {
            currentTitle = 0;
        }
    }
}