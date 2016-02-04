package mobi.mmdt.ott.core.model.database;

import android.net.Uri;

/* renamed from: mobi.mmdt.ott.core.model.database.f */
public class MatchedContactData {
    private long f3985a;
    private String f3986b;
    private String f3987c;
    private String f3988d;
    private Uri f3989e;
    private boolean f3990f;

    public MatchedContactData(long j, String str, String str2, String str3, Uri uri, boolean z) {
        this.f3989e = null;
        this.f3990f = false;
        this.f3985a = j;
        this.f3986b = str;
        this.f3990f = z;
        this.f3987c = str2;
        this.f3989e = uri;
        this.f3988d = str3;
    }

    public boolean m5126a() {
        return this.f3990f;
    }

    public Uri m5127b() {
        return this.f3989e;
    }

    public String m5128c() {
        return this.f3986b;
    }

    public String m5129d() {
        return this.f3987c;
    }

    public String m5130e() {
        return this.f3988d;
    }
}
