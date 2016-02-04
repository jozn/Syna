package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* renamed from: android.support.v4.widget.n */
final class SwipeProgressBar {
    private static final Interpolator f695a;
    private final Paint f696b;
    private final RectF f697c;
    private float f698d;
    private long f699e;
    private long f700f;
    private boolean f701g;
    private int f702h;
    private int f703i;
    private int f704j;
    private int f705k;
    private View f706l;
    private Rect f707m;

    static {
        f695a = BakedBezierInterpolator.m1300a();
    }

    public SwipeProgressBar(View view) {
        this.f696b = new Paint();
        this.f697c = new RectF();
        this.f707m = new Rect();
        this.f706l = view;
        this.f702h = -1291845632;
        this.f703i = Integer.MIN_VALUE;
        this.f704j = 1291845632;
        this.f705k = 436207616;
    }

    private void m1400a(Canvas canvas, float f, float f2, int i, float f3) {
        this.f696b.setColor(i);
        canvas.save();
        canvas.translate(f, f2);
        float interpolation = f695a.getInterpolation(f3);
        canvas.scale(interpolation, interpolation);
        canvas.drawCircle(0.0f, 0.0f, f, this.f696b);
        canvas.restore();
    }

    private void m1401a(Canvas canvas, int i, int i2) {
        this.f696b.setColor(this.f702h);
        canvas.drawCircle((float) i, (float) i2, ((float) i) * this.f698d, this.f696b);
    }

    void m1402a() {
        if (!this.f701g) {
            this.f698d = 0.0f;
            this.f699e = AnimationUtils.currentAnimationTimeMillis();
            this.f701g = true;
            this.f706l.postInvalidate();
        }
    }

    void m1403a(float f) {
        this.f698d = f;
        this.f699e = 0;
        ViewCompat.m1145b(this.f706l);
    }

    void m1404a(int i, int i2, int i3, int i4) {
        this.f702h = i;
        this.f703i = i2;
        this.f704j = i3;
        this.f705k = i4;
    }

    void m1405a(Canvas canvas) {
        int width = this.f707m.width();
        int height = this.f707m.height();
        int i = width / 2;
        int i2 = height / 2;
        int save = canvas.save();
        canvas.clipRect(this.f707m);
        if (this.f701g || this.f700f > 0) {
            Canvas canvas2;
            int i3;
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            long j = (currentAnimationTimeMillis - this.f699e) / 2000;
            float f = ((float) ((currentAnimationTimeMillis - this.f699e) % 2000)) / 20.0f;
            Object obj;
            if (this.f701g) {
                obj = null;
            } else {
                if (currentAnimationTimeMillis - this.f700f >= 1000) {
                    this.f700f = 0;
                    return;
                }
                float f2 = (float) (width / 2);
                float interpolation = f695a.getInterpolation((((float) ((currentAnimationTimeMillis - this.f700f) % 1000)) / 10.0f) / 100.0f) * f2;
                this.f697c.set(((float) i) - interpolation, 0.0f, interpolation + ((float) i), (float) height);
                canvas.saveLayerAlpha(this.f697c, 0, 0);
                obj = 1;
            }
            if (j == 0) {
                canvas.drawColor(this.f702h);
            } else if (f >= 0.0f && f < 25.0f) {
                canvas.drawColor(this.f705k);
            } else if (f >= 25.0f && f < 50.0f) {
                canvas.drawColor(this.f702h);
            } else if (f < 50.0f || f >= 75.0f) {
                canvas.drawColor(this.f704j);
            } else {
                canvas.drawColor(this.f703i);
            }
            if (f >= 0.0f && f <= 25.0f) {
                canvas2 = canvas;
                m1400a(canvas2, (float) i, (float) i2, this.f702h, ((25.0f + f) * 2.0f) / 100.0f);
            }
            if (f >= 0.0f && f <= 50.0f) {
                canvas2 = canvas;
                m1400a(canvas2, (float) i, (float) i2, this.f703i, (2.0f * f) / 100.0f);
            }
            if (f >= 25.0f && f <= 75.0f) {
                canvas2 = canvas;
                m1400a(canvas2, (float) i, (float) i2, this.f704j, ((f - 25.0f) * 2.0f) / 100.0f);
            }
            if (f >= 50.0f && f <= 100.0f) {
                canvas2 = canvas;
                m1400a(canvas2, (float) i, (float) i2, this.f705k, ((f - 50.0f) * 2.0f) / 100.0f);
            }
            if (f >= 75.0f && f <= 100.0f) {
                canvas2 = canvas;
                m1400a(canvas2, (float) i, (float) i2, this.f702h, ((f - 75.0f) * 2.0f) / 100.0f);
            }
            if (this.f698d <= 0.0f || r9 == null) {
                i3 = save;
            } else {
                canvas.restoreToCount(save);
                i3 = canvas.save();
                canvas.clipRect(this.f707m);
                m1401a(canvas, i, i2);
            }
            ViewCompat.m1145b(this.f706l);
            save = i3;
        } else if (this.f698d > 0.0f && ((double) this.f698d) <= 1.0d) {
            m1401a(canvas, i, i2);
        }
        canvas.restoreToCount(save);
    }

    void m1406b() {
        if (this.f701g) {
            this.f698d = 0.0f;
            this.f700f = AnimationUtils.currentAnimationTimeMillis();
            this.f701g = false;
            this.f706l.postInvalidate();
        }
    }

    void m1407b(int i, int i2, int i3, int i4) {
        this.f707m.left = i;
        this.f707m.top = i2;
        this.f707m.right = i3;
        this.f707m.bottom = i4;
    }
}
