package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p078a.p079a.PathCommand;
import org.catrobat.paintroid.p078a.p079a.PointCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.g */
public class CursorTool extends BaseToolWithShape {
    private static /* synthetic */ int[] f4369p;
    protected Path f4370l;
    private int f4371m;
    private int f4372n;
    private boolean f4373o;

    public CursorTool(Context context, ToolType toolType) {
        super(context, toolType);
        this.f4373o = false;
        this.f4370l = new Path();
        this.f4370l.incReserve(1);
        this.f4371m = PaintroidApplication.f4191a.getResources().getColor(R.cursor_tool_deactive_primary_color);
        this.f4372n = -3355444;
    }

    static /* synthetic */ int[] m5602i() {
        int[] iArr = f4369p;
        if (iArr == null) {
            iArr = new int[TopBar.values().length];
            try {
                iArr[TopBar.BUTTON_ID_PARAMETER_BOTTOM_1.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TopBar.BUTTON_ID_PARAMETER_BOTTOM_2.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TopBar.BUTTON_ID_PARAMETER_TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TopBar.BUTTON_ID_TOOL.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            f4369p = iArr;
        }
        return iArr;
    }

    private void m5603j() {
        if (this.f4373o) {
            if (5.0f < this.g.x || 5.0f < this.g.y) {
                m5612d(this.A);
                this.f4372n = c.getColor();
                return;
            }
            this.f4373o = false;
            this.f4372n = -3355444;
        } else if (5.0f >= this.g.x && 5.0f >= this.g.y) {
            this.f4373o = true;
            this.f4372n = c.getColor();
            m5613e(this.A);
        }
    }

    public int m5604a(TopBar topBar) {
        switch (CursorTool.m5602i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return m5528e();
            case Version.API03_CUPCAKE_15 /*3*/:
                return R.icon_menu_strokes;
            case Version.API04_DONUT_16 /*4*/:
                return R.icon_menu_color_palette;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5605a(Canvas canvas) {
        m5607b(d.getColor());
        if (this.f4373o) {
            if (d.getColor() == 0) {
                d.setColor(-16777216);
                canvas.drawPath(this.f4370l, d);
                d.setColor(0);
            } else {
                canvas.drawPath(this.f4370l, c);
            }
        }
        m5608b(canvas);
    }

    public boolean m5606a(PointF pointF) {
        this.f4370l.moveTo(this.A.x, this.A.y);
        this.h.set(pointF);
        this.g.set(0.0f, 0.0f);
        return true;
    }

    public void m5607b(int i) {
        super.m5524b(i);
        if (this.f4373o) {
            this.f4372n = c.getColor();
        }
    }

    public void m5608b(Canvas canvas) {
        float max = Math.max(c.getStrokeWidth() / 2.0f, 1.0f);
        float a = m5537a(5.0f, 1.0f, 10.0f);
        float f = a * 2.0f;
        max += a / 2.0f;
        float f2 = max + a;
        this.B.setColor(this.f4371m);
        this.B.setStyle(Style.STROKE);
        this.B.setStrokeWidth(a);
        if (c.getStrokeCap().equals(Cap.ROUND)) {
            canvas.drawCircle(this.A.x, this.A.y, f2, this.B);
            this.B.setColor(-3355444);
            canvas.drawCircle(this.A.x, this.A.y, max, this.B);
            this.B.setColor(0);
            this.B.setStyle(Style.FILL);
            canvas.drawCircle(this.A.x, this.A.y, max - (a / 2.0f), this.B);
        } else {
            RectF rectF = new RectF(this.A.x - f2, this.A.y - f2, this.A.x + f2, this.A.y + f2);
            canvas.drawRect(rectF, this.B);
            rectF.set(this.A.x - max, this.A.y - max, this.A.x + max, this.A.y + max);
            this.B.setColor(-3355444);
            canvas.drawRect(rectF, this.B);
            this.B.setColor(0);
            this.B.setStyle(Style.FILL);
            rectF.set((this.A.x - max) + (a / 2.0f), (this.A.y - max) + (a / 2.0f), (this.A.x + max) - (a / 2.0f), (max + this.A.y) - (a / 2.0f));
            canvas.drawRect(rectF, this.B);
        }
        this.B.setStyle(Style.FILL);
        int i = 0;
        float f3 = f + a;
        float f4 = a / 2.0f;
        while (i < 4) {
            if (i % 2 == 0) {
                this.B.setColor(this.f4372n);
            } else {
                this.B.setColor(this.f4371m);
            }
            canvas.drawLine((this.A.x - f2) - f4, this.A.y, (this.A.x - f2) - f3, this.A.y, this.B);
            canvas.drawLine((this.A.x + f2) + f4, this.A.y, (this.A.x + f2) + f3, this.A.y, this.B);
            canvas.drawLine(this.A.x, (this.A.y + f2) + f4, this.A.x, (this.A.y + f2) + f3, this.B);
            canvas.drawLine(this.A.x, (this.A.y - f2) - f4, this.A.x, (this.A.y - f2) - f3, this.B);
            int i2 = i + 1;
            i = i2;
            f3 = ((((float) i2) + 1.0f) * f) + a;
            f4 = (((float) i2) * f) + (a / 2.0f);
        }
    }

    public void m5609b(TopBar topBar) {
        switch (CursorTool.m5602i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
            case Version.API04_DONUT_16 /*4*/:
                m5526c();
            case Version.API03_CUPCAKE_15 /*3*/:
                m5527d();
            default:
        }
    }

    public boolean m5610b(PointF pointF) {
        float f;
        float f2 = this.A.x + (pointF.x - this.h.x);
        float f3 = (pointF.y - this.h.y) + this.A.y;
        PointF d = PaintroidApplication.f4195e.m5806d(new PointF(f2, f3));
        float width = (float) PaintroidApplication.f4192b.getWidth();
        float height = (float) PaintroidApplication.f4192b.getHeight();
        boolean z = false;
        if (d.x > width) {
            d.x = width;
            z = true;
        } else if (d.x < 0.0f) {
            d.x = 0.0f;
            z = true;
        }
        if (d.y > height) {
            d.y = height;
            z = true;
        } else if (d.y < 0.0f) {
            d.y = 0.0f;
            z = true;
        }
        if (z) {
            PointF b = PaintroidApplication.f4195e.m5802b(d);
            f3 = b.x;
            f = b.y;
        } else {
            f = f3;
            f3 = f2;
        }
        this.A.set(f3, f);
        if (this.f4373o) {
            this.f4370l.quadTo(this.A.x, this.A.y, (f3 + this.A.x) / 2.0f, (f + this.A.y) / 2.0f);
            this.f4370l.incReserve(1);
        }
        this.g.set(this.g.x + Math.abs(pointF.x - this.h.x), this.g.y + Math.abs(pointF.y - this.h.y));
        this.h.set(pointF.x, pointF.y);
        return true;
    }

    public boolean m5611c(PointF pointF) {
        this.g.set(this.g.x + Math.abs(pointF.x - this.h.x), this.g.y + Math.abs(pointF.y - this.h.y));
        m5603j();
        return true;
    }

    protected boolean m5612d(PointF pointF) {
        this.f4370l.lineTo(pointF.x, pointF.y);
        return PaintroidApplication.f4193c.m5447a(new PathCommand(c, this.f4370l));
    }

    protected boolean m5613e(PointF pointF) {
        return PaintroidApplication.f4193c.m5447a(new PointCommand(c, pointF));
    }

    public void m5614f() {
        this.f4370l.rewind();
    }
}
