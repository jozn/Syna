package com.mmdt.syna.view.more.settings.themselect;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

public class ThemsSelect extends Activity {
    private ActionBar f2795a;
    private ThemsSelectFragment f2796b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903082);
        this.f2795a = getActionBar();
        this.f2795a.setDisplayHomeAsUpEnabled(true);
        this.f2795a.setIcon(2130837994);
        this.f2795a.setTitle(2131493482);
        this.f2796b = new ThemsSelectFragment();
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2796b);
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
