package android.support.v7.internal.view.menu;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.p005a.p006a.SupportMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;

/* renamed from: android.support.v7.internal.view.menu.r */
class MenuWrapperICS extends BaseMenuWrapper<Menu> implements SupportMenu {
    MenuWrapperICS(Menu menu) {
        super(menu);
    }

    public MenuItem add(int i) {
        return m1731a(((Menu) this.a).add(i));
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return m1731a(((Menu) this.a).add(i, i2, i3, i4));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return m1731a(((Menu) this.a).add(i, i2, i3, charSequence));
    }

    public MenuItem add(CharSequence charSequence) {
        return m1731a(((Menu) this.a).add(charSequence));
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = null;
        if (menuItemArr != null) {
            menuItemArr2 = new MenuItem[menuItemArr.length];
        }
        int addIntentOptions = ((Menu) this.a).addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = m1731a(menuItemArr2[i5]);
            }
        }
        return addIntentOptions;
    }

    public SubMenu addSubMenu(int i) {
        return m1732a(((Menu) this.a).addSubMenu(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return m1732a(((Menu) this.a).addSubMenu(i, i2, i3, i4));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return m1732a(((Menu) this.a).addSubMenu(i, i2, i3, charSequence));
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return m1732a(((Menu) this.a).addSubMenu(charSequence));
    }

    public void clear() {
        m1733a();
        ((Menu) this.a).clear();
    }

    public void close() {
        ((Menu) this.a).close();
    }

    public MenuItem findItem(int i) {
        return m1731a(((Menu) this.a).findItem(i));
    }

    public MenuItem getItem(int i) {
        return m1731a(((Menu) this.a).getItem(i));
    }

    public boolean hasVisibleItems() {
        return ((Menu) this.a).hasVisibleItems();
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return ((Menu) this.a).isShortcutKey(i, keyEvent);
    }

    public boolean performIdentifierAction(int i, int i2) {
        return ((Menu) this.a).performIdentifierAction(i, i2);
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return ((Menu) this.a).performShortcut(i, keyEvent, i2);
    }

    public void removeGroup(int i) {
        m1734a(i);
        ((Menu) this.a).removeGroup(i);
    }

    public void removeItem(int i) {
        m1735b(i);
        ((Menu) this.a).removeItem(i);
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        ((Menu) this.a).setGroupCheckable(i, z, z2);
    }

    public void setGroupEnabled(int i, boolean z) {
        ((Menu) this.a).setGroupEnabled(i, z);
    }

    public void setGroupVisible(int i, boolean z) {
        ((Menu) this.a).setGroupVisible(i, z);
    }

    public void setQwertyMode(boolean z) {
        ((Menu) this.a).setQwertyMode(z);
    }

    public int size() {
        return ((Menu) this.a).size();
    }
}
