package com.mmdt.syna.view.p023c;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import mobi.mmdt.ott.core.model.database.MatchedContactData;

/* renamed from: com.mmdt.syna.view.c.a */
public class BaseAdapterForMathedNumbers extends BaseAdapter {
    Context f1699a;
    private ArrayList<MatchedContactData> f1700b;
    private LayoutInflater f1701c;

    /* renamed from: com.mmdt.syna.view.c.a.a */
    static class BaseAdapterForMathedNumbers {
        TextView f1695a;
        TextView f1696b;
        ImageView f1697c;
        ImageView f1698d;

        BaseAdapterForMathedNumbers() {
        }
    }

    public BaseAdapterForMathedNumbers(Context context, ArrayList<MatchedContactData> arrayList) {
        this.f1699a = context;
        this.f1700b = arrayList;
        this.f1701c = (LayoutInflater) context.getSystemService("layout_inflater");
        notifyDataSetChanged();
    }

    public MatchedContactData m2574a(int i) {
        return (MatchedContactData) this.f1700b.get(i);
    }

    public int getCount() {
        return this.f1700b.size();
    }

    public /* synthetic */ Object getItem(int i) {
        return m2574a(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseAdapterForMathedNumbers baseAdapterForMathedNumbers;
        if (view == null) {
            view = this.f1701c.inflate(2130903163, null);
            baseAdapterForMathedNumbers = new BaseAdapterForMathedNumbers();
            baseAdapterForMathedNumbers.f1695a = (TextView) view.findViewById(2131427638);
            baseAdapterForMathedNumbers.f1696b = (TextView) view.findViewById(2131427639);
            baseAdapterForMathedNumbers.f1697c = (ImageView) view.findViewById(2131427611);
            baseAdapterForMathedNumbers.f1698d = (ImageView) view.findViewById(2131427570);
            view.setTag(baseAdapterForMathedNumbers);
        } else {
            baseAdapterForMathedNumbers = (BaseAdapterForMathedNumbers) view.getTag();
        }
        if (((MatchedContactData) this.f1700b.get(i)).m5128c() != null) {
            baseAdapterForMathedNumbers.f1695a.setText(((MatchedContactData) this.f1700b.get(i)).m5128c());
        } else {
            baseAdapterForMathedNumbers.f1695a.setText("Unknown");
        }
        if (((MatchedContactData) this.f1700b.get(i)).m5130e() != null) {
            baseAdapterForMathedNumbers.f1696b.setText(Html.fromHtml(((MatchedContactData) this.f1700b.get(i)).m5130e()));
        }
        if (((MatchedContactData) this.f1700b.get(i)).m5127b() != null) {
            try {
                baseAdapterForMathedNumbers.f1697c.setImageURI(((MatchedContactData) this.f1700b.get(i)).m5127b());
            } catch (Exception e) {
                baseAdapterForMathedNumbers.f1697c.setImageResource(2130838061);
            }
        } else {
            baseAdapterForMathedNumbers.f1697c.setImageResource(2130838061);
        }
        if (((MatchedContactData) this.f1700b.get(i)).m5126a()) {
            baseAdapterForMathedNumbers.f1698d.setVisibility(0);
        } else {
            baseAdapterForMathedNumbers.f1698d.setVisibility(4);
        }
        return view;
    }
}
