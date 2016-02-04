package com.felipecsl.asymmetricgridview.library;

/* renamed from: com.felipecsl.asymmetricgridview.library.f */
class AsyncTaskCompat implements Runnable {
    final /* synthetic */ AsyncTaskCompat f1514a;
    private final /* synthetic */ Runnable f1515b;

    AsyncTaskCompat(AsyncTaskCompat asyncTaskCompat, Runnable runnable) {
        this.f1514a = asyncTaskCompat;
        this.f1515b = runnable;
    }

    public void run() {
        try {
            this.f1515b.run();
        } finally {
            this.f1514a.m2292a();
        }
    }
}
