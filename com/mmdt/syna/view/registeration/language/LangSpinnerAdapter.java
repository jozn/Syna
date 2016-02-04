package com.mmdt.syna.view.registeration.language;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/* renamed from: com.mmdt.syna.view.registeration.language.a */
public class LangSpinnerAdapter extends ArrayAdapter<String> {
    private String[] f2838a;
    private Activity f2839b;

    public LangSpinnerAdapter(Activity activity, int i, String[] strArr) {
        super(activity, i, strArr);
        this.f2838a = strArr;
        this.f2839b = activity;
    }

    public View m3712a(int i, View view, ViewGroup viewGroup) {
        View inflate = this.f2839b.getLayoutInflater().inflate(2130903155, viewGroup, false);
        ((TextView) inflate.findViewById(2131427739)).setText(this.f2838a[i]);
        return inflate;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return m3712a(i, view, viewGroup);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return m3712a(i, view, viewGroup);
    }
}
