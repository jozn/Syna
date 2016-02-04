package android.support.v7.internal.view.menu;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuBuilder.MenuBuilder;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public final class ExpandedMenuView extends ListView implements MenuBuilder, MenuView, OnItemClickListener {
    private MenuBuilder f915a;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
    }

    public void m1716a(MenuBuilder menuBuilder) {
        this.f915a = menuBuilder;
    }

    public boolean m1717a(MenuItemImpl menuItemImpl) {
        return this.f915a.m1772a((MenuItem) menuItemImpl, 0);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        m1717a((MenuItemImpl) getAdapter().getItem(i));
    }
}
