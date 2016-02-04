package com.mmdt.syna.view.components.pulltorefresh.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mmdt.syna.view.components.pulltorefresh.ILoadingLayout;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0096b;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshBase.C0101h;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

@SuppressLint({"ViewConstructor"})
public abstract class LoadingLayout extends FrameLayout implements ILoadingLayout {
    static final Interpolator f1923a;
    private static /* synthetic */ int[] f1924m;
    private static /* synthetic */ int[] f1925n;
    protected final ImageView f1926b;
    protected final ProgressBar f1927c;
    protected final C0096b f1928d;
    protected final C0101h f1929e;
    private FrameLayout f1930f;
    private boolean f1931g;
    private final TextView f1932h;
    private final TextView f1933i;
    private CharSequence f1934j;
    private CharSequence f1935k;
    private CharSequence f1936l;

    static {
        f1923a = new LinearInterpolator();
    }

    public LoadingLayout(Context context, C0096b c0096b, C0101h c0101h, TypedArray typedArray) {
        Drawable drawable;
        ColorStateList colorStateList;
        super(context);
        this.f1928d = c0096b;
        this.f1929e = c0101h;
        switch (m2877n()[c0101h.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                LayoutInflater.from(context).inflate(2130903175, this);
                break;
            default:
                LayoutInflater.from(context).inflate(2130903176, this);
                break;
        }
        this.f1930f = (FrameLayout) findViewById(2131427781);
        this.f1932h = (TextView) this.f1930f.findViewById(2131427784);
        this.f1927c = (ProgressBar) this.f1930f.findViewById(2131427783);
        this.f1933i = (TextView) this.f1930f.findViewById(2131427785);
        this.f1926b = (ImageView) this.f1930f.findViewById(2131427782);
        LayoutParams layoutParams = (LayoutParams) this.f1930f.getLayoutParams();
        switch (m2878o()[c0096b.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                layoutParams.gravity = c0101h == C0101h.VERTICAL ? 48 : 3;
                this.f1934j = context.getString(2131493467);
                this.f1935k = context.getString(2131493469);
                this.f1936l = context.getString(2131493468);
                break;
            default:
                layoutParams.gravity = c0101h == C0101h.VERTICAL ? 80 : 5;
                this.f1934j = context.getString(2131493687);
                this.f1935k = context.getString(2131493689);
                this.f1936l = context.getString(2131493688);
                break;
        }
        if (typedArray.hasValue(1)) {
            drawable = typedArray.getDrawable(1);
            if (drawable != null) {
                ViewCompat.m2920a((View) this, drawable);
            }
        }
        if (typedArray.hasValue(10)) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(10, typedValue);
            m2875b(typedValue.data);
        }
        if (typedArray.hasValue(11)) {
            typedValue = new TypedValue();
            typedArray.getValue(11, typedValue);
            m2872a(typedValue.data);
        }
        if (typedArray.hasValue(2)) {
            colorStateList = typedArray.getColorStateList(2);
            if (colorStateList != null) {
                m2876b(colorStateList);
            }
        }
        if (typedArray.hasValue(3)) {
            colorStateList = typedArray.getColorStateList(3);
            if (colorStateList != null) {
                m2873a(colorStateList);
            }
        }
        drawable = null;
        if (typedArray.hasValue(6)) {
            drawable = typedArray.getDrawable(6);
        }
        switch (m2878o()[c0096b.ordinal()]) {
            case Version.API03_CUPCAKE_15 /*3*/:
                if (!typedArray.hasValue(8)) {
                    if (typedArray.hasValue(18)) {
                        Utils.m2917a("ptrDrawableBottom", "ptrDrawableEnd");
                        drawable = typedArray.getDrawable(18);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(8);
                break;
                break;
            default:
                if (!typedArray.hasValue(7)) {
                    if (typedArray.hasValue(17)) {
                        Utils.m2917a("ptrDrawableTop", "ptrDrawableStart");
                        drawable = typedArray.getDrawable(17);
                        break;
                    }
                }
                drawable = typedArray.getDrawable(7);
                break;
                break;
        }
        if (drawable == null) {
            drawable = context.getResources().getDrawable(m2886e());
        }
        setLoadingDrawable(drawable);
        m2892l();
    }

    private void m2872a(int i) {
        if (this.f1933i != null) {
            this.f1933i.setTextAppearance(getContext(), i);
        }
    }

    private void m2873a(ColorStateList colorStateList) {
        if (this.f1933i != null) {
            this.f1933i.setTextColor(colorStateList);
        }
    }

    private void m2874a(CharSequence charSequence) {
        if (this.f1933i == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.f1933i.setVisibility(8);
            return;
        }
        this.f1933i.setText(charSequence);
        if (8 == this.f1933i.getVisibility()) {
            this.f1933i.setVisibility(0);
        }
    }

    private void m2875b(int i) {
        if (this.f1932h != null) {
            this.f1932h.setTextAppearance(getContext(), i);
        }
        if (this.f1933i != null) {
            this.f1933i.setTextAppearance(getContext(), i);
        }
    }

    private void m2876b(ColorStateList colorStateList) {
        if (this.f1932h != null) {
            this.f1932h.setTextColor(colorStateList);
        }
        if (this.f1933i != null) {
            this.f1933i.setTextColor(colorStateList);
        }
    }

    static /* synthetic */ int[] m2877n() {
        int[] iArr = f1924m;
        if (iArr == null) {
            iArr = new int[C0101h.values().length];
            try {
                iArr[C0101h.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0101h.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            f1924m = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] m2878o() {
        int[] iArr = f1925n;
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
            f1925n = iArr;
        }
        return iArr;
    }

    protected abstract void m2879a();

    protected abstract void m2880a(float f);

    protected abstract void m2881a(Drawable drawable);

    protected abstract void m2882b();

    public final void m2883b(float f) {
        if (!this.f1931g) {
            m2880a(f);
        }
    }

    protected abstract void m2884c();

    protected abstract void m2885d();

    protected abstract int m2886e();

    public final int m2887g() {
        switch (m2877n()[this.f1929e.ordinal()]) {
            case VideoSize.HVGA /*2*/:
                return this.f1930f.getWidth();
            default:
                return this.f1930f.getHeight();
        }
    }

    public final void m2888h() {
        if (this.f1932h.getVisibility() == 0) {
            this.f1932h.setVisibility(4);
        }
        if (this.f1927c.getVisibility() == 0) {
            this.f1927c.setVisibility(4);
        }
        if (this.f1926b.getVisibility() == 0) {
            this.f1926b.setVisibility(4);
        }
        if (this.f1933i.getVisibility() == 0) {
            this.f1933i.setVisibility(4);
        }
    }

    public final void m2889i() {
        if (this.f1932h != null) {
            this.f1932h.setText(this.f1934j);
        }
        m2879a();
    }

    public final void m2890j() {
        if (this.f1932h != null) {
            this.f1932h.setText(this.f1935k);
        }
        if (this.f1931g) {
            ((AnimationDrawable) this.f1926b.getDrawable()).start();
        } else {
            m2882b();
        }
        if (this.f1933i != null) {
            this.f1933i.setVisibility(8);
        }
    }

    public final void m2891k() {
        if (this.f1932h != null) {
            this.f1932h.setText(this.f1936l);
        }
        m2884c();
    }

    public final void m2892l() {
        if (this.f1932h != null) {
            this.f1932h.setText(this.f1934j);
        }
        this.f1926b.setVisibility(0);
        if (this.f1931g) {
            ((AnimationDrawable) this.f1926b.getDrawable()).stop();
        } else {
            m2885d();
        }
        if (this.f1933i == null) {
            return;
        }
        if (TextUtils.isEmpty(this.f1933i.getText())) {
            this.f1933i.setVisibility(8);
        } else {
            this.f1933i.setVisibility(0);
        }
    }

    public final void m2893m() {
        if (4 == this.f1932h.getVisibility()) {
            this.f1932h.setVisibility(0);
        }
        if (4 == this.f1927c.getVisibility()) {
            this.f1927c.setVisibility(0);
        }
        if (4 == this.f1926b.getVisibility()) {
            this.f1926b.setVisibility(0);
        }
        if (4 == this.f1933i.getVisibility()) {
            this.f1933i.setVisibility(0);
        }
    }

    public final void setHeight(int i) {
        getLayoutParams().height = i;
        requestLayout();
    }

    public void setLastUpdatedLabel(CharSequence charSequence) {
        m2874a(charSequence);
    }

    public final void setLoadingDrawable(Drawable drawable) {
        this.f1926b.setImageDrawable(drawable);
        this.f1931g = drawable instanceof AnimationDrawable;
        m2881a(drawable);
    }

    public void setPullLabel(CharSequence charSequence) {
        this.f1934j = charSequence;
    }

    public void setRefreshingLabel(CharSequence charSequence) {
        this.f1935k = charSequence;
    }

    public void setReleaseLabel(CharSequence charSequence) {
        this.f1936l = charSequence;
    }

    public void setTextTypeface(Typeface typeface) {
        this.f1932h.setTypeface(typeface);
    }

    public final void setWidth(int i) {
        getLayoutParams().width = i;
        requestLayout();
    }
}
