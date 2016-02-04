package com.mmdt.syna.view.conversation.conversationpage.activities;

import mobi.mmdt.ott.asmackengine.chat.Chat.Chat;
import mobi.mmdt.ott.core.logic.message.MessageService;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.activities.p */
class MainConversationActivity implements Runnable {
    final /* synthetic */ MainConversationActivity f2078a;
    private final /* synthetic */ Chat f2079b;

    MainConversationActivity(MainConversationActivity mainConversationActivity, Chat chat) {
        this.f2078a = mainConversationActivity;
        this.f2079b = chat;
    }

    public void run() {
        if (this.f2078a.f2033p) {
            MessageService.m4799a(this.f2078a.f2037t).m4814a(this.f2078a.f2034q, this.f2079b, true);
        } else {
            MessageService.m4799a(this.f2078a.f2037t).m4814a(this.f2078a.f2034q, this.f2079b, false);
        }
    }
}
 }
}
