package com.mmdt.syna.view.conversation.conversationpage.activities;

import com.mmdt.syna.view.conversation.conversationpage.activities.MainConversationActivity.C0106b;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;

/* compiled from: MainConversationActivity */
class ac implements Runnable {
    final /* synthetic */ C0106b f2052a;

    ac(C0106b c0106b) {
        this.f2052a = c0106b;
    }

    public void run() {
        try {
            ContactShow a = SynaContacts.m5002a(this.f2052a.f2046a.getApplicationContext(), this.f2052a.f2046a.f2034q, false);
            if (a != null) {
                int a2 = a.m5060a();
                if (a2 == 1) {
                    this.f2052a.f2046a.f2040w = false;
                } else if (a2 == 2) {
                    this.f2052a.f2046a.f2040w = true;
                    Thread.sleep(5000);
                } else {
                    this.f2052a.f2046a.f2040w = false;
                }
            } else {
                this.f2052a.f2046a.f2040w = false;
            }
            this.f2052a.f2046a.runOnUiThread(new ad(this, a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
