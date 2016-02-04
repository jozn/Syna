package mobi.mmdt.ott.p043b.p044a;

import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.a.g */
public class ServerAuthData {
    private String f3414a;
    private String f3415b;
    private String f3416c;
    private String f3417d;

    public ServerAuthData(JSONObject jSONObject) throws JSONException {
        this.f3414a = jSONObject.m5365g("Username");
        this.f3416c = jSONObject.m5365g("Timestamp");
        this.f3417d = jSONObject.m5365g("Sign");
        this.f3415b = jSONObject.m5365g("ResponseID");
    }
}
