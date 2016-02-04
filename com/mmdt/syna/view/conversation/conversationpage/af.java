package com.mmdt.syna.view.conversation.conversationpage;

import android.view.inputmethod.InputMethodManager;

/* compiled from: TextBoxFragment */
class af implements Runnable {
    final /* synthetic */ TextBoxFragment f2114a;

    af(TextBoxFragment textBoxFragment) {
        this.f2114a = textBoxFragment;
    }

    public void run() {
        ((InputMethodManager) this.f2114a.m91h().getSystemService("input_method")).hideSoftInputFromWindow(this.f2114a.f2284d.getWindowToken(), 0);
    }
}
