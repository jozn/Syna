package mobi.mmdt.ott.core.logic.core;

import mobi.mmdt.ott.core.logic.ServiceManager;
import mobi.mmdt.ott.core.logic.p055d.XmppManager;

/* renamed from: mobi.mmdt.ott.core.logic.core.p */
class MyService extends Thread {
    final /* synthetic */ MyService f3689a;

    MyService(MyService myService) {
        this.f3689a = myService;
    }

    public void run() {
        try {
            ServiceManager.m4551a(this.f3689a.getApplicationContext(), 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            XmppManager.m4723a(this.f3689a.getApplicationContext()).m4734a(this.f3689a.f3642g);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
