package com.mmdt.syna.view.components.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0095a;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0096b;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0097c;
import com.mmdt.syna.view.components.pulltorefresh.internal.EmptyViewMethodAccessor;
import com.mmdt.syna.view.components.pulltorefresh.internal.IndicatorLayout;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public abstract class PullToRefreshAdapterViewBase<T extends AbsListView> extends PullToRefreshBase<T> implements OnScrollListener {
    private static /* synthetic */ int[] f1869j;
    private boolean f1870b;
    private OnScrollListener f1871c;
    private C0097c f1872d;
    private View f1873e;
    private IndicatorLayout f1874f;
    private IndicatorLayout f1875g;
    private boolean f1876h;
    private boolean f1877i;

    public PullToRefreshAdapterViewBase(Context context) {
        super(context);
        this.f1877i = true;
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1877i = true;
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, C0096b c0096b) {
        super(context, c0096b);
        this.f1877i = true;
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    public PullToRefreshAdapterViewBase(Context context, C0096b c0096b, C0095a c0095a) {
        super(context, c0096b, c0095a);
        this.f1877i = true;
        ((AbsListView) this.a).setOnScrollListener(this);
    }

    private void m2821D() {
        C0096b j = m2804j();
        FrameLayout y = m2819y();
        if (j.m2844c() && this.f1874f == null) {
            this.f1874f = new IndicatorLayout(getContext(), C0096b.PULL_FROM_START);
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.rightMargin = getResources().getDimensionPixelSize(2131296301);
            layoutParams.gravity = 53;
            y.addView(this.f1874f, layoutParams);
        } else if (!(j.m2844c() || this.f1874f == null)) {
            y.removeView(this.f1874f);
            this.f1874f = null;
        }
        if (j.m2845d() && this.f1875g == null) {
            this.f1875g = new IndicatorLayout(getContext(), C0096b.PULL_FROM_END);
            LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
            layoutParams2.rightMargin = getResources().getDimensionPixelSize(2131296301);
            layoutParams2.gravity = 85;
            y.addView(this.f1875g, layoutParams2);
        } else if (!j.m2845d() && this.f1875g != null) {
            y.removeView(this.f1875g);
            this.f1875g = null;
        }
    }

    private boolean m2822E() {
        return this.f1876h && m2808n();
    }

    private boolean m2823F() {
        Adapter adapter = ((AbsListView) this.a).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            return true;
        }
        if (((AbsListView) this.a).getFirstVisiblePosition() <= 1) {
            View childAt = ((AbsListView) this.a).getChildAt(0);
            if (childAt != null) {
                return childAt.getTop() >= ((AbsListView) this.a).getTop();
            }
        }
        return false;
    }

    private boolean m2824G() {
        Adapter adapter = ((AbsListView) this.a).getAdapter();
        if (adapter == null || adapter.isEmpty()) {
            return true;
        }
        int count = ((AbsListView) this.a).getCount() - 1;
        int lastVisiblePosition = ((AbsListView) this.a).getLastVisiblePosition();
        if (lastVisiblePosition >= count - 1) {
            View childAt = ((AbsListView) this.a).getChildAt(lastVisiblePosition - ((AbsListView) this.a).getFirstVisiblePosition());
            if (childAt != null) {
                return childAt.getBottom() <= ((AbsListView) this.a).getBottom();
            }
        }
        return false;
    }

    private void m2825H() {
        if (this.f1874f != null) {
            m2819y().removeView(this.f1874f);
            this.f1874f = null;
        }
        if (this.f1875g != null) {
            m2819y().removeView(this.f1875g);
            this.f1875g = null;
        }
    }

    private void m2826I() {
        if (this.f1874f != null) {
            if (m2810p() || !m2834d()) {
                if (this.f1874f.m2904a()) {
                    this.f1874f.m2905b();
                }
            } else if (!this.f1874f.m2904a()) {
                this.f1874f.m2906c();
            }
        }
        if (this.f1875g == null) {
            return;
        }
        if (m2810p() || !m2835e()) {
            if (this.f1875g.m2904a()) {
                this.f1875g.m2905b();
            }
        } else if (!this.f1875g.m2904a()) {
            this.f1875g.m2906c();
        }
    }

    private static FrameLayout.LayoutParams m2827a(LayoutParams layoutParams) {
        FrameLayout.LayoutParams layoutParams2 = null;
        if (layoutParams != null) {
            layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            if (layoutParams instanceof LinearLayout.LayoutParams) {
                layoutParams2.gravity = ((LinearLayout.LayoutParams) layoutParams).gravity;
            } else {
                layoutParams2.gravity = 17;
            }
        }
        return layoutParams2;
    }

    static /* synthetic */ int[] m2828g() {
        int[] iArr = f1869j;
        if (iArr == null) {
            iArr = new int[C0096b.values().length];
            try {
                iArr[C0096b.BOTH.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0096b.DISABLED.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[C0096b.MANUAL_REFRESH_ONLY.ordinal()] = 5;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[C0096b.PULL_FROM_END.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[C0096b.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            f1869j = iArr;
        }
        return iArr;
    }

    protected void m2829a() {
        super.m2784a();
        if (m2822E()) {
            switch (m2828g()[m2802h().ordinal()]) {
                case VideoSize.HVGA /*2*/:
                    this.f1874f.m2908e();
                case Version.API03_CUPCAKE_15 /*3*/:
                    this.f1875g.m2908e();
                default:
            }
        }
    }

    protected void m2830a(TypedArray typedArray) {
        boolean z = false;
        this.f1876h = typedArray.getBoolean(5, !m2809o());
        if (!m2809o()) {
            z = true;
        }
        this.f1876h = typedArray.getBoolean(5, z);
    }

    protected void m2831a(boolean z) {
        super.m2793a(z);
        if (m2822E()) {
            m2826I();
        }
    }

    protected void m2832b() {
        super.m2795b();
        if (m2822E()) {
            switch (m2828g()[m2802h().ordinal()]) {
                case VideoSize.HVGA /*2*/:
                    this.f1874f.m2907d();
                case Version.API03_CUPCAKE_15 /*3*/:
                    this.f1875g.m2907d();
                default:
            }
        }
    }

    protected void m2833c() {
        super.m2798c();
        if (m2822E()) {
            m2826I();
        }
    }

    protected boolean m2834d() {
        return m2823F();
    }

    protected boolean m2835e() {
        return m2824G();
    }

    protected void m2836f() {
        super.m2801f();
        if (m2822E()) {
            m2821D();
        } else {
            m2825H();
        }
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.f1872d != null) {
            boolean z = i3 > 0 && i + i2 >= i3 - 1;
            this.f1870b = z;
        }
        if (m2822E()) {
            m2826I();
        }
        if (this.f1871c != null) {
            this.f1871c.onScroll(absListView, i, i2, i3);
        }
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.f1873e != null && !this.f1877i) {
            this.f1873e.scrollTo(-i, -i2);
        }
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0 && this.f1872d != null && this.f1870b) {
            this.f1872d.m2847a();
        }
        if (this.f1871c != null) {
            this.f1871c.onScrollStateChanged(absListView, i);
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        ((AdapterView) this.a).setAdapter(listAdapter);
    }

    public final void setEmptyView(View view) {
        FrameLayout y = m2819y();
        if (view != null) {
            view.setClickable(true);
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof ViewGroup)) {
                ((ViewGroup) parent).removeView(view);
            }
            LayoutParams a = m2827a(view.getLayoutParams());
            if (a != null) {
                y.addView(view, a);
            } else {
                y.addView(view);
            }
        }
        if (this.a instanceof EmptyViewMethodAccessor) {
            ((EmptyViewMethodAccessor) this.a).setEmptyViewInternal(view);
        } else {
            ((AbsListView) this.a).setEmptyView(view);
        }
        this.f1873e = view;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        ((AbsListView) this.a).setOnItemClickListener(onItemClickListener);
    }

    public final void setOnLastItemVisibleListener(C0097c c0097c) {
        this.f1872d = c0097c;
    }

    public final void setOnScrollListener(OnScrollListener onScrollListener) {
        this.f1871c = onScrollListener;
    }

    public final void setScrollEmptyView(boolean z) {
        this.f1877i = z;
    }

    public void setShowIndicator(boolean z) {
        this.f1876h = z;
        if (m2822E()) {
            m2821D();
        } else {
            m2825H();
        }
    }
}
