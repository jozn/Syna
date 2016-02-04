package com.mmdt.syna.view.components.p028d;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

/* renamed from: com.mmdt.syna.view.components.d.a */
public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private SparseArray<Fragment> f1834a;

    public SmartFragmentStatePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.f1834a = new SparseArray();
    }

    public Object m2756a(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.m352a(viewGroup, i);
        this.f1834a.put(i, fragment);
        return fragment;
    }

    public void m2757a(ViewGroup viewGroup, int i, Object obj) {
        this.f1834a.remove(i);
        super.m355a(viewGroup, i, obj);
    }
}
