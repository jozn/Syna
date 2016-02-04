package android.support.v7.app;

import android.content.Context;
import android.support.v7.internal.view.ActionModeWrapper;
import android.support.v7.internal.view.ActionModeWrapperJB;
import android.view.ActionMode;

/* renamed from: android.support.v7.app.f */
class ActionBarActivityDelegateJB extends ActionBarActivityDelegateICS {
    ActionBarActivityDelegateJB(ActionBarActivity actionBarActivity) {
        super(actionBarActivity);
    }

    public ActionBar m1553a() {
        return new ActionBarImplJB(this.a, this.a);
    }

    ActionModeWrapper m1554a(Context context, ActionMode actionMode) {
        return new ActionModeWrapperJB(context, actionMode);
    }
}
