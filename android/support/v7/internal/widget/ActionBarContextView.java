package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.ActionMenuView;
import android.support.v7.p010a.R.R;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionBarContextView extends AbsActionBarView {
    private CharSequence f1079g;
    private CharSequence f1080h;
    private View f1081i;
    private View f1082j;
    private LinearLayout f1083k;
    private TextView f1084l;
    private TextView f1085m;
    private int f1086n;
    private int f1087o;
    private Drawable f1088p;
    private boolean f1089q;

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ActionMode, i, 0);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(3));
        this.f1086n = obtainStyledAttributes.getResourceId(1, 0);
        this.f1087o = obtainStyledAttributes.getResourceId(2, 0);
        this.f = obtainStyledAttributes.getLayoutDimension(0, 0);
        this.f1088p = obtainStyledAttributes.getDrawable(4);
        obtainStyledAttributes.recycle();
    }

    private void m1901h() {
        int i = 8;
        Object obj = 1;
        if (this.f1083k == null) {
            LayoutInflater.from(getContext()).inflate(R.abc_action_bar_title_item, this);
            this.f1083k = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f1084l = (TextView) this.f1083k.findViewById(R.action_bar_title);
            this.f1085m = (TextView) this.f1083k.findViewById(R.action_bar_subtitle);
            if (this.f1086n != 0) {
                this.f1084l.setTextAppearance(getContext(), this.f1086n);
            }
            if (this.f1087o != 0) {
                this.f1085m.setTextAppearance(getContext(), this.f1087o);
            }
        }
        this.f1084l.setText(this.f1079g);
        this.f1085m.setText(this.f1080h);
        Object obj2 = !TextUtils.isEmpty(this.f1079g) ? 1 : null;
        if (TextUtils.isEmpty(this.f1080h)) {
            obj = null;
        }
        this.f1085m.setVisibility(obj != null ? 0 : 8);
        LinearLayout linearLayout = this.f1083k;
        if (!(obj2 == null && obj == null)) {
            i = 0;
        }
        linearLayout.setVisibility(i);
        if (this.f1083k.getParent() == null) {
            addView(this.f1083k);
        }
    }

    public /* bridge */ /* synthetic */ int m1902a() {
        return super.m1891a();
    }

    public boolean m1903b() {
        return this.b != null ? this.b.m1687a() : false;
    }

    public /* bridge */ /* synthetic */ void m1904c() {
        super.m1896c();
    }

    public boolean m1905d() {
        return this.b != null ? this.b.m1692b() : false;
    }

    public boolean m1906e() {
        return this.b != null ? this.b.m1696e() : false;
    }

    public /* bridge */ /* synthetic */ boolean m1907f() {
        return super.m1899f();
    }

    public /* bridge */ /* synthetic */ void m1908g() {
        super.m1900g();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(-1, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new MarginLayoutParams(getContext(), attributeSet);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.b != null) {
            this.b.m1692b();
            this.b.m1695d();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        if (this.f1081i == null || this.f1081i.getVisibility() == 8) {
            i5 = paddingLeft;
        } else {
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f1081i.getLayoutParams();
            paddingLeft += marginLayoutParams.leftMargin;
            i5 = marginLayoutParams.rightMargin + (paddingLeft + m1893b(this.f1081i, paddingLeft, paddingTop, paddingTop2));
        }
        if (!(this.f1083k == null || this.f1082j != null || this.f1083k.getVisibility() == 8)) {
            i5 += m1893b(this.f1083k, i5, paddingTop, paddingTop2);
        }
        if (this.f1082j != null) {
            i5 += m1893b(this.f1082j, i5, paddingTop, paddingTop2);
        }
        i5 = (i3 - i) - getPaddingRight();
        if (this.a != null) {
            i5 -= m1895c(this.a, i5, paddingTop, paddingTop2);
        }
    }

    protected void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        int i4 = 0;
        if (MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"FILL_PARENT\" (or fill_parent)");
        } else if (MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        } else {
            int a;
            int size = MeasureSpec.getSize(i);
            int size2 = this.f > 0 ? this.f : MeasureSpec.getSize(i2);
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i5 = size2 - paddingTop;
            int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
            if (this.f1081i != null) {
                a = m1892a(this.f1081i, paddingLeft, makeMeasureSpec, 0);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f1081i.getLayoutParams();
                paddingLeft = a - (marginLayoutParams.rightMargin + marginLayoutParams.leftMargin);
            }
            if (this.a != null && this.a.getParent() == this) {
                paddingLeft = m1892a(this.a, paddingLeft, makeMeasureSpec, 0);
            }
            if (this.f1083k != null && this.f1082j == null) {
                if (this.f1089q) {
                    this.f1083k.measure(MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    a = this.f1083k.getMeasuredWidth();
                    makeMeasureSpec = a <= paddingLeft ? 1 : 0;
                    if (makeMeasureSpec != 0) {
                        paddingLeft -= a;
                    }
                    this.f1083k.setVisibility(makeMeasureSpec != 0 ? 0 : 8);
                } else {
                    paddingLeft = m1892a(this.f1083k, paddingLeft, makeMeasureSpec, 0);
                }
            }
            if (this.f1082j != null) {
                LayoutParams layoutParams = this.f1082j.getLayoutParams();
                makeMeasureSpec = layoutParams.width != -2 ? 1073741824 : Integer.MIN_VALUE;
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i3 = Integer.MIN_VALUE;
                }
                this.f1082j.measure(MeasureSpec.makeMeasureSpec(paddingLeft, makeMeasureSpec), MeasureSpec.makeMeasureSpec(layoutParams.height >= 0 ? Math.min(layoutParams.height, i5) : i5, i3));
            }
            if (this.f <= 0) {
                makeMeasureSpec = getChildCount();
                size2 = 0;
                while (i4 < makeMeasureSpec) {
                    paddingLeft = getChildAt(i4).getMeasuredHeight() + paddingTop;
                    if (paddingLeft <= size2) {
                        paddingLeft = size2;
                    }
                    i4++;
                    size2 = paddingLeft;
                }
                setMeasuredDimension(size, size2);
                return;
            }
            setMeasuredDimension(size, size2);
        }
    }

    public void setContentHeight(int i) {
        this.f = i;
    }

    public void setCustomView(View view) {
        if (this.f1082j != null) {
            removeView(this.f1082j);
        }
        this.f1082j = view;
        if (this.f1083k != null) {
            removeView(this.f1083k);
            this.f1083k = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSplitActionBar(boolean z) {
        if (this.d != z) {
            if (this.b != null) {
                LayoutParams layoutParams = new LayoutParams(-2, -1);
                ViewGroup viewGroup;
                if (z) {
                    this.b.m1681a(getContext().getResources().getDisplayMetrics().widthPixels, true);
                    this.b.m1680a(Integer.MAX_VALUE);
                    layoutParams.width = -1;
                    layoutParams.height = this.f;
                    this.a = (ActionMenuView) this.b.m1678a((ViewGroup) this);
                    this.a.setBackgroundDrawable(this.f1088p);
                    viewGroup = (ViewGroup) this.a.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(this.a);
                    }
                    this.c.addView(this.a, layoutParams);
                } else {
                    this.a = (ActionMenuView) this.b.m1678a((ViewGroup) this);
                    this.a.setBackgroundDrawable(null);
                    viewGroup = (ViewGroup) this.a.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeView(this.a);
                    }
                    addView(this.a, layoutParams);
                }
            }
            super.setSplitActionBar(z);
        }
    }

    public /* bridge */ /* synthetic */ void setSplitView(ActionBarContainer actionBarContainer) {
        super.setSplitView(actionBarContainer);
    }

    public /* bridge */ /* synthetic */ void setSplitWhenNarrow(boolean z) {
        super.setSplitWhenNarrow(z);
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f1080h = charSequence;
        m1901h();
    }

    public void setTitle(CharSequence charSequence) {
        this.f1079g = charSequence;
        m1901h();
    }

    public void setTitleOptional(boolean z) {
        if (z != this.f1089q) {
            requestLayout();
        }
        this.f1089q = z;
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }
}
