package android.support.v4.p005a.p006a;

import android.support.v4.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v4.a.a.b */
public interface SupportMenuItem extends MenuItem {
    SupportMenuItem m44a(ActionProvider actionProvider);

    boolean expandActionView();

    View getActionView();

    boolean isActionViewExpanded();

    MenuItem setActionView(int i);

    MenuItem setActionView(View view);

    void setShowAsAction(int i);
}
