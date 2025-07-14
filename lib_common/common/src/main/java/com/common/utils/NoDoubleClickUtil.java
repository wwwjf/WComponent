package com.common.utils;

public class NoDoubleClickUtil {
    private static long lastClickTime = 0;

    public synchronized static void isDoubleClick(long SPACE_TIME, Runnable okRunnable, Runnable noRunnable) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > SPACE_TIME) {
            lastClickTime = currentTime;
            okRunnable.run();
        } else {
            noRunnable.run();
        }
    }

    public synchronized static void isDoubleClick(long SPACE_TIME, Runnable okRunnable) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > SPACE_TIME) {
            lastClickTime = currentTime;
            okRunnable.run();
        }
    }

    public synchronized static void isDoubleClick(Runnable okRunnable) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime > 1000) {
            lastClickTime = currentTime;
            okRunnable.run();
        }
    }

}