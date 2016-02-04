package android.support.v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.ActionBar.C0035a;
import android.support.v7.app.ActionBar.OnMenuVisibilityListener;
import android.support.v7.internal.view.ActionBarPolicy;
import android.support.v7.internal.widget.ActionBarContainer;
import android.support.v7.internal.widget.ActionBarContextView;
import android.support.v7.internal.widget.ActionBarOverlayLayout;
import android.support.v7.internal.widget.ActionBarView;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.p010a.R.R;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;

/* renamed from: android.support.v7.app.h */
class ActionBarImplBase extends ActionBar {
    final Handler f763a;
    private Context f764b;
    private Context f765c;
    private ActionBarActivity f766d;
    private ActionBarOverlayLayout f767e;
    private ActionBarContainer f768f;
    private ViewGroup f769g;
    private ActionBarView f770h;
    private ActionBarContextView f771i;
    private ActionBarContainer f772j;
    private ScrollingTabContainerView f773k;
    private ArrayList<TabImpl> f774l;
    private int f775m;
    private boolean f776n;
    private ArrayList<OnMenuVisibilityListener> f777o;
    private int f778p;
    private boolean f779q;
    private int f780r;
    private boolean f781s;
    private boolean f782t;
    private boolean f783u;
    private boolean f784v;
    private boolean f785w;
    private C0035a f786x;

    public ActionBarImplBase(ActionBarActivity actionBarActivity, C0035a c0035a) {
        this.f774l = new ArrayList();
        this.f775m = -1;
        this.f777o = new ArrayList();
        this.f763a = new Handler();
        this.f780r = 0;
        this.f784v = true;
        this.f766d = actionBarActivity;
        this.f764b = actionBarActivity;
        this.f786x = c0035a;
        m1556a(this.f766d);
    }

    private void m1556a(ActionBarActivity actionBarActivity) {
        boolean z = false;
        this.f767e = (ActionBarOverlayLayout) actionBarActivity.findViewById(R.action_bar_overlay_layout);
        if (this.f767e != null) {
            this.f767e.setActionBar(this);
        }
        this.f770h = (ActionBarView) actionBarActivity.findViewById(R.action_bar);
        this.f771i = (ActionBarContextView) actionBarActivity.findViewById(R.action_context_bar);
        this.f768f = (ActionBarContainer) actionBarActivity.findViewById(R.action_bar_container);
        this.f769g = (ViewGroup) actionBarActivity.findViewById(R.top_action_bar);
        if (this.f769g == null) {
            this.f769g = this.f768f;
        }
        this.f772j = (ActionBarContainer) actionBarActivity.findViewById(R.split_action_bar);
        if (this.f770h == null || this.f771i == null || this.f768f == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with a compatible window decor layout");
        }
        this.f770h.setContextView(this.f771i);
        this.f778p = this.f770h.m1947j() ? 1 : 0;
        boolean z2 = (this.f770h.m1951n() & 4) != 0;
        if (z2) {
            this.f776n = true;
        }
        ActionBarPolicy a = ActionBarPolicy.m1589a(this.f764b);
        if (a.m1595f() || z2) {
            z = true;
        }
        m1566b(z);
        m1558f(a.m1593d());
        m1563a(this.f766d.getTitle());
    }

    private static boolean m1557a(boolean z, boolean z2, boolean z3) {
        return z3 ? true : (z || z2) ? false : true;
    }

    private void m1558f(boolean z) {
        boolean z2 = true;
        this.f779q = z;
        if (this.f779q) {
            this.f768f.setTabContainer(null);
            this.f770h.setEmbeddedTabView(this.f773k);
        } else {
            this.f770h.setEmbeddedTabView(null);
            this.f768f.setTabContainer(this.f773k);
        }
        boolean z3 = m1567c() == 2;
        if (this.f773k != null) {
            if (z3) {
                this.f773k.setVisibility(0);
            } else {
                this.f773k.setVisibility(8);
            }
        }
        ActionBarView actionBarView = this.f770h;
        if (this.f779q || !z3) {
            z2 = false;
        }
        actionBarView.setCollapsable(z2);
    }

    private void m1559g(boolean z) {
        if (ActionBarImplBase.m1557a(this.f781s, this.f782t, this.f783u)) {
            if (!this.f784v) {
                this.f784v = true;
                m1570d(z);
            }
        } else if (this.f784v) {
            this.f784v = false;
            m1572e(z);
        }
    }

    public int m1560a() {
        return this.f770h.m1951n();
    }

    public void m1561a(int i, int i2) {
        int n = this.f770h.m1951n();
        if ((i2 & 4) != 0) {
            this.f776n = true;
        }
        this.f770h.setDisplayOptions((n & (i2 ^ -1)) | (i & i2));
    }

    public void m1562a(Configuration configuration) {
        m1558f(ActionBarPolicy.m1589a(this.f764b).m1593d());
    }

    public void m1563a(CharSequence charSequence) {
        this.f770h.setTitle(charSequence);
    }

    public void m1564a(boolean z) {
        m1561a(z ? 4 : 0, 4);
    }

    public Context m1565b() {
        if (this.f765c == null) {
            TypedValue typedValue = new TypedValue();
            this.f764b.getTheme().resolveAttribute(R.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f765c = new ContextThemeWrapper(this.f764b, i);
            } else {
                this.f765c = this.f764b;
            }
        }
        return this.f765c;
    }

    public void m1566b(boolean z) {
        this.f770h.setHomeButtonEnabled(z);
    }

    public int m1567c() {
        return this.f770h.m1950m();
    }

    public void m1568c(boolean z) {
        this.f785w = z;
        if (!z) {
            this.f769g.clearAnimation();
            if (this.f772j != null) {
                this.f772j.clearAnimation();
            }
        }
    }

    void m1569d() {
        if (!this.f783u) {
            this.f783u = true;
            m1559g(false);
        }
    }

    public void m1570d(boolean z) {
        this.f769g.clearAnimation();
        if (this.f769g.getVisibility() != 0) {
            int i = (m1573f() || z) ? 1 : 0;
            if (i != 0) {
                this.f769g.startAnimation(AnimationUtils.loadAnimation(this.f764b, R.abc_slide_in_top));
            }
            this.f769g.setVisibility(0);
            if (this.f772j != null && this.f772j.getVisibility() != 0) {
                if (i != 0) {
                    this.f772j.startAnimation(AnimationUtils.loadAnimation(this.f764b, R.abc_slide_in_bottom));
                }
                this.f772j.setVisibility(0);
            }
        }
    }

    void m1571e() {
        if (this.f783u) {
            this.f783u = false;
            m1559g(false);
        }
    }

    public void m1572e(boolean z) {
        this.f769g.clearAnimation();
        if (this.f769g.getVisibility() != 8) {
            Object obj = (m1573f() || z) ? 1 : null;
            if (obj != null) {
                this.f769g.startAnimation(AnimationUtils.loadAnimation(this.f764b, R.abc_slide_out_top));
            }
            this.f769g.setVisibility(8);
            if (this.f772j != null && this.f772j.getVisibility() != 8) {
                if (obj != null) {
                    this.f772j.startAnimation(AnimationUtils.loadAnimation(this.f764b, R.abc_slide_out_bottom));
                }
                this.f772j.setVisibility(8);
            }
        }
    }

    boolean m1573f() {
        return this.f785w;
    }
}
