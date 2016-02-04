package com.felipecsl.asymmetricgridview.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

public class IcsLinearLayout extends NineLinearLayout {
    private static final int[] f1536a;
    private Drawable f1537b;
    private int f1538c;
    private int f1539d;
    private int f1540e;
    private int f1541f;

    static {
        f1536a = new int[]{16843049, 16843561, 16843562};
    }

    public IcsLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f1536a);
        setDividerDrawable(obtainStyledAttributes.getDrawable(0));
        this.f1540e = obtainStyledAttributes.getInt(1, 0);
        this.f1541f = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        obtainStyledAttributes.recycle();
    }

    void m2325a(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m2327a(i))) {
                m2326a(canvas, childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin);
            }
            i++;
        }
        if (m2327a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            m2326a(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.f1539d : childAt2.getBottom());
        }
    }

    void m2326a(Canvas canvas, int i) {
        this.f1537b.setBounds(getPaddingLeft() + this.f1541f, i, (getWidth() - getPaddingRight()) - this.f1541f, this.f1539d + i);
        this.f1537b.draw(canvas);
    }

    protected boolean m2327a(int i) {
        if (i == 0) {
            return (this.f1540e & 1) != 0;
        } else {
            if (i == getChildCount()) {
                return (this.f1540e & 4) != 0;
            } else {
                if ((this.f1540e & 2) == 0) {
                    return false;
                }
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    if (getChildAt(i2).getVisibility() != 8) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

    void m2328b(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m2327a(i))) {
                m2329b(canvas, childAt.getLeft() - ((LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
            i++;
        }
        if (m2327a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            m2329b(canvas, childAt2 == null ? (getWidth() - getPaddingRight()) - this.f1538c : childAt2.getRight());
        }
    }

    void m2329b(Canvas canvas, int i) {
        this.f1537b.setBounds(i, getPaddingTop() + this.f1541f, this.f1538c + i, (getHeight() - getPaddingBottom()) - this.f1541f);
        this.f1537b.draw(canvas);
    }

    public int getDividerPadding() {
        return this.f1541f;
    }

    public int getShowDividers() {
        return this.f1540e;
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (m2327a(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.f1539d;
            } else {
                layoutParams.leftMargin = this.f1538c;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && m2327a(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.f1539d;
            } else {
                layoutParams.rightMargin = this.f1538c;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        if (this.f1537b != null) {
            if (getOrientation() == 1) {
                m2325a(canvas);
            } else {
                m2328b(canvas);
            }
        }
        super.onDraw(canvas);
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.f1537b) {
            if ((drawable instanceof ColorDrawable) && VERSION.SDK_INT < 11) {
                drawable = new IcsColorDrawable((ColorDrawable) drawable);
            }
            this.f1537b = drawable;
            if (drawable != null) {
                this.f1538c = drawable.getIntrinsicWidth();
                this.f1539d = drawable.getIntrinsicHeight();
            } else {
                this.f1538c = 0;
                this.f1539d = 0;
            }
            setWillNotDraw(drawable == null);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.f1541f = i;
    }

    public void setShowDividers(int i) {
        if (i != this.f1540e) {
            requestLayout();
            invalidate();
        }
        this.f1540e = i;
    }
}
