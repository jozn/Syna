package org.jivesoftware.smack.util;

/* renamed from: org.jivesoftware.smack.util.a */
public class Async {
    public static Thread m5843a(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        return thread;
    }

    public static Thread m5844a(Runnable runnable, String str) {
        Thread a = Async.m5843a(runnable);
        a.setName(str);
        a.start();
        return a;
    }
}
