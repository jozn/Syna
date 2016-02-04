package android.support.v7.internal.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.support.v7.p010a.R.R;

/* renamed from: android.support.v7.internal.view.a */
public class ActionBarPolicy {
    private Context f795a;

    private ActionBarPolicy(Context context) {
        this.f795a = context;
    }

    public static ActionBarPolicy m1589a(Context context) {
        return new ActionBarPolicy(context);
    }

    public int m1590a() {
        return this.f795a.getResources().getInteger(R.abc_max_action_buttons);
    }

    public boolean m1591b() {
        return VERSION.SDK_INT >= 11;
    }

    public int m1592c() {
        return this.f795a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean m1593d() {
        return this.f795a.getResources().getBoolean(R.abc_action_bar_embed_tabs_pre_jb);
    }

    public int m1594e() {
        TypedArray obtainStyledAttributes = this.f795a.obtainStyledAttributes(null, R.ActionBar, R.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(1, 0);
        Resources resources = this.f795a.getResources();
        if (!m1593d()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(R.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public boolean m1595f() {
        return this.f795a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int m1596g() {
        return this.f795a.getResources().getDimensionPixelSize(R.abc_action_bar_stacked_tab_max_width);
    }
}
