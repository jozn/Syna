package android.support.v7.internal.view.menu;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.IBinder;
import android.support.v7.internal.view.menu.MenuPresenter.MenuPresenter;
import android.support.v7.p010a.R.R;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

/* renamed from: android.support.v7.internal.view.menu.h */
public class MenuDialogHelper implements OnClickListener, OnDismissListener, OnKeyListener, MenuPresenter {
    private MenuBuilder f844a;
    ListMenuPresenter f845b;
    private AlertDialog f846c;
    private MenuPresenter f847d;

    public MenuDialogHelper(MenuBuilder menuBuilder) {
        this.f844a = menuBuilder;
    }

    public void m1627a() {
        if (this.f846c != null) {
            this.f846c.dismiss();
        }
    }

    public void m1628a(IBinder iBinder) {
        MenuBuilder menuBuilder = this.f844a;
        Builder builder = new Builder(menuBuilder.m1783e());
        this.f845b = new ListMenuPresenter(R.abc_list_menu_item_layout, R.Theme_AppCompat_CompactMenu_Dialog);
        this.f845b.m1743a((MenuPresenter) this);
        this.f844a.m1767a(this.f845b);
        builder.setAdapter(this.f845b.m1740a(), this);
        View o = menuBuilder.m1793o();
        if (o != null) {
            builder.setCustomTitle(o);
        } else {
            builder.setIcon(menuBuilder.m1792n()).setTitle(menuBuilder.m1791m());
        }
        builder.setOnKeyListener(this);
        this.f846c = builder.create();
        this.f846c.setOnDismissListener(this);
        LayoutParams attributes = this.f846c.getWindow().getAttributes();
        attributes.type = 1003;
        if (iBinder != null) {
            attributes.token = iBinder;
        }
        attributes.flags |= 131072;
        this.f846c.show();
    }

    public void m1629a(MenuBuilder menuBuilder, boolean z) {
        if (z || menuBuilder == this.f844a) {
            m1627a();
        }
        if (this.f847d != null) {
            this.f847d.m1503a(menuBuilder, z);
        }
    }

    public boolean m1630b(MenuBuilder menuBuilder) {
        return this.f847d != null ? this.f847d.m1504b(menuBuilder) : false;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.f844a.m1772a((MenuItemImpl) this.f845b.m1740a().getItem(i), 0);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f845b.m1742a(this.f844a, true);
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i == 82 || i == 4) {
            Window window;
            View decorView;
            DispatcherState keyDispatcherState;
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                window = this.f846c.getWindow();
                if (window != null) {
                    decorView = window.getDecorView();
                    if (decorView != null) {
                        keyDispatcherState = decorView.getKeyDispatcherState();
                        if (keyDispatcherState != null) {
                            keyDispatcherState.startTracking(keyEvent, this);
                            return true;
                        }
                    }
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled()) {
                window = this.f846c.getWindow();
                if (window != null) {
                    decorView = window.getDecorView();
                    if (decorView != null) {
                        keyDispatcherState = decorView.getKeyDispatcherState();
                        if (keyDispatcherState != null && keyDispatcherState.isTracking(keyEvent)) {
                            this.f844a.m1770a(true);
                            dialogInterface.dismiss();
                            return true;
                        }
                    }
                }
            }
        }
        return this.f844a.performShortcut(i, keyEvent, 0);
    }
}
