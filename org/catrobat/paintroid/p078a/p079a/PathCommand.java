package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;

/* renamed from: org.catrobat.paintroid.a.a.h */
public class PathCommand extends BaseCommand {
    protected Path f4226d;

    public PathCommand(Paint paint, Path path) {
        super(paint);
        if (path != null) {
            this.f4226d = new Path(path);
        }
    }

    private boolean m5468a(RectF rectF, Rect rect) {
        RectF rectF2 = new RectF(rect);
        float strokeWidth = this.a.getStrokeWidth();
        rectF.bottom += strokeWidth / 2.0f;
        rectF.left -= strokeWidth / 2.0f;
        rectF.right += strokeWidth / 2.0f;
        rectF.top -= strokeWidth / 2.0f;
        return RectF.intersects(rectF2, rectF);
    }

    public void m5469a(Canvas canvas, Bitmap bitmap) {
        if (canvas == null || this.f4226d == null) {
            Log.w("PAINTROID", "Object must not be null in PathCommand.");
            return;
        }
        RectF rectF = new RectF();
        this.f4226d.computeBounds(rectF, true);
        Rect clipBounds = canvas.getClipBounds();
        if (clipBounds == null) {
            m5441a(BaseCommand.COMMAND_FAILED);
        } else if (m5468a(rectF, clipBounds)) {
            canvas.drawPath(this.f4226d, this.a);
        } else {
            m5441a(BaseCommand.COMMAND_FAILED);
        }
    }
}
