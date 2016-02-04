package android.support.v4.view;

import android.view.MotionEvent;

/* renamed from: android.support.v4.view.r */
class MotionEventCompatEclair {
    public static int m1074a(MotionEvent motionEvent) {
        return motionEvent.getPointerCount();
    }

    public static int m1075a(MotionEvent motionEvent, int i) {
        return motionEvent.findPointerIndex(i);
    }

    public static int m1076b(MotionEvent motionEvent, int i) {
        return motionEvent.getPointerId(i);
    }

    public static float m1077c(MotionEvent motionEvent, int i) {
        return motionEvent.getX(i);
    }

    public static float m1078d(MotionEvent motionEvent, int i) {
        return motionEvent.getY(i);
    }
}
