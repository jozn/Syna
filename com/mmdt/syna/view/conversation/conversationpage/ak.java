package com.mmdt.syna.view.conversation.conversationpage;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: TextBoxFragment */
class ak implements OnClickListener {
    final /* synthetic */ TextBoxFragment f2119a;

    ak(TextBoxFragment textBoxFragment) {
        this.f2119a = textBoxFragment;
    }

    public void onClick(View view) {
        if (this.f2119a.f2289i) {
            this.f2119a.f2289i = false;
            this.f2119a.f2286f.setImageResource(2130837949);
            this.f2119a.f2282b.d_();
            return;
        }
        this.f2119a.f2289i = true;
        this.f2119a.f2286f.setImageResource(2130837946);
        this.f2119a.f2282b.c_();
    }
}
