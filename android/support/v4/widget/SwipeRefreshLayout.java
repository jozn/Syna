package android.support.v4.widget;

/* renamed from: android.support.v4.widget.t */
class SwipeRefreshLayout implements Runnable {
    final /* synthetic */ SwipeRefreshLayout f713a;

    SwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.f713a = swipeRefreshLayout;
    }

    public void run() {
        this.f713a.f661p = true;
        if (this.f713a.f646a != null) {
            this.f713a.f657l = this.f713a.f658m;
            this.f713a.f665u.setDuration((long) this.f713a.f656k);
            this.f713a.f665u.setAnimationListener(this.f713a.f667w);
            this.f713a.f665u.reset();
            this.f713a.f665u.setInterpolator(this.f713a.f662q);
            this.f713a.startAnimation(this.f713a.f665u);
        }
        this.f713a.m1278a(this.f713a.f660o + this.f713a.getPaddingTop(), this.f713a.f666v);
    }
}
