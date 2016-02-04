package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p078a.p079a.RotateCommand.RotateCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.r */
public class RotationTool extends BaseTool {
    private static /* synthetic */ int[] f4405l;

    public RotationTool(Context context, ToolType toolType) {
        super(context, toolType);
    }

    static /* synthetic */ int[] m5704i() {
        int[] iArr = f4405l;
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
            f4405l = iArr;
        }
        return iArr;
    }

    public int m5705a(TopBar topBar) {
        switch (RotationTool.m5704i()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                return R.icon_menu_rotate_left;
            case Version.API04_DONUT_16 /*4*/:
                return R.icon_menu_rotate_right;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5706a(Canvas canvas) {
    }

    public boolean m5707a(PointF pointF) {
        return false;
    }

    public void m5708b(TopBar topBar) {
        RotateCommand rotateCommand;
        switch (RotationTool.m5704i()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                rotateCommand = RotateCommand.ROTATE_LEFT;
                break;
            case Version.API04_DONUT_16 /*4*/:
                rotateCommand = RotateCommand.ROTATE_RIGHT;
                break;
            default:
                return;
        }
        Command rotateCommand2 = new org.catrobat.paintroid.p078a.p079a.RotateCommand(rotateCommand);
        IndeterminateProgressDialog.m5764a().show();
        ((org.catrobat.paintroid.p078a.p079a.RotateCommand) rotateCommand2).addObserver(this);
        PaintroidApplication.f4193c.m5447a(rotateCommand2);
    }

    public boolean m5709b(PointF pointF) {
        return false;
    }

    public boolean m5710c(PointF pointF) {
        return false;
    }

    public void m5711f() {
    }
}
