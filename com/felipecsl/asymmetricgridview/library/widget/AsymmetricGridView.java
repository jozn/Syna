package com.felipecsl.asymmetricgridview.library.widget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* renamed from: com.felipecsl.asymmetricgridview.library.widget.b */
class AsymmetricGridView implements OnGlobalLayoutListener {
    final /* synthetic */ AsymmetricGridView f1563a;

    AsymmetricGridView(AsymmetricGridView asymmetricGridView) {
        this.f1563a = asymmetricGridView;
    }

    public void onGlobalLayout() {
        this.f1563a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        this.f1563a.m2318b();
        if (this.f1563a.f1532g != null) {
            this.f1563a.f1532g.m2289a();
        }
    }
}
