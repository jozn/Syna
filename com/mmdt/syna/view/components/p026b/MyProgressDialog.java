package com.mmdt.syna.view.components.p026b;

/* renamed from: com.mmdt.syna.view.components.b.e */
class MyProgressDialog implements Runnable {
    final /* synthetic */ MyProgressDialog f1833a;

    MyProgressDialog(MyProgressDialog myProgressDialog) {
        this.f1833a = myProgressDialog;
    }

    public void run() {
        try {
            this.f1833a.f1823a.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
