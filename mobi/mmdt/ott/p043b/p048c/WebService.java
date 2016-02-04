package mobi.mmdt.ott.p043b.p048c;

import android.content.Context;
import java.io.IOException;
import java.security.GeneralSecurityException;
import mobi.mmdt.ott.p043b.p047b.RestfulWS;
import org.p074b.JSONArray;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.c.a */
public class WebService {
    private final String f3464a;
    private Context f3465b;

    public WebService(Context context, String str) {
        this.f3464a = str;
        this.f3465b = context;
    }

    public JSONObject m4366a(String str) throws JSONException, GeneralSecurityException, IOException {
        return RestfulWS.m4336a(this.f3465b, this.f3464a + "/client_country", str);
    }

    public JSONObject m4367a(String str, int i, String str2, String str3) throws GeneralSecurityException, IOException, JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("phone_num", (Object) str);
        jSONObject.m5358a("device_id", i);
        return RestfulWS.m4337a(this.f3465b, this.f3464a + "/register/" + str + "/" + str2, jSONObject, str3);
    }

    public JSONObject m4368a(String str, String str2) throws JSONException, IOException, GeneralSecurityException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("phone_num", (Object) str);
        return RestfulWS.m4339b(this.f3465b, this.f3464a + "/register", jSONObject, str2);
    }

    public JSONObject m4369a(String str, String str2, int i, String str3) throws GeneralSecurityException, IOException, JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5358a("confirm", i);
        return RestfulWS.m4337a(this.f3465b, this.f3464a + "/terminate/" + str + "/" + str2, jSONObject, str3);
    }

    public JSONObject m4370a(String str, String str2, String str3) throws GeneralSecurityException, IOException, JSONException {
        return RestfulWS.m4336a(this.f3465b, this.f3464a + "/card/" + str + "/" + str2, str3);
    }

    public JSONObject m4371a(String[] strArr, String str, String str2, String str3) throws JSONException, GeneralSecurityException, IOException {
        Object jSONArray = new JSONArray();
        for (Object a : strArr) {
            jSONArray.m5347a(a);
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("numbers", jSONArray);
        return RestfulWS.m4339b(this.f3465b, this.f3464a + "/members/" + str + "/" + str2, jSONObject, str3);
    }

    public JSONObject m4372b(String str, String str2) throws JSONException, GeneralSecurityException, IOException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("phone_num", (Object) str);
        return RestfulWS.m4339b(this.f3465b, this.f3464a + "/ivr", jSONObject, str2);
    }

    public JSONObject m4373b(String str, String str2, String str3) throws JSONException, GeneralSecurityException, IOException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("voucher", (Object) str2);
        return RestfulWS.m4339b(this.f3465b, this.f3464a + "/credit/" + str, jSONObject, str3);
    }

    public JSONObject m4374c(String str, String str2) throws GeneralSecurityException, IOException, JSONException {
        return RestfulWS.m4336a(this.f3465b, this.f3464a + "/rates/" + str, str2);
    }

    public JSONObject m4375d(String str, String str2) throws GeneralSecurityException, IOException, JSONException {
        return RestfulWS.m4336a(this.f3465b, this.f3464a + "/rate/" + str, str2);
    }
}
