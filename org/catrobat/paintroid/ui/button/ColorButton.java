package org.catrobat.paintroid.ui.button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageButton;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog.ColorPickerDialog;

public class ColorButton extends ImageButton implements ColorPickerDialog {
    private Paint f4523a;
    private Paint f4524b;
    private Paint f4525c;
    private Bitmap f4526d;
    private int f4527e;
    private int f4528f;

    public ColorButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m5790a(context);
    }

    private void m5790a(Context context) {
        this.f4523a = new Paint();
        this.f4525c = new Paint();
        this.f4524b = new Paint();
        this.f4524b.setColor(-3355444);
        this.f4526d = BitmapFactory.decodeResource(getResources(), R.checkeredbg);
        this.f4525c.setShader(new BitmapShader(this.f4526d, TileMode.REPEAT, TileMode.REPEAT));
        org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog.m5753a().m5760a((ColorPickerDialog) this);
    }

    public void m5791a(int i) {
        this.f4523a.setColor(i);
        invalidate();
    }

    public void draw(Canvas canvas) {
        int i = (this.f4528f / 2) - 25;
        int i2 = (this.f4527e / 2) - 25;
        Rect rect = new Rect(i, i2, i + 50, i2 + 50);
        canvas.drawRect(new Rect(rect.left - 2, rect.top - 2, rect.right + 2, rect.bottom + 2), this.f4524b);
        canvas.drawRect(rect, this.f4525c);
        canvas.drawRect(rect, this.f4523a);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f4528f = MeasureSpec.getSize(i);
        this.f4527e = MeasureSpec.getSize(i2);
    }
}
