package mobi.mmdt.ott.p043b.p048c.p049a;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.XMPPError;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.j */
public class RegistrationResult {
    private JSONObject f3456a;
    private boolean f3457b;
    private String f3458c;
    private int f3459d;

    public RegistrationResult(JSONObject jSONObject) {
        this.f3457b = false;
        this.f3459d = -1;
        this.f3456a = jSONObject;
    }

    public int m4360a() throws JSONException {
        if (this.f3456a.m5366h("type") && this.f3456a.m5366h("error_code")) {
            this.f3459d = this.f3456a.m5361c("error_code");
        }
        return this.f3459d;
    }

    public boolean m4361b() throws JSONException {
        if (this.f3456a.m5366h("type") && this.f3456a.m5365g("type").equals(XMPPError.ERROR)) {
            this.f3457b = true;
        }
        return this.f3457b;
    }

    public String m4362c() throws JSONException {
        if (this.f3456a.m5366h(Message.ELEMENT)) {
            this.f3458c = this.f3456a.m5365g(Message.ELEMENT);
        }
        return this.f3458c;
    }

    public String toString() {
        return this.f3456a.toString();
    }
}
