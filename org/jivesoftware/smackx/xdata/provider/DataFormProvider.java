package org.jivesoftware.smackx.xdata.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.roster.provider.RosterPacketProvider;
import org.jivesoftware.smackx.search.UserSearch;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.FormField.Option;
import org.jivesoftware.smackx.xdata.FormField.Type;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.jivesoftware.smackx.xdata.packet.DataForm.ReportedData;
import org.jivesoftware.smackx.xdatalayout.p088a.DataLayoutProvider;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout;
import org.jivesoftware.smackx.xdatavalidation.packet.ValidateElement;
import org.jivesoftware.smackx.xdatavalidation.provider.DataValidationProvider;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DataFormProvider extends ExtensionElementProvider<DataForm> {
    private FormField m6010a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        FormField formField;
        int depth = xmlPullParser.getDepth();
        String attributeValue = xmlPullParser.getAttributeValue("", "var");
        Type fromString = Type.fromString(xmlPullParser.getAttributeValue("", "type"));
        if (fromString == Type.fixed) {
            formField = new FormField();
        } else {
            formField = new FormField(attributeValue);
            formField.setType(fromString);
        }
        formField.setLabel(xmlPullParser.getAttributeValue("", "label"));
        while (true) {
            switch (xmlPullParser.next()) {
                case VideoSize.HVGA /*2*/:
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    boolean z = true;
                    switch (name.hashCode()) {
                        case -1421272810:
                            if (name.equals(ValidateElement.ELEMENT)) {
                                z = true;
                                break;
                            }
                            break;
                        case -1010136971:
                            if (name.equals(Option.ELEMENT)) {
                                z = true;
                                break;
                            }
                            break;
                        case -393139297:
                            if (name.equals("required")) {
                                z = true;
                                break;
                            }
                            break;
                        case 3079825:
                            if (name.equals("desc")) {
                                z = false;
                                break;
                            }
                            break;
                        case 111972721:
                            if (name.equals("value")) {
                                z = true;
                                break;
                            }
                            break;
                    }
                    switch (z) {
                        case VideoSize.QCIF /*0*/:
                            formField.setDescription(xmlPullParser.nextText());
                            break;
                        case VideoSize.CIF /*1*/:
                            formField.addValue(xmlPullParser.nextText());
                            break;
                        case VideoSize.HVGA /*2*/:
                            formField.setRequired(true);
                            break;
                        case Version.API03_CUPCAKE_15 /*3*/:
                            formField.addOption(m6013d(xmlPullParser));
                            break;
                        case Version.API04_DONUT_16 /*4*/:
                            if (!namespace.equals(ValidateElement.NAMESPACE)) {
                                break;
                            }
                            formField.setValidateElement(DataValidationProvider.parse(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                case Version.API03_CUPCAKE_15 /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return formField;
                default:
                    break;
            }
        }
    }

    private Item m6011b(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        List arrayList = new ArrayList();
        while (true) {
            switch (xmlPullParser.next()) {
                case VideoSize.HVGA /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case 97427706:
                            if (name.equals(FormField.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case VideoSize.QCIF /*0*/:
                            arrayList.add(m6010a(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                case Version.API03_CUPCAKE_15 /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return new Item(arrayList);
                default:
                    break;
            }
        }
    }

    private ReportedData m6012c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        List arrayList = new ArrayList();
        while (true) {
            switch (xmlPullParser.next()) {
                case VideoSize.HVGA /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case 97427706:
                            if (name.equals(FormField.ELEMENT)) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case VideoSize.QCIF /*0*/:
                            arrayList.add(m6010a(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                case Version.API03_CUPCAKE_15 /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return new ReportedData(arrayList);
                default:
                    break;
            }
        }
    }

    private Option m6013d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth();
        Option option = null;
        String attributeValue = xmlPullParser.getAttributeValue("", "label");
        while (true) {
            switch (xmlPullParser.next()) {
                case VideoSize.HVGA /*2*/:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case 111972721:
                            if (name.equals("value")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case VideoSize.QCIF /*0*/:
                            option = new Option(attributeValue, xmlPullParser.nextText());
                            break;
                        default:
                            break;
                    }
                case Version.API03_CUPCAKE_15 /*3*/:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return option;
                default:
                    break;
            }
        }
    }

    public DataForm m6014a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        DataForm dataForm = new DataForm(DataForm.Type.fromString(xmlPullParser.getAttributeValue("", "type")));
        while (true) {
            switch (xmlPullParser.next()) {
                case VideoSize.HVGA /*2*/:
                    String name = xmlPullParser.getName();
                    String namespace = xmlPullParser.getNamespace();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -427039533:
                            if (name.equals(ReportedData.ELEMENT)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 3242771:
                            if (name.equals(Item.ELEMENT)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3433103:
                            if (name.equals(DataLayout.ELEMENT)) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 97427706:
                            if (name.equals(FormField.ELEMENT)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 107944136:
                            if (name.equals(UserSearch.ELEMENT)) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 110371416:
                            if (name.equals("title")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 757376421:
                            if (name.equals("instructions")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case VideoSize.QCIF /*0*/:
                            dataForm.addInstruction(xmlPullParser.nextText());
                            break;
                        case VideoSize.CIF /*1*/:
                            dataForm.setTitle(xmlPullParser.nextText());
                            break;
                        case VideoSize.HVGA /*2*/:
                            dataForm.addField(m6010a(xmlPullParser));
                            break;
                        case Version.API03_CUPCAKE_15 /*3*/:
                            dataForm.addItem(m6011b(xmlPullParser));
                            break;
                        case Version.API04_DONUT_16 /*4*/:
                            dataForm.setReportedData(m6012c(xmlPullParser));
                            break;
                        case Version.API05_ECLAIR_20 /*5*/:
                            if (!namespace.equals(RosterPacket.NAMESPACE)) {
                                break;
                            }
                            dataForm.addExtensionElement(RosterPacketProvider.INSTANCE.parse(xmlPullParser));
                            break;
                        case Version.API06_ECLAIR_201 /*6*/:
                            if (!namespace.equals(DataLayout.NAMESPACE)) {
                                break;
                            }
                            dataForm.addExtensionElement(DataLayoutProvider.m6016a(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                case Version.API03_CUPCAKE_15 /*3*/:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return dataForm;
                default:
                    break;
            }
        }
    }

    public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m6014a(xmlPullParser, i);
    }
}
