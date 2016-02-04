package com.mmdt.syna.view.more;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.mmdt.syna.view.components.p025a.CustomDialog;
import com.mmdt.syna.view.feedback.FeedbackActivity;
import com.mmdt.syna.view.more.aboutus.AboutUsActivity;
import com.mmdt.syna.view.more.credit.CreditsActivity;
import com.mmdt.syna.view.more.rate.RateActivity;
import com.mmdt.syna.view.more.settings.SettingsActivity;
import com.mmdt.syna.view.more.settings.SettingsListFragment.SettingsListFragment;
import com.mmdt.syna.view.more.settings.SettingsListItemDataHolder;
import com.mmdt.syna.view.stickermarket.StickerMarketActivity;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class MoreActivity extends Activity implements SettingsListFragment {
    public SettingsListItemDataHolder[] f2563a;
    private ActionBar f2564b;
    private com.mmdt.syna.view.more.settings.SettingsListFragment f2565c;

    private void m3516b() {
        startActivity(new Intent(getApplicationContext(), RateActivity.class));
        overridePendingTransition(0, 0);
    }

    private void m3517c() {
        startActivity(new Intent(getApplicationContext(), StickerMarketActivity.class));
        overridePendingTransition(0, 0);
    }

    private void m3518d() {
        Intent intent = new Intent(this, SettingsActivity.class);
        overridePendingTransition(0, 0);
        startActivityForResult(intent, 15648);
        overridePendingTransition(0, 0);
    }

    private void m3519e() {
        startActivity(new Intent(getApplicationContext(), CreditsActivity.class));
        overridePendingTransition(0, 0);
    }

    private void m3520f() {
        Dialog b = CustomDialog.m2739b(this, getString(2131493506));
        b.findViewById(2131427718).setOnClickListener(new MoreActivity(this, b));
        b.findViewById(2131427717).setOnClickListener(new MoreActivity(this, b));
        b.show();
    }

    private void m3521g() {
        startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
        overridePendingTransition(0, 0);
    }

    public void m3522a() {
        startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
        overridePendingTransition(2130968584, 2130968585);
    }

    public void m3523a(int i) {
        switch (i) {
            case VideoSize.QCIF /*0*/:
                m3518d();
            case VideoSize.CIF /*1*/:
                m3517c();
            case VideoSize.HVGA /*2*/:
                m3516b();
            case Version.API03_CUPCAKE_15 /*3*/:
                m3519e();
            case Version.API04_DONUT_16 /*4*/:
                m3521g();
            case Version.API05_ECLAIR_20 /*5*/:
                m3522a();
            case Version.API06_ECLAIR_201 /*6*/:
                m3520f();
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
        setContentView(2130903164);
        this.f2564b = getActionBar();
        this.f2564b.setDisplayHomeAsUpEnabled(true);
        this.f2564b.setIcon(2130837982);
        this.f2564b.setTitle(2131493617);
        this.f2563a = new SettingsListItemDataHolder[]{new SettingsListItemDataHolder(getString(2131493584), 2130837868, 0), new SettingsListItemDataHolder(getString(2131493589), 2130837961, 1), new SettingsListItemDataHolder(getString(2131493585), 2130837979, 2), new SettingsListItemDataHolder(getString(2131493586), 2130837912, 3), new SettingsListItemDataHolder(getString(2131493587), 2130837862, 4), new SettingsListItemDataHolder(getString(2131493481), 2130837952, 6)};
        this.f2565c = new com.mmdt.syna.view.more.settings.SettingsListFragment(this, this.f2563a);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2565c);
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
