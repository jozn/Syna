package com.mmdt.syna.view.main;

import mobi.mmdt.ott.core.logic.core.ConnectionManager;

/* renamed from: com.mmdt.syna.view.main.m */
class Splash implements Runnable {
    final /* synthetic */ C0111a f2562a;

    Splash(C0111a c0111a) {
        this.f2562a = c0111a;
    }

    public void run() {
        if (ConnectionManager.f3661b.size() == 0 || ConnectionManager.f3662c.size() == 0) {
            ConnectionManager.f3664e = false;
        }
        ConnectionManager.m4680a(this.f2562a.f2544a.getApplicationContext());
        ConnectionManager.m4684a(true);
    }
}
