package com.mmdt.syna.view.registeration.termandconditions;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import com.mmdt.syna.view.registeration.registernumber.RegistrationActivity;
import com.mmdt.syna.view.registeration.termandconditions.TermsAndConditionsFragment.TermsAndConditionsFragment;

public class TermsAndConditionsActivity extends Activity implements TermsAndConditionsFragment {
    private ActionBar f2886a;
    private TermsAndConditionsFragment f2887b;

    public void m3745a() {
        startActivity(new Intent(this, RegistrationActivity.class));
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903211);
        this.f2886a = getActionBar();
        this.f2886a.setIcon(2130838043);
        this.f2886a.setTitle(2131493695);
        this.f2887b = new TermsAndConditionsFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2887b);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }
}
