package android.support.v7.widget;

import android.view.inputmethod.InputMethodManager;

/* renamed from: android.support.v7.widget.a */
class SearchView implements Runnable {
    final /* synthetic */ SearchView f1358a;

    SearchView(SearchView searchView) {
        this.f1358a = searchView;
    }

    public void run() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.f1358a.getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            SearchView.f1315a.m2100a(inputMethodManager, this.f1358a, 0);
        }
    }
}
