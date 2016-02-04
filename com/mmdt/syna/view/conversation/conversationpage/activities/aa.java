package com.mmdt.syna.view.conversation.conversationpage.activities;

import com.mmdt.syna.view.conversation.conversationpage.activities.MainConversationActivity.C0105a;

/* compiled from: MainConversationActivity */
class aa implements Runnable {
    final /* synthetic */ C0105a f2050a;

    aa(C0105a c0105a) {
        this.f2050a = c0105a;
    }

    public void run() {
        try {
            this.f2050a.f2045a.runOnUiThread(new ab(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
