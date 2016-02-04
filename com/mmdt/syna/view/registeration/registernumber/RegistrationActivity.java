package com.mmdt.syna.view.registeration.registernumber;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import com.mmdt.syna.view.registeration.accesscode.AccessCodeActivity;
import com.mmdt.syna.view.registeration.registernumber.RegistrationFragment.RegistrationFragment;

public class RegistrationActivity extends Activity implements RegistrationFragment {
    private ActionBar f2854a;
    private RegistrationFragment f2855b;

    public void m3726a() {
        startActivity(new Intent(this, AccessCodeActivity.class));
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903183);
        this.f2854a = getActionBar();
        this.f2854a.setIcon(2130838003);
        this.f2854a.setTitle(2131493620);
        this.f2855b = new RegistrationFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2855b);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
