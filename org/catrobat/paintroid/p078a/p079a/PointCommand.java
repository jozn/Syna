package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;

/* renamed from: org.catrobat.paintroid.a.a.i */
public class PointCommand extends BaseCommand {
    protected PointF f4227d;

    public PointCommand(Paint paint, PointF pointF) {
        super(paint);
        if (pointF != null) {
            this.f4227d = new PointF(pointF.x, pointF.y);
        }
    }

    public void m5470a(Canvas canvas, Bitmap bitmap) {
        if (canvas == null || this.f4227d == null) {
            Log.w("PAINTROID", "Object must not be null in PointCommand.");
        } else {
            canvas.drawPoint(this.f4227d.x, this.f4227d.y, this.a);
        }
    }
}
