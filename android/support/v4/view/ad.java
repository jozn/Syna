package android.support.v4.view;

import android.view.View;
import android.view.ViewParent;

/* compiled from: ViewCompatJB */
class ad {
    public static void m912a(View view) {
        view.postInvalidateOnAnimation();
    }

    public static void m913a(View view, int i) {
        view.setImportantForAccessibility(i);
    }

    public static void m914a(View view, int i, int i2, int i3, int i4) {
        view.postInvalidate(i, i2, i3, i4);
    }

    public static void m915a(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    public static int m916b(View view) {
        return view.getImportantForAccessibility();
    }

    public static ViewParent m917c(View view) {
        return view.getParentForAccessibility();
    }
}
