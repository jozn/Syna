package android.support.v4.view;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewParent;

/* renamed from: android.support.v4.view.y */
public class ViewCompat {
    static final ViewCompat f568a;

    /* renamed from: android.support.v4.view.y.i */
    interface ViewCompat {
        int m1090a(View view);

        void m1091a(View view, int i, int i2, int i3, int i4);

        void m1092a(View view, int i, Paint paint);

        void m1093a(View view, Paint paint);

        void m1094a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat);

        void m1095a(View view, Runnable runnable);

        boolean m1096a(View view, int i);

        void m1097b(View view);

        boolean m1098b(View view, int i);

        int m1099c(View view);

        void m1100c(View view, int i);

        int m1101d(View view);

        int m1102e(View view);

        ViewParent m1103f(View view);

        boolean m1104g(View view);
    }

    /* renamed from: android.support.v4.view.y.a */
    static class ViewCompat implements ViewCompat {
        ViewCompat() {
        }

        public int m1105a(View view) {
            return 2;
        }

        long m1106a() {
            return 10;
        }

        public void m1107a(View view, int i, int i2, int i3, int i4) {
            view.postInvalidateDelayed(m1106a(), i, i2, i3, i4);
        }

        public void m1108a(View view, int i, Paint paint) {
        }

        public void m1109a(View view, Paint paint) {
        }

        public void m1110a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        }

        public void m1111a(View view, Runnable runnable) {
            view.postDelayed(runnable, m1106a());
        }

        public boolean m1112a(View view, int i) {
            return false;
        }

        public void m1113b(View view) {
            view.postInvalidateDelayed(m1106a());
        }

        public boolean m1114b(View view, int i) {
            return false;
        }

        public int m1115c(View view) {
            return 0;
        }

        public void m1116c(View view, int i) {
        }

        public int m1117d(View view) {
            return 0;
        }

        public int m1118e(View view) {
            return 0;
        }

        public ViewParent m1119f(View view) {
            return view.getParent();
        }

        public boolean m1120g(View view) {
            Drawable background = view.getBackground();
            return background != null && background.getOpacity() == -1;
        }
    }

    /* renamed from: android.support.v4.view.y.b */
    static class ViewCompat extends ViewCompat {
        ViewCompat() {
        }

        public boolean m1121g(View view) {
            return ViewCompatEclairMr1.m1153a(view);
        }
    }

    /* renamed from: android.support.v4.view.y.c */
    static class ViewCompat extends ViewCompat {
        ViewCompat() {
        }

        public int m1122a(View view) {
            return aa.m905a(view);
        }
    }

    /* renamed from: android.support.v4.view.y.d */
    static class ViewCompat extends ViewCompat {
        ViewCompat() {
        }

        long m1123a() {
            return ab.m907a();
        }

        public void m1124a(View view, int i, Paint paint) {
            ab.m908a(view, i, paint);
        }

        public void m1125a(View view, Paint paint) {
            m1124a(view, m1126d(view), paint);
            view.invalidate();
        }

        public int m1126d(View view) {
            return ab.m906a(view);
        }
    }

    /* renamed from: android.support.v4.view.y.e */
    static class ViewCompat extends ViewCompat {
        ViewCompat() {
        }

        public void m1127a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
            ac.m909a(view, accessibilityDelegateCompat.m540a());
        }

        public boolean m1128a(View view, int i) {
            return ac.m910a(view, i);
        }

        public boolean m1129b(View view, int i) {
            return ac.m911b(view, i);
        }
    }

    /* renamed from: android.support.v4.view.y.f */
    static class ViewCompat extends ViewCompat {
        ViewCompat() {
        }

        public void m1130a(View view, int i, int i2, int i3, int i4) {
            ad.m914a(view, i, i2, i3, i4);
        }

        public void m1131a(View view, Runnable runnable) {
            ad.m915a(view, runnable);
        }

        public void m1132b(View view) {
            ad.m912a(view);
        }

        public int m1133c(View view) {
            return ad.m916b(view);
        }

        public void m1134c(View view, int i) {
            ad.m913a(view, i);
        }

        public ViewParent m1135f(View view) {
            return ad.m917c(view);
        }
    }

    /* renamed from: android.support.v4.view.y.g */
    static class ViewCompat extends ViewCompat {
        ViewCompat() {
        }

        public void m1136a(View view, Paint paint) {
            ae.m919a(view, paint);
        }

        public int m1137e(View view) {
            return ae.m918a(view);
        }
    }

    /* renamed from: android.support.v4.view.y.h */
    static class ViewCompat extends ViewCompat {
        ViewCompat() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 19) {
            f568a = new ViewCompat();
        } else if (i >= 17) {
            f568a = new ViewCompat();
        } else if (i >= 16) {
            f568a = new ViewCompat();
        } else if (i >= 14) {
            f568a = new ViewCompat();
        } else if (i >= 11) {
            f568a = new ViewCompat();
        } else if (i >= 9) {
            f568a = new ViewCompat();
        } else {
            f568a = new ViewCompat();
        }
    }

    public static int m1138a(View view) {
        return f568a.m1090a(view);
    }

    public static void m1139a(View view, int i, int i2, int i3, int i4) {
        f568a.m1091a(view, i, i2, i3, i4);
    }

    public static void m1140a(View view, int i, Paint paint) {
        f568a.m1092a(view, i, paint);
    }

    public static void m1141a(View view, Paint paint) {
        f568a.m1093a(view, paint);
    }

    public static void m1142a(View view, AccessibilityDelegateCompat accessibilityDelegateCompat) {
        f568a.m1094a(view, accessibilityDelegateCompat);
    }

    public static void m1143a(View view, Runnable runnable) {
        f568a.m1095a(view, runnable);
    }

    public static boolean m1144a(View view, int i) {
        return f568a.m1096a(view, i);
    }

    public static void m1145b(View view) {
        f568a.m1097b(view);
    }

    public static boolean m1146b(View view, int i) {
        return f568a.m1098b(view, i);
    }

    public static int m1147c(View view) {
        return f568a.m1099c(view);
    }

    public static void m1148c(View view, int i) {
        f568a.m1100c(view, i);
    }

    public static int m1149d(View view) {
        return f568a.m1101d(view);
    }

    public static int m1150e(View view) {
        return f568a.m1102e(view);
    }

    public static ViewParent m1151f(View view) {
        return f568a.m1103f(view);
    }

    public static boolean m1152g(View view) {
        return f568a.m1104g(view);
    }
}
