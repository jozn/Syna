package android.support.v4.view;

import android.view.KeyEvent;

/* renamed from: android.support.v4.view.m */
class KeyEventCompatHoneycomb {
    public static int m1014a(int i) {
        return KeyEvent.normalizeMetaState(i);
    }

    public static boolean m1015a(int i, int i2) {
        return KeyEvent.metaStateHasModifiers(i, i2);
    }

    public static boolean m1016b(int i) {
        return KeyEvent.metaStateHasNoModifiers(i);
    }
}
