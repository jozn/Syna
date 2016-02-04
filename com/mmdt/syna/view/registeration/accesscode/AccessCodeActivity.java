package com.mmdt.syna.view.registeration.accesscode;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.mmdt.syna.view.registeration.accesscode.AccessCodeFragment.AccessCodeFragment;
import com.mmdt.syna.view.registeration.registerationresult.RegistrationResultActivity;
import com.mmdt.syna.view.registeration.registernumber.RegistrationActivity;
import com.mmdt.syna.view.registeration.termandconditions.TermsAndConditionsActivity;
import mobi.mmdt.ott.core.model.p058a.AppSettings;

public class AccessCodeActivity extends Activity implements AccessCodeFragment {
    private ActionBar f2798a;
    private AccessCodeFragment f2799b;

    public void m3683a() {
        startActivity(new Intent(this, RegistrationResultActivity.class));
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
        AppSettings.m4867a(getApplicationContext()).m4882c(null);
        AppSettings.m4867a(getApplicationContext()).m4888e(null);
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903067);
        this.f2798a = getActionBar();
        this.f2798a.setDisplayHomeAsUpEnabled(true);
        this.f2798a.setIcon(2130837879);
        this.f2798a.setTitle(2131493573);
        this.f2799b = new AccessCodeFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2799b);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                AppSettings.m4867a(getApplicationContext()).m4882c(null);
                AppSettings.m4867a(getApplicationContext()).m4888e(null);
                startActivity(new Intent(getApplicationContext(), TermsAndConditionsActivity.class));
                overridePendingTransition(0, 0);
                finish();
                overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
