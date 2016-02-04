package android.support.v7.internal.view.menu;

import android.os.Build.VERSION;
import android.support.v4.p005a.p006a.SupportMenuItem;
import android.support.v4.p005a.p006a.SupportSubMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* renamed from: android.support.v7.internal.view.menu.q */
public final class MenuWrapperFactory {
    public static SupportSubMenu m1840a(SubMenu subMenu) {
        if (VERSION.SDK_INT >= 14) {
            return new SubMenuWrapperICS(subMenu);
        }
        throw new UnsupportedOperationException();
    }

    public static Menu m1841a(Menu menu) {
        return VERSION.SDK_INT >= 14 ? new MenuWrapperICS(menu) : menu;
    }

    public static MenuItem m1842a(MenuItem menuItem) {
        return VERSION.SDK_INT >= 16 ? new MenuItemWrapperJB(menuItem) : VERSION.SDK_INT >= 14 ? new MenuItemWrapperICS(menuItem) : menuItem;
    }

    public static SupportMenuItem m1843b(MenuItem menuItem) {
        if (VERSION.SDK_INT >= 16) {
            return new MenuItemWrapperJB(menuItem);
        }
        if (VERSION.SDK_INT >= 14) {
            return new MenuItemWrapperICS(menuItem);
        }
        throw new UnsupportedOperationException();
    }
}
