package com.mmdt.syna.view.call;

import mobi.mmdt.ott.core.logic.p024b.CallManager;
import mobi.mmdt.ott.core.p051a.CountryTools;

/* renamed from: com.mmdt.syna.view.call.b */
class CallHandler implements Runnable {
    final /* synthetic */ CallHandler f1766a;

    CallHandler(CallHandler callHandler) {
        this.f1766a = callHandler;
    }

    public void run() {
        while (this.f1766a.f1762h) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (this.f1766a.f1759e == null && this.f1766a.m2651l() != null) {
                this.f1766a.f1759e = CountryTools.m4416a().m4421a(this.f1766a.m2651l());
                this.f1766a.f1758d = CountryTools.m4416a().m4426c(this.f1766a.f1759e);
            }
            this.f1766a.f1756b.m2653a(CallManager.m4553a(this.f1766a.f1755a).m4579h(), CallManager.m4553a(this.f1766a.f1755a).m4580i(), this.f1766a.f1757c, this.f1766a.f1758d);
        }
    }
}
