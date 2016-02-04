package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.ActionMenuView.C0040a;
import android.support.v7.internal.view.menu.MenuBuilder.MenuBuilder;
import android.support.v7.internal.view.menu.MenuView.MenuView;
import android.support.v7.internal.widget.CompatTextView;
import android.support.v7.p010a.R.R;
import android.text.TextUtils;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import java.util.Locale;

public class ActionMenuItemView extends CompatTextView implements C0040a, MenuView, OnClickListener, OnLongClickListener {
    private MenuItemImpl f835a;
    private CharSequence f836b;
    private Drawable f837c;
    private MenuBuilder f838d;
    private boolean f839e;
    private boolean f840f;
    private int f841g;
    private int f842h;

    /* renamed from: android.support.v7.internal.view.menu.ActionMenuItemView.a */
    private class C0039a implements TransformationMethod {
        final /* synthetic */ ActionMenuItemView f833a;
        private Locale f834b;

        public C0039a(ActionMenuItemView actionMenuItemView) {
            this.f833a = actionMenuItemView;
            this.f834b = actionMenuItemView.getContext().getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence charSequence, View view) {
            return charSequence != null ? charSequence.toString().toUpperCase(this.f834b) : null;
        }

        public void onFocusChanged(View view, CharSequence charSequence, boolean z, int i, Rect rect) {
        }
    }

    public ActionMenuItemView(Context context) {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f839e = context.getResources().getBoolean(R.abc_config_allowActionMenuItemTextWithIcon);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.ActionMenuItemView, 0, 0);
        this.f841g = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        setOnClickListener(this);
        setOnLongClickListener(this);
        setTransformationMethod(new C0039a(this));
        this.f842h = -1;
    }

    private void m1620f() {
        int i = 0;
        int i2 = !TextUtils.isEmpty(this.f836b) ? 1 : 0;
        if (this.f837c == null || (this.f835a.m1821l() && (this.f839e || this.f840f))) {
            i = 1;
        }
        setText((i2 & i) != 0 ? this.f836b : null);
    }

    public MenuItemImpl m1621a() {
        return this.f835a;
    }

    public void m1622a(MenuItemImpl menuItemImpl, int i) {
        this.f835a = menuItemImpl;
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.m1801a((MenuView) this));
        setId(menuItemImpl.getItemId());
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setEnabled(menuItemImpl.isEnabled());
    }

    public boolean m1623b() {
        return true;
    }

    public boolean m1624c() {
        return !TextUtils.isEmpty(getText());
    }

    public boolean m1625d() {
        return m1624c() && this.f835a.getIcon() == null;
    }

    public boolean m1626e() {
        return m1624c();
    }

    public void onClick(View view) {
        if (this.f838d != null) {
            this.f838d.m1705a(this.f835a);
        }
    }

    public boolean onLongClick(View view) {
        if (m1624c()) {
            return false;
        }
        int[] iArr = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(iArr);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int width = getWidth();
        int height = getHeight();
        int i = iArr[1] + (height / 2);
        int i2 = context.getResources().getDisplayMetrics().widthPixels;
        Toast makeText = Toast.makeText(context, this.f835a.getTitle(), 0);
        if (i < rect.height()) {
            makeText.setGravity(53, (i2 - iArr[0]) - (width / 2), height);
        } else {
            makeText.setGravity(81, 0, height);
        }
        makeText.show();
        return true;
    }

    protected void onMeasure(int i, int i2) {
        boolean c = m1624c();
        if (c && this.f842h >= 0) {
            super.setPadding(this.f842h, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        size = mode == Integer.MIN_VALUE ? Math.min(size, this.f841g) : this.f841g;
        if (mode != 1073741824 && this.f841g > 0 && measuredWidth < size) {
            super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), i2);
        }
        if (!c && this.f837c != null) {
            super.setPadding((getMeasuredWidth() - this.f837c.getIntrinsicWidth()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public void setExpandedFormat(boolean z) {
        if (this.f840f != z) {
            this.f840f = z;
            if (this.f835a != null) {
                this.f835a.m1816g();
            }
        }
    }

    public void setIcon(Drawable drawable) {
        this.f837c = drawable;
        setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        m1620f();
    }

    public void setItemInvoker(MenuBuilder menuBuilder) {
        this.f838d = menuBuilder;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f842h = i;
        super.setPadding(i, i2, i3, i4);
    }

    public void setShortcut(boolean z, char c) {
    }

    public void setTitle(CharSequence charSequence) {
        this.f836b = charSequence;
        setContentDescription(this.f836b);
        m1620f();
    }
}
