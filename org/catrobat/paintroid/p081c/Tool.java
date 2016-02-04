package org.catrobat.paintroid.p081c;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Point;
import android.graphics.PointF;
import org.catrobat.paintroid.ui.TopBar.TopBar;

/* renamed from: org.catrobat.paintroid.c.a */
public interface Tool {

    /* renamed from: org.catrobat.paintroid.c.a.a */
    public enum Tool {
        ALL,
        RESET_INTERNAL_STATE,
        NEW_IMAGE_LOADED,
        MOVE_CANCELED
    }

    int m5501a(TopBar topBar);

    Paint m5502a();

    Point m5503a(float f, float f2, int i, int i2);

    void m5504a(int i);

    void m5505a(Canvas canvas);

    void m5506a(Cap cap);

    void m5507a(Paint paint);

    void m5508a(Tool tool);

    boolean m5509a(PointF pointF);

    ToolType m5510b();

    void m5511b(TopBar topBar);

    boolean m5512b(PointF pointF);

    boolean m5513c(PointF pointF);
}
