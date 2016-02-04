package com.mmdt.syna.view.introduction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/* renamed from: com.mmdt.syna.view.introduction.c */
public class IntroFragmentAdapter extends FragmentPagerAdapter {
    public static final int[] f2508a;
    private onIntroTransactionListener f2509b;
    private int f2510c;

    static {
        f2508a = new int[]{2130838156, 2130838155, 2130838154, 2130838153, 2130838152, 2130838151, 2130838150};
    }

    public IntroFragmentAdapter(FragmentManager fragmentManager, onIntroTransactionListener com_mmdt_syna_view_introduction_d) {
        super(fragmentManager);
        this.f2510c = f2508a.length;
        this.f2509b = com_mmdt_syna_view_introduction_d;
    }

    public Fragment m3472a(int i) {
        return new IntroFragment(f2508a[i % f2508a.length], i, this.f2509b);
    }

    public int m3473b() {
        return this.f2510c;
    }

    public CharSequence m3474c(int i) {
        return "";
    }
}
