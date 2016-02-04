package android.support.v4.view;

import android.os.Build.VERSION;

/* renamed from: android.support.v4.view.i */
public class GravityCompat {
    static final GravityCompat f560a;

    /* renamed from: android.support.v4.view.i.a */
    interface GravityCompat {
        int m993a(int i, int i2);
    }

    /* renamed from: android.support.v4.view.i.b */
    static class GravityCompat implements GravityCompat {
        GravityCompat() {
        }

        public int m994a(int i, int i2) {
            return -8388609 & i;
        }
    }

    /* renamed from: android.support.v4.view.i.c */
    static class GravityCompat implements GravityCompat {
        GravityCompat() {
        }

        public int m995a(int i, int i2) {
            return GravityCompatJellybeanMr1.m997a(i, i2);
        }
    }

    static {
        if (VERSION.SDK_INT >= 17) {
            f560a = new GravityCompat();
        } else {
            f560a = new GravityCompat();
        }
    }

    public static int m996a(int i, int i2) {
        return f560a.m993a(i, i2);
    }
}
