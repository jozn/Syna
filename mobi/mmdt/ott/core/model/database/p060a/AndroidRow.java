package mobi.mmdt.ott.core.model.database.p060a;

/* renamed from: mobi.mmdt.ott.core.model.database.a.e */
public class AndroidRow {
    private int f3894a;
    private String f3895b;
    private boolean f3896c;
    private String f3897d;

    public AndroidRow(boolean z, String str, String str2, int i) {
        this.f3894a = i;
        this.f3895b = str;
        this.f3897d = str2;
        this.f3896c = z;
    }

    public boolean m4953a() {
        return this.f3896c;
    }

    public String m4954b() {
        return this.f3895b;
    }

    public String m4955c() {
        return this.f3897d;
    }

    public int m4956d() {
        return this.f3894a;
    }
}
