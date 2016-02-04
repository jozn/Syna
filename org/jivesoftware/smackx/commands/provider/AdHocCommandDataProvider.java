package org.jivesoftware.smackx.commands.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.amp.packet.AMPExtension.Action;
import org.jivesoftware.smackx.commands.AdHocCommand;
import org.jivesoftware.smackx.commands.AdHocCommand.SpecificErrorCondition;
import org.jivesoftware.smackx.commands.AdHocCommandNote;
import org.jivesoftware.smackx.commands.AdHocCommandNote.Type;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData;
import org.jivesoftware.smackx.commands.packet.AdHocCommandData.SpecificError;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.provider.DataFormProvider;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AdHocCommandDataProvider extends IQProvider<AdHocCommandData> {

    public static class BadActionError extends ExtensionElementProvider<SpecificError> {
        public SpecificError m5874a(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badAction);
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5874a(xmlPullParser, i);
        }
    }

    public static class BadLocaleError extends ExtensionElementProvider<SpecificError> {
        public SpecificError m5875a(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badLocale);
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5875a(xmlPullParser, i);
        }
    }

    public static class BadPayloadError extends ExtensionElementProvider<SpecificError> {
        public SpecificError m5876a(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badPayload);
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5876a(xmlPullParser, i);
        }
    }

    public static class BadSessionIDError extends ExtensionElementProvider<SpecificError> {
        public SpecificError m5877a(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.badSessionid);
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5877a(xmlPullParser, i);
        }
    }

    public static class MalformedActionError extends ExtensionElementProvider<SpecificError> {
        public SpecificError m5878a(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.malformedAction);
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5878a(xmlPullParser, i);
        }
    }

    public static class SessionExpiredError extends ExtensionElementProvider<SpecificError> {
        public SpecificError m5879a(XmlPullParser xmlPullParser, int i) {
            return new SpecificError(SpecificErrorCondition.sessionExpired);
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m5879a(xmlPullParser, i);
        }
    }

    public AdHocCommandData m5880a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        Object obj;
        int next;
        String name;
        String attributeValue;
        AdHocCommandData adHocCommandData = new AdHocCommandData();
        DataFormProvider dataFormProvider = new DataFormProvider();
        adHocCommandData.setSessionID(xmlPullParser.getAttributeValue("", "sessionid"));
        adHocCommandData.setNode(xmlPullParser.getAttributeValue("", "node"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", Status.ELEMENT);
        if (AdHocCommand.Status.executing.toString().equalsIgnoreCase(attributeValue2)) {
            adHocCommandData.setStatus(AdHocCommand.Status.executing);
        } else if (AdHocCommand.Status.completed.toString().equalsIgnoreCase(attributeValue2)) {
            adHocCommandData.setStatus(AdHocCommand.Status.completed);
        } else if (AdHocCommand.Status.canceled.toString().equalsIgnoreCase(attributeValue2)) {
            adHocCommandData.setStatus(AdHocCommand.Status.canceled);
        }
        attributeValue2 = xmlPullParser.getAttributeValue("", Action.ATTRIBUTE_NAME);
        if (attributeValue2 != null) {
            AdHocCommand.Action valueOf = AdHocCommand.Action.valueOf(attributeValue2);
            if (valueOf == null || valueOf.equals(AdHocCommand.Action.unknown)) {
                adHocCommandData.setAction(AdHocCommand.Action.unknown);
                obj = null;
                while (obj == null) {
                    next = xmlPullParser.next();
                    name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    if (next != 2) {
                        if (xmlPullParser.getName().equals("actions")) {
                            attributeValue = xmlPullParser.getAttributeValue("", "execute");
                            if (attributeValue != null) {
                                adHocCommandData.setExecuteAction(AdHocCommand.Action.valueOf(attributeValue));
                            }
                        } else if (xmlPullParser.getName().equals("next")) {
                            adHocCommandData.addAction(AdHocCommand.Action.next);
                        } else if (xmlPullParser.getName().equals("complete")) {
                            adHocCommandData.addAction(AdHocCommand.Action.complete);
                        } else if (xmlPullParser.getName().equals("prev")) {
                            adHocCommandData.addAction(AdHocCommand.Action.prev);
                        } else if (!name.equals(DataForm.ELEMENT) && namespace.equals(DataForm.NAMESPACE)) {
                            adHocCommandData.setForm((DataForm) dataFormProvider.parse(xmlPullParser));
                        } else if (xmlPullParser.getName().equals("note")) {
                            adHocCommandData.addNote(new AdHocCommandNote(Type.valueOf(xmlPullParser.getAttributeValue("", "type")), xmlPullParser.nextText()));
                        } else if (xmlPullParser.getName().equals(XMPPError.ERROR)) {
                            adHocCommandData.setError(PacketParserUtils.parseError(xmlPullParser));
                        }
                    } else if (next == 3 && xmlPullParser.getName().equals(AdHocCommandData.ELEMENT)) {
                        obj = 1;
                    }
                }
                return adHocCommandData;
            }
            adHocCommandData.setAction(valueOf);
        }
        obj = null;
        while (obj == null) {
            next = xmlPullParser.next();
            name = xmlPullParser.getName();
            String namespace2 = xmlPullParser.getNamespace();
            if (next != 2) {
                obj = 1;
            } else if (xmlPullParser.getName().equals("actions")) {
                attributeValue = xmlPullParser.getAttributeValue("", "execute");
                if (attributeValue != null) {
                    adHocCommandData.setExecuteAction(AdHocCommand.Action.valueOf(attributeValue));
                }
            } else if (xmlPullParser.getName().equals("next")) {
                adHocCommandData.addAction(AdHocCommand.Action.next);
            } else if (xmlPullParser.getName().equals("complete")) {
                adHocCommandData.addAction(AdHocCommand.Action.complete);
            } else if (xmlPullParser.getName().equals("prev")) {
                adHocCommandData.addAction(AdHocCommand.Action.prev);
            } else {
                if (!name.equals(DataForm.ELEMENT)) {
                }
                if (xmlPullParser.getName().equals("note")) {
                    adHocCommandData.addNote(new AdHocCommandNote(Type.valueOf(xmlPullParser.getAttributeValue("", "type")), xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals(XMPPError.ERROR)) {
                    adHocCommandData.setError(PacketParserUtils.parseError(xmlPullParser));
                }
            }
        }
        return adHocCommandData;
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m5880a(xmlPullParser, i);
    }
}
