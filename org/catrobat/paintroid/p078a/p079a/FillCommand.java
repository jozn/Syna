package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;
import org.catrobat.paintroid.p081c.p082a.p083a.QueueLinearFloodFiller;

/* renamed from: org.catrobat.paintroid.a.a.f */
public class FillCommand extends BaseCommand {
    private Point f4220d;

    public FillCommand(Point point, Paint paint) {
        super(paint);
        this.f4220d = point;
    }

    public void m5465a(Canvas canvas, Bitmap bitmap) {
        m5441a(BaseCommand.COMMAND_STARTED);
        if (this.f4220d == null) {
            setChanged();
            m5441a(BaseCommand.COMMAND_FAILED);
            return;
        }
        if (PaintroidApplication.f4201k == null && PaintroidApplication.f4193c.m5453g() == 2) {
            canvas.drawColor(this.a.getColor());
            Log.w("PAINTROID", "Fill Command color: " + this.a.getColor());
        } else {
            int pixel = bitmap.getPixel(this.f4220d.x, this.f4220d.y);
            int[] iArr = new int[(bitmap.getWidth() * bitmap.getHeight())];
            bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            QueueLinearFloodFiller.m5498a(iArr, bitmap.getWidth(), bitmap.getHeight(), this.f4220d, pixel, this.a.getColor(), 50.0d);
            bitmap.setPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
        m5441a(BaseCommand.COMMAND_DONE);
    }
}
