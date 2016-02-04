package com.mmdt.syna.view.components.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.mmdt.syna.R.R;
import com.mmdt.syna.view.components.pulltorefresh.internal.FlipLoadingLayout;
import com.mmdt.syna.view.components.pulltorefresh.internal.LoadingLayout;
import com.mmdt.syna.view.components.pulltorefresh.internal.RotateLoadingLayout;
import com.mmdt.syna.view.components.pulltorefresh.internal.Utils;
import com.mmdt.syna.view.components.pulltorefresh.internal.ViewCompat;
import org.apache.http.HttpStatus;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public abstract class PullToRefreshBase<T extends View> extends LinearLayout {
    private static /* synthetic */ int[] f1842A;
    private static /* synthetic */ int[] f1843y;
    private static /* synthetic */ int[] f1844z;
    T f1845a;
    private int f1846b;
    private float f1847c;
    private float f1848d;
    private float f1849e;
    private float f1850f;
    private boolean f1851g;
    private C0103j f1852h;
    private C0096b f1853i;
    private C0096b f1854j;
    private FrameLayout f1855k;
    private boolean f1856l;
    private boolean f1857m;
    private boolean f1858n;
    private boolean f1859o;
    private boolean f1860p;
    private Interpolator f1861q;
    private C0095a f1862r;
    private LoadingLayout f1863s;
    private LoadingLayout f1864t;
    private C0094e<T> f1865u;
    private C0099f<T> f1866v;
    private C0098d<T> f1867w;
    private C0102i f1868x;

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.e */
    public interface C0094e<V extends View> {
        void m2488a(PullToRefreshBase<V> pullToRefreshBase);
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.a */
    public enum C0095a {
        ROTATE,
        FLIP;

        static C0095a m2837a() {
            return ROTATE;
        }

        static C0095a m2838a(int i) {
            switch (i) {
                case VideoSize.CIF /*1*/:
                    return FLIP;
                default:
                    return ROTATE;
            }
        }

        LoadingLayout m2840a(Context context, C0096b c0096b, C0101h c0101h, TypedArray typedArray) {
            switch (C0095a.m2839b()[ordinal()]) {
                case VideoSize.HVGA /*2*/:
                    return new FlipLoadingLayout(context, c0096b, c0101h, typedArray);
                default:
                    return new RotateLoadingLayout(context, c0096b, c0101h, typedArray);
            }
        }
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.b */
    public enum C0096b {
        DISABLED(0),
        PULL_FROM_START(1),
        PULL_FROM_END(2),
        BOTH(3),
        MANUAL_REFRESH_ONLY(4);
        
        public static C0096b f1887f;
        public static C0096b f1888g;
        private int f1890h;

        static {
            f1887f = PULL_FROM_START;
            f1888g = PULL_FROM_END;
        }

        private C0096b(int i) {
            this.f1890h = i;
        }

        static C0096b m2841a() {
            return PULL_FROM_START;
        }

        static C0096b m2842a(int i) {
            for (C0096b c0096b : C0096b.values()) {
                if (i == c0096b.m2846e()) {
                    return c0096b;
                }
            }
            return C0096b.m2841a();
        }

        boolean m2843b() {
            return (this == DISABLED || this == MANUAL_REFRESH_ONLY) ? false : true;
        }

        public boolean m2844c() {
            return this == PULL_FROM_START || this == BOTH;
        }

        public boolean m2845d() {
            return this == PULL_FROM_END || this == BOTH || this == MANUAL_REFRESH_ONLY;
        }

        int m2846e() {
            return this.f1890h;
        }
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.c */
    public interface C0097c {
        void m2847a();
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.d */
    public interface C0098d<V extends View> {
        void m2848a(PullToRefreshBase<V> pullToRefreshBase, C0103j c0103j, C0096b c0096b);
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.f */
    public interface C0099f<V extends View> {
        void m2849a(PullToRefreshBase<V> pullToRefreshBase);

        void m2850b(PullToRefreshBase<V> pullToRefreshBase);
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.g */
    interface C0100g {
        void m2851a();
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.h */
    public enum C0101h {
        VERTICAL,
        HORIZONTAL
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.i */
    final class C0102i implements Runnable {
        final /* synthetic */ PullToRefreshBase f1894a;
        private final Interpolator f1895b;
        private final int f1896c;
        private final int f1897d;
        private final long f1898e;
        private C0100g f1899f;
        private boolean f1900g;
        private long f1901h;
        private int f1902i;

        public C0102i(PullToRefreshBase pullToRefreshBase, int i, int i2, long j, C0100g c0100g) {
            this.f1894a = pullToRefreshBase;
            this.f1900g = true;
            this.f1901h = -1;
            this.f1902i = -1;
            this.f1897d = i;
            this.f1896c = i2;
            this.f1895b = pullToRefreshBase.f1861q;
            this.f1898e = j;
            this.f1899f = c0100g;
        }

        public void m2852a() {
            this.f1900g = false;
            this.f1894a.removeCallbacks(this);
        }

        public void run() {
            if (this.f1901h == -1) {
                this.f1901h = System.currentTimeMillis();
            } else {
                float f = (float) (this.f1897d - this.f1896c);
                this.f1902i = this.f1897d - Math.round(this.f1895b.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.f1901h) * 1000) / this.f1898e, 1000), 0)) / 1000.0f) * f);
                this.f1894a.m2785a(this.f1902i);
            }
            if (this.f1900g && this.f1896c != this.f1902i) {
                ViewCompat.m2921a(this.f1894a, (Runnable) this);
            } else if (this.f1899f != null) {
                this.f1899f.m2851a();
            }
        }
    }

    /* renamed from: com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.j */
    public enum C0103j {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9),
        OVERSCROLLING(16);
        
        private int f1910g;

        private C0103j(int i) {
            this.f1910g = i;
        }

        static C0103j m2853a(int i) {
            for (C0103j c0103j : C0103j.values()) {
                if (i == c0103j.m2854a()) {
                    return c0103j;
                }
            }
            return RESET;
        }

        int m2854a() {
            return this.f1910g;
        }
    }

    public PullToRefreshBase(Context context) {
        super(context);
        this.f1851g = false;
        this.f1852h = C0103j.RESET;
        this.f1853i = C0096b.m2841a();
        this.f1856l = true;
        this.f1857m = false;
        this.f1858n = true;
        this.f1859o = true;
        this.f1860p = true;
        this.f1862r = C0095a.m2837a();
        m2778b(context, null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1851g = false;
        this.f1852h = C0103j.RESET;
        this.f1853i = C0096b.m2841a();
        this.f1856l = true;
        this.f1857m = false;
        this.f1858n = true;
        this.f1859o = true;
        this.f1860p = true;
        this.f1862r = C0095a.m2837a();
        m2778b(context, attributeSet);
    }

    public PullToRefreshBase(Context context, C0096b c0096b) {
        super(context);
        this.f1851g = false;
        this.f1852h = C0103j.RESET;
        this.f1853i = C0096b.m2841a();
        this.f1856l = true;
        this.f1857m = false;
        this.f1858n = true;
        this.f1859o = true;
        this.f1860p = true;
        this.f1862r = C0095a.m2837a();
        this.f1853i = c0096b;
        m2778b(context, null);
    }

    public PullToRefreshBase(Context context, C0096b c0096b, C0095a c0095a) {
        super(context);
        this.f1851g = false;
        this.f1852h = C0103j.RESET;
        this.f1853i = C0096b.m2841a();
        this.f1856l = true;
        this.f1857m = false;
        this.f1858n = true;
        this.f1859o = true;
        this.f1860p = true;
        this.f1862r = C0095a.m2837a();
        this.f1853i = c0096b;
        this.f1862r = c0095a;
        m2778b(context, null);
    }

    static /* synthetic */ int[] m2767A() {
        int[] iArr = f1843y;
        if (iArr == null) {
            iArr = new int[C0101h.values().length];
            try {
                iArr[C0101h.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0101h.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            f1843y = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m2768B() {
        int[] iArr = f1844z;
        if (iArr == null) {
            iArr = new int[C0103j.values().length];
            try {
                iArr[C0103j.MANUAL_REFRESHING.ordinal()] = 5;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0103j.OVERSCROLLING.ordinal()] = 6;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[C0103j.PULL_TO_REFRESH.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[C0103j.REFRESHING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[C0103j.RELEASE_TO_REFRESH.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[C0103j.RESET.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            f1844z = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m2769C() {
        int[] iArr = f1842A;
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
            f1842A = iArr;
        }
        return iArr;
    }

    private boolean m2770D() {
        switch (m2769C()[this.f1853i.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return m2799d();
            case Version.API03_CUPCAKE_15 /*3*/:
                return m2800e();
            case Version.API04_DONUT_16 /*4*/:
                return m2800e() || m2799d();
            default:
                return false;
        }
    }

    private void m2771E() {
        float f;
        float f2;
        int round;
        int u;
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.HVGA /*2*/:
                f = this.f1849e;
                f2 = this.f1847c;
                break;
            default:
                f = this.f1850f;
                f2 = this.f1848d;
                break;
        }
        switch (m2769C()[this.f1854j.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                round = Math.round(Math.max(f - f2, 0.0f) / 2.0f);
                u = m2815u();
                break;
            default:
                round = Math.round(Math.min(f - f2, 0.0f) / 2.0f);
                u = m2817w();
                break;
        }
        m2785a(round);
        if (round != 0 && !m2810p()) {
            float abs = ((float) Math.abs(round)) / ((float) u);
            switch (m2769C()[this.f1854j.ordinal()]) {
                case Version.API03_CUPCAKE_15 /*3*/:
                    this.f1864t.m2883b(abs);
                    break;
                default:
                    this.f1863s.m2883b(abs);
                    break;
            }
            if (this.f1852h != C0103j.PULL_TO_REFRESH && u >= Math.abs(round)) {
                m2792a(C0103j.PULL_TO_REFRESH, new boolean[0]);
            } else if (this.f1852h == C0103j.PULL_TO_REFRESH && u < Math.abs(round)) {
                m2792a(C0103j.RELEASE_TO_REFRESH, new boolean[0]);
            }
        }
    }

    private LayoutParams m2772F() {
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return new LayoutParams(-2, -1);
            default:
                return new LayoutParams(-1, -2);
        }
    }

    private int m2773G() {
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return Math.round(((float) getWidth()) / 2.0f);
            default:
                return Math.round(((float) getHeight()) / 2.0f);
        }
    }

    private final void m2775a(int i, long j) {
        m2776a(i, j, 0, null);
    }

    private final void m2776a(int i, long j, long j2, C0100g c0100g) {
        int scrollX;
        if (this.f1868x != null) {
            this.f1868x.m2852a();
        }
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.HVGA /*2*/:
                scrollX = getScrollX();
                break;
            default:
                scrollX = getScrollY();
                break;
        }
        if (scrollX != i) {
            if (this.f1861q == null) {
                this.f1861q = new DecelerateInterpolator();
            }
            this.f1868x = new C0102i(this, scrollX, i, j, c0100g);
            if (j2 > 0) {
                postDelayed(this.f1868x, j2);
            } else {
                post(this.f1868x);
            }
        }
    }

    private void m2777a(Context context, T t) {
        this.f1855k = new FrameLayout(context);
        this.f1855k.addView(t, -1, -1);
        m2791a(this.f1855k, new LayoutParams(-1, -1));
    }

    private void m2778b(Context context, AttributeSet attributeSet) {
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.HVGA /*2*/:
                setOrientation(0);
                break;
            default:
                setOrientation(1);
                break;
        }
        setGravity(17);
        this.f1846b = ViewConfiguration.get(context).getScaledTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.PullToRefresh);
        if (obtainStyledAttributes.hasValue(4)) {
            this.f1853i = C0096b.m2842a(obtainStyledAttributes.getInteger(4, 0));
        }
        if (obtainStyledAttributes.hasValue(12)) {
            this.f1862r = C0095a.m2838a(obtainStyledAttributes.getInteger(12, 0));
        }
        this.f1845a = m2781a(context, attributeSet);
        m2777a(context, this.f1845a);
        this.f1863s = m2783a(context, C0096b.PULL_FROM_START, obtainStyledAttributes);
        this.f1864t = m2783a(context, C0096b.PULL_FROM_END, obtainStyledAttributes);
        Drawable drawable;
        if (obtainStyledAttributes.hasValue(0)) {
            drawable = obtainStyledAttributes.getDrawable(0);
            if (drawable != null) {
                this.f1845a.setBackgroundDrawable(drawable);
            }
        } else if (obtainStyledAttributes.hasValue(16)) {
            Utils.m2917a("ptrAdapterViewBackground", "ptrRefreshableViewBackground");
            drawable = obtainStyledAttributes.getDrawable(16);
            if (drawable != null) {
                this.f1845a.setBackgroundDrawable(drawable);
            }
        }
        if (obtainStyledAttributes.hasValue(9)) {
            this.f1859o = obtainStyledAttributes.getBoolean(9, true);
        }
        if (obtainStyledAttributes.hasValue(13)) {
            this.f1857m = obtainStyledAttributes.getBoolean(13, false);
        }
        m2788a(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
        m2801f();
    }

    private void m2780g() {
        if (this.f1865u != null) {
            this.f1865u.m2488a(this);
        } else if (this.f1866v == null) {
        } else {
            if (this.f1854j == C0096b.PULL_FROM_START) {
                this.f1866v.m2849a(this);
            } else if (this.f1854j == C0096b.PULL_FROM_END) {
                this.f1866v.m2850b(this);
            }
        }
    }

    protected abstract T m2781a(Context context, AttributeSet attributeSet);

    public final ILoadingLayout m2782a(boolean z, boolean z2) {
        return m2794b(z, z2);
    }

    protected LoadingLayout m2783a(Context context, C0096b c0096b, TypedArray typedArray) {
        LoadingLayout a = this.f1862r.m2840a(context, c0096b, m2812r(), typedArray);
        a.setVisibility(4);
        return a;
    }

    protected void m2784a() {
        switch (m2769C()[this.f1854j.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                this.f1863s.m2889i();
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f1864t.m2889i();
            default:
        }
    }

    protected final void m2785a(int i) {
        int G = m2773G();
        G = Math.min(G, Math.max(-G, i));
        if (this.f1860p) {
            if (G < 0) {
                this.f1863s.setVisibility(0);
            } else if (G > 0) {
                this.f1864t.setVisibility(0);
            } else {
                this.f1863s.setVisibility(4);
                this.f1864t.setVisibility(4);
            }
        }
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.CIF /*1*/:
                scrollTo(0, G);
            case VideoSize.HVGA /*2*/:
                scrollTo(G, 0);
            default:
        }
    }

    protected final void m2786a(int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) this.f1855k.getLayoutParams();
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.CIF /*1*/:
                if (layoutParams.height != i2) {
                    layoutParams.height = i2;
                    this.f1855k.requestLayout();
                }
            case VideoSize.HVGA /*2*/:
                if (layoutParams.width != i) {
                    layoutParams.width = i;
                    this.f1855k.requestLayout();
                }
            default:
        }
    }

    protected final void m2787a(int i, C0100g c0100g) {
        m2776a(i, (long) m2818x(), 0, c0100g);
    }

    protected void m2788a(TypedArray typedArray) {
    }

    protected void m2789a(Bundle bundle) {
    }

    protected final void m2790a(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
    }

    protected final void m2791a(View view, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, -1, layoutParams);
    }

    final void m2792a(C0103j c0103j, boolean... zArr) {
        this.f1852h = c0103j;
        switch (m2768B()[this.f1852h.ordinal()]) {
            case VideoSize.CIF /*1*/:
                m2798c();
                break;
            case VideoSize.HVGA /*2*/:
                m2784a();
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                m2795b();
                break;
            case Version.API04_DONUT_16 /*4*/:
            case Version.API05_ECLAIR_20 /*5*/:
                m2793a(zArr[0]);
                break;
        }
        if (this.f1867w != null) {
            this.f1867w.m2848a(this, this.f1852h, this.f1854j);
        }
    }

    protected void m2793a(boolean z) {
        if (this.f1853i.m2844c()) {
            this.f1863s.m2890j();
        }
        if (this.f1853i.m2845d()) {
            this.f1864t.m2890j();
        }
        if (!z) {
            m2780g();
        } else if (this.f1856l) {
            C0100g pullToRefreshBase = new PullToRefreshBase(this);
            switch (m2769C()[this.f1854j.ordinal()]) {
                case Version.API03_CUPCAKE_15 /*3*/:
                case Version.API05_ECLAIR_20 /*5*/:
                    m2787a(m2815u(), pullToRefreshBase);
                default:
                    m2787a(-m2817w(), pullToRefreshBase);
            }
        } else {
            m2796b(0);
        }
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        View k = m2805k();
        if (k instanceof ViewGroup) {
            ((ViewGroup) k).addView(view, i, layoutParams);
            return;
        }
        throw new UnsupportedOperationException("Refreshable View is not a ViewGroup so can't addView");
    }

    protected LoadingLayoutProxy m2794b(boolean z, boolean z2) {
        LoadingLayoutProxy loadingLayoutProxy = new LoadingLayoutProxy();
        if (z && this.f1853i.m2844c()) {
            loadingLayoutProxy.m2865a(this.f1863s);
        }
        if (z2 && this.f1853i.m2845d()) {
            loadingLayoutProxy.m2865a(this.f1864t);
        }
        return loadingLayoutProxy;
    }

    protected void m2795b() {
        switch (m2769C()[this.f1854j.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                this.f1863s.m2891k();
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f1864t.m2891k();
            default:
        }
    }

    protected final void m2796b(int i) {
        m2775a(i, (long) m2818x());
    }

    protected void m2797b(Bundle bundle) {
    }

    protected void m2798c() {
        this.f1851g = false;
        this.f1860p = true;
        this.f1863s.m2892l();
        this.f1864t.m2892l();
        m2796b(0);
    }

    protected abstract boolean m2799d();

    protected abstract boolean m2800e();

    protected void m2801f() {
        ViewGroup.LayoutParams F = m2772F();
        if (this == this.f1863s.getParent()) {
            removeView(this.f1863s);
        }
        if (this.f1853i.m2844c()) {
            m2790a(this.f1863s, 0, F);
        }
        if (this == this.f1864t.getParent()) {
            removeView(this.f1864t);
        }
        if (this.f1853i.m2845d()) {
            m2791a(this.f1864t, F);
        }
        m2820z();
        this.f1854j = this.f1853i != C0096b.BOTH ? this.f1853i : C0096b.PULL_FROM_START;
    }

    public final C0096b m2802h() {
        return this.f1854j;
    }

    public final ILoadingLayout m2803i() {
        return m2782a(true, true);
    }

    public final C0096b m2804j() {
        return this.f1853i;
    }

    public final T m2805k() {
        return this.f1845a;
    }

    public final boolean m2806l() {
        return this.f1856l;
    }

    public final C0103j m2807m() {
        return this.f1852h;
    }

    public final boolean m2808n() {
        return this.f1853i.m2843b();
    }

    public final boolean m2809o() {
        return VERSION.SDK_INT >= 9 && this.f1859o && OverscrollHelper.m2869a(this.f1845a);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!m2808n()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 3 || action == 1) {
            this.f1851g = false;
            return false;
        } else if (action != 0 && this.f1851g) {
            return true;
        } else {
            float y;
            switch (action) {
                case VideoSize.QCIF /*0*/:
                    if (m2770D()) {
                        y = motionEvent.getY();
                        this.f1850f = y;
                        this.f1848d = y;
                        y = motionEvent.getX();
                        this.f1849e = y;
                        this.f1847c = y;
                        this.f1851g = false;
                        break;
                    }
                    break;
                case VideoSize.HVGA /*2*/:
                    if (this.f1857m || !m2810p()) {
                        if (m2770D()) {
                            float y2 = motionEvent.getY();
                            float x = motionEvent.getX();
                            float f;
                            switch (m2767A()[m2812r().ordinal()]) {
                                case VideoSize.HVGA /*2*/:
                                    y = x - this.f1847c;
                                    f = y2 - this.f1848d;
                                    break;
                                default:
                                    y = y2 - this.f1848d;
                                    f = x - this.f1847c;
                                    break;
                            }
                            float abs = Math.abs(y);
                            if (abs > ((float) this.f1846b) && (!this.f1858n || abs > Math.abs(r0))) {
                                if (!this.f1853i.m2844c() || y < 1.0f || !m2799d()) {
                                    if (this.f1853i.m2845d() && y <= -1.0f && m2800e()) {
                                        this.f1848d = y2;
                                        this.f1847c = x;
                                        this.f1851g = true;
                                        if (this.f1853i == C0096b.BOTH) {
                                            this.f1854j = C0096b.PULL_FROM_END;
                                            break;
                                        }
                                    }
                                }
                                this.f1848d = y2;
                                this.f1847c = x;
                                this.f1851g = true;
                                if (this.f1853i == C0096b.BOTH) {
                                    this.f1854j = C0096b.PULL_FROM_START;
                                    break;
                                }
                            }
                        }
                    }
                    return true;
                    break;
            }
            return this.f1851g;
        }
    }

    protected final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            setMode(C0096b.m2842a(bundle.getInt("ptr_mode", 0)));
            this.f1854j = C0096b.m2842a(bundle.getInt("ptr_current_mode", 0));
            this.f1857m = bundle.getBoolean("ptr_disable_scrolling", false);
            this.f1856l = bundle.getBoolean("ptr_show_refreshing_view", true);
            super.onRestoreInstanceState(bundle.getParcelable("ptr_super"));
            C0103j a = C0103j.m2853a(bundle.getInt("ptr_state", 0));
            if (a == C0103j.REFRESHING || a == C0103j.MANUAL_REFRESHING) {
                m2792a(a, true);
            }
            m2789a(bundle);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected final Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        m2797b(bundle);
        bundle.putInt("ptr_state", this.f1852h.m2854a());
        bundle.putInt("ptr_mode", this.f1853i.m2846e());
        bundle.putInt("ptr_current_mode", this.f1854j.m2846e());
        bundle.putBoolean("ptr_disable_scrolling", this.f1857m);
        bundle.putBoolean("ptr_show_refreshing_view", this.f1856l);
        bundle.putParcelable("ptr_super", super.onSaveInstanceState());
        return bundle;
    }

    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m2820z();
        m2786a(i, i2);
        post(new PullToRefreshBase(this));
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!m2808n()) {
            return false;
        }
        if (!this.f1857m && m2810p()) {
            return true;
        }
        if (motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case VideoSize.QCIF /*0*/:
                if (!m2770D()) {
                    return false;
                }
                float y = motionEvent.getY();
                this.f1850f = y;
                this.f1848d = y;
                y = motionEvent.getX();
                this.f1849e = y;
                this.f1847c = y;
                return true;
            case VideoSize.CIF /*1*/:
            case Version.API03_CUPCAKE_15 /*3*/:
                if (!this.f1851g) {
                    return false;
                }
                this.f1851g = false;
                if (this.f1852h == C0103j.RELEASE_TO_REFRESH && (this.f1865u != null || this.f1866v != null)) {
                    m2792a(C0103j.REFRESHING, true);
                    return true;
                } else if (m2810p()) {
                    m2796b(0);
                    return true;
                } else {
                    m2792a(C0103j.RESET, new boolean[0]);
                    return true;
                }
            case VideoSize.HVGA /*2*/:
                if (!this.f1851g) {
                    return false;
                }
                this.f1848d = motionEvent.getY();
                this.f1847c = motionEvent.getX();
                m2771E();
                return true;
            default:
                return false;
        }
    }

    public final boolean m2810p() {
        return this.f1852h == C0103j.REFRESHING || this.f1852h == C0103j.MANUAL_REFRESHING;
    }

    public final void m2811q() {
        if (m2810p()) {
            m2792a(C0103j.RESET, new boolean[0]);
        }
    }

    public abstract C0101h m2812r();

    protected final void m2813s() {
        this.f1860p = false;
    }

    public void setDisableScrollingWhileRefreshing(boolean z) {
        setScrollingWhileRefreshingEnabled(!z);
    }

    public final void setFilterTouchEvents(boolean z) {
        this.f1858n = z;
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        m2803i().setLastUpdatedLabel(charSequence);
    }

    public void setLoadingDrawable(Drawable drawable) {
        m2803i().setLoadingDrawable(drawable);
    }

    public void setLoadingDrawable(Drawable drawable, C0096b c0096b) {
        m2782a(c0096b.m2844c(), c0096b.m2845d()).setLoadingDrawable(drawable);
    }

    public void setLongClickable(boolean z) {
        m2805k().setLongClickable(z);
    }

    public final void setMode(C0096b c0096b) {
        if (c0096b != this.f1853i) {
            this.f1853i = c0096b;
            m2801f();
        }
    }

    public void setOnPullEventListener(C0098d<T> c0098d) {
        this.f1867w = c0098d;
    }

    public final void setOnRefreshListener(C0094e<T> c0094e) {
        this.f1865u = c0094e;
        this.f1866v = null;
    }

    public final void setOnRefreshListener(C0099f<T> c0099f) {
        this.f1866v = c0099f;
        this.f1865u = null;
    }

    public void setPullLabel(CharSequence charSequence) {
        m2803i().setPullLabel(charSequence);
    }

    public void setPullLabel(CharSequence charSequence, C0096b c0096b) {
        m2782a(c0096b.m2844c(), c0096b.m2845d()).setPullLabel(charSequence);
    }

    public final void setPullToRefreshEnabled(boolean z) {
        setMode(z ? C0096b.m2841a() : C0096b.DISABLED);
    }

    public final void setPullToRefreshOverScrollEnabled(boolean z) {
        this.f1859o = z;
    }

    public final void setRefreshing() {
        setRefreshing(true);
    }

    public final void setRefreshing(boolean z) {
        if (!m2810p()) {
            m2792a(C0103j.MANUAL_REFRESHING, z);
        }
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        m2803i().setRefreshingLabel(charSequence);
    }

    public void setRefreshingLabel(CharSequence charSequence, C0096b c0096b) {
        m2782a(c0096b.m2844c(), c0096b.m2845d()).setRefreshingLabel(charSequence);
    }

    public void setReleaseLabel(CharSequence charSequence) {
        setReleaseLabel(charSequence, C0096b.BOTH);
    }

    public void setReleaseLabel(CharSequence charSequence, C0096b c0096b) {
        m2782a(c0096b.m2844c(), c0096b.m2845d()).setReleaseLabel(charSequence);
    }

    public void setScrollAnimationInterpolator(Interpolator interpolator) {
        this.f1861q = interpolator;
    }

    public final void setScrollingWhileRefreshingEnabled(boolean z) {
        this.f1857m = z;
    }

    public final void setShowViewWhileRefreshing(boolean z) {
        this.f1856l = z;
    }

    protected final LoadingLayout m2814t() {
        return this.f1864t;
    }

    protected final int m2815u() {
        return this.f1864t.m2887g();
    }

    protected final LoadingLayout m2816v() {
        return this.f1863s;
    }

    protected final int m2817w() {
        return this.f1863s.m2887g();
    }

    protected int m2818x() {
        return HttpStatus.SC_OK;
    }

    protected FrameLayout m2819y() {
        return this.f1855k;
    }

    protected final void m2820z() {
        int i;
        int i2 = 0;
        int G = (int) (((float) m2773G()) * 1.2f);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        switch (m2767A()[m2812r().ordinal()]) {
            case VideoSize.CIF /*1*/:
                if (this.f1853i.m2844c()) {
                    this.f1863s.setHeight(G);
                    i = -G;
                } else {
                    i = 0;
                }
                if (!this.f1853i.m2845d()) {
                    paddingBottom = i;
                    i = paddingRight;
                    paddingRight = paddingLeft;
                    break;
                }
                this.f1864t.setHeight(G);
                i2 = -G;
                paddingBottom = i;
                i = paddingRight;
                paddingRight = paddingLeft;
                break;
            case VideoSize.HVGA /*2*/:
                if (this.f1853i.m2844c()) {
                    this.f1863s.setWidth(G);
                    i = -G;
                } else {
                    i = 0;
                }
                if (!this.f1853i.m2845d()) {
                    paddingRight = i;
                    i = 0;
                    i2 = paddingBottom;
                    paddingBottom = paddingTop;
                    break;
                }
                this.f1864t.setWidth(G);
                paddingRight = i;
                i = -G;
                i2 = paddingBottom;
                paddingBottom = paddingTop;
                break;
            default:
                i2 = paddingBottom;
                i = paddingRight;
                paddingBottom = paddingTop;
                paddingRight = paddingLeft;
                break;
        }
        setPadding(paddingRight, paddingBottom, i, i2);
    }
}
