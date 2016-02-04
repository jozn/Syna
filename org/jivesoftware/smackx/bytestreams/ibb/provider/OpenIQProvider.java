package org.jivesoftware.smackx.bytestreams.ibb.provider;

import java.io.IOException;
import java.util.Locale;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager.StanzaType;
import org.jivesoftware.smackx.bytestreams.ibb.packet.Open;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OpenIQProvider extends IQProvider<Open> {
    public Open m5862a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue("", "sid");
        int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue("", "block-size"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "stanza");
        StanzaType valueOf = attributeValue2 == null ? StanzaType.IQ : StanzaType.valueOf(attributeValue2.toUpperCase(Locale.US));
        xmlPullParser.next();
        return new Open(attributeValue, parseInt, valueOf);
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5862a(xmlPullParser, i);
    }
}
