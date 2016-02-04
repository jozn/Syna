package android.support.v7.internal.view;

import android.content.Context;
import android.support.v7.p011b.ActionMode;
import android.view.MenuInflater;

/* renamed from: android.support.v7.internal.view.b */
public class ActionModeWrapper extends ActionMode {
    final MenuInflater f796a;
    final android.view.ActionMode f797b;

    public ActionModeWrapper(Context context, android.view.ActionMode actionMode) {
        this.f797b = actionMode;
        this.f796a = new SupportMenuInflater(context);
    }

    public void m1597a() {
        this.f797b.finish();
    }
}
