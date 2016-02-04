package org.jivesoftware.smackx.csi.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.csi.packet.ClientStateIndication.Feature;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ClientStateIndicationFeatureProvider extends ExtensionElementProvider<Feature> {
    public Feature m5881a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return Feature.INSTANCE;
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5881a(xmlPullParser, i);
    }
}
