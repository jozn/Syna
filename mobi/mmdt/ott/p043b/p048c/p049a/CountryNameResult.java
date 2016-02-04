package mobi.mmdt.ott.p043b.p048c.p049a;

import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.d */
public class CountryNameResult {
    private JSONObject f3435a;
    private boolean f3436b;
    private String f3437c;
    private int f3438d;

    public CountryNameResult(JSONObject jSONObject) {
        this.f3436b = false;
        this.f3438d = -1;
        this.f3435a = jSONObject;
    }

    public String m4353a() throws JSONException {
        if (this.f3435a.m5366h("cc")) {
            this.f3437c = this.f3435a.m5365g("cc");
        }
        return this.f3437c;
    }

    public String toString() {
        return this.f3435a.toString();
    }
}
