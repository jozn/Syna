package android.support.v7.internal.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* renamed from: android.support.v7.internal.widget.j */
class ActivityChooserView implements OnGlobalLayoutListener {
    final /* synthetic */ ActivityChooserView f1282a;

    ActivityChooserView(ActivityChooserView activityChooserView) {
        this.f1282a = activityChooserView;
    }

    public void onGlobalLayout() {
        if (!this.f1282a.m1980c()) {
            return;
        }
        if (this.f1282a.isShown()) {
            this.f1282a.m1970d().m2000c();
            if (this.f1282a.f1152a != null) {
                this.f1282a.f1152a.m986a(true);
                return;
            }
            return;
        }
        this.f1282a.m1970d().m2002d();
    }
}
