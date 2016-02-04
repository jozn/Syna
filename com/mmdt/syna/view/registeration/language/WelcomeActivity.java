package com.mmdt.syna.view.registeration.language;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import com.mmdt.syna.view.registeration.language.WelcomeFragment.WelcomeFragment;
import java.util.Locale;
import mobi.mmdt.ott.core.model.p058a.AppSettings;

public class WelcomeActivity extends Activity implements WelcomeFragment {
    boolean f2835a;
    private ActionBar f2836b;
    private WelcomeFragment f2837c;

    public WelcomeActivity() {
        this.f2835a = false;
    }

    private void m3710a(String str) {
        Locale locale = new Locale(str);
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
        AppSettings.m4867a(getApplicationContext()).m4873a(str);
    }

    public void m3711a(int i) {
        if (!this.f2835a) {
            this.f2835a = true;
        } else if (i == 0) {
            runOnUiThread(new WelcomeActivity(this));
        } else if (i == 1) {
            runOnUiThread(new WelcomeActivity(this));
        }
    }

    public void onBackPressed() {
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903219);
        this.f2836b = getActionBar();
        this.f2836b.setDisplayHomeAsUpEnabled(true);
        this.f2836b.setIcon(2130837860);
        this.f2836b.setTitle(2131493609);
        m3710a("fa");
        this.f2837c = new WelcomeFragment(AppSettings.m4867a(getApplicationContext()).m4889f());
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2837c);
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

    public void onWelcomeNextBtnClick(View view) {
    }
}
