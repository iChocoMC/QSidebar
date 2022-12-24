package qscoreboard.types.sidebar;

import qscoreboard.QScoreboard;

public class UpdateThread extends Thread {

    private final Update method;
    private final long milis;

    public UpdateThread(Update method) {
        this.method = method;
        this.milis = QScoreboard.getConfiguration().getInt("update-sidebar.seconds") * 1000;
    }

    @Override
    public void run() {
        while (true) {
            method.runnableTask();
            try {
                sleep(milis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
