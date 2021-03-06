package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p078a.p079a.FlipCommand.FlipCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.k */
public class FlipTool extends BaseTool {
    private static /* synthetic */ int[] f4381l;

    public FlipTool(Context context, ToolType toolType) {
        super(context, toolType);
    }

    static /* synthetic */ int[] m5643i() {
        int[] iArr = f4381l;
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
            f4381l = iArr;
        }
        return iArr;
    }

    public int m5644a(TopBar topBar) {
        switch (FlipTool.m5643i()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                return R.icon_menu_flip_horizontal;
            case Version.API04_DONUT_16 /*4*/:
                return R.icon_menu_flip_vertical;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5645a(Canvas canvas) {
    }

    public boolean m5646a(PointF pointF) {
        return false;
    }

    public void m5647b(TopBar topBar) {
        FlipCommand flipCommand;
        switch (FlipTool.m5643i()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                flipCommand = FlipCommand.FLIP_HORIZONTAL;
                break;
            case Version.API04_DONUT_16 /*4*/:
                flipCommand = FlipCommand.FLIP_VERTICAL;
                break;
            default:
                return;
        }
        Command flipCommand2 = new org.catrobat.paintroid.p078a.p079a.FlipCommand(flipCommand);
        IndeterminateProgressDialog.m5764a().show();
        ((org.catrobat.paintroid.p078a.p079a.FlipCommand) flipCommand2).addObserver(this);
        PaintroidApplication.f4193c.m5447a(flipCommand2);
    }

    public boolean m5648b(PointF pointF) {
        return false;
    }

    public boolean m5649c(PointF pointF) {
        return false;
    }

    public void m5650f() {
    }
}
