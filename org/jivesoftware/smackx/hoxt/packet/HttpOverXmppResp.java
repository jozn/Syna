package org.jivesoftware.smackx.hoxt.packet;

import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smack.util.StringUtils;

public class HttpOverXmppResp extends AbstractHttpOverXmpp {
    public static final String ELEMENT = "resp";
    private int statusCode;
    private String statusMessage;

    public HttpOverXmppResp() {
        super(ELEMENT);
        this.statusMessage = null;
    }

    protected IQChildElementXmlStringBuilder getIQHoxtChildElementBuilder(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "version='").append(StringUtils.escapeForXML(this.version)).append((CharSequence) "'");
        iQChildElementXmlStringBuilder.append((CharSequence) " ");
        iQChildElementXmlStringBuilder.append((CharSequence) "statusCode='").append(Integer.toString(this.statusCode)).append((CharSequence) "'");
        if (this.statusMessage != null) {
            iQChildElementXmlStringBuilder.append((CharSequence) " ");
            iQChildElementXmlStringBuilder.append((CharSequence) "statusMessage='").append(StringUtils.escapeForXML(this.statusMessage)).append((CharSequence) "'");
        }
        iQChildElementXmlStringBuilder.append((CharSequence) ">");
        return iQChildElementXmlStringBuilder;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public void setStatusMessage(String str) {
        this.statusMessage = str;
    }
}
