package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.KeyEvent;

/* renamed from: android.support.v4.view.k */
public class KeyEventCompat {
    static final KeyEventCompat f561a;

    /* renamed from: android.support.v4.view.k.d */
    interface KeyEventCompat {
        void m998a(KeyEvent keyEvent);

        boolean m999a(int i, int i2);

        boolean m1000b(int i);
    }

    /* renamed from: android.support.v4.view.k.a */
    static class KeyEventCompat implements KeyEventCompat {
        KeyEventCompat() {
        }

        private static int m1001a(int i, int i2, int i3, int i4, int i5) {
            Object obj = 1;
            Object obj2 = (i2 & i3) != 0 ? 1 : null;
            int i6 = i4 | i5;
            if ((i2 & i6) == 0) {
                obj = null;
            }
            if (obj2 == null) {
                return obj != null ? i & (i3 ^ -1) : i;
            } else {
                if (obj == null) {
                    return i & (i6 ^ -1);
                }
                throw new IllegalArgumentException("bad arguments");
            }
        }

        public int m1002a(int i) {
            int i2 = (i & 192) != 0 ? i | 1 : i;
            if ((i2 & 48) != 0) {
                i2 |= 2;
            }
            return i2 & 247;
        }

        public void m1003a(KeyEvent keyEvent) {
        }

        public boolean m1004a(int i, int i2) {
            return KeyEventCompat.m1001a(KeyEventCompat.m1001a(m1002a(i) & 247, i2, 1, 64, 128), i2, 2, 16, 32) == i2;
        }

        public boolean m1005b(int i) {
            return (m1002a(i) & 247) == 0;
        }
    }

    /* renamed from: android.support.v4.view.k.b */
    static class KeyEventCompat extends KeyEventCompat {
        KeyEventCompat() {
        }

        public void m1006a(KeyEvent keyEvent) {
            KeyEventCompatEclair.m1013a(keyEvent);
        }
    }

    /* renamed from: android.support.v4.view.k.c */
    static class KeyEventCompat extends KeyEventCompat {
        KeyEventCompat() {
        }

        public int m1007a(int i) {
            return KeyEventCompatHoneycomb.m1014a(i);
        }

        public boolean m1008a(int i, int i2) {
            return KeyEventCompatHoneycomb.m1015a(i, i2);
        }

        public boolean m1009b(int i) {
            return KeyEventCompatHoneycomb.m1016b(i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f561a = new KeyEventCompat();
        } else {
            f561a = new KeyEventCompat();
        }
    }

    public static boolean m1010a(KeyEvent keyEvent) {
        return f561a.m1000b(keyEvent.getMetaState());
    }

    public static boolean m1011a(KeyEvent keyEvent, int i) {
        return f561a.m999a(keyEvent.getMetaState(), i);
    }

    public static void m1012b(KeyEvent keyEvent) {
        f561a.m998a(keyEvent);
    }
}
