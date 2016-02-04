package com.mmdt.syna.view.forceaction;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import mobi.mmdt.ott.core.logic.ServiceManager;
import mobi.mmdt.ott.core.model.p058a.AppSettings;

/* renamed from: com.mmdt.syna.view.forceaction.a */
class ForceUpdateActivity implements OnClickListener {
    final /* synthetic */ ForceUpdateActivity f2405a;
    private final /* synthetic */ Dialog f2406b;

    ForceUpdateActivity(ForceUpdateActivity forceUpdateActivity, Dialog dialog) {
        this.f2405a = forceUpdateActivity;
        this.f2406b = dialog;
    }

    public void onClick(View view) {
        boolean z = true;
        this.f2406b.dismiss();
        AppSettings.m4867a(this.f2405a.getApplicationContext()).m4874a(true);
        ServiceManager.m4550a(this.f2405a.getApplicationContext());
        Account[] accountsByType = AccountManager.get(this.f2405a.getApplicationContext()).getAccountsByType("com.google");
        if (accountsByType == null || accountsByType.length <= 0) {
            z = false;
        }
        this.f2405a.startActivity(z ? new Intent("android.intent.action.VIEW").setData(Uri.parse("market://details?id=" + this.f2405a.getPackageName())) : new Intent("android.intent.action.VIEW").setData(Uri.parse("http://syna-app.ir/apk/latest/")));
        this.f2405a.finish();
    }
}
