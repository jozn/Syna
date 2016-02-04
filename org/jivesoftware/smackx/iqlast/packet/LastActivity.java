package org.jivesoftware.smackx.iqlast.packet;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.provider.IQProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class LastActivity extends IQ {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:last";
    public long lastActivity;
    public String message;

    public static class Provider extends IQProvider<LastActivity> {
        public LastActivity m5894a(XmlPullParser xmlPullParser, int i) throws SmackException, XmlPullParserException {
            LastActivity lastActivity = new LastActivity();
            String attributeValue = xmlPullParser.getAttributeValue("", "seconds");
            if (attributeValue != null) {
                try {
                    lastActivity.setLastActivity(Long.parseLong(attributeValue));
                } catch (Throwable e) {
                    throw new SmackException("Could not parse last activity number", e);
                }
            }
            try {
                lastActivity.setMessage(xmlPullParser.nextText());
                return lastActivity;
            } catch (Throwable e2) {
                throw new SmackException(e2);
            }
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5894a(xmlPullParser, i);
        }
    }

    public LastActivity() {
        super(ELEMENT, NAMESPACE);
        this.lastActivity = -1;
        setType(Type.get);
    }

    public LastActivity(String str) {
        this();
        setTo(str);
    }

    private void setMessage(String str) {
        this.message = str;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.optLongAttribute("seconds", Long.valueOf(this.lastActivity));
        iQChildElementXmlStringBuilder.setEmptyElement();
        return iQChildElementXmlStringBuilder;
    }

    public long getIdleTime() {
        return this.lastActivity;
    }

    public String getStatusMessage() {
        return this.message;
    }

    public void setLastActivity(long j) {
        this.lastActivity = j;
    }
}
