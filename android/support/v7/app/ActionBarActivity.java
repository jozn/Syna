package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ab;
import android.support.v4.app.af;
import android.support.v4.app.af.TaskStackBuilder;
import android.support.v7.app.ActionBar.C0035a;
import android.support.v7.p011b.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ActionBarActivity extends FragmentActivity implements TaskStackBuilder, C0035a {
    ActionBarActivityDelegate f739n;

    public Intent m1460a() {
        return ab.m175a(this);
    }

    public void m1461a(af afVar) {
        afVar.m188a((Activity) this);
    }

    public void m1462a(ActionMode actionMode) {
    }

    void m1463a(View view) {
        super.setContentView(view);
    }

    void m1464a(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
    }

    boolean m1465a(int i, Menu menu) {
        return super.onCreatePanelMenu(i, menu);
    }

    boolean m1466a(int i, MenuItem menuItem) {
        return super.onMenuItemSelected(i, menuItem);
    }

    boolean m1467a(int i, View view, Menu menu) {
        return super.onPreparePanel(i, view, menu);
    }

    public boolean m1468a(Intent intent) {
        return ab.m177a((Activity) this, intent);
    }

    protected boolean m1469a(View view, Menu menu) {
        return this.f739n.m1489a(view, menu);
    }

    void a_(int i) {
        super.setContentView(i);
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        this.f739n.m1492b(view, layoutParams);
    }

    public void m1470b(Intent intent) {
        ab.m180b((Activity) this, intent);
    }

    public void m1471b(af afVar) {
    }

    public void m1472b(ActionMode actionMode) {
    }

    void m1473b(View view, LayoutParams layoutParams) {
        super.addContentView(view, layoutParams);
    }

    boolean m1474b(View view, Menu menu) {
        return super.m120a(view, menu);
    }

    public void e_() {
        if (VERSION.SDK_INT >= 14) {
            super.e_();
        }
        this.f739n.m1496f();
    }

    public ActionBar m1475f() {
        return this.f739n.m1490b();
    }

    public boolean m1476g() {
        Intent a = m1460a();
        if (a == null) {
            return false;
        }
        if (m1468a(a)) {
            af a2 = af.m187a((Context) this);
            m1461a(a2);
            m1471b(a2);
            a2.m191a();
            try {
                ActivityCompat.m133a(this);
            } catch (IllegalStateException e) {
                finish();
            }
        } else {
            m1470b(a);
        }
        return true;
    }

    public MenuInflater getMenuInflater() {
        return this.f739n.m1493c();
    }

    public void m1477h() {
    }

    public void onBackPressed() {
        if (!this.f739n.m1497g()) {
            super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f739n.m1481a(configuration);
    }

    public final void onContentChanged() {
        this.f739n.m1498h();
    }

    protected void onCreate(Bundle bundle) {
        this.f739n = ActionBarActivityDelegate.m1478a(this);
        super.onCreate(bundle);
        this.f739n.m1482a(bundle);
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        return this.f739n.m1486a(i, menu);
    }

    public View onCreatePanelView(int i) {
        return i == 0 ? this.f739n.m1491b(i) : super.onCreatePanelView(i);
    }

    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (this.f739n.m1487a(i, menuItem)) {
            return true;
        }
        ActionBar f = m1475f();
        return (menuItem.getItemId() != 16908332 || f == null || (f.m1456a() & 4) == 0) ? false : m1476g();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.f739n.m1495e();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        return this.f739n.m1488a(i, view, menu);
    }

    protected void onStop() {
        super.onStop();
        this.f739n.m1494d();
    }

    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        this.f739n.m1485a(charSequence);
    }

    public void setContentView(int i) {
        this.f739n.m1480a(i);
    }

    public void setContentView(View view) {
        this.f739n.m1483a(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        this.f739n.m1484a(view, layoutParams);
    }
}
