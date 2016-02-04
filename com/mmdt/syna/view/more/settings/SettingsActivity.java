package com.mmdt.syna.view.more.settings;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.mmdt.syna.view.introduction.IntroductionActivity;
import com.mmdt.syna.view.more.language.LanguageActivity;
import com.mmdt.syna.view.more.settings.SettingsListFragment.SettingsListFragment;
import com.mmdt.syna.view.more.settings.nightmode.NightModeActivity;
import com.mmdt.syna.view.more.settings.profile.MyProfileActivity;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class SettingsActivity extends Activity implements SettingsListFragment {
    public SettingsListItemDataHolder[] f2669a;
    private SettingsListFragment f2670b;
    private boolean f2671c;
    private ActionBar f2672d;

    public SettingsActivity() {
        this.f2671c = false;
    }

    private void m3588a() {
        startActivity(new Intent(getApplicationContext(), LanguageActivity.class));
        overridePendingTransition(0, 0);
    }

    private void m3589b() {
        startActivity(new Intent(getApplicationContext(), NightModeActivity.class));
        overridePendingTransition(0, 0);
    }

    private void m3590c() {
        Intent intent = new Intent(this, MyProfileActivity.class);
        overridePendingTransition(0, 0);
        startActivityForResult(intent, 15648);
        overridePendingTransition(0, 0);
    }

    private void m3591d() {
        startActivity(new Intent(getApplicationContext(), IntroductionActivity.class));
        overridePendingTransition(0, 0);
    }

    private void m3592e() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType("vnd.android-dir/mms-sms");
        intent.putExtra("sms_body", getString(2131493595));
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void m3593a(int i) {
        switch (i) {
            case VideoSize.QCIF /*0*/:
                m3590c();
            case VideoSize.CIF /*1*/:
                m3588a();
            case VideoSize.HVGA /*2*/:
                m3589b();
            case Version.API03_CUPCAKE_15 /*3*/:
                m3591d();
            case Version.API04_DONUT_16 /*4*/:
                m3592e();
            default:
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 15648) {
            setResult(15648);
            overridePendingTransition(0, 0);
            finish();
        }
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(0, 0);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903190);
        this.f2672d = getActionBar();
        this.f2672d.setDisplayHomeAsUpEnabled(true);
        this.f2672d.setIcon(2130837866);
        this.f2672d.setTitle(2131493584);
        this.f2669a = new SettingsListItemDataHolder[]{new SettingsListItemDataHolder(getString(2131493606), 2130837999, 0), new SettingsListItemDataHolder(getString(2131493609), 2130837974, 1), new SettingsListItemDataHolder(getString(2131493715), 2130837996, 2), new SettingsListItemDataHolder(getString(2131493607), 2130837862, 3), new SettingsListItemDataHolder(getString(2131493608), 2130837957, 4)};
        this.f2670b = new SettingsListFragment(this, this.f2669a);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2670b);
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

    protected void onResume() {
        super.onResume();
    }
}
