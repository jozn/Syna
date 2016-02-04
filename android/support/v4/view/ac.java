package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;

/* compiled from: ViewCompatICS */
class ac {
    public static void m909a(View view, Object obj) {
        view.setAccessibilityDelegate((AccessibilityDelegate) obj);
    }

    public static boolean m910a(View view, int i) {
        return view.canScrollHorizontally(i);
    }

    public static boolean m911b(View view, int i) {
        return view.canScrollVertically(i);
    }
}
