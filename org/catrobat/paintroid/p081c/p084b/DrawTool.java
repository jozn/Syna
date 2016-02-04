package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p078a.p079a.PathCommand;
import org.catrobat.paintroid.p078a.p079a.PointCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.h */
public class DrawTool extends BaseTool {
    private static /* synthetic */ int[] f4374o;
    protected final Path f4375l;
    protected PointF f4376m;
    protected final PointF f4377n;

    public DrawTool(Context context, ToolType toolType) {
        super(context, toolType);
        this.f4375l = new Path();
        this.f4375l.incReserve(1);
        this.f4377n = new PointF(0.0f, 0.0f);
    }

    static /* synthetic */ int[] m5615i() {
        int[] iArr = f4374o;
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
            f4374o = iArr;
        }
        return iArr;
    }

    public int m5616a(TopBar topBar) {
        switch (DrawTool.m5615i()[topBar.ordinal()]) {
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

    public void m5617a(Canvas canvas) {
        m5524b(d.getColor());
        if (d.getColor() == 0) {
            d.setColor(-16777216);
            canvas.drawPath(this.f4375l, d);
            d.setColor(0);
            return;
        }
        canvas.drawPath(this.f4375l, c);
    }

    public boolean m5618a(PointF pointF) {
        if (pointF == null) {
            return false;
        }
        this.f4376m = new PointF(pointF.x, pointF.y);
        this.h = new PointF(pointF.x, pointF.y);
        this.f4375l.moveTo(pointF.x, pointF.y);
        this.f4377n.set(0.0f, 0.0f);
        return true;
    }

    public void m5619b(TopBar topBar) {
        switch (DrawTool.m5615i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
            case Version.API04_DONUT_16 /*4*/:
                m5526c();
            case Version.API03_CUPCAKE_15 /*3*/:
                m5527d();
            default:
        }
    }

    public boolean m5620b(PointF pointF) {
        if (this.f4376m == null || this.h == null || pointF == null) {
            return false;
        }
        this.f4375l.quadTo(this.h.x, this.h.y, pointF.x, pointF.y);
        this.f4375l.incReserve(1);
        this.f4377n.set(this.f4377n.x + Math.abs(pointF.x - this.h.x), this.f4377n.y + Math.abs(pointF.y - this.h.y));
        this.h.set(pointF.x, pointF.y);
        return true;
    }

    public boolean m5621c(PointF pointF) {
        if (this.f4376m == null || this.h == null || pointF == null) {
            return false;
        }
        this.f4377n.set(this.f4377n.x + Math.abs(pointF.x - this.h.x), this.f4377n.y + Math.abs(pointF.y - this.h.y));
        return (5.0f < this.f4377n.x || 5.0f < this.f4377n.y) ? m5622d(pointF) : m5623e(this.f4376m);
    }

    protected boolean m5622d(PointF pointF) {
        this.f4375l.lineTo(pointF.x, pointF.y);
        PaintroidApplication.f4193c.m5447a(new PathCommand(c, this.f4375l));
        return true;
    }

    protected boolean m5623e(PointF pointF) {
        PaintroidApplication.f4193c.m5447a(new PointCommand(c, pointF));
        return true;
    }

    public void m5624f() {
        this.f4375l.rewind();
        this.f4376m = null;
        this.h = null;
    }
}
