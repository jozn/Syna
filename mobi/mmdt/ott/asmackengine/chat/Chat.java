package mobi.mmdt.ott.asmackengine.chat;

import java.util.HashMap;
import java.util.Map;
import mobi.mmdt.ott.asmackengine.core.Core;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.jivesoftware.smackx.chatstates.ChatStateManager;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager.AutoReceiptMode;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import org.jivesoftware.smackx.receipts.ReceiptReceivedListener;

/* renamed from: mobi.mmdt.ott.asmackengine.chat.a */
public class Chat {
    private static Chat f3258a;
    private ChatManager f3259b;
    private ChatListener f3260c;
    private HashMap<String, org.jivesoftware.smack.chat.Chat> f3261d;
    private ReceiptReceivedListener f3262e;
    private ChatManagerListener f3263f;
    private ChatMessageListener f3264g;

    /* renamed from: mobi.mmdt.ott.asmackengine.chat.a.a */
    public enum Chat {
        ACTIVE,
        PAUSED,
        COMPOSING,
        GONE,
        INACTIVE
    }

    private Chat() {
        this.f3261d = new HashMap();
        this.f3262e = new Chat$1(this);
        this.f3263f = new Chat$2(this);
        this.f3264g = new Chat$3(this);
    }

    public static Chat m4186a() {
        if (f3258a == null) {
            f3258a = new Chat();
        }
        return f3258a;
    }

    private org.jivesoftware.smack.chat.Chat m4188a(String str) {
        if (this.f3261d.containsKey(str)) {
            return (org.jivesoftware.smack.chat.Chat) this.f3261d.get(str);
        }
        org.jivesoftware.smack.chat.Chat createChat = this.f3259b.createChat(str, null);
        this.f3261d.put(str, createChat);
        return createChat;
    }

    public String m4191a(String str, String str2, String str3, Map<String, String> map) throws NotConnectedException {
        if (Core.m4198a().m4207a(true)) {
            Message message = new Message();
            message.setBody(str2);
            if (str3 != null) {
                message.setStanzaId(str3);
            }
            for (String str4 : map.keySet()) {
                String str5 = (String) map.get(str4);
                if (str5 != null) {
                    message.addBody(str4, str5);
                }
            }
            DeliveryReceiptRequest.addTo(message);
            m4188a(str).sendMessage(message);
            return message.getStanzaId();
        }
        throw new NotConnectedException();
    }

    public void m4192a(ChatListener chatListener) {
        this.f3260c = chatListener;
    }

    public void m4193a(ChatState chatState, String str) throws NotConnectedException {
        if (Core.m4198a().m4207a(false)) {
            org.jivesoftware.smack.chat.Chat a = m4188a(str);
            if (a != null) {
                ChatStateManager.getInstance(Core.m4198a().m4210d()).setCurrentState(chatState, a);
                return;
            }
            return;
        }
        throw new NotConnectedException();
    }

    public void m4194b() {
        this.f3261d.clear();
    }

    public void m4195c() {
        DeliveryReceiptManager.getInstanceFor(Core.m4198a().m4210d()).setAutoReceiptMode(AutoReceiptMode.always);
        DeliveryReceiptManager.getInstanceFor(Core.m4198a().m4210d()).addReceiptReceivedListener(this.f3262e);
        this.f3259b = ChatManager.getInstanceFor(Core.m4198a().m4210d());
        this.f3259b.addChatListener(this.f3263f);
        this.f3261d.clear();
    }
}
