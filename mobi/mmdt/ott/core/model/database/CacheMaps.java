package mobi.mmdt.ott.core.model.database;

import java.util.HashMap;
import mobi.mmdt.ott.core.p051a.Statics;

/* renamed from: mobi.mmdt.ott.core.model.database.c */
class CacheMaps implements Runnable {
    final /* synthetic */ CacheMaps f3942a;
    private final /* synthetic */ HashMap f3943b;

    CacheMaps(CacheMaps cacheMaps, HashMap hashMap) {
        this.f3942a = cacheMaps;
        this.f3943b = hashMap;
    }

    public void run() {
        if (this.f3943b != null) {
            try {
                if (this.f3943b.size() != 0) {
                    Statics.m4458a(this.f3942a.f3899b, this.f3943b, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                this.f3942a.f3900c = false;
            } catch (Exception e2) {
                e2.printStackTrace();
                this.f3942a.f3900c = false;
            }
        }
    }
}
