package com.mmdt.syna.view.conversation.conversationpage;

import com.mmdt.syna.view.tools.p037b.EmoticonManager;

/* compiled from: TextBoxFragment */
class ac implements Runnable {
    final /* synthetic */ TextBoxFragment f2016a;
    private final /* synthetic */ String f2017b;

    ac(TextBoxFragment textBoxFragment, String str) {
        this.f2016a = textBoxFragment;
        this.f2017b = str;
    }

    public void run() {
        int max = Math.max(this.f2016a.f2284d.getSelectionStart(), 0);
        int max2 = Math.max(this.f2016a.f2284d.getSelectionEnd(), 0);
        this.f2016a.f2284d.getText().replace(Math.min(max, max2), Math.max(max, max2), EmoticonManager.m4022a(this.f2016a.m91h()).m4024a(this.f2016a.m91h(), this.f2017b, false), 0, this.f2017b.length());
    }
}
