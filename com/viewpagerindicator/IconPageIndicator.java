package com.viewpagerindicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.C0010e;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.viewpagerindicator.R.R;

public class IconPageIndicator extends HorizontalScrollView implements PageIndicator {
    private final IcsLinearLayout f3191a;
    private ViewPager f3192b;
    private C0010e f3193c;
    private Runnable f3194d;
    private int f3195e;
    private LayoutInflater f3196f;

    public IconPageIndicator(Context context) {
        this(context, null);
    }

    public IconPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setHorizontalScrollBarEnabled(false);
        this.f3196f = LayoutInflater.from(getContext());
        this.f3191a = new IcsLinearLayout(context, R.vpiIconPageIndicatorStyle);
        addView(this.f3191a, new LayoutParams(-1, -1, 3));
        setPadding(10, 3, 10, 3);
    }

    private void m4037c(int i) {
        View childAt = this.f3191a.getChildAt(i);
        if (this.f3194d != null) {
            removeCallbacks(this.f3194d);
        }
        this.f3194d = new IconPageIndicator(this, childAt);
        post(this.f3194d);
    }

    public void m4038a() {
        this.f3191a.removeAllViews();
        IconPagerAdapter iconPagerAdapter = (IconPagerAdapter) this.f3192b.m597b();
        int b = iconPagerAdapter.m3130b();
        for (int i = 0; i < b; i++) {
            StickersIndicatorItemDataHolder e = iconPagerAdapter.m3131e(i);
            View inflate = this.f3196f.inflate(R.stickers_indicator_item, null, false);
            ((ImageButton) inflate.findViewById(R.imageButton1)).setImageBitmap(e.m4050b());
            RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.relativeLayout1);
            relativeLayout.setBackgroundResource(R.stickers_indicator_item_selector);
            relativeLayout.setOnClickListener(new IconPageIndicator(this, e));
            this.f3191a.addView(inflate);
        }
        if (this.f3195e > b) {
            this.f3195e = b - 1;
        }
        setCurrentItem(this.f3195e);
        requestLayout();
    }

    public void m4039a(int i) {
        setCurrentItem(i);
        if (this.f3193c != null) {
            this.f3193c.m529a(i);
        }
    }

    public void m4040a(int i, float f, int i2) {
        if (this.f3193c != null) {
            this.f3193c.m530a(i, f, i2);
        }
    }

    public void m4041b(int i) {
        if (this.f3193c != null) {
            this.f3193c.m531b(i);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f3194d != null) {
            post(this.f3194d);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f3194d != null) {
            removeCallbacks(this.f3194d);
        }
    }

    public void setCurrentItem(int i) {
        if (this.f3192b == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        this.f3195e = i;
        this.f3192b.setCurrentItem(i);
        int childCount = this.f3191a.getChildCount();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.f3191a.getChildAt(i2);
            boolean z = i2 == i;
            childAt.setSelected(z);
            if (z) {
                m4037c(i);
            }
            i2++;
        }
    }

    public void setOnPageChangeListener(C0010e c0010e) {
        this.f3193c = c0010e;
    }

    public void setViewPager(ViewPager viewPager) {
        if (this.f3192b != viewPager) {
            if (this.f3192b != null) {
                this.f3192b.setOnPageChangeListener(null);
            }
            if (viewPager.m597b() == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            this.f3192b = viewPager;
            viewPager.setOnPageChangeListener(this);
            m4038a();
        }
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }
}
