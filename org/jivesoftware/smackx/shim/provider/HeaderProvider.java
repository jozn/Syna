package org.jivesoftware.smackx.shim.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.shim.packet.Header;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class HeaderProvider extends ExtensionElementProvider<Header> {
    public Header m6006a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        String str = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "name");
        xmlPullParser.next();
        if (xmlPullParser.getEventType() == 4) {
            str = xmlPullParser.getText();
        }
        while (xmlPullParser.getEventType() != 3) {
            xmlPullParser.next();
        }
        return new Header(attributeValue, str);
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m6006a(xmlPullParser, i);
    }
}
