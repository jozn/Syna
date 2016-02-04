package org.jivesoftware.smackx.hoxt.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.hoxt.packet.Base64BinaryChunk;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class Base64BinaryChunkProvider extends ExtensionElementProvider<Base64BinaryChunk> {
    public Base64BinaryChunk m5892a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        Object obj = null;
        String attributeValue = xmlPullParser.getAttributeValue("", Base64BinaryChunk.ATTRIBUTE_STREAM_ID);
        String attributeValue2 = xmlPullParser.getAttributeValue("", Base64BinaryChunk.ATTRIBUTE_NR);
        String attributeValue3 = xmlPullParser.getAttributeValue("", Base64BinaryChunk.ATTRIBUTE_LAST);
        int parseInt = Integer.parseInt(attributeValue2);
        boolean parseBoolean = attributeValue3 != null ? Boolean.parseBoolean(attributeValue3) : false;
        attributeValue3 = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 3) {
                if (xmlPullParser.getName().equals(Base64BinaryChunk.ELEMENT_CHUNK)) {
                    obj = 1;
                } else {
                    throw new IllegalArgumentException("unexpected end tag of: " + xmlPullParser.getName());
                }
            } else if (next == 4) {
                attributeValue3 = xmlPullParser.getText();
            } else {
                throw new IllegalArgumentException("unexpected eventType: " + next);
            }
        }
        return new Base64BinaryChunk(attributeValue3, attributeValue, parseInt, parseBoolean);
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5892a(xmlPullParser, i);
    }
}
