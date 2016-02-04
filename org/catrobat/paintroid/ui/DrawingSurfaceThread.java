package org.catrobat.paintroid.ui;

import android.util.Log;
import java.lang.Thread.State;

/* renamed from: org.catrobat.paintroid.ui.b */
class DrawingSurfaceThread {
    private Thread f4520a;
    private Runnable f4521b;
    private boolean f4522c;

    /* renamed from: org.catrobat.paintroid.ui.b.a */
    private class DrawingSurfaceThread implements Runnable {
        final /* synthetic */ DrawingSurfaceThread f4519a;

        private DrawingSurfaceThread(DrawingSurfaceThread drawingSurfaceThread) {
            this.f4519a = drawingSurfaceThread;
        }

        public void run() {
            Thread.yield();
            this.f4519a.m5787c();
        }
    }

    DrawingSurfaceThread(Runnable runnable) {
        this.f4521b = runnable;
        this.f4520a = new Thread(new DrawingSurfaceThread(), "DrawingSurfaceThread");
        this.f4520a.setDaemon(true);
    }

    private void m5787c() {
        while (this.f4522c) {
            this.f4521b.run();
        }
    }

    synchronized void m5788a() {
        Log.d("PAINTROID", "DrawingSurfaceThread.start");
        if (this.f4522c || this.f4521b == null || this.f4520a == null || this.f4520a.getState().equals(State.TERMINATED)) {
            Log.d("PAINTROID", "DrawingSurfaceThread.start returning");
        } else if (!this.f4520a.isAlive()) {
            this.f4522c = true;
            this.f4520a.start();
        }
    }

    synchronized void m5789b() {
        Throwable e;
        Log.d("PAINTROID", "DrawingSurfaceThread.stop");
        this.f4522c = false;
        if (this.f4520a != null && this.f4520a.isAlive()) {
            Log.w("PAINTROID", "DrawingSurfaceThread.join");
            Object obj = 1;
            while (obj != null) {
                try {
                    this.f4520a.join();
                    try {
                        Log.d("PAINTROID", "DrawingSurfaceThread.stopped");
                        obj = null;
                    } catch (InterruptedException e2) {
                        e = e2;
                        obj = null;
                        Log.e("PAINTROID", "Interrupt while joining DrawingSurfaceThread\n", e);
                    }
                } catch (InterruptedException e3) {
                    e = e3;
                    Log.e("PAINTROID", "Interrupt while joining DrawingSurfaceThread\n", e);
                }
            }
        }
    }
}
