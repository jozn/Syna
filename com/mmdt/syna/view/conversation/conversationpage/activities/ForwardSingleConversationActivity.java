package com.mmdt.syna.view.conversation.conversationpage.activities;

import android.os.Bundle;
import android.view.Menu;
import java.io.FileNotFoundException;
import javax.crypto.IllegalBlockSizeException;
import mobi.mmdt.ott.core.logic.message.MessageHandler;
import mobi.mmdt.ott.core.p051a.Statics;

public class ForwardSingleConversationActivity extends MainConversationActivity {
    public void onBackPressed() {
        m3031a((MainConversationActivity) this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.p = false;
        this.s = new MessageHandler(this.t, this.p, new String[]{this.q});
        int intExtra = getIntent().getIntExtra("KEY_TO_FORWARD_MESSAGE_TYPE", 1);
        String stringExtra = getIntent().getStringExtra("KEY_TO_FORWARD_MESSAGE");
        String stringExtra2 = getIntent().getStringExtra("KEY_TO_FORWARD_MESSAGE_BIND_DATA");
        if (intExtra == 1) {
            m3038b(this.q, stringExtra);
        } else {
            try {
                this.s.m4792a(stringExtra, stringExtra2);
            } catch (IllegalBlockSizeException e) {
                Statics.m4457a(this.t, getString(2131493679), 1);
                e.printStackTrace();
            } catch (FileNotFoundException e2) {
                Statics.m4457a(this.t, getString(2131493680), 1);
                e2.printStackTrace();
            }
        }
        m3045i();
        m3050n();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131689485, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
