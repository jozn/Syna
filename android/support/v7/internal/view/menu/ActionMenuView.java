package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v7.internal.view.menu.MenuBuilder.MenuBuilder;
import android.support.v7.internal.widget.LinearLayoutICS;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewDebug.ExportedProperty;
import android.view.accessibility.AccessibilityEvent;

public class ActionMenuView extends LinearLayoutICS implements MenuBuilder, MenuView {
    private MenuBuilder f906a;
    private boolean f907b;
    private ActionMenuPresenter f908c;
    private boolean f909d;
    private int f910e;
    private int f911f;
    private int f912g;
    private int f913h;
    private int f914i;

    /* renamed from: android.support.v7.internal.view.menu.ActionMenuView.a */
    public interface C0040a {
        boolean m1615d();

        boolean m1616e();
    }

    public static class LayoutParams extends android.widget.LinearLayout.LayoutParams {
        @ExportedProperty
        public boolean f895a;
        @ExportedProperty
        public int f896b;
        @ExportedProperty
        public int f897c;
        @ExportedProperty
        public boolean f898d;
        @ExportedProperty
        public boolean f899e;
        public boolean f900f;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f895a = false;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.f895a = layoutParams.f895a;
        }
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.f911f = (int) (56.0f * f);
        this.f912g = (int) (f * 4.0f);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ActionBar, R.actionBarStyle, 0);
        this.f914i = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
    }

    static int m1707a(View view, int i, int i2, int i3, int i4) {
        int i5;
        boolean z = false;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i3) - i4, MeasureSpec.getMode(i3));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z2 = actionMenuItemView != null && actionMenuItemView.m1624c();
        if (i2 <= 0 || (z2 && i2 < 2)) {
            i5 = 0;
        } else {
            view.measure(MeasureSpec.makeMeasureSpec(i * i2, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            i5 = measuredWidth / i;
            if (measuredWidth % i != 0) {
                i5++;
            }
            if (z2 && r1 < 2) {
                i5 = 2;
            }
        }
        if (!layoutParams.f895a && z2) {
            z = true;
        }
        layoutParams.f898d = z;
        layoutParams.f896b = i5;
        view.measure(MeasureSpec.makeMeasureSpec(i5 * i, 1073741824), makeMeasureSpec);
        return i5;
    }

    private void m1708a(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int makeMeasureSpec = mode == 1073741824 ? MeasureSpec.makeMeasureSpec(size2 - paddingTop, 1073741824) : MeasureSpec.makeMeasureSpec(Math.min(this.f914i, size2 - paddingTop), Integer.MIN_VALUE);
        int i3 = size - paddingRight;
        int i4 = i3 / this.f911f;
        int i5 = i3 % this.f911f;
        if (i4 == 0) {
            setMeasuredDimension(i3, 0);
            return;
        }
        int i6;
        int a;
        Object obj;
        int i7 = this.f911f + (i5 / i4);
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        size = 0;
        Object obj2 = null;
        long j = 0;
        int childCount = getChildCount();
        int i11 = 0;
        while (i11 < childCount) {
            long j2;
            int i12;
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() == 8) {
                paddingRight = size;
                i6 = i8;
                j2 = j;
                i12 = i4;
                i4 = i9;
            } else {
                boolean z = childAt instanceof ActionMenuItemView;
                i6 = size + 1;
                if (z) {
                    childAt.setPadding(this.f912g, 0, this.f912g, 0);
                }
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.f900f = false;
                layoutParams.f897c = 0;
                layoutParams.f896b = 0;
                layoutParams.f898d = false;
                layoutParams.leftMargin = 0;
                layoutParams.rightMargin = 0;
                boolean z2 = z && ((ActionMenuItemView) childAt).m1624c();
                layoutParams.f899e = z2;
                a = m1707a(childAt, i7, layoutParams.f895a ? 1 : i4, makeMeasureSpec, paddingTop);
                i9 = Math.max(i9, a);
                size = layoutParams.f898d ? i10 + 1 : i10;
                obj = layoutParams.f895a ? 1 : obj2;
                int i13 = i4 - a;
                paddingRight = Math.max(i8, childAt.getMeasuredHeight());
                int i14;
                if (a == 1) {
                    long j3 = ((long) (1 << i11)) | j;
                    i12 = i13;
                    obj2 = obj;
                    i14 = i6;
                    i6 = paddingRight;
                    paddingRight = i14;
                    j2 = j3;
                    i4 = i9;
                    i10 = size;
                } else {
                    i10 = size;
                    i4 = i9;
                    i14 = i6;
                    i6 = paddingRight;
                    paddingRight = i14;
                    Object obj3 = obj;
                    j2 = j;
                    i12 = i13;
                    obj2 = obj3;
                }
            }
            i11++;
            i9 = i4;
            i8 = i6;
            i4 = i12;
            j = j2;
            size = paddingRight;
        }
        Object obj4 = (obj2 == null || size != 2) ? null : 1;
        Object obj5 = null;
        long j4 = j;
        paddingTop = i4;
        while (i10 > 0 && paddingTop > 0) {
            i6 = Integer.MAX_VALUE;
            j = 0;
            i4 = 0;
            a = 0;
            while (a < childCount) {
                layoutParams = (LayoutParams) getChildAt(a).getLayoutParams();
                if (layoutParams.f898d) {
                    int i15 = layoutParams.f896b;
                    if (r0 < i6) {
                        i4 = layoutParams.f896b;
                        j = (long) (1 << a);
                        i5 = 1;
                    } else if (layoutParams.f896b == i6) {
                        j |= (long) (1 << a);
                        i5 = i4 + 1;
                        i4 = i6;
                    } else {
                        i5 = i4;
                        i4 = i6;
                    }
                } else {
                    i5 = i4;
                    i4 = i6;
                }
                a++;
                i6 = i4;
                i4 = i5;
            }
            j4 |= j;
            if (i4 > paddingTop) {
                j = j4;
                break;
            }
            a = i6 + 1;
            i6 = 0;
            i4 = paddingTop;
            long j5 = j4;
            while (i6 < childCount) {
                View childAt2 = getChildAt(i6);
                layoutParams = (LayoutParams) childAt2.getLayoutParams();
                if ((((long) (1 << i6)) & j) != 0) {
                    if (obj4 != null && layoutParams.f899e && i4 == 1) {
                        childAt2.setPadding(this.f912g + i7, 0, this.f912g, 0);
                    }
                    layoutParams.f896b++;
                    layoutParams.f900f = true;
                    i5 = i4 - 1;
                } else if (layoutParams.f896b == a) {
                    j5 |= (long) (1 << i6);
                    i5 = i4;
                } else {
                    i5 = i4;
                }
                i6++;
                i4 = i5;
            }
            j4 = j5;
            i11 = 1;
            paddingTop = i4;
        }
        j = j4;
        obj = (obj2 == null && size == 1) ? 1 : null;
        if (paddingTop <= 0 || j == 0 || (paddingTop >= size - 1 && obj == null && i9 <= 1)) {
            obj4 = obj5;
            i4 = paddingTop;
        } else {
            float f;
            View childAt3;
            float bitCount = (float) Long.bitCount(j);
            if (obj == null) {
                if (!((1 & j) == 0 || ((LayoutParams) getChildAt(0).getLayoutParams()).f899e)) {
                    bitCount -= 0.5f;
                }
                if (!((((long) (1 << (childCount - 1))) & j) == 0 || ((LayoutParams) getChildAt(childCount - 1).getLayoutParams()).f899e)) {
                    f = bitCount - 0.5f;
                    size = f <= 0.0f ? (int) (((float) (paddingTop * i7)) / f) : 0;
                    i4 = 0;
                    obj4 = obj5;
                    while (i4 < childCount) {
                        if ((((long) (1 << i4)) & j) != 0) {
                            obj = obj4;
                        } else {
                            childAt3 = getChildAt(i4);
                            layoutParams = (LayoutParams) childAt3.getLayoutParams();
                            if (childAt3 instanceof ActionMenuItemView) {
                                layoutParams.f897c = size;
                                layoutParams.f900f = true;
                                if (i4 == 0 && !layoutParams.f899e) {
                                    layoutParams.leftMargin = (-size) / 2;
                                }
                                obj = 1;
                            } else if (layoutParams.f895a) {
                                if (i4 != 0) {
                                    layoutParams.leftMargin = size / 2;
                                }
                                if (i4 != childCount - 1) {
                                    layoutParams.rightMargin = size / 2;
                                }
                                obj = obj4;
                            } else {
                                layoutParams.f897c = size;
                                layoutParams.f900f = true;
                                layoutParams.rightMargin = (-size) / 2;
                                obj = 1;
                            }
                        }
                        i4++;
                        obj4 = obj;
                    }
                    i4 = 0;
                }
            }
            f = bitCount;
            if (f <= 0.0f) {
            }
            i4 = 0;
            obj4 = obj5;
            while (i4 < childCount) {
                if ((((long) (1 << i4)) & j) != 0) {
                    childAt3 = getChildAt(i4);
                    layoutParams = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        layoutParams.f897c = size;
                        layoutParams.f900f = true;
                        layoutParams.leftMargin = (-size) / 2;
                        obj = 1;
                    } else if (layoutParams.f895a) {
                        if (i4 != 0) {
                            layoutParams.leftMargin = size / 2;
                        }
                        if (i4 != childCount - 1) {
                            layoutParams.rightMargin = size / 2;
                        }
                        obj = obj4;
                    } else {
                        layoutParams.f897c = size;
                        layoutParams.f900f = true;
                        layoutParams.rightMargin = (-size) / 2;
                        obj = 1;
                    }
                } else {
                    obj = obj4;
                }
                i4++;
                obj4 = obj;
            }
            i4 = 0;
        }
        if (obj4 != null) {
            for (size = 0; size < childCount; size++) {
                childAt = getChildAt(size);
                layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.f900f) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(layoutParams.f897c + (layoutParams.f896b * i7), 1073741824), makeMeasureSpec);
                }
            }
        }
        if (mode == 1073741824) {
            i8 = size2;
        }
        setMeasuredDimension(i3, i8);
        this.f913h = i4 * i7;
    }

    protected LayoutParams m1709a() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }

    public LayoutParams m1710a(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    protected LayoutParams m1711a(android.view.ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams)) {
            return m1709a();
        }
        LayoutParams layoutParams2 = new LayoutParams((LayoutParams) layoutParams);
        if (layoutParams2.gravity > 0) {
            return layoutParams2;
        }
        layoutParams2.gravity = 16;
        return layoutParams2;
    }

    public void m1712a(MenuBuilder menuBuilder) {
        this.f906a = menuBuilder;
    }

    protected boolean m1713a(int i) {
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        boolean z = false;
        if (i < getChildCount() && (childAt instanceof C0040a)) {
            z = 0 | ((C0040a) childAt).m1616e();
        }
        return (i <= 0 || !(childAt2 instanceof C0040a)) ? z : ((C0040a) childAt2).m1615d() | z;
    }

    public boolean m1714a(MenuItemImpl menuItemImpl) {
        return this.f906a.m1772a((MenuItem) menuItemImpl, 0);
    }

    public LayoutParams m1715b() {
        LayoutParams a = m1709a();
        a.f895a = true;
        return a;
    }

    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return m1709a();
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams m6035generateDefaultLayoutParams() {
        return m1709a();
    }

    public /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return m1710a(attributeSet);
    }

    protected /* synthetic */ android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return m1711a(layoutParams);
    }

    public /* synthetic */ android.widget.LinearLayout.LayoutParams m6036generateLayoutParams(AttributeSet attributeSet) {
        return m1710a(attributeSet);
    }

    protected /* synthetic */ android.widget.LinearLayout.LayoutParams m6037generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return m1711a(layoutParams);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.f908c.m1693c(false);
        if (this.f908c != null && this.f908c.m1696e()) {
            this.f908c.m1692b();
            this.f908c.m1687a();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f908c.m1694c();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.f909d) {
            int i5;
            LayoutParams layoutParams;
            int width;
            int measuredWidth;
            int childCount = getChildCount();
            int i6 = (i2 + i4) / 2;
            int c = m1704c();
            int i7 = 0;
            int i8 = 0;
            int paddingRight = ((i3 - i) - getPaddingRight()) - getPaddingLeft();
            Object obj = null;
            int i9 = 0;
            while (i9 < childCount) {
                Object obj2;
                View childAt = getChildAt(i9);
                if (childAt.getVisibility() == 8) {
                    obj2 = obj;
                    i5 = paddingRight;
                    paddingRight = i8;
                    i8 = i7;
                } else {
                    layoutParams = (LayoutParams) childAt.getLayoutParams();
                    if (layoutParams.f895a) {
                        i5 = childAt.getMeasuredWidth();
                        if (m1713a(i9)) {
                            i5 += c;
                        }
                        int measuredHeight = childAt.getMeasuredHeight();
                        width = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                        int i10 = i6 - (measuredHeight / 2);
                        childAt.layout(width - i5, i10, width, measuredHeight + i10);
                        i5 = paddingRight - i5;
                        obj2 = 1;
                        paddingRight = i8;
                        i8 = i7;
                    } else {
                        measuredWidth = (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                        width = i7 + measuredWidth;
                        paddingRight -= measuredWidth;
                        if (m1713a(i9)) {
                            width += c;
                        }
                        Object obj3 = obj;
                        i5 = paddingRight;
                        paddingRight = i8 + 1;
                        i8 = width;
                        obj2 = obj3;
                    }
                }
                i9++;
                i7 = i8;
                i8 = paddingRight;
                paddingRight = i5;
                obj = obj2;
            }
            if (childCount == 1 && obj == null) {
                View childAt2 = getChildAt(0);
                i5 = childAt2.getMeasuredWidth();
                paddingRight = childAt2.getMeasuredHeight();
                i8 = ((i3 - i) / 2) - (i5 / 2);
                i7 = i6 - (paddingRight / 2);
                childAt2.layout(i8, i7, i5 + i8, paddingRight + i7);
                return;
            }
            width = i8 - (obj != null ? 0 : 1);
            i8 = Math.max(0, width > 0 ? paddingRight / width : 0);
            i5 = getPaddingLeft();
            paddingRight = 0;
            while (paddingRight < childCount) {
                View childAt3 = getChildAt(paddingRight);
                layoutParams = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() == 8) {
                    width = i5;
                } else if (layoutParams.f895a) {
                    width = i5;
                } else {
                    i5 += layoutParams.leftMargin;
                    i9 = childAt3.getMeasuredWidth();
                    c = childAt3.getMeasuredHeight();
                    measuredWidth = i6 - (c / 2);
                    childAt3.layout(i5, measuredWidth, i5 + i9, c + measuredWidth);
                    width = ((layoutParams.rightMargin + i9) + i8) + i5;
                }
                paddingRight++;
                i5 = width;
            }
            return;
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        boolean z = this.f909d;
        this.f909d = MeasureSpec.getMode(i) == 1073741824;
        if (z != this.f909d) {
            this.f910e = 0;
        }
        int mode = MeasureSpec.getMode(i);
        if (!(!this.f909d || this.f906a == null || mode == this.f910e)) {
            this.f910e = mode;
            this.f906a.m1777b(true);
        }
        if (this.f909d) {
            m1708a(i, i2);
            return;
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
            layoutParams.rightMargin = 0;
            layoutParams.leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    public void setOverflowReserved(boolean z) {
        this.f907b = z;
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.f908c = actionMenuPresenter;
    }
}
