package mobi.mmdt.ott.core.logic.p024b;

import java.util.Vector;

/* renamed from: mobi.mmdt.ott.core.logic.b.d */
public class CallState {
    public static final CallState f3566a;
    public static final CallState f3567b;
    public static final CallState f3568c;
    public static final CallState f3569d;
    public static final CallState f3570e;
    public static final CallState f3571f;
    public static final CallState f3572g;
    public static final CallState f3573h;
    public static final CallState f3574i;
    public static final CallState f3575j;
    public static final CallState f3576k;
    public static final CallState f3577l;
    public static final CallState f3578m;
    public static final CallState f3579n;
    public static final CallState f3580o;
    public static final CallState f3581p;
    public static final CallState f3582q;
    public static final CallState f3583r;
    public static final CallState f3584s;
    private Vector<CallState> f3585t;
    private final int f3586u;
    private final String f3587v;

    static {
        f3566a = new CallState(0, "Idle");
        f3567b = new CallState(1, "IncomingReceived");
        f3568c = new CallState(2, "OutgoingInit");
        f3569d = new CallState(3, "OutgoingProgress");
        f3570e = new CallState(4, "OutgoingRinging");
        f3571f = new CallState(5, "OutgoingEarlyMedia");
        f3572g = new CallState(6, "Connected");
        f3573h = new CallState(7, "StreamsRunning");
        f3574i = new CallState(8, "Pausing");
        f3575j = new CallState(9, "Paused");
        f3576k = new CallState(10, "Resuming");
        f3577l = new CallState(11, "Refered");
        f3578m = new CallState(12, "Error");
        f3579n = new CallState(13, "CallEnd");
        f3580o = new CallState(14, "PausedByRemote");
        f3581p = new CallState(15, "UpdatedByRemote");
        f3582q = new CallState(16, "IncomingEarlyMedia");
        f3583r = new CallState(17, "Updating");
        f3584s = new CallState(18, "Released");
    }

    private CallState(int i, String str) {
        this.f3585t = new Vector();
        this.f3586u = i;
        this.f3585t.addElement(this);
        this.f3587v = str;
    }

    public String toString() {
        return this.f3587v;
    }
}
