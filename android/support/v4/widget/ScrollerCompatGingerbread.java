package android.support.v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/* renamed from: android.support.v4.widget.l */
class ScrollerCompatGingerbread {
    public static Object m1389a(Context context, Interpolator interpolator) {
        return interpolator != null ? new OverScroller(context, interpolator) : new OverScroller(context);
    }

    public static void m1390a(Object obj, int i, int i2, int i3, int i4, int i5) {
        ((OverScroller) obj).startScroll(i, i2, i3, i4, i5);
    }

    public static boolean m1391a(Object obj) {
        return ((OverScroller) obj).isFinished();
    }

    public static int m1392b(Object obj) {
        return ((OverScroller) obj).getCurrX();
    }

    public static int m1393c(Object obj) {
        return ((OverScroller) obj).getCurrY();
    }

    public static boolean m1394d(Object obj) {
        return ((OverScroller) obj).computeScrollOffset();
    }

    public static void m1395e(Object obj) {
        ((OverScroller) obj).abortAnimation();
    }

    public static int m1396f(Object obj) {
        return ((OverScroller) obj).getFinalX();
    }

    public static int m1397g(Object obj) {
        return ((OverScroller) obj).getFinalY();
    }
}
