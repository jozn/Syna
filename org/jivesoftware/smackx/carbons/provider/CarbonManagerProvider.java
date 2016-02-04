package org.jivesoftware.smackx.carbons.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.carbons.packet.CarbonExtension;
import org.jivesoftware.smackx.carbons.packet.CarbonExtension.Direction;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jivesoftware.smackx.forward.provider.ForwardedProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class CarbonManagerProvider extends ExtensionElementProvider<CarbonExtension> {
    private static final ForwardedProvider FORWARDED_PROVIDER;

    static {
        FORWARDED_PROVIDER = new ForwardedProvider();
    }

    public CarbonExtension parse(XmlPullParser xmlPullParser, int i) throws SmackException, XmlPullParserException, IOException {
        Direction valueOf = Direction.valueOf(xmlPullParser.getName());
        Forwarded forwarded = null;
        Object obj = null;
        while (obj == null) {
            Forwarded forwarded2;
            Object obj2;
            int next = xmlPullParser.next();
            if (next == 2 && xmlPullParser.getName().equals(Forwarded.ELEMENT)) {
                Object obj3 = obj;
                forwarded2 = (Forwarded) FORWARDED_PROVIDER.parse(xmlPullParser);
                obj2 = obj3;
            } else if (next == 3 && valueOf == Direction.valueOf(xmlPullParser.getName())) {
                obj2 = 1;
                forwarded2 = forwarded;
            } else {
                obj2 = obj;
                forwarded2 = forwarded;
            }
            forwarded = forwarded2;
            obj = obj2;
        }
        if (forwarded != null) {
            return new CarbonExtension(valueOf, forwarded);
        }
        throw new SmackException("sent/received must contain exactly one <forwarded> tag");
    }
}
