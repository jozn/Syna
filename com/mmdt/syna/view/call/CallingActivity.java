package com.mmdt.syna.view.call;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.mmdt.syna.view.call.CallingFragment.CallingFragment;
import com.mmdt.syna.view.tools.SIPUri;

public class CallingActivity extends FragmentActivity implements CallingFragment {
    private Activity f1748n;
    private CallingFragment f1749o;
    private View f1750p;
    private ActionBar f1751q;
    private TextView f1752r;
    private View f1753s;
    private int f1754t;

    public CallingActivity() {
        this.f1754t = 0;
    }

    public void m2622b(String str) {
        runOnUiThread(new CallingActivity(this, str));
    }

    public void m2623b(boolean z) {
        if (z) {
            this.f1753s.setVisibility(8);
        } else {
            this.f1753s.setVisibility(0);
        }
    }

    public void m2624f() {
        finish();
    }

    public void onAcceptCall(View view) {
        this.f1749o.onAcceptCall(view);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(1);
        this.f1748n = this;
        this.f1751q = getActionBar();
        setContentView(2130903087);
        Window window = getWindow();
        window.addFlags(524288);
        window.addFlags(128);
        window.addFlags(2097152);
        this.f1750p = ((LayoutInflater) getActionBar().getThemedContext().getSystemService("layout_inflater")).inflate(2130903089, null);
        this.f1749o = new CallingFragment(this.f1748n, getIntent().getStringExtra("com.mmdt.sipclient.view.call.CallingActivity.NUMBER_FROM_CALLER"), getIntent().getBooleanExtra("com.mmdt.sipclient.view.call.CallingActivity.IS_SUN_FROM_CALLER", false));
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f1749o);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
        this.f1753s = this.f1750p.findViewById(2131427542);
        this.f1752r = (TextView) this.f1750p.findViewById(2131427541);
        this.f1752r.setText("");
        this.f1750p.startAnimation(AnimationUtils.loadAnimation(this, 2130968588));
        this.f1750p.setVisibility(0);
        this.f1751q.setDisplayOptions(16, 26);
        this.f1751q.setCustomView(this.f1750p, new LayoutParams(-1, -1));
    }

    protected void onDestroy() {
        super.onDestroy();
        if (SIPUri.f3104a != null) {
            SIPUri.f3104a.m3933a();
        }
    }

    public void onDoNothing(View view) {
    }

    public void onEndCall(View view) {
        this.f1749o.onEndCall(view);
        int i = this.f1754t + 1;
        this.f1754t = i;
        if (i >= 2) {
            finish();
        }
    }

    protected void onPause() {
        super.onPause();
    }
}
