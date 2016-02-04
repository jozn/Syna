package org.jivesoftware.smackx.chatstates.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ChatStateExtension implements ExtensionElement {
    public static final String NAMESPACE = "http://jabber.org/protocol/chatstates";
    private final ChatState state;

    public static class Provider extends ExtensionElementProvider<ChatStateExtension> {
        public ChatStateExtension m5872a(XmlPullParser xmlPullParser, int i) {
            ChatState valueOf;
            try {
                valueOf = ChatState.valueOf(xmlPullParser.getName());
            } catch (Exception e) {
                valueOf = ChatState.active;
            }
            return new ChatStateExtension(valueOf);
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5872a(xmlPullParser, i);
        }
    }

    public ChatStateExtension(ChatState chatState) {
        this.state = chatState;
    }

    public ChatState getChatState() {
        return this.state;
    }

    public String getElementName() {
        return this.state.name();
    }

    public String getNamespace() {
        return NAMESPACE;
    }

    public XmlStringBuilder toXML() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement) this);
        xmlStringBuilder.closeEmptyElement();
        return xmlStringBuilder;
    }
}
