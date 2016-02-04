package org.catrobat.paintroid.p081c;

import android.app.Activity;
import org.catrobat.paintroid.p081c.p084b.CropTool;
import org.catrobat.paintroid.p081c.p084b.CursorTool;
import org.catrobat.paintroid.p081c.p084b.DrawTool;
import org.catrobat.paintroid.p081c.p084b.EraserTool;
import org.catrobat.paintroid.p081c.p084b.FillTool;
import org.catrobat.paintroid.p081c.p084b.FlipTool;
import org.catrobat.paintroid.p081c.p084b.GeometricFillTool;
import org.catrobat.paintroid.p081c.p084b.ImportTool;
import org.catrobat.paintroid.p081c.p084b.LineTool;
import org.catrobat.paintroid.p081c.p084b.MoveZoomTool;
import org.catrobat.paintroid.p081c.p084b.PipetteTool;
import org.catrobat.paintroid.p081c.p084b.RotationTool;
import org.catrobat.paintroid.p081c.p084b.StampTool;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b */
public class ToolFactory {
    private static /* synthetic */ int[] f4407a;

    public static Tool m5714a(Activity activity, ToolType toolType) {
        switch (ToolFactory.m5715a()[toolType.ordinal()]) {
            case VideoSize.CIF /*1*/:
                return new GeometricFillTool(activity, toolType);
            case VideoSize.HVGA /*2*/:
            case Version.API17_JELLY_BEAN_42 /*17*/:
                return new MoveZoomTool(activity, toolType);
            case Version.API03_CUPCAKE_15 /*3*/:
                return new PipetteTool(activity, toolType);
            case Version.API04_DONUT_16 /*4*/:
                return new DrawTool(activity, toolType);
            case Version.API08_FROYO_22 /*8*/:
                return new FillTool(activity, toolType);
            case Version.API09_GINGERBREAD_23 /*9*/:
                return new StampTool(activity, toolType);
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                return new LineTool(activity, toolType);
            case Version.API11_HONEYCOMB_30 /*11*/:
                return new CursorTool(activity, toolType);
            case Version.API12_HONEYCOMB_MR1_31X /*12*/:
                return new ImportTool(activity, toolType);
            case Version.API13_HONEYCOMB_MR2_32 /*13*/:
                return new CropTool(activity, toolType);
            case Version.API14_ICE_CREAM_SANDWICH_40 /*14*/:
                return new EraserTool(activity, toolType);
            case Version.API15_ICE_CREAM_SANDWICH_403 /*15*/:
                return new FlipTool(activity, toolType);
            case Version.API16_JELLY_BEAN_41 /*16*/:
                return new GeometricFillTool(activity, toolType);
            case Version.API18_JELLY_BEAN_43 /*18*/:
                return new RotationTool(activity, toolType);
            default:
                return new DrawTool(activity, ToolType.BRUSH);
        }
    }

    static /* synthetic */ int[] m5715a() {
        int[] iArr = f4407a;
        if (iArr == null) {
            iArr = new int[ToolType.values().length];
            try {
                iArr[ToolType.BRUSH.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToolType.CROP.ordinal()] = 13;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToolType.CURSOR.ordinal()] = 11;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ToolType.ELLIPSE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ToolType.ERASER.ordinal()] = 14;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[ToolType.FILL.ordinal()] = 8;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[ToolType.FLIP.ordinal()] = 15;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[ToolType.IMPORTPNG.ordinal()] = 12;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[ToolType.LINE.ordinal()] = 10;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[ToolType.MOVE.ordinal()] = 17;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[ToolType.NONE.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[ToolType.PIPETTE.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[ToolType.RECT.ordinal()] = 16;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[ToolType.REDO.ordinal()] = 6;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[ToolType.ROTATE.ordinal()] = 18;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[ToolType.STAMP.ordinal()] = 9;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[ToolType.UNDO.ordinal()] = 5;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[ToolType.ZOOM.ordinal()] = 2;
            } catch (NoSuchFieldError e18) {
            }
            f4407a = iArr;
        }
        return iArr;
    }
}
