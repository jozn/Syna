package com.mmdt.syna.view.components.p029e;

/* renamed from: com.mmdt.syna.view.components.e.d */
class WaitProgressDialog implements Runnable {
    final /* synthetic */ WaitProgressDialog f1840a;
    private final /* synthetic */ boolean f1841b;

    WaitProgressDialog(WaitProgressDialog waitProgressDialog, boolean z) {
        this.f1840a = waitProgressDialog;
        this.f1841b = z;
    }

    public void run() {
        this.f1840a.f1836a.setCancelable(this.f1841b);
    }
}
