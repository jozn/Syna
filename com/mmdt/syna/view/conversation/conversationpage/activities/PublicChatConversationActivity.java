package com.mmdt.syna.view.conversation.conversationpage.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import mobi.mmdt.ott.core.logic.message.MessageHandler;
import mobi.mmdt.ott.core.model.database.p063d.Groups;

public class PublicChatConversationActivity extends MainConversationActivity {
    private int f2048x;

    public void onBackPressed() {
        m3031a((MainConversationActivity) this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.p = true;
        this.u.setIcon(2130838020);
        if (getIntent().hasExtra("party") && getIntent().getStringExtra("party") != null) {
            this.q = getIntent().getStringExtra("party");
        }
        if (getIntent().hasExtra("user_privilage")) {
            this.f2048x = getIntent().getIntExtra("user_privilage", 23);
        }
        if (getIntent().hasExtra("party_name") && getIntent().getStringExtra("party_name") != null) {
            this.r = getIntent().getStringExtra("party_name");
            try {
                m3030a(this.u, this.r);
            } catch (Exception e) {
            }
        }
        this.s = new MessageHandler(this.t, this.p, new String[]{this.q});
        if (this.f2048x != 23) {
            m3049m();
        }
        m3045i();
        m3050n();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131689487, menu);
        this.v = menu.findItem(2131427847);
        if (Groups.m5049a((Context) this, this.q)) {
            this.v.setChecked(true);
        } else {
            this.v.setChecked(false);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
