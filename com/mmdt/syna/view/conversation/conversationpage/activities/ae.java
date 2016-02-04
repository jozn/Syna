package com.mmdt.syna.view.conversation.conversationpage.activities;

/* compiled from: MultipleRecipientMessageActivity */
class ae implements Runnable {
    final /* synthetic */ MultipleRecipientMessageActivity f2055a;
    private final /* synthetic */ String f2056b;

    ae(MultipleRecipientMessageActivity multipleRecipientMessageActivity, String str) {
        this.f2055a = multipleRecipientMessageActivity;
        this.f2056b = str;
    }

    public void run() {
        this.f2055a.m3056b("", this.f2056b);
    }
}
