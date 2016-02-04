package com.mmdt.syna.view.forceaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ForceSettingActivity extends Activity {
    TextView f2404a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903072);
        this.f2404a = (TextView) findViewById(2131427492);
        this.f2404a.setText(getString(2131493729));
    }

    public void onForceSetting(View view) {
        startActivity(new Intent("android.settings.DATE_SETTINGS"));
        finish();
    }
}
