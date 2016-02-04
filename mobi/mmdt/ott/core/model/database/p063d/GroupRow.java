package mobi.mmdt.ott.core.model.database.p063d;

import android.net.Uri;
import java.io.Serializable;

/* renamed from: mobi.mmdt.ott.core.model.database.d.a */
public class GroupRow implements Serializable {
    private long f3944a;
    private String f3945b;
    private String f3946c;
    private String f3947d;
    private String f3948e;
    private String f3949f;
    private Uri f3950g;
    private int f3951h;
    private int f3952i;
    private boolean f3953j;
    private boolean f3954k;
    private boolean f3955l;
    private String f3956m;

    public GroupRow(long j, String str, String str2, String str3, String str4, String str5, Uri uri, int i, int i2, boolean z, boolean z2, boolean z3, String str6) {
        this.f3944a = j;
        this.f3956m = str6;
        this.f3945b = str;
        this.f3946c = str2;
        this.f3947d = str3;
        this.f3948e = str4;
        this.f3949f = str5;
        this.f3950g = uri;
        this.f3951h = i;
        this.f3952i = i2;
        this.f3953j = z;
        this.f3954k = z2;
        this.f3955l = z3;
    }

    public String m5041a() {
        return this.f3945b;
    }

    public String m5042b() {
        return this.f3948e;
    }

    public String m5043c() {
        return this.f3949f;
    }
}
