package mobi.mmdt.ott.asmackengine.groupchat;

import android.util.Log;
import mobi.mmdt.ott.asmackengine.core.Core;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

class Group$1 implements InvitationListener {
    final /* synthetic */ Group f3293a;

    Group$1(Group group) {
        this.a = group;
    }

    public void m4234a(XMPPConnection xMPPConnection, MultiUserChat multiUserChat, String str, String str2, String str3, Message message) {
        Log.d("AsmackEngine", "invitationReceived");
        if (this.a.f3296b == null) {
            return;
        }
        if (str.contains(new StringBuilder(String.valueOf(Core.m4198a().m4208b())).append("@").append(Core.m4198a().m4209c()).toString())) {
            Log.e("Group", "Can not invite him self.");
        } else {
            this.a.f3296b.m4219a(multiUserChat.getRoom(), str, str2);
        }
    }
}
