package com.mmdt.syna.view.tools;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.mmdt.syna.view.components.p025a.CustomDialog;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.CountryTools;

public class SIPUri extends Activity {
    public static SIPUri f3104a;
    private Activity f3105b;

    public void m3933a() {
        if (f3104a != null) {
            f3104a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f3104a = this;
        this.f3105b = this;
        requestWindowFeature(1);
        if (AppSettings.m4867a(getApplicationContext()).m4911u()) {
            CustomDialog.m2737a(this.f3105b, this.f3105b.getString(2131493530)).show();
            return;
        }
        Uri data = getIntent().getData();
        String str;
        if (data.getAuthority() != null) {
            String str2 = "";
            Cursor query = getContentResolver().query(data, null, null, null, null);
            if (query != null) {
                str = str2;
                while (query.moveToNext()) {
                    str = query.getString(query.getColumnIndexOrThrow("data3"));
                }
            } else {
                str = str2;
            }
            query.close();
            CallAndMessageHandler.m4021b(this.f3105b, str, true);
            return;
        }
        data.getScheme();
        str = data.getSchemeSpecificPart();
        if (str != null) {
            CallAndMessageHandler.m4021b(this.f3105b, CountryTools.m4416a().m4422b(this.f3105b, str.replaceAll("\\s+", "")), true);
        }
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
