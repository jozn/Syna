package android.support.v7.app;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ab;
import android.support.v7.internal.view.SupportMenuInflater;
import android.support.v7.p010a.R.R;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/* renamed from: android.support.v7.app.a */
abstract class ActionBarActivityDelegate {
    final ActionBarActivity f740a;
    boolean f741b;
    boolean f742c;
    private ActionBar f743d;
    private MenuInflater f744e;
    private boolean f745f;

    ActionBarActivityDelegate(ActionBarActivity actionBarActivity) {
        this.f740a = actionBarActivity;
    }

    static ActionBarActivityDelegate m1478a(ActionBarActivity actionBarActivity) {
        return VERSION.SDK_INT >= 18 ? new ActionBarActivityDelegateJBMR2(actionBarActivity) : VERSION.SDK_INT >= 16 ? new ActionBarActivityDelegateJB(actionBarActivity) : VERSION.SDK_INT >= 14 ? new ActionBarActivityDelegateICS(actionBarActivity) : VERSION.SDK_INT >= 11 ? new ActionBarActivityDelegateHC(actionBarActivity) : new ActionBarActivityDelegateBase(actionBarActivity);
    }

    abstract ActionBar m1479a();

    abstract void m1480a(int i);

    abstract void m1481a(Configuration configuration);

    void m1482a(Bundle bundle) {
        TypedArray obtainStyledAttributes = this.f740a.obtainStyledAttributes(R.ActionBarWindow);
        if (obtainStyledAttributes.hasValue(0)) {
            this.f741b = obtainStyledAttributes.getBoolean(0, false);
            this.f742c = obtainStyledAttributes.getBoolean(1, false);
            obtainStyledAttributes.recycle();
            if (ab.m178b(this.f740a) == null) {
                return;
            }
            if (this.f743d == null) {
                this.f745f = true;
                return;
            } else {
                this.f743d.m1457a(true);
                return;
            }
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    abstract void m1483a(View view);

    abstract void m1484a(View view, LayoutParams layoutParams);

    abstract void m1485a(CharSequence charSequence);

    abstract boolean m1486a(int i, Menu menu);

    abstract boolean m1487a(int i, MenuItem menuItem);

    abstract boolean m1488a(int i, View view, Menu menu);

    boolean m1489a(View view, Menu menu) {
        return VERSION.SDK_INT < 16 ? this.f740a.onPrepareOptionsMenu(menu) : this.f740a.m1474b(view, menu);
    }

    final ActionBar m1490b() {
        if (!this.f741b && !this.f742c) {
            this.f743d = null;
        } else if (this.f743d == null) {
            this.f743d = m1479a();
            if (this.f745f) {
                this.f743d.m1457a(true);
            }
        }
        return this.f743d;
    }

    abstract View m1491b(int i);

    abstract void m1492b(View view, LayoutParams layoutParams);

    MenuInflater m1493c() {
        if (this.f744e == null) {
            this.f744e = new SupportMenuInflater(m1500j());
        }
        return this.f744e;
    }

    abstract void m1494d();

    abstract void m1495e();

    abstract void m1496f();

    abstract boolean m1497g();

    abstract void m1498h();

    protected final String m1499i() {
        String str = null;
        try {
            ActivityInfo activityInfo = this.f740a.getPackageManager().getActivityInfo(this.f740a.getComponentName(), 128);
            if (activityInfo.metaData != null) {
                str = activityInfo.metaData.getString("android.support.UI_OPTIONS");
            }
        } catch (NameNotFoundException e) {
            Log.e("ActionBarActivityDelegate", "getUiOptionsFromMetadata: Activity '" + this.f740a.getClass().getSimpleName() + "' not in manifest");
        }
        return str;
    }

    protected final Context m1500j() {
        Context context = this.f740a;
        ActionBar b = m1490b();
        return b != null ? b.m1458b() : context;
    }
}
