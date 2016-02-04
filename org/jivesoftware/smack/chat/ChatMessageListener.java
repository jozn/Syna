package org.jivesoftware.smack.chat;

import org.jivesoftware.smack.packet.Message;

/* renamed from: org.jivesoftware.smack.chat.b */
public interface ChatMessageListener {
    void processMessage(Chat chat, Message message);
}
