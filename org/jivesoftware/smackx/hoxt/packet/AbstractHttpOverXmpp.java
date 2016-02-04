package org.jivesoftware.smackx.hoxt.packet;

import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.shim.packet.HeadersExtension;

public abstract class AbstractHttpOverXmpp extends IQ {
    public static final String NAMESPACE = "urn:xmpp:http";
    private Data data;
    private HeadersExtension headers;
    protected String version;

    public static class Base64 implements NamedElement {
        public static final String ELEMENT = "base64";
        private final String text;

        public Base64(String str) {
            this.text = str;
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getText() {
            return this.text;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.rightAngleBracket();
            xmlStringBuilder.optAppend(this.text);
            xmlStringBuilder.closeElement((NamedElement) this);
            return xmlStringBuilder;
        }
    }

    public static class ChunkedBase64 implements NamedElement {
        public static final String ELEMENT = "chunkedBase64";
        private final String streamId;

        public ChunkedBase64(String str) {
            this.streamId = str;
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getStreamId() {
            return this.streamId;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.attribute(Base64BinaryChunk.ATTRIBUTE_STREAM_ID, this.streamId);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }
    }

    public static class Data {
        private final NamedElement child;

        public Data(NamedElement namedElement) {
            this.child = namedElement;
        }

        public NamedElement getChild() {
            return this.child;
        }

        public String toXML() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<data>");
            stringBuilder.append(this.child.toXML());
            stringBuilder.append("</data>");
            return stringBuilder.toString();
        }
    }

    public static class Ibb implements NamedElement {
        public static final String ELEMENT = "ibb";
        private final String sid;

        public Ibb(String str) {
            this.sid = str;
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getSid() {
            return this.sid;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.attribute("sid", this.sid);
            xmlStringBuilder.closeEmptyElement();
            return xmlStringBuilder;
        }
    }

    public static class Text implements NamedElement {
        public static final String ELEMENT = "text";
        private final String text;

        public Text(String str) {
            this.text = str;
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getText() {
            return this.text;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.rightAngleBracket();
            xmlStringBuilder.optAppend(this.text);
            xmlStringBuilder.closeElement((NamedElement) this);
            return xmlStringBuilder;
        }
    }

    public static class Xml implements NamedElement {
        public static final String ELEMENT = "xml";
        private final String text;

        public Xml(String str) {
            this.text = str;
        }

        public String getElementName() {
            return ELEMENT;
        }

        public String getText() {
            return this.text;
        }

        public XmlStringBuilder toXML() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((NamedElement) this);
            xmlStringBuilder.rightAngleBracket();
            xmlStringBuilder.optAppend(this.text);
            xmlStringBuilder.closeElement((NamedElement) this);
            return xmlStringBuilder;
        }
    }

    protected AbstractHttpOverXmpp(String str) {
        super(str, NAMESPACE);
    }

    public Data getData() {
        return this.data;
    }

    public HeadersExtension getHeaders() {
        return this.headers;
    }

    protected IQChildElementXmlStringBuilder getIQChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        IQChildElementXmlStringBuilder iQHoxtChildElementBuilder = getIQHoxtChildElementBuilder(iQChildElementXmlStringBuilder);
        iQHoxtChildElementBuilder.append(this.headers.toXML());
        iQHoxtChildElementBuilder.append(this.data.toXML());
        return iQHoxtChildElementBuilder;
    }

    protected abstract IQChildElementXmlStringBuilder getIQHoxtChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder);

    public String getVersion() {
        return this.version;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setHeaders(HeadersExtension headersExtension) {
        this.headers = headersExtension;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
