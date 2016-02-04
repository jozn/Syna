package com.mmdt.syna.view.conversation.conversationpage;

import android.widget.ImageButton;

/* compiled from: TextBoxFragment */
class al implements Runnable {
    final /* synthetic */ TextBoxFragment f2120a;
    private final /* synthetic */ boolean f2121b;

    al(TextBoxFragment textBoxFragment, boolean z) {
        this.f2120a = textBoxFragment;
        this.f2121b = z;
    }

    public void run() {
        this.f2120a.f2285e = (ImageButton) this.f2120a.f2283c.findViewById(2131427619);
        this.f2120a.f2285e.setVisibility(this.f2121b ? 0 : 8);
    }
}
