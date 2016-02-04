package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p081c.C0139d;
import org.catrobat.paintroid.p081c.ToolType;

/* renamed from: org.catrobat.paintroid.c.b.e */
public abstract class BaseToolWithShape extends BaseTool implements C0139d {
    protected PointF f4324A;
    protected Paint f4325B;
    protected int f4326y;
    protected int f4327z;

    public BaseToolWithShape(Context context, ToolType toolType) {
        super(context, toolType);
        this.f4326y = PaintroidApplication.f4191a.getResources().getColor(R.rectangle_primary_color);
        this.f4327z = PaintroidApplication.f4191a.getResources().getColor(R.rectangle_secondary_color);
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        this.f4324A = new PointF(((float) defaultDisplay.getWidth()) / 2.0f, (((float) defaultDisplay.getHeight()) / 2.0f) - (displayMetrics.density * 50.0f));
        PaintroidApplication.f4195e.m5798a(this.f4324A);
        this.f4325B = new Paint();
        this.f4325B.setColor(this.f4326y);
    }

    protected float m5536a(float f) {
        return (this.f.getResources().getDisplayMetrics().density * f) / PaintroidApplication.f4195e.m5801b();
    }

    protected float m5537a(float f, float f2, float f3) {
        float b = (this.f.getResources().getDisplayMetrics().density * f) / PaintroidApplication.f4195e.m5801b();
        return b < f2 ? f2 : b > f3 ? f3 : b;
    }

    public Point m5538a(float f, float f2, int i, int i2) {
        int i3 = 1;
        int i4 = -1;
        PointF d = PaintroidApplication.f4195e.m5806d(new PointF(this.f4324A.x, this.f4324A.y));
        int i5 = d.x < ((float) i) ? 1 : 0;
        if (d.x > ((float) (i - i))) {
            i5 = -1;
        }
        if (d.y >= ((float) i)) {
            i3 = 0;
        }
        if (d.y <= ((float) (i2 - i))) {
            i4 = i3;
        }
        return new Point(i5, i4);
    }
}
