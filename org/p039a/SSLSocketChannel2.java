package org.p039a;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* renamed from: org.a.b */
public class SSLSocketChannel2 implements ByteChannel, WrappedByteChannel {
    protected static ByteBuffer f4041a;
    static final /* synthetic */ boolean f4042k;
    protected ExecutorService f4043b;
    protected List<Future<?>> f4044c;
    protected ByteBuffer f4045d;
    protected ByteBuffer f4046e;
    protected ByteBuffer f4047f;
    protected SocketChannel f4048g;
    protected SelectionKey f4049h;
    protected SSLEngineResult f4050i;
    protected SSLEngine f4051j;
    private Status f4052l;

    static {
        f4042k = !SSLSocketChannel2.class.desiredAssertionStatus();
        f4041a = ByteBuffer.allocate(0);
    }

    public SSLSocketChannel2(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws IOException {
        this.f4052l = Status.BUFFER_UNDERFLOW;
        if (socketChannel == null || sSLEngine == null || executorService == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.f4048g = socketChannel;
        this.f4051j = sSLEngine;
        this.f4043b = executorService;
        this.f4044c = new ArrayList(3);
        if (selectionKey != null) {
            selectionKey.interestOps(selectionKey.interestOps() | 4);
            this.f4049h = selectionKey;
        }
        m5243a(sSLEngine.getSession());
        this.f4048g.write(m5237b(f4041a));
        m5239d();
    }

    private int m5235a(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int remaining = byteBuffer.remaining();
        int remaining2 = byteBuffer2.remaining();
        if (remaining > remaining2) {
            remaining = Math.min(remaining, remaining2);
            for (remaining2 = 0; remaining2 < remaining; remaining2++) {
                byteBuffer2.put(byteBuffer.get());
            }
        } else {
            byteBuffer2.put(byteBuffer);
        }
        return remaining;
    }

    private void m5236a(Future<?> future) {
        Object obj = null;
        while (true) {
            try {
                future.get();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            }
        }
        if (obj != null) {
            try {
                Thread.currentThread().interrupt();
            } catch (Throwable e2) {
                throw new RuntimeException(e2);
            }
        }
    }

    private synchronized ByteBuffer m5237b(ByteBuffer byteBuffer) throws SSLException {
        this.f4046e.compact();
        this.f4050i = this.f4051j.wrap(byteBuffer, this.f4046e);
        this.f4046e.flip();
        return this.f4046e;
    }

    private int m5238c(ByteBuffer byteBuffer) throws SSLException {
        if (this.f4045d.hasRemaining()) {
            return m5235a(this.f4045d, byteBuffer);
        }
        if (!this.f4045d.hasRemaining()) {
            this.f4045d.clear();
        }
        if (this.f4047f.hasRemaining()) {
            m5240e();
            int a = m5235a(this.f4045d, byteBuffer);
            if (a > 0) {
                return a;
            }
        }
        return 0;
    }

    private synchronized void m5239d() throws IOException {
        if (this.f4050i.getHandshakeStatus() != HandshakeStatus.NOT_HANDSHAKING) {
            if (!this.f4044c.isEmpty()) {
                Iterator it = this.f4044c.iterator();
                while (it.hasNext()) {
                    Future future = (Future) it.next();
                    if (future.isDone()) {
                        it.remove();
                    } else if (m5245b()) {
                        m5236a(future);
                    }
                }
            }
            if (this.f4050i.getHandshakeStatus() == HandshakeStatus.NEED_UNWRAP) {
                if (!m5245b() || this.f4052l == Status.BUFFER_UNDERFLOW) {
                    this.f4047f.compact();
                    if (this.f4048g.read(this.f4047f) == -1) {
                        throw new IOException("connection closed unexpectedly by peer");
                    }
                    this.f4047f.flip();
                }
                this.f4045d.compact();
                m5240e();
                if (this.f4050i.getHandshakeStatus() == HandshakeStatus.FINISHED) {
                    m5243a(this.f4051j.getSession());
                }
            }
            m5246c();
            if (!f4042k && this.f4050i.getHandshakeStatus() == HandshakeStatus.NOT_HANDSHAKING) {
                throw new AssertionError();
            } else if (this.f4044c.isEmpty() || this.f4050i.getHandshakeStatus() == HandshakeStatus.NEED_WRAP) {
                this.f4048g.write(m5237b(f4041a));
                if (this.f4050i.getHandshakeStatus() == HandshakeStatus.FINISHED) {
                    m5243a(this.f4051j.getSession());
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.nio.ByteBuffer m5240e() throws javax.net.ssl.SSLException {
        /*
        r4 = this;
        monitor-enter(r4);
    L_0x0001:
        r0 = r4.f4045d;	 Catch:{ all -> 0x003c }
        r0 = r0.remaining();	 Catch:{ all -> 0x003c }
        r1 = r4.f4051j;	 Catch:{ all -> 0x003c }
        r2 = r4.f4047f;	 Catch:{ all -> 0x003c }
        r3 = r4.f4045d;	 Catch:{ all -> 0x003c }
        r1 = r1.unwrap(r2, r3);	 Catch:{ all -> 0x003c }
        r4.f4050i = r1;	 Catch:{ all -> 0x003c }
        r1 = r4.f4050i;	 Catch:{ all -> 0x003c }
        r1 = r1.getStatus();	 Catch:{ all -> 0x003c }
        r4.f4052l = r1;	 Catch:{ all -> 0x003c }
        r1 = r4.f4052l;	 Catch:{ all -> 0x003c }
        r2 = javax.net.ssl.SSLEngineResult.Status.OK;	 Catch:{ all -> 0x003c }
        if (r1 != r2) goto L_0x0033;
    L_0x0021:
        r1 = r4.f4045d;	 Catch:{ all -> 0x003c }
        r1 = r1.remaining();	 Catch:{ all -> 0x003c }
        if (r0 != r1) goto L_0x0001;
    L_0x0029:
        r0 = r4.f4050i;	 Catch:{ all -> 0x003c }
        r0 = r0.getHandshakeStatus();	 Catch:{ all -> 0x003c }
        r1 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP;	 Catch:{ all -> 0x003c }
        if (r0 == r1) goto L_0x0001;
    L_0x0033:
        r0 = r4.f4045d;	 Catch:{ all -> 0x003c }
        r0.flip();	 Catch:{ all -> 0x003c }
        r0 = r4.f4045d;	 Catch:{ all -> 0x003c }
        monitor-exit(r4);
        return r0;
    L_0x003c:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.a.b.e():java.nio.ByteBuffer");
    }

    private boolean m5241f() {
        HandshakeStatus handshakeStatus = this.f4050i.getHandshakeStatus();
        return handshakeStatus == HandshakeStatus.FINISHED || handshakeStatus == HandshakeStatus.NOT_HANDSHAKING;
    }

    public int m5242a(ByteBuffer byteBuffer) throws SSLException {
        return m5238c(byteBuffer);
    }

    protected void m5243a(SSLSession sSLSession) {
        int applicationBufferSize = sSLSession.getApplicationBufferSize();
        int packetBufferSize = sSLSession.getPacketBufferSize();
        if (this.f4045d == null) {
            this.f4045d = ByteBuffer.allocate(applicationBufferSize);
            this.f4046e = ByteBuffer.allocate(packetBufferSize);
            this.f4047f = ByteBuffer.allocate(packetBufferSize);
        } else {
            if (this.f4045d.capacity() != applicationBufferSize) {
                this.f4045d = ByteBuffer.allocate(applicationBufferSize);
            }
            if (this.f4046e.capacity() != packetBufferSize) {
                this.f4046e = ByteBuffer.allocate(packetBufferSize);
            }
            if (this.f4047f.capacity() != packetBufferSize) {
                this.f4047f = ByteBuffer.allocate(packetBufferSize);
            }
        }
        this.f4045d.rewind();
        this.f4045d.flip();
        this.f4047f.rewind();
        this.f4047f.flip();
        this.f4046e.rewind();
        this.f4046e.flip();
    }

    public boolean m5244a() {
        return this.f4045d.hasRemaining() || (this.f4047f.hasRemaining() && this.f4050i.getStatus() != Status.BUFFER_UNDERFLOW);
    }

    public boolean m5245b() {
        return this.f4048g.isBlocking();
    }

    protected void m5246c() {
        while (true) {
            Runnable delegatedTask = this.f4051j.getDelegatedTask();
            if (delegatedTask != null) {
                this.f4044c.add(this.f4043b.submit(delegatedTask));
            } else {
                return;
            }
        }
    }

    public void close() throws IOException {
        this.f4051j.closeOutbound();
        this.f4051j.getSession().invalidate();
        if (this.f4048g.isOpen()) {
            this.f4048g.write(m5237b(f4041a));
        }
        this.f4048g.close();
    }

    public boolean isOpen() {
        return this.f4048g.isOpen();
    }

    public int read(ByteBuffer byteBuffer) throws IOException {
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        if (!m5241f()) {
            if (m5245b()) {
                while (!m5241f()) {
                    m5239d();
                }
            } else {
                m5239d();
                if (!m5241f()) {
                    return 0;
                }
            }
        }
        int c = m5238c(byteBuffer);
        if (c != 0) {
            return c;
        }
        if (f4042k || this.f4045d.position() == 0) {
            this.f4045d.clear();
            if (this.f4047f.hasRemaining()) {
                this.f4047f.compact();
            } else {
                this.f4047f.clear();
            }
            if (((m5245b() && this.f4047f.position() == 0) || this.f4052l == Status.BUFFER_UNDERFLOW) && this.f4048g.read(this.f4047f) == -1) {
                return -1;
            }
            this.f4047f.flip();
            m5240e();
            c = m5235a(this.f4045d, byteBuffer);
            return (c == 0 && m5245b()) ? read(byteBuffer) : c;
        } else {
            throw new AssertionError();
        }
    }

    public int write(ByteBuffer byteBuffer) throws IOException {
        if (m5241f()) {
            return this.f4048g.write(m5237b(byteBuffer));
        }
        m5239d();
        return 0;
    }
}
