package org.catrobat.paintroid.p081c.p084b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.view.WindowManager;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p081c.ToolType;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: org.catrobat.paintroid.c.b.d */
public abstract class BaseToolWithRectangleShape extends BaseToolWithShape {
    private static final int f4328C;
    private static final int f4329D;
    private static final int f4330E;
    private static final int f4331F;
    private static final int f4332G;
    private static /* synthetic */ int[] f4333N;
    private static /* synthetic */ int[] f4334O;
    protected static final Cap f4335l;
    protected static final PorterDuffXfermode f4336m;
    private boolean f4337H;
    private boolean f4338I;
    private boolean f4339J;
    private boolean f4340K;
    private boolean f4341L;
    private boolean f4342M;
    protected float f4343n;
    protected float f4344o;
    protected float f4345p;
    protected float f4346q;
    protected float f4347r;
    protected float f4348s;
    protected float f4349t;
    protected BaseToolWithRectangleShape f4350u;
    protected BaseToolWithRectangleShape f4351v;
    protected BaseToolWithRectangleShape f4352w;
    protected Bitmap f4353x;

    /* renamed from: org.catrobat.paintroid.c.b.d.a */
    private enum BaseToolWithRectangleShape {
        NONE,
        MOVE,
        RESIZE,
        ROTATE
    }

    /* renamed from: org.catrobat.paintroid.c.b.d.b */
    private enum BaseToolWithRectangleShape {
        NONE,
        TOP,
        RIGHT,
        BOTTOM,
        LEFT,
        TOPLEFT,
        TOPRIGHT,
        BOTTOMLEFT,
        BOTTOMRIGHT
    }

    /* renamed from: org.catrobat.paintroid.c.b.d.c */
    private enum BaseToolWithRectangleShape {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }

    static {
        f4335l = Cap.SQUARE;
        f4336m = new PorterDuffXfermode(Mode.CLEAR);
        f4328C = BaseToolWithRectangleShape.m5542c(4);
        f4329D = BaseToolWithRectangleShape.m5542c(2);
        f4330E = BaseToolWithRectangleShape.m5542c(8);
        f4331F = BaseToolWithRectangleShape.m5542c(3);
        f4332G = BaseToolWithRectangleShape.m5542c(3);
    }

    public BaseToolWithRectangleShape(Context context, ToolType toolType) {
        super(context, toolType);
        this.f4342M = false;
        this.e = toolType;
        this.f4343n = (((float) ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth()) / PaintroidApplication.f4195e.m5801b()) - (m5536a(100.0f) * 2.0f);
        this.f4344o = this.f4343n;
        if (this.f4344o > ((float) PaintroidApplication.f4192b.m5784e()) * 2.0f || this.f4343n > ((float) PaintroidApplication.f4192b.m5783d()) * 2.0f) {
            this.f4344o = ((float) PaintroidApplication.f4192b.m5784e()) * 2.0f;
            this.f4343n = ((float) PaintroidApplication.f4192b.m5783d()) * 2.0f;
        }
        this.f4352w = BaseToolWithRectangleShape.TOP_LEFT;
        this.f4350u = BaseToolWithRectangleShape.NONE;
        this.f4337H = false;
        this.f4338I = false;
        this.f4339J = true;
        this.f4340K = true;
        this.f4341L = false;
        m5554l();
        m5555m();
    }

    private void m5539a(float f, float f2) {
        float f3 = this.A.x + f;
        float f4 = this.A.y + f2;
        if (this.f4337H) {
            if (f3 - (this.f4343n / 2.0f) < 0.0f) {
                f3 = this.f4343n / 2.0f;
            } else if ((this.f4343n / 2.0f) + f3 > ((float) PaintroidApplication.f4192b.m5783d())) {
                f3 = ((float) PaintroidApplication.f4192b.m5783d()) - (this.f4343n / 2.0f);
            }
            if (f4 - (this.f4344o / 2.0f) < 0.0f) {
                f4 = f3;
                f3 = this.f4344o / 2.0f;
            } else if ((this.f4344o / 2.0f) + f4 > ((float) PaintroidApplication.f4192b.m5784e())) {
                f4 = f3;
                f3 = ((float) PaintroidApplication.f4192b.m5784e()) - (this.f4344o / 2.0f);
            }
            this.A.x = f4;
            this.A.y = f3;
        }
        float f5 = f4;
        f4 = f3;
        f3 = f5;
        this.A.x = f4;
        this.A.y = f3;
    }

    private boolean m5540a(float f, float f2, PointF pointF) {
        return f > pointF.x - (this.f4347r / 2.0f) && f < pointF.x + (this.f4347r / 2.0f) && f2 > pointF.y - (this.f4347r / 2.0f) && f2 < pointF.y + (this.f4347r / 2.0f);
    }

    private void m5541b(float f, float f2) {
        if (this.f4353x != null) {
            PointF pointF = new PointF(this.h.x, this.h.y);
            double d = (double) ((this.h.y - f2) - this.A.y);
            double d2 = (double) (pointF.x - this.A.x);
            double d3 = -(Math.atan2(d, (double) ((this.h.x - f) - this.A.x)) - Math.atan2((double) (pointF.y - this.A.y), d2));
            this.f4345p = (((float) Math.toDegrees(d3)) + 360.0f) + this.f4345p;
            this.f4345p %= 360.0f;
            if (this.f4345p > 180.0f) {
                this.f4345p = -180.0f + (this.f4345p - 180.0f);
            }
        }
    }

    private static int m5542c(int i) {
        int i2 = PaintroidApplication.f4191a.getResources().getDisplayMetrics().densityDpi;
        if (i2 < 160) {
            i2 = 160;
        }
        return (i2 * i) / 160;
    }

    private BaseToolWithRectangleShape m5543c(float f, float f2) {
        this.f4350u = BaseToolWithRectangleShape.NONE;
        double d = (((double) this.f4345p) * 3.141592653589793d) / 180.0d;
        float cos = (float) ((((double) this.A.x) + (Math.cos(-d) * ((double) (f - this.A.x)))) - (Math.sin(-d) * ((double) (f2 - this.A.y))));
        float cos2 = (float) ((Math.cos(-d) * ((double) (f2 - this.A.y))) + (((double) this.A.y) + (Math.sin(-d) * ((double) (f - this.A.x)))));
        if (cos < (this.A.x + (this.f4343n / 2.0f)) - this.f4346q && cos > (this.A.x - (this.f4343n / 2.0f)) + this.f4346q && cos2 < (this.A.y + (this.f4344o / 2.0f)) - this.f4346q && cos2 > (this.A.y - (this.f4344o / 2.0f)) + this.f4346q) {
            return BaseToolWithRectangleShape.MOVE;
        }
        if (cos >= (this.A.x + (this.f4343n / 2.0f)) + this.f4346q || cos <= (this.A.x - (this.f4343n / 2.0f)) - this.f4346q || cos2 >= (this.A.y + (this.f4344o / 2.0f)) + this.f4346q || cos2 <= (this.A.y - (this.f4344o / 2.0f)) - this.f4346q) {
            if (this.f4353x != null && this.f4338I) {
                PointF pointF = new PointF((this.A.x - (this.f4343n / 2.0f)) - (this.f4347r / 2.0f), (this.A.y - (this.f4344o / 2.0f)) - (this.f4347r / 2.0f));
                PointF pointF2 = new PointF((this.A.x + (this.f4343n / 2.0f)) + (this.f4347r / 2.0f), (this.A.y - (this.f4344o / 2.0f)) - (this.f4347r / 2.0f));
                PointF pointF3 = new PointF((this.A.x - (this.f4343n / 2.0f)) - (this.f4347r / 2.0f), (this.A.y + (this.f4344o / 2.0f)) + (this.f4347r / 2.0f));
                PointF pointF4 = new PointF((this.A.x + (this.f4343n / 2.0f)) + (this.f4347r / 2.0f), (this.A.y + (this.f4344o / 2.0f)) + (this.f4347r / 2.0f));
                if (m5540a(cos, cos2, pointF) || m5540a(cos, cos2, pointF2) || m5540a(cos, cos2, pointF3) || m5540a(cos, cos2, pointF4)) {
                    return BaseToolWithRectangleShape.ROTATE;
                }
            }
            return BaseToolWithRectangleShape.MOVE;
        }
        if (cos < (this.A.x - (this.f4343n / 2.0f)) + this.f4346q) {
            this.f4350u = BaseToolWithRectangleShape.LEFT;
        } else if (cos > (this.A.x + (this.f4343n / 2.0f)) - this.f4346q) {
            this.f4350u = BaseToolWithRectangleShape.RIGHT;
        }
        if (cos2 < (this.A.y - (this.f4344o / 2.0f)) + this.f4346q) {
            if (this.f4350u == BaseToolWithRectangleShape.LEFT) {
                this.f4350u = BaseToolWithRectangleShape.TOPLEFT;
            } else if (this.f4350u == BaseToolWithRectangleShape.RIGHT) {
                this.f4350u = BaseToolWithRectangleShape.TOPRIGHT;
            } else {
                this.f4350u = BaseToolWithRectangleShape.TOP;
            }
        } else if (cos2 > (this.A.y + (this.f4344o / 2.0f)) - this.f4346q) {
            if (this.f4350u == BaseToolWithRectangleShape.LEFT) {
                this.f4350u = BaseToolWithRectangleShape.BOTTOMLEFT;
            } else if (this.f4350u == BaseToolWithRectangleShape.RIGHT) {
                this.f4350u = BaseToolWithRectangleShape.BOTTOMRIGHT;
            } else {
                this.f4350u = BaseToolWithRectangleShape.BOTTOM;
            }
        }
        return BaseToolWithRectangleShape.RESIZE;
    }

    private void m5544d(float f, float f2) {
        float f3;
        float f4;
        double d = (((double) this.f4345p) * 3.141592653589793d) / 180.0d;
        double cos = (Math.cos(-d) * ((double) f)) - (Math.sin(-d) * ((double) f2));
        double sin = (Math.sin(-d) * ((double) f)) + (Math.cos(-d) * ((double) f2));
        switch (BaseToolWithRectangleShape.m5553k()[this.f4350u.ordinal()]) {
            case Version.API06_ECLAIR_201 /*6*/:
            case Version.API09_GINGERBREAD_23 /*9*/:
                if (Math.abs(cos) <= Math.abs(sin)) {
                    cos = ((((double) this.f4343n) * (((double) this.f4344o) + sin)) / ((double) this.f4344o)) - ((double) this.f4343n);
                    break;
                } else {
                    sin = (((((double) this.f4343n) + cos) * ((double) this.f4344o)) / ((double) this.f4343n)) - ((double) this.f4344o);
                    break;
                }
            case Version.API07_ECLAIR_21 /*7*/:
            case Version.API08_FROYO_22 /*8*/:
                if (Math.abs(cos) <= Math.abs(sin)) {
                    cos = ((((double) this.f4343n) * (((double) this.f4344o) - sin)) / ((double) this.f4344o)) - ((double) this.f4343n);
                    break;
                } else {
                    sin = (((((double) this.f4343n) - cos) * ((double) this.f4344o)) / ((double) this.f4343n)) - ((double) this.f4344o);
                    break;
                }
        }
        float cos2 = (float) ((cos / 2.0d) * Math.cos(d));
        float sin2 = (float) ((cos / 2.0d) * Math.sin(d));
        float sin3 = (float) ((sin / 2.0d) * Math.sin(d));
        float cos3 = (float) (Math.cos(d) * (sin / 2.0d));
        float f5 = this.A.x;
        f5 = this.A.y;
        f5 = this.A.x;
        float f6 = this.A.y;
        switch (BaseToolWithRectangleShape.m5553k()[this.f4350u.ordinal()]) {
            case VideoSize.HVGA /*2*/:
            case Version.API06_ECLAIR_201 /*6*/:
            case Version.API07_ECLAIR_21 /*7*/:
                f3 = (float) (((double) this.f4344o) - sin);
                f4 = this.A.x - sin3;
                cos3 += this.A.y;
                if (!this.f4337H || cos3 - (f3 / 2.0f) >= 0.0f) {
                    if (f3 <= ((float) PaintroidApplication.f4192b.m5784e()) * 2.0f) {
                        this.f4344o = f3;
                        this.A.x = f4;
                        this.A.y = cos3;
                        break;
                    }
                    this.f4344o = ((float) PaintroidApplication.f4192b.m5784e()) * 2.0f;
                    break;
                }
                f3 = this.A.x;
                f3 = this.A.y;
                break;
                break;
            case Version.API04_DONUT_16 /*4*/:
            case Version.API08_FROYO_22 /*8*/:
            case Version.API09_GINGERBREAD_23 /*9*/:
                f3 = (float) (sin + ((double) this.f4344o));
                f4 = this.A.x - sin3;
                cos3 += this.A.y;
                if (!this.f4337H || (f3 / 2.0f) + cos3 <= ((float) PaintroidApplication.f4192b.m5784e())) {
                    if (f3 <= ((float) PaintroidApplication.f4192b.m5784e()) * 2.0f) {
                        this.f4344o = f3;
                        this.A.x = f4;
                        this.A.y = cos3;
                        break;
                    }
                    this.f4344o = ((float) PaintroidApplication.f4192b.m5784e()) * 2.0f;
                    break;
                }
                f3 = this.A.x;
                f3 = this.A.y;
                break;
                break;
        }
        float f7;
        switch (BaseToolWithRectangleShape.m5553k()[this.f4350u.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
            case Version.API07_ECLAIR_21 /*7*/:
            case Version.API09_GINGERBREAD_23 /*9*/:
                f3 = (float) (((double) this.f4343n) + cos);
                f4 = this.A.x + cos2;
                f7 = this.A.y + sin2;
                if (!this.f4337H || (f3 / 2.0f) + f4 <= ((float) PaintroidApplication.f4192b.m5783d())) {
                    if (f3 <= ((float) PaintroidApplication.f4192b.m5783d()) * 2.0f) {
                        this.f4343n = f3;
                        this.A.x = f4;
                        this.A.y = f7;
                        break;
                    }
                    this.f4343n = ((float) PaintroidApplication.f4192b.m5783d()) * 2.0f;
                    break;
                }
                f3 = this.A.x;
                f3 = this.A.y;
                break;
                break;
            case Version.API05_ECLAIR_20 /*5*/:
            case Version.API06_ECLAIR_201 /*6*/:
            case Version.API08_FROYO_22 /*8*/:
                f3 = (float) (((double) this.f4343n) - cos);
                f4 = this.A.x + cos2;
                f7 = this.A.y + sin2;
                if (!this.f4337H || f4 - (f3 / 2.0f) >= 0.0f) {
                    if (f3 <= ((float) PaintroidApplication.f4192b.m5783d()) * 2.0f) {
                        this.f4343n = f3;
                        this.A.x = f4;
                        this.A.y = f7;
                        break;
                    }
                    this.f4343n = ((float) PaintroidApplication.f4192b.m5783d()) * 2.0f;
                    break;
                }
                f3 = this.A.x;
                f3 = this.A.y;
                break;
                break;
        }
        if (this.f4343n < 20.0f) {
            this.f4343n = 20.0f;
            this.A.x = f5;
        }
        if (this.f4344o < 20.0f) {
            this.f4344o = 20.0f;
            this.A.y = f6;
        }
    }

    private void m5545d(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.argb(128, 0, 0, 0));
        paint.setStyle(Style.FILL);
        canvas.clipRect(((-this.f4343n) + this.f4349t) / 2.0f, (this.f4344o - this.f4349t) / 2.0f, (this.f4343n - this.f4349t) / 2.0f, ((-this.f4344o) + this.f4349t) / 2.0f, Op.DIFFERENCE);
        canvas.rotate(-this.f4345p);
        canvas.translate(-this.A.x, -this.A.y);
        canvas.drawRect(0.0f, 0.0f, (float) PaintroidApplication.f4192b.m5783d(), (float) PaintroidApplication.f4192b.m5784e(), paint);
        canvas.translate(this.A.x, this.A.y);
        canvas.rotate(this.f4345p);
    }

    private boolean m5546d(PointF pointF) {
        return pointF.x > this.A.x - (this.f4343n / 2.0f) && pointF.x < this.A.x + (this.f4343n / 2.0f) && pointF.y > this.A.y - (this.f4344o / 2.0f) && pointF.y < this.A.y + (this.f4344o / 2.0f);
    }

    private void m5547e(Canvas canvas) {
        float a = m5536a((float) f4328C);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(this.z);
        paint.setStyle(Style.FILL);
        canvas.drawCircle(0.0f, (-this.f4344o) / 2.0f, a, paint);
        canvas.drawCircle(this.f4343n / 2.0f, (-this.f4344o) / 2.0f, a, paint);
        canvas.drawCircle(this.f4343n / 2.0f, 0.0f, a, paint);
        canvas.drawCircle(this.f4343n / 2.0f, this.f4344o / 2.0f, a, paint);
        canvas.drawCircle(0.0f, this.f4344o / 2.0f, a, paint);
        canvas.drawCircle((-this.f4343n) / 2.0f, this.f4344o / 2.0f, a, paint);
        canvas.drawCircle((-this.f4343n) / 2.0f, 0.0f, a, paint);
        canvas.drawCircle((-this.f4343n) / 2.0f, (-this.f4344o) / 2.0f, a, paint);
    }

    private void m5548f(Canvas canvas) {
        float a = m5536a((float) f4329D);
        float a2 = m5536a((float) f4330E);
        float a3 = m5536a((float) f4331F);
        float a4 = m5536a((float) f4332G);
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setStrokeWidth(a);
        paint.setStyle(Style.STROKE);
        paint.setStrokeCap(Cap.BUTT);
        Paint paint2 = new Paint();
        paint2.setColor(-1);
        paint2.setStyle(Style.FILL);
        float f = this.f4343n;
        float f2 = this.f4344o;
        int i = 0;
        while (i < 4) {
            float f3 = ((-f) / 2.0f) - a4;
            float f4 = ((-f2) / 2.0f) - a4;
            Path path = new Path();
            path.addArc(new RectF(f3 - a2, f4 - a2, f3 + a2, f4 + a2), 180.0f, 90.0f);
            canvas.drawPath(path, paint);
            path = new Path();
            path.moveTo((f3 - a2) - a3, f4);
            path.lineTo((f3 - a2) + a3, f4);
            path.lineTo(f3 - a2, f4 + a3);
            path.close();
            path.moveTo(f3, (f4 - a2) - a3);
            path.lineTo(f3, (f4 - a2) + a3);
            path.lineTo(f3 + a3, f4 - a2);
            path.close();
            canvas.drawPath(path, paint2);
            canvas.rotate(90.0f);
            i++;
            float f5 = f;
            f = f2;
            f2 = f5;
        }
    }

    private void m5549g(Canvas canvas) {
        Paint paint = new Paint(4);
        canvas.save();
        canvas.clipRect(new RectF((-this.f4343n) / 2.0f, (-this.f4344o) / 2.0f, this.f4343n / 2.0f, this.f4344o / 2.0f), Op.UNION);
        canvas.drawBitmap(this.f4353x, null, new RectF((-this.f4343n) / 2.0f, (-this.f4344o) / 2.0f, this.f4343n / 2.0f, this.f4344o / 2.0f), paint);
    }

    private void m5550h(Canvas canvas) {
        this.B.setStrokeWidth(this.f4349t);
        this.B.setColor(this.z);
        canvas.drawRect(new RectF((-this.f4343n) / 2.0f, (-this.f4344o) / 2.0f, this.f4343n / 2.0f, this.f4344o / 2.0f), this.B);
    }

    private void m5551i(Canvas canvas) {
        RectF rectF = new RectF(-48.0f, -48.0f, 48.0f, 48.0f);
        if (this.f4342M) {
            int i;
            switch (BaseToolWithRectangleShape.m5552j()[this.f4351v.ordinal()]) {
                case VideoSize.HVGA /*2*/:
                    i = R.def_icon_move;
                    break;
                case Version.API03_CUPCAKE_15 /*3*/:
                    i = R.def_icon_resize;
                    break;
                case Version.API04_DONUT_16 /*4*/:
                    i = R.def_icon_rotate;
                    break;
                default:
                    i = R.icon_menu_no_icon;
                    break;
            }
            if (i != R.icon_menu_no_icon) {
                Paint paint = new Paint();
                paint.setColor(this.z);
                canvas.clipRect(rectF, Op.UNION);
                paint.setAlpha(128);
                canvas.drawOval(rectF, paint);
                Bitmap decodeResource = BitmapFactory.decodeResource(PaintroidApplication.f4191a.getResources(), i);
                paint.setAlpha(255);
                canvas.rotate(-this.f4345p);
                canvas.drawBitmap(decodeResource, -24.0f, -24.0f, paint);
                canvas.rotate(this.f4345p);
            }
        }
    }

    static /* synthetic */ int[] m5552j() {
        int[] iArr = f4333N;
        if (iArr == null) {
            iArr = new int[BaseToolWithRectangleShape.values().length];
            try {
                iArr[BaseToolWithRectangleShape.MOVE.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BaseToolWithRectangleShape.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BaseToolWithRectangleShape.RESIZE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BaseToolWithRectangleShape.ROTATE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            f4333N = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m5553k() {
        int[] iArr = f4334O;
        if (iArr == null) {
            iArr = new int[BaseToolWithRectangleShape.values().length];
            try {
                iArr[BaseToolWithRectangleShape.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BaseToolWithRectangleShape.BOTTOMLEFT.ordinal()] = 8;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BaseToolWithRectangleShape.BOTTOMRIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BaseToolWithRectangleShape.LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BaseToolWithRectangleShape.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[BaseToolWithRectangleShape.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[BaseToolWithRectangleShape.TOP.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[BaseToolWithRectangleShape.TOPLEFT.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[BaseToolWithRectangleShape.TOPRIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError e9) {
            }
            f4334O = iArr;
        }
        return iArr;
    }

    private void m5554l() {
        this.B = new Paint();
        this.B.setDither(true);
        this.B.setStyle(Style.STROKE);
        this.B.setStrokeJoin(Join.ROUND);
    }

    private void m5555m() {
        this.f4349t = m5537a(3.0f, 1.0f, 8.0f);
        this.f4346q = m5536a(20.0f);
        this.f4347r = m5536a(20.0f) * 2.0f;
        this.f4348s = m5536a(30.0f);
    }

    public Point m5556a(float f, float f2, int i, int i2) {
        return (this.f4351v == BaseToolWithRectangleShape.MOVE || this.f4351v == BaseToolWithRectangleShape.RESIZE) ? super.m5538a(f, f2, i, i2) : new Point(0, 0);
    }

    public void m5557a(Bitmap bitmap) {
        if (bitmap != null) {
            this.f4353x = bitmap;
            float width = ((float) bitmap.getWidth()) / ((float) bitmap.getHeight());
            if (width > 1.0f) {
                this.f4343n = width * this.f4343n;
            } else {
                this.f4344o /= width;
            }
        }
    }

    public void m5558a(Canvas canvas) {
        m5561b(canvas);
    }

    protected void m5559a(boolean z) {
        this.f4337H = z;
    }

    public boolean m5560a(PointF pointF) {
        this.f4342M = true;
        this.g.set(0.0f, 0.0f);
        this.h = new PointF(pointF.x, pointF.y);
        this.f4351v = m5543c(pointF.x, pointF.y);
        return true;
    }

    public void m5561b(Canvas canvas) {
        m5555m();
        canvas.translate(this.A.x, this.A.y);
        canvas.rotate(this.f4345p);
        if (this.f4339J) {
            m5545d(canvas);
        }
        if (this.f4340K) {
            m5547e(canvas);
        }
        if (this.f4353x != null && this.f4338I) {
            m5548f(canvas);
        }
        if (this.f4353x != null) {
            m5549g(canvas);
        }
        m5550h(canvas);
        m5564c(canvas);
        if (this.f4341L) {
            m5551i(canvas);
        }
    }

    protected void m5562b(boolean z) {
        this.f4338I = z;
    }

    public boolean m5563b(PointF pointF) {
        if (this.h == null || this.f4351v == null) {
            return false;
        }
        PointF pointF2 = new PointF(pointF.x - this.h.x, pointF.y - this.h.y);
        this.g.set(this.g.x + Math.abs(pointF2.x), this.g.y + Math.abs(pointF2.y));
        this.h.set(pointF.x, pointF.y);
        switch (BaseToolWithRectangleShape.m5552j()[this.f4351v.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                m5539a(pointF2.x, pointF2.y);
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                m5544d(pointF2.x, pointF2.y);
                break;
            case Version.API04_DONUT_16 /*4*/:
                m5541b(pointF2.x, pointF2.y);
                break;
        }
        return true;
    }

    protected abstract void m5564c(Canvas canvas);

    protected void m5565c(boolean z) {
        this.f4340K = z;
    }

    public boolean m5566c(PointF pointF) {
        this.f4342M = false;
        if (this.h == null) {
            return false;
        }
        this.g.set(this.g.x + Math.abs(pointF.x - this.h.x), this.g.y + Math.abs(pointF.y - this.h.y));
        if (10.0f >= this.g.x && 10.0f >= this.g.y && m5546d(pointF)) {
            m5567i();
        }
        return true;
    }

    protected abstract void m5567i();
}
