package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuPresenter.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView.MenuView;
import android.support.v7.p010a.R.R;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import java.util.ArrayList;

/* renamed from: android.support.v7.internal.view.menu.f */
public class ListMenuPresenter implements MenuPresenter, OnItemClickListener {
    Context f949a;
    LayoutInflater f950b;
    MenuBuilder f951c;
    ExpandedMenuView f952d;
    int f953e;
    int f954f;
    ListMenuPresenter f955g;
    private int f956h;
    private MenuPresenter f957i;

    /* renamed from: android.support.v7.internal.view.menu.f.a */
    private class ListMenuPresenter extends BaseAdapter {
        final /* synthetic */ ListMenuPresenter f947a;
        private int f948b;

        public ListMenuPresenter(ListMenuPresenter listMenuPresenter) {
            this.f947a = listMenuPresenter;
            this.f948b = -1;
            m1737a();
        }

        public MenuItemImpl m1736a(int i) {
            ArrayList l = this.f947a.f951c.m1790l();
            int a = this.f947a.f956h + i;
            if (this.f948b >= 0 && a >= this.f948b) {
                a++;
            }
            return (MenuItemImpl) l.get(a);
        }

        void m1737a() {
            MenuItemImpl r = this.f947a.f951c.m1796r();
            if (r != null) {
                ArrayList l = this.f947a.f951c.m1790l();
                int size = l.size();
                for (int i = 0; i < size; i++) {
                    if (((MenuItemImpl) l.get(i)) == r) {
                        this.f948b = i;
                        return;
                    }
                }
            }
            this.f948b = -1;
        }

        public int getCount() {
            int size = this.f947a.f951c.m1790l().size() - this.f947a.f956h;
            return this.f948b < 0 ? size : size - 1;
        }

        public /* synthetic */ Object getItem(int i) {
            return m1736a(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = view == null ? this.f947a.f950b.inflate(this.f947a.f954f, viewGroup, false) : view;
            ((MenuView) inflate).m1618a(m1736a(i), 0);
            return inflate;
        }

        public void notifyDataSetChanged() {
            m1737a();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(int i, int i2) {
        this.f954f = i;
        this.f953e = i2;
    }

    public MenuView m1739a(ViewGroup viewGroup) {
        if (this.f955g == null) {
            this.f955g = new ListMenuPresenter(this);
        }
        if (this.f955g.isEmpty()) {
            return null;
        }
        if (this.f952d == null) {
            this.f952d = (ExpandedMenuView) this.f950b.inflate(R.abc_expanded_menu_layout, viewGroup, false);
            this.f952d.setAdapter(this.f955g);
            this.f952d.setOnItemClickListener(this);
        }
        return this.f952d;
    }

    public ListAdapter m1740a() {
        if (this.f955g == null) {
            this.f955g = new ListMenuPresenter(this);
        }
        return this.f955g;
    }

    public void m1741a(Context context, MenuBuilder menuBuilder) {
        if (this.f953e != 0) {
            this.f949a = new ContextThemeWrapper(context, this.f953e);
            this.f950b = LayoutInflater.from(this.f949a);
        } else if (this.f949a != null) {
            this.f949a = context;
            if (this.f950b == null) {
                this.f950b = LayoutInflater.from(this.f949a);
            }
        }
        this.f951c = menuBuilder;
        if (this.f955g != null) {
            this.f955g.notifyDataSetChanged();
        }
    }

    public void m1742a(MenuBuilder menuBuilder, boolean z) {
        if (this.f957i != null) {
            this.f957i.m1503a(menuBuilder, z);
        }
    }

    public void m1743a(MenuPresenter menuPresenter) {
        this.f957i = menuPresenter;
    }

    public boolean m1744a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean m1745a(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder).m1628a(null);
        if (this.f957i != null) {
            this.f957i.m1504b(subMenuBuilder);
        }
        return true;
    }

    public boolean m1746b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void m1747c(boolean z) {
        if (this.f955g != null) {
            this.f955g.notifyDataSetChanged();
        }
    }

    public boolean m1748g() {
        return false;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f951c.m1772a(this.f955g.m1736a(i), 0);
    }
}
