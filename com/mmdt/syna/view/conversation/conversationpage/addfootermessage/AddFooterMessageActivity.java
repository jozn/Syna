package com.mmdt.syna.view.conversation.conversationpage.addfootermessage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import mobi.mmdt.p041a.ThumbnailMaker;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.addfootermessage.b */
class AddFooterMessageActivity implements Runnable {
    final /* synthetic */ AddFooterMessageActivity f2106a;
    private Bitmap f2107b;

    AddFooterMessageActivity(AddFooterMessageActivity addFooterMessageActivity) {
        this.f2106a = addFooterMessageActivity;
        this.f2107b = null;
    }

    public void run() {
        if (this.f2106a.f2102u.equalsIgnoreCase("photo_message")) {
            this.f2107b = BitmapFactory.decodeFile(this.f2106a.f2101t);
        } else {
            this.f2107b = ThumbnailMaker.m4109a(this.f2106a.f2101t);
        }
        this.f2106a.runOnUiThread(new AddFooterMessageActivity(this));
    }
}
