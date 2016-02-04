package org.catrobat.paintroid.dialog.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View.MeasureSpec;
import android.widget.Button;

@SuppressLint({"ViewConstructor"})
public class ColorPickerPresetColorButton extends Button {
    private Paint f4438a;
    private int f4439b;
    private int f4440c;

    public ColorPickerPresetColorButton(Context context, int i) {
        super(context);
        this.f4438a = new Paint();
        this.f4439b = 0;
        this.f4440c = 0;
        this.f4438a.setColor(i);
        this.f4439b = getWidth();
        this.f4440c = getHeight();
    }

    public void draw(Canvas canvas) {
        Rect rect = new Rect(0, 0, this.f4439b, this.f4440c);
        if (ColorPickerDialog.f4467b != null) {
            canvas.drawRect(rect, ColorPickerDialog.f4467b);
        }
        canvas.drawRect(rect, this.f4438a);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f4439b = MeasureSpec.getSize(i);
        this.f4440c = MeasureSpec.getSize(i2);
    }
}
