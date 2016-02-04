package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.internal.view.menu.MenuPresenter.MenuPresenter;
import android.support.v7.internal.view.menu.MenuView.MenuView;
import android.support.v7.internal.widget.ListPopupWindow;
import android.support.v7.p010a.R.R;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;
import java.util.ArrayList;

/* renamed from: android.support.v7.internal.view.menu.n */
public class MenuPopupHelper implements MenuPresenter, OnKeyListener, OnGlobalLayoutListener, OnItemClickListener, OnDismissListener {
    static final int f852b;
    private Context f853a;
    boolean f854c;
    private LayoutInflater f855d;
    private ListPopupWindow f856e;
    private MenuBuilder f857f;
    private int f858g;
    private View f859h;
    private boolean f860i;
    private ViewTreeObserver f861j;
    private MenuPopupHelper f862k;
    private MenuPresenter f863l;
    private ViewGroup f864m;

    /* renamed from: android.support.v7.internal.view.menu.n.a */
    private class MenuPopupHelper extends BaseAdapter {
        final /* synthetic */ MenuPopupHelper f1020a;
        private MenuBuilder f1021b;
        private int f1022c;

        public MenuPopupHelper(MenuPopupHelper menuPopupHelper, MenuBuilder menuBuilder) {
            this.f1020a = menuPopupHelper;
            this.f1022c = -1;
            this.f1021b = menuBuilder;
            m1839a();
        }

        public MenuItemImpl m1838a(int i) {
            ArrayList l = this.f1020a.f860i ? this.f1021b.m1790l() : this.f1021b.m1787i();
            if (this.f1022c >= 0 && i >= this.f1022c) {
                i++;
            }
            return (MenuItemImpl) l.get(i);
        }

        void m1839a() {
            MenuItemImpl r = this.f1020a.f857f.m1796r();
            if (r != null) {
                ArrayList l = this.f1020a.f857f.m1790l();
                int size = l.size();
                for (int i = 0; i < size; i++) {
                    if (((MenuItemImpl) l.get(i)) == r) {
                        this.f1022c = i;
                        return;
                    }
                }
            }
            this.f1022c = -1;
        }

        public int getCount() {
            ArrayList l = this.f1020a.f860i ? this.f1021b.m1790l() : this.f1021b.m1787i();
            return this.f1022c < 0 ? l.size() : l.size() - 1;
        }

        public /* synthetic */ Object getItem(int i) {
            return m1838a(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = view == null ? this.f1020a.f855d.inflate(MenuPopupHelper.f852b, viewGroup, false) : view;
            MenuView menuView = (MenuView) inflate;
            if (this.f1020a.f854c) {
                ((ListMenuItemView) inflate).setForceShowIcon(true);
            }
            menuView.m1618a(m1838a(i), 0);
            return inflate;
        }

        public void notifyDataSetChanged() {
            m1839a();
            super.notifyDataSetChanged();
        }
    }

    static {
        f852b = R.abc_popup_menu_item_layout;
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z) {
        this.f853a = context;
        this.f855d = LayoutInflater.from(context);
        this.f857f = menuBuilder;
        this.f860i = z;
        Resources resources = context.getResources();
        this.f858g = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.abc_config_prefDialogWidth));
        this.f859h = view;
        menuBuilder.m1767a((MenuPresenter) this);
    }

    private int m1640a(ListAdapter listAdapter) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(0, 0);
        int count = listAdapter.getCount();
        int i = 0;
        int i2 = 0;
        View view = null;
        int i3 = 0;
        while (i < count) {
            View view2;
            int itemViewType = listAdapter.getItemViewType(i);
            if (itemViewType != i2) {
                view2 = null;
            } else {
                itemViewType = i2;
                view2 = view;
            }
            if (this.f864m == null) {
                this.f864m = new FrameLayout(this.f853a);
            }
            view = listAdapter.getView(i, view2, this.f864m);
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i3 = Math.max(i3, view.getMeasuredWidth());
            i++;
            i2 = itemViewType;
        }
        return i3;
    }

    public void m1644a(Context context, MenuBuilder menuBuilder) {
    }

    public void m1645a(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.f857f) {
            m1651b();
            if (this.f863l != null) {
                this.f863l.m1503a(menuBuilder, z);
            }
        }
    }

    public void m1646a(MenuPresenter menuPresenter) {
        this.f863l = menuPresenter;
    }

    public void m1647a(boolean z) {
        this.f854c = z;
    }

    public boolean m1648a() {
        boolean z = false;
        this.f856e = new ListPopupWindow(this.f853a, null, R.popupMenuStyle);
        this.f856e.m1996a((OnDismissListener) this);
        this.f856e.m1994a((OnItemClickListener) this);
        this.f862k = new MenuPopupHelper(this, this.f857f);
        this.f856e.m1995a(this.f862k);
        this.f856e.m1997a(true);
        View view = this.f859h;
        if (view == null) {
            return false;
        }
        if (this.f861j == null) {
            z = true;
        }
        this.f861j = view.getViewTreeObserver();
        if (z) {
            this.f861j.addOnGlobalLayoutListener(this);
        }
        this.f856e.m1993a(view);
        this.f856e.m2005e(Math.min(m1640a(this.f862k), this.f858g));
        this.f856e.m2006f(2);
        this.f856e.m2000c();
        this.f856e.m2010h().setOnKeyListener(this);
        return true;
    }

    public boolean m1649a(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public boolean m1650a(SubMenuBuilder subMenuBuilder) {
        if (subMenuBuilder.hasVisibleItems()) {
            boolean z;
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.f853a, subMenuBuilder, this.f859h, false);
            menuPopupHelper.m1646a(this.f863l);
            int size = subMenuBuilder.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = subMenuBuilder.getItem(i);
                if (item.isVisible() && item.getIcon() != null) {
                    z = true;
                    break;
                }
            }
            z = false;
            menuPopupHelper.m1647a(z);
            if (menuPopupHelper.m1648a()) {
                if (this.f863l == null) {
                    return true;
                }
                this.f863l.m1504b(subMenuBuilder);
                return true;
            }
        }
        return false;
    }

    public void m1651b() {
        if (m1654c()) {
            this.f856e.m2002d();
        }
    }

    public boolean m1652b(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        return false;
    }

    public void m1653c(boolean z) {
        if (this.f862k != null) {
            this.f862k.notifyDataSetChanged();
        }
    }

    public boolean m1654c() {
        return this.f856e != null && this.f856e.m2007f();
    }

    public boolean m1655g() {
        return false;
    }

    public void onDismiss() {
        this.f856e = null;
        this.f857f.close();
        if (this.f861j != null) {
            if (!this.f861j.isAlive()) {
                this.f861j = this.f859h.getViewTreeObserver();
            }
            this.f861j.removeGlobalOnLayoutListener(this);
            this.f861j = null;
        }
    }

    public void onGlobalLayout() {
        if (m1654c()) {
            View view = this.f859h;
            if (view == null || !view.isShown()) {
                m1651b();
            } else if (m1654c()) {
                this.f856e.m2000c();
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MenuPopupHelper menuPopupHelper = this.f862k;
        menuPopupHelper.f1021b.m1772a(menuPopupHelper.m1838a(i), 0);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        m1651b();
        return true;
    }
}
