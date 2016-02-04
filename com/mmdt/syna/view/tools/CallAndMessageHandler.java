package com.mmdt.syna.view.tools;

import android.app.Activity;
import com.mmdt.syna.view.components.p025a.CustomDialog;

/* renamed from: com.mmdt.syna.view.tools.f */
class CallAndMessageHandler implements Runnable {
    final /* synthetic */ CallAndMessageHandler f3167a;
    private final /* synthetic */ Activity f3168b;

    CallAndMessageHandler(CallAndMessageHandler callAndMessageHandler, Activity activity) {
        this.f3167a = callAndMessageHandler;
        this.f3168b = activity;
    }

    public void run() {
        CustomDialog.m2737a(this.f3168b, this.f3168b.getString(2131493472)).show();
    }
}
