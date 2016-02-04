package org.p039a;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SocketChannel;
import javax.net.ssl.SSLException;

/* renamed from: org.a.a */
public class AbstractWrappedByteChannel implements WrappedByteChannel {
    private final ByteChannel f4009a;

    public AbstractWrappedByteChannel(ByteChannel byteChannel) {
        this.f4009a = byteChannel;
    }

    public int m5156a(ByteBuffer byteBuffer) throws SSLException {
        return this.f4009a instanceof WrappedByteChannel ? ((WrappedByteChannel) this.f4009a).m5153a(byteBuffer) : 0;
    }

    public boolean m5157a() {
        return this.f4009a instanceof WrappedByteChannel ? ((WrappedByteChannel) this.f4009a).m5154a() : false;
    }

    public boolean m5158b() {
        return this.f4009a instanceof SocketChannel ? ((SocketChannel) this.f4009a).isBlocking() : this.f4009a instanceof WrappedByteChannel ? ((WrappedByteChannel) this.f4009a).m5155b() : false;
    }

    public void close() throws IOException {
        this.f4009a.close();
    }

    public boolean isOpen() {
        return this.f4009a.isOpen();
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        return this.f4009a.read(byteBuffer);
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        return this.f4009a.write(byteBuffer);
    }
}
