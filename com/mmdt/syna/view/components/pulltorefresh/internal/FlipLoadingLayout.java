package com.mmdt.syna.view.components.pulltorefresh.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView.ScaleType;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0096b;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0101h;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

@SuppressLint({"ViewConstructor"})
public class FlipLoadingLayout extends LoadingLayout {
    private static /* synthetic */ int[] f1937h;
    private final Animation f1938f;
    private final Animation f1939g;

    public FlipLoadingLayout(Context context, C0096b c0096b, C0101h c0101h, TypedArray typedArray) {
        super(context, c0096b, c0101h, typedArray);
        int i = c0096b == C0096b.PULL_FROM_START ? -180 : 180;
        this.f1938f = new RotateAnimation(0.0f, (float) i, 1, 0.5f, 1, 0.5f);
        this.f1938f.setInterpolator(a);
        this.f1938f.setDuration(150);
        this.f1938f.setFillAfter(true);
        this.f1939g = new RotateAnimation((float) i, 0.0f, 1, 0.5f, 1, 0.5f);
        this.f1939g.setInterpolator(a);
        this.f1939g.setDuration(150);
        this.f1939g.setFillAfter(true);
    }

    static /* synthetic */ int[] m2894f() {
        int[] iArr = f1937h;
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
            f1937h = iArr;
        }
        return iArr;
    }

    private float m2895p() {
        switch (m2894f()[this.d.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return this.e == C0101h.HORIZONTAL ? 270.0f : 0.0f;
            case Version.API03_CUPCAKE_15 /*3*/:
                return this.e == C0101h.HORIZONTAL ? 90.0f : 180.0f;
            default:
                return 0.0f;
        }
    }

    protected void m2896a() {
        if (this.f1938f == this.b.getAnimation()) {
            this.b.startAnimation(this.f1939g);
        }
    }

    protected void m2897a(float f) {
    }

    protected void m2898a(Drawable drawable) {
        if (drawable != null) {
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int intrinsicWidth = drawable.getIntrinsicWidth();
            LayoutParams layoutParams = this.b.getLayoutParams();
            int max = Math.max(intrinsicHeight, intrinsicWidth);
            layoutParams.height = max;
            layoutParams.width = max;
            this.b.requestLayout();
            this.b.setScaleType(ScaleType.MATRIX);
            Matrix matrix = new Matrix();
            matrix.postTranslate(((float) (layoutParams.width - intrinsicWidth)) / 2.0f, ((float) (layoutParams.height - intrinsicHeight)) / 2.0f);
            matrix.postRotate(m2895p(), ((float) layoutParams.width) / 2.0f, ((float) layoutParams.height) / 2.0f);
            this.b.setImageMatrix(matrix);
        }
    }

    protected void m2899b() {
        this.b.clearAnimation();
        this.b.setVisibility(4);
        this.c.setVisibility(0);
    }

    protected void m2900c() {
        this.b.startAnimation(this.f1938f);
    }

    protected void m2901d() {
        this.b.clearAnimation();
        this.c.setVisibility(8);
        this.b.setVisibility(0);
    }

    protected int m2902e() {
        return 2130837785;
    }
}
