package com.mmdt.syna.view.more.settings.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* compiled from: MySpinnerAdapter */
public class ac extends ArrayAdapter<String> {
    private Context f2716a;
    private String[] f2717b;

    public ac(Context context, String[] strArr) {
        super(context, 2130903145, strArr);
        this.f2717b = strArr;
        this.f2716a = context;
    }

    public View m3627a(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.f2716a.getSystemService("layout_inflater")).inflate(2130903145, viewGroup, false);
        ((TextView) inflate.findViewById(2131427467)).setText(this.f2717b[i]);
        return inflate;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return m3627a(i, view, viewGroup);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return m3627a(i, view, viewGroup);
    }
}
