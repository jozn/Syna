package mobi.mmdt.ott.p043b.p048c.p049a;

import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.e */
public class DeActivationResult {
    private JSONObject f3439a;
    private boolean f3440b;
    private int f3441c;

    public DeActivationResult(JSONObject jSONObject) throws JSONException {
        this.f3440b = false;
        this.f3441c = -1;
        this.f3439a = jSONObject;
    }

    public String toString() {
        return this.f3439a.toString();
    }
}
