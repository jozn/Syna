package org.jivesoftware.smackx.iqprivate.p085a;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.iqprivate.packet.PrivateData;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: org.jivesoftware.smackx.iqprivate.a.a */
public interface PrivateDataProvider {
    PrivateData m5856a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, SmackException;
}
