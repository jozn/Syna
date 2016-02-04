package android.support.v4.view;

import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.view.o */
class MenuItemCompatHoneycomb {
    public static MenuItem m1046a(MenuItem menuItem, View view) {
        return menuItem.setActionView(view);
    }

    public static View m1047a(MenuItem menuItem) {
        return menuItem.getActionView();
    }

    public static void m1048a(MenuItem menuItem, int i) {
        menuItem.setShowAsAction(i);
    }

    public static MenuItem m1049b(MenuItem menuItem, int i) {
        return menuItem.setActionView(i);
    }
}
