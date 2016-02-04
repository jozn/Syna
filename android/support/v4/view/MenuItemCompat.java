package android.support.v4.view;

import android.os.Build.VERSION;
import android.support.v4.p005a.p006a.SupportMenuItem;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.view.n */
public class MenuItemCompat {
    static final MenuItemCompat f562a;

    /* renamed from: android.support.v4.view.n.d */
    interface MenuItemCompat {
        MenuItem m1017a(MenuItem menuItem, View view);

        View m1018a(MenuItem menuItem);

        void m1019a(MenuItem menuItem, int i);

        MenuItem m1020b(MenuItem menuItem, int i);

        boolean m1021b(MenuItem menuItem);

        boolean m1022c(MenuItem menuItem);
    }

    /* renamed from: android.support.v4.view.n.a */
    static class MenuItemCompat implements MenuItemCompat {
        MenuItemCompat() {
        }

        public MenuItem m1023a(MenuItem menuItem, View view) {
            return menuItem;
        }

        public View m1024a(MenuItem menuItem) {
            return null;
        }

        public void m1025a(MenuItem menuItem, int i) {
        }

        public MenuItem m1026b(MenuItem menuItem, int i) {
            return menuItem;
        }

        public boolean m1027b(MenuItem menuItem) {
            return false;
        }

        public boolean m1028c(MenuItem menuItem) {
            return false;
        }
    }

    /* renamed from: android.support.v4.view.n.b */
    static class MenuItemCompat implements MenuItemCompat {
        MenuItemCompat() {
        }

        public MenuItem m1029a(MenuItem menuItem, View view) {
            return MenuItemCompatHoneycomb.m1046a(menuItem, view);
        }

        public View m1030a(MenuItem menuItem) {
            return MenuItemCompatHoneycomb.m1047a(menuItem);
        }

        public void m1031a(MenuItem menuItem, int i) {
            MenuItemCompatHoneycomb.m1048a(menuItem, i);
        }

        public MenuItem m1032b(MenuItem menuItem, int i) {
            return MenuItemCompatHoneycomb.m1049b(menuItem, i);
        }

        public boolean m1033b(MenuItem menuItem) {
            return false;
        }

        public boolean m1034c(MenuItem menuItem) {
            return false;
        }
    }

    /* renamed from: android.support.v4.view.n.c */
    static class MenuItemCompat extends MenuItemCompat {
        MenuItemCompat() {
        }

        public boolean m1035b(MenuItem menuItem) {
            return MenuItemCompatIcs.m1050a(menuItem);
        }

        public boolean m1036c(MenuItem menuItem) {
            return MenuItemCompatIcs.m1051b(menuItem);
        }
    }

    /* renamed from: android.support.v4.view.n.e */
    public interface MenuItemCompat {
        boolean m1037a(MenuItem menuItem);

        boolean m1038b(MenuItem menuItem);
    }

    static {
        int i = VERSION.SDK_INT;
        if (i >= 14) {
            f562a = new MenuItemCompat();
        } else if (i >= 11) {
            f562a = new MenuItemCompat();
        } else {
            f562a = new MenuItemCompat();
        }
    }

    public static MenuItem m1039a(MenuItem menuItem, ActionProvider actionProvider) {
        if (menuItem instanceof SupportMenuItem) {
            return ((SupportMenuItem) menuItem).m44a(actionProvider);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem;
    }

    public static MenuItem m1040a(MenuItem menuItem, View view) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).setActionView(view) : f562a.m1017a(menuItem, view);
    }

    public static View m1041a(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).getActionView() : f562a.m1018a(menuItem);
    }

    public static void m1042a(MenuItem menuItem, int i) {
        if (menuItem instanceof SupportMenuItem) {
            ((SupportMenuItem) menuItem).setShowAsAction(i);
        } else {
            f562a.m1019a(menuItem, i);
        }
    }

    public static MenuItem m1043b(MenuItem menuItem, int i) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).setActionView(i) : f562a.m1020b(menuItem, i);
    }

    public static boolean m1044b(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).expandActionView() : f562a.m1021b(menuItem);
    }

    public static boolean m1045c(MenuItem menuItem) {
        return menuItem instanceof SupportMenuItem ? ((SupportMenuItem) menuItem).isActionViewExpanded() : f562a.m1022c(menuItem);
    }
}
