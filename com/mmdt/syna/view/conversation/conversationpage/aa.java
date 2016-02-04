package com.mmdt.syna.view.conversation.conversationpage;

import java.util.TimerTask;

/* compiled from: TextBoxFragment */
class aa extends TimerTask {
    final /* synthetic */ TextBoxFragment f2014a;

    aa(TextBoxFragment textBoxFragment) {
        this.f2014a = textBoxFragment;
    }

    public void run() {
        try {
            this.f2014a.f2290a.m91h().runOnUiThread(new ab(this));
        } catch (Exception e) {
        }
    }
}
