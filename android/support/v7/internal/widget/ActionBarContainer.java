package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class ActionBarContainer extends FrameLayout {
    private boolean f1065a;
    private View f1066b;
    private ActionBarView f1067c;
    private Drawable f1068d;
    private Drawable f1069e;
    private Drawable f1070f;
    private boolean f1071g;
    private boolean f1072h;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        boolean z = true;
        super(context, attributeSet);
        setBackgroundDrawable(null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ActionBar);
        this.f1068d = obtainStyledAttributes.getDrawable(10);
        this.f1069e = obtainStyledAttributes.getDrawable(11);
        if (getId() == R.split_action_bar) {
            this.f1071g = true;
            this.f1070f = obtainStyledAttributes.getDrawable(12);
        }
        obtainStyledAttributes.recycle();
        if (this.f1071g) {
            if (this.f1070f != null) {
                z = false;
            }
        } else if (!(this.f1068d == null && this.f1069e == null)) {
            z = false;
        }
        setWillNotDraw(z);
    }

    private void m1890a(Drawable drawable, Canvas canvas) {
        Rect bounds = drawable.getBounds();
        if (!(drawable instanceof ColorDrawable) || bounds.isEmpty() || VERSION.SDK_INT >= 11) {
            drawable.draw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(bounds);
        drawable.draw(canvas);
        canvas.restore();
    }

    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f1068d != null && this.f1068d.isStateful()) {
            this.f1068d.setState(getDrawableState());
        }
        if (this.f1069e != null && this.f1069e.isStateful()) {
            this.f1069e.setState(getDrawableState());
        }
        if (this.f1070f != null && this.f1070f.isStateful()) {
            this.f1070f.setState(getDrawableState());
        }
    }

    public void onDraw(Canvas canvas) {
        if (getWidth() != 0 && getHeight() != 0) {
            if (!this.f1071g) {
                if (this.f1068d != null) {
                    m1890a(this.f1068d, canvas);
                }
                if (this.f1069e != null && this.f1072h) {
                    m1890a(this.f1069e, canvas);
                }
            } else if (this.f1070f != null) {
                m1890a(this.f1070f, canvas);
            }
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.f1067c = (ActionBarView) findViewById(R.action_bar);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f1065a || super.onInterceptTouchEvent(motionEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2 = true;
        boolean z3 = false;
        super.onLayout(z, i, i2, i3, i4);
        boolean z4 = (this.f1066b == null || this.f1066b.getVisibility() == 8) ? false : true;
        if (!(this.f1066b == null || this.f1066b.getVisibility() == 8)) {
            int measuredHeight = getMeasuredHeight();
            int measuredHeight2 = this.f1066b.getMeasuredHeight();
            if ((this.f1067c.m1951n() & 2) == 0) {
                int childCount = getChildCount();
                for (measuredHeight = 0; measuredHeight < childCount; measuredHeight++) {
                    View childAt = getChildAt(measuredHeight);
                    if (!(childAt == this.f1066b || this.f1067c.m1952o())) {
                        childAt.offsetTopAndBottom(measuredHeight2);
                    }
                }
                this.f1066b.layout(i, 0, i3, measuredHeight2);
            } else {
                this.f1066b.layout(i, measuredHeight - measuredHeight2, i3, measuredHeight);
            }
        }
        if (!this.f1071g) {
            boolean z5;
            if (this.f1068d != null) {
                this.f1068d.setBounds(this.f1067c.getLeft(), this.f1067c.getTop(), this.f1067c.getRight(), this.f1067c.getBottom());
                z5 = true;
            } else {
                z5 = false;
            }
            if (z4 && this.f1069e != null) {
                z3 = true;
            }
            this.f1072h = z3;
            if (z3) {
                this.f1069e.setBounds(this.f1066b.getLeft(), this.f1066b.getTop(), this.f1066b.getRight(), this.f1066b.getBottom());
            } else {
                z2 = z5;
            }
        } else if (this.f1070f != null) {
            this.f1070f.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        } else {
            z2 = false;
        }
        if (z2) {
            invalidate();
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f1067c != null) {
            int i3;
            LayoutParams layoutParams = (LayoutParams) this.f1067c.getLayoutParams();
            if (this.f1067c.m1952o()) {
                i3 = 0;
            } else {
                i3 = layoutParams.bottomMargin + (this.f1067c.getMeasuredHeight() + layoutParams.topMargin);
            }
            if (this.f1066b != null && this.f1066b.getVisibility() != 8 && MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                setMeasuredDimension(getMeasuredWidth(), Math.min(i3 + this.f1066b.getMeasuredHeight(), MeasureSpec.getSize(i2)));
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        boolean z = true;
        if (this.f1068d != null) {
            this.f1068d.setCallback(null);
            unscheduleDrawable(this.f1068d);
        }
        this.f1068d = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1067c != null) {
                this.f1068d.setBounds(this.f1067c.getLeft(), this.f1067c.getTop(), this.f1067c.getRight(), this.f1067c.getBottom());
            }
        }
        if (this.f1071g) {
            if (this.f1070f != null) {
                z = false;
            }
        } else if (!(this.f1068d == null && this.f1069e == null)) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        boolean z = true;
        if (this.f1070f != null) {
            this.f1070f.setCallback(null);
            unscheduleDrawable(this.f1070f);
        }
        this.f1070f = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1071g && this.f1070f != null) {
                this.f1070f.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (this.f1071g) {
            if (this.f1070f != null) {
                z = false;
            }
        } else if (!(this.f1068d == null && this.f1069e == null)) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        boolean z = true;
        if (this.f1069e != null) {
            this.f1069e.setCallback(null);
            unscheduleDrawable(this.f1069e);
        }
        this.f1069e = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1072h && this.f1069e != null) {
                this.f1069e.setBounds(this.f1066b.getLeft(), this.f1066b.getTop(), this.f1066b.getRight(), this.f1066b.getBottom());
            }
        }
        if (this.f1071g) {
            if (this.f1070f != null) {
                z = false;
            }
        } else if (!(this.f1068d == null && this.f1069e == null)) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.f1066b != null) {
            removeView(this.f1066b);
        }
        this.f1066b = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.f1065a = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        if (this.f1068d != null) {
            this.f1068d.setVisible(z, false);
        }
        if (this.f1069e != null) {
            this.f1069e.setVisible(z, false);
        }
        if (this.f1070f != null) {
            this.f1070f.setVisible(z, false);
        }
    }

    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f1068d && !this.f1071g) || ((drawable == this.f1069e && this.f1072h) || ((drawable == this.f1070f && this.f1071g) || super.verifyDrawable(drawable)));
    }
}
