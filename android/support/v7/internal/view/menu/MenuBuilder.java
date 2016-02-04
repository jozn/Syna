package android.support.v7.internal.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.p005a.p006a.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.p010a.R.R;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

/* renamed from: android.support.v7.internal.view.menu.g */
public class MenuBuilder implements SupportMenu {
    private static final int[] f958d;
    CharSequence f959a;
    Drawable f960b;
    View f961c;
    private final Context f962e;
    private final Resources f963f;
    private boolean f964g;
    private boolean f965h;
    private MenuBuilder f966i;
    private ArrayList<MenuItemImpl> f967j;
    private ArrayList<MenuItemImpl> f968k;
    private boolean f969l;
    private ArrayList<MenuItemImpl> f970m;
    private ArrayList<MenuItemImpl> f971n;
    private boolean f972o;
    private int f973p;
    private ContextMenuInfo f974q;
    private boolean f975r;
    private boolean f976s;
    private boolean f977t;
    private boolean f978u;
    private ArrayList<MenuItemImpl> f979v;
    private CopyOnWriteArrayList<WeakReference<MenuPresenter>> f980w;
    private MenuItemImpl f981x;

    /* renamed from: android.support.v7.internal.view.menu.g.a */
    public interface MenuBuilder {
        void m1501a(MenuBuilder menuBuilder);

        boolean m1502a(MenuBuilder menuBuilder, MenuItem menuItem);
    }

    /* renamed from: android.support.v7.internal.view.menu.g.b */
    public interface MenuBuilder {
        boolean m1705a(MenuItemImpl menuItemImpl);
    }

    static {
        f958d = new int[]{1, 4, 5, 3, 2, 0};
    }

    public MenuBuilder(Context context) {
        this.f973p = 0;
        this.f975r = false;
        this.f976s = false;
        this.f977t = false;
        this.f978u = false;
        this.f979v = new ArrayList();
        this.f980w = new CopyOnWriteArrayList();
        this.f962e = context;
        this.f963f = context.getResources();
        this.f967j = new ArrayList();
        this.f968k = new ArrayList();
        this.f969l = true;
        this.f970m = new ArrayList();
        this.f971n = new ArrayList();
        this.f972o = true;
        m1756d(true);
    }

    private static int m1749a(ArrayList<MenuItemImpl> arrayList, int i) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (((MenuItemImpl) arrayList.get(size)).m1806b() <= i) {
                return size + 1;
            }
        }
        return 0;
    }

    private MenuItem m1750a(int i, int i2, int i3, CharSequence charSequence) {
        int c = MenuBuilder.m1754c(i3);
        MenuItem menuItemImpl = new MenuItemImpl(this, i, i2, i3, c, charSequence, this.f973p);
        if (this.f974q != null) {
            menuItemImpl.m1803a(this.f974q);
        }
        this.f967j.add(MenuBuilder.m1749a(this.f967j, c), menuItemImpl);
        m1777b(true);
        return menuItemImpl;
    }

    private void m1751a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources d = m1781d();
        if (view != null) {
            this.f961c = view;
            this.f959a = null;
            this.f960b = null;
        } else {
            if (i > 0) {
                this.f959a = d.getText(i);
            } else if (charSequence != null) {
                this.f959a = charSequence;
            }
            if (i2 > 0) {
                this.f960b = d.getDrawable(i2);
            } else if (drawable != null) {
                this.f960b = drawable;
            }
            this.f961c = null;
        }
        m1777b(false);
    }

    private void m1752a(int i, boolean z) {
        if (i >= 0 && i < this.f967j.size()) {
            this.f967j.remove(i);
            if (z) {
                m1777b(true);
            }
        }
    }

    private boolean m1753a(SubMenuBuilder subMenuBuilder) {
        if (this.f980w.isEmpty()) {
            return false;
        }
        Iterator it = this.f980w.iterator();
        boolean z = false;
        while (it.hasNext()) {
            boolean z2;
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
            if (menuPresenter == null) {
                this.f980w.remove(weakReference);
                z2 = z;
            } else {
                z2 = !z ? menuPresenter.m1636a(subMenuBuilder) : z;
            }
            z = z2;
        }
        return z;
    }

    private static int m1754c(int i) {
        int i2 = (-65536 & i) >> 16;
        if (i2 >= 0 && i2 < f958d.length) {
            return (f958d[i2] << 16) | (InBandBytestreamManager.MAXIMUM_BLOCK_SIZE & i);
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    private void m1755c(boolean z) {
        if (!this.f980w.isEmpty()) {
            m1785g();
            Iterator it = this.f980w.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f980w.remove(weakReference);
                } else {
                    menuPresenter.m1638c(z);
                }
            }
            m1786h();
        }
    }

    private void m1756d(boolean z) {
        boolean z2 = true;
        if (!(z && this.f963f.getConfiguration().keyboard != 1 && this.f963f.getBoolean(R.abc_config_showMenuShortcutsWhenKeyboardPresent))) {
            z2 = false;
        }
        this.f965h = z2;
    }

    public int m1757a(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((MenuItemImpl) this.f967j.get(i2)).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public int m1758a(int i, int i2) {
        int size = size();
        if (i2 < 0) {
            i2 = 0;
        }
        for (int i3 = i2; i3 < size; i3++) {
            if (((MenuItemImpl) this.f967j.get(i3)).getGroupId() == i) {
                return i3;
            }
        }
        return -1;
    }

    protected MenuBuilder m1759a(Drawable drawable) {
        m1751a(0, null, 0, drawable, null);
        return this;
    }

    protected MenuBuilder m1760a(View view) {
        m1751a(0, null, 0, null, view);
        return this;
    }

    protected MenuBuilder m1761a(CharSequence charSequence) {
        m1751a(0, charSequence, 0, null, null);
        return this;
    }

    MenuItemImpl m1762a(int i, KeyEvent keyEvent) {
        ArrayList arrayList = this.f979v;
        arrayList.clear();
        m1769a(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return (MenuItemImpl) arrayList.get(0);
        }
        boolean b = m1778b();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i2);
            char alphabeticShortcut = b ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
            if (alphabeticShortcut == keyData.meta[0] && (metaState & 2) == 0) {
                return menuItemImpl;
            }
            if (alphabeticShortcut == keyData.meta[2] && (metaState & 2) != 0) {
                return menuItemImpl;
            }
            if (b && alphabeticShortcut == '\b' && i == 67) {
                return menuItemImpl;
            }
        }
        return null;
    }

    protected String m1763a() {
        return "android:menu:actionviewstates";
    }

    public void m1764a(Bundle bundle) {
        int size = size();
        int i = 0;
        SparseArray sparseArray = null;
        while (i < size) {
            MenuItem item = getItem(i);
            View a = MenuItemCompat.m1041a(item);
            if (!(a == null || a.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                a.saveHierarchyState(sparseArray);
                if (MenuItemCompat.m1045c(item)) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            SparseArray sparseArray2 = sparseArray;
            if (item.hasSubMenu()) {
                ((SubMenuBuilder) item.getSubMenu()).m1764a(bundle);
            }
            i++;
            sparseArray = sparseArray2;
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(m1763a(), sparseArray);
        }
    }

    public void m1765a(MenuBuilder menuBuilder) {
        this.f966i = menuBuilder;
    }

    void m1766a(MenuItemImpl menuItemImpl) {
        this.f969l = true;
        m1777b(true);
    }

    public void m1767a(MenuPresenter menuPresenter) {
        this.f980w.add(new WeakReference(menuPresenter));
        menuPresenter.m1633a(this.f962e, this);
        this.f972o = true;
    }

    void m1768a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.f967j.size();
        for (int i = 0; i < size; i++) {
            MenuItem menuItem2 = (MenuItemImpl) this.f967j.get(i);
            if (menuItem2.getGroupId() == groupId && menuItem2.m1815f() && menuItem2.isCheckable()) {
                menuItem2.m1808b(menuItem2 == menuItem);
            }
        }
    }

    void m1769a(List<MenuItemImpl> list, int i, KeyEvent keyEvent) {
        boolean b = m1778b();
        int metaState = keyEvent.getMetaState();
        KeyData keyData = new KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.f967j.size();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) this.f967j.get(i2);
                if (menuItemImpl.hasSubMenu()) {
                    ((MenuBuilder) menuItemImpl.getSubMenu()).m1769a(list, i, keyEvent);
                }
                char alphabeticShortcut = b ? menuItemImpl.getAlphabeticShortcut() : menuItemImpl.getNumericShortcut();
                if ((metaState & 5) == 0 && alphabeticShortcut != '\u0000' && ((alphabeticShortcut == keyData.meta[0] || alphabeticShortcut == keyData.meta[2] || (b && alphabeticShortcut == '\b' && i == 67)) && menuItemImpl.isEnabled())) {
                    list.add(menuItemImpl);
                }
            }
        }
    }

    final void m1770a(boolean z) {
        if (!this.f978u) {
            this.f978u = true;
            Iterator it = this.f980w.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f980w.remove(weakReference);
                } else {
                    menuPresenter.m1634a(this, z);
                }
            }
            this.f978u = false;
        }
    }

    boolean m1771a(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.f966i != null && this.f966i.m1502a(menuBuilder, menuItem);
    }

    public boolean m1772a(MenuItem menuItem, int i) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
        if (menuItemImpl == null || !menuItemImpl.isEnabled()) {
            return false;
        }
        boolean a = menuItemImpl.m1805a();
        ActionProvider m = menuItemImpl.m1822m();
        boolean z = m != null && m.m992g();
        boolean expandActionView;
        if (menuItemImpl.m1823n()) {
            expandActionView = menuItemImpl.expandActionView() | a;
            if (!expandActionView) {
                return expandActionView;
            }
            m1770a(true);
            return expandActionView;
        } else if (menuItemImpl.hasSubMenu() || z) {
            m1770a(false);
            if (!menuItemImpl.hasSubMenu()) {
                menuItemImpl.m1802a(new SubMenuBuilder(m1783e(), this, menuItemImpl));
            }
            SubMenuBuilder subMenuBuilder = (SubMenuBuilder) menuItemImpl.getSubMenu();
            if (z) {
                m.m985a((SubMenu) subMenuBuilder);
            }
            expandActionView = m1753a(subMenuBuilder) | a;
            if (expandActionView) {
                return expandActionView;
            }
            m1770a(true);
            return expandActionView;
        } else {
            if ((i & 1) == 0) {
                m1770a(true);
            }
            return a;
        }
    }

    public MenuItem add(int i) {
        return m1750a(0, 0, 0, this.f963f.getString(i));
    }

    public MenuItem add(int i, int i2, int i3, int i4) {
        return m1750a(i, i2, i3, this.f963f.getString(i4));
    }

    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return m1750a(i, i2, i3, charSequence);
    }

    public MenuItem add(CharSequence charSequence) {
        return m1750a(0, 0, 0, charSequence);
    }

    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.f962e.getPackageManager();
        List queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (int i5 = 0; i5 < size; i5++) {
            ResolveInfo resolveInfo = (ResolveInfo) queryIntentActivityOptions.get(i5);
            Intent intent2 = new Intent(resolveInfo.specificIndex < 0 ? intent : intentArr[resolveInfo.specificIndex]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = add(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && resolveInfo.specificIndex >= 0) {
                menuItemArr[resolveInfo.specificIndex] = intent3;
            }
        }
        return size;
    }

    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f963f.getString(i));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f963f.getString(i4));
    }

    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        MenuItemImpl menuItemImpl = (MenuItemImpl) m1750a(i, i2, i3, charSequence);
        SubMenuBuilder subMenuBuilder = new SubMenuBuilder(this.f962e, this, menuItemImpl);
        menuItemImpl.m1802a(subMenuBuilder);
        return subMenuBuilder;
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public int m1773b(int i) {
        return m1758a(i, 0);
    }

    public void m1774b(Bundle bundle) {
        if (bundle != null) {
            MenuItem item;
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(m1763a());
            int size = size();
            for (int i = 0; i < size; i++) {
                item = getItem(i);
                View a = MenuItemCompat.m1041a(item);
                if (!(a == null || a.getId() == -1)) {
                    a.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((SubMenuBuilder) item.getSubMenu()).m1774b(bundle);
                }
            }
            int i2 = bundle.getInt("android:menu:expandedactionview");
            if (i2 > 0) {
                item = findItem(i2);
                if (item != null) {
                    MenuItemCompat.m1044b(item);
                }
            }
        }
    }

    void m1775b(MenuItemImpl menuItemImpl) {
        this.f972o = true;
        m1777b(true);
    }

    public void m1776b(MenuPresenter menuPresenter) {
        Iterator it = this.f980w.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            MenuPresenter menuPresenter2 = (MenuPresenter) weakReference.get();
            if (menuPresenter2 == null || menuPresenter2 == menuPresenter) {
                this.f980w.remove(weakReference);
            }
        }
    }

    void m1777b(boolean z) {
        if (this.f975r) {
            this.f976s = true;
            return;
        }
        if (z) {
            this.f969l = true;
            this.f972o = true;
        }
        m1755c(z);
    }

    boolean m1778b() {
        return this.f964g;
    }

    public boolean m1779c() {
        return this.f965h;
    }

    public boolean m1780c(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (!this.f980w.isEmpty()) {
            m1785g();
            Iterator it = this.f980w.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f980w.remove(weakReference);
                    z = z2;
                } else {
                    z = menuPresenter.m1635a(this, menuItemImpl);
                    if (z) {
                        break;
                    }
                }
                z2 = z;
            }
            z = z2;
            m1786h();
            if (z) {
                this.f981x = menuItemImpl;
            }
        }
        return z;
    }

    public void clear() {
        if (this.f981x != null) {
            m1782d(this.f981x);
        }
        this.f967j.clear();
        m1777b(true);
    }

    public void clearHeader() {
        this.f960b = null;
        this.f959a = null;
        this.f961c = null;
        m1777b(false);
    }

    public void close() {
        m1770a(true);
    }

    Resources m1781d() {
        return this.f963f;
    }

    public boolean m1782d(MenuItemImpl menuItemImpl) {
        boolean z = false;
        if (!this.f980w.isEmpty() && this.f981x == menuItemImpl) {
            m1785g();
            Iterator it = this.f980w.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f980w.remove(weakReference);
                    z = z2;
                } else {
                    z = menuPresenter.m1637b(this, menuItemImpl);
                    if (z) {
                        break;
                    }
                }
                z2 = z;
            }
            z = z2;
            m1786h();
            if (z) {
                this.f981x = null;
            }
        }
        return z;
    }

    public Context m1783e() {
        return this.f962e;
    }

    public void m1784f() {
        if (this.f966i != null) {
            this.f966i.m1501a(this);
        }
    }

    public MenuItem findItem(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f967j.get(i2);
            if (menuItemImpl.getItemId() == i) {
                return menuItemImpl;
            }
            if (menuItemImpl.hasSubMenu()) {
                MenuItem findItem = menuItemImpl.getSubMenu().findItem(i);
                if (findItem != null) {
                    return findItem;
                }
            }
        }
        return null;
    }

    public void m1785g() {
        if (!this.f975r) {
            this.f975r = true;
            this.f976s = false;
        }
    }

    public MenuItem getItem(int i) {
        return (MenuItem) this.f967j.get(i);
    }

    public void m1786h() {
        this.f975r = false;
        if (this.f976s) {
            this.f976s = false;
            m1777b(true);
        }
    }

    public boolean hasVisibleItems() {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (((MenuItemImpl) this.f967j.get(i)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    ArrayList<MenuItemImpl> m1787i() {
        if (!this.f969l) {
            return this.f968k;
        }
        this.f968k.clear();
        int size = this.f967j.size();
        for (int i = 0; i < size; i++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f967j.get(i);
            if (menuItemImpl.isVisible()) {
                this.f968k.add(menuItemImpl);
            }
        }
        this.f969l = false;
        this.f972o = true;
        return this.f968k;
    }

    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return m1762a(i, keyEvent) != null;
    }

    public void m1788j() {
        if (this.f972o) {
            Iterator it = this.f980w.iterator();
            int i = 0;
            while (it.hasNext()) {
                int i2;
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    this.f980w.remove(weakReference);
                    i2 = i;
                } else {
                    i2 = menuPresenter.m1639g() | i;
                }
                i = i2;
            }
            if (i != 0) {
                this.f970m.clear();
                this.f971n.clear();
                ArrayList i3 = m1787i();
                int size = i3.size();
                for (int i4 = 0; i4 < size; i4++) {
                    MenuItemImpl menuItemImpl = (MenuItemImpl) i3.get(i4);
                    if (menuItemImpl.m1818i()) {
                        this.f970m.add(menuItemImpl);
                    } else {
                        this.f971n.add(menuItemImpl);
                    }
                }
            } else {
                this.f970m.clear();
                this.f971n.clear();
                this.f971n.addAll(m1787i());
            }
            this.f972o = false;
        }
    }

    ArrayList<MenuItemImpl> m1789k() {
        m1788j();
        return this.f970m;
    }

    ArrayList<MenuItemImpl> m1790l() {
        m1788j();
        return this.f971n;
    }

    public CharSequence m1791m() {
        return this.f959a;
    }

    public Drawable m1792n() {
        return this.f960b;
    }

    public View m1793o() {
        return this.f961c;
    }

    public MenuBuilder m1794p() {
        return this;
    }

    public boolean performIdentifierAction(int i, int i2) {
        return m1772a(findItem(i), i2);
    }

    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        MenuItem a = m1762a(i, keyEvent);
        boolean z = false;
        if (a != null) {
            z = m1772a(a, i2);
        }
        if ((i2 & 2) != 0) {
            m1770a(true);
        }
        return z;
    }

    boolean m1795q() {
        return this.f977t;
    }

    public MenuItemImpl m1796r() {
        return this.f981x;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeGroup(int r6) {
        /*
        r5 = this;
        r1 = 0;
        r3 = r5.m1773b(r6);
        if (r3 < 0) goto L_0x002b;
    L_0x0007:
        r0 = r5.f967j;
        r0 = r0.size();
        r4 = r0 - r3;
        r0 = r1;
    L_0x0010:
        r2 = r0 + 1;
        if (r0 >= r4) goto L_0x0027;
    L_0x0014:
        r0 = r5.f967j;
        r0 = r0.get(r3);
        r0 = (android.support.v7.internal.view.menu.MenuItemImpl) r0;
        r0 = r0.getGroupId();
        if (r0 != r6) goto L_0x0027;
    L_0x0022:
        r5.m1752a(r3, r1);
        r0 = r2;
        goto L_0x0010;
    L_0x0027:
        r0 = 1;
        r5.m1777b(r0);
    L_0x002b:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.internal.view.menu.g.removeGroup(int):void");
    }

    public void removeItem(int i) {
        m1752a(m1757a(i), true);
    }

    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.f967j.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f967j.get(i2);
            if (menuItemImpl.getGroupId() == i) {
                menuItemImpl.m1804a(z2);
                menuItemImpl.setCheckable(z);
            }
        }
    }

    public void setGroupEnabled(int i, boolean z) {
        int size = this.f967j.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f967j.get(i2);
            if (menuItemImpl.getGroupId() == i) {
                menuItemImpl.setEnabled(z);
            }
        }
    }

    public void setGroupVisible(int i, boolean z) {
        int size = this.f967j.size();
        int i2 = 0;
        boolean z2 = false;
        while (i2 < size) {
            MenuItemImpl menuItemImpl = (MenuItemImpl) this.f967j.get(i2);
            boolean z3 = (menuItemImpl.getGroupId() == i && menuItemImpl.m1810c(z)) ? true : z2;
            i2++;
            z2 = z3;
        }
        if (z2) {
            m1777b(true);
        }
    }

    public void setQwertyMode(boolean z) {
        this.f964g = z;
        m1777b(false);
    }

    public int size() {
        return this.f967j.size();
    }
}
