package org.jivesoftware.smackx.hoxt.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Base64;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.ChunkedBase64;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Data;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Ibb;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Text;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Xml;
import org.jivesoftware.smackx.shim.packet.HeadersExtension;
import org.jivesoftware.smackx.shim.provider.HeadersProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class AbstractHttpOverXmppProvider<H extends AbstractHttpOverXmpp> extends IQProvider<H> {
    private static final String ATTRIBUTE_SID = "sid";
    private static final String ATTRIBUTE_STREAM_ID = "streamId";
    static final String ATTRIBUTE_VERSION = "version";
    private static final String ELEMENT_BASE_64 = "base64";
    private static final String ELEMENT_CHUNKED_BASE_64 = "chunkedBase64";
    private static final String ELEMENT_DATA = "data";
    static final String ELEMENT_IBB = "ibb";
    static final String ELEMENT_JINGLE = "jingle";
    static final String ELEMENT_SIPUB = "sipub";
    private static final String ELEMENT_TEXT = "text";
    private static final String ELEMENT_XML = "xml";

    private void appendXmlAttributes(XmlPullParser xmlPullParser, StringBuilder stringBuilder) {
        int attributeCount = xmlPullParser.getAttributeCount();
        if (attributeCount > 0) {
            for (int i = 0; i < attributeCount; i++) {
                stringBuilder.append(' ');
                stringBuilder.append(xmlPullParser.getAttributeName(i));
                stringBuilder.append("=\"");
                stringBuilder.append(StringUtils.escapeForXML(xmlPullParser.getAttributeValue(i)));
                stringBuilder.append('\"');
            }
        }
    }

    private Base64 parseBase64(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String str = null;
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 3) {
                if (xmlPullParser.getName().equals(ELEMENT_BASE_64)) {
                    obj = 1;
                } else {
                    throw new IllegalArgumentException("unexpected end tag of: " + xmlPullParser.getName());
                }
            } else if (next == 4) {
                str = xmlPullParser.getText();
            } else {
                throw new IllegalArgumentException("unexpected eventType: " + next);
            }
        }
        return new Base64(str);
    }

    private ChunkedBase64 parseChunkedBase64(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ChunkedBase64 chunkedBase64 = new ChunkedBase64(xmlPullParser.getAttributeValue("", ATTRIBUTE_STREAM_ID));
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next != 3) {
                throw new IllegalArgumentException("unexpected event type: " + next);
            } else if (xmlPullParser.getName().equals(ELEMENT_CHUNKED_BASE_64)) {
                obj = 1;
            } else {
                throw new IllegalArgumentException("unexpected end tag: " + xmlPullParser.getName());
            }
        }
        return chunkedBase64;
    }

    private Data parseData(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        NamedElement namedElement = null;
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals(ELEMENT_TEXT)) {
                    namedElement = parseText(xmlPullParser);
                } else if (xmlPullParser.getName().equals(ELEMENT_BASE_64)) {
                    namedElement = parseBase64(xmlPullParser);
                } else if (xmlPullParser.getName().equals(ELEMENT_CHUNKED_BASE_64)) {
                    namedElement = parseChunkedBase64(xmlPullParser);
                } else if (xmlPullParser.getName().equals(ELEMENT_XML)) {
                    namedElement = parseXml(xmlPullParser);
                } else if (xmlPullParser.getName().equals(ELEMENT_IBB)) {
                    namedElement = parseIbb(xmlPullParser);
                } else if (xmlPullParser.getName().equals(ELEMENT_SIPUB)) {
                    throw new UnsupportedOperationException("sipub is not supported yet");
                } else if (xmlPullParser.getName().equals(ELEMENT_JINGLE)) {
                    throw new UnsupportedOperationException("jingle is not supported yet");
                } else {
                    throw new IllegalArgumentException("unsupported child tag: " + xmlPullParser.getName());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(ELEMENT_DATA)) {
                obj = 1;
            }
        }
        return new Data(namedElement);
    }

    private Ibb parseIbb(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Ibb ibb = new Ibb(xmlPullParser.getAttributeValue("", ATTRIBUTE_SID));
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next != 3) {
                throw new IllegalArgumentException("unexpected event type: " + next);
            } else if (xmlPullParser.getName().equals(ELEMENT_IBB)) {
                obj = 1;
            } else {
                throw new IllegalArgumentException("unexpected end tag: " + xmlPullParser.getName());
            }
        }
        return ibb;
    }

    private Text parseText(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String str = null;
        Object obj = null;
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 3) {
                if (xmlPullParser.getName().equals(ELEMENT_TEXT)) {
                    obj = 1;
                } else {
                    throw new IllegalArgumentException("unexpected end tag of: " + xmlPullParser.getName());
                }
            } else if (next == 4) {
                str = xmlPullParser.getText();
            } else {
                throw new IllegalArgumentException("unexpected eventType: " + next);
            }
        }
        return new Text(str);
    }

    private Xml parseXml(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        Object obj2 = null;
        while (obj2 == null) {
            int next = xmlPullParser.next();
            if (next == 3 && xmlPullParser.getName().equals(ELEMENT_XML)) {
                obj2 = 1;
            } else if (next == 2) {
                if (obj == null) {
                    stringBuilder.append('>');
                }
                stringBuilder.append('<');
                stringBuilder.append(xmlPullParser.getName());
                appendXmlAttributes(xmlPullParser, stringBuilder);
                obj = null;
            } else if (next == 3) {
                if (obj != null) {
                    stringBuilder.append("</");
                    stringBuilder.append(xmlPullParser.getName());
                    stringBuilder.append('>');
                } else {
                    stringBuilder.append("/>");
                    obj = 1;
                }
            } else if (next == 4) {
                if (obj == null) {
                    stringBuilder.append('>');
                    obj = 1;
                }
                stringBuilder.append(StringUtils.escapeForXML(xmlPullParser.getText()));
            } else {
                throw new IllegalArgumentException("unexpected eventType: " + next);
            }
        }
        return new Xml(stringBuilder.toString());
    }

    protected void parseHeadersAndData(XmlPullParser xmlPullParser, String str, AbstractHttpOverXmpp abstractHttpOverXmpp) throws XmlPullParserException, IOException, SmackException {
        Object obj;
        for (Object obj2 = null; obj2 == null; obj2 = obj) {
            int next = xmlPullParser.next();
            if (next != 2) {
                obj = (next == 3 && xmlPullParser.getName().equals(str)) ? 1 : obj2;
            } else if (xmlPullParser.getName().equals(HeadersExtension.ELEMENT)) {
                abstractHttpOverXmpp.setHeaders((HeadersExtension) HeadersProvider.INSTANCE.parse(xmlPullParser));
                obj = obj2;
            } else if (xmlPullParser.getName().endsWith(ELEMENT_DATA)) {
                abstractHttpOverXmpp.setData(parseData(xmlPullParser));
                obj = obj2;
            } else {
                throw new IllegalArgumentException("unexpected tag:" + xmlPullParser.getName() + "'");
            }
        }
    }
}
