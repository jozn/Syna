package com.mmdt.syna.view.conversation.conversationpage.activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import mobi.mmdt.ott.core.logic.message.MessageHandler;
import mobi.mmdt.ott.core.p051a.CountryTools;

public class SingleConversationActivity extends MainConversationActivity {
    public void onBackPressed() {
        try {
            m3031a((MainConversationActivity) this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.p = false;
        this.u.setIcon(2130838017);
        Uri data = getIntent().getData();
        if (!getIntent().hasExtra("party") || getIntent().getStringExtra("party") == null) {
            this.q = CountryTools.m4416a().m4422b(this.t, data.getSchemeSpecificPart());
        } else {
            this.q = getIntent().getStringExtra("party");
            if (this.q.matches("[0-9]+")) {
                this.q = CountryTools.m4416a().m4422b(this.t, this.q);
                this.o = false;
            } else {
                this.o = true;
            }
        }
        this.s = new MessageHandler(this.t, this.p, new String[]{this.q});
        if (this.o) {
            m3030a(this.u, this.q);
            this.u.setSubtitle(null);
        } else {
            m3049m();
        }
        m3045i();
        m3050n();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        if (this.o) {
            menuInflater.inflate(2131689489, menu);
        } else {
            menuInflater.inflate(2131689488, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
