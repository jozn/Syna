package android.support.v7.internal.widget;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.widget.AdapterViewICS.AdapterViewICS;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: android.support.v7.internal.widget.q */
class SpinnerICS extends AbsSpinnerICS implements OnClickListener {
    int f1302E;
    private SpinnerICS f1303F;
    private SpinnerICS f1304G;
    private int f1305H;
    private Rect f1306I;

    /* renamed from: android.support.v7.internal.widget.q.d */
    private interface SpinnerICS {
        void m2074a(ListAdapter listAdapter);

        void m2075a(CharSequence charSequence);

        void m2076c();

        void m2077d();

        boolean m2078f();
    }

    /* renamed from: android.support.v7.internal.widget.q.a */
    private class SpinnerICS implements OnClickListener, SpinnerICS {
        final /* synthetic */ SpinnerICS f1293a;
        private AlertDialog f1294b;
        private ListAdapter f1295c;
        private CharSequence f1296d;

        private SpinnerICS(SpinnerICS spinnerICS) {
            this.f1293a = spinnerICS;
        }

        public void m2079a(ListAdapter listAdapter) {
            this.f1295c = listAdapter;
        }

        public void m2080a(CharSequence charSequence) {
            this.f1296d = charSequence;
        }

        public void m2081c() {
            Builder builder = new Builder(this.f1293a.getContext());
            if (this.f1296d != null) {
                builder.setTitle(this.f1296d);
            }
            this.f1294b = builder.setSingleChoiceItems(this.f1295c, this.f1293a.m1870f(), this).show();
        }

        public void m2082d() {
            this.f1294b.dismiss();
            this.f1294b = null;
        }

        public boolean m2083f() {
            return this.f1294b != null ? this.f1294b.isShowing() : false;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f1293a.m1883a(i);
            if (this.f1293a.t != null) {
                this.f1293a.m1864a(null, i, this.f1295c.getItemId(i));
            }
            m2082d();
        }
    }

    /* renamed from: android.support.v7.internal.widget.q.b */
    private static class SpinnerICS implements ListAdapter, SpinnerAdapter {
        private SpinnerAdapter f1297a;
        private ListAdapter f1298b;

        public SpinnerICS(SpinnerAdapter spinnerAdapter) {
            this.f1297a = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f1298b = (ListAdapter) spinnerAdapter;
            }
        }

        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f1298b;
            return listAdapter != null ? listAdapter.areAllItemsEnabled() : true;
        }

        public int getCount() {
            return this.f1297a == null ? 0 : this.f1297a.getCount();
        }

        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            return this.f1297a == null ? null : this.f1297a.getDropDownView(i, view, viewGroup);
        }

        public Object getItem(int i) {
            return this.f1297a == null ? null : this.f1297a.getItem(i);
        }

        public long getItemId(int i) {
            return this.f1297a == null ? -1 : this.f1297a.getItemId(i);
        }

        public int getItemViewType(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean hasStableIds() {
            return this.f1297a != null && this.f1297a.hasStableIds();
        }

        public boolean isEmpty() {
            return getCount() == 0;
        }

        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f1298b;
            return listAdapter != null ? listAdapter.isEnabled(i) : true;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.f1297a != null) {
                this.f1297a.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            if (this.f1297a != null) {
                this.f1297a.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* renamed from: android.support.v7.internal.widget.q.c */
    private class SpinnerICS extends ListPopupWindow implements SpinnerICS {
        final /* synthetic */ SpinnerICS f1299b;
        private CharSequence f1300c;
        private ListAdapter f1301d;

        public SpinnerICS(SpinnerICS spinnerICS, Context context, AttributeSet attributeSet, int i) {
            this.f1299b = spinnerICS;
            super(context, attributeSet, i);
            m1993a((View) spinnerICS);
            m1997a(true);
            m1991a(0);
            m1994a(new AdapterViewICS(spinnerICS, new SpinnerICS(this, spinnerICS)));
        }

        public void m2085a(ListAdapter listAdapter) {
            super.m1995a(listAdapter);
            this.f1301d = listAdapter;
        }

        public void m2086a(CharSequence charSequence) {
            this.f1300c = charSequence;
        }

        public void m2087c() {
            int paddingLeft = this.f1299b.getPaddingLeft();
            if (this.f1299b.f1302E == -2) {
                int width = this.f1299b.getWidth();
                m2005e(Math.max(this.f1299b.m2091a((SpinnerAdapter) this.f1301d, m1990a()), (width - paddingLeft) - this.f1299b.getPaddingRight()));
            } else if (this.f1299b.f1302E == -1) {
                m2005e((this.f1299b.getWidth() - paddingLeft) - this.f1299b.getPaddingRight());
            } else {
                m2005e(this.f1299b.f1302E);
            }
            Drawable a = m1990a();
            int i = 0;
            if (a != null) {
                a.getPadding(this.f1299b.f1306I);
                i = -this.f1299b.f1306I.left;
            }
            m1999b(i + paddingLeft);
            m2006f(2);
            super.m2000c();
            m2010h().setChoiceMode(1);
            m2008g(this.f1299b.m1870f());
        }
    }

    SpinnerICS(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    SpinnerICS(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.f1306I = new Rect();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.Spinner, i, 0);
        if (i2 == -1) {
            i2 = obtainStyledAttributes.getInt(7, 0);
        }
        switch (i2) {
            case VideoSize.QCIF /*0*/:
                this.f1303F = new SpinnerICS();
                break;
            case VideoSize.CIF /*1*/:
                SpinnerICS spinnerICS = new SpinnerICS(this, context, attributeSet, i);
                this.f1302E = obtainStyledAttributes.getLayoutDimension(3, -2);
                spinnerICS.m1992a(obtainStyledAttributes.getDrawable(2));
                int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(5, 0);
                if (dimensionPixelOffset != 0) {
                    spinnerICS.m2001c(dimensionPixelOffset);
                }
                dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(4, 0);
                if (dimensionPixelOffset != 0) {
                    spinnerICS.m1999b(dimensionPixelOffset);
                }
                this.f1303F = spinnerICS;
                break;
        }
        this.f1305H = obtainStyledAttributes.getInt(0, 17);
        this.f1303F.m2075a(obtainStyledAttributes.getString(6));
        obtainStyledAttributes.recycle();
        if (this.f1304G != null) {
            this.f1303F.m2074a(this.f1304G);
            this.f1304G = null;
        }
    }

    private void m2089c(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addViewInLayout(view, 0, layoutParams);
        view.setSelected(hasFocus());
        view.measure(ViewGroup.getChildMeasureSpec(this.c, this.i.left + this.i.right, layoutParams.width), ViewGroup.getChildMeasureSpec(this.b, this.i.top + this.i.bottom, layoutParams.height));
        int measuredHeight = this.i.top + ((((getMeasuredHeight() - this.i.bottom) - this.i.top) - view.getMeasuredHeight()) / 2);
        view.layout(0, measuredHeight, view.getMeasuredWidth() + 0, view.getMeasuredHeight() + measuredHeight);
    }

    private View m2090e(int i) {
        View a;
        if (!this.u) {
            a = this.j.m1853a(i);
            if (a != null) {
                m2089c(a);
                return a;
            }
        }
        a = this.a.getView(i, null, this);
        m2089c(a);
        return a;
    }

    int m2091a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
        int max = Math.max(0, m1870f());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        int max2 = Math.max(0, max - (15 - (min - max)));
        View view = null;
        int i = 0;
        max = 0;
        while (max2 < min) {
            View view2;
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != max) {
                view2 = null;
            } else {
                itemViewType = max;
                view2 = view;
            }
            view = spinnerAdapter.getView(max2, view2, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i = Math.max(i, view.getMeasuredWidth());
            max2++;
            max = itemViewType;
        }
        if (drawable == null) {
            return i;
        }
        drawable.getPadding(this.f1306I);
        return (this.f1306I.left + this.f1306I.right) + i;
    }

    public void m2092a(AdapterViewICS adapterViewICS) {
        throw new RuntimeException("setOnItemClickListener cannot be used with a spinner.");
    }

    public void m2093a(SpinnerAdapter spinnerAdapter) {
        super.m1884a(spinnerAdapter);
        if (this.f1303F != null) {
            this.f1303F.m2074a(new SpinnerICS(spinnerAdapter));
        } else {
            this.f1304G = new SpinnerICS(spinnerAdapter);
        }
    }

    void m2094b(int i, boolean z) {
        int i2 = this.i.left;
        int right = ((getRight() - getLeft()) - this.i.left) - this.i.right;
        if (this.u) {
            m1875k();
        }
        if (this.z == 0) {
            m1882a();
            return;
        }
        if (this.v >= 0) {
            m1867c(this.v);
        }
        m1886b();
        removeAllViewsInLayout();
        this.k = this.x;
        View e = m2090e(this.x);
        int measuredWidth = e.getMeasuredWidth();
        switch (this.f1305H & 7) {
            case VideoSize.CIF /*1*/:
                i2 = (i2 + (right / 2)) - (measuredWidth / 2);
                break;
            case Version.API05_ECLAIR_20 /*5*/:
                i2 = (i2 + right) - measuredWidth;
                break;
        }
        e.offsetLeftAndRight(i2);
        this.j.m1854a();
        invalidate();
        m1876l();
        this.u = false;
        this.p = false;
        m1868d(this.x);
    }

    void m2095b(AdapterViewICS adapterViewICS) {
        super.m1862a(adapterViewICS);
    }

    public int getBaseline() {
        View view = null;
        if (getChildCount() > 0) {
            view = getChildAt(0);
        } else if (this.a != null && this.a.getCount() > 0) {
            view = m2090e(0);
            this.j.m1855a(0, view);
            removeAllViewsInLayout();
        }
        if (view == null) {
            return -1;
        }
        int baseline = view.getBaseline();
        return baseline >= 0 ? view.getTop() + baseline : -1;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        m1883a(i);
        dialogInterface.dismiss();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f1303F != null && this.f1303F.m2078f()) {
            this.f1303F.m2077d();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.r = true;
        m2094b(0, false);
        this.r = false;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f1303F != null && MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), m2091a(m1888d(), getBackground())), MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public boolean performClick() {
        boolean performClick = super.performClick();
        if (!performClick) {
            performClick = true;
            if (!this.f1303F.m2078f()) {
                this.f1303F.m2076c();
            }
        }
        return performClick;
    }
}
