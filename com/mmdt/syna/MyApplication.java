package com.mmdt.syna;

import android.accounts.AccountAuthenticatorResponse;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.mmdt.syna.p017a.PrivateSetting;
import com.mmdt.syna.p017a.p018a.CallNotification;
import com.mmdt.syna.p017a.p018a.MessagesNotificationManager;
import com.mmdt.syna.p017a.p018a.MissedCallsNotificationManager;
import com.mmdt.syna.p017a.p018a.NewContactNotification;
import com.mmdt.syna.p017a.p018a.Ringer;
import com.mmdt.syna.view.call.CallingActivity;
import com.mmdt.syna.view.components.p027c.MyToast;
import com.mmdt.syna.view.forceaction.ForceUpdateActivity;
import com.mmdt.syna.view.registeration.registernumber.RegistrationActivity;
import mobi.mmdt.ott.core.UiInterface;
import mobi.mmdt.ott.core.p051a.CountryTools;
import org.catrobat.paintroid.R.R;

/* renamed from: com.mmdt.syna.a */
class MyApplication implements UiInterface {
    MyApplication() {
    }

    public Bundle m2429a(Context context, AccountAuthenticatorResponse accountAuthenticatorResponse) {
        Parcelable intent = new Intent(context, RegistrationActivity.class);
        intent.putExtra("accountAuthenticatorResponse", accountAuthenticatorResponse);
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", intent);
        return bundle;
    }

    public String m2430a() {
        return PrivateSetting.m2403a().m2404b();
    }

    public void m2431a(Context context) {
        Intent intent = new Intent(context, ForceUpdateActivity.class);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public void m2432a(Context context, String str) {
        Intent intent = new Intent(context, CallingActivity.class);
        intent.putExtra("com.mmdt.sipclient.view.call.CallingActivity.NUMBER_FROM_CALLER", CountryTools.m4416a().m4434g(str));
        intent.putExtra("com.mmdt.sipclient.view.call.CallingActivity.IS_SUN_FROM_CALLER", CountryTools.m4416a().m4432f(str));
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public void m2433a(Context context, String str, int i) {
        MyToast.m2755a(context, str, i);
    }

    public void m2434a(Context context, String str, boolean z) {
        Intent intent = new Intent(context, CallingActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("com.mmdt.sipclient.view.call.CallingActivity.NUMBER_FROM_CALLER", str);
        intent.putExtra("com.mmdt.sipclient.view.call.CallingActivity.IS_SUN_FROM_CALLER", z);
        context.startActivity(intent);
    }

    public String m2435b() {
        return PrivateSetting.m2403a().m2405c();
    }

    public void m2436b(Context context) {
        Ringer.m2396a(context).m2401a();
    }

    public void m2437b(Context context, String str, boolean z) {
        CallNotification.m2377a(context, 1, str, z);
    }

    public String m2438c() {
        return PrivateSetting.m2403a().m2406d();
    }

    public String m2439c(Context context) {
        return context.getString(R.app_name);
    }

    public void m2440c(Context context, String str, boolean z) {
        CallNotification.m2377a(context, 2, str, z);
    }

    public String m2441d() {
        return PrivateSetting.m2403a().m2407e();
    }

    public String m2442d(Context context) {
        return context.getString(2131493762);
    }

    public void m2443e(Context context) {
        NewContactNotification.m2394a(context);
    }

    public String[] m2444e() {
        return PrivateSetting.m2403a().m2408f();
    }

    public String m2445f() {
        return PrivateSetting.m2403a().m2409g();
    }

    public void m2446f(Context context) {
        MissedCallsNotificationManager.m2391a(context);
    }

    public void m2447g(Context context) {
        MessagesNotificationManager.m2382a(context);
    }
}
