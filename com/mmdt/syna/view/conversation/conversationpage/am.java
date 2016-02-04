package com.mmdt.syna.view.conversation.conversationpage;

import android.widget.ImageButton;
import com.viewpagerindicator.R.R;

/* compiled from: TextBoxFragment */
class am implements Runnable {
    final /* synthetic */ TextBoxFragment f2122a;
    private final /* synthetic */ boolean f2123b;

    am(TextBoxFragment textBoxFragment, boolean z) {
        this.f2122a = textBoxFragment;
        this.f2123b = z;
    }

    public void run() {
        this.f2122a.f2287g = (ImageButton) this.f2122a.f2283c.findViewById(R.imageButton1);
        this.f2122a.f2287g.setVisibility(this.f2123b ? 0 : 8);
    }
}
