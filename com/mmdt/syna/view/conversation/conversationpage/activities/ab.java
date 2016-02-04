package com.mmdt.syna.view.conversation.conversationpage.activities;

import mobi.mmdt.ott.core.model.database.p065f.p066a.ChatStates;
import mobi.mmdt.ott.core.model.database.p065f.p066a.ChatStatesRow;
import mobi.mmdt.ott.core.model.p058a.AppSettings;

/* compiled from: MainConversationActivity */
class ab implements Runnable {
    final /* synthetic */ aa f2051a;

    ab(aa aaVar) {
        this.f2051a = aaVar;
    }

    public void run() {
        ChatStatesRow[] a = ChatStates.m5079a(this.f2051a.f2050a.f2045a.getApplicationContext(), this.f2051a.f2050a.f2045a.f2034q, new StringBuilder(String.valueOf(AppSettings.m4867a(this.f2051a.f2050a.f2045a.getApplicationContext()).m4905o())).append(AppSettings.m4867a(this.f2051a.f2050a.f2045a.getApplicationContext()).m4903m()).toString());
        if (a != null && a.length > 0) {
            if (a[0].m5080a().equals("ACTIVE")) {
                this.f2051a.f2050a.f2045a.f2028J.setVisibility(8);
            } else if (a[0].m5080a().equals("INACTIVE")) {
                this.f2051a.f2050a.f2045a.f2028J.setVisibility(8);
            } else if (a[0].m5080a().equals("PAUSED")) {
                this.f2051a.f2050a.f2045a.f2028J.setVisibility(8);
            } else if (a[0].m5080a().equals("COMPOSING")) {
                this.f2051a.f2050a.f2045a.f2028J.setVisibility(0);
            }
            if (this.f2051a.f2050a.f2045a.f2033p) {
                this.f2051a.f2050a.f2045a.f2029K.setText(new StringBuilder(String.valueOf(a[0].m5081b())).append("  ").append(this.f2051a.f2050a.m3054a(a[0].m5080a())).toString());
                this.f2051a.f2050a.f2045a.f2029K.setVisibility(8);
            } else if (this.f2051a.f2050a.f2045a.f2035r == null) {
                this.f2051a.f2050a.f2045a.f2029K.setVisibility(0);
                this.f2051a.f2050a.f2045a.f2029K.setText(new StringBuilder(String.valueOf(this.f2051a.f2050a.f2045a.f2034q)).append("  ").append(this.f2051a.f2050a.m3054a(a[0].m5080a())).toString());
            } else {
                this.f2051a.f2050a.f2045a.f2029K.setText(new StringBuilder(String.valueOf(this.f2051a.f2050a.f2045a.f2035r)).append("  ").append(this.f2051a.f2050a.m3054a(a[0].m5080a())).toString());
            }
        }
    }
}
