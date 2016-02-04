package mobi.mmdt.ott.asmackengine.core;

import android.util.Log;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smackx.ping.PingManager;

class Core$2 implements ConnectionListener {
    final /* synthetic */ Core f3265a;

    Core$2(Core core) {
        this.a = core;
    }

    public void authenticated(XMPPConnection xMPPConnection, boolean z) {
        Log.d("AsmackEngine", "authenticated");
        if (this.a.f3268b != null) {
            this.a.f3268b.m4216b();
        }
        PingManager.getInstanceFor(this.a.f3269c).registerPingFailedListener(this.a.f3282p);
        Thread thread = new Thread(new Core(this));
        Thread thread2 = new Thread(new Core(this));
        Thread thread3 = new Thread(new Core(this));
        thread.setPriority(1);
        thread2.setPriority(1);
        thread3.setPriority(1);
        thread.start();
        thread2.start();
        thread3.start();
    }

    public void connected(XMPPConnection xMPPConnection) {
        Log.d("AsmackEngine", "connected");
    }

    public void connectionClosed() {
        Log.d("AsmackEngine", "connectionClosed");
        if (this.a.f3268b != null) {
            this.a.f3268b.m4215a();
        }
    }

    public void connectionClosedOnError(Exception exception) {
        Log.d("AsmackEngine", "connectionClosedOnError");
        if (this.a.f3268b != null) {
            this.a.f3268b.m4215a();
        }
    }

    public void reconnectingIn(int i) {
        Log.d("AsmackEngine", "reconnectingIn");
    }

    public void reconnectionFailed(Exception exception) {
        Log.d("AsmackEngine", "reconnectionFailed");
    }

    public void reconnectionSuccessful() {
        Log.d("AsmackEngine", "reconnectionSuccessful");
    }
}
