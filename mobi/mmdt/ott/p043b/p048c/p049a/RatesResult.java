package mobi.mmdt.ott.p043b.p048c.p049a;

import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.p074b.JSONArray;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a.i */
public class RatesResult {
    private JSONObject f3453a;
    private boolean f3454b;
    private int f3455c;

    public RatesResult(JSONObject jSONObject) {
        this.f3454b = false;
        this.f3455c = -1;
        this.f3453a = jSONObject;
    }

    public RateObject[] m4359a() throws JSONException {
        int i = 0;
        if (!this.f3453a.m5366h("rates")) {
            return new RateObject[0];
        }
        JSONArray d = this.f3453a.m5362d("rates");
        RateObject[] rateObjectArr = new RateObject[d.m5344a()];
        while (i < rateObjectArr.length) {
            rateObjectArr[i] = new RateObject(d.m5348b(i).m5365g(XHTMLText.CODE), d.m5348b(i).m5365g("title"), d.m5348b(i).m5365g("subtitle"), d.m5348b(i).m5365g("rate"));
            i++;
        }
        return rateObjectArr;
    }

    public String toString() {
        return this.f3453a.toString();
    }
}
