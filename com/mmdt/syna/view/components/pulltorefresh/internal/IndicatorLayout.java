package com.mmdt.syna.view.components.pulltorefresh.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0096b;
import org.linphone.mediastream.Version;

@SuppressLint({"ViewConstructor"})
public class IndicatorLayout extends FrameLayout implements AnimationListener {
    private static /* synthetic */ int[] f1940f;
    private Animation f1941a;
    private Animation f1942b;
    private ImageView f1943c;
    private final Animation f1944d;
    private final Animation f1945e;

    public IndicatorLayout(Context context, C0096b c0096b) {
        int i;
        super(context);
        this.f1943c = new ImageView(context);
        Drawable drawable = getResources().getDrawable(2130838147);
        this.f1943c.setImageDrawable(drawable);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131296303);
        this.f1943c.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        addView(this.f1943c);
        switch (m2903f()[c0096b.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                i = 2130968587;
                dimensionPixelSize = 2130968595;
                setBackgroundResource(2130838148);
                this.f1943c.setScaleType(ScaleType.MATRIX);
                Matrix matrix = new Matrix();
                matrix.setRotate(180.0f, ((float) drawable.getIntrinsicWidth()) / 2.0f, ((float) drawable.getIntrinsicHeight()) / 2.0f);
                this.f1943c.setImageMatrix(matrix);
                break;
            default:
                i = 2130968588;
                dimensionPixelSize = 2130968596;
                setBackgroundResource(2130838149);
                break;
        }
        this.f1941a = AnimationUtils.loadAnimation(context, i);
        this.f1941a.setAnimationListener(this);
        this.f1942b = AnimationUtils.loadAnimation(context, dimensionPixelSize);
        this.f1942b.setAnimationListener(this);
        Interpolator linearInterpolator = new LinearInterpolator();
        this.f1944d = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.f1944d.setInterpolator(linearInterpolator);
        this.f1944d.setDuration(150);
        this.f1944d.setFillAfter(true);
        this.f1945e = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.f1945e.setInterpolator(linearInterpolator);
        this.f1945e.setDuration(150);
        this.f1945e.setFillAfter(true);
    }

    static /* synthetic */ int[] m2903f() {
        int[] iArr = f1940f;
        if (iArr == null) {
            iArr = new int[C0096b.values().length];
            try {
                iArr[C0096b.BOTH.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0096b.DISABLED.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[C0096b.MANUAL_REFRESH_ONLY.ordinal()] = 5;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[C0096b.PULL_FROM_END.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[C0096b.PULL_FROM_START.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            f1940f = iArr;
        }
        return iArr;
    }

    public final boolean m2904a() {
        Animation animation = getAnimation();
        return animation != null ? this.f1941a == animation : getVisibility() == 0;
    }

    public void m2905b() {
        startAnimation(this.f1942b);
    }

    public void m2906c() {
        this.f1943c.clearAnimation();
        startAnimation(this.f1941a);
    }

    public void m2907d() {
        this.f1943c.startAnimation(this.f1944d);
    }

    public void m2908e() {
        this.f1943c.startAnimation(this.f1945e);
    }

    public void onAnimationEnd(Animation animation) {
        if (animation == this.f1942b) {
            this.f1943c.clearAnimation();
            setVisibility(8);
        } else if (animation == this.f1941a) {
            setVisibility(0);
        }
        clearAnimation();
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationStart(Animation animation) {
        setVisibility(0);
    }
}
