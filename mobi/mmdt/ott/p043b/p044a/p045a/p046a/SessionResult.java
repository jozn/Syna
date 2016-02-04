package mobi.mmdt.ott.p043b.p044a.p045a.p046a;

import mobi.mmdt.ott.p043b.p044a.ServerAuthData;

/* renamed from: mobi.mmdt.ott.b.a.a.a.a */
public class SessionResult {
    private int f3324a;
    private String f3325b;
    private String f3326c;
    private String f3327d;
    private String f3328e;
    private ServerAuthData f3329f;

    public SessionResult(ServerAuthData serverAuthData, int i, String str, String str2, String str3, String str4) {
        this.f3329f = serverAuthData;
        this.f3324a = i;
        this.f3325b = str;
        this.f3327d = str3;
        this.f3326c = str2;
        this.f3328e = str4;
    }

    public String m4278a() {
        return this.f3325b;
    }
}
