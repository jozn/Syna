package android.support.v7.internal.view.menu;

import android.graphics.drawable.Drawable;
import android.support.v4.p005a.p006a.SupportSubMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.internal.view.menu.t */
class SubMenuWrapperICS extends MenuWrapperICS implements SupportSubMenu {
    SubMenuWrapperICS(SubMenu subMenu) {
        super(subMenu);
    }

    public void clearHeader() {
        ((SubMenu) this.a).clearHeader();
    }

    public MenuItem getItem() {
        return m1731a(((SubMenu) this.a).getItem());
    }

    public SubMenu setHeaderIcon(int i) {
        ((SubMenu) this.a).setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        ((SubMenu) this.a).setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        ((SubMenu) this.a).setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        ((SubMenu) this.a).setHeaderTitle(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        ((SubMenu) this.a).setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i) {
        ((SubMenu) this.a).setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable) {
        ((SubMenu) this.a).setIcon(drawable);
        return this;
    }
}
