package org.catrobat.paintroid.p081c.p084b;

import android.graphics.Paint.Cap;
import org.catrobat.paintroid.dialog.BrushPickerDialog.BrushPickerDialog;

/* renamed from: org.catrobat.paintroid.c.b.c */
class BaseTool implements BrushPickerDialog {
    final /* synthetic */ BaseTool f4303a;

    BaseTool(BaseTool baseTool) {
        this.f4303a = baseTool;
    }

    public void m5534a(int i) {
        this.f4303a.m5519a(i);
    }

    public void m5535a(Cap cap) {
        this.f4303a.m5520a(cap);
    }
}

import android.view.WindowManager;
import java.util.Observable;
import java.util.Observer;
import org.catrobat.paintroid.PaintMainActivity;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.dialog.BrushPickerDialog.BrushPickerDialog;
import org.catrobat.paintroid.dialog.IndeterminateProgressDialog;
import org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog.ColorPickerDialog;
import org.catrobat.paintroid.p078a.p079a.BaseCommand.BaseCommand;
import org.catrobat.paintroid.p081c.Tool;
import org.catrobat.paintroid.p081c.ToolType;
import org.catrobat.paintroid.ui.TopBar.TopBar;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.a */
public abstract class BaseTool extends Observable implements Observer, Tool {
    public static final Paint f4288a;
    protected static final int f4289b;
    protected static Paint f4290c;
    protected static Paint f4291d;
    protected static int f4292i;
    protected static final PorterDuffXfermode f4293k;
    private static /* synthetic */ int[] f4294m;
    private static /* synthetic */ int[] f4295n;
    protected ToolType f4296e;
    protected Context f4297f;
    protected PointF f4298g;
    protected PointF f4299h;
    protected ColorPickerDialog f4300j;
    private BrushPickerDialog f4301l;

    static {
        f4288a = new Paint();
        f4289b = R.icon_menu_no_icon;
        f4293k = new PorterDuffXfermode(Mode.CLEAR);
        f4290c = new Paint();
        f4290c.setColor(-16777216);
        f4290c.setAntiAlias(true);
        f4290c.setDither(true);
        f4290c.setStyle(Style.STROKE);
        f4290c.setStrokeJoin(Join.ROUND);
        f4290c.setStrokeCap(Cap.ROUND);
        f4290c.setStrokeWidth(25.0f);
        f4291d = new Paint(f4290c);
        f4288a.setShader(new BitmapShader(BitmapFactory.decodeResource(PaintroidApplication.f4191a.getResources(), R.checkeredbg), TileMode.REPEAT, TileMode.REPEAT));
        f4292i = (((WindowManager) PaintroidApplication.f4191a.getSystemService("window")).getDefaultDisplay().getWidth() * 10) / 100;
    }

    public BaseTool(Context context, ToolType toolType) {
        this.f4296e = toolType;
        this.f4297f = context;
        this.f4300j = new BaseTool(this);
        this.f4301l = new BaseTool(this);
        org.catrobat.paintroid.dialog.BrushPickerDialog.m5720D().m5727a(this.f4301l);
        org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog.m5753a().m5760a(this.f4300j);
        this.f4298g = new PointF(0.0f, 0.0f);
        this.f4299h = new PointF(0.0f, 0.0f);
    }

    static /* synthetic */ int[] m5514g() {
        int[] iArr = f4294m;
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
            f4294m = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m5515h() {
        int[] iArr = f4295n;
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
            f4295n = iArr;
        }
        return iArr;
    }

    public int m5516a(TopBar topBar) {
        switch (BaseTool.m5515h()[topBar.ordinal()]) {
            case VideoSize.CIF /*1*/:
                switch (BaseTool.m5514g()[this.f4296e.ordinal()]) {
                    case VideoSize.CIF /*1*/:
                        return R.icon_menu_ellipse;
                    case VideoSize.HVGA /*2*/:
                        return R.icon_menu_zoom;
                    case Version.API03_CUPCAKE_15 /*3*/:
                        return R.icon_menu_pipette;
                    case Version.API04_DONUT_16 /*4*/:
                        return R.icon_menu_brush;
                    case Version.API08_FROYO_22 /*8*/:
                        return R.icon_menu_bucket;
                    case Version.API09_GINGERBREAD_23 /*9*/:
                        return R.icon_menu_stamp;
                    case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                        return R.icon_menu_straight_line;
                    case Version.API11_HONEYCOMB_30 /*11*/:
                        return R.icon_menu_cursor;
                    case Version.API13_HONEYCOMB_MR2_32 /*13*/:
                        return R.icon_menu_crop;
                    case Version.API14_ICE_CREAM_SANDWICH_40 /*14*/:
                        return R.icon_menu_eraser;
                    case Version.API15_ICE_CREAM_SANDWICH_403 /*15*/:
                        return R.icon_menu_flip_horizontal;
                    case Version.API16_JELLY_BEAN_41 /*16*/:
                        return R.icon_menu_rectangle;
                    case Version.API17_JELLY_BEAN_42 /*17*/:
                        return R.icon_menu_move;
                    case Version.API18_JELLY_BEAN_43 /*18*/:
                        return R.icon_menu_rotate_left;
                    default:
                        return R.icon_menu_brush;
                }
            default:
                return f4289b;
        }
    }

    public Paint m5517a() {
        return new Paint(f4290c);
    }

    public Point m5518a(float f, float f2, int i, int i2) {
        int i3 = 1;
        int i4 = -1;
        int i5 = f < ((float) f4292i) ? 1 : 0;
        if (f > ((float) (i - f4292i))) {
            i5 = -1;
        }
        if (f2 >= ((float) f4292i)) {
            i3 = 0;
        }
        if (f2 <= ((float) (i2 - f4292i))) {
            i4 = i3;
        }
        return new Point(i5, i4);
    }

    public void m5519a(int i) {
        boolean z = true;
        f4290c.setStrokeWidth((float) i);
        f4291d.setStrokeWidth((float) i);
        if (i <= 1) {
            z = false;
        }
        f4290c.setAntiAlias(z);
        f4291d.setAntiAlias(z);
        super.setChanged();
        super.notifyObservers();
    }

    public void m5520a(Cap cap) {
        f4290c.setStrokeCap(cap);
        f4291d.setStrokeCap(cap);
        super.setChanged();
        super.notifyObservers();
    }

    public void m5521a(Paint paint) {
        f4290c.set(paint);
        f4291d.set(paint);
        super.setChanged();
        super.notifyObservers();
    }

    public void m5522a(Tool.Tool tool) {
        if (m5523b().m5717a(tool)) {
            m5529f();
        }
    }

    public ToolType m5523b() {
        return this.f4296e;
    }

    public void m5524b(int i) {
        f4290c.setColor(i);
        if (Color.alpha(i) == 0) {
            f4290c.setXfermode(f4293k);
            f4291d.reset();
            f4291d.setStyle(f4290c.getStyle());
            f4291d.setStrokeJoin(f4290c.getStrokeJoin());
            f4291d.setStrokeCap(f4290c.getStrokeCap());
            f4291d.setStrokeWidth(f4290c.getStrokeWidth());
            f4291d.setShader(f4288a.getShader());
            f4291d.setColor(-16777216);
            f4290c.setAlpha(0);
            f4291d.setAlpha(0);
        } else {
            f4290c.setXfermode(null);
            f4291d.set(f4290c);
        }
        super.setChanged();
        super.notifyObservers();
    }

    public void m5525b(TopBar topBar) {
    }

    protected void m5526c() {
        org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog.m5753a().m5760a(this.f4300j);
        org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog.m5753a().show();
        org.catrobat.paintroid.dialog.colorpicker.ColorPickerDialog.m5753a().m5759a(m5517a().getColor());
    }

    protected void m5527d() {
        org.catrobat.paintroid.dialog.BrushPickerDialog.m5720D().m5727a(this.f4301l);
        org.catrobat.paintroid.dialog.BrushPickerDialog.m5720D().m5726a(f4290c);
        org.catrobat.paintroid.dialog.BrushPickerDialog.m5720D().m229a(((PaintMainActivity) this.f4297f).m121e(), "brushpicker");
    }

    protected int m5528e() {
        return f4290c.getColor() == 0 ? R.checkeredbg_repeat : f4289b;
    }

    protected abstract void m5529f();

    public void update(Observable observable, Object obj) {
        if (!(obj instanceof BaseCommand)) {
            return;
        }
        if (BaseCommand.COMMAND_DONE == obj || BaseCommand.COMMAND_FAILED == obj) {
            IndeterminateProgressDialog.m5764a().dismiss();
            observable.deleteObserver(this);
        }
    }
}
