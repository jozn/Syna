package com.mmdt.syna.view.introduction;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.R.R;
import mobi.mmdt.ott.core.model.p058a.AppSettings;

public class IntroductionActivity extends FragmentActivity implements onIntroTransactionListener {
    private IntroFragmentAdapter f2500n;
    private ViewPager f2501o;
    private CirclePageIndicator f2502p;

    public void m3466f() {
        AppSettings.m4867a(getApplicationContext()).m4886d(true);
        finish();
    }

    public void onBackPressed() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(2130903154);
        this.f2500n = new IntroFragmentAdapter(m121e(), this);
        this.f2501o = (ViewPager) findViewById(2131427494);
        this.f2501o.setAdapter(this.f2500n);
        this.f2502p = (CirclePageIndicator) findViewById(2131427738);
        this.f2502p.setViewPager(this.f2501o);
        this.f2501o.setCurrentItem(IntroFragmentAdapter.f2508a.length - 1, false);
        float f = getResources().getDisplayMetrics().density;
        this.f2502p.setRadius(5.0f * f);
        this.f2502p.setPageColor(getApplicationContext().getResources().getColor(2131230794));
        this.f2502p.setFillColor(getApplicationContext().getResources().getColor(R.my_blue));
        this.f2502p.setStrokeColor(getApplicationContext().getResources().getColor(R.my_yellow));
        this.f2502p.setStrokeWidth(f * 1.0f);
    }
}
