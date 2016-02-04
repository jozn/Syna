package com.mmdt.syna.view.conversation.conversationpage.activities;

import mobi.mmdt.ott.core.model.database.ContactShow;

/* compiled from: MainConversationActivity */
class ad implements Runnable {
    final /* synthetic */ ac f2053a;
    private final /* synthetic */ ContactShow f2054b;

    ad(ac acVar, ContactShow contactShow) {
        this.f2053a = acVar;
        this.f2054b = contactShow;
    }

    public void run() {
        if (this.f2054b != null) {
            int a = this.f2054b.m5060a();
            if (a == 1) {
                this.f2053a.f2052a.f2046a.m2992b(this.f2053a.f2052a.f2046a.f2038u, this.f2053a.f2052a.f2046a.getString(2131493634));
                return;
            } else if (a != 2) {
                this.f2053a.f2052a.f2046a.f2038u.setSubtitle(null);
                return;
            } else if (this.f2053a.f2052a.f2046a.f2040w) {
                this.f2053a.f2052a.f2046a.m2992b(this.f2053a.f2052a.f2046a.f2038u, this.f2053a.f2052a.f2046a.getString(2131493635));
                this.f2053a.f2052a.f2046a.f2040w = false;
                return;
            } else {
                return;
            }
        }
        this.f2053a.f2052a.f2046a.f2038u.setSubtitle(null);
    }
}
