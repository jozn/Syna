package org.catrobat.paintroid.dialog.colorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class CheckeredTransparentLinearLayout extends LinearLayout {
    private Paint f4437a;

    public CheckeredTransparentLinearLayout(Context context) {
        super(context);
        this.f4437a = new Paint();
    }

    public CheckeredTransparentLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4437a = new Paint();
    }

    public void m5730a() {
        if (getWidth() != 0 && getHeight() != 0) {
            Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
            createBitmap.eraseColor(ColorPickerDialog.f4466a);
            Canvas canvas = new Canvas(createBitmap);
            Rect rect = new Rect(0, 0, getWidth(), getHeight());
            if (ColorPickerDialog.f4467b != null) {
                canvas.drawRect(rect, ColorPickerDialog.f4467b);
            }
            this.f4437a.setColor(ColorPickerDialog.f4466a);
            canvas.drawPaint(this.f4437a);
            setBackgroundDrawable(new BitmapDrawable(getResources(), createBitmap));
        }
    }
}
