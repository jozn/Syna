package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import org.catrobat.paintroid.FileIO;
import org.catrobat.paintroid.PaintroidApplication;

/* renamed from: org.catrobat.paintroid.a.a.b */
public class BitmapCommand extends BaseCommand {
    private boolean f4210d;

    public BitmapCommand(Bitmap bitmap) {
        this.f4210d = true;
        if (bitmap != null) {
            this.b = Bitmap.createBitmap(bitmap);
        }
    }

    public BitmapCommand(Bitmap bitmap, boolean z) {
        this(bitmap);
        this.f4210d = z;
    }

    public void m5443a(Canvas canvas, Bitmap bitmap) {
        if (this.b == null && this.c != null) {
            this.b = FileIO.m5479a(this.c);
        }
        if (this.b != null) {
            if (bitmap != null) {
                bitmap.eraseColor(0);
            }
            PaintroidApplication.f4192b.setBitmap(this.b.copy(Config.ARGB_8888, true));
            if (this.f4210d && PaintroidApplication.f4195e != null) {
                PaintroidApplication.f4195e.m5794a();
            }
            if (this.c == null) {
                m5442b();
            }
        }
    }
}
