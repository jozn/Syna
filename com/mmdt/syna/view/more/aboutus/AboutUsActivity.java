package com.mmdt.syna.view.more.aboutus;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutUsActivity extends Activity {
    private ActionBar f2568a;
    private AboutUsFragment f2569b;

    public void onBackPressed() {
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903065);
        this.f2568a = getActionBar();
        this.f2568a.setDisplayHomeAsUpEnabled(true);
        this.f2568a.setIcon(2130837860);
        this.f2568a.setTitle(2131493587);
        this.f2569b = new AboutUsFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2569b);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
