package com.mmdt.syna.view.more.rate;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/* renamed from: com.mmdt.syna.view.more.rate.p */
public class RatesListAdapter extends ArrayAdapter<RateListItemDataHolder> {
    private final List<RateListItemDataHolder> f2667a;
    private LayoutInflater f2668b;

    /* renamed from: com.mmdt.syna.view.more.rate.p.a */
    static class RatesListAdapter {
        protected TextView f2664a;
        protected TextView f2665b;
        protected TextView f2666c;

        RatesListAdapter() {
        }
    }

    public RatesListAdapter(Activity activity, List<RateListItemDataHolder> list) {
        super(activity, 2130903179, list);
        this.f2667a = list;
        this.f2668b = activity.getLayoutInflater();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = this.f2668b.inflate(2130903179, null);
        RatesListAdapter ratesListAdapter = new RatesListAdapter();
        ratesListAdapter.f2664a = (TextView) inflate.findViewById(2131427791);
        ratesListAdapter.f2665b = (TextView) inflate.findViewById(2131427792);
        ratesListAdapter.f2666c = (TextView) inflate.findViewById(2131427793);
        ratesListAdapter.f2664a.setText(((RateListItemDataHolder) this.f2667a.get(i)).m3585a());
        ratesListAdapter.f2665b.setText(((RateListItemDataHolder) this.f2667a.get(i)).m3586b());
        ratesListAdapter.f2666c.setText(((RateListItemDataHolder) this.f2667a.get(i)).m3587c());
        return inflate;
    }
}
