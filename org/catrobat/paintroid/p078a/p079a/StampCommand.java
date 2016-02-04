package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import org.catrobat.paintroid.FileIO;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;

/* renamed from: org.catrobat.paintroid.a.a.k */
public class StampCommand extends BaseCommand {
    protected final Point f4233d;
    protected final float f4234e;
    protected final float f4235f;
    protected final float f4236g;
    protected final RectF f4237h;

    public StampCommand(Bitmap bitmap, Point point, float f, float f2, float f3) {
        super(new Paint(4));
        if (point != null) {
            this.f4233d = new Point(point.x, point.y);
        } else {
            this.f4233d = null;
        }
        if (bitmap != null) {
            this.b = bitmap.copy(Config.ARGB_8888, false);
        }
        this.f4234e = f;
        this.f4235f = f2;
        this.f4236g = f3;
        this.f4237h = new RectF((-this.f4234e) / 2.0f, (-this.f4235f) / 2.0f, this.f4234e / 2.0f, this.f4235f / 2.0f);
    }

    public void m5473a(Canvas canvas, Bitmap bitmap) {
        m5441a(BaseCommand.COMMAND_STARTED);
        if (this.c != null) {
            this.b = FileIO.m5479a(this.c);
        }
        if (this.b == null) {
            setChanged();
            m5441a(BaseCommand.COMMAND_FAILED);
            return;
        }
        canvas.save();
        canvas.translate((float) this.f4233d.x, (float) this.f4233d.y);
        canvas.rotate(this.f4236g);
        canvas.drawBitmap(this.b, null, this.f4237h, this.a);
        canvas.restore();
        if (this.c == null) {
            m5442b();
        } else {
            this.b.recycle();
            this.b = null;
        }
        m5441a(BaseCommand.COMMAND_DONE);
    }
}
