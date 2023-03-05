package com.github.ichocomc.qsidebar.animatedtitle;

import com.github.ichocomc.qsidebar.interfaces.SidebarTitle;
import org.bukkit.ChatColor;

public class DefaultTitle implements SidebarTitle {
    private final String title;

    public DefaultTitle(String newTitle) {
        title = ChatColor.translateAlternateColorCodes('&', newTitle);
    }

    public String getTitle() {
        return title;
    }
}