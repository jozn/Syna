package mobi.mmdt.ott.asmackengine.chat;

import android.util.Log;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Body;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

class Chat$3 implements ChatMessageListener {
    private static /* synthetic */ int[] f3250b;
    final /* synthetic */ Chat f3251a;

    Chat$3(Chat chat) {
        this.a = chat;
    }

    static /* synthetic */ int[] m4185a() {
        int[] iArr = b;
        if (iArr == null) {
            iArr = new int[Type.values().length];
            try {
                iArr[Type.chat.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Type.error.ordinal()] = 5;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Type.groupchat.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Type.headline.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Type.normal.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            b = iArr;
        }
        return iArr;
    }

    public void processMessage(Chat chat, Message message) {
        try {
            switch (m4185a()[message.getType().ordinal()]) {
                case VideoSize.CIF /*1*/:
                    Log.d("AsmackEngine", "onReceiveMessagenormal");
                    return;
                case VideoSize.HVGA /*2*/:
                    ExtensionElement extension = message.getExtension(ChatStateExtension.NAMESPACE);
                    if (extension != null) {
                        try {
                            ChatState valueOf = ChatState.valueOf(extension.getElementName());
                            if (valueOf == ChatState.active) {
                                this.a.f3260c.m4177a(Chat.Chat.ACTIVE, message.getFrom(), message.getFrom());
                            } else if (valueOf == ChatState.inactive) {
                                this.a.f3260c.m4177a(Chat.Chat.INACTIVE, message.getFrom(), message.getFrom());
                            } else if (valueOf == ChatState.composing) {
                                this.a.f3260c.m4177a(Chat.Chat.COMPOSING, message.getFrom(), message.getFrom());
                            } else if (valueOf == ChatState.gone) {
                                this.a.f3260c.m4177a(Chat.Chat.GONE, message.getFrom(), message.getFrom());
                            } else if (valueOf == ChatState.paused) {
                                this.a.f3260c.m4177a(Chat.Chat.PAUSED, message.getFrom(), message.getFrom());
                            }
                        } catch (Exception e) {
                        }
                    }
                    if (this.a.f3260c != null && message.getBody() != null && message.getStanzaId() != null) {
                        Collection<Body> bodies = message.getBodies();
                        Map hashMap = new HashMap();
                        for (Body body : bodies) {
                            hashMap.put(body.getLanguage(), body.getMessage());
                        }
                        this.a.f3260c.m4176a(message.getFrom(), message.getBody(), hashMap, message.getStanzaId());
                        return;
                    }
                    return;
                case Version.API03_CUPCAKE_15 /*3*/:
                    Log.d("AsmackEngine", "onReceiveMessagegroupchat");
                    return;
                case Version.API04_DONUT_16 /*4*/:
                    Log.d("AsmackEngine", "onReceiveMessageheadline");
                    return;
                case Version.API05_ECLAIR_20 /*5*/:
                    Log.d("AsmackEngine", "onReceiveMessageerror");
                    return;
                default:
                    Log.d("AsmackEngine", "onReceiveMessagedefault");
                    return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        e2.printStackTrace();
    }
}
