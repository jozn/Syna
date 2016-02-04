package com.mmdt.syna.view.conversation.conversationpage.activities;

import android.content.Intent;
import android.os.Bundle;
import com.mmdt.syna.view.main.MainActivity;
import mobi.mmdt.ott.core.logic.message.MessageHandler;

public class MultipleRecipientMessageActivity extends MainConversationActivity {
    private String[] f2047x;

    public MultipleRecipientMessageActivity() {
        this.f2047x = new String[0];
    }

    protected boolean m3056b(String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        this.s.m4791a(str2);
        m3046j();
        MainActivity.f2520n = true;
        Intent intent = new Intent(this.t, MainActivity.class);
        intent.putExtra("com.mmdt.sipclient.view.MainActivity.JOB_VALUE", 2);
        intent.setFlags(67108864);
        startActivity(intent);
        overridePendingTransition(0, 0);
        return true;
    }

    public void m3057c(String str) {
        new Thread(new ae(this, str)).start();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.p = false;
        this.u.setIcon(2130837942);
        if (getIntent().hasExtra("multi_message_numbers")) {
            this.f2047x = getIntent().getStringArrayExtra("multi_message_numbers");
        }
        this.u.setTitle(Integer.toString(this.f2047x.length));
        this.s = new MessageHandler(this.t, false, this.f2047x);
        m3049m();
        m3050n();
    }
}
