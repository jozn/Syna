package org.jivesoftware.smack.sm.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.sm.packet.StreamManagement.StreamManagementFeature;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class StreamManagementStreamFeatureProvider extends ExtensionElementProvider<StreamManagementFeature> {
    public StreamManagementFeature m5834a(XmlPullParser xmlPullParser, int i) {
        return StreamManagementFeature.INSTANCE;
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5834a(xmlPullParser, i);
    }
}
