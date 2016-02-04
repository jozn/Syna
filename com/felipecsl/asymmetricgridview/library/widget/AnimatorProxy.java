package com.felipecsl.asymmetricgridview.library.widget;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.util.FloatMath;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* renamed from: com.felipecsl.asymmetricgridview.library.widget.a */
public final class AnimatorProxy extends Animation {
    public static final boolean f1552a;
    private static final WeakHashMap<View, AnimatorProxy> f1553b;
    private final WeakReference<View> f1554c;
    private float f1555d;
    private float f1556e;
    private float f1557f;
    private float f1558g;
    private float f1559h;
    private final RectF f1560i;
    private final RectF f1561j;
    private final Matrix f1562k;

    static {
        f1552a = VERSION.SDK_INT < 11;
        f1553b = new WeakHashMap();
    }

    private AnimatorProxy(View view) {
        this.f1555d = 1.0f;
        this.f1556e = 1.0f;
        this.f1557f = 1.0f;
        this.f1560i = new RectF();
        this.f1561j = new RectF();
        this.f1562k = new Matrix();
        setDuration(0);
        setFillAfter(true);
        view.setAnimation(this);
        this.f1554c = new WeakReference(view);
    }

    public static AnimatorProxy m2337a(View view) {
        AnimatorProxy animatorProxy = (AnimatorProxy) f1553b.get(view);
        if (animatorProxy != null) {
            return animatorProxy;
        }
        animatorProxy = new AnimatorProxy(view);
        f1553b.put(view, animatorProxy);
        return animatorProxy;
    }

    private void m2338a(Matrix matrix, View view) {
        float width = (float) view.getWidth();
        float height = (float) view.getHeight();
        float f = this.f1556e;
        float f2 = this.f1557f;
        if (!(f == 1.0f && f2 == 1.0f)) {
            width = ((f * width) - width) / 2.0f;
            height = ((f2 * height) - height) / 2.0f;
            matrix.postScale(f, f2);
            matrix.postTranslate(-width, -height);
        }
        matrix.postTranslate(this.f1558g, this.f1559h);
    }

    private void m2339a(RectF rectF, View view) {
        rectF.set(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
        Matrix matrix = this.f1562k;
        matrix.reset();
        m2338a(matrix, view);
        this.f1562k.mapRect(rectF);
        rectF.offset((float) view.getLeft(), (float) view.getTop());
        if (rectF.right < rectF.left) {
            float f = rectF.right;
            rectF.right = rectF.left;
            rectF.left = f;
        }
        if (rectF.bottom < rectF.top) {
            f = rectF.top;
            rectF.top = rectF.bottom;
            rectF.bottom = f;
        }
    }

    private void m2340c() {
        View view = (View) this.f1554c.get();
        if (view != null) {
            m2339a(this.f1560i, view);
        }
    }

    private void m2341d() {
        View view = (View) this.f1554c.get();
        if (view != null) {
            View view2 = (View) view.getParent();
            if (view2 != null) {
                view.setAnimation(this);
                RectF rectF = this.f1561j;
                m2339a(rectF, view);
                rectF.union(this.f1560i);
                view2.invalidate((int) FloatMath.floor(rectF.left), (int) FloatMath.floor(rectF.top), (int) FloatMath.ceil(rectF.right), (int) FloatMath.ceil(rectF.bottom));
            }
        }
    }

    public float m2342a() {
        return this.f1555d;
    }

    public void m2343a(float f) {
        if (this.f1555d != f) {
            this.f1555d = f;
            View view = (View) this.f1554c.get();
            if (view != null) {
                view.invalidate();
            }
        }
    }

    protected void applyTransformation(float f, Transformation transformation) {
        View view = (View) this.f1554c.get();
        if (view != null) {
            transformation.setAlpha(this.f1555d);
            m2338a(transformation.getMatrix(), view);
        }
    }

    public float m2344b() {
        return this.f1558g;
    }

    public void m2345b(float f) {
        if (this.f1558g != f) {
            m2340c();
            this.f1558g = f;
            m2341d();
        }
    }

    public void reset() {
    }
}
