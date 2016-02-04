package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p078a.p079a.FillCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.j */
public class FillTool extends BaseTool {
    private static /* synthetic */ int[] f4380l;

    public FillTool(Context context, ToolType toolType) {
        super(context, toolType);
    }

    static /* synthetic */ int[] m5635i() {
        int[] iArr = f4380l;
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
            f4380l = iArr;
        }
        return iArr;
    }

    public int m5636a(TopBar topBar) {
        switch (FillTool.m5635i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return m5528e();
            case Version.API04_DONUT_16 /*4*/:
                return R.icon_menu_color_palette;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5637a(Canvas canvas) {
    }

    public boolean m5638a(PointF pointF) {
        return false;
    }

    public void m5639b(TopBar topBar) {
        switch (FillTool.m5635i()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
            case Version.API04_DONUT_16 /*4*/:
                m5526c();
            default:
                super.m5525b(topBar);
        }
    }

    public boolean m5640b(PointF pointF) {
        return false;
    }

    public boolean m5641c(PointF pointF) {
        int e = PaintroidApplication.f4192b.m5784e();
        if (pointF.x > ((float) PaintroidApplication.f4192b.m5783d()) || pointF.y > ((float) e) || pointF.x < 0.0f || pointF.y < 0.0f || c.getColor() == PaintroidApplication.f4192b.m5777a(pointF)) {
            return false;
        }
        Command fillCommand = new FillCommand(new Point((int) pointF.x, (int) pointF.y), c);
        IndeterminateProgressDialog.m5764a().show();
        ((FillCommand) fillCommand).addObserver(this);
        PaintroidApplication.f4193c.m5447a(fillCommand);
        return true;
    }

    public void m5642f() {
    }
}
