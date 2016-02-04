package org.jivesoftware.smackx.disco.provider;

import java.io.IOException;
import org.apache.http.protocol.HTTP;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Xml;
import org.jivesoftware.smackx.search.UserSearch;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DiscoverInfoProvider extends IQProvider<DiscoverInfo> {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !DiscoverInfoProvider.class.desiredAssertionStatus();
    }

    public DiscoverInfo parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        Stanza discoverInfo = new DiscoverInfo();
        Object obj = null;
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        discoverInfo.setNode(xmlPullParser.getAttributeValue("", "node"));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(HTTP.IDENTITY_CODING)) {
                    str = xmlPullParser.getAttributeValue("", "category");
                    str2 = xmlPullParser.getAttributeValue("", "name");
                    str3 = xmlPullParser.getAttributeValue("", "type");
                    str5 = xmlPullParser.getAttributeValue(xmlPullParser.getNamespace(Xml.ELEMENT), "lang");
                } else if (xmlPullParser.getName().equals("feature")) {
                    str4 = xmlPullParser.getAttributeValue("", "var");
                } else {
                    PacketParserUtils.addExtensionElement(discoverInfo, xmlPullParser);
                }
            } else if (next != 3) {
                continue;
            } else {
                if (xmlPullParser.getName().equals(HTTP.IDENTITY_CODING)) {
                    discoverInfo.addIdentity(new Identity(str, str3, str2, str5));
                }
                if (xmlPullParser.getName().equals("feature")) {
                    boolean addFeature = discoverInfo.addFeature(str4);
                    if (!($assertionsDisabled || addFeature)) {
                        throw new AssertionError();
                    }
                }
                if (xmlPullParser.getName().equals(UserSearch.ELEMENT)) {
                    obj = 1;
                }
            }
        }
        return discoverInfo;
    }
}
