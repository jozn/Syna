package com.mmdt.syna.view.registeration.registernumber;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import mobi.mmdt.ott.core.p051a.CountryTools;

/* renamed from: com.mmdt.syna.view.registeration.registernumber.a */
public class CountrySpinnerAdapter extends ArrayAdapter<String> {
    private List<String> f2856a;
    private Activity f2857b;

    public CountrySpinnerAdapter(Activity activity, int i, List<String> list) {
        super(activity, i, list);
        this.f2856a = list;
        this.f2857b = activity;
    }

    public View m3727a(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f2857b.getLayoutInflater().inflate(2130903195, viewGroup, false);
        }
        ((TextView) view.findViewById(2131427739)).setText((CharSequence) this.f2856a.get(i));
        ((ImageView) view.findViewById(2131427810)).setImageResource(CountryTools.m4416a().m4429e(CountryTools.m4416a().m4428d((String) this.f2856a.get(i))));
        return view;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return m3727a(i, view, viewGroup);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return m3727a(i, view, viewGroup);
    }
}
