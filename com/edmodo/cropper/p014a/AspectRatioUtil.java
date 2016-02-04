package com.edmodo.cropper.p014a;

import android.graphics.Rect;

/* renamed from: com.edmodo.cropper.a.a */
public class AspectRatioUtil {
    public static float m2224a(float f, float f2, float f3) {
        return (f2 - f) * f3;
    }

    public static float m2225a(float f, float f2, float f3, float f4) {
        return (f3 - f) / (f4 - f2);
    }

    public static float m2226a(Rect rect) {
        return ((float) rect.width()) / ((float) rect.height());
    }

    public static float m2227b(float f, float f2, float f3) {
        return (f2 - f) / f3;
    }

    public static float m2228b(float f, float f2, float f3, float f4) {
        return f2 - ((f3 - f) * f4);
    }

    public static float m2229c(float f, float f2, float f3, float f4) {
        return f3 - ((f2 - f) / f4);
    }

    public static float m2230d(float f, float f2, float f3, float f4) {
        return ((f3 - f2) * f4) + f;
    }

    public static float m2231e(float f, float f2, float f3, float f4) {
        return ((f3 - f) / f4) + f2;
    }
}
