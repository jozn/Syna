package android.support.v4.view;

import android.os.Build.VERSION;
import android.view.MotionEvent;

/* renamed from: android.support.v4.view.q */
public class MotionEventCompat {
    static final MotionEventCompat f563a;

    /* renamed from: android.support.v4.view.q.c */
    interface MotionEventCompat {
        int m1052a(MotionEvent motionEvent);

        int m1053a(MotionEvent motionEvent, int i);

        int m1054b(MotionEvent motionEvent, int i);

        float m1055c(MotionEvent motionEvent, int i);

        float m1056d(MotionEvent motionEvent, int i);
    }

    /* renamed from: android.support.v4.view.q.a */
    static class MotionEventCompat implements MotionEventCompat {
        MotionEventCompat() {
        }

        public int m1057a(MotionEvent motionEvent) {
            return 1;
        }

        public int m1058a(MotionEvent motionEvent, int i) {
            return i == 0 ? 0 : -1;
        }

        public int m1059b(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return 0;
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float m1060c(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getX();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }

        public float m1061d(MotionEvent motionEvent, int i) {
            if (i == 0) {
                return motionEvent.getY();
            }
            throw new IndexOutOfBoundsException("Pre-Eclair does not support multiple pointers");
        }
    }

    /* renamed from: android.support.v4.view.q.b */
    static class MotionEventCompat implements MotionEventCompat {
        MotionEventCompat() {
        }

        public int m1062a(MotionEvent motionEvent) {
            return MotionEventCompatEclair.m1074a(motionEvent);
        }

        public int m1063a(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m1075a(motionEvent, i);
        }

        public int m1064b(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m1076b(motionEvent, i);
        }

        public float m1065c(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m1077c(motionEvent, i);
        }

        public float m1066d(MotionEvent motionEvent, int i) {
            return MotionEventCompatEclair.m1078d(motionEvent, i);
        }
    }

    static {
        if (VERSION.SDK_INT >= 5) {
            f563a = new MotionEventCompat();
        } else {
            f563a = new MotionEventCompat();
        }
    }

    public static int m1067a(MotionEvent motionEvent) {
        return motionEvent.getAction() & 255;
    }

    public static int m1068a(MotionEvent motionEvent, int i) {
        return f563a.m1053a(motionEvent, i);
    }

    public static int m1069b(MotionEvent motionEvent) {
        return (motionEvent.getAction() & 65280) >> 8;
    }

    public static int m1070b(MotionEvent motionEvent, int i) {
        return f563a.m1054b(motionEvent, i);
    }

    public static float m1071c(MotionEvent motionEvent, int i) {
        return f563a.m1055c(motionEvent, i);
    }

    public static int m1072c(MotionEvent motionEvent) {
        return f563a.m1052a(motionEvent);
    }

    public static float m1073d(MotionEvent motionEvent, int i) {
        return f563a.m1056d(motionEvent, i);
    }
}
