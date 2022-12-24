package qscoreboard.types.sidebar;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

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
            method.SCOREBOARD.unregisterObjective(method.objective);
            method.objective = null;

            method.objective = method.startObjective();

            for (Player player : Bukkit.getOnlinePlayers()) {
                method.setScoreboard(((CraftPlayer)player).getHandle().playerConnection, player);
            }

            try {
                sleep(milis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
