package org.jivesoftware.smackx.hoxt.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp;
import org.jivesoftware.smackx.hoxt.packet.HttpOverXmppResp;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class HttpOverXmppRespProvider extends AbstractHttpOverXmppProvider<HttpOverXmppResp> {
    private static final String ATTRIBUTE_STATUS_CODE = "statusCode";
    private static final String ATTRIBUTE_STATUS_MESSAGE = "statusMessage";

    public HttpOverXmppResp parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        String attributeValue = xmlPullParser.getAttributeValue("", "version");
        String attributeValue2 = xmlPullParser.getAttributeValue("", ATTRIBUTE_STATUS_MESSAGE);
        int parseInt = Integer.parseInt(xmlPullParser.getAttributeValue("", ATTRIBUTE_STATUS_CODE));
        AbstractHttpOverXmpp httpOverXmppResp = new HttpOverXmppResp();
        httpOverXmppResp.setVersion(attributeValue);
        httpOverXmppResp.setStatusMessage(attributeValue2);
        httpOverXmppResp.setStatusCode(parseInt);
        parseHeadersAndData(xmlPullParser, HttpOverXmppResp.ELEMENT, httpOverXmppResp);
        return httpOverXmppResp;
    }
}
