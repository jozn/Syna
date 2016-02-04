package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuPresenter.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: android.support.v7.internal.view.menu.c */
public abstract class BaseMenuPresenter implements MenuPresenter {
    private MenuPresenter f867a;
    private int f868b;
    protected Context f869c;
    protected Context f870d;
    protected MenuBuilder f871e;
    protected LayoutInflater f872f;
    protected LayoutInflater f873g;
    protected MenuView f874h;
    private int f875i;
    private int f876j;

    public BaseMenuPresenter(Context context, int i, int i2) {
        this.f869c = context;
        this.f872f = LayoutInflater.from(context);
        this.f868b = i;
        this.f875i = i2;
    }

    public MenuView m1658a(ViewGroup viewGroup) {
        if (this.f874h == null) {
            this.f874h = (MenuView) this.f872f.inflate(this.f868b, viewGroup, false);
            this.f874h.m1706a(this.f871e);
            m1672c(true);
        }
        return this.f874h;
    }

    public View m1659a(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView b = view instanceof MenuView ? (MenuView) view : m1669b(viewGroup);
        m1662a(menuItemImpl, b);
        return (View) b;
    }

    public void m1660a(Context context, MenuBuilder menuBuilder) {
        this.f870d = context;
        this.f873g = LayoutInflater.from(this.f870d);
        this.f871e = menuBuilder;
    }

    public void m1661a(MenuBuilder menuBuilder, boolean z) {
        if (this.f867a != null) {
            this.f867a.m1503a(menuBuilder, z);
        }
    }

    public abstract void m1662a(MenuItemImpl menuItemImpl, MenuView menuView);

    public void m1663a(MenuPresenter menuPresenter) {
        this.f867a = menuPresenter;
    }

    protected void m1664a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.f874h).addView(view, i);
    }

    public boolean m1665a(int i, MenuItemImpl menuItemImpl) {
        return true;
    }

    public boolean m1666a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean m1667a(SubMenuBuilder subMenuBuilder) {
        return this.f867a != null ? this.f867a.m1504b(subMenuBuilder) : false;
    }

    protected boolean m1668a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    public MenuView m1669b(ViewGroup viewGroup) {
        return (MenuView) this.f872f.inflate(this.f875i, viewGroup, false);
    }

    public void m1670b(int i) {
        this.f876j = i;
    }

    public boolean m1671b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void m1672c(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.f874h;
        if (viewGroup != null) {
            int i;
            if (this.f871e != null) {
                this.f871e.m1788j();
                ArrayList i2 = this.f871e.m1787i();
                int size = i2.size();
                int i3 = 0;
                i = 0;
                while (i3 < size) {
                    int i4;
                    MenuItemImpl menuItemImpl = (MenuItemImpl) i2.get(i3);
                    if (m1665a(i, menuItemImpl)) {
                        View childAt = viewGroup.getChildAt(i);
                        MenuItemImpl a = childAt instanceof MenuView ? ((MenuView) childAt).m1617a() : null;
                        View a2 = m1659a(menuItemImpl, childAt, viewGroup);
                        if (menuItemImpl != a) {
                            a2.setPressed(false);
                        }
                        if (a2 != childAt) {
                            m1664a(a2, i);
                        }
                        i4 = i + 1;
                    } else {
                        i4 = i;
                    }
                    i3++;
                    i = i4;
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (!m1668a(viewGroup, i)) {
                    i++;
                }
            }
        }
    }

    public boolean m1673g() {
        return false;
    }
}
