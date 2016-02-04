package com.edmodo.cropper.p014a;

import android.content.Context;
import android.util.Pair;
import android.util.TypedValue;
import com.edmodo.cropper.cropwindow.CropOverlayView;
import com.edmodo.cropper.cropwindow.p016b.Handle;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: com.edmodo.cropper.a.b */
public class HandleUtil {
    private static /* synthetic */ int[] f1437a;

    public static float m2232a(Context context) {
        return TypedValue.applyDimension(1, 24.0f, context.getResources().getDisplayMetrics());
    }

    public static Pair<Float, Float> m2233a(Handle handle, float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = 0.0f;
        if (handle == null) {
            return null;
        }
        float f8;
        switch (HandleUtil.m2237a()[handle.ordinal()]) {
            case VideoSize.CIF /*1*/:
                f8 = f3 - f;
                f7 = f4 - f2;
                break;
            case VideoSize.HVGA /*2*/:
                f8 = f5 - f;
                f7 = f4 - f2;
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                f8 = f3 - f;
                f7 = f6 - f2;
                break;
            case Version.API04_DONUT_16 /*4*/:
                f8 = f5 - f;
                f7 = f6 - f2;
                break;
            case Version.API05_ECLAIR_20 /*5*/:
                f8 = f3 - f;
                break;
            case Version.API06_ECLAIR_201 /*6*/:
                f8 = 0.0f;
                f7 = f4 - f2;
                break;
            case Version.API07_ECLAIR_21 /*7*/:
                f8 = f5 - f;
                break;
            case Version.API08_FROYO_22 /*8*/:
                f8 = 0.0f;
                f7 = f6 - f2;
                break;
            case Version.API09_GINGERBREAD_23 /*9*/:
                f8 = ((f5 + f3) / 2.0f) - f;
                f7 = ((f4 + f6) / 2.0f) - f2;
                break;
            default:
                f8 = 0.0f;
                break;
        }
        return new Pair(Float.valueOf(f8), Float.valueOf(f7));
    }

    public static Handle m2234a(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        return HandleUtil.m2235a(f, f2, f3, f4, f7) ? Handle.TOP_LEFT : HandleUtil.m2235a(f, f2, f5, f4, f7) ? Handle.TOP_RIGHT : HandleUtil.m2235a(f, f2, f3, f6, f7) ? Handle.BOTTOM_LEFT : HandleUtil.m2235a(f, f2, f5, f6, f7) ? Handle.BOTTOM_RIGHT : (HandleUtil.m2240c(f, f2, f3, f4, f5, f6) && HandleUtil.m2238b()) ? Handle.CENTER : HandleUtil.m2236a(f, f2, f3, f5, f4, f7) ? Handle.TOP : HandleUtil.m2236a(f, f2, f3, f5, f6, f7) ? Handle.BOTTOM : HandleUtil.m2239b(f, f2, f3, f4, f6, f7) ? Handle.LEFT : HandleUtil.m2239b(f, f2, f5, f4, f6, f7) ? Handle.RIGHT : (!HandleUtil.m2240c(f, f2, f3, f4, f5, f6) || HandleUtil.m2238b()) ? null : Handle.CENTER;
    }

    private static boolean m2235a(float f, float f2, float f3, float f4, float f5) {
        return Math.abs(f - f3) <= f5 && Math.abs(f2 - f4) <= f5;
    }

    private static boolean m2236a(float f, float f2, float f3, float f4, float f5, float f6) {
        return f > f3 && f < f4 && Math.abs(f2 - f5) <= f6;
    }

    static /* synthetic */ int[] m2237a() {
        int[] iArr = f1437a;
        if (iArr == null) {
            iArr = new int[Handle.values().length];
            try {
                iArr[Handle.BOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Handle.BOTTOM_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Handle.BOTTOM_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Handle.CENTER.ordinal()] = 9;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Handle.LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Handle.RIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[Handle.TOP.ordinal()] = 6;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[Handle.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[Handle.TOP_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            f1437a = iArr;
        }
        return iArr;
    }

    private static boolean m2238b() {
        return !CropOverlayView.m2257b();
    }

    private static boolean m2239b(float f, float f2, float f3, float f4, float f5, float f6) {
        return Math.abs(f - f3) <= f6 && f2 > f4 && f2 < f5;
    }

    private static boolean m2240c(float f, float f2, float f3, float f4, float f5, float f6) {
        return f > f3 && f < f5 && f2 > f4 && f2 < f6;
    }
}
