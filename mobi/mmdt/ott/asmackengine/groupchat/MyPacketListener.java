package mobi.mmdt.ott.asmackengine.groupchat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import mobi.mmdt.ott.asmackengine.chat.Chat.Chat;
import mobi.mmdt.ott.asmackengine.core.Core;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Body;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;

public class MyPacketListener implements MessageListener {
    private GroupListener f3294a;

    public MyPacketListener(GroupListener groupListener) {
        this.f3294a = groupListener;
    }

    public void processMessage(Message message) {
        try {
            if (message instanceof Message) {
                ExtensionElement extension = message.getExtension(ChatStateExtension.NAMESPACE);
                if (extension != null) {
                    ChatState.valueOf(((ChatStateExtension) extension).getElementName());
                    try {
                        ChatState valueOf = ChatState.valueOf(((ChatStateExtension) extension).getElementName());
                        if (message.getFrom().split("/").length == 2) {
                            String str = message.getFrom().split("/")[0];
                            String stringBuilder = new StringBuilder(String.valueOf(message.getFrom().split("/")[1])).append("@").append(Core.m4198a().m4209c()).toString();
                            if (valueOf == ChatState.active) {
                                this.f3294a.m4221a(Chat.ACTIVE, str, stringBuilder);
                            } else if (valueOf == ChatState.inactive) {
                                this.f3294a.m4221a(Chat.INACTIVE, str, stringBuilder);
                            } else if (valueOf == ChatState.composing) {
                                this.f3294a.m4221a(Chat.COMPOSING, str, stringBuilder);
                            } else if (valueOf == ChatState.gone) {
                                this.f3294a.m4221a(Chat.GONE, str, stringBuilder);
                            } else if (valueOf == ChatState.paused) {
                                this.f3294a.m4221a(Chat.PAUSED, str, stringBuilder);
                            }
                        }
                    } catch (Exception e) {
                    }
                } else if (this.f3294a != null && message.getFrom().split("/").length == 2 && message.getBody() != null) {
                    Collection<Body> bodies = message.getBodies();
                    Map hashMap = new HashMap();
                    for (Body body : bodies) {
                        hashMap.put(body.getLanguage(), body.getMessage());
                    }
                    this.f3294a.m4220a(message.getFrom().split("/")[0], new StringBuilder(String.valueOf(message.getFrom().split("/")[1])).append("@").append(Core.m4198a().m4209c()).toString(), message.getBody(), message.getStanzaId(), hashMap);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
