package com.felipecsl.asymmetricgridview.library.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.felipecsl.asymmetricgridview.library.AsymmetricGridViewAdapterContract;
import com.felipecsl.asymmetricgridview.library.AsyncTaskCompat;
import com.felipecsl.asymmetricgridview.library.R.R;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.felipecsl.asymmetricgridview.library.widget.e */
public abstract class AsymmetricGridViewAdapter<T extends AsymmetricItem> extends BaseAdapter implements OnClickListener, OnLongClickListener, AsymmetricGridViewAdapterContract {
    protected final AsymmetricGridView f1566a;
    protected final Context f1567b;
    protected final List<T> f1568c;
    private Map<Integer, RowInfo<T>> f1569d;
    private final ViewPool<IcsLinearLayout> f1570e;
    private final ViewPool<View> f1571f;
    private AsymmetricGridViewAdapter f1572g;

    /* renamed from: com.felipecsl.asymmetricgridview.library.widget.e.a */
    class AsymmetricGridViewAdapter extends AsyncTaskCompat<List<T>, Void, List<RowInfo<T>>> {
        final /* synthetic */ AsymmetricGridViewAdapter f1565c;

        AsymmetricGridViewAdapter(AsymmetricGridViewAdapter asymmetricGridViewAdapter) {
            this.f1565c = asymmetricGridViewAdapter;
        }

        private List<RowInfo<T>> m2348a(int i, List<T> list) {
            List arrayList = new ArrayList();
            while (!list.isEmpty()) {
                RowInfo a = this.f1565c.m2358b((List) list);
                List<AsymmetricItem> a2 = a.m2330a();
                if (a2.isEmpty()) {
                    break;
                }
                for (AsymmetricItem remove : a2) {
                    list.remove(remove);
                }
                arrayList.add(a);
                i++;
            }
            return arrayList;
        }

        @SafeVarargs
        protected final List<RowInfo<T>> m2350a(List<T>... listArr) {
            return m2348a(0, listArr[0]);
        }

        protected void m2352a(List<RowInfo<T>> list) {
            for (RowInfo put : list) {
                this.f1565c.f1569d.put(Integer.valueOf(this.f1565c.m2368c()), put);
            }
            if (this.f1565c.f1566a.m2324g()) {
                for (Entry entry : this.f1565c.f1569d.entrySet()) {
                    Log.d("AsymmetricGridViewAdapter", "row: " + entry.getKey() + ", items: " + ((RowInfo) entry.getValue()).m2330a().size());
                }
            }
            this.f1565c.notifyDataSetChanged();
        }
    }

    public AsymmetricGridViewAdapter(Context context, AsymmetricGridView asymmetricGridView, List<T> list) {
        this.f1569d = new HashMap();
        this.f1571f = new ViewPool();
        this.f1570e = new ViewPool(new LinearLayoutPoolObjectFactory(context));
        this.f1568c = list;
        this.f1567b = context;
        this.f1566a = asymmetricGridView;
    }

    private IcsLinearLayout m2353a(View view) {
        IcsLinearLayout icsLinearLayout;
        if (view == null || !(view instanceof IcsLinearLayout)) {
            icsLinearLayout = new IcsLinearLayout(this.f1567b, null);
            icsLinearLayout.setShowDividers(2);
            icsLinearLayout.setDividerDrawable(this.f1567b.getResources().getDrawable(R.item_divider_horizontal));
            icsLinearLayout.setLayoutParams(new LayoutParams(-1, -2));
        } else {
            icsLinearLayout = (IcsLinearLayout) view;
        }
        for (int i = 0; i < icsLinearLayout.getChildCount(); i++) {
            View view2 = (IcsLinearLayout) icsLinearLayout.getChildAt(i);
            this.f1570e.m2335a(view2);
            for (int i2 = 0; i2 < view2.getChildCount(); i2++) {
                this.f1571f.m2335a(view2.getChildAt(i2));
            }
            view2.removeAllViews();
        }
        icsLinearLayout.removeAllViews();
        return icsLinearLayout;
    }

    private IcsLinearLayout m2354a(LinearLayout linearLayout, int i) {
        IcsLinearLayout icsLinearLayout = (IcsLinearLayout) linearLayout.getChildAt(i);
        if (icsLinearLayout != null) {
            return icsLinearLayout;
        }
        icsLinearLayout = (IcsLinearLayout) this.f1570e.m2333a();
        icsLinearLayout.setOrientation(1);
        icsLinearLayout.setShowDividers(2);
        icsLinearLayout.setDividerDrawable(this.f1567b.getResources().getDrawable(R.item_divider_vertical));
        icsLinearLayout.setLayoutParams(new LayoutParams(-2, -1));
        linearLayout.addView(icsLinearLayout);
        return icsLinearLayout;
    }

    private RowInfo<T> m2356a(List<T> list, float f) {
        List arrayList = new ArrayList();
        float f2 = f;
        int i = 1;
        int i2 = 0;
        while (f2 > 0.0f && i2 < list.size()) {
            int i3 = i2 + 1;
            AsymmetricItem asymmetricItem = (AsymmetricItem) list.get(i2);
            float b = (float) (asymmetricItem.m2315b() * asymmetricItem.m2314a());
            if (this.f1566a.m2324g()) {
                Log.d("AsymmetricGridViewAdapter", String.format("item %s in row with height %s consumes %s area", new Object[]{asymmetricItem, Integer.valueOf(i), Float.valueOf(b)}));
            }
            if (i >= asymmetricItem.m2315b()) {
                if (f2 < b) {
                    if (!this.f1566a.m2323f()) {
                        break;
                    }
                    i2 = i3;
                } else {
                    f2 -= b;
                    arrayList.add(asymmetricItem);
                    i2 = i3;
                }
            } else {
                arrayList.clear();
                i = asymmetricItem.m2315b();
                f2 = ((float) asymmetricItem.m2315b()) * f;
                i2 = 0;
            }
        }
        return new RowInfo(i, arrayList, f2);
    }

    private RowInfo<T> m2358b(List<T> list) {
        return m2356a((List) list, (float) this.f1566a.m2320c());
    }

    protected int m2359a(AsymmetricItem asymmetricItem) {
        return m2365b(asymmetricItem.m2315b());
    }

    public abstract View m2360a(int i, View view, ViewGroup viewGroup);

    public T m2361a(int i) {
        return (AsymmetricItem) this.f1568c.get(i);
    }

    public void m2362a() {
        if (this.f1572g != null) {
            this.f1572g.m2305a(true);
        }
        this.f1570e.m2336b();
        this.f1571f.m2336b();
        this.f1569d.clear();
        new ArrayList().addAll(this.f1568c);
        this.f1572g = new AsymmetricGridViewAdapter(this);
        this.f1572g.m2309c((Object[]) new List[]{r0});
    }

    public void m2363a(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
            int i = bundle.getInt("totalItems");
            List arrayList = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                arrayList.add((AsymmetricItem) bundle.getParcelable("item_" + i2));
            }
            m2364a(arrayList);
        }
    }

    public void m2364a(List<T> list) {
        this.f1570e.m2336b();
        this.f1571f.m2336b();
        this.f1568c.clear();
        this.f1568c.addAll(list);
        m2362a();
        notifyDataSetChanged();
    }

    protected int m2365b(int i) {
        return (this.f1566a.m2321d() * i) + ((i - 1) * this.f1566a.getDividerHeight());
    }

    protected int m2366b(AsymmetricItem asymmetricItem) {
        return m2369c(asymmetricItem.m2314a());
    }

    public Parcelable m2367b() {
        Parcelable bundle = new Bundle();
        bundle.putInt("totalItems", this.f1568c.size());
        for (int i = 0; i < this.f1568c.size(); i++) {
            bundle.putParcelable("item_" + i, (Parcelable) this.f1568c.get(i));
        }
        return bundle;
    }

    public int m2368c() {
        return this.f1569d.size();
    }

    protected int m2369c(int i) {
        return Math.min((this.f1566a.m2321d() * i) + ((i - 1) * this.f1566a.m2316a()), Utils.m2311a(this.f1567b));
    }

    public int getCount() {
        return m2368c();
    }

    public /* synthetic */ Object getItem(int i) {
        return m2361a(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.f1566a.m2324g()) {
            Log.d("AsymmetricGridViewAdapter", "getView(" + String.valueOf(i) + ")");
        }
        LinearLayout a = m2353a(view);
        RowInfo rowInfo = (RowInfo) this.f1569d.get(Integer.valueOf(i));
        List arrayList = new ArrayList();
        arrayList.addAll(rowInfo.m2330a());
        int b = rowInfo.m2331b();
        int i2 = 0;
        int i3 = 0;
        while (!arrayList.isEmpty() && i3 < this.f1566a.m2320c()) {
            AsymmetricItem asymmetricItem = (AsymmetricItem) arrayList.get(i2);
            if (b != 0) {
                if (b < asymmetricItem.m2315b()) {
                    if (i2 >= arrayList.size() - 1) {
                        break;
                    }
                    i2++;
                } else {
                    arrayList.remove(asymmetricItem);
                    i2 = this.f1568c.indexOf(asymmetricItem);
                    LinearLayout a2 = m2354a(a, i3);
                    View a3 = m2360a(i2, this.f1571f.m2333a(), viewGroup);
                    a3.setTag(asymmetricItem);
                    a3.setOnClickListener(this);
                    a3.setOnLongClickListener(this);
                    b -= asymmetricItem.m2315b();
                    a3.setLayoutParams(new LinearLayout.LayoutParams(m2366b(asymmetricItem), m2359a(asymmetricItem)));
                    a2.addView(a3);
                    i2 = 0;
                }
            } else {
                i2 = 0;
                i3++;
                b = rowInfo.m2331b();
            }
        }
        if (this.f1566a.m2324g() && i % 20 == 0) {
            Log.d("AsymmetricGridViewAdapter", this.f1570e.m2334a("LinearLayout"));
            Log.d("AsymmetricGridViewAdapter", this.f1571f.m2334a("Views"));
        }
        return a;
    }

    public void onClick(View view) {
        this.f1566a.m2317a(this.f1568c.indexOf((AsymmetricItem) view.getTag()), view);
    }

    public boolean onLongClick(View view) {
        return this.f1566a.m2319b(this.f1568c.indexOf((AsymmetricItem) view.getTag()), view);
    }
}
