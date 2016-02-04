package android.support.v7.internal.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.View;
import android.widget.LinearLayout;

public class NativeActionModeAwareLayout extends LinearLayout {
    private C0038a f1205a;

    /* renamed from: android.support.v7.internal.widget.NativeActionModeAwareLayout.a */
    public interface C0038a {
        Callback m1574a(Callback callback);
    }

    public NativeActionModeAwareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setActionModeForChildListener(C0038a c0038a) {
        this.f1205a = c0038a;
    }

    public ActionMode startActionModeForChild(View view, Callback callback) {
        if (this.f1205a != null) {
            callback = this.f1205a.m1574a(callback);
        }
        return super.startActionModeForChild(view, callback);
    }
}
