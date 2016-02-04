package com.mmdt.syna.view.components.p025a;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.mmdt.syna.view.components.a.e */
class CustomDialog implements OnClickListener {
    private final /* synthetic */ Dialog f1821a;
    private final /* synthetic */ ContextMenuItemContainer f1822b;

    CustomDialog(Dialog dialog, ContextMenuItemContainer contextMenuItemContainer) {
        this.f1821a = dialog;
        this.f1822b = contextMenuItemContainer;
    }

    public void onClick(View view) {
        this.f1821a.dismiss();
        this.f1822b.m2736b().run();
    }
}
