package android.support.v4.view.p009a;

import android.view.accessibility.AccessibilityRecord;

/* renamed from: android.support.v4.view.a.m */
class AccessibilityRecordCompatIcs {
    public static Object m900a() {
        return AccessibilityRecord.obtain();
    }

    public static void m901a(Object obj, int i) {
        ((AccessibilityRecord) obj).setFromIndex(i);
    }

    public static void m902a(Object obj, boolean z) {
        ((AccessibilityRecord) obj).setScrollable(z);
    }

    public static void m903b(Object obj, int i) {
        ((AccessibilityRecord) obj).setItemCount(i);
    }

    public static void m904c(Object obj, int i) {
        ((AccessibilityRecord) obj).setToIndex(i);
    }
}
