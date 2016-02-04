package org.jivesoftware.smackx.chatstates;

import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;

public interface ChatStateListener extends ChatMessageListener {
    void m5871a(Chat chat, ChatState chatState);
}
