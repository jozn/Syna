package mobi.mmdt.ott.p043b.p044a.p045a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Random;
import mobi.mmdt.ott.p043b.p044a.AmirWebserviceException;
import mobi.mmdt.ott.p043b.p044a.ClientAuthData;
import mobi.mmdt.ott.p043b.p044a.ServerAuthData;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.SessionResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StarsResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerAddressResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerCategoriesResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerCollectionResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerCollectionsResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerPackageAddressResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerPackageDetailResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerUrlResult;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StrickerCategoryResult;
import mobi.mmdt.ott.p043b.p047b.RestfulWS;
import mobi.mmdt.ott.p043b.p050d.WebservicesTools;
import mobi.mmdt.p041a.hash;
import org.p074b.JSONArray;
import org.p074b.JSONException;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.b.a.a.a */
public class StickerWebservices {
    private static StickerWebservices f3382a;
    private Context f3383b;
    private String f3384c;
    private String f3385d;
    private String f3386e;
    private String f3387f;
    private String f3388g;

    private StickerWebservices(Context context, String str, String str2, String str3, String str4) throws NameNotFoundException, JSONException, IOException, AmirWebserviceException, NumberFormatException, GeneralSecurityException {
        this.f3387f = null;
        this.f3383b = context;
        this.f3386e = str2;
        this.f3388g = str4;
        this.f3385d = str3;
        this.f3384c = str;
        this.f3387f = m4306b().m4278a();
    }

    public static StickerWebservices m4305a(Context context, String str, String str2, String str3, String str4) throws NameNotFoundException, JSONException, IOException, AmirWebserviceException, NumberFormatException, GeneralSecurityException {
        if (f3382a == null) {
            f3382a = new StickerWebservices(context, str, str2, str3, str4);
        }
        return f3382a;
    }

    private SessionResult m4306b() throws JSONException, IOException, AmirWebserviceException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        Object obj;
        String[] strArr = new String[]{WebservicesTools.m4392a(this.f3383b)};
        if (this.f3388g.equals("fa")) {
            obj = "fa";
        } else {
            String str = "en";
        }
        Object obj2 = strArr[0];
        int i = 1;
        while (i < strArr.length) {
            i++;
            String stringBuilder = new StringBuilder(String.valueOf(obj2)).append(",").append(strArr[i]).toString();
        }
        long currentTimeMillis = System.currentTimeMillis();
        String a = hash.m4111a(new StringBuilder(String.valueOf((new Random(System.currentTimeMillis()).nextLong() | currentTimeMillis) | Long.parseLong(this.f3386e))).toString());
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("ClientAuthData", new ClientAuthData(this.f3383b).m4324a(a, currentTimeMillis, this.f3386e, this.f3385d));
        jSONObject.m5359a("DeviceModel", Build.MODEL);
        jSONObject.m5359a("Language", obj);
        jSONObject.m5359a("Resolution", obj2);
        JSONObject b = RestfulWS.m4339b(this.f3383b, this.f3384c + "/" + "authentication", jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            return new SessionResult(new ServerAuthData(b.m5363e("ServerAuthData")), b.m5361c("ResultCode"), b.m5365g("SessionKey"), b.m5365g("Language"), b.m5365g("DeviceModel"), b.m5365g("Resolution"));
        }
        throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
    }

    private StickerCollectionsResult m4307c(String str, String str2, String str3) throws JSONException, IOException, AmirWebserviceException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("SessionKey", this.f3387f);
        jSONObject.m5359a("Count", (Object) str);
        jSONObject.m5359a("Offset", (Object) str2);
        JSONObject b = RestfulWS.m4339b(this.f3383b, str3, jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            JSONArray d = b.m5362d("Packages");
            StickerCollectionResult[] stickerCollectionResultArr = new StickerCollectionResult[d.m5344a()];
            for (int i = 0; i < stickerCollectionResultArr.length; i++) {
                stickerCollectionResultArr[i] = new StickerCollectionResult(d.m5348b(i).m5365g("ID"), d.m5348b(i).m5365g("Title"), d.m5348b(i).m5365g("Thumbnail"), d.m5348b(i).m5365g("Price"), d.m5348b(i).m5365g("Downloads"));
            }
            return new StickerCollectionsResult(b.m5361c("Count"), b.m5361c("Offset"), b.m5361c("ResultCode"), b.m5365g("SessionKey"), stickerCollectionResultArr);
        } else if (b.m5361c("ResultCode") == 314 || b.m5361c("ResultCode") == 315) {
            this.f3387f = m4306b().m4278a();
            return m4307c(str, str2, str3);
        } else {
            throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
        }
    }

    public StickerAddressResult m4308a(String str, String str2, String str3, String str4) throws JSONException, IOException, AmirWebserviceException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        Object a = WebservicesTools.m4392a(this.f3383b);
        Object obj = Build.MODEL;
        long currentTimeMillis = System.currentTimeMillis();
        String a2 = hash.m4111a(new StringBuilder(String.valueOf((new Random(System.currentTimeMillis()).nextLong() | currentTimeMillis) | Long.parseLong(this.f3386e))).toString());
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("ClientAuthData", new ClientAuthData(this.f3383b).m4324a(a2, currentTimeMillis, this.f3386e, this.f3385d));
        jSONObject.m5359a("FromUsername", (Object) str);
        jSONObject.m5359a("StickerVersion", (Object) str2);
        jSONObject.m5359a("PackageID", (Object) str3);
        jSONObject.m5359a("StickerID", (Object) str4);
        jSONObject.m5359a("Resolution", a);
        jSONObject.m5359a("DeviceModel", obj);
        JSONObject b = RestfulWS.m4339b(this.f3383b, this.f3384c + "/" + "stickers" + "/" + "download", jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            JSONObject e = b.m5363e("StickerURL");
            return new StickerAddressResult(b.m5361c("ResultCode"), b.m5365g("StickerVersion"), b.m5365g("StickerID"), b.m5365g("PackageStickerID"), b.m5365g("FromUsername"), b.m5365g("Resolution"), new StickerUrlResult(e.m5365g("ldpi"), e.m5365g("mdpi"), e.m5365g("hdpi"), e.m5365g("xhdpi"), e.m5365g("xxhdpi"), e.m5365g("xxxhdpi")));
        } else if (b.m5361c("ResultCode") == 314 || b.m5361c("ResultCode") == 315) {
            this.f3387f = m4306b().m4278a();
            return m4308a(str, str2, str3, str4);
        } else {
            throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
        }
    }

    public StickerCategoriesResult m4309a() throws JSONException, IOException, AmirWebserviceException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("SessionKey", this.f3387f);
        JSONObject b = RestfulWS.m4339b(this.f3383b, this.f3384c + "/" + "browse" + "/" + "index", jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            JSONArray d = b.m5362d("Categories");
            StrickerCategoryResult[] strickerCategoryResultArr = new StrickerCategoryResult[d.m5344a()];
            for (int i = 0; i < strickerCategoryResultArr.length; i++) {
                strickerCategoryResultArr[i] = new StrickerCategoryResult(d.m5348b(i).m5365g("ID"), d.m5348b(i).m5365g("Name"), d.m5348b(i).m5365g("Description"), d.m5348b(i).m5365g("Thumbnail"));
            }
            return new StickerCategoriesResult(b.m5361c("ResultCode"), b.m5365g("SessionKey"), b.m5361c("Count"), strickerCategoryResultArr);
        } else if (b.m5361c("ResultCode") == 314 || b.m5361c("ResultCode") == 315) {
            this.f3387f = m4306b().m4278a();
            return m4309a();
        } else {
            throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
        }
    }

    public StickerCollectionsResult m4310a(String str, String str2, String str3) throws AmirWebserviceException, JSONException, IOException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        return m4307c(str, str2, this.f3384c + "/" + "browse" + "/" + "index" + "/" + str3);
    }

    public StickerPackageDetailResult m4311a(String str) throws JSONException, IOException, AmirWebserviceException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("SessionKey", this.f3387f);
        JSONObject b = RestfulWS.m4339b(this.f3383b, this.f3384c + "/" + "packages" + "/" + "view" + "/" + str, jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            JSONObject e = b.m5363e("Stars");
            StarsResult starsResult = new StarsResult(e.m5361c("One"), e.m5361c("Two"), e.m5361c("Three"), e.m5361c("Four"), e.m5361c("Five"));
            return new StickerPackageDetailResult(b.m5361c("ResultCode"), b.m5365g("SessionKey"), b.m5365g("ID"), b.m5365g("CategoryID"), b.m5365g("DateAdded"), b.m5365g("Title"), b.m5365g("Description"), b.m5365g("Author"), b.m5365g("Price"), b.m5365g("Thumbnail"), b.m5365g("StickersThumbnail"), b.m5365g("StarMean"), starsResult, b.m5365g("Downloads"));
        } else if (b.m5361c("ResultCode") == 314 || b.m5361c("ResultCode") == 315) {
            this.f3387f = m4306b().m4278a();
            return m4311a(str);
        } else {
            throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
        }
    }

    public StickerCollectionsResult m4312b(String str, String str2, String str3) throws AmirWebserviceException, JSONException, IOException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        return m4307c(str, str2, this.f3384c + "/" + "browse" + "/" + str3);
    }

    public StickerPackageAddressResult m4313b(String str) throws JSONException, IOException, AmirWebserviceException, NameNotFoundException, NumberFormatException, GeneralSecurityException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.m5359a("SessionKey", this.f3387f);
        JSONObject b = RestfulWS.m4339b(this.f3383b, this.f3384c + "/" + "packages" + "/" + "download" + "/" + str, jSONObject, null);
        if (new StringBuilder(String.valueOf(b.m5361c("ResultCode"))).toString().startsWith("2")) {
            JSONObject e = b.m5363e("PackageData");
            return new StickerPackageAddressResult(b.m5365g("SessionKey"), b.m5365g("PackageID"), new StickerUrlResult(e.m5365g("ldpi"), e.m5365g("mdpi"), e.m5365g("hdpi"), e.m5365g("xhdpi"), e.m5365g("xxhdpi"), e.m5365g("xxxhdpi")));
        } else if (b.m5361c("ResultCode") == 314 || b.m5361c("ResultCode") == 315) {
            this.f3387f = m4306b().m4278a();
            return m4313b(str);
        } else {
            throw new AmirWebserviceException(b.m5361c("ResultCode"), b.m5365g("ResultMessage"));
        }
    }
}
