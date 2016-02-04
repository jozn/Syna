package com.mmdt.syna.view.conversation.conversationpage;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: TextBoxFragment */
class aj implements OnClickListener {
    final /* synthetic */ TextBoxFragment f2118a;

    aj(TextBoxFragment textBoxFragment) {
        this.f2118a = textBoxFragment;
    }

    public void onClick(View view) {
        if (this.f2118a.f2288h) {
            this.f2118a.f2288h = false;
            this.f2118a.f2285e.setImageResource(2130837896);
            this.f2118a.f2282b.m2975c();
            return;
        }
        this.f2118a.f2288h = true;
        this.f2118a.f2285e.setImageResource(2130837902);
        this.f2118a.f2282b.m2977d();
    }
}
