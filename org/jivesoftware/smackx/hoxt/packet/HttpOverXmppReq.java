package org.jivesoftware.smackx.hoxt.packet;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.util.StringUtils;

public class HttpOverXmppReq extends AbstractHttpOverXmpp {
    public static final String ELEMENT = "req";
    private boolean ibb;
    private boolean jingle;
    private int maxChunkSize;
    private HttpMethod method;
    private String resource;
    private boolean sipub;

    public HttpOverXmppReq(HttpMethod httpMethod, String str) {
        super(ELEMENT);
        this.maxChunkSize = 0;
        this.sipub = true;
        this.ibb = true;
        this.jingle = true;
        this.method = httpMethod;
        this.resource = str;
        setType(Type.set);
    }

    protected IQChildElementXmlStringBuilder getIQHoxtChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "method='").append(this.method.toString()).append((CharSequence) "'");
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "resource='").append(StringUtils.escapeForXML(this.resource)).append((CharSequence) "'");
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "version='").append(StringUtils.escapeForXML(this.version)).append((CharSequence) "'");
        if (this.maxChunkSize != 0) {
            iQChildElementXmlStringBuilder.append((CharSequence) " ");
            iQChildElementXmlStringBuilder.append((CharSequence) "maxChunkSize='").append(Integer.toString(this.maxChunkSize)).append((CharSequence) "'");
        }
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "sipub='").append(Boolean.toString(this.sipub)).append((CharSequence) "'");
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "ibb='").append(Boolean.toString(this.ibb)).append((CharSequence) "'");
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "jingle='").append(Boolean.toString(this.jingle)).append((CharSequence) "'");
        iQChildElementXmlStringBuilder.append((CharSequence) ">");
        return iQChildElementXmlStringBuilder;
    }

    public int getMaxChunkSize() {
        return this.maxChunkSize;
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    public String getResource() {
        return this.resource;
    }

    public boolean isIbb() {
        return this.ibb;
    }

    public boolean isJingle() {
        return this.jingle;
    }

    public boolean isSipub() {
        return this.sipub;
    }

    public void setIbb(boolean z) {
        this.ibb = z;
    }

    public void setJingle(boolean z) {
        this.jingle = z;
    }

    public void setMaxChunkSize(int i) {
        this.maxChunkSize = i;
    }

    public void setSipub(boolean z) {
        this.sipub = z;
    }
}
