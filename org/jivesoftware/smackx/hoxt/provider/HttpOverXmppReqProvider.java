package org.jivesoftware.smackx.hoxt.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Ibb;
import org.jivesoftware.smackx.hoxt.packet.HttpMethod;
import org.jivesoftware.smackx.hoxt.packet.HttpOverXmppReq;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class HttpOverXmppReqProvider extends AbstractHttpOverXmppProvider<HttpOverXmppReq> {
    private static final String ATTRIBUTE_MAX_CHUNK_SIZE = "maxChunkSize";
    private static final String ATTRIBUTE_METHOD = "method";
    private static final String ATTRIBUTE_RESOURCE = "resource";

    public HttpOverXmppReq parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        String attributeValue = xmlPullParser.getAttributeValue("", ATTRIBUTE_METHOD);
        String attributeValue2 = xmlPullParser.getAttributeValue("", ATTRIBUTE_RESOURCE);
        String attributeValue3 = xmlPullParser.getAttributeValue("", "version");
        String attributeValue4 = xmlPullParser.getAttributeValue("", ATTRIBUTE_MAX_CHUNK_SIZE);
        AbstractHttpOverXmpp httpOverXmppReq = new HttpOverXmppReq(HttpMethod.valueOf(attributeValue), attributeValue2);
        httpOverXmppReq.setVersion(attributeValue3);
        Boolean valueOf = Boolean.valueOf(true);
        Boolean valueOf2 = Boolean.valueOf(true);
        Boolean valueOf3 = Boolean.valueOf(true);
        String attributeValue5 = xmlPullParser.getAttributeValue("", "sipub");
        String attributeValue6 = xmlPullParser.getAttributeValue("", Ibb.ELEMENT);
        String attributeValue7 = xmlPullParser.getAttributeValue("", "jingle");
        if (attributeValue5 != null) {
            valueOf = Boolean.valueOf(attributeValue5);
        }
        if (attributeValue6 != null) {
            valueOf3 = Boolean.valueOf(attributeValue6);
        }
        if (attributeValue7 != null) {
            valueOf2 = Boolean.valueOf(attributeValue7);
        }
        httpOverXmppReq.setIbb(valueOf3.booleanValue());
        httpOverXmppReq.setSipub(valueOf.booleanValue());
        httpOverXmppReq.setJingle(valueOf2.booleanValue());
        if (attributeValue4 != null) {
            httpOverXmppReq.setMaxChunkSize(Integer.parseInt(attributeValue4));
        }
        parseHeadersAndData(xmlPullParser, HttpOverXmppReq.ELEMENT, httpOverXmppReq);
        return httpOverXmppReq;
    }
}
