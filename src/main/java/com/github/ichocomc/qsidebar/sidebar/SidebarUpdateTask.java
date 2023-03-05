package com.github.ichocomc.qsidebar.sidebar;

public class SidebarUpdateTask implements Runnable {

    private final Sidebar sidebar;

    public SidebarUpdateTask(Sidebar sidebar) {
        this.sidebar = sidebar;
    }

    @Override
    public void run() {
        sidebar.updateSidebar();     
    }
}