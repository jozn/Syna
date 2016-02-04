package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.ViewGroup;

/* compiled from: ViewGroupCompat */
public class ah {
    static final ViewGroupCompat f549a;

    /* renamed from: android.support.v4.view.ah.c */
    interface ViewGroupCompat {
        void m925a(ViewGroup viewGroup, boolean z);
    }

    /* renamed from: android.support.v4.view.ah.e */
    static class ViewGroupCompat implements ViewGroupCompat {
        ViewGroupCompat() {
        }

        public void m926a(ViewGroup viewGroup, boolean z) {
        }
    }

    /* renamed from: android.support.v4.view.ah.a */
    static class ViewGroupCompat extends ViewGroupCompat {
        ViewGroupCompat() {
        }

        public void m927a(ViewGroup viewGroup, boolean z) {
            ai.m929a(viewGroup, z);
        }
    }

    /* renamed from: android.support.v4.view.ah.b */
    static class ViewGroupCompat extends ViewGroupCompat {
        ViewGroupCompat() {
        }
    }

    /* renamed from: android.support.v4.view.ah.d */
    static class ViewGroupCompat extends ViewGroupCompat {
        ViewGroupCompat() {
        }
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 18) {
            f549a = new ViewGroupCompat();
        } else if (i >= 14) {
            f549a = new ViewGroupCompat();
        } else if (i >= 11) {
            f549a = new ViewGroupCompat();
        } else {
            f549a = new ViewGroupCompat();
        }
    }

    public static void m928a(ViewGroup viewGroup, boolean z) {
        f549a.m925a(viewGroup, z);
    }
}
