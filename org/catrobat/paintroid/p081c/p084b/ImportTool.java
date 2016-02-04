package org.catrobat.paintroid.p081c.p084b;

import android.app.Activity;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.n */
public class ImportTool extends StampTool {
    private static /* synthetic */ int[] f4399G;

    public ImportTool(Activity activity, ToolType toolType) {
        super(activity, toolType);
        this.D = true;
        this.F.setEnabled(false);
    }

    static /* synthetic */ int[] m5675l() {
        int[] iArr = f4399G;
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
            f4399G = iArr;
        }
        return iArr;
    }

    public int m5676a(TopBar topBar) {
        switch (ImportTool.m5675l()[topBar.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                return R.icon_menu_stamp_paste;
            case Version.API04_DONUT_16 /*4*/:
                return b;
            default:
                return super.m5668a(topBar);
        }
    }
}
