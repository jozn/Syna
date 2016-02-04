package org.p039a;

import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import javax.net.ssl.SSLException;

/* renamed from: org.a.i */
public interface WrappedByteChannel extends ByteChannel {
    int m5153a(ByteBuffer byteBuffer) throws SSLException;

    boolean m5154a();

    boolean m5155b();
}
