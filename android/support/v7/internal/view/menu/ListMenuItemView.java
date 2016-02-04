package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.MenuView.MenuView;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListMenuItemView extends LinearLayout implements MenuView {
    private MenuItemImpl f916a;
    private ImageView f917b;
    private RadioButton f918c;
    private TextView f919d;
    private CheckBox f920e;
    private TextView f921f;
    private Drawable f922g;
    private int f923h;
    private Context f924i;
    private boolean f925j;
    private int f926k;
    private Context f927l;
    private LayoutInflater f928m;
    private boolean f929n;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.f927l = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.MenuView, i, 0);
        this.f922g = obtainStyledAttributes.getDrawable(5);
        this.f923h = obtainStyledAttributes.getResourceId(1, -1);
        this.f925j = obtainStyledAttributes.getBoolean(7, false);
        this.f924i = context;
        obtainStyledAttributes.recycle();
    }

    private void m1718c() {
        this.f917b = (ImageView) m1721f().inflate(R.abc_list_menu_item_icon, this, false);
        addView(this.f917b, 0);
    }

    private void m1719d() {
        this.f918c = (RadioButton) m1721f().inflate(R.abc_list_menu_item_radio, this, false);
        addView(this.f918c);
    }

    private void m1720e() {
        this.f920e = (CheckBox) m1721f().inflate(R.abc_list_menu_item_checkbox, this, false);
        addView(this.f920e);
    }

    private LayoutInflater m1721f() {
        if (this.f928m == null) {
            this.f928m = LayoutInflater.from(this.f927l);
        }
        return this.f928m;
    }

    public MenuItemImpl m1722a() {
        return this.f916a;
    }

    public void m1723a(MenuItemImpl menuItemImpl, int i) {
        this.f916a = menuItemImpl;
        this.f926k = i;
        setVisibility(menuItemImpl.isVisible() ? 0 : 8);
        setTitle(menuItemImpl.m1801a((MenuView) this));
        setCheckable(menuItemImpl.isCheckable());
        setShortcut(menuItemImpl.m1814e(), menuItemImpl.m1809c());
        setIcon(menuItemImpl.getIcon());
        setEnabled(menuItemImpl.isEnabled());
    }

    public boolean m1724b() {
        return false;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setBackgroundDrawable(this.f922g);
        this.f919d = (TextView) findViewById(R.title);
        if (this.f923h != -1) {
            this.f919d.setTextAppearance(this.f924i, this.f923h);
        }
        this.f921f = (TextView) findViewById(R.shortcut);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f917b != null && this.f925j) {
            LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f917b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        if (z || this.f918c != null || this.f920e != null) {
            CompoundButton compoundButton;
            CompoundButton compoundButton2;
            if (this.f916a.m1815f()) {
                if (this.f918c == null) {
                    m1719d();
                }
                compoundButton = this.f918c;
                compoundButton2 = this.f920e;
            } else {
                if (this.f920e == null) {
                    m1720e();
                }
                compoundButton = this.f920e;
                compoundButton2 = this.f918c;
            }
            if (z) {
                compoundButton.setChecked(this.f916a.isChecked());
                int i = z ? 0 : 8;
                if (compoundButton.getVisibility() != i) {
                    compoundButton.setVisibility(i);
                }
                if (compoundButton2 != null && compoundButton2.getVisibility() != 8) {
                    compoundButton2.setVisibility(8);
                    return;
                }
                return;
            }
            if (this.f920e != null) {
                this.f920e.setVisibility(8);
            }
            if (this.f918c != null) {
                this.f918c.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f916a.m1815f()) {
            if (this.f918c == null) {
                m1719d();
            }
            compoundButton = this.f918c;
        } else {
            if (this.f920e == null) {
                m1720e();
            }
            compoundButton = this.f920e;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.f929n = z;
        this.f925j = z;
    }

    public void setIcon(Drawable drawable) {
        int i = (this.f916a.m1817h() || this.f929n) ? 1 : 0;
        if (i == 0 && !this.f925j) {
            return;
        }
        if (this.f917b != null || drawable != null || this.f925j) {
            if (this.f917b == null) {
                m1718c();
            }
            if (drawable != null || this.f925j) {
                ImageView imageView = this.f917b;
                if (i == 0) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.f917b.getVisibility() != 0) {
                    this.f917b.setVisibility(0);
                    return;
                }
                return;
            }
            this.f917b.setVisibility(8);
        }
    }

    public void setShortcut(boolean z, char c) {
        int i = (z && this.f916a.m1814e()) ? 0 : 8;
        if (i == 0) {
            this.f921f.setText(this.f916a.m1811d());
        }
        if (this.f921f.getVisibility() != i) {
            this.f921f.setVisibility(i);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.f919d.setText(charSequence);
            if (this.f919d.getVisibility() != 0) {
                this.f919d.setVisibility(0);
            }
        } else if (this.f919d.getVisibility() != 8) {
            this.f919d.setVisibility(8);
        }
    }
}
