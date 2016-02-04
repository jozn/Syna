package org.jivesoftware.smackx.hoxt.packet;

import org.jivesoftware.smack.packet.ExtensionElement;

public class Base64BinaryChunk implements ExtensionElement {
    public static final String ATTRIBUTE_LAST = "last";
    public static final String ATTRIBUTE_NR = "nr";
    public static final String ATTRIBUTE_STREAM_ID = "streamId";
    public static final String ELEMENT_CHUNK = "chunk";
    private final boolean last;
    private final int nr;
    private final String streamId;
    private final String text;

    public Base64BinaryChunk(String str, String str2, int i) {
        this(str, str2, i, false);
    }

    public Base64BinaryChunk(String str, String str2, int i, boolean z) {
        this.text = str;
        this.streamId = str2;
        this.nr = i;
        this.last = z;
    }

    public String getElementName() {
        return ELEMENT_CHUNK;
    }

    public String getNamespace() {
        return AbstractHttpOverXmpp.NAMESPACE;
    }

    public int getNr() {
        return this.nr;
    }

    public String getStreamId() {
        return this.streamId;
    }

    public String getText() {
        return this.text;
    }

    public boolean isLast() {
        return this.last;
    }

    public String toXML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<chunk xmlns='urn:xmpp:http' streamId='");
        stringBuilder.append(this.streamId);
        stringBuilder.append("' nr='");
        stringBuilder.append(this.nr);
        stringBuilder.append("' last='");
        stringBuilder.append(Boolean.toString(this.last));
        stringBuilder.append("'>");
        stringBuilder.append(this.text);
        stringBuilder.append("</chunk>");
        return stringBuilder.toString();
    }
}
