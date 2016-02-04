package com.edmodo.cropper.cropwindow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import com.edmodo.cropper.cropwindow.p015a.Edge;
import com.edmodo.cropper.cropwindow.p016b.Handle;
import com.edmodo.cropper.p014a.AspectRatioUtil;
import com.edmodo.cropper.p014a.HandleUtil;
import com.edmodo.cropper.p014a.PaintUtil;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class CropOverlayView extends View {
    private static final float f1439a;
    private static final float f1440b;
    private static final float f1441c;
    private static final float f1442d;
    private Paint f1443e;
    private Paint f1444f;
    private Paint f1445g;
    private Paint f1446h;
    private Rect f1447i;
    private float f1448j;
    private float f1449k;
    private Pair<Float, Float> f1450l;
    private Handle f1451m;
    private boolean f1452n;
    private int f1453o;
    private int f1454p;
    private float f1455q;
    private int f1456r;
    private boolean f1457s;
    private float f1458t;
    private float f1459u;
    private float f1460v;
    private float f1461w;

    static {
        f1439a = PaintUtil.m2246b();
        f1440b = PaintUtil.m2248c();
        f1441c = (f1439a / 2.0f) - (f1440b / 2.0f);
        f1442d = (f1439a / 2.0f) + f1441c;
    }

    public CropOverlayView(Context context) {
        super(context);
        this.f1452n = false;
        this.f1453o = 1;
        this.f1454p = 1;
        this.f1455q = ((float) this.f1453o) / ((float) this.f1454p);
        this.f1457s = false;
        this.f1461w = 0.1f;
        m2251a(context);
    }

    public CropOverlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1452n = false;
        this.f1453o = 1;
        this.f1454p = 1;
        this.f1455q = ((float) this.f1453o) / ((float) this.f1454p);
        this.f1457s = false;
        this.f1461w = 0.1f;
        m2251a(context);
    }

    private void m2250a(float f, float f2) {
        float a = Edge.LEFT.m2268a();
        float a2 = Edge.TOP.m2268a();
        float a3 = Edge.RIGHT.m2268a();
        float a4 = Edge.BOTTOM.m2268a();
        this.f1451m = HandleUtil.m2234a(f, f2, a, a2, a3, a4, this.f1448j);
        if (this.f1451m != null) {
            this.f1450l = HandleUtil.m2233a(this.f1451m, f, f2, a, a2, a3, a4);
            invalidate();
        }
    }

    private void m2251a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f1448j = HandleUtil.m2232a(context);
        this.f1449k = TypedValue.applyDimension(1, 6.0f, displayMetrics);
        this.f1443e = PaintUtil.m2245a(context);
        this.f1444f = PaintUtil.m2244a();
        this.f1446h = PaintUtil.m2247b(context);
        this.f1445g = PaintUtil.m2249c(context);
        this.f1459u = TypedValue.applyDimension(1, f1441c, displayMetrics);
        this.f1458t = TypedValue.applyDimension(1, f1442d, displayMetrics);
        this.f1460v = TypedValue.applyDimension(1, 20.0f, displayMetrics);
        this.f1456r = 1;
    }

    private void m2252a(Canvas canvas) {
        float a = Edge.LEFT.m2268a();
        float a2 = Edge.TOP.m2268a();
        float a3 = Edge.RIGHT.m2268a();
        float a4 = Edge.BOTTOM.m2268a();
        float b = Edge.m2262b() / 3.0f;
        float f = a + b;
        canvas.drawLine(f, a2, f, a4, this.f1444f);
        f = a3 - b;
        canvas.drawLine(f, a2, f, a4, this.f1444f);
        float c = Edge.m2264c() / 3.0f;
        b = a2 + c;
        canvas.drawLine(a, b, a3, b, this.f1444f);
        b = a4 - c;
        canvas.drawLine(a, b, a3, b, this.f1444f);
    }

    private void m2253a(Canvas canvas, Rect rect) {
        float a = Edge.LEFT.m2268a();
        float a2 = Edge.TOP.m2268a();
        float a3 = Edge.RIGHT.m2268a();
        float a4 = Edge.BOTTOM.m2268a();
        canvas.drawRect((float) rect.left, (float) rect.top, (float) rect.right, a2, this.f1446h);
        canvas.drawRect((float) rect.left, a4, (float) rect.right, (float) rect.bottom, this.f1446h);
        canvas.drawRect((float) rect.left, a2, a, a4, this.f1446h);
        canvas.drawRect(a3, a2, (float) rect.right, a4, this.f1446h);
    }

    private void m2254a(Rect rect) {
        if (!this.f1457s) {
            this.f1457s = true;
        }
        float width;
        float height;
        if (!this.f1452n) {
            width = this.f1461w * ((float) rect.width());
            height = this.f1461w * ((float) rect.height());
            Edge.LEFT.m2270a(((float) rect.left) + width);
            Edge.TOP.m2270a(((float) rect.top) + height);
            Edge.RIGHT.m2270a(((float) rect.right) - width);
            Edge.BOTTOM.m2270a(((float) rect.bottom) - height);
        } else if (AspectRatioUtil.m2226a(rect) > this.f1455q) {
            Edge.TOP.m2270a((float) rect.top);
            Edge.BOTTOM.m2270a((float) rect.bottom);
            width = ((float) getWidth()) / 2.0f;
            height = Math.max(40.0f, AspectRatioUtil.m2224a(Edge.TOP.m2268a(), Edge.BOTTOM.m2268a(), this.f1455q));
            if (height == 40.0f) {
                this.f1455q = 40.0f / (Edge.BOTTOM.m2268a() - Edge.TOP.m2268a());
            }
            height /= 2.0f;
            Edge.LEFT.m2270a(width - height);
            Edge.RIGHT.m2270a(width + height);
        } else {
            Edge.LEFT.m2270a((float) rect.left);
            Edge.RIGHT.m2270a((float) rect.right);
            width = ((float) getHeight()) / 2.0f;
            height = Math.max(40.0f, AspectRatioUtil.m2227b(Edge.LEFT.m2268a(), Edge.RIGHT.m2268a(), this.f1455q));
            if (height == 40.0f) {
                this.f1455q = (Edge.RIGHT.m2268a() - Edge.LEFT.m2268a()) / 40.0f;
            }
            height /= 2.0f;
            Edge.TOP.m2270a(width - height);
            Edge.BOTTOM.m2270a(width + height);
        }
    }

    private void m2255b(float f, float f2) {
        if (this.f1451m != null) {
            float floatValue = f + ((Float) this.f1450l.first).floatValue();
            float floatValue2 = f2 + ((Float) this.f1450l.second).floatValue();
            if (this.f1452n) {
                this.f1451m.m2285a(floatValue, floatValue2, this.f1455q, this.f1447i, this.f1449k);
            } else {
                this.f1451m.m2286a(floatValue, floatValue2, this.f1447i, this.f1449k);
            }
            invalidate();
        }
    }

    private void m2256b(Canvas canvas) {
        float a = Edge.LEFT.m2268a();
        float a2 = Edge.TOP.m2268a();
        float a3 = Edge.RIGHT.m2268a();
        float a4 = Edge.BOTTOM.m2268a();
        canvas.drawLine(a - this.f1459u, a2 - this.f1458t, a - this.f1459u, a2 + this.f1460v, this.f1445g);
        canvas.drawLine(a, a2 - this.f1459u, a + this.f1460v, a2 - this.f1459u, this.f1445g);
        canvas.drawLine(a3 + this.f1459u, a2 - this.f1458t, a3 + this.f1459u, a2 + this.f1460v, this.f1445g);
        canvas.drawLine(a3, a2 - this.f1459u, a3 - this.f1460v, a2 - this.f1459u, this.f1445g);
        canvas.drawLine(a - this.f1459u, a4 + this.f1458t, a - this.f1459u, a4 - this.f1460v, this.f1445g);
        canvas.drawLine(a, a4 + this.f1459u, a + this.f1460v, a4 + this.f1459u, this.f1445g);
        canvas.drawLine(a3 + this.f1459u, a4 + this.f1458t, a3 + this.f1459u, a4 - this.f1460v, this.f1445g);
        canvas.drawLine(a3, a4 + this.f1459u, a3 - this.f1460v, a4 + this.f1459u, this.f1445g);
    }

    public static boolean m2257b() {
        return Math.abs(Edge.LEFT.m2268a() - Edge.RIGHT.m2268a()) >= 100.0f && Math.abs(Edge.TOP.m2268a() - Edge.BOTTOM.m2268a()) >= 100.0f;
    }

    private void m2258c() {
        if (this.f1451m != null) {
            this.f1451m = null;
            invalidate();
        }
    }

    public void m2259a() {
        if (this.f1457s) {
            m2254a(this.f1447i);
            invalidate();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m2253a(canvas, this.f1447i);
        if (m2257b()) {
            if (this.f1456r == 2) {
                m2252a(canvas);
            } else if (this.f1456r == 1 && this.f1451m != null) {
                m2252a(canvas);
            }
        }
        canvas.drawRect(Edge.LEFT.m2268a(), Edge.TOP.m2268a(), Edge.RIGHT.m2268a(), Edge.BOTTOM.m2268a(), this.f1443e);
        m2256b(canvas);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        m2254a(this.f1447i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case VideoSize.QCIF /*0*/:
                m2250a(motionEvent.getX(), motionEvent.getY());
                return true;
            case VideoSize.CIF /*1*/:
            case Version.API03_CUPCAKE_15 /*3*/:
                getParent().requestDisallowInterceptTouchEvent(false);
                m2258c();
                return true;
            case VideoSize.HVGA /*2*/:
                m2255b(motionEvent.getX(), motionEvent.getY());
                getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            default:
                return false;
        }
    }

    public void setAspectRatioX(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f1453o = i;
        this.f1455q = ((float) this.f1453o) / ((float) this.f1454p);
        if (this.f1457s) {
            m2254a(this.f1447i);
            invalidate();
        }
    }

    public void setAspectRatioY(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f1454p = i;
        this.f1455q = ((float) this.f1453o) / ((float) this.f1454p);
        if (this.f1457s) {
            m2254a(this.f1447i);
            invalidate();
        }
    }

    public void setBitmapRect(Rect rect) {
        this.f1447i = rect;
        m2254a(this.f1447i);
    }

    public void setDefaultPaddingInPercent(float f) {
        this.f1461w = f;
    }

    public void setFixedAspectRatio(boolean z) {
        this.f1452n = z;
        if (this.f1457s) {
            m2254a(this.f1447i);
            invalidate();
        }
    }

    public void setGuidelines(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Guideline value must be set between 0 and 2. See documentation.");
        }
        this.f1456r = i;
        if (this.f1457s) {
            m2254a(this.f1447i);
            invalidate();
        }
    }

    public void setInitialAttributeValues(int i, boolean z, int i2, int i3) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Guideline value must be set between 0 and 2. See documentation.");
        }
        this.f1456r = i;
        this.f1452n = z;
        if (i2 <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f1453o = i2;
        this.f1455q = ((float) this.f1453o) / ((float) this.f1454p);
        if (i3 <= 0) {
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
        }
        this.f1454p = i3;
        this.f1455q = ((float) this.f1453o) / ((float) this.f1454p);
    }
}
