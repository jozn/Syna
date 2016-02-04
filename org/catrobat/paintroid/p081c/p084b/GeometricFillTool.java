package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.p078a.Command;
import org.catrobat.paintroid.p078a.p079a.StampCommand;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.DrawingSurface;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.l */
public class GeometricFillTool extends BaseToolWithRectangleShape {
    private static /* synthetic */ int[] f4388E;
    private static /* synthetic */ int[] f4389F;
    private static /* synthetic */ int[] f4390G;
    private GeometricFillTool f4391C;
    private GeometricFillTool f4392D;

    /* renamed from: org.catrobat.paintroid.c.b.l.a */
    public enum GeometricFillTool {
        RECTANGLE,
        OVAL
    }

    /* renamed from: org.catrobat.paintroid.c.b.l.b */
    public enum GeometricFillTool {
        OUTLINE,
        FILL
    }

    public GeometricFillTool(Context context, ToolType toolType) {
        super(context, toolType);
        m5562b(true);
        m5559a(false);
        if (toolType == ToolType.ELLIPSE) {
            this.f4391C = GeometricFillTool.OVAL;
        } else {
            this.f4391C = GeometricFillTool.RECTANGLE;
        }
        this.f4392D = GeometricFillTool.FILL;
        this.j = new GeometricFillTool(this);
        m5656a(PaintroidApplication.f4192b);
    }

    static /* synthetic */ int[] m5651l() {
        int[] iArr = f4388E;
        if (iArr == null) {
            iArr = new int[GeometricFillTool.values().length];
            try {
                iArr[GeometricFillTool.FILL.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[GeometricFillTool.OUTLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            f4388E = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m5652m() {
        int[] iArr = f4389F;
        if (iArr == null) {
            iArr = new int[GeometricFillTool.values().length];
            try {
                iArr[GeometricFillTool.OVAL.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[GeometricFillTool.RECTANGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            f4389F = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m5653n() {
        int[] iArr = f4390G;
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
            f4390G = iArr;
        }
        return iArr;
    }

    public int m5654a(TopBar topBar) {
        switch (GeometricFillTool.m5653n()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return m5528e();
            case Version.API04_DONUT_16 /*4*/:
                return R.icon_menu_color_palette;
            default:
                return super.m5516a(topBar);
        }
    }

    public void m5655a(Paint paint) {
        super.m5521a(paint);
        m5656a(PaintroidApplication.f4192b);
    }

    protected void m5656a(DrawingSurface drawingSurface) {
        Bitmap createBitmap = Bitmap.createBitmap((int) this.n, (int) this.o, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(10.0f, 10.0f, this.n - 10.0f, this.o - 10.0f);
        Paint paint = new Paint();
        paint.setColor(d.getColor());
        paint.setAntiAlias(true);
        switch (GeometricFillTool.m5651l()[this.f4392D.ordinal()]) {
            case VideoSize.CIF /*1*/:
                paint.setStyle(Style.STROKE);
                float strokeWidth = c.getStrokeWidth();
                rectF = new RectF((strokeWidth / 2.0f) + 10.0f, (strokeWidth / 2.0f) + 10.0f, (this.n - 10.0f) - (strokeWidth / 2.0f), (this.o - 10.0f) - (strokeWidth / 2.0f));
                paint.setStrokeWidth(strokeWidth);
                paint.setStrokeCap(Cap.BUTT);
                break;
            case VideoSize.HVGA /*2*/:
                paint.setStyle(Style.FILL);
                break;
        }
        switch (GeometricFillTool.m5652m()[this.f4391C.ordinal()]) {
            case VideoSize.CIF /*1*/:
                canvas.drawRect(rectF, paint);
                break;
            case VideoSize.HVGA /*2*/:
                canvas.drawOval(rectF, paint);
                break;
        }
        this.x = createBitmap;
    }

    public void m5657b(int i) {
        super.m5524b(i);
        m5656a(PaintroidApplication.f4192b);
    }

    public void m5658b(TopBar topBar) {
        switch (GeometricFillTool.m5653n()[topBar.ordinal()]) {
            case VideoSize.HVGA /*2*/:
            case Version.API04_DONUT_16 /*4*/:
                m5526c();
            default:
        }
    }

    protected void m5659c(Canvas canvas) {
    }

    public void m5660f() {
    }

    protected void m5661i() {
        Command stampCommand = new StampCommand(this.x, new Point((int) this.A.x, (int) this.A.y), this.n, this.o, this.p);
        ((StampCommand) stampCommand).addObserver(this);
        IndeterminateProgressDialog.m5764a().show();
        PaintroidApplication.f4193c.m5447a(stampCommand);
    }
}
