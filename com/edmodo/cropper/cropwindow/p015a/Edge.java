package com.edmodo.cropper.cropwindow.p015a;

import android.graphics.Rect;
import com.edmodo.cropper.p014a.AspectRatioUtil;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: com.edmodo.cropper.cropwindow.a.a */
public enum Edge {
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;
    
    private float f1468e;

    private static float m2260a(float f, Rect rect, float f2, float f3) {
        float f4 = Float.POSITIVE_INFINITY;
        if (f - ((float) rect.left) < f2) {
            return (float) rect.left;
        }
        float a = f >= RIGHT.m2268a() - 40.0f ? RIGHT.m2268a() - 40.0f : Float.POSITIVE_INFINITY;
        if ((RIGHT.m2268a() - f) / f3 <= 40.0f) {
            f4 = RIGHT.m2268a() - (40.0f * f3);
        }
        return Math.min(f, Math.min(a, f4));
    }

    private boolean m2261a(float f, float f2, float f3, float f4, Rect rect) {
        return f < ((float) rect.top) || f2 < ((float) rect.left) || f3 > ((float) rect.bottom) || f4 > ((float) rect.right);
    }

    public static float m2262b() {
        return RIGHT.m2268a() - LEFT.m2268a();
    }

    private static float m2263b(float f, Rect rect, float f2, float f3) {
        float f4 = Float.NEGATIVE_INFINITY;
        if (((float) rect.right) - f < f2) {
            return (float) rect.right;
        }
        float a = f <= LEFT.m2268a() + 40.0f ? LEFT.m2268a() + 40.0f : Float.NEGATIVE_INFINITY;
        if ((f - LEFT.m2268a()) / f3 <= 40.0f) {
            f4 = LEFT.m2268a() + (40.0f * f3);
        }
        return Math.max(f, Math.max(a, f4));
    }

    public static float m2264c() {
        return BOTTOM.m2268a() - TOP.m2268a();
    }

    private static float m2265c(float f, Rect rect, float f2, float f3) {
        float f4 = Float.POSITIVE_INFINITY;
        if (f - ((float) rect.top) < f2) {
            return (float) rect.top;
        }
        float a = f >= BOTTOM.m2268a() - 40.0f ? BOTTOM.m2268a() - 40.0f : Float.POSITIVE_INFINITY;
        if ((BOTTOM.m2268a() - f) * f3 <= 40.0f) {
            f4 = BOTTOM.m2268a() - (40.0f / f3);
        }
        return Math.min(f, Math.min(a, f4));
    }

    private static float m2266d(float f, Rect rect, float f2, float f3) {
        float f4 = Float.NEGATIVE_INFINITY;
        if (((float) rect.bottom) - f < f2) {
            return (float) rect.bottom;
        }
        float a = f <= TOP.m2268a() + 40.0f ? TOP.m2268a() + 40.0f : Float.NEGATIVE_INFINITY;
        if ((f - TOP.m2268a()) * f3 <= 40.0f) {
            f4 = TOP.m2268a() + (40.0f / f3);
        }
        return Math.max(f, Math.max(f4, a));
    }

    public float m2268a() {
        return this.f1468e;
    }

    public float m2269a(Rect rect) {
        float f = this.f1468e;
        switch (Edge.m2267d()[ordinal()]) {
            case VideoSize.CIF /*1*/:
                this.f1468e = (float) rect.left;
                break;
            case VideoSize.HVGA /*2*/:
                this.f1468e = (float) rect.top;
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f1468e = (float) rect.right;
                break;
            case Version.API04_DONUT_16 /*4*/:
                this.f1468e = (float) rect.bottom;
                break;
        }
        return this.f1468e - f;
    }

    public void m2270a(float f) {
        this.f1468e = f;
    }

    public void m2271a(float f, float f2, Rect rect, float f3, float f4) {
        switch (Edge.m2267d()[ordinal()]) {
            case VideoSize.CIF /*1*/:
                this.f1468e = Edge.m2260a(f, rect, f3, f4);
            case VideoSize.HVGA /*2*/:
                this.f1468e = Edge.m2265c(f2, rect, f3, f4);
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f1468e = Edge.m2263b(f, rect, f3, f4);
            case Version.API04_DONUT_16 /*4*/:
                this.f1468e = Edge.m2266d(f2, rect, f3, f4);
            default:
        }
    }

    public boolean m2272a(Rect rect, float f) {
        switch (Edge.m2267d()[ordinal()]) {
            case VideoSize.CIF /*1*/:
                return this.f1468e - ((float) rect.left) < f;
            case VideoSize.HVGA /*2*/:
                return this.f1468e - ((float) rect.top) < f;
            case Version.API03_CUPCAKE_15 /*3*/:
                return ((float) rect.right) - this.f1468e < f;
            case Version.API04_DONUT_16 /*4*/:
                return ((float) rect.bottom) - this.f1468e < f;
            default:
                return false;
        }
    }

    public boolean m2273a(Edge edge, Rect rect, float f) {
        float b = edge.m2274b(rect);
        float f2;
        float a;
        float a2;
        float f3;
        switch (Edge.m2267d()[ordinal()]) {
            case VideoSize.CIF /*1*/:
                if (edge.equals(TOP)) {
                    f2 = (float) rect.top;
                    a = BOTTOM.m2268a() - b;
                    a2 = RIGHT.m2268a();
                    return m2261a(f2, AspectRatioUtil.m2228b(f2, a2, a, f), a, a2, rect);
                } else if (edge.equals(BOTTOM)) {
                    a = (float) rect.bottom;
                    f2 = TOP.m2268a() - b;
                    a2 = RIGHT.m2268a();
                    return m2261a(f2, AspectRatioUtil.m2228b(f2, a2, a, f), a, a2, rect);
                }
                break;
            case VideoSize.HVGA /*2*/:
                if (edge.equals(LEFT)) {
                    f3 = (float) rect.left;
                    a2 = RIGHT.m2268a() - b;
                    a = BOTTOM.m2268a();
                    return m2261a(AspectRatioUtil.m2229c(f3, a2, a, f), f3, a, a2, rect);
                } else if (edge.equals(RIGHT)) {
                    a2 = (float) rect.right;
                    f3 = LEFT.m2268a() - b;
                    a = BOTTOM.m2268a();
                    return m2261a(AspectRatioUtil.m2229c(f3, a2, a, f), f3, a, a2, rect);
                }
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                if (edge.equals(TOP)) {
                    f2 = (float) rect.top;
                    a = BOTTOM.m2268a() - b;
                    f3 = LEFT.m2268a();
                    return m2261a(f2, f3, a, AspectRatioUtil.m2230d(f3, f2, a, f), rect);
                } else if (edge.equals(BOTTOM)) {
                    a = (float) rect.bottom;
                    f2 = TOP.m2268a() - b;
                    f3 = LEFT.m2268a();
                    return m2261a(f2, f3, a, AspectRatioUtil.m2230d(f3, f2, a, f), rect);
                }
                break;
            case Version.API04_DONUT_16 /*4*/:
                if (edge.equals(LEFT)) {
                    f3 = (float) rect.left;
                    a2 = RIGHT.m2268a() - b;
                    f2 = TOP.m2268a();
                    return m2261a(f2, f3, AspectRatioUtil.m2231e(f3, f2, a2, f), a2, rect);
                } else if (edge.equals(RIGHT)) {
                    a2 = (float) rect.right;
                    f3 = LEFT.m2268a() - b;
                    f2 = TOP.m2268a();
                    return m2261a(f2, f3, AspectRatioUtil.m2231e(f3, f2, a2, f), a2, rect);
                }
                break;
        }
        return true;
    }

    public float m2274b(Rect rect) {
        float f;
        float f2 = this.f1468e;
        switch (Edge.m2267d()[ordinal()]) {
            case VideoSize.CIF /*1*/:
                f = (float) rect.left;
                break;
            case VideoSize.HVGA /*2*/:
                f = (float) rect.top;
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                f = (float) rect.right;
                break;
            case Version.API04_DONUT_16 /*4*/:
                f = (float) rect.bottom;
                break;
            default:
                f = f2;
                break;
        }
        return f - f2;
    }

    public void m2275b(float f) {
        this.f1468e += f;
    }

    public void m2276c(float f) {
        float a = LEFT.m2268a();
        float a2 = TOP.m2268a();
        float a3 = RIGHT.m2268a();
        float a4 = BOTTOM.m2268a();
        switch (Edge.m2267d()[ordinal()]) {
            case VideoSize.CIF /*1*/:
                this.f1468e = AspectRatioUtil.m2228b(a2, a3, a4, f);
            case VideoSize.HVGA /*2*/:
                this.f1468e = AspectRatioUtil.m2229c(a, a3, a4, f);
            case Version.API03_CUPCAKE_15 /*3*/:
                this.f1468e = AspectRatioUtil.m2230d(a, a2, a4, f);
            case Version.API04_DONUT_16 /*4*/:
                this.f1468e = AspectRatioUtil.m2231e(a, a2, a3, f);
            default:
        }
    }
}
