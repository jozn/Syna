package mobi.mmdt.ott.asmackengine.chat;

import android.util.Log;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManagerListener;

class Chat$2 implements ChatManagerListener {
    final /* synthetic */ Chat f3249a;

    Chat$2(Chat chat) {
        this.a = chat;
    }

    public void chatCreated(Chat chat, boolean z) {
        Log.d("AsmackEngine", "chatCreated " + z);
        this.a.f3261d.put(chat.getParticipant(), chat);
        chat.addMessageListener(this.a.f3264g);
    }
}
