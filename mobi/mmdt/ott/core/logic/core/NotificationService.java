package mobi.mmdt.ott.core.logic.core;

import mobi.mmdt.ott.core.model.database.CacheMaps;

/* renamed from: mobi.mmdt.ott.core.logic.core.t */
class NotificationService implements Runnable {
    final /* synthetic */ NotificationService f3694a;

    NotificationService(NotificationService notificationService) {
        this.f3694a = notificationService;
    }

    public void run() {
        CacheMaps.m4958a(this.f3694a.getApplicationContext());
    }
}
