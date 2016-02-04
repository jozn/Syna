package com.mmdt.syna.view.registeration.registerationresult;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import com.mmdt.syna.view.components.p025a.CustomDialog;
import com.mmdt.syna.view.main.MainActivity;
import com.mmdt.syna.view.more.settings.profile.MyProfileActivity;
import com.mmdt.syna.view.registeration.registerationresult.RegistrationResultFragment.RegistrationResultFragment;
import mobi.mmdt.ott.core.logic.p055d.XmppManager;

public class RegistrationResultActivity extends Activity implements RegistrationResultFragment {
    private ActionBar f2848a;
    private RegistrationResultFragment f2849b;

    private void m3718c() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(67108864);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    private void m3719d() {
        if (XmppManager.m4723a(getApplicationContext()).m4746e()) {
            startActivity(new Intent(getApplicationContext(), MyProfileActivity.class));
            overridePendingTransition(0, 0);
            return;
        }
        CustomDialog.m2737a(this, getString(2131493496)).show();
    }

    public void m3720a() {
        m3718c();
    }

    public void m3721b() {
        m3719d();
    }

    public void onBackPressed() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903186);
        this.f2848a = getActionBar();
        this.f2848a.setIcon(2130838031);
        this.f2848a.setTitle(2131493760);
        this.f2849b = new RegistrationResultFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2849b);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }
}
