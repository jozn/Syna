package mobi.mmdt.ott.asmackengine.chat;

import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.receipts.ReceiptReceivedListener;

class Chat$1 implements ReceiptReceivedListener {
    final /* synthetic */ Chat f3248a;

    Chat$1(Chat chat) {
        this.a = chat;
    }

    public void m4184a(String str, String str2, String str3, Stanza stanza) {
        if (this.a.f3260c != null) {
            this.a.f3260c.m4175a(str3, str);
        }
    }
}
