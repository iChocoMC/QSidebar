package qscoreboard.utils;

import qscoreboard.scoreboards.TypeScoreboard;

public class RunnableUtil implements Runnable {

    private final TypeScoreboard util;

    public RunnableUtil(TypeScoreboard util) {
        this.util = util;
    }

    @Override
    public void run() {
        util.runnableTask();
    }
}