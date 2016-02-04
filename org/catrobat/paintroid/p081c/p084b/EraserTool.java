package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.p081c.Tool.Tool;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.i */
public class EraserTool extends DrawTool {
    private static /* synthetic */ int[] f4378p;
    protected Paint f4379o;

    public EraserTool(Context context, ToolType toolType) {
        super(context, toolType);
        this.f4379o = new Paint(PaintroidApplication.f4194d.m5502a());
        m5524b(0);
        d.setStrokeCap(this.f4379o.getStrokeCap());
        d.setStrokeWidth(this.f4379o.getStrokeWidth());
    }

    static /* synthetic */ int[] m5625j() {
        int[] iArr = f4378p;
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
            f4378p = iArr;
        }
        return iArr;
    }

    public int m5626a(TopBar topBar) {
        switch (EraserTool.m5625j()[topBar.ordinal()]) {
            case Version.API04_DONUT_16 /*4*/:
                return b;
            default:
                return super.m5616a(topBar);
        }
    }

    public Paint m5627a() {
        return new Paint(this.f4379o);
    }

    public void m5628a(Canvas canvas) {
        super.m5617a(canvas);
    }

    public void m5629a(Paint paint) {
        m5524b(0);
    }

    public void m5630a(Tool tool) {
        super.m5522a(tool);
    }

    public boolean m5631a(PointF pointF) {
        return super.m5618a(pointF);
    }

    public void m5632b(TopBar topBar) {
        switch (EraserTool.m5625j()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                super.m5619b(topBar);
            default:
        }
    }

    public boolean m5633b(PointF pointF) {
        return super.m5620b(pointF);
    }

    public boolean m5634c(PointF pointF) {
        return super.m5621c(pointF);
    }
}
