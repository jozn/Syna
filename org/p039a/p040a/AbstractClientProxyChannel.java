package org.p039a.p040a;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import org.apache.http.protocol.HTTP;
import org.p039a.AbstractWrappedByteChannel;

/* renamed from: org.a.a.a */
public abstract class AbstractClientProxyChannel extends AbstractWrappedByteChannel {
    protected final ByteBuffer f4010a;

    public AbstractClientProxyChannel(ByteChannel byteChannel) {
        super(byteChannel);
        try {
            this.f4010a = ByteBuffer.wrap(m5159c().getBytes(HTTP.ASCII));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String m5159c();

    public int write(ByteBuffer byteBuffer) throws IOException {
        return !this.f4010a.hasRemaining() ? super.write(byteBuffer) : super.write(this.f4010a);
    }
}
