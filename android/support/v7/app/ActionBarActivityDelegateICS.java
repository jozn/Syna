package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.internal.view.ActionModeWrapper;
import android.support.v7.internal.view.menu.MenuWrapperFactory;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.Window.Callback;
import android.view.WindowManager.LayoutParams;
import android.view.accessibility.AccessibilityEvent;

/* renamed from: android.support.v7.app.e */
class ActionBarActivityDelegateICS extends ActionBarActivityDelegate {
    Menu f762d;

    /* renamed from: android.support.v7.app.e.a */
    class ActionBarActivityDelegateICS implements Callback {
        final Callback f760a;
        final /* synthetic */ ActionBarActivityDelegateICS f761b;

        public ActionBarActivityDelegateICS(ActionBarActivityDelegateICS actionBarActivityDelegateICS, Callback callback) {
            this.f761b = actionBarActivityDelegateICS;
            this.f760a = callback;
        }

        public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
            return this.f760a.dispatchGenericMotionEvent(motionEvent);
        }

        public boolean dispatchKeyEvent(KeyEvent keyEvent) {
            return this.f760a.dispatchKeyEvent(keyEvent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
            return this.f760a.dispatchKeyShortcutEvent(keyEvent);
        }

        public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            return this.f760a.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return this.f760a.dispatchTouchEvent(motionEvent);
        }

        public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
            return this.f760a.dispatchTrackballEvent(motionEvent);
        }

        public void onActionModeFinished(ActionMode actionMode) {
            this.f760a.onActionModeFinished(actionMode);
            this.f761b.m1546b(actionMode);
        }

        public void onActionModeStarted(ActionMode actionMode) {
            this.f760a.onActionModeStarted(actionMode);
            this.f761b.m1538a(actionMode);
        }

        public void onAttachedToWindow() {
            this.f760a.onAttachedToWindow();
        }

        public void onContentChanged() {
            this.f760a.onContentChanged();
        }

        public boolean onCreatePanelMenu(int i, Menu menu) {
            return this.f760a.onCreatePanelMenu(i, menu);
        }

        public View onCreatePanelView(int i) {
            return this.f760a.onCreatePanelView(i);
        }

        public void onDetachedFromWindow() {
            this.f760a.onDetachedFromWindow();
        }

        public boolean onMenuItemSelected(int i, MenuItem menuItem) {
            return this.f760a.onMenuItemSelected(i, menuItem);
        }

        public boolean onMenuOpened(int i, Menu menu) {
            return this.f760a.onMenuOpened(i, menu);
        }

        public void onPanelClosed(int i, Menu menu) {
            this.f760a.onPanelClosed(i, menu);
        }

        public boolean onPreparePanel(int i, View view, Menu menu) {
            return this.f760a.onPreparePanel(i, view, menu);
        }

        public boolean onSearchRequested() {
            return this.f760a.onSearchRequested();
        }

        public void onWindowAttributesChanged(LayoutParams layoutParams) {
            this.f760a.onWindowAttributesChanged(layoutParams);
        }

        public void onWindowFocusChanged(boolean z) {
            this.f760a.onWindowFocusChanged(z);
        }

        public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
            return this.f760a.onWindowStartingActionMode(callback);
        }
    }

    ActionBarActivityDelegateICS(ActionBarActivity actionBarActivity) {
        super(actionBarActivity);
    }

    public ActionBar m1532a() {
        return new ActionBarImplICS(this.a, this.a);
    }

    ActionModeWrapper m1533a(Context context, ActionMode actionMode) {
        return new ActionModeWrapper(context, actionMode);
    }

    Callback m1534a(Callback callback) {
        return new ActionBarActivityDelegateICS(this, callback);
    }

    public void m1535a(int i) {
        this.a.a_(i);
    }

    public void m1536a(Configuration configuration) {
    }

    public void m1537a(Bundle bundle) {
        if ("splitActionBarWhenNarrow".equals(m1499i())) {
            this.a.getWindow().setUiOptions(1, 1);
        }
        super.m1482a(bundle);
        if (this.b) {
            this.a.requestWindowFeature(8);
        }
        if (this.c) {
            this.a.requestWindowFeature(9);
        }
        Window window = this.a.getWindow();
        window.setCallback(m1534a(window.getCallback()));
    }

    public void m1538a(ActionMode actionMode) {
        this.a.m1462a(m1533a(m1500j(), actionMode));
    }

    public void m1539a(View view) {
        this.a.m1463a(view);
    }

    public void m1540a(View view, ViewGroup.LayoutParams layoutParams) {
        this.a.m1464a(view, layoutParams);
    }

    public void m1541a(CharSequence charSequence) {
    }

    public boolean m1542a(int i, Menu menu) {
        if (i != 0 && i != 8) {
            return this.a.m1465a(i, menu);
        }
        if (this.f762d == null) {
            this.f762d = MenuWrapperFactory.m1841a(menu);
        }
        return this.a.m1465a(i, this.f762d);
    }

    public boolean m1543a(int i, MenuItem menuItem) {
        if (i == 0) {
            menuItem = MenuWrapperFactory.m1842a(menuItem);
        }
        return this.a.m1466a(i, menuItem);
    }

    public boolean m1544a(int i, View view, Menu menu) {
        return (i == 0 || i == 8) ? this.a.m1467a(i, view, this.f762d) : this.a.m1467a(i, view, menu);
    }

    public View m1545b(int i) {
        return null;
    }

    public void m1546b(ActionMode actionMode) {
        this.a.m1472b(m1533a(m1500j(), actionMode));
    }

    public void m1547b(View view, ViewGroup.LayoutParams layoutParams) {
        this.a.m1473b(view, layoutParams);
    }

    public void m1548d() {
    }

    public void m1549e() {
    }

    public void m1550f() {
        this.f762d = null;
    }

    public boolean m1551g() {
        return false;
    }

    public void m1552h() {
        this.a.m1477h();
    }
}
