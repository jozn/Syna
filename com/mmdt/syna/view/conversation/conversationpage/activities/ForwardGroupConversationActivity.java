package com.mmdt.syna.view.conversation.conversationpage.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import java.io.FileNotFoundException;
import javax.crypto.IllegalBlockSizeException;
import mobi.mmdt.ott.core.logic.message.MessageHandler;
import mobi.mmdt.ott.core.model.database.p063d.Groups;
import mobi.mmdt.ott.core.p051a.Statics;

public class ForwardGroupConversationActivity extends MainConversationActivity {
    private int f2043x;

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
            this.f2043x = getIntent().getIntExtra("user_privilage", 23);
        }
        if (getIntent().hasExtra("party_name") && getIntent().getStringExtra("party_name") != null) {
            this.r = getIntent().getStringExtra("party_name");
            try {
                m3030a(this.u, this.r);
            } catch (Exception e) {
            }
        }
        this.s = new MessageHandler(this.t, this.p, new String[]{this.q});
        if (this.f2043x != 23) {
            m3049m();
        }
        int intExtra = getIntent().getIntExtra("KEY_TO_FORWARD_MESSAGE_TYPE", 1);
        String stringExtra = getIntent().getStringExtra("KEY_TO_FORWARD_MESSAGE");
        String stringExtra2 = getIntent().getStringExtra("KEY_TO_FORWARD_MESSAGE_BIND_DATA");
        if (intExtra == 1) {
            m3038b(this.q, stringExtra);
        } else {
            try {
                this.s.m4792a(stringExtra, stringExtra2);
            } catch (IllegalBlockSizeException e2) {
                Statics.m4457a(this.t, getString(2131493679), 1);
                e2.printStackTrace();
            } catch (FileNotFoundException e3) {
                Statics.m4457a(this.t, getString(2131493680), 1);
                e3.printStackTrace();
            }
        }
        m3045i();
        m3050n();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131689484, menu);
        this.v = menu.findItem(2131427847);
        if (Groups.m5049a((Context) this, this.q)) {
            this.v.setChecked(true);
        } else {
            this.v.setChecked(false);
        }
        return super.onCreateOptionsMenu(menu);
    }
}
