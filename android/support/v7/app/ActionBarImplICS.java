package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBar.C0035a;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* renamed from: android.support.v7.app.j */
class ActionBarImplICS extends ActionBar {
    final Activity f791a;
    final C0035a f792b;
    final ActionBar f793c;
    private ArrayList<WeakReference<OnMenuVisibilityListenerWrapper>> f794d;

    public ActionBarImplICS(Activity activity, C0035a c0035a) {
        this(activity, c0035a, true);
    }

    ActionBarImplICS(Activity activity, C0035a c0035a, boolean z) {
        this.f794d = new ArrayList();
        this.f791a = activity;
        this.f792b = c0035a;
        this.f793c = activity.getActionBar();
        if (z && (m1578a() & 4) != 0) {
            m1581b(true);
        }
    }

    public int m1578a() {
        return this.f793c.getDisplayOptions();
    }

    public void m1579a(boolean z) {
        this.f793c.setDisplayHomeAsUpEnabled(z);
    }

    public Context m1580b() {
        return this.f793c.getThemedContext();
    }

    public void m1581b(boolean z) {
        this.f793c.setHomeButtonEnabled(z);
    }
}
