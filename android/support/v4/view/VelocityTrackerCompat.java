package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.VelocityTracker;

/* renamed from: android.support.v4.view.w */
public class VelocityTrackerCompat {
    static final VelocityTrackerCompat f567a;

    /* renamed from: android.support.v4.view.w.c */
    interface VelocityTrackerCompat {
        float m1080a(VelocityTracker velocityTracker, int i);

        float m1081b(VelocityTracker velocityTracker, int i);
    }

    /* renamed from: android.support.v4.view.w.a */
    static class VelocityTrackerCompat implements VelocityTrackerCompat {
        VelocityTrackerCompat() {
        }

        public float m1082a(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getXVelocity();
        }

        public float m1083b(VelocityTracker velocityTracker, int i) {
            return velocityTracker.getYVelocity();
        }
    }

    /* renamed from: android.support.v4.view.w.b */
    static class VelocityTrackerCompat implements VelocityTrackerCompat {
        VelocityTrackerCompat() {
        }

        public float m1084a(VelocityTracker velocityTracker, int i) {
            return VelocityTrackerCompatHoneycomb.m1088a(velocityTracker, i);
        }

        public float m1085b(VelocityTracker velocityTracker, int i) {
            return VelocityTrackerCompatHoneycomb.m1089b(velocityTracker, i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f567a = new VelocityTrackerCompat();
        } else {
            f567a = new VelocityTrackerCompat();
        }
    }

    public static float m1086a(VelocityTracker velocityTracker, int i) {
        return f567a.m1080a(velocityTracker, i);
    }

    public static float m1087b(VelocityTracker velocityTracker, int i) {
        return f567a.m1081b(velocityTracker, i);
    }
}
