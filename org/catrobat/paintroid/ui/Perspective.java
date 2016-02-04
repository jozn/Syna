package org.catrobat.paintroid.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import java.io.Serializable;
import org.catrobat.paintroid.PaintroidApplication;

/* renamed from: org.catrobat.paintroid.ui.c */
public class Perspective implements Serializable {
    private float f4531a;
    private float f4532b;
    private float f4533c;
    private float f4534d;
    private float f4535e;
    private float f4536f;
    private float f4537g;
    private float f4538h;
    private float f4539i;
    private float f4540j;
    private boolean f4541k;
    private float f4542l;
    private float f4543m;

    public Perspective(Context context, SurfaceHolder surfaceHolder) {
        m5799a(surfaceHolder);
        this.f4535e = 1.0f;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.f4540j = displayMetrics.density;
        this.f4541k = false;
    }

    public synchronized void m5794a() {
        float f = 1.0f;
        synchronized (this) {
            float f2 = 50.0f * this.f4540j;
            this.f4538h = (float) PaintroidApplication.f4192b.m5783d();
            this.f4539i = (float) PaintroidApplication.f4192b.m5784e();
            this.f4535e = 1.0f;
            if (this.f4531a == 0.0f || this.f4532b == 0.0f) {
                this.f4536f = 0.0f;
                this.f4537g = -f2;
            } else {
                this.f4536f = (this.f4531a / 2.0f) - (this.f4538h / 2.0f);
                this.f4542l = this.f4536f;
                this.f4537g = (this.f4532b / 2.0f) - (this.f4539i / 2.0f);
                this.f4543m = this.f4537g;
            }
            if (!this.f4541k) {
                f = 0.95f;
            }
            this.f4535e = f * m5804c();
        }
    }

    public synchronized void m5795a(float f) {
        if (f >= 0.1f) {
            this.f4535e = f;
        } else {
            this.f4535e = 0.1f;
        }
    }

    public synchronized void m5796a(float f, float f2) {
        this.f4536f += f / this.f4535e;
        this.f4537g += f2 / this.f4535e;
        float f3 = (this.f4538h / 2.0f) + (((this.f4531a / 2.0f) - 50.0f) / this.f4535e);
        if (this.f4536f > this.f4542l + f3) {
            this.f4536f = f3 + this.f4542l;
        } else if (this.f4536f < (-f3) + this.f4542l) {
            this.f4536f = (-f3) + this.f4542l;
        }
        f3 = (this.f4539i / 2.0f) + (((this.f4532b / 2.0f) - 50.0f) / this.f4535e);
        if (this.f4537g > this.f4543m + f3) {
            this.f4537g = f3 + this.f4543m;
        } else if (this.f4537g < (-f3) + this.f4543m) {
            this.f4537g = (-f3) + this.f4543m;
        }
    }

    public synchronized void m5797a(Canvas canvas) {
        canvas.scale(this.f4535e, this.f4535e, this.f4533c, this.f4534d);
        canvas.translate(this.f4536f, this.f4537g);
    }

    @Deprecated
    public synchronized void m5798a(PointF pointF) {
        pointF.x = (((pointF.x - this.f4533c) / this.f4535e) + this.f4533c) - this.f4536f;
        pointF.y = (((pointF.y - this.f4534d) / this.f4535e) + this.f4534d) - this.f4537g;
    }

    public synchronized void m5799a(SurfaceHolder surfaceHolder) {
        Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
        this.f4531a = (float) surfaceFrame.right;
        this.f4533c = surfaceFrame.exactCenterX();
        this.f4532b = (float) surfaceFrame.bottom;
        this.f4534d = surfaceFrame.exactCenterY();
        m5794a();
    }

    public void m5800a(boolean z) {
        this.f4541k = z;
        m5794a();
    }

    public float m5801b() {
        return this.f4535e;
    }

    public synchronized PointF m5802b(PointF pointF) {
        return new PointF((((pointF.x - this.f4533c) / this.f4535e) + this.f4533c) - this.f4536f, (((pointF.y - this.f4534d) / this.f4535e) + this.f4534d) - this.f4537g);
    }

    public synchronized void m5803b(float f) {
        this.f4535e *= f;
        if (this.f4535e < 0.1f) {
            this.f4535e = 0.1f;
        } else if (this.f4535e > 100.0f) {
            this.f4535e = 100.0f;
        }
    }

    public float m5804c() {
        float f = 1.0f;
        float f2 = this.f4531a / this.f4532b > this.f4538h / this.f4539i ? this.f4532b / this.f4539i : this.f4531a / this.f4538h;
        if (f2 <= 1.0f) {
            f = f2;
        }
        return f < 0.1f ? 0.1f : f;
    }

    @Deprecated
    public synchronized void m5805c(PointF pointF) {
        pointF.x = (((pointF.x + this.f4536f) - this.f4533c) * this.f4535e) + this.f4533c;
        pointF.y = (((pointF.y + this.f4537g) - this.f4534d) * this.f4535e) + this.f4534d;
    }

    public synchronized PointF m5806d(PointF pointF) {
        return new PointF((((pointF.x + this.f4536f) - this.f4533c) * this.f4535e) + this.f4533c, (((pointF.y + this.f4537g) - this.f4534d) * this.f4535e) + this.f4534d);
    }
}
