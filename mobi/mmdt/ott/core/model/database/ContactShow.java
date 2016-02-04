package mobi.mmdt.ott.core.model.database;

import android.net.Uri;

/* renamed from: mobi.mmdt.ott.core.model.database.d */
public class ContactShow {
    private Uri f3957a;
    private String f3958b;
    private String f3959c;
    private boolean f3960d;
    private int f3961e;

    public ContactShow(Uri uri, String str, String str2, boolean z, int i) {
        this.f3960d = false;
        this.f3961e = -1;
        this.f3957a = uri;
        this.f3958b = str;
        this.f3959c = str2;
        this.f3960d = z;
        this.f3961e = i;
    }

    public int m5060a() {
        return this.f3961e;
    }

    public Uri m5061b() {
        return this.f3957a;
    }

    public String m5062c() {
        return this.f3958b;
    }
}
