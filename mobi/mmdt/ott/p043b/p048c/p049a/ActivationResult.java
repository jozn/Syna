package mobi.mmdt.ott.p043b.p048c.p049a;

import org.jivesoftware.smack.packet.XMPPError;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.a */
public class ActivationResult {
    private JSONObject f3419a;
    private boolean f3420b;
    private String f3421c;
    private String f3422d;
    private int f3423e;

    public ActivationResult(JSONObject jSONObject) throws JSONException {
        this.f3420b = false;
        this.f3423e = -1;
        this.f3419a = jSONObject;
    }

    public int m4340a() throws JSONException {
        if (this.f3419a.m5366h("type") && this.f3419a.m5366h("error_code")) {
            this.f3423e = this.f3419a.m5361c("error_code");
        }
        return this.f3423e;
    }

    public boolean m4341b() throws JSONException {
        if (this.f3419a.m5366h("type") && this.f3419a.m5365g("type").equals(XMPPError.ERROR)) {
            this.f3420b = true;
        }
        return this.f3420b;
    }

    public String m4342c() throws JSONException {
        if (this.f3419a.m5366h("username")) {
            this.f3421c = this.f3419a.m5365g("username");
        }
        return this.f3421c;
    }

    public String m4343d() throws JSONException {
        if (this.f3419a.m5366h("password")) {
            this.f3422d = this.f3419a.m5365g("password");
        }
        return this.f3422d;
    }

    public String toString() {
        return this.f3419a.toString();
    }
}
