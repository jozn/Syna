package com.mmdt.syna.view.components.pulltorefresh.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView.ScaleType;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0096b;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0101h;

public class RotateLoadingLayout extends LoadingLayout {
    private final Animation f1946f;
    private final Matrix f1947g;
    private float f1948h;
    private float f1949i;
    private final boolean f1950j;

    public RotateLoadingLayout(Context context, C0096b c0096b, C0101h c0101h, TypedArray typedArray) {
        super(context, c0096b, c0101h, typedArray);
        this.f1950j = typedArray.getBoolean(15, true);
        this.b.setScaleType(ScaleType.MATRIX);
        this.f1947g = new Matrix();
        this.b.setImageMatrix(this.f1947g);
        this.f1946f = new RotateAnimation(0.0f, 720.0f, 1, 0.5f, 1, 0.5f);
        this.f1946f.setInterpolator(a);
        this.f1946f.setDuration(1200);
        this.f1946f.setRepeatCount(-1);
        this.f1946f.setRepeatMode(1);
    }

    private void m2909f() {
        if (this.f1947g != null) {
            this.f1947g.reset();
            this.b.setImageMatrix(this.f1947g);
        }
    }

    protected void m2910a() {
    }

    protected void m2911a(float f) {
        this.f1947g.setRotate(this.f1950j ? 90.0f * f : Math.max(0.0f, Math.min(180.0f, (360.0f * f) - 180.0f)), this.f1948h, this.f1949i);
        this.b.setImageMatrix(this.f1947g);
    }

    public void m2912a(Drawable drawable) {
        if (drawable != null) {
            this.f1948h = (float) Math.round(((float) drawable.getIntrinsicWidth()) / 2.0f);
            this.f1949i = (float) Math.round(((float) drawable.getIntrinsicHeight()) / 2.0f);
        }
    }

    protected void m2913b() {
        this.b.startAnimation(this.f1946f);
    }

    protected void m2914c() {
    }

    protected void m2915d() {
        this.b.clearAnimation();
        m2909f();
    }

    protected int m2916e() {
        return 2130837786;
    }
}
