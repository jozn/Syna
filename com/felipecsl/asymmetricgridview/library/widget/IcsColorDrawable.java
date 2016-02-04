package com.felipecsl.asymmetricgridview.library.widget;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

/* renamed from: com.felipecsl.asymmetricgridview.library.widget.f */
public class IcsColorDrawable extends Drawable {
    private int f1573a;
    private final Paint f1574b;

    public IcsColorDrawable(ColorDrawable colorDrawable) {
        this.f1574b = new Paint();
        Bitmap createBitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        colorDrawable.draw(new Canvas(createBitmap));
        this.f1573a = createBitmap.getPixel(0, 0);
        createBitmap.recycle();
    }

    public void draw(Canvas canvas) {
        if ((this.f1573a >>> 24) != 0) {
            this.f1574b.setColor(this.f1573a);
            canvas.drawRect(getBounds(), this.f1574b);
        }
    }

    public int getOpacity() {
        return this.f1573a >>> 24;
    }

    public void setAlpha(int i) {
        if (i != (this.f1573a >>> 24)) {
            this.f1573a = (this.f1573a & 16777215) | (i << 24);
            invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
