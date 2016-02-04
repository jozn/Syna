package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar.C0037c;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.widget.AdapterViewICS.AdapterViewICS;
import android.support.v7.p010a.R.R;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterViewICS {
    Runnable f1244a;
    int f1245b;
    int f1246c;
    private C0062b f1247d;
    private LinearLayout f1248e;
    private SpinnerICS f1249f;
    private boolean f1250g;
    private final LayoutInflater f1251h;
    private int f1252i;
    private int f1253j;

    public static class TabView extends LinearLayout {
        private C0037c f1237a;
        private TextView f1238b;
        private ImageView f1239c;
        private View f1240d;
        private ScrollingTabContainerView f1241e;

        public TabView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public void m2025a() {
            C0037c c0037c = this.f1237a;
            View c = c0037c.m1453c();
            if (c != null) {
                TabView parent = c.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(c);
                    }
                    addView(c);
                }
                this.f1240d = c;
                if (this.f1238b != null) {
                    this.f1238b.setVisibility(8);
                }
                if (this.f1239c != null) {
                    this.f1239c.setVisibility(8);
                    this.f1239c.setImageDrawable(null);
                    return;
                }
                return;
            }
            if (this.f1240d != null) {
                removeView(this.f1240d);
                this.f1240d = null;
            }
            Drawable a = c0037c.m1451a();
            CharSequence b = c0037c.m1452b();
            if (a != null) {
                if (this.f1239c == null) {
                    View imageView = new ImageView(getContext());
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 16;
                    imageView.setLayoutParams(layoutParams);
                    addView(imageView, 0);
                    this.f1239c = imageView;
                }
                this.f1239c.setImageDrawable(a);
                this.f1239c.setVisibility(0);
            } else if (this.f1239c != null) {
                this.f1239c.setVisibility(8);
                this.f1239c.setImageDrawable(null);
            }
            if (b != null) {
                if (this.f1238b == null) {
                    c = new CompatTextView(getContext(), null, R.actionBarTabTextStyle);
                    c.setEllipsize(TruncateAt.END);
                    LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams2.gravity = 16;
                    c.setLayoutParams(layoutParams2);
                    addView(c);
                    this.f1238b = c;
                }
                this.f1238b.setText(b);
                this.f1238b.setVisibility(0);
            } else if (this.f1238b != null) {
                this.f1238b.setVisibility(8);
                this.f1238b.setText(null);
            }
            if (this.f1239c != null) {
                this.f1239c.setContentDescription(c0037c.m1455e());
            }
        }

        public void m2026a(C0037c c0037c) {
            this.f1237a = c0037c;
            m2025a();
        }

        void m2027a(ScrollingTabContainerView scrollingTabContainerView, C0037c c0037c, boolean z) {
            this.f1241e = scrollingTabContainerView;
            this.f1237a = c0037c;
            if (z) {
                setGravity(19);
            }
            m2025a();
        }

        public C0037c m2028b() {
            return this.f1237a;
        }

        public void onMeasure(int i, int i2) {
            super.onMeasure(i, i2);
            int i3 = this.f1241e != null ? this.f1241e.f1245b : 0;
            if (i3 > 0 && getMeasuredWidth() > i3) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
            }
        }
    }

    /* renamed from: android.support.v7.internal.widget.ScrollingTabContainerView.a */
    private class C0061a extends BaseAdapter {
        final /* synthetic */ ScrollingTabContainerView f1242a;

        private C0061a(ScrollingTabContainerView scrollingTabContainerView) {
            this.f1242a = scrollingTabContainerView;
        }

        public int getCount() {
            return this.f1242a.f1248e.getChildCount();
        }

        public Object getItem(int i) {
            return ((TabView) this.f1242a.f1248e.getChildAt(i)).m2028b();
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                return this.f1242a.m2030a((C0037c) getItem(i), true);
            }
            ((TabView) view).m2026a((C0037c) getItem(i));
            return view;
        }
    }

    /* renamed from: android.support.v7.internal.widget.ScrollingTabContainerView.b */
    private class C0062b implements OnClickListener {
        final /* synthetic */ ScrollingTabContainerView f1243a;

        private C0062b(ScrollingTabContainerView scrollingTabContainerView) {
            this.f1243a = scrollingTabContainerView;
        }

        public void onClick(View view) {
            ((TabView) view).m2028b().m1454d();
            int childCount = this.f1243a.f1248e.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f1243a.f1248e.getChildAt(i);
                childAt.setSelected(childAt == view);
            }
        }
    }

    public ScrollingTabContainerView(Context context) {
        super(context);
        this.f1251h = LayoutInflater.from(context);
        setHorizontalScrollBarEnabled(false);
        ActionBarPolicy a = ActionBarPolicy.m1589a(context);
        setContentHeight(a.m1594e());
        this.f1246c = a.m1596g();
        this.f1248e = (LinearLayout) this.f1251h.inflate(R.abc_action_bar_tabbar, this, false);
        addView(this.f1248e, new LayoutParams(-2, -1));
    }

    private TabView m2030a(C0037c c0037c, boolean z) {
        TabView tabView = (TabView) this.f1251h.inflate(R.abc_action_bar_tab, this.f1248e, false);
        tabView.m2027a(this, c0037c, z);
        if (z) {
            tabView.setBackgroundDrawable(null);
            tabView.setLayoutParams(new AbsListView.LayoutParams(-1, this.f1252i));
        } else {
            tabView.setFocusable(true);
            if (this.f1247d == null) {
                this.f1247d = new C0062b();
            }
            tabView.setOnClickListener(this.f1247d);
        }
        return tabView;
    }

    private boolean m2033a() {
        return this.f1249f != null && this.f1249f.getParent() == this;
    }

    private void m2034b() {
        if (!m2033a()) {
            if (this.f1249f == null) {
                this.f1249f = m2036d();
            }
            removeView(this.f1248e);
            addView(this.f1249f, new LayoutParams(-2, -1));
            if (this.f1249f.m1888d() == null) {
                this.f1249f.m2093a(new C0061a());
            }
            if (this.f1244a != null) {
                removeCallbacks(this.f1244a);
                this.f1244a = null;
            }
            this.f1249f.m1883a(this.f1253j);
        }
    }

    private boolean m2035c() {
        if (m2033a()) {
            removeView(this.f1249f);
            addView(this.f1248e, new LayoutParams(-2, -1));
            setTabSelected(this.f1249f.m1870f());
        }
        return false;
    }

    private SpinnerICS m2036d() {
        SpinnerICS spinnerICS = new SpinnerICS(getContext(), null, R.actionDropDownStyle);
        spinnerICS.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        spinnerICS.m2095b(this);
        return spinnerICS;
    }

    public void m2037a(int i) {
        View childAt = this.f1248e.getChildAt(i);
        if (this.f1244a != null) {
            removeCallbacks(this.f1244a);
        }
        this.f1244a = new ScrollingTabContainerView(this, childAt);
        post(this.f1244a);
    }

    public void m2038a(AdapterViewICS<?> adapterViewICS, View view, int i, long j) {
        ((TabView) view).m2028b().m1454d();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f1244a != null) {
            post(this.f1244a);
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        ActionBarPolicy a = ActionBarPolicy.m1589a(getContext());
        setContentHeight(a.m1594e());
        this.f1246c = a.m1596g();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f1244a != null) {
            removeCallbacks(this.f1244a);
        }
    }

    public void onMeasure(int i, int i2) {
        int i3 = 1;
        int mode = MeasureSpec.getMode(i);
        boolean z = mode == 1073741824;
        setFillViewport(z);
        int childCount = this.f1248e.getChildCount();
        if (childCount <= 1 || !(mode == 1073741824 || mode == Integer.MIN_VALUE)) {
            this.f1245b = -1;
        } else {
            if (childCount > 2) {
                this.f1245b = (int) (((float) MeasureSpec.getSize(i)) * 0.4f);
            } else {
                this.f1245b = MeasureSpec.getSize(i) / 2;
            }
            this.f1245b = Math.min(this.f1245b, this.f1246c);
        }
        mode = MeasureSpec.makeMeasureSpec(this.f1252i, 1073741824);
        if (z || !this.f1250g) {
            i3 = 0;
        }
        if (i3 != 0) {
            this.f1248e.measure(0, mode);
            if (this.f1248e.getMeasuredWidth() > MeasureSpec.getSize(i)) {
                m2034b();
            } else {
                m2035c();
            }
        } else {
            m2035c();
        }
        i3 = getMeasuredWidth();
        super.onMeasure(i, mode);
        int measuredWidth = getMeasuredWidth();
        if (z && i3 != measuredWidth) {
            setTabSelected(this.f1253j);
        }
    }

    public void setAllowCollapse(boolean z) {
        this.f1250g = z;
    }

    public void setContentHeight(int i) {
        this.f1252i = i;
        requestLayout();
    }

    public void setTabSelected(int i) {
        this.f1253j = i;
        int childCount = this.f1248e.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.f1248e.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                m2037a(i);
            }
            i2++;
        }
        if (this.f1249f != null && i >= 0) {
            this.f1249f.m1883a(i);
        }
    }
}
