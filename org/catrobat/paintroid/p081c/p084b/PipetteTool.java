package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;

/* renamed from: org.catrobat.paintroid.c.b.q */
public class PipetteTool extends BaseTool {
    private static /* synthetic */ int[] f4404l;

    public PipetteTool(Context context, ToolType toolType) {
        super(context, toolType);
    }

    static /* synthetic */ int[] m5695i() {
        int[] iArr = f4404l;
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
            f4404l = iArr;
        }
        return iArr;
    }

    public int m5696a(TopBar topBar) {
        switch (PipetteTool.m5695i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return m5528e();
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5697a(Canvas canvas) {
    }

    public boolean m5698a(PointF pointF) {
        return m5702d(pointF);
    }

    public void m5699b(TopBar topBar) {
    }

    public boolean m5700b(PointF pointF) {
        return m5702d(pointF);
    }

    public boolean m5701c(PointF pointF) {
        return m5702d(pointF);
    }

    protected boolean m5702d(PointF pointF) {
        if (pointF == null) {
            return false;
        }
        int a = PaintroidApplication.f4192b.m5777a(pointF);
        ColorPickerDialog.m5753a().m5759a(a);
        m5524b(a);
        return true;
    }

    public void m5703f() {
    }
}
