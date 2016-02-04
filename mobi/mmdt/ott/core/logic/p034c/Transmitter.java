package mobi.mmdt.ott.core.logic.p034c;

import android.content.Context;
import java.util.ArrayList;
import org.apache.http.HttpHost;

/* renamed from: mobi.mmdt.ott.core.logic.c.e */
public abstract class Transmitter {
    protected boolean f3608a;
    protected int f3609b;
    protected long f3610c;
    protected Context f3611d;
    protected long f3612e;
    protected String f3613f;
    protected boolean f3614g;
    protected int f3615h;
    protected String f3616i;
    protected ArrayList<LocalTransmitterListener> f3617j;

    public Transmitter(Context context, String str, long j, String str2) {
        this.f3608a = false;
        this.f3609b = 150;
        this.f3610c = 0;
        this.f3614g = false;
        this.f3615h = 0;
        this.f3617j = new ArrayList();
        if (str.startsWith("https")) {
            str = str.replaceFirst("https", HttpHost.DEFAULT_SCHEME_NAME);
        }
        this.f3613f = str;
        this.f3611d = context;
        this.f3616i = str2;
        this.f3612e = j;
    }

    public abstract void m4637a();

    public void m4638a(LocalTransmitterListener localTransmitterListener) {
        this.f3617j.add(localTransmitterListener);
    }

    public void m4639b() {
        this.f3608a = true;
    }

    public String m4640c() {
        return this.f3616i;
    }
}
