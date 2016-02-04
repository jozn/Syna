package org.jivesoftware.smackx.pubsub.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.pubsub.Subscription;
import org.jivesoftware.smackx.pubsub.Subscription.State;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SubscriptionProvider extends ExtensionElementProvider<Subscription> {
    public Subscription m5998a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        State state = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "jid");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "node");
        String attributeValue3 = xmlPullParser.getAttributeValue(null, "subid");
        String attributeValue4 = xmlPullParser.getAttributeValue(null, "subscription");
        boolean z = false;
        if (xmlPullParser.next() == 2 && xmlPullParser.getName().equals("subscribe-options")) {
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals("required")) {
                z = true;
            }
            while (next != 3 && !xmlPullParser.getName().equals("subscribe-options")) {
                next = xmlPullParser.next();
            }
        }
        while (xmlPullParser.getEventType() != 3) {
            xmlPullParser.next();
        }
        if (attributeValue4 != null) {
            state = State.valueOf(attributeValue4);
        }
        return new Subscription(attributeValue, attributeValue2, attributeValue3, state, z);
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5998a(xmlPullParser, i);
    }
}
