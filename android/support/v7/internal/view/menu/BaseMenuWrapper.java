package android.support.v7.internal.view.menu;

import android.support.v4.p005a.p006a.SupportMenuItem;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: android.support.v7.internal.view.menu.d */
abstract class BaseMenuWrapper<T> extends BaseWrapper<T> {
    private HashMap<MenuItem, SupportMenuItem> f945b;
    private HashMap<SubMenu, SubMenu> f946c;

    BaseMenuWrapper(T t) {
        super(t);
    }

    final SupportMenuItem m1731a(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }
        if (this.f945b == null) {
            this.f945b = new HashMap();
        }
        SupportMenuItem supportMenuItem = (SupportMenuItem) this.f945b.get(menuItem);
        if (supportMenuItem != null) {
            return supportMenuItem;
        }
        supportMenuItem = MenuWrapperFactory.m1843b(menuItem);
        this.f945b.put(menuItem, supportMenuItem);
        return supportMenuItem;
    }

    final SubMenu m1732a(SubMenu subMenu) {
        if (subMenu == null) {
            return null;
        }
        if (this.f946c == null) {
            this.f946c = new HashMap();
        }
        SubMenu subMenu2 = (SubMenu) this.f946c.get(subMenu);
        if (subMenu2 != null) {
            return subMenu2;
        }
        subMenu2 = MenuWrapperFactory.m1840a(subMenu);
        this.f946c.put(subMenu, subMenu2);
        return subMenu2;
    }

    final void m1733a() {
        if (this.f945b != null) {
            this.f945b.clear();
        }
        if (this.f946c != null) {
            this.f946c.clear();
        }
    }

    final void m1734a(int i) {
        if (this.f945b != null) {
            Iterator it = this.f945b.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getGroupId()) {
                    it.remove();
                }
            }
        }
    }

    final void m1735b(int i) {
        if (this.f945b != null) {
            Iterator it = this.f945b.keySet().iterator();
            while (it.hasNext()) {
                if (i == ((MenuItem) it.next()).getItemId()) {
                    it.remove();
                    return;
                }
            }
        }
    }
}
