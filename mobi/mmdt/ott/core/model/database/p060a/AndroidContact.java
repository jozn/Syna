package mobi.mmdt.ott.core.model.database.p060a;

import android.net.Uri;

/* renamed from: mobi.mmdt.ott.core.model.database.a.a */
public class AndroidContact {
    private int f3882a;
    private String[] f3883b;
    private String f3884c;
    private boolean f3885d;
    private Uri f3886e;

    public AndroidContact(int i, String[] strArr, String str, boolean z, Uri uri) {
        this.f3885d = false;
        this.f3883b = strArr;
        this.f3884c = str;
        this.f3885d = z;
        this.f3886e = uri;
    }

    public int m4938a() {
        return this.f3882a;
    }

    public String m4939b() {
        return this.f3884c;
    }

    public Uri m4940c() {
        return this.f3886e;
    }

    public String[] m4941d() {
        return this.f3883b;
    }
}
