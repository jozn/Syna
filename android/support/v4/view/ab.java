package android.support.v4.view;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.View;

/* compiled from: ViewCompatHC */
class ab {
    public static int m906a(View view) {
        return view.getLayerType();
    }

    static long m907a() {
        return ValueAnimator.getFrameDelay();
    }

    public static void m908a(View view, int i, Paint paint) {
        view.setLayerType(i, paint);
    }
}
