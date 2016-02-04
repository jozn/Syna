package org.jivesoftware.smackx.caps.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.roster.packet.RosterVer;
import org.jivesoftware.smackx.caps.packet.CapsExtension;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class CapsExtensionProvider extends ExtensionElementProvider<CapsExtension> {
    public CapsExtension m5869a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        if (xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equalsIgnoreCase(CapsExtension.ELEMENT)) {
            String attributeValue = xmlPullParser.getAttributeValue(null, "hash");
            String attributeValue2 = xmlPullParser.getAttributeValue(null, RosterVer.ELEMENT);
            String attributeValue3 = xmlPullParser.getAttributeValue(null, "node");
            xmlPullParser.next();
            if (xmlPullParser.getEventType() != 3 || !xmlPullParser.getName().equalsIgnoreCase(CapsExtension.ELEMENT)) {
                throw new SmackException("Malformed nested Caps element");
            } else if (attributeValue != null && attributeValue2 != null && attributeValue3 != null) {
                return new CapsExtension(attributeValue3, attributeValue2, attributeValue);
            } else {
                throw new SmackException("Caps elment with missing attributes. Attributes: hash=" + attributeValue + " version=" + attributeValue2 + " node=" + attributeValue3);
            }
        }
        throw new SmackException("Malformed Caps element");
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5869a(xmlPullParser, i);
    }
}
