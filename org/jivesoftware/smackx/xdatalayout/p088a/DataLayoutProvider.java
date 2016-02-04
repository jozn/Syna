package org.jivesoftware.smackx.xdatalayout.p088a;

import java.io.IOException;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.DataFormLayoutElement;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Fieldref;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Reportedref;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Section;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout.Text;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: org.jivesoftware.smackx.xdatalayout.a.a */
public class DataLayoutProvider {
    public static DataLayout m6016a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, SmackException {
        DataLayout dataLayout = new DataLayout(xmlPullParser.getAttributeValue("", "label"));
        DataLayoutProvider.m6017a(dataLayout.getPageLayout(), xmlPullParser);
        return dataLayout;
    }

    private static void m6017a(List<DataFormLayoutElement> list, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        while (true) {
            switch (xmlPullParser.next()) {
                case VideoSize.HVGA /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -928989863:
                            if (name.equals(Fieldref.ELEMENT)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -241484064:
                            if (name.equals(Reportedref.ELEMENT)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3556653:
                            if (name.equals(Text.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1970241253:
                            if (name.equals(Section.ELEMENT)) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case VideoSize.QCIF /*0*/:
                            list.add(new Text(xmlPullParser.nextText()));
                            break;
                        case VideoSize.CIF /*1*/:
                            list.add(DataLayoutProvider.m6018b(xmlPullParser));
                            break;
                        case VideoSize.HVGA /*2*/:
                            list.add(DataLayoutProvider.m6019c(xmlPullParser));
                            break;
                        case Version.API03_CUPCAKE_15 /*3*/:
                            list.add(new Reportedref());
                            break;
                        default:
                            break;
                    }
                case Version.API03_CUPCAKE_15 /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return;
                default:
                    break;
            }
        }
    }

    private static Section m6018b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Section section = new Section(xmlPullParser.getAttributeValue("", "label"));
        DataLayoutProvider.m6017a(section.getSectionLayout(), xmlPullParser);
        return section;
    }

    private static Fieldref m6019c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        Fieldref fieldref = new Fieldref(xmlPullParser.getAttributeValue("", "var"));
        while (true) {
            if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == depth) {
                return fieldref;
            }
        }
    }
}
