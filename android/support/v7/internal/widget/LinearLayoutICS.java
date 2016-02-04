package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class LinearLayoutICS extends LinearLayout {
    private final Drawable f901a;
    private final int f902b;
    private final int f903c;
    private final int f904d;
    private final int f905e;

    public LinearLayoutICS(Context context, AttributeSet attributeSet) {
        boolean z = true;
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.LinearLayoutICS);
        this.f901a = obtainStyledAttributes.getDrawable(0);
        if (this.f901a != null) {
            this.f902b = this.f901a.getIntrinsicWidth();
            this.f903c = this.f901a.getIntrinsicHeight();
        } else {
            this.f902b = 0;
            this.f903c = 0;
        }
        this.f904d = obtainStyledAttributes.getInt(1, 0);
        this.f905e = obtainStyledAttributes.getDimensionPixelSize(2, 0);
        obtainStyledAttributes.recycle();
        if (this.f901a != null) {
            z = false;
        }
        setWillNotDraw(z);
    }

    void m1699a(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m1701a(i))) {
                m1700a(canvas, childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin);
            }
            i++;
        }
        if (m1701a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            m1700a(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.f903c : childAt2.getBottom());
        }
    }

    void m1700a(Canvas canvas, int i) {
        this.f901a.setBounds(getPaddingLeft() + this.f905e, i, (getWidth() - getPaddingRight()) - this.f905e, this.f903c + i);
        this.f901a.draw(canvas);
    }

    protected boolean m1701a(int i) {
        if (i == 0) {
            return (this.f904d & 1) != 0;
        } else {
            if (i == getChildCount()) {
                return (this.f904d & 4) != 0;
            } else {
                if ((this.f904d & 2) == 0) {
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

    void m1702b(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m1701a(i))) {
                m1703b(canvas, childAt.getLeft() - ((LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
            i++;
        }
        if (m1701a(childCount)) {
            View childAt2 = getChildAt(childCount - 1);
            m1703b(canvas, childAt2 == null ? (getWidth() - getPaddingRight()) - this.f902b : childAt2.getRight());
        }
    }

    void m1703b(Canvas canvas, int i) {
        this.f901a.setBounds(i, getPaddingTop() + this.f905e, this.f902b + i, (getHeight() - getPaddingBottom()) - this.f905e);
        this.f901a.draw(canvas);
    }

    public int m1704c() {
        return this.f902b;
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        if (this.f901a != null) {
            int indexOfChild = indexOfChild(view);
            int childCount = getChildCount();
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (getOrientation() == 1) {
                if (m1701a(indexOfChild)) {
                    layoutParams.topMargin = this.f903c;
                } else if (indexOfChild == childCount - 1 && m1701a(childCount)) {
                    layoutParams.bottomMargin = this.f903c;
                }
            } else if (m1701a(indexOfChild)) {
                layoutParams.leftMargin = this.f902b;
            } else if (indexOfChild == childCount - 1 && m1701a(childCount)) {
                layoutParams.rightMargin = this.f902b;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        if (this.f901a != null) {
            if (getOrientation() == 1) {
                m1699a(canvas);
            } else {
                m1702b(canvas);
            }
        }
    }
}
