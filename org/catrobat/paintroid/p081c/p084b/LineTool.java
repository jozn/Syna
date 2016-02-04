package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p078a.p079a.PathCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.o */
public class LineTool extends BaseTool {
    private static /* synthetic */ int[] f4400n;
    protected PointF f4401l;
    protected PointF f4402m;

    public LineTool(Context context, ToolType toolType) {
        super(context, toolType);
    }

    static /* synthetic */ int[] m5677i() {
        int[] iArr = f4400n;
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
            f4400n = iArr;
        }
        return iArr;
    }

    public int m5678a(TopBar topBar) {
        switch (LineTool.m5677i()[topBar.ordinal()]) {
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

    public void m5679a(Canvas canvas) {
        if (this.f4401l != null && this.f4402m != null) {
            m5524b(d.getColor());
            if (d.getAlpha() == 0) {
                d.setColor(-16777216);
                canvas.drawLine(this.f4401l.x, this.f4401l.y, this.f4402m.x, this.f4402m.y, d);
                d.setColor(0);
                return;
            }
            canvas.drawLine(this.f4401l.x, this.f4401l.y, this.f4402m.x, this.f4402m.y, c);
        }
    }

    public boolean m5680a(PointF pointF) {
        if (pointF == null) {
            return false;
        }
        this.f4401l = new PointF(pointF.x, pointF.y);
        this.h = new PointF(pointF.x, pointF.y);
        return true;
    }

    public void m5681b(TopBar topBar) {
        switch (LineTool.m5677i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
            case Version.API04_DONUT_16 /*4*/:
                m5526c();
            case Version.API03_CUPCAKE_15 /*3*/:
                m5527d();
            default:
        }
    }

    public boolean m5682b(PointF pointF) {
        this.f4402m = new PointF(pointF.x, pointF.y);
        return true;
    }

    public boolean m5683c(PointF pointF) {
        if (this.f4401l == null || this.h == null || pointF == null) {
            return false;
        }
        Path path = new Path();
        path.moveTo(this.f4401l.x, this.f4401l.y);
        path.lineTo(pointF.x, pointF.y);
        PaintroidApplication.f4193c.m5447a(new PathCommand(c, path));
        return true;
    }

    public void m5684f() {
        this.f4401l = null;
        this.f4402m = null;
    }
}
