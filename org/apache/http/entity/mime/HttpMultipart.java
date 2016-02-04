package org.apache.http.entity.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.protocol.HTTP;
import org.linphone.core.VideoSize;

@Deprecated
public class HttpMultipart extends AbstractMultipartForm {
    private final HttpMultipartMode mode;
    private final List<FormBodyPart> parts;

    /* renamed from: org.apache.http.entity.mime.HttpMultipart.1 */
    static /* synthetic */ class C01311 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$http$entity$mime$HttpMultipartMode;

        static {
            $SwitchMap$org$apache$http$entity$mime$HttpMultipartMode = new int[HttpMultipartMode.values().length];
            try {
                $SwitchMap$org$apache$http$entity$mime$HttpMultipartMode[HttpMultipartMode.BROWSER_COMPATIBLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public HttpMultipart(String str, String str2) {
        this(str, null, str2);
    }

    public HttpMultipart(String str, Charset charset, String str2) {
        this(str, charset, str2, HttpMultipartMode.STRICT);
    }

    public HttpMultipart(String str, Charset charset, String str2, HttpMultipartMode httpMultipartMode) {
        super(str, charset, str2);
        this.mode = httpMultipartMode;
        this.parts = new ArrayList();
    }

    public void addBodyPart(FormBodyPart formBodyPart) {
        if (formBodyPart != null) {
            this.parts.add(formBodyPart);
        }
    }

    protected void formatMultipartHeader(FormBodyPart formBodyPart, OutputStream outputStream) throws IOException {
        Header header = formBodyPart.getHeader();
        switch (C01311.$SwitchMap$org$apache$http$entity$mime$HttpMultipartMode[this.mode.ordinal()]) {
            case VideoSize.CIF /*1*/:
                AbstractMultipartForm.writeField(header.getField(MIME.CONTENT_DISPOSITION), this.charset, outputStream);
                if (formBodyPart.getBody().getFilename() != null) {
                    AbstractMultipartForm.writeField(header.getField(HTTP.CONTENT_TYPE), this.charset, outputStream);
                }
            default:
                Iterator it = header.iterator();
                while (it.hasNext()) {
                    AbstractMultipartForm.writeField((MinimalField) it.next(), outputStream);
                }
        }
    }

    public List<FormBodyPart> getBodyParts() {
        return this.parts;
    }

    public /* bridge */ /* synthetic */ String getBoundary() {
        return super.getBoundary();
    }

    public /* bridge */ /* synthetic */ Charset getCharset() {
        return super.getCharset();
    }

    public HttpMultipartMode getMode() {
        return this.mode;
    }

    public /* bridge */ /* synthetic */ String getSubType() {
        return super.getSubType();
    }

    public /* bridge */ /* synthetic */ long getTotalLength() {
        return super.getTotalLength();
    }

    public /* bridge */ /* synthetic */ void writeTo(OutputStream outputStream) throws IOException {
        super.writeTo(outputStream);
    }
}
