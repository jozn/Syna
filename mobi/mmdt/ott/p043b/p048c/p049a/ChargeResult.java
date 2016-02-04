package mobi.mmdt.ott.p043b.p048c.p049a;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.XMPPError;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.c */
public class ChargeResult {
    private JSONObject f3431a;
    private boolean f3432b;
    private String f3433c;
    private int f3434d;

    public ChargeResult(JSONObject jSONObject) {
        this.f3432b = false;
        this.f3434d = -1;
        this.f3431a = jSONObject;
    }

    public int m4350a() throws JSONException {
        if (this.f3431a.m5366h("type") && this.f3431a.m5366h("error_code")) {
            this.f3434d = this.f3431a.m5361c("error_code");
        }
        return this.f3434d;
    }

    public boolean m4351b() throws JSONException {
        if (this.f3431a.m5366h("type") && this.f3431a.m5365g("type").equals(XMPPError.ERROR)) {
            this.f3432b = true;
        }
        return this.f3432b;
    }

    public String m4352c() throws JSONException {
        if (this.f3431a.m5366h(Message.ELEMENT)) {
            this.f3433c = this.f3431a.m5365g(Message.ELEMENT);
        }
        return this.f3433c;
    }

    public String toString() {
        return this.f3431a.toString();
    }
}
