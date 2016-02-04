package mobi.mmdt.ott.core.p051a;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import java.io.IOException;
import java.net.MalformedURLException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Locale;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import mobi.mmdt.ott.core.UiRequests;
import mobi.mmdt.ott.core.logic.p034c.Downloader;
import mobi.mmdt.ott.core.logic.p055d.VCardProfile;
import mobi.mmdt.ott.core.logic.p055d.XmppManager;
import mobi.mmdt.ott.core.logic.p055d.p057a.IXmppException;
import mobi.mmdt.ott.core.logic.p055d.p057a.NotConnectedException;
import mobi.mmdt.ott.core.model.contactmanager.SyncAdapter;
import mobi.mmdt.ott.core.model.contactmanager.User;
import mobi.mmdt.ott.core.model.database.p060a.AndroidContacts;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.database.p063d.Groups;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.model.p059b.RateLoader;
import mobi.mmdt.ott.core.model.p059b.RateObject;
import mobi.mmdt.ott.p043b.p044a.AmirWebservice;
import mobi.mmdt.ott.p043b.p044a.AmirWebserviceException;
import mobi.mmdt.ott.p043b.p044a.Room;
import mobi.mmdt.ott.p043b.p048c.WebServiceManager;
import mobi.mmdt.ott.p043b.p048c.p049a.SunContacsResult;
import mobi.mmdt.p041a.Connectivity;
import org.apache.http.client.ClientProtocolException;
import org.p074b.JSONException;

/* renamed from: mobi.mmdt.ott.core.a.f */
public class Statics {
    private static boolean f3514a;

    static {
        f3514a = false;
    }

    public static int m4452a(Context context, String str) {
        return MediaPlayer.create(context, Uri.parse(str)).getDuration();
    }

    public static String m4453a(Context context, String str, boolean z) {
        if (Connectivity.m4082b(context)) {
            try {
                return WebServiceManager.m4390c(context, CountryTools.m4416a().m4425c(context, str), UiRequests.m4503c(), AppSettings.m4867a(context).m4906p()).m4358a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String a = CountryTools.m4416a().m4421a(str);
        String c = CountryTools.m4416a().m4426c(a);
        if (str.startsWith("00")) {
            str = str.replaceFirst("00", "");
        }
        String a2 = RateLoader.m4917a(context, str);
        RateObject a3 = RateLoader.m4918a(context, a, c);
        a = (String) a3.m4919a().get("Fix");
        if (a2.toLowerCase(Locale.US).contains("mci")) {
            return (String) a3.m4919a().get("MCI");
        } else if (!a2.toLowerCase(Locale.US).contains("mobile")) {
            return a;
        } else {
            return (String) a3.m4919a().get("Mobile");
        }
    }

    public static HashMap<String, User> m4454a(Context context, HashMap<String, User> hashMap) throws JSONException, NameNotFoundException, NetworkErrorException, IOException, GeneralSecurityException {
        int i = 0;
        String[] strArr = (String[]) hashMap.keySet().toArray(new String[hashMap.size()]);
        String[] strArr2 = new String[strArr.length];
        for (int i2 = 0; i2 < strArr2.length; i2++) {
            strArr2[i2] = CountryTools.m4416a().m4425c(context, strArr[i2]);
        }
        SunContacsResult a = WebServiceManager.m4385a(context, AppSettings.m4867a(context).m4897j(), AppSettings.m4867a(context).m4901l(), strArr2, UiRequests.m4506d(), AppSettings.m4867a(context).m4906p());
        if (a.m4364b() && a.m4363a() == 1001) {
            AppSettings.m4867a(context).m4870a(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            return null;
        }
        HashMap<String, User> hashMap2 = new HashMap();
        while (i < a.m4365c().length) {
            User user = new User();
            user.f3875c = ((User) hashMap.get("00" + a.m4365c()[i])).m4925c();
            user.f3874b = ((User) hashMap.get("00" + a.m4365c()[i])).m4924b();
            hashMap2.put("00" + a.m4365c()[i], user);
            i++;
        }
        SyncAdapter.f3871a = hashMap2;
        return hashMap2;
    }

    public static synchronized void m4455a(Context context) {
        synchronized (Statics.class) {
            if (!f3514a) {
                f3514a = true;
                Thread thread = new Thread(new Statics(context));
                thread.setPriority(1);
                thread.start();
                thread = new Thread(new Statics(context));
                thread.setPriority(1);
                thread.start();
            }
        }
    }

    public static void m4456a(Context context, int i) {
        SynaContacts.m4992a(context, i);
    }

    public static synchronized void m4457a(Context context, String str, int i) {
        synchronized (Statics.class) {
            UiRequests.m4498a(context, str, i);
        }
    }

    public static synchronized void m4458a(Context context, HashMap<String, User> hashMap, boolean z) throws NetworkErrorException, JSONException, IOException, NameNotFoundException, IXmppException, GeneralSecurityException {
        synchronized (Statics.class) {
            HashMap a = Statics.m4454a(context, (HashMap) hashMap);
            Account account = new Account(UiRequests.m4496a(context).toUpperCase(), UiRequests.m4501b(context));
            Bundle bundle = new Bundle();
            bundle.putBoolean("force", true);
            bundle.putBoolean("expedited", true);
            ContentResolver.requestSync(account, "com.android.contacts", bundle);
            Statics.m4459a(context, (String[]) a.keySet().toArray(new String[a.size()]), z);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void m4459a(android.content.Context r37, java.lang.String[] r38, boolean r39) {
        /*
        r35 = mobi.mmdt.ott.core.p051a.Statics.class;
        monitor-enter(r35);
        r4 = mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m5015d(r37);	 Catch:{ all -> 0x0239 }
        if (r4 != 0) goto L_0x0032;
    L_0x0009:
        r18 = 0;
    L_0x000b:
        if (r39 == 0) goto L_0x0010;
    L_0x000d:
        mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m4997a(r37, r38);	 Catch:{ all -> 0x0239 }
    L_0x0010:
        r4 = mobi.mmdt.ott.core.logic.p055d.XmppManager.m4723a(r37);	 Catch:{ all -> 0x0239 }
        r5 = 1;
        r4 = r4.m4737a(r5);	 Catch:{ all -> 0x0239 }
        if (r4 == 0) goto L_0x023c;
    L_0x001b:
        r36 = new java.util.ArrayList;	 Catch:{ all -> 0x0239 }
        r36.<init>();	 Catch:{ all -> 0x0239 }
        r4 = 0;
        r34 = r4;
    L_0x0023:
        r0 = r38;
        r4 = r0.length;	 Catch:{ all -> 0x0239 }
        r0 = r34;
        if (r0 < r4) goto L_0x0035;
    L_0x002a:
        mobi.mmdt.ott.core.p051a.Statics.m4471k(r37);	 Catch:{ all -> 0x0239 }
    L_0x002d:
        mobi.mmdt.ott.core.UiRequests.m4508e(r37);	 Catch:{ all -> 0x0239 }
        monitor-exit(r35);
        return;
    L_0x0032:
        r18 = 1;
        goto L_0x000b;
    L_0x0035:
        r4 = mobi.mmdt.ott.core.model.p058a.AppSettings.m4867a(r37);	 Catch:{ all -> 0x0239 }
        r4 = r4.m4911u();	 Catch:{ all -> 0x0239 }
        if (r4 != 0) goto L_0x002a;
    L_0x003f:
        r4 = mobi.mmdt.ott.core.logic.p055d.XmppManager.m4723a(r37);	 Catch:{ Exception -> 0x0233 }
        r5 = r38[r34];	 Catch:{ Exception -> 0x0233 }
        r13 = r4.m4741c(r5);	 Catch:{ Exception -> 0x0233 }
        r4 = new mobi.mmdt.ott.core.model.database.b.c;	 Catch:{ Exception -> 0x0233 }
        r5 = r13.m4719f();	 Catch:{ Exception -> 0x0233 }
        r6 = r13.m4720g();	 Catch:{ Exception -> 0x0233 }
        r7 = r38[r34];	 Catch:{ Exception -> 0x0233 }
        r8 = r13.m4718e();	 Catch:{ Exception -> 0x0233 }
        r9 = 0;
        r10 = r13.m4714a();	 Catch:{ Exception -> 0x0233 }
        r11 = r13.m4716c();	 Catch:{ Exception -> 0x0233 }
        r12 = r13.m4715b();	 Catch:{ Exception -> 0x0233 }
        r13 = r13.m4717d();	 Catch:{ Exception -> 0x0233 }
        r14 = mobi.mmdt.ott.core.logic.p055d.XmppManager.m4723a(r37);	 Catch:{ Exception -> 0x0233 }
        r15 = r38[r34];	 Catch:{ Exception -> 0x0233 }
        r14 = r14.m4743d(r15);	 Catch:{ Exception -> 0x0233 }
        r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);	 Catch:{ Exception -> 0x0233 }
        r0 = r36;
        r0.add(r4);	 Catch:{ Exception -> 0x0233 }
        r5 = 0;
        r6 = r4.m4990i();	 Catch:{ Exception -> 0x0233 }
        r0 = r37;
        r20 = mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m5012c(r0, r6);	 Catch:{ Exception -> 0x0233 }
        r6 = r4.m4990i();	 Catch:{ Exception -> 0x0233 }
        r0 = r37;
        r6 = mobi.mmdt.ott.core.model.database.p060a.AndroidContacts.m4949d(r0, r6);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x0099;
    L_0x0093:
        r0 = r37;
        r5 = mobi.mmdt.ott.core.model.database.p060a.AndroidContacts.m4950e(r0, r6);	 Catch:{ Exception -> 0x0233 }
    L_0x0099:
        r6 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00ab;
    L_0x009f:
        r6 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        r7 = "";
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x0139;
    L_0x00ab:
        r6 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00bd;
    L_0x00b1:
        r6 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        r7 = "";
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x0139;
    L_0x00bd:
        r6 = r4.m4985d();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00cf;
    L_0x00c3:
        r6 = r4.m4985d();	 Catch:{ Exception -> 0x0233 }
        r7 = "";
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x0139;
    L_0x00cf:
        if (r5 == 0) goto L_0x0134;
    L_0x00d1:
        r8 = r5.m4951a();	 Catch:{ Exception -> 0x0233 }
    L_0x00d5:
        if (r8 == 0) goto L_0x00df;
    L_0x00d7:
        r6 = "";
        r6 = r8.equals(r6);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00e3;
    L_0x00df:
        r8 = r4.m4990i();	 Catch:{ Exception -> 0x0233 }
    L_0x00e3:
        r12 = 0;
        r6 = r4.m4987f();	 Catch:{ Exception -> 0x0233 }
        if (r6 != 0) goto L_0x01b4;
    L_0x00ea:
        if (r5 == 0) goto L_0x00fa;
    L_0x00ec:
        r6 = r5.m4952b();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00fa;
    L_0x00f2:
        r5 = r5.m4952b();	 Catch:{ Exception -> 0x0233 }
        r12 = android.net.Uri.parse(r5);	 Catch:{ Exception -> 0x0233 }
    L_0x00fa:
        r6 = -1;
        r5 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1));
        if (r5 != 0) goto L_0x01fb;
    L_0x0100:
        r6 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        r7 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        r9 = r4.m4984c();	 Catch:{ Exception -> 0x0233 }
        r10 = r4.m4990i();	 Catch:{ Exception -> 0x0233 }
        r11 = r4.m4983b();	 Catch:{ Exception -> 0x0233 }
        r13 = r4.m4987f();	 Catch:{ Exception -> 0x0233 }
        r14 = r4.m4986e();	 Catch:{ Exception -> 0x0233 }
        r15 = r4.m4982a();	 Catch:{ Exception -> 0x0233 }
        r4 = java.lang.System.nanoTime();	 Catch:{ Exception -> 0x0233 }
        r16 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r16 = r4 / r16;
        r5 = r37;
        mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m4998a(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18);	 Catch:{ Exception -> 0x0233 }
    L_0x012e:
        r4 = r34 + 1;
        r34 = r4;
        goto L_0x0023;
    L_0x0134:
        r8 = r4.m4990i();	 Catch:{ Exception -> 0x0233 }
        goto L_0x00d5;
    L_0x0139:
        r6 = r4.m4985d();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x014b;
    L_0x013f:
        r6 = r4.m4985d();	 Catch:{ Exception -> 0x0233 }
        r7 = "";
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x01ae;
    L_0x014b:
        r6 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x0190;
    L_0x0151:
        r6 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        r7 = "";
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 != 0) goto L_0x0190;
    L_0x015d:
        r6 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x0190;
    L_0x0163:
        r6 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        r7 = "";
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 != 0) goto L_0x0190;
    L_0x016f:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0233 }
        r7 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ Exception -> 0x0233 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x0233 }
        r7 = " ";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0233 }
        r7 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0233 }
        r8 = r6.toString();	 Catch:{ Exception -> 0x0233 }
        goto L_0x00d5;
    L_0x0190:
        r6 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x01a2;
    L_0x0196:
        r6 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        r7 = "";
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x01a8;
    L_0x01a2:
        r8 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        goto L_0x00d5;
    L_0x01a8:
        r8 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        goto L_0x00d5;
    L_0x01ae:
        r8 = r4.m4985d();	 Catch:{ Exception -> 0x0233 }
        goto L_0x00d5;
    L_0x01b4:
        r6 = -1;
        r5 = (r20 > r6 ? 1 : (r20 == r6 ? 0 : -1));
        if (r5 == 0) goto L_0x00fa;
    L_0x01ba:
        r0 = r37;
        r1 = r20;
        r5 = mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m5008b(r0, r1);	 Catch:{ Exception -> 0x0233 }
        if (r5 == 0) goto L_0x00fa;
    L_0x01c4:
        r6 = r5.m4981b();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00fa;
    L_0x01ca:
        r6 = r5.m4981b();	 Catch:{ Exception -> 0x0233 }
        r7 = r4.m4987f();	 Catch:{ Exception -> 0x0233 }
        r6 = r6.equals(r7);	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00fa;
    L_0x01d8:
        r6 = r5.m4980a();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00fa;
    L_0x01de:
        r6 = new java.io.File;	 Catch:{ Exception -> 0x0233 }
        r7 = r5.m4980a();	 Catch:{ Exception -> 0x0233 }
        r7 = java.net.URI.create(r7);	 Catch:{ Exception -> 0x0233 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x0233 }
        r6 = r6.exists();	 Catch:{ Exception -> 0x0233 }
        if (r6 == 0) goto L_0x00fa;
    L_0x01f1:
        r5 = r5.m4980a();	 Catch:{ Exception -> 0x0233 }
        r12 = android.net.Uri.parse(r5);	 Catch:{ Exception -> 0x0233 }
        goto L_0x00fa;
    L_0x01fb:
        r22 = r4.m4989h();	 Catch:{ Exception -> 0x0233 }
        r23 = r4.m4988g();	 Catch:{ Exception -> 0x0233 }
        r25 = r4.m4984c();	 Catch:{ Exception -> 0x0233 }
        r26 = r4.m4990i();	 Catch:{ Exception -> 0x0233 }
        r27 = r4.m4983b();	 Catch:{ Exception -> 0x0233 }
        r29 = r4.m4987f();	 Catch:{ Exception -> 0x0233 }
        r30 = r4.m4986e();	 Catch:{ Exception -> 0x0233 }
        r4 = r4.m4982a();	 Catch:{ Exception -> 0x0233 }
        r31 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0233 }
        r4 = java.lang.System.nanoTime();	 Catch:{ Exception -> 0x0233 }
        r6 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r32 = r4 / r6;
        r19 = r37;
        r24 = r8;
        r28 = r12;
        mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m4996a(r19, r20, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32);	 Catch:{ Exception -> 0x0233 }
        goto L_0x012e;
    L_0x0233:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ all -> 0x0239 }
        goto L_0x012e;
    L_0x0239:
        r4 = move-exception;
        monitor-exit(r35);
        throw r4;
    L_0x023c:
        r4 = 0;
        r19 = r4;
    L_0x023f:
        r0 = r38;
        r4 = r0.length;	 Catch:{ all -> 0x0239 }
        r0 = r19;
        if (r0 >= r4) goto L_0x002d;
    L_0x0246:
        r4 = mobi.mmdt.ott.core.model.p058a.AppSettings.m4867a(r37);	 Catch:{ all -> 0x0239 }
        r4 = r4.m4911u();	 Catch:{ all -> 0x0239 }
        if (r4 != 0) goto L_0x002d;
    L_0x0250:
        r4 = 0;
        r5 = r38[r19];	 Catch:{ Exception -> 0x02a5 }
        r0 = r37;
        r6 = mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m5012c(r0, r5);	 Catch:{ Exception -> 0x02a5 }
        r8 = -1;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 != 0) goto L_0x029d;
    L_0x025f:
        r5 = r38[r19];	 Catch:{ Exception -> 0x02a5 }
        r0 = r37;
        r5 = mobi.mmdt.ott.core.model.database.p060a.AndroidContacts.m4949d(r0, r5);	 Catch:{ Exception -> 0x02a5 }
        if (r5 == 0) goto L_0x026f;
    L_0x0269:
        r0 = r37;
        r4 = mobi.mmdt.ott.core.model.database.p060a.AndroidContacts.m4950e(r0, r5);	 Catch:{ Exception -> 0x02a5 }
    L_0x026f:
        if (r4 == 0) goto L_0x02a2;
    L_0x0271:
        r8 = r4.m4951a();	 Catch:{ Exception -> 0x02a5 }
    L_0x0275:
        r12 = 0;
        if (r4 == 0) goto L_0x0286;
    L_0x0278:
        r5 = r4.m4952b();	 Catch:{ Exception -> 0x02a5 }
        if (r5 == 0) goto L_0x0286;
    L_0x027e:
        r4 = r4.m4952b();	 Catch:{ Exception -> 0x02a5 }
        r12 = android.net.Uri.parse(r4);	 Catch:{ Exception -> 0x02a5 }
    L_0x0286:
        r6 = 0;
        r7 = 0;
        r9 = 0;
        r10 = r38[r19];	 Catch:{ Exception -> 0x02a5 }
        r11 = 0;
        r13 = 0;
        r14 = 0;
        r15 = 2;
        r4 = java.lang.System.nanoTime();	 Catch:{ Exception -> 0x02a5 }
        r16 = 1000000; // 0xf4240 float:1.401298E-39 double:4.940656E-318;
        r16 = r4 / r16;
        r5 = r37;
        mobi.mmdt.ott.core.model.database.p061b.SynaContacts.m4998a(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18);	 Catch:{ Exception -> 0x02a5 }
    L_0x029d:
        r4 = r19 + 1;
        r19 = r4;
        goto L_0x023f;
    L_0x02a2:
        r8 = r38[r19];	 Catch:{ Exception -> 0x02a5 }
        goto L_0x0275;
    L_0x02a5:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ all -> 0x0239 }
        goto L_0x029d;
        */
        throw new UnsupportedOperationException("Method not decompiled: mobi.mmdt.ott.core.a.f.a(android.content.Context, java.lang.String[], boolean):void");
    }

    public static boolean m4460a(Context context, VCardProfile vCardProfile) throws IXmppException, MalformedURLException {
        if (!XmppManager.m4723a(context).m4737a(true)) {
            return false;
        }
        XmppManager.m4723a(context).m4733a(vCardProfile);
        Statics.m4464d(context);
        return true;
    }

    public static void m4461b(Context context) throws NetworkErrorException, ClientProtocolException, NameNotFoundException, JSONException, IOException {
    }

    public static void m4462b(Context context, String str) throws IOException {
        String a = FileManager.m4437a(context).m4439a("MyAvatar_" + AppSettings.m4867a(context).m4897j() + ".jpg");
        Downloader downloader = new Downloader(context, str, 1576, 65536, a);
        downloader.m4638a(new Statics(context, a));
        downloader.m4645a();
    }

    public static void m4463c(Context context) {
        if (XmppManager.m4723a(context).m4737a(true)) {
            String[] strArr = (String[]) AndroidContacts.m4943a(context).keySet().toArray(new String[AndroidContacts.m4943a(context).keySet().size()]);
            for (int i = 0; i < strArr.length; i++) {
                if (SynaContacts.m5010b(context, strArr[i])) {
                    SynaContacts.m4993a(context, SynaContacts.m5012c(context, strArr[i]), XmppManager.m4723a(context).m4743d(strArr[i]));
                }
            }
            return;
        }
        SynaContacts.m4992a(context, 2);
    }

    public static boolean m4464d(Context context) throws IXmppException {
        if (!XmppManager.m4723a(context).m4737a(true)) {
            return false;
        }
        if (XmppManager.m4723a(context).m4744d() == null) {
            return true;
        }
        VCardProfile d = XmppManager.m4723a(context).m4744d();
        if (d.m4718e() != null) {
            AppSettings.m4867a(context).m4896i(d.m4718e());
            try {
                Statics.m4462b(context, d.m4718e());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AppSettings.m4867a(context).m4890f(d.m4719f());
        AppSettings.m4867a(context).m4872a(d.m4714a());
        AppSettings.m4867a(context).m4892g(d.m4720g());
        AppSettings.m4867a(context).m4893h(d.m4716c());
        AppSettings.m4867a(context).m4898j(d.m4715b());
        return true;
    }

    public static synchronized void m4465e(Context context) throws NetworkErrorException, NameNotFoundException, JSONException, IOException, GeneralSecurityException {
        synchronized (Statics.class) {
            if (Connectivity.m4082b(context)) {
                HashMap a = Statics.m4454a(context, AndroidContacts.m4943a(context));
                try {
                    Account account = new Account(UiRequests.m4496a(context).toUpperCase(), UiRequests.m4501b(context));
                    ((AccountManager) context.getSystemService("account")).addAccountExplicitly(account, null, null);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("force", true);
                    bundle.putBoolean("expedited", true);
                    ContentResolver.requestSync(account, "com.android.contacts", bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Statics.m4459a(context, (String[]) a.keySet().toArray(new String[a.size()]), true);
            } else {
                throw new NetworkErrorException();
            }
        }
    }

    public static synchronized void m4466f(Context context) throws NumberFormatException, NameNotFoundException, JSONException, IOException, AmirWebserviceException, NotConnectedException, GeneralSecurityException {
        synchronized (Statics.class) {
            if (Connectivity.m4082b(context)) {
                Room[] a = AmirWebservice.m4314a(context, UiRequests.m4495a(), AppSettings.m4867a(context).m4897j(), AppSettings.m4867a(context).m4901l()).m4315a().m4325a();
                for (int i = 0; i < a.length; i++) {
                    int i2 = 22;
                    if (a[i].m4329d().equals("R")) {
                        i2 = 23;
                    }
                    if (Groups.m5054c(context, a[i].m4328c())) {
                        Groups.m5045a(context, a[i].m4328c(), a[i].m4333h());
                        Groups.m5044a(context, a[i].m4328c(), a[i].m4332g(), a[i].m4330e(), null, a[i].m4331f(), a[i].m4326a(), -1, i2, a[i].m4327b());
                    } else {
                        Groups.m5046a(context, a[i].m4328c(), a[i].m4332g(), a[i].m4330e(), null, a[i].m4331f(), a[i].m4326a(), 11, i2, a[i].m4333h(), true, true, a[i].m4327b());
                    }
                }
                Statics.m4470j(context);
            } else {
                throw new NotConnectedException();
            }
        }
    }

    public static void m4467g(Context context) throws ParserConfigurationException, ClientProtocolException, NetworkErrorException, JSONException, IOException, TransformerException, NameNotFoundException {
    }

    private static void m4469i(Context context) {
    }

    private static void m4470j(Context context) {
        Thread thread = new Thread(new Statics(context));
        thread.setPriority(1);
        thread.start();
    }

    private static void m4471k(Context context) {
        Thread thread = new Thread(new Statics(context));
        thread.setPriority(1);
        thread.start();
    }
}
