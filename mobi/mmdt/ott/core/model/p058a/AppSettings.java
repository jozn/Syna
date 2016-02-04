package mobi.mmdt.ott.core.model.p058a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.telephony.TelephonyManager;
import mobi.mmdt.ott.core.UiRequests;

/* renamed from: mobi.mmdt.ott.core.model.a.a */
public class AppSettings {
    private static AppSettings f3840a;
    private Context f3841b;
    private SharedPreferences f3842c;

    private AppSettings(Context context) {
        this.f3841b = context;
        this.f3842c = this.f3841b.getSharedPreferences("com.mmdt.sipclient.model.pref.PrefKeys.KEY_MAIN_PREF_1", 0);
    }

    public static AppSettings m4867a(Context context) {
        if (f3840a == null) {
            f3840a = new AppSettings(context);
        }
        return f3840a;
    }

    public String m4868A() {
        return UiRequests.m4506d();
    }

    public void m4869a() {
        this.f3842c.edit().clear().commit();
    }

    public void m4870a(int i) {
        this.f3842c.edit().putInt("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_OBSOLUTE_VERSION_NUMBER", i).commit();
    }

    public void m4871a(Uri uri) {
        if (uri != null) {
            this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_AVATAR_LOCAL_ADDRESS", uri.toString()).commit();
        } else {
            this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_AVATAR_LOCAL_ADDRESS", null).commit();
        }
    }

    public void m4872a(Boolean bool) {
        if (bool != null) {
            this.f3842c.edit().putBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_IS_MALE", bool.booleanValue()).commit();
        }
    }

    public void m4873a(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_SETTING_LANGUAGE", str).commit();
    }

    public void m4874a(boolean z) {
        this.f3842c.edit().putBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_IS_EXIT", z).commit();
    }

    public boolean m4875a(Context context, boolean z, String str) throws NameNotFoundException {
        if (!str.equals("987" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName.replace(".", "") + ((TelephonyManager) context.getSystemService("phone")).getDeviceId())) {
            return false;
        }
        m4879b(true);
        return true;
    }

    public String m4876b() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_FIRST_NAME", PrefDefaultValues.f3848f);
    }

    public void m4877b(int i) {
        this.f3842c.edit().putInt("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_NIGHT_MOOD_FROM_SECOND", i).commit();
    }

    public void m4878b(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PINCODE", str).commit();
    }

    public void m4879b(boolean z) {
        this.f3842c.edit().putBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_DEBUG_MODE", z).commit();
    }

    public String m4880c() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_LAST_NAME", PrefDefaultValues.f3849g);
    }

    public void m4881c(int i) {
        this.f3842c.edit().putInt("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_NIGHT_MOOD_TO_SECOND", i).commit();
    }

    public void m4882c(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_PHONE_NUMBER", str).commit();
    }

    public void m4883c(boolean z) {
        this.f3842c.edit().putBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_IS_NIGHT_MOOD", z).commit();
    }

    public String m4884d() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_NICK_NAME", PrefDefaultValues.f3850h);
    }

    public void m4885d(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_BALANCE_LAST", str).commit();
    }

    public void m4886d(boolean z) {
        this.f3842c.edit().putBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_IS_INTRODUCTION_SEEN", z).commit();
    }

    public Uri m4887e() {
        String string = this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_AVATAR_LOCAL_ADDRESS", PrefDefaultValues.f3852j);
        return string == null ? null : Uri.parse(string);
    }

    public void m4888e(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_COUNTRY_CODE", str).commit();
    }

    public String m4889f() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_SETTING_LANGUAGE", "en-us");
    }

    public void m4890f(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_FIRST_NAME", str).commit();
    }

    public Boolean m4891g() {
        return !this.f3842c.contains("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_IS_MALE") ? null : Boolean.valueOf(this.f3842c.getBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_IS_MALE", true));
    }

    public void m4892g(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_LAST_NAME", str).commit();
    }

    public void m4893h(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_NICK_NAME", str).commit();
    }

    public boolean m4894h() {
        return this.f3842c.getBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_IS_NIGHT_MOOD", false);
    }

    public String m4895i() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_EMAIL", PrefDefaultValues.f3851i);
    }

    public void m4896i(String str) {
        if (str != null) {
            this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_AVATAR_SERVER_ADDRESS", str).commit();
        } else {
            this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_AVATAR_SERVER_ADDRESS", null).commit();
        }
    }

    public String m4897j() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_USER_NAME", PrefDefaultValues.f3843a);
    }

    public void m4898j(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_EMAIL", str).commit();
    }

    public String m4899k() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_PASSWORD", PrefDefaultValues.f3844b);
    }

    public void m4900k(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_USER_NAME", str).commit();
    }

    public String m4901l() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PINCODE", PrefDefaultValues.f3845c);
    }

    public void m4902l(String str) {
        this.f3842c.edit().putString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_PASSWORD", str).commit();
    }

    public String m4903m() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_PHONE_NUMBER", PrefDefaultValues.f3846d);
    }

    public String m4904n() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_BALANCE_LAST", "0.00");
    }

    public String m4905o() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_PROFILE_COUNTRY_CODE", PrefDefaultValues.f3847e);
    }

    public String m4906p() {
        return this.f3842c.getString("com.mmdt.sipclient.model.pref.PrefKeys.KEY_WEBSERVICE_SERVER_VERSION", "4");
    }

    public int m4907q() {
        return this.f3842c.getInt("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_OBSOLUTE_VERSION_NUMBER", -1);
    }

    public int m4908r() {
        return this.f3842c.getInt("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_NIGHT_MOOD_FROM_SECOND", 0);
    }

    public int m4909s() {
        return this.f3842c.getInt("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_NIGHT_MOOD_TO_SECOND", 1439);
    }

    public boolean m4910t() {
        return this.f3842c.getBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_DEBUG_MODE", false);
    }

    public boolean m4911u() {
        return this.f3842c.getBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_IS_EXIT", true);
    }

    public boolean m4912v() {
        return this.f3842c.getBoolean("com.mmdt.sipclient.model.pref.PrefKeys.KEY_OPTION_IS_INTRODUCTION_SEEN", false);
    }

    public int m4913w() {
        return 120;
    }

    public String m4914x() {
        return UiRequests.m4495a();
    }

    public String m4915y() {
        return UiRequests.m4500b();
    }

    public String m4916z() {
        return UiRequests.m4503c();
    }
}
