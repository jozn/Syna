package org.jivesoftware.smack.compression;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class XMPPInputOutputStream {
    protected static FlushMethod flushMethod;
    protected final String compressionMethod;

    public enum FlushMethod {
        FULL_FLUSH,
        SYNC_FLUSH
    }

    protected XMPPInputOutputStream(String str) {
        this.compressionMethod = str;
    }

    public static void setFlushMethod(FlushMethod flushMethod) {
        flushMethod = flushMethod;
    }

    public String getCompressionMethod() {
        return this.compressionMethod;
    }

    public abstract InputStream getInputStream(InputStream inputStream) throws IOException;

    public abstract OutputStream getOutputStream(OutputStream outputStream) throws IOException;

    public abstract boolean isSupported();
}
