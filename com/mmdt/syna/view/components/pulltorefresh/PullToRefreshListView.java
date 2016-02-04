package com.mmdt.syna.view.components.pulltorefresh;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0095a;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0096b;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0101h;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0103j;
import com.mmdt.syna.view.components.pulltorefresh.internal.EmptyViewMethodAccessor;
import com.mmdt.syna.view.components.pulltorefresh.internal.LoadingLayout;
import org.linphone.mediastream.Version;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {
    private static /* synthetic */ int[] f1914f;
    private LoadingLayout f1915b;
    private LoadingLayout f1916c;
    private FrameLayout f1917d;
    private boolean f1918e;

    protected class InternalListView extends ListView implements EmptyViewMethodAccessor {
        final /* synthetic */ PullToRefreshListView f1911a;
        private boolean f1912b;

        public InternalListView(PullToRefreshListView pullToRefreshListView, Context context, AttributeSet attributeSet) {
            this.f1911a = pullToRefreshListView;
            super(context, attributeSet);
            this.f1912b = false;
        }

        protected void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            try {
                return super.dispatchTouchEvent(motionEvent);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void setAdapter(ListAdapter listAdapter) {
            if (!(this.f1911a.f1917d == null || this.f1912b)) {
                addFooterView(this.f1911a.f1917d, null, false);
                this.f1912b = true;
            }
            super.setAdapter(listAdapter);
        }

        public void setEmptyView(View view) {
            this.f1911a.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }

    @TargetApi(9)
    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshListView.a */
    final class C0104a extends InternalListView {
        final /* synthetic */ PullToRefreshListView f1913b;

        public C0104a(PullToRefreshListView pullToRefreshListView, Context context, AttributeSet attributeSet) {
            this.f1913b = pullToRefreshListView;
            super(pullToRefreshListView, context, attributeSet);
        }

        protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.m2868a(this.f1913b, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshListView(Context context, C0096b c0096b) {
        super(context, c0096b);
    }

    public PullToRefreshListView(Context context, C0096b c0096b, C0095a c0095a) {
        super(context, c0096b, c0095a);
    }

    static /* synthetic */ int[] m2855D() {
        int[] iArr = f1914f;
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
            f1914f = iArr;
        }
        return iArr;
    }

    protected /* synthetic */ View m2857a(Context context, AttributeSet attributeSet) {
        return m2862c(context, attributeSet);
    }

    protected void m2858a(TypedArray typedArray) {
        super.m2830a(typedArray);
        this.f1918e = typedArray.getBoolean(14, true);
        if (this.f1918e) {
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
            View frameLayout = new FrameLayout(getContext());
            this.f1915b = m2783a(getContext(), C0096b.PULL_FROM_START, typedArray);
            this.f1915b.setVisibility(8);
            frameLayout.addView(this.f1915b, layoutParams);
            ((ListView) this.a).addHeaderView(frameLayout, null, false);
            this.f1917d = new FrameLayout(getContext());
            this.f1916c = m2783a(getContext(), C0096b.PULL_FROM_END, typedArray);
            this.f1916c.setVisibility(8);
            this.f1917d.addView(this.f1916c, layoutParams);
            if (!typedArray.hasValue(13)) {
                setScrollingWhileRefreshingEnabled(true);
            }
        }
    }

    protected void m2859a(boolean z) {
        ListAdapter adapter = ((ListView) this.a).getAdapter();
        if (!this.f1918e || !m2806l() || adapter == null || adapter.isEmpty()) {
            super.m2831a(z);
            return;
        }
        LoadingLayout t;
        LoadingLayout loadingLayout;
        LoadingLayout loadingLayout2;
        int count;
        int scrollY;
        super.m2831a(false);
        switch (m2855D()[m2802h().ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
            case Version.API05_ECLAIR_20 /*5*/:
                t = m2814t();
                loadingLayout = this.f1916c;
                loadingLayout2 = this.f1915b;
                count = ((ListView) this.a).getCount() - 1;
                scrollY = getScrollY() - m2815u();
                break;
            default:
                loadingLayout = m2816v();
                loadingLayout2 = this.f1915b;
                scrollY = getScrollY() + m2817w();
                t = loadingLayout;
                loadingLayout = loadingLayout2;
                loadingLayout2 = this.f1916c;
                count = 0;
                break;
        }
        t.m2892l();
        t.m2888h();
        loadingLayout2.setVisibility(8);
        loadingLayout.setVisibility(0);
        loadingLayout.m2890j();
        if (z) {
            m2813s();
            m2785a(scrollY);
            ((ListView) this.a).setSelection(count);
            m2796b(0);
        }
    }

    protected ListView m2860b(Context context, AttributeSet attributeSet) {
        return VERSION.SDK_INT >= 9 ? new C0104a(this, context, attributeSet) : new InternalListView(this, context, attributeSet);
    }

    protected LoadingLayoutProxy m2861b(boolean z, boolean z2) {
        LoadingLayoutProxy b = super.m2794b(z, z2);
        if (this.f1918e) {
            C0096b j = m2804j();
            if (z && j.m2844c()) {
                b.m2865a(this.f1915b);
            }
            if (z2 && j.m2845d()) {
                b.m2865a(this.f1916c);
            }
        }
        return b;
    }

    protected ListView m2862c(Context context, AttributeSet attributeSet) {
        ListView b = m2860b(context, attributeSet);
        b.setId(16908298);
        return b;
    }

    protected void m2863c() {
        int i = 0;
        int i2 = 1;
        if (this.f1918e) {
            int i3;
            LoadingLayout loadingLayout;
            LoadingLayout loadingLayout2;
            LoadingLayout loadingLayout3;
            int count;
            switch (m2855D()[m2802h().ordinal()]) {
                case Version.API03_CUPCAKE_15 /*3*/:
                case Version.API05_ECLAIR_20 /*5*/:
                    LoadingLayout t = m2814t();
                    loadingLayout3 = this.f1916c;
                    count = ((ListView) this.a).getCount() - 1;
                    int u = m2815u();
                    i3 = Math.abs(((ListView) this.a).getLastVisiblePosition() - count) <= 1 ? 1 : 0;
                    i = count;
                    i2 = u;
                    loadingLayout = loadingLayout3;
                    loadingLayout2 = t;
                    break;
                default:
                    loadingLayout3 = m2816v();
                    loadingLayout2 = this.f1915b;
                    count = -m2817w();
                    if (Math.abs(((ListView) this.a).getFirstVisiblePosition() - 0) > 1) {
                        i2 = 0;
                    }
                    i3 = i2;
                    i2 = count;
                    loadingLayout = loadingLayout2;
                    loadingLayout2 = loadingLayout3;
                    break;
            }
            if (loadingLayout.getVisibility() == 0) {
                loadingLayout2.m2893m();
                loadingLayout.setVisibility(8);
                if (!(i3 == 0 || m2807m() == C0103j.MANUAL_REFRESHING)) {
                    ((ListView) this.a).setSelection(i);
                    m2785a(i2);
                }
            }
            super.m2833c();
            return;
        }
        super.m2833c();
    }

    public final C0101h m2864r() {
        return C0101h.VERTICAL;
    }
}
