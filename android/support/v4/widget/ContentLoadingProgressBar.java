package android.support.v4.widget;

/* renamed from: android.support.v4.widget.b */
class ContentLoadingProgressBar implements Runnable {
    final /* synthetic */ ContentLoadingProgressBar f673a;

    ContentLoadingProgressBar(ContentLoadingProgressBar contentLoadingProgressBar) {
        this.f673a = contentLoadingProgressBar;
    }

    public void run() {
        this.f673a.f570b = false;
        this.f673a.f569a = -1;
        this.f673a.setVisibility(8);
    }
}
