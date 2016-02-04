package android.support.v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.EdgeEffect;

/* renamed from: android.support.v4.widget.i */
class EdgeEffectCompatIcs {
    public static Object m1344a(Context context) {
        return new EdgeEffect(context);
    }

    public static void m1345a(Object obj, int i, int i2) {
        ((EdgeEffect) obj).setSize(i, i2);
    }

    public static boolean m1346a(Object obj) {
        return ((EdgeEffect) obj).isFinished();
    }

    public static boolean m1347a(Object obj, float f) {
        ((EdgeEffect) obj).onPull(f);
        return true;
    }

    public static boolean m1348a(Object obj, Canvas canvas) {
        return ((EdgeEffect) obj).draw(canvas);
    }

    public static void m1349b(Object obj) {
        ((EdgeEffect) obj).finish();
    }

    public static boolean m1350c(Object obj) {
        EdgeEffect edgeEffect = (EdgeEffect) obj;
        edgeEffect.onRelease();
        return edgeEffect.isFinished();
    }
}
