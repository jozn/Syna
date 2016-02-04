package org.jivesoftware.smackx.json.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.json.packet.AbstractJsonPacketExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class AbstractJsonExtensionProvider extends ExtensionElementProvider<AbstractJsonPacketExtension> {
    public abstract AbstractJsonPacketExtension m5888a(String str);

    public AbstractJsonPacketExtension m5889a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        String parseElementText = PacketParserUtils.parseElementText(xmlPullParser);
        xmlPullParser.next();
        return m5888a(parseElementText);
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5889a(xmlPullParser, i);
    }
}
