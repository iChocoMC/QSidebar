package qscoreboard.utils;

public class RunnableUtil implements Runnable {

    private final ScoreboardUtil util;

    public RunnableUtil(ScoreboardUtil util) {
        this.util = util;
    }

    @Override
    public void run() {
        util.runnableTask();
    }
}