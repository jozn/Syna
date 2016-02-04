package com.viewpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

/* renamed from: com.viewpagerindicator.e */
class IcsLinearLayout extends LinearLayout {
    private static final int[] f3201a;
    private Drawable f3202b;
    private int f3203c;
    private int f3204d;
    private int f3205e;
    private int f3206f;

    static {
        f3201a = new int[]{16843049, 16843561, 16843562};
    }

    public IcsLinearLayout(Context context, int i) {
        super(context);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(null, f3201a, i, 0);
        setDividerDrawable(obtainStyledAttributes.getDrawable(0));
        this.f3206f = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        this.f3205e = obtainStyledAttributes.getInteger(1, 0);
        obtainStyledAttributes.recycle();
    }

    private void m4044a(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m4046a(i))) {
                m4045a(canvas, childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin);
            }
            i++;
        }
        if (m4046a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            m4045a(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.f3204d : childAt2.getBottom());
        }
    }

    private void m4045a(Canvas canvas, int i) {
        this.f3202b.setBounds(getPaddingLeft() + this.f3206f, i, (getWidth() - getPaddingRight()) - this.f3206f, this.f3204d + i);
        this.f3202b.draw(canvas);
    }

    private boolean m4046a(int i) {
        if (i == 0 || i == getChildCount() || (this.f3205e & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    private void m4047b(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m4046a(i))) {
                m4048b(canvas, childAt.getLeft() - ((LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
            i++;
        }
        if (m4046a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            m4048b(canvas, childAt2 == null ? (getWidth() - getPaddingRight()) - this.f3203c : childAt2.getRight());
        }
    }

    private void m4048b(Canvas canvas, int i) {
        this.f3202b.setBounds(i, getPaddingTop() + this.f3206f, this.f3203c + i, (getHeight() - getPaddingBottom()) - this.f3206f);
        this.f3202b.draw(canvas);
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (m4046a(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.f3204d;
            } else {
                layoutParams.leftMargin = this.f3203c;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && m4046a(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.f3204d;
            } else {
                layoutParams.rightMargin = this.f3203c;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        if (this.f3202b != null) {
            if (getOrientation() == 1) {
                m4044a(canvas);
            } else {
                m4047b(canvas);
            }
        }
        super.onDraw(canvas);
    }

    public void setDividerDrawable(Drawable drawable) {
        boolean z = false;
        if (drawable != this.f3202b) {
            this.f3202b = drawable;
            if (drawable != null) {
                this.f3203c = drawable.getIntrinsicWidth();
                this.f3204d = drawable.getIntrinsicHeight();
            } else {
                this.f3203c = 0;
                this.f3204d = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }
}
