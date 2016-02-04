package mobi.mmdt.ott.asmackengine.core;

import android.util.Log;
import org.jivesoftware.smackx.ping.PingFailedListener;

class Core$3 implements PingFailedListener {
    final /* synthetic */ Core f3266a;

    Core$3(Core core) {
        this.a = core;
    }

    public void m4197a() {
        Log.e("Core", "Ping failed.");
    }
}
