package org.jivesoftware.smackx.ping.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.ping.packet.Ping;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PingProvider extends IQProvider<Ping> {
    public Ping m5973a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        return new Ping();
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5973a(xmlPullParser, i);
    }
}
