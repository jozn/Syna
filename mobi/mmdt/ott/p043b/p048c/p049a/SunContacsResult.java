package mobi.mmdt.ott.p043b.p048c.p049a;

import org.jivesoftware.smack.packet.XMPPError;
import org.p074b.JSONArray;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.k */
public class SunContacsResult {
    private JSONObject f3460a;
    private String[] f3461b;
    private boolean f3462c;
    private int f3463d;

    public SunContacsResult(JSONObject jSONObject) {
        this.f3462c = false;
        this.f3463d = -1;
        this.f3460a = jSONObject;
    }

    public int m4363a() throws JSONException {
        if (this.f3460a.m5366h("type") && this.f3460a.m5366h("error_code")) {
            this.f3463d = this.f3460a.m5361c("error_code");
        }
        return this.f3463d;
    }

    public boolean m4364b() throws JSONException {
        if (this.f3460a.m5366h("type") && this.f3460a.m5365g("type").equals(XMPPError.ERROR)) {
            this.f3462c = true;
        }
        return this.f3462c;
    }

    public String[] m4365c() throws JSONException {
        JSONArray d = this.f3460a.m5362d("members");
        this.f3461b = new String[d.m5344a()];
        for (int i = 0; i < d.m5344a(); i++) {
            this.f3461b[i] = d.m5349c(i);
        }
        return this.f3461b;
    }

    public String toString() {
        return this.f3460a.toString();
    }
}
