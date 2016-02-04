package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ListMenuPresenter;
import android.support.v7.internal.view.menu.MenuBuilder.MenuBuilder;
import android.support.v7.internal.view.menu.MenuPresenter.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarView;
import android.support.v7.p010a.R.R;
import android.support.v7.p011b.ActionMode;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

/* renamed from: android.support.v7.app.b */
class ActionBarActivityDelegateBase extends ActionBarActivityDelegate implements MenuBuilder, MenuPresenter {
    private static final int[] f746d;
    private ActionBarView f747e;
    private ListMenuPresenter f748f;
    private android.support.v7.internal.view.menu.MenuBuilder f749g;
    private ActionMode f750h;
    private boolean f751i;
    private CharSequence f752j;
    private boolean f753k;
    private boolean f754l;
    private boolean f755m;
    private boolean f756n;
    private boolean f757o;
    private Bundle f758p;

    static {
        f746d = new int[]{R.homeAsUpIndicator};
    }

    ActionBarActivityDelegateBase(ActionBarActivity actionBarActivity) {
        super(actionBarActivity);
    }

    private MenuView m1505a(Context context, MenuPresenter menuPresenter) {
        if (this.f749g == null) {
            return null;
        }
        if (this.f748f == null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R.Theme);
            int resourceId = obtainStyledAttributes.getResourceId(4, R.Theme_AppCompat_CompactMenu);
            obtainStyledAttributes.recycle();
            this.f748f = new ListMenuPresenter(R.abc_list_menu_item_layout, resourceId);
            this.f748f.m1743a(menuPresenter);
            this.f749g.m1767a(this.f748f);
        } else {
            this.f748f.m1747c(false);
        }
        return this.f748f.m1739a(new FrameLayout(context));
    }

    private void m1506b(android.support.v7.internal.view.menu.MenuBuilder menuBuilder, boolean z) {
        if (this.f747e == null || !this.f747e.m1943f()) {
            menuBuilder.close();
        } else if (this.f747e.m1942e() && z) {
            this.f747e.m1941d();
        } else if (this.f747e.getVisibility() == 0) {
            this.f747e.m1939b();
        }
    }

    private void m1507l() {
        TypedValue typedValue;
        TypedValue typedValue2;
        TypedValue typedValue3;
        int dimension;
        int dimension2;
        TypedValue typedValue4 = null;
        TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.ActionBarWindow);
        if (obtainStyledAttributes.hasValue(3)) {
            typedValue = null == null ? new TypedValue() : null;
            obtainStyledAttributes.getValue(3, typedValue);
        } else {
            typedValue = null;
        }
        if (obtainStyledAttributes.hasValue(5)) {
            typedValue2 = null == null ? new TypedValue() : null;
            obtainStyledAttributes.getValue(5, typedValue2);
        } else {
            typedValue2 = null;
        }
        if (obtainStyledAttributes.hasValue(6)) {
            typedValue3 = null == null ? new TypedValue() : null;
            obtainStyledAttributes.getValue(6, typedValue3);
        } else {
            typedValue3 = null;
        }
        if (obtainStyledAttributes.hasValue(4)) {
            if (null == null) {
                typedValue4 = new TypedValue();
            }
            obtainStyledAttributes.getValue(4, typedValue4);
        }
        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
        Object obj = displayMetrics.widthPixels < displayMetrics.heightPixels ? 1 : null;
        if (obj == null) {
            typedValue2 = typedValue;
        }
        if (!(typedValue2 == null || typedValue2.type == 0)) {
            if (typedValue2.type == 5) {
                dimension = (int) typedValue2.getDimension(displayMetrics);
            } else if (typedValue2.type == 6) {
                dimension = (int) typedValue2.getFraction((float) displayMetrics.widthPixels, (float) displayMetrics.widthPixels);
            }
            if (obj == null) {
                typedValue3 = typedValue4;
            }
            if (!(typedValue3 == null || typedValue3.type == 0)) {
                if (typedValue3.type != 5) {
                    dimension2 = (int) typedValue3.getDimension(displayMetrics);
                } else if (typedValue3.type == 6) {
                    dimension2 = (int) typedValue3.getFraction((float) displayMetrics.heightPixels, (float) displayMetrics.heightPixels);
                }
                if (!(dimension == -1 && dimension2 == -1)) {
                    this.a.getWindow().setLayout(dimension, dimension2);
                }
                obtainStyledAttributes.recycle();
            }
            dimension2 = -1;
            this.a.getWindow().setLayout(dimension, dimension2);
            obtainStyledAttributes.recycle();
        }
        dimension = -1;
        if (obj == null) {
            typedValue3 = typedValue4;
        }
        if (typedValue3.type != 5) {
            if (typedValue3.type == 6) {
                dimension2 = (int) typedValue3.getFraction((float) displayMetrics.heightPixels, (float) displayMetrics.heightPixels);
            }
            dimension2 = -1;
        } else {
            dimension2 = (int) typedValue3.getDimension(displayMetrics);
        }
        this.a.getWindow().setLayout(dimension, dimension2);
        obtainStyledAttributes.recycle();
    }

    private boolean m1508m() {
        this.f749g = new android.support.v7.internal.view.menu.MenuBuilder(m1500j());
        this.f749g.m1765a((MenuBuilder) this);
        return true;
    }

    private boolean m1509n() {
        if (this.f756n) {
            return true;
        }
        if (this.f749g == null || this.f757o) {
            if (this.f749g == null && (!m1508m() || this.f749g == null)) {
                return false;
            }
            if (this.f747e != null) {
                this.f747e.setMenu(this.f749g, this);
            }
            this.f749g.m1785g();
            if (this.a.m1465a(0, this.f749g)) {
                this.f757o = false;
            } else {
                this.f749g = null;
                if (this.f747e != null) {
                    this.f747e.setMenu(null, this);
                }
                return false;
            }
        }
        this.f749g.m1785g();
        if (this.f758p != null) {
            this.f749g.m1774b(this.f758p);
            this.f758p = null;
        }
        if (this.a.m1467a(0, null, this.f749g)) {
            this.f749g.m1786h();
            this.f756n = true;
            return true;
        }
        if (this.f747e != null) {
            this.f747e.setMenu(null, this);
        }
        this.f749g.m1786h();
        return false;
    }

    public ActionBar m1510a() {
        m1530k();
        return new ActionBarImplBase(this.a, this.a);
    }

    public void m1511a(int i) {
        m1530k();
        ViewGroup viewGroup = (ViewGroup) this.a.findViewById(16908290);
        viewGroup.removeAllViews();
        this.a.getLayoutInflater().inflate(i, viewGroup);
        this.a.m1477h();
    }

    public void m1512a(Configuration configuration) {
        if (this.b && this.f751i) {
            ((ActionBarImplBase) m1490b()).m1562a(configuration);
        }
    }

    public void m1513a(android.support.v7.internal.view.menu.MenuBuilder menuBuilder) {
        m1506b(menuBuilder, true);
    }

    public void m1514a(android.support.v7.internal.view.menu.MenuBuilder menuBuilder, boolean z) {
        if (!this.f755m) {
            this.f755m = true;
            this.a.closeOptionsMenu();
            this.f747e.m1944g();
            this.f755m = false;
        }
    }

    public void m1515a(View view) {
        m1530k();
        ViewGroup viewGroup = (ViewGroup) this.a.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.a.m1477h();
    }

    public void m1516a(View view, LayoutParams layoutParams) {
        m1530k();
        ViewGroup viewGroup = (ViewGroup) this.a.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.a.m1477h();
    }

    public void m1517a(CharSequence charSequence) {
        if (this.f747e != null) {
            this.f747e.setWindowTitle(charSequence);
        } else {
            this.f752j = charSequence;
        }
    }

    public boolean m1518a(int i, Menu menu) {
        return i != 0 ? this.a.m1465a(i, menu) : false;
    }

    public boolean m1519a(int i, MenuItem menuItem) {
        if (i == 0) {
            menuItem = MenuWrapperFactory.m1842a(menuItem);
        }
        return this.a.m1466a(i, menuItem);
    }

    public boolean m1520a(int i, View view, Menu menu) {
        return i != 0 ? this.a.m1467a(i, view, menu) : false;
    }

    public boolean m1521a(android.support.v7.internal.view.menu.MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.a.onMenuItemSelected(0, menuItem);
    }

    public View m1522b(int i) {
        return (i == 0 && m1509n()) ? (View) m1505a(this.a, (MenuPresenter) this) : null;
    }

    public void m1523b(View view, LayoutParams layoutParams) {
        m1530k();
        ((ViewGroup) this.a.findViewById(16908290)).addView(view, layoutParams);
        this.a.m1477h();
    }

    public boolean m1524b(android.support.v7.internal.view.menu.MenuBuilder menuBuilder) {
        return false;
    }

    public void m1525d() {
        ActionBarImplBase actionBarImplBase = (ActionBarImplBase) m1490b();
        if (actionBarImplBase != null) {
            actionBarImplBase.m1568c(false);
        }
    }

    public void m1526e() {
        ActionBarImplBase actionBarImplBase = (ActionBarImplBase) m1490b();
        if (actionBarImplBase != null) {
            actionBarImplBase.m1568c(true);
        }
    }

    public void m1527f() {
        if (this.f749g != null) {
            Bundle bundle = new Bundle();
            this.f749g.m1764a(bundle);
            if (bundle.size() > 0) {
                this.f758p = bundle;
            }
            this.f749g.m1785g();
            this.f749g.clear();
        }
        this.f757o = true;
        if (this.f747e != null) {
            this.f756n = false;
            m1509n();
        }
    }

    public boolean m1528g() {
        if (this.f750h != null) {
            this.f750h.m1586a();
            return true;
        } else if (this.f747e == null || !this.f747e.m1948k()) {
            return false;
        } else {
            this.f747e.m1949l();
            return true;
        }
    }

    public void m1529h() {
    }

    final void m1530k() {
        if (!this.f751i) {
            if (this.b) {
                boolean z;
                if (this.c) {
                    this.a.a_(R.abc_action_bar_decor_overlay);
                } else {
                    this.a.a_(R.abc_action_bar_decor);
                }
                this.f747e = (ActionBarView) this.a.findViewById(R.action_bar);
                this.f747e.setWindowCallback(this.a);
                if (this.f753k) {
                    this.f747e.m1945h();
                }
                if (this.f754l) {
                    this.f747e.m1946i();
                }
                boolean equals = "splitActionBarWhenNarrow".equals(m1499i());
                if (equals) {
                    z = this.a.getResources().getBoolean(R.abc_split_action_bar_is_narrow);
                } else {
                    TypedArray obtainStyledAttributes = this.a.obtainStyledAttributes(R.ActionBarWindow);
                    boolean z2 = obtainStyledAttributes.getBoolean(2, false);
                    obtainStyledAttributes.recycle();
                    z = z2;
                }
                ActionBarContainer actionBarContainer = (ActionBarContainer) this.a.findViewById(R.split_action_bar);
                if (actionBarContainer != null) {
                    this.f747e.setSplitView(actionBarContainer);
                    this.f747e.setSplitActionBar(z);
                    this.f747e.setSplitWhenNarrow(equals);
                    ActionBarContextView actionBarContextView = (ActionBarContextView) this.a.findViewById(R.action_context_bar);
                    actionBarContextView.setSplitView(actionBarContainer);
                    actionBarContextView.setSplitActionBar(z);
                    actionBarContextView.setSplitWhenNarrow(equals);
                }
            } else {
                this.a.a_(R.abc_simple_decor);
            }
            this.a.findViewById(16908290).setId(-1);
            this.a.findViewById(R.action_bar_activity_content).setId(16908290);
            if (this.f752j != null) {
                this.f747e.setWindowTitle(this.f752j);
                this.f752j = null;
            }
            m1507l();
            this.f751i = true;
            this.a.getWindow().getDecorView().post(new ActionBarActivityDelegateBase(this));
        }
    }
}
