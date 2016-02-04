package android.support.v4.widget;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/* renamed from: android.support.v4.widget.k */
public class ScrollerCompat {
    static final ScrollerCompat f693b;
    Object f694a;

    /* renamed from: android.support.v4.widget.k.a */
    interface ScrollerCompat {
        Object m1353a(Context context, Interpolator interpolator);

        void m1354a(Object obj, int i, int i2, int i3, int i4, int i5);

        boolean m1355a(Object obj);

        int m1356b(Object obj);

        int m1357c(Object obj);

        boolean m1358d(Object obj);

        void m1359e(Object obj);

        int m1360f(Object obj);

        int m1361g(Object obj);
    }

    /* renamed from: android.support.v4.widget.k.b */
    static class ScrollerCompat implements ScrollerCompat {
        ScrollerCompat() {
        }

        public Object m1362a(Context context, Interpolator interpolator) {
            return interpolator != null ? new Scroller(context, interpolator) : new Scroller(context);
        }

        public void m1363a(Object obj, int i, int i2, int i3, int i4, int i5) {
            ((Scroller) obj).startScroll(i, i2, i3, i4, i5);
        }

        public boolean m1364a(Object obj) {
            return ((Scroller) obj).isFinished();
        }

        public int m1365b(Object obj) {
            return ((Scroller) obj).getCurrX();
        }

        public int m1366c(Object obj) {
            return ((Scroller) obj).getCurrY();
        }

        public boolean m1367d(Object obj) {
            return ((Scroller) obj).computeScrollOffset();
        }

        public void m1368e(Object obj) {
            ((Scroller) obj).abortAnimation();
        }

        public int m1369f(Object obj) {
            return ((Scroller) obj).getFinalX();
        }

        public int m1370g(Object obj) {
            return ((Scroller) obj).getFinalY();
        }
    }

    /* renamed from: android.support.v4.widget.k.c */
    static class ScrollerCompat implements ScrollerCompat {
        ScrollerCompat() {
        }

        public Object m1371a(Context context, Interpolator interpolator) {
            return ScrollerCompatGingerbread.m1389a(context, interpolator);
        }

        public void m1372a(Object obj, int i, int i2, int i3, int i4, int i5) {
            ScrollerCompatGingerbread.m1390a(obj, i, i2, i3, i4, i5);
        }

        public boolean m1373a(Object obj) {
            return ScrollerCompatGingerbread.m1391a(obj);
        }

        public int m1374b(Object obj) {
            return ScrollerCompatGingerbread.m1392b(obj);
        }

        public int m1375c(Object obj) {
            return ScrollerCompatGingerbread.m1393c(obj);
        }

        public boolean m1376d(Object obj) {
            return ScrollerCompatGingerbread.m1394d(obj);
        }

        public void m1377e(Object obj) {
            ScrollerCompatGingerbread.m1395e(obj);
        }

        public int m1378f(Object obj) {
            return ScrollerCompatGingerbread.m1396f(obj);
        }

        public int m1379g(Object obj) {
            return ScrollerCompatGingerbread.m1397g(obj);
        }
    }

    /* renamed from: android.support.v4.widget.k.d */
    static class ScrollerCompat extends ScrollerCompat {
        ScrollerCompat() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 14) {
            f693b = new ScrollerCompat();
        } else if (i >= 9) {
            f693b = new ScrollerCompat();
        } else {
            f693b = new ScrollerCompat();
        }
    }

    ScrollerCompat(Context context, Interpolator interpolator) {
        this.f694a = f693b.m1353a(context, interpolator);
    }

    public static ScrollerCompat m1380a(Context context, Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }

    public void m1381a(int i, int i2, int i3, int i4, int i5) {
        f693b.m1354a(this.f694a, i, i2, i3, i4, i5);
    }

    public boolean m1382a() {
        return f693b.m1355a(this.f694a);
    }

    public int m1383b() {
        return f693b.m1356b(this.f694a);
    }

    public int m1384c() {
        return f693b.m1357c(this.f694a);
    }

    public int m1385d() {
        return f693b.m1360f(this.f694a);
    }

    public int m1386e() {
        return f693b.m1361g(this.f694a);
    }

    public boolean m1387f() {
        return f693b.m1358d(this.f694a);
    }

    public void m1388g() {
        f693b.m1359e(this.f694a);
    }
}
