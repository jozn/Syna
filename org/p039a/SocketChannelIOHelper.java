package org.p039a;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.spi.AbstractSelectableChannel;

/* renamed from: org.a.c */
public class SocketChannelIOHelper {
    static final /* synthetic */ boolean f4055a;

    static {
        f4055a = !SocketChannelIOHelper.class.desiredAssertionStatus();
    }

    public static void m5249a(WebSocketImpl webSocketImpl, ByteChannel byteChannel) throws InterruptedException, IOException {
        if (!f4055a && (byteChannel instanceof AbstractSelectableChannel) && !((AbstractSelectableChannel) byteChannel).isBlocking()) {
            throw new AssertionError();
        } else if (f4055a || !(byteChannel instanceof WrappedByteChannel) || ((WrappedByteChannel) byteChannel).m5155b()) {
            ByteBuffer byteBuffer = (ByteBuffer) webSocketImpl.f4109f.take();
            while (byteBuffer.hasRemaining()) {
                byteChannel.write(byteBuffer);
            }
        } else {
            throw new AssertionError();
        }
    }

    public static boolean m5250a(ByteBuffer byteBuffer, WebSocketImpl webSocketImpl, ByteChannel byteChannel) throws IOException {
        byteBuffer.clear();
        int read = byteChannel.read(byteBuffer);
        byteBuffer.flip();
        if (read != -1) {
            return read != 0;
        } else {
            webSocketImpl.m5336b();
            return false;
        }
    }

    public static boolean m5251a(ByteBuffer byteBuffer, WebSocketImpl webSocketImpl, WrappedByteChannel wrappedByteChannel) throws IOException {
        byteBuffer.clear();
        int a = wrappedByteChannel.m5153a(byteBuffer);
        byteBuffer.flip();
        if (a != -1) {
            return wrappedByteChannel.m5154a();
        }
        webSocketImpl.m5336b();
        return false;
    }
}
