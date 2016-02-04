package android.support.v7.internal.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.internal.view.menu.MenuBuilder.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.internal.view.menu.s */
public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuBuilder f1023d;
    private MenuItemImpl f1024e;

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.f1023d = menuBuilder;
        this.f1024e = menuItemImpl;
    }

    public String m1844a() {
        int itemId = this.f1024e != null ? this.f1024e.getItemId() : 0;
        return itemId == 0 ? null : super.m1763a() + ":" + itemId;
    }

    public void m1845a(MenuBuilder menuBuilder) {
        this.f1023d.m1765a(menuBuilder);
    }

    public boolean m1846a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return super.m1771a(menuBuilder, menuItem) || this.f1023d.m1771a(menuBuilder, menuItem);
    }

    public boolean m1847b() {
        return this.f1023d.m1778b();
    }

    public boolean m1848c() {
        return this.f1023d.m1779c();
    }

    public boolean m1849c(MenuItemImpl menuItemImpl) {
        return this.f1023d.m1780c(menuItemImpl);
    }

    public void clearHeader() {
    }

    public boolean m1850d(MenuItemImpl menuItemImpl) {
        return this.f1023d.m1782d(menuItemImpl);
    }

    public MenuItem getItem() {
        return this.f1024e;
    }

    public MenuBuilder m1851p() {
        return this.f1023d;
    }

    public Menu m1852s() {
        return this.f1023d;
    }

    public SubMenu setHeaderIcon(int i) {
        super.m1759a(m1783e().getResources().getDrawable(i));
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        super.m1759a(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        super.m1761a(m1783e().getResources().getString(i));
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.m1761a(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.m1760a(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.f1024e.setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        this.f1024e.setIcon(drawable);
        return this;
    }

    public void setQwertyMode(boolean z) {
        this.f1023d.setQwertyMode(z);
    }
}
