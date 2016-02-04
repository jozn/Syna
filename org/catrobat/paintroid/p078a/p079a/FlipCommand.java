package org.catrobat.paintroid.p078a.p079a;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;
import org.linphone.core.VideoSize;

/* renamed from: org.catrobat.paintroid.a.a.g */
public class FlipCommand extends BaseCommand {
    private static /* synthetic */ int[] f4224e;
    private FlipCommand f4225d;

    /* renamed from: org.catrobat.paintroid.a.a.g.a */
    public enum FlipCommand {
        FLIP_HORIZONTAL,
        FLIP_VERTICAL
    }

    public FlipCommand(FlipCommand flipCommand) {
        this.f4225d = flipCommand;
    }

    static /* synthetic */ int[] m5466c() {
        int[] iArr = f4224e;
        if (iArr == null) {
            iArr = new int[FlipCommand.values().length];
            try {
                iArr[FlipCommand.FLIP_HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[FlipCommand.FLIP_VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            f4224e = iArr;
        }
        return iArr;
    }

    public void m5467a(Canvas canvas, Bitmap bitmap) {
        m5441a(BaseCommand.COMMAND_STARTED);
        if (this.f4225d == null) {
            m5441a(BaseCommand.COMMAND_FAILED);
            return;
        }
        Matrix matrix = new Matrix();
        switch (FlipCommand.m5466c()[this.f4225d.ordinal()]) {
            case VideoSize.CIF /*1*/:
                matrix.setScale(1.0f, -1.0f);
                matrix.postTranslate(0.0f, (float) bitmap.getHeight());
                Log.i("PAINTROID", "flip horizontal");
                break;
            case VideoSize.HVGA /*2*/:
                matrix.setScale(-1.0f, 1.0f);
                matrix.postTranslate((float) bitmap.getWidth(), 0.0f);
                Log.i("PAINTROID", "flip vertical");
                break;
            default:
                m5441a(BaseCommand.COMMAND_FAILED);
                return;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        new Canvas(createBitmap).drawBitmap(bitmap, matrix, new Paint());
        if (PaintroidApplication.f4192b != null) {
            PaintroidApplication.f4192b.setBitmap(createBitmap);
        }
        m5441a(BaseCommand.COMMAND_DONE);
    }
}
