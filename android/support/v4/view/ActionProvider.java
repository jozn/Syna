package android.support.v4.view;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v4.view.h */
public abstract class ActionProvider {
    private final Context f557a;
    private ActionProvider f558b;
    private ActionProvider f559c;

    /* renamed from: android.support.v4.view.h.a */
    public interface ActionProvider {
        void m979a(boolean z);
    }

    /* renamed from: android.support.v4.view.h.b */
    public interface ActionProvider {
        void m980a(boolean z);
    }

    public Context m981a() {
        return this.f557a;
    }

    public View m982a(MenuItem menuItem) {
        return m987b();
    }

    public void m983a(ActionProvider actionProvider) {
        this.f558b = actionProvider;
    }

    public void m984a(ActionProvider actionProvider) {
        if (!(this.f559c == null || actionProvider == null)) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f559c = actionProvider;
    }

    public void m985a(SubMenu subMenu) {
    }

    public void m986a(boolean z) {
        if (this.f558b != null) {
            this.f558b.m979a(z);
        }
    }

    public abstract View m987b();

    public boolean m988c() {
        return false;
    }

    public boolean m989d() {
        return true;
    }

    public void m990e() {
        if (this.f559c != null && m988c()) {
            this.f559c.m980a(m989d());
        }
    }

    public boolean m991f() {
        return false;
    }

    public boolean m992g() {
        return false;
    }
}
