package mobi.mmdt.ott.core.model.contactmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SyncService extends Service {
    private static final Object f3868a;
    private static SyncAdapter f3869b;

    static {
        f3868a = new Object();
        f3869b = null;
    }

    public IBinder onBind(Intent intent) {
        return f3869b.getSyncAdapterBinder();
    }

    public void onCreate() {
        synchronized (f3868a) {
            if (f3869b == null) {
                f3869b = new SyncAdapter(getApplicationContext(), true);
            }
        }
    }

    public void onDestroy() {
        f3869b = null;
    }
}
