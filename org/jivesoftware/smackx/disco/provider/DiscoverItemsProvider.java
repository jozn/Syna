package org.jivesoftware.smackx.disco.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.disco.packet.DiscoverItems;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DiscoverItemsProvider extends IQProvider<DiscoverItems> {
    public DiscoverItems m5883a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        DiscoverItems discoverItems = new DiscoverItems();
        Object obj = null;
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        discoverItems.setNode(xmlPullParser.getAttributeValue("", "node"));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2 && Item.ELEMENT.equals(xmlPullParser.getName())) {
                str = xmlPullParser.getAttributeValue("", "jid");
                str2 = xmlPullParser.getAttributeValue("", "name");
                str4 = xmlPullParser.getAttributeValue("", "node");
                str3 = xmlPullParser.getAttributeValue("", Action.ATTRIBUTE_NAME);
            } else if (next == 3 && Item.ELEMENT.equals(xmlPullParser.getName())) {
                DiscoverItems.Item item = new DiscoverItems.Item(str);
                item.setName(str2);
                item.setNode(str4);
                item.setAction(str3);
                discoverItems.addItem(item);
            } else if (next == 3 && UserSearch.ELEMENT.equals(xmlPullParser.getName())) {
                obj = 1;
            }
        }
        return discoverItems;
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5883a(xmlPullParser, i);
    }
}
