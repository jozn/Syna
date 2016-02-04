package com.mmdt.syna.view.more.settings.nightmode;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class NightModeActivity extends Activity {
    private ActionBar f2686a;
    private NightModeFragment f2687b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903078);
        this.f2686a = getActionBar();
        this.f2686a.setDisplayHomeAsUpEnabled(true);
        this.f2686a.setIcon(2130837994);
        this.f2686a.setTitle(2131493715);
        this.f2687b = new NightModeFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2687b);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
    }

    public void onFromTime(View view) {
    }

    public void onNightModeSelect(View view) {
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

    public void onToTime(View view) {
    }
}
