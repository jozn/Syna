package mobi.mmdt.ott.p043b.p048c.p049a;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.XMPPError;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.b */
public class CardInfoResult {
    private JSONObject f3424a;
    private boolean f3425b;
    private String f3426c;
    private String f3427d;
    private String f3428e;
    private String f3429f;
    private int f3430g;

    public CardInfoResult(JSONObject jSONObject) {
        this.f3425b = false;
        this.f3430g = -1;
        this.f3424a = jSONObject;
    }

    public int m4344a() throws JSONException {
        if (this.f3424a.m5366h("type") && this.f3424a.m5366h("error_code")) {
            this.f3430g = this.f3424a.m5361c("error_code");
        }
        return this.f3430g;
    }

    public boolean m4345b() throws JSONException {
        if (this.f3424a.m5366h("type") && this.f3424a.m5365g("type").equals(XMPPError.ERROR)) {
            this.f3425b = true;
        }
        return this.f3425b;
    }

    public String m4346c() throws JSONException {
        if (this.f3424a.m5366h(Message.ELEMENT)) {
            this.f3426c = this.f3424a.m5365g(Message.ELEMENT);
        }
        return this.f3426c;
    }

    public String m4347d() throws JSONException {
        if (this.f3424a.m5366h("username")) {
            this.f3427d = this.f3424a.m5365g("username");
        }
        return this.f3427d;
    }

    public String m4348e() throws JSONException {
        if (this.f3424a.m5366h("password")) {
            this.f3428e = this.f3424a.m5365g("password");
        }
        return this.f3428e;
    }

    public String m4349f() throws JSONException {
        if (this.f3424a.m5366h("credit")) {
            this.f3429f = this.f3424a.m5365g("credit");
        }
        return this.f3429f;
    }

    public String toString() {
        return this.f3424a.toString();
    }
}
