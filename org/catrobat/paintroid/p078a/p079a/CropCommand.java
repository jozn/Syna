package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.util.Log;
import org.catrobat.paintroid.FileIO;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;

/* renamed from: org.catrobat.paintroid.a.a.e */
public class CropCommand extends BaseCommand {
    private final int f4216d;
    private final int f4217e;
    private final int f4218f;
    private final int f4219g;

    public CropCommand(int i, int i2, int i3, int i4) {
        this.f4216d = i;
        this.f4217e = i2;
        this.f4218f = i3;
        this.f4219g = i4;
    }

    public void m5464a(Canvas canvas, Bitmap bitmap) {
        m5441a(BaseCommand.COMMAND_STARTED);
        if (this.c != null) {
            PaintroidApplication.f4192b.setBitmap(FileIO.m5479a(this.c));
            m5441a(BaseCommand.COMMAND_DONE);
            return;
        }
        try {
            if (this.f4218f < this.f4216d) {
                Log.e("PAINTROID", "coordinate X left is larger than coordinate X right");
                m5441a(BaseCommand.COMMAND_FAILED);
            } else if (this.f4218f < 0 || this.f4216d < 0 || this.f4218f > bitmap.getWidth() || this.f4216d > bitmap.getWidth()) {
                Log.e("PAINTROID", "coordinate X is out of bitmap scope");
                m5441a(BaseCommand.COMMAND_FAILED);
            } else if (this.f4219g < this.f4217e) {
                Log.e("PAINTROID", "coordinate Y bottom is smaller than coordinate Y top");
                m5441a(BaseCommand.COMMAND_FAILED);
            } else if (this.f4219g < 0 || this.f4217e < 0 || this.f4219g > bitmap.getHeight() || this.f4217e > bitmap.getHeight()) {
                Log.e("PAINTROID", "coordinate Y is out of bitmap scope");
                m5441a(BaseCommand.COMMAND_FAILED);
            } else if (this.f4216d > 0 || this.f4218f != bitmap.getWidth() || this.f4219g != bitmap.getHeight() || this.f4217e > 0) {
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, this.f4216d, this.f4217e, this.f4218f - this.f4216d, this.f4219g - this.f4217e);
                PaintroidApplication.f4192b.setBitmap(createBitmap);
                if (this.c == null) {
                    this.b = createBitmap.copy(Config.ARGB_8888, true);
                    m5442b();
                }
                m5441a(BaseCommand.COMMAND_DONE);
            } else {
                Log.e("PAINTROID", " no need to crop ");
                m5441a(BaseCommand.COMMAND_FAILED);
            }
        } catch (Exception e) {
            Log.e("PAINTROID", "failed to crop bitmap:" + e.getMessage());
            m5441a(BaseCommand.COMMAND_FAILED);
        }
    }
}
