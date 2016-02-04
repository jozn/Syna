package android.support.v7.internal.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/* renamed from: android.support.v7.internal.widget.n */
class ListPopupWindow implements OnItemSelectedListener {
    final /* synthetic */ ListPopupWindow f1290a;

    ListPopupWindow(ListPopupWindow listPopupWindow) {
        this.f1290a = listPopupWindow;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if (i != -1) {
            C0053a a = this.f1290a.f1183e;
            if (a != null) {
                a.f1171a = false;
            }
        }
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
