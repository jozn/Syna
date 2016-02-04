package android.support.v7.internal.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.CapturedViewProperty;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/* renamed from: android.support.v7.internal.widget.l */
abstract class AdapterViewICS<T extends Adapter> extends ViewGroup {
    int f1029A;
    int f1030B;
    long f1031C;
    boolean f1032D;
    private int f1033a;
    private View f1034b;
    private boolean f1035c;
    private boolean f1036d;
    private AdapterViewICS f1037e;
    @ExportedProperty(category = "scrolling")
    int f1038k;
    int f1039l;
    int f1040m;
    long f1041n;
    long f1042o;
    boolean f1043p;
    int f1044q;
    boolean f1045r;
    AdapterViewICS f1046s;
    AdapterViewICS f1047t;
    boolean f1048u;
    @ExportedProperty(category = "list")
    int f1049v;
    long f1050w;
    @ExportedProperty(category = "list")
    int f1051x;
    long f1052y;
    @ExportedProperty(category = "list")
    int f1053z;

    /* renamed from: android.support.v7.internal.widget.l.b */
    public interface AdapterViewICS {
        void m2029a(AdapterViewICS<?> adapterViewICS, View view, int i, long j);
    }

    /* renamed from: android.support.v7.internal.widget.l.d */
    public interface AdapterViewICS {
        void m2041a(AdapterViewICS<?> adapterViewICS);

        void m2042a(AdapterViewICS<?> adapterViewICS, View view, int i, long j);
    }

    /* renamed from: android.support.v7.internal.widget.l.a */
    class AdapterViewICS extends DataSetObserver {
        final /* synthetic */ AdapterViewICS f1284a;
        private Parcelable f1285b;

        AdapterViewICS(AdapterViewICS adapterViewICS) {
            this.f1284a = adapterViewICS;
            this.f1285b = null;
        }

        public void onChanged() {
            this.f1284a.f1048u = true;
            this.f1284a.f1029A = this.f1284a.f1053z;
            this.f1284a.f1053z = this.f1284a.m1869e().getCount();
            if (!this.f1284a.m1869e().hasStableIds() || this.f1285b == null || this.f1284a.f1029A != 0 || this.f1284a.f1053z <= 0) {
                this.f1284a.m1878n();
            } else {
                this.f1284a.onRestoreInstanceState(this.f1285b);
                this.f1285b = null;
            }
            this.f1284a.m1873i();
            this.f1284a.requestLayout();
        }

        public void onInvalidated() {
            this.f1284a.f1048u = true;
            if (this.f1284a.m1869e().hasStableIds()) {
                this.f1285b = this.f1284a.onSaveInstanceState();
            }
            this.f1284a.f1029A = this.f1284a.f1053z;
            this.f1284a.f1053z = 0;
            this.f1284a.f1051x = -1;
            this.f1284a.f1052y = Long.MIN_VALUE;
            this.f1284a.f1049v = -1;
            this.f1284a.f1050w = Long.MIN_VALUE;
            this.f1284a.f1043p = false;
            this.f1284a.m1873i();
            this.f1284a.requestLayout();
        }
    }

    /* renamed from: android.support.v7.internal.widget.l.c */
    class AdapterViewICS implements OnItemClickListener {
        final /* synthetic */ AdapterViewICS f1286a;
        private final AdapterViewICS f1287b;

        public AdapterViewICS(AdapterViewICS adapterViewICS, AdapterViewICS adapterViewICS2) {
            this.f1286a = adapterViewICS;
            this.f1287b = adapterViewICS2;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f1287b.m2029a(this.f1286a, view, i, j);
        }
    }

    /* renamed from: android.support.v7.internal.widget.l.e */
    private class AdapterViewICS implements Runnable {
        final /* synthetic */ AdapterViewICS f1288a;

        private AdapterViewICS(AdapterViewICS adapterViewICS) {
            this.f1288a = adapterViewICS;
        }

        public void run() {
            if (!this.f1288a.f1048u) {
                this.f1288a.m1857a();
            } else if (this.f1288a.m1869e() != null) {
                this.f1288a.post(this);
            }
        }
    }

    AdapterViewICS(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1038k = 0;
        this.f1041n = Long.MIN_VALUE;
        this.f1043p = false;
        this.f1045r = false;
        this.f1049v = -1;
        this.f1050w = Long.MIN_VALUE;
        this.f1051x = -1;
        this.f1052y = Long.MIN_VALUE;
        this.f1030B = -1;
        this.f1031C = Long.MIN_VALUE;
        this.f1032D = false;
    }

    private void m1857a() {
        if (this.f1046s != null) {
            int f = m1870f();
            if (f >= 0) {
                View c = m1866c();
                this.f1046s.m2042a(this, c, f, m1869e().getItemId(f));
                return;
            }
            this.f1046s.m2041a(this);
        }
    }

    private void m1859a(boolean z) {
        if (m1872h()) {
            z = false;
        }
        if (z) {
            if (this.f1034b != null) {
                this.f1034b.setVisibility(0);
                setVisibility(8);
            } else {
                setVisibility(0);
            }
            if (this.f1048u) {
                onLayout(false, getLeft(), getTop(), getRight(), getBottom());
                return;
            }
            return;
        }
        if (this.f1034b != null) {
            this.f1034b.setVisibility(8);
        }
        setVisibility(0);
    }

    int m1861a(int i, boolean z) {
        return i;
    }

    public void m1862a(AdapterViewICS adapterViewICS) {
        this.f1047t = adapterViewICS;
    }

    public void m1863a(AdapterViewICS adapterViewICS) {
        this.f1046s = adapterViewICS;
    }

    public boolean m1864a(View view, int i, long j) {
        if (this.f1047t == null) {
            return false;
        }
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        this.f1047t.m2029a(this, view, i, j);
        return true;
    }

    public void addView(View view) {
        throw new UnsupportedOperationException("addView(View) is not supported in AdapterView");
    }

    public void addView(View view, int i) {
        throw new UnsupportedOperationException("addView(View, int) is not supported in AdapterView");
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, int, LayoutParams) is not supported in AdapterView");
    }

    public void addView(View view, LayoutParams layoutParams) {
        throw new UnsupportedOperationException("addView(View, LayoutParams) is not supported in AdapterView");
    }

    public long m1865b(int i) {
        Adapter e = m1869e();
        return (e == null || i < 0) ? Long.MIN_VALUE : e.getItemId(i);
    }

    public abstract View m1866c();

    void m1867c(int i) {
        this.f1051x = i;
        this.f1052y = m1865b(i);
    }

    protected boolean canAnimate() {
        return super.canAnimate() && this.f1053z > 0;
    }

    void m1868d(int i) {
        this.f1049v = i;
        this.f1050w = m1865b(i);
        if (this.f1043p && this.f1044q == 0 && i >= 0) {
            this.f1040m = i;
            this.f1041n = this.f1050w;
        }
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        View c = m1866c();
        return c != null && c.getVisibility() == 0 && c.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    protected void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    public abstract T m1869e();

    @CapturedViewProperty
    public int m1870f() {
        return this.f1049v;
    }

    @CapturedViewProperty
    public long m1871g() {
        return this.f1050w;
    }

    boolean m1872h() {
        return false;
    }

    void m1873i() {
        boolean z = false;
        Adapter e = m1869e();
        boolean z2 = e == null || e.getCount() == 0;
        boolean z3 = !z2 || m1872h();
        z2 = z3 && this.f1036d;
        super.setFocusableInTouchMode(z2);
        z2 = z3 && this.f1035c;
        super.setFocusable(z2);
        if (this.f1034b != null) {
            if (e == null || e.isEmpty()) {
                z = true;
            }
            m1859a(z);
        }
    }

    void m1874j() {
        if (this.f1046s != null) {
            if (this.f1045r || this.f1032D) {
                if (this.f1037e == null) {
                    this.f1037e = new AdapterViewICS();
                }
                post(this.f1037e);
            } else {
                m1857a();
            }
        }
        if (this.f1051x != -1 && isShown() && !isInTouchMode()) {
            sendAccessibilityEvent(4);
        }
    }

    void m1875k() {
        boolean z;
        int i = this.f1053z;
        if (i > 0) {
            int m;
            boolean z2;
            if (this.f1043p) {
                this.f1043p = false;
                m = m1877m();
                if (m >= 0 && m1861a(m, true) == m) {
                    m1868d(m);
                    z2 = true;
                    if (!z2) {
                        m = m1870f();
                        if (m >= i) {
                            m = i - 1;
                        }
                        if (m < 0) {
                            m = 0;
                        }
                        i = m1861a(m, true);
                        m = i >= 0 ? m1861a(m, false) : i;
                        if (m >= 0) {
                            m1868d(m);
                            m1876l();
                            z = true;
                        }
                    }
                    z = z2;
                }
            }
            z2 = false;
            if (z2) {
                m = m1870f();
                if (m >= i) {
                    m = i - 1;
                }
                if (m < 0) {
                    m = 0;
                }
                i = m1861a(m, true);
                if (i >= 0) {
                }
                if (m >= 0) {
                    m1868d(m);
                    m1876l();
                    z = true;
                }
            }
            z = z2;
        } else {
            z = false;
        }
        if (!z) {
            this.f1051x = -1;
            this.f1052y = Long.MIN_VALUE;
            this.f1049v = -1;
            this.f1050w = Long.MIN_VALUE;
            this.f1043p = false;
            m1876l();
        }
    }

    void m1876l() {
        if (this.f1051x != this.f1030B || this.f1052y != this.f1031C) {
            m1874j();
            this.f1030B = this.f1051x;
            this.f1031C = this.f1052y;
        }
    }

    int m1877m() {
        int i = this.f1053z;
        if (i == 0) {
            return -1;
        }
        long j = this.f1041n;
        int i2 = this.f1040m;
        if (j == Long.MIN_VALUE) {
            return -1;
        }
        int min = Math.min(i - 1, Math.max(0, i2));
        long uptimeMillis = SystemClock.uptimeMillis() + 100;
        Object obj = null;
        Adapter e = m1869e();
        if (e == null) {
            return -1;
        }
        int i3 = min;
        int i4 = min;
        while (SystemClock.uptimeMillis() <= uptimeMillis) {
            if (e.getItemId(i4) != j) {
                Object obj2 = min == i + -1 ? 1 : null;
                Object obj3 = i3 == 0 ? 1 : null;
                if (obj2 != null && obj3 != null) {
                    break;
                } else if (obj3 != null || (r0 != null && obj2 == null)) {
                    min++;
                    obj = null;
                    i4 = min;
                } else if (obj2 != null || (r0 == null && obj3 == null)) {
                    i3--;
                    obj = 1;
                    i4 = i3;
                }
            } else {
                return i4;
            }
        }
        return -1;
    }

    void m1878n() {
        if (getChildCount() > 0) {
            this.f1043p = true;
            this.f1042o = (long) this.f1033a;
            View childAt;
            if (this.f1051x >= 0) {
                childAt = getChildAt(this.f1051x - this.f1038k);
                this.f1041n = this.f1050w;
                this.f1040m = this.f1049v;
                if (childAt != null) {
                    this.f1039l = childAt.getTop();
                }
                this.f1044q = 0;
                return;
            }
            childAt = getChildAt(0);
            Adapter e = m1869e();
            if (this.f1038k < 0 || this.f1038k >= e.getCount()) {
                this.f1041n = -1;
            } else {
                this.f1041n = e.getItemId(this.f1038k);
            }
            this.f1040m = this.f1038k;
            if (childAt != null) {
                this.f1039l = childAt.getTop();
            }
            this.f1044q = 1;
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.f1037e);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f1033a = getHeight();
    }

    public void removeAllViews() {
        throw new UnsupportedOperationException("removeAllViews() is not supported in AdapterView");
    }

    public void removeView(View view) {
        throw new UnsupportedOperationException("removeView(View) is not supported in AdapterView");
    }

    public void removeViewAt(int i) {
        throw new UnsupportedOperationException("removeViewAt(int) is not supported in AdapterView");
    }

    public void setFocusable(boolean z) {
        boolean z2 = true;
        Adapter e = m1869e();
        boolean z3 = e == null || e.getCount() == 0;
        this.f1035c = z;
        if (!z) {
            this.f1036d = false;
        }
        if (!z || (z3 && !m1872h())) {
            z2 = false;
        }
        super.setFocusable(z2);
    }

    public void setFocusableInTouchMode(boolean z) {
        boolean z2 = true;
        Adapter e = m1869e();
        boolean z3 = e == null || e.getCount() == 0;
        this.f1036d = z;
        if (z) {
            this.f1035c = true;
        }
        if (!z || (z3 && !m1872h())) {
            z2 = false;
        }
        super.setFocusableInTouchMode(z2);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        throw new RuntimeException("Don't call setOnClickListener for an AdapterView. You probably want setOnItemClickListener instead");
    }
}
