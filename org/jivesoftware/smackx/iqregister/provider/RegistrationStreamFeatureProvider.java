package org.jivesoftware.smackx.iqregister.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.iqregister.packet.Registration.Feature;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class RegistrationStreamFeatureProvider extends ExtensionElementProvider<Feature> {
    public Feature m5897a(XmlPullParser xmlPullParser, int i) {
        return Feature.INSTANCE;
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5897a(xmlPullParser, i);
    }
}
