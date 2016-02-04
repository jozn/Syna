package mobi.mmdt.ott.core.model.contactmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AuthenticationService extends Service {
    private static final Object f3866a;
    private Authenticator f3867b;

    static {
        f3866a = new Object();
    }

    public IBinder onBind(Intent intent) {
        return this.f3867b.getIBinder();
    }

    public void onCreate() {
        synchronized (f3866a) {
            if (this.f3867b == null) {
                this.f3867b = new Authenticator(this);
            }
        }
    }
}
