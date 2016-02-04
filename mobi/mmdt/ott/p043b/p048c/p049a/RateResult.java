package mobi.mmdt.ott.p043b.p048c.p049a;

import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.h */
public class RateResult {
    private JSONObject f3449a;
    private boolean f3450b;
    private String f3451c;
    private int f3452d;

    public RateResult(JSONObject jSONObject) {
        this.f3450b = false;
        this.f3452d = -1;
        this.f3449a = jSONObject;
    }

    public String m4358a() throws JSONException {
        if (this.f3449a.m5366h("rate")) {
            this.f3451c = this.f3449a.m5365g("rate");
        }
        return this.f3451c;
    }

    public String toString() {
        return this.f3449a.toString();
    }
}
