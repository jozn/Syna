package mobi.mmdt.ott.p043b.p044a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import mobi.mmdt.ott.p043b.p048c.WebServiceManager;
import mobi.mmdt.p041a.hash;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.a.c */
public class ClientAuthData {
    private Context f3396a;
    private String f3397b;
    private String f3398c;

    public ClientAuthData(Context context) {
        this.f3396a = context;
    }

    private String m4318a() {
        return WebServiceManager.m4386b(this.f3396a);
    }

    private String m4319b() {
        return "android";
    }

    private String m4320c() throws NameNotFoundException {
        return this.f3396a.getPackageManager().getPackageInfo(this.f3396a.getPackageName(), 0).versionName;
    }

    private String m4321d() {
        return this.f3397b;
    }

    private String m4322e() {
        return hash.m4112b(this.f3398c);
    }

    private String m4323f() {
        return "0";
    }

    public JSONObject m4324a(String str, long j, String str2, String str3) throws JSONException, NumberFormatException, NoSuchAlgorithmException, UnsupportedEncodingException, NameNotFoundException {
        this.f3397b = str2;
        this.f3398c = str3;
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("Username", m4321d());
        jSONObject.m5359a("AuthenticationCode", m4322e());
        jSONObject.m5359a("RequestID", (Object) str);
        jSONObject.m5359a("Timestamp", new StringBuilder(String.valueOf(j)).toString());
        jSONObject.m5359a("ClientVersion", m4320c());
        jSONObject.m5359a("Platform", m4319b());
        jSONObject.m5359a("DeviceID", m4318a());
        jSONObject.m5359a("Sign", m4323f());
        return jSONObject;
    }
}
