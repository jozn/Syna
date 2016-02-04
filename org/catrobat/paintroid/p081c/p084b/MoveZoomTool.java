package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.p */
public class MoveZoomTool extends BaseTool {
    private static /* synthetic */ int[] f4403l;

    public MoveZoomTool(Context context, ToolType toolType) {
        super(context, toolType);
    }

    static /* synthetic */ int[] m5685i() {
        int[] iArr = f4403l;
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
            f4403l = iArr;
        }
        return iArr;
    }

    private void m5686j() {
        PaintroidApplication.f4195e.m5803b(0.5714286f);
    }

    private void m5687k() {
        PaintroidApplication.f4195e.m5803b(1.75f);
        PaintroidApplication.f4195e.m5796a(0.0f, 0.0f);
    }

    public int m5688a(TopBar topBar) {
        switch (MoveZoomTool.m5685i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return b;
            case Version.API03_CUPCAKE_15 /*3*/:
                return R.icon_zoom_out;
            case Version.API04_DONUT_16 /*4*/:
                return R.icon_zoom_in;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5689a(Canvas canvas) {
    }

    public boolean m5690a(PointF pointF) {
        PaintroidApplication.f4195e.m5805c(pointF);
        this.h = pointF;
        return true;
    }

    public void m5691b(TopBar topBar) {
        switch (MoveZoomTool.m5685i()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                m5686j();
            case Version.API04_DONUT_16 /*4*/:
                m5687k();
            default:
                super.m5525b(topBar);
        }
    }

    public boolean m5692b(PointF pointF) {
        PaintroidApplication.f4195e.m5805c(pointF);
        PaintroidApplication.f4195e.m5796a(pointF.x - this.h.x, pointF.y - this.h.y);
        this.h.set(pointF);
        return true;
    }

    public boolean m5693c(PointF pointF) {
        return false;
    }

    public void m5694f() {
    }
}
