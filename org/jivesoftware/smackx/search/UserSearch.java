package org.jivesoftware.smackx.search;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.SimpleIQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.hoxt.packet.Base64BinaryChunk;
import org.jivesoftware.smackx.nick.packet.Nick;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;
import org.jivesoftware.smackx.xdata.packet.DataForm.Type;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class UserSearch extends SimpleIQ {
    public static final String ELEMENT = "query";
    public static final String NAMESPACE = "jabber:iq:search";

    public static class Provider extends IQProvider<IQ> {
        public IQ m6004a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            IQ iq = null;
            IQ simpleUserSearch = new SimpleUserSearch();
            Object obj = null;
            while (obj == null) {
                int next = xmlPullParser.next();
                if (next == 2 && xmlPullParser.getName().equals("instructions")) {
                    UserSearch.buildDataForm(simpleUserSearch, xmlPullParser.nextText(), xmlPullParser);
                    return simpleUserSearch;
                } else if (next == 2 && xmlPullParser.getName().equals(Item.ELEMENT)) {
                    simpleUserSearch.parseItems(xmlPullParser);
                    return simpleUserSearch;
                } else if (next == 2 && xmlPullParser.getNamespace().equals(DataForm.NAMESPACE)) {
                    iq = new UserSearch();
                    PacketParserUtils.addExtensionElement((Stanza) iq, xmlPullParser);
                } else if (next == 3 && xmlPullParser.getName().equals(UserSearch.ELEMENT)) {
                    obj = 1;
                }
            }
            return iq == null ? simpleUserSearch : iq;
        }

        public /* synthetic */ Element parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m6004a(xmlPullParser, i);
        }
    }

    public UserSearch() {
        super(ELEMENT, NAMESPACE);
    }

    private static void buildDataForm(SimpleUserSearch simpleUserSearch, String str, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, SmackException {
        ExtensionElement dataForm = new DataForm(Type.form);
        Object obj = null;
        dataForm.setTitle("User Search");
        dataForm.addInstruction(str);
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2 && !xmlPullParser.getNamespace().equals(DataForm.NAMESPACE)) {
                String name = xmlPullParser.getName();
                FormField formField = new FormField(name);
                if (name.equals("first")) {
                    formField.setLabel("First Name");
                } else if (name.equals(Base64BinaryChunk.ATTRIBUTE_LAST)) {
                    formField.setLabel("Last Name");
                } else if (name.equals("email")) {
                    formField.setLabel("Email Address");
                } else if (name.equals(Nick.ELEMENT_NAME)) {
                    formField.setLabel("Nickname");
                }
                formField.setType(FormField.Type.text_single);
                dataForm.addField(formField);
            } else if (next == 3) {
                if (xmlPullParser.getName().equals(ELEMENT)) {
                    obj = 1;
                }
            } else if (next == 2 && xmlPullParser.getNamespace().equals(DataForm.NAMESPACE)) {
                PacketParserUtils.addExtensionElement((Stanza) simpleUserSearch, xmlPullParser);
                obj = 1;
            }
        }
        if (simpleUserSearch.getExtension(DataForm.ELEMENT, DataForm.NAMESPACE) == null) {
            simpleUserSearch.addExtension(dataForm);
        }
    }

    public Form getSearchForm(XMPPConnection xMPPConnection, String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ userSearch = new UserSearch();
        userSearch.setType(IQ.Type.get);
        userSearch.setTo(str);
        return Form.getFormFrom((IQ) xMPPConnection.createPacketCollectorAndSend(userSearch).nextResultOrThrow());
    }

    public ReportedData sendSearchForm(XMPPConnection xMPPConnection, Form form, String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ userSearch = new UserSearch();
        userSearch.setType(IQ.Type.set);
        userSearch.setTo(str);
        userSearch.addExtension(form.getDataFormToSend());
        return ReportedData.getReportedDataFrom((IQ) xMPPConnection.createPacketCollectorAndSend(userSearch).nextResultOrThrow());
    }

    public ReportedData sendSimpleSearchForm(XMPPConnection xMPPConnection, Form form, String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ simpleUserSearch = new SimpleUserSearch();
        simpleUserSearch.setForm(form);
        simpleUserSearch.setType(IQ.Type.set);
        simpleUserSearch.setTo(str);
        return ((SimpleUserSearch) xMPPConnection.createPacketCollectorAndSend(simpleUserSearch).nextResultOrThrow()).getReportedData();
    }
}
