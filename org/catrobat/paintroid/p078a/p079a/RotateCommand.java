package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;
import org.linphone.core.VideoSize;

/* renamed from: org.catrobat.paintroid.a.a.j */
public class RotateCommand extends BaseCommand {
    private static /* synthetic */ int[] f4231e;
    private RotateCommand f4232d;

    /* renamed from: org.catrobat.paintroid.a.a.j.a */
    public enum RotateCommand {
        ROTATE_LEFT,
        ROTATE_RIGHT
    }

    public RotateCommand(RotateCommand rotateCommand) {
        this.f4232d = rotateCommand;
    }

    static /* synthetic */ int[] m5471c() {
        int[] iArr = f4231e;
        if (iArr == null) {
            iArr = new int[RotateCommand.values().length];
            try {
                iArr[RotateCommand.ROTATE_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RotateCommand.ROTATE_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            f4231e = iArr;
        }
        return iArr;
    }

    public void m5472a(Canvas canvas, Bitmap bitmap) {
        setChanged();
        m5441a(BaseCommand.COMMAND_STARTED);
        if (this.f4232d == null) {
            setChanged();
            m5441a(BaseCommand.COMMAND_FAILED);
            return;
        }
        Matrix matrix = new Matrix();
        switch (RotateCommand.m5471c()[this.f4232d.ordinal()]) {
            case VideoSize.CIF /*1*/:
                matrix.postRotate(-90.0f);
                Log.i("PAINTROID", "rotate left");
                break;
            case VideoSize.HVGA /*2*/:
                matrix.postRotate(90.0f);
                Log.i("PAINTROID", "rotate right");
                break;
            default:
                setChanged();
                m5441a(BaseCommand.COMMAND_FAILED);
                return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        new Canvas(createBitmap).drawBitmap(bitmap, matrix, new Paint());
        if (PaintroidApplication.f4192b != null) {
            PaintroidApplication.f4192b.setBitmap(createBitmap);
        }
        setChanged();
        PaintroidApplication.f4195e.m5794a();
        m5441a(BaseCommand.COMMAND_DONE);
    }
}
