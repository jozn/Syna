package org.jivesoftware.smackx.muc.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.muc.packet.MUCUser.Decline;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.jivesoftware.smackx.xhtmlim.XHTMLText;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MUCUserProvider extends ExtensionElementProvider<MUCUser> {
    private static Invite m5961a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Object obj = null;
        Invite invite = new Invite();
        invite.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM));
        invite.setTo(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_TO));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    invite.setReason(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(Invite.ELEMENT)) {
                obj = 1;
            }
        }
        return invite;
    }

    private static Decline m5962b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Object obj = null;
        Decline decline = new Decline();
        decline.setFrom(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_FROM));
        decline.setTo(xmlPullParser.getAttributeValue("", PrivacyItem.SUBSCRIPTION_TO));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    decline.setReason(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals(Decline.ELEMENT)) {
                obj = 1;
            }
        }
        return decline;
    }

    public MUCUser m5963a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        MUCUser mUCUser = new MUCUser();
        while (true) {
            switch (xmlPullParser.next()) {
                case VideoSize.HVGA /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -1183699191:
                            if (name.equals(Invite.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                        case -892481550:
                            if (name.equals(Status.ELEMENT)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3242771:
                            if (name.equals(Item.ELEMENT)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1216985755:
                            if (name.equals("password")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1542349558:
                            if (name.equals(Decline.ELEMENT)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 1557372922:
                            if (name.equals(Destroy.ELEMENT)) {
                                obj = 5;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case VideoSize.QCIF /*0*/:
                            mUCUser.setInvite(m5961a(xmlPullParser));
                            break;
                        case VideoSize.CIF /*1*/:
                            mUCUser.setItem(MUCParserUtils.m5964a(xmlPullParser));
                            break;
                        case VideoSize.HVGA /*2*/:
                            mUCUser.setPassword(xmlPullParser.nextText());
                            break;
                        case Version.API03_CUPCAKE_15 /*3*/:
                            mUCUser.addStatusCode(Status.create(xmlPullParser.getAttributeValue("", XHTMLText.CODE)));
                            break;
                        case Version.API04_DONUT_16 /*4*/:
                            mUCUser.setDecline(m5962b(xmlPullParser));
                            break;
                        case Version.API05_ECLAIR_20 /*5*/:
                            mUCUser.setDestroy(MUCParserUtils.m5965b(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                case Version.API03_CUPCAKE_15 /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return mUCUser;
                default:
                    break;
            }
        }
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5963a(xmlPullParser, i);
    }
}
