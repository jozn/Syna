package android.support.v4.view.p009a;

import android.os.Build.VERSION;

/* renamed from: android.support.v4.view.a.l */
public class AccessibilityRecordCompat {
    private static final AccessibilityRecordCompat f546a;
    private final Object f547b;

    /* renamed from: android.support.v4.view.a.l.c */
    interface AccessibilityRecordCompat {
        Object m880a();

        void m881a(Object obj, int i);

        void m882a(Object obj, boolean z);

        void m883b(Object obj, int i);

        void m884c(Object obj, int i);
    }

    /* renamed from: android.support.v4.view.a.l.e */
    static class AccessibilityRecordCompat implements AccessibilityRecordCompat {
        AccessibilityRecordCompat() {
        }

        public Object m885a() {
            return null;
        }

        public void m886a(Object obj, int i) {
        }

        public void m887a(Object obj, boolean z) {
        }

        public void m888b(Object obj, int i) {
        }

        public void m889c(Object obj, int i) {
        }
    }

    /* renamed from: android.support.v4.view.a.l.a */
    static class AccessibilityRecordCompat extends AccessibilityRecordCompat {
        AccessibilityRecordCompat() {
        }

        public Object m890a() {
            return AccessibilityRecordCompatIcs.m900a();
        }

        public void m891a(Object obj, int i) {
            AccessibilityRecordCompatIcs.m901a(obj, i);
        }

        public void m892a(Object obj, boolean z) {
            AccessibilityRecordCompatIcs.m902a(obj, z);
        }

        public void m893b(Object obj, int i) {
            AccessibilityRecordCompatIcs.m903b(obj, i);
        }

        public void m894c(Object obj, int i) {
            AccessibilityRecordCompatIcs.m904c(obj, i);
        }
    }

    /* renamed from: android.support.v4.view.a.l.b */
    static class AccessibilityRecordCompat extends AccessibilityRecordCompat {
        AccessibilityRecordCompat() {
        }
    }

    /* renamed from: android.support.v4.view.a.l.d */
    static class AccessibilityRecordCompat extends AccessibilityRecordCompat {
        AccessibilityRecordCompat() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f546a = new AccessibilityRecordCompat();
        } else if (VERSION.SDK_INT >= 15) {
            f546a = new AccessibilityRecordCompat();
        } else if (VERSION.SDK_INT >= 14) {
            f546a = new AccessibilityRecordCompat();
        } else {
            f546a = new AccessibilityRecordCompat();
        }
    }

    public AccessibilityRecordCompat(Object obj) {
        this.f547b = obj;
    }

    public static AccessibilityRecordCompat m895a() {
        return new AccessibilityRecordCompat(f546a.m880a());
    }

    public void m896a(int i) {
        f546a.m883b(this.f547b, i);
    }

    public void m897a(boolean z) {
        f546a.m882a(this.f547b, z);
    }

    public void m898b(int i) {
        f546a.m881a(this.f547b, i);
    }

    public void m899c(int i) {
        f546a.m884c(this.f547b, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
        return this.f547b == null ? accessibilityRecordCompat.f547b == null : this.f547b.equals(accessibilityRecordCompat.f547b);
    }

    public int hashCode() {
        return this.f547b == null ? 0 : this.f547b.hashCode();
    }
}
