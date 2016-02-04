package mobi.mmdt.ott.core.model.database.p061b;

import android.net.Uri;

/* renamed from: mobi.mmdt.ott.core.model.database.b.a */
public class SynaContact {
    private int f3908a;
    private String f3909b;
    private String f3910c;
    private String f3911d;
    private Uri f3912e;
    private Uri f3913f;
    private int f3914g;
    private String f3915h;

    public SynaContact(int i, String str, String str2, String str3, Uri uri, Uri uri2, int i2, String str4) {
        this.f3910c = str2;
        this.f3911d = str3;
        this.f3912e = uri;
        this.f3913f = uri2;
        this.f3908a = i;
        this.f3914g = i2;
        this.f3909b = str;
        this.f3915h = str4;
    }

    public String m4973a() {
        return this.f3915h;
    }

    public int m4974b() {
        return this.f3914g;
    }

    public String m4975c() {
        return this.f3910c;
    }

    public String m4976d() {
        return this.f3909b;
    }

    public int m4977e() {
        return this.f3908a;
    }

    public Uri m4978f() {
        return this.f3913f;
    }

    public String m4979g() {
        return this.f3911d;
    }
}
