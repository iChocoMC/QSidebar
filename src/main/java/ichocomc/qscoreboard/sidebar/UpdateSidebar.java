package ichocomc.qscoreboard.sidebar;

public class UpdateSidebar implements Runnable {

    private final Sidebar sidebar;

    public UpdateSidebar(Sidebar sidebar) {
        this.sidebar = sidebar;
    }

    /*
     * Update the sidebar for every player online
     */
    @Override
    public void run() {
        sidebar.updateSidebar();     
    }
}