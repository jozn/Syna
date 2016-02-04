package org.jivesoftware.smackx.bytestreams.socks5.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.Activate;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.Mode;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHostUsed;
import org.jivesoftware.smackx.search.UserSearch;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class BytestreamsProvider extends IQProvider<Bytestream> {
    public Bytestream m5866a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        Bytestream bytestream = new Bytestream();
        String attributeValue = xmlPullParser.getAttributeValue("", "sid");
        String attributeValue2 = xmlPullParser.getAttributeValue("", "mode");
        String str = null;
        String str2 = null;
        Object obj = null;
        String str3 = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            String name = xmlPullParser.getName();
            if (next == 2) {
                if (name.equals(StreamHost.ELEMENTNAME)) {
                    str2 = xmlPullParser.getAttributeValue("", "jid");
                    str = xmlPullParser.getAttributeValue("", "host");
                    str3 = xmlPullParser.getAttributeValue("", "port");
                } else if (name.equals(StreamHostUsed.ELEMENTNAME)) {
                    bytestream.setUsedHost(xmlPullParser.getAttributeValue("", "jid"));
                } else if (name.equals(Activate.ELEMENTNAME)) {
                    bytestream.setToActivate(xmlPullParser.getAttributeValue("", "jid"));
                }
            } else if (next == 3) {
                if (name.equals("streamhost")) {
                    if (str3 == null) {
                        bytestream.addStreamHost(str2, str);
                    } else {
                        bytestream.addStreamHost(str2, str, Integer.parseInt(str3));
                    }
                    str3 = null;
                    str = null;
                    str2 = null;
                } else if (name.equals(UserSearch.ELEMENT)) {
                    obj = 1;
                }
            }
        }
        if (attributeValue2 == null) {
            bytestream.setMode(Mode.tcp);
        } else {
            bytestream.setMode(Mode.fromName(attributeValue2));
        }
        bytestream.setSessionID(attributeValue);
        return bytestream;
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5866a(xmlPullParser, i);
    }
}
