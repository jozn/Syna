package com.edmodo.cropper.p014a;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.TypedValue;

/* renamed from: com.edmodo.cropper.a.d */
public class PaintUtil {
    private static final int f1438a;

    static {
        f1438a = Color.parseColor("#FFF8A61C");
    }

    public static Paint m2244a() {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#AAFFFFFF"));
        paint.setStrokeWidth(1.0f);
        return paint;
    }

    public static Paint m2245a(Context context) {
        float applyDimension = TypedValue.applyDimension(1, 3.0f, context.getResources().getDisplayMetrics());
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#AAFFFFFF"));
        paint.setStrokeWidth(applyDimension);
        paint.setStyle(Style.STROKE);
        return paint;
    }

    public static float m2246b() {
        return 5.0f;
    }

    public static Paint m2247b(Context context) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#B0000000"));
        return paint;
    }

    public static float m2248c() {
        return 3.0f;
    }

    public static Paint m2249c(Context context) {
        float applyDimension = TypedValue.applyDimension(1, 5.0f, context.getResources().getDisplayMetrics());
        Paint paint = new Paint();
        paint.setColor(f1438a);
        paint.setStrokeWidth(applyDimension);
        paint.setStyle(Style.STROKE);
        return paint;
    }
}
