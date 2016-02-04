package mobi.mmdt.ott.p043b.p048c;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.security.GeneralSecurityException;
import mobi.mmdt.ott.p043b.p048c.p049a.ActivationResult;
import mobi.mmdt.ott.p043b.p048c.p049a.CardInfoResult;
import mobi.mmdt.ott.p043b.p048c.p049a.ChargeResult;
import mobi.mmdt.ott.p043b.p048c.p049a.CountryNameResult;
import mobi.mmdt.ott.p043b.p048c.p049a.DeActivationResult;
import mobi.mmdt.ott.p043b.p048c.p049a.IVRResult;
import mobi.mmdt.ott.p043b.p048c.p049a.RateResult;
import mobi.mmdt.ott.p043b.p048c.p049a.RatesResult;
import mobi.mmdt.ott.p043b.p048c.p049a.RegistrationResult;
import mobi.mmdt.ott.p043b.p048c.p049a.SunContacsResult;
import mobi.mmdt.p041a.Connectivity;
import mobi.mmdt.p041a.hash;
import org.p074b.JSONException;

/* renamed from: mobi.mmdt.ott.b.c.b */
public class WebServiceManager {
    public static int m4376a(Context context) {
        return (int) Long.parseLong(WebServiceManager.m4386b(context));
    }

    public static String m4377a(float f) {
        return WebServiceManager.m4387b(new StringBuilder(String.valueOf((int) (30000.0f * f))).toString());
    }

    public static String m4378a(Context context, String str) throws NameNotFoundException {
        return "Android_" + context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName + "_" + str;
    }

    public static String m4379a(String str) {
        return WebServiceManager.m4387b(new StringBuilder(String.valueOf((int) (Float.parseFloat(str) * 30000.0f))).toString());
    }

    public static ActivationResult m4380a(Context context, String str, int i, String str2, String str3, String str4) throws IOException, JSONException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new ActivationResult(new WebService(context, str3).m4367a(str, i, hash.m4112b(str2), WebServiceManager.m4378a(context, str4)));
        }
        throw new NetworkErrorException();
    }

    public static CardInfoResult m4381a(Context context, String str, String str2, String str3, String str4) throws IOException, JSONException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new CardInfoResult(new WebService(context, str3).m4370a(str, hash.m4112b(str2), WebServiceManager.m4378a(context, str4)));
        }
        throw new NetworkErrorException();
    }

    public static CountryNameResult m4382a(Context context, String str, String str2) throws NetworkErrorException, NameNotFoundException, JSONException, IOException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new CountryNameResult(new WebService(context, str).m4366a(WebServiceManager.m4378a(context, str2)));
        }
        throw new NetworkErrorException();
    }

    public static DeActivationResult m4383a(Context context, String str, String str2, int i, String str3, String str4) throws IOException, JSONException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new DeActivationResult(new WebService(context, str3).m4369a(str, hash.m4112b(str2), i, WebServiceManager.m4378a(context, str4)));
        }
        throw new NetworkErrorException();
    }

    public static RegistrationResult m4384a(Context context, String str, String str2, String str3) throws JSONException, IOException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new RegistrationResult(new WebService(context, str2).m4368a(str, WebServiceManager.m4378a(context, str3)));
        }
        throw new NetworkErrorException();
    }

    public static SunContacsResult m4385a(Context context, String str, String str2, String[] strArr, String str3, String str4) throws JSONException, IOException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new SunContacsResult(new WebService(context, str3).m4371a(strArr, str, hash.m4112b(str2), WebServiceManager.m4378a(context, str4)));
        }
        throw new NetworkErrorException();
    }

    public static String m4386b(Context context) {
        String str = "";
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        return telephonyManager.getDeviceId() != null ? telephonyManager.getDeviceId() : Secure.getString(context.getContentResolver(), "android_id");
    }

    private static String m4387b(String str) {
        if (str.length() <= 3) {
            return str;
        }
        String str2 = str;
        for (int i = 0; i < (str.length() - 1) / 3; i++) {
            int length = (str.length() - 3) - (i * 3);
            str2 = str2.substring(0, length) + "," + str2.substring(length);
        }
        return str2;
    }

    public static ChargeResult m4388b(Context context, String str, String str2, String str3, String str4) throws JSONException, IOException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new ChargeResult(new WebService(context, str3).m4373b(str, str2, WebServiceManager.m4378a(context, str4)));
        }
        throw new NetworkErrorException();
    }

    public static IVRResult m4389b(Context context, String str, String str2, String str3) throws JSONException, IOException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new IVRResult(new WebService(context, str2).m4372b(str, WebServiceManager.m4378a(context, str3)));
        }
        throw new NetworkErrorException();
    }

    public static RateResult m4390c(Context context, String str, String str2, String str3) throws IOException, JSONException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new RateResult(new WebService(context, str2).m4375d(str, WebServiceManager.m4378a(context, str3)));
        }
        throw new NetworkErrorException();
    }

    public static RatesResult m4391d(Context context, String str, String str2, String str3) throws IOException, JSONException, NetworkErrorException, NameNotFoundException, GeneralSecurityException {
        if (Connectivity.m4082b(context)) {
            return new RatesResult(new WebService(context, str2).m4374c(str, WebServiceManager.m4378a(context, str3)));
        }
        throw new NetworkErrorException();
    }
}
