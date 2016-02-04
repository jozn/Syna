package com.mmdt.syna.view.more.language;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import com.mmdt.syna.view.more.language.LanguageFragment.LanguageFragment;
import java.util.Locale;
import mobi.mmdt.ott.core.model.p058a.AppSettings;

public class LanguageActivity extends Activity implements LanguageFragment {
    boolean f2620a;
    private ActionBar f2621b;
    private LanguageFragment f2622c;

    public LanguageActivity() {
        this.f2620a = false;
    }

    private void m3549a(String str) {
        Locale locale = new Locale(str);
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, displayMetrics);
        AppSettings.m4867a(getApplicationContext()).m4873a(str);
    }

    public void m3550a(int i) {
        if (!this.f2620a) {
            this.f2620a = true;
        } else if (i == 0) {
            runOnUiThread(new LanguageActivity(this));
        } else if (i == 1) {
            runOnUiThread(new LanguageActivity(this));
        }
    }

    public void onBackPressed() {
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903156);
        this.f2621b = getActionBar();
        this.f2621b.setDisplayHomeAsUpEnabled(true);
        this.f2621b.setIcon(2130837972);
        this.f2621b.setTitle(2131493609);
        this.f2622c = new LanguageFragment(AppSettings.m4867a(getApplicationContext()).m4889f());
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2622c);
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
