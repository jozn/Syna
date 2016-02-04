package android.support.v7.app;

import android.support.v7.app.ActionBar.C0035a;
import android.support.v7.internal.widget.NativeActionModeAwareLayout;
import android.support.v7.internal.widget.NativeActionModeAwareLayout.C0038a;
import android.support.v7.p010a.R.R;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuItem;

/* renamed from: android.support.v7.app.i */
class ActionBarImplHC extends ActionBarImplBase implements C0038a {
    final NativeActionModeAwareLayout f789b;
    private ActionMode f790c;

    /* renamed from: android.support.v7.app.i.a */
    private class ActionBarImplHC implements Callback {
        final /* synthetic */ ActionBarImplHC f787a;
        private final Callback f788b;

        ActionBarImplHC(ActionBarImplHC actionBarImplHC, Callback callback) {
            this.f787a = actionBarImplHC;
            this.f788b = callback;
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.f788b.onActionItemClicked(actionMode, menuItem);
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            boolean onCreateActionMode = this.f788b.onCreateActionMode(actionMode, menu);
            if (onCreateActionMode) {
                this.f787a.f790c = actionMode;
                this.f787a.m1569d();
            }
            return onCreateActionMode;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.f788b.onDestroyActionMode(actionMode);
            this.f787a.m1571e();
            this.f787a.f790c = null;
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.f788b.onPrepareActionMode(actionMode, menu);
        }
    }

    public ActionBarImplHC(ActionBarActivity actionBarActivity, C0035a c0035a) {
        super(actionBarActivity, c0035a);
        this.f789b = (NativeActionModeAwareLayout) actionBarActivity.findViewById(R.action_bar_root);
        if (this.f789b != null) {
            this.f789b.setActionModeForChildListener(this);
        }
    }

    public Callback m1576a(Callback callback) {
        return new ActionBarImplHC(this, callback);
    }

    boolean m1577f() {
        return this.f790c == null && super.m1573f();
    }
}
