package org.p039a.p069b;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;
import org.p039a.WebSocket.WebSocket;
import org.p039a.p069b.Draft.Draft;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p070c.InvalidFrameException;
import org.p039a.p070c.InvalidHandshakeException;
import org.p039a.p070c.LimitExedeedException;
import org.p039a.p070c.NotSendableException;
import org.p039a.p071d.CloseFrameBuilder;
import org.p039a.p071d.FrameBuilder;
import org.p039a.p071d.Framedata;
import org.p039a.p071d.FramedataImpl1;
import org.p039a.p072e.ClientHandshake;
import org.p039a.p072e.ClientHandshakeBuilder;
import org.p039a.p072e.HandshakeBuilder;
import org.p039a.p072e.Handshakedata;
import org.p039a.p072e.ServerHandshake;
import org.p039a.p072e.ServerHandshakeBuilder;
import org.p039a.p073f.Base64;
import org.p039a.p073f.Charsetfunctions;

/* renamed from: org.a.b.b */
public class Draft_10 extends Draft {
    static final /* synthetic */ boolean f4029e;
    private ByteBuffer f4030f;
    private Framedata f4031g;
    private final Random f4032h;

    /* renamed from: org.a.b.b.a */
    private class Draft_10 extends Throwable {
        final /* synthetic */ Draft_10 f4027a;
        private int f4028b;

        public Draft_10(Draft_10 draft_10, int i) {
            this.f4027a = draft_10;
            this.f4028b = i;
        }

        public int m5188a() {
            return this.f4028b;
        }
    }

    static {
        f4029e = !Draft_10.class.desiredAssertionStatus();
    }

    public Draft_10() {
        this.f4031g = null;
        this.f4032h = new Random();
    }

    private byte m5189a(Framedata.Framedata framedata) {
        if (framedata == Framedata.Framedata.CONTINUOUS) {
            return (byte) 0;
        }
        if (framedata == Framedata.Framedata.TEXT) {
            return (byte) 1;
        }
        if (framedata == Framedata.Framedata.BINARY) {
            return (byte) 2;
        }
        if (framedata == Framedata.Framedata.CLOSING) {
            return (byte) 8;
        }
        if (framedata == Framedata.Framedata.PING) {
            return (byte) 9;
        }
        if (framedata == Framedata.Framedata.PONG) {
            return (byte) 10;
        }
        throw new RuntimeException("Don't know how to handle " + framedata.toString());
    }

    private String m5190a(String str) {
        String str2 = str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        try {
            return Base64.m5303a(MessageDigest.getInstance("SHA1").digest(str2.getBytes()));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private Framedata.Framedata m5191a(byte b) throws InvalidFrameException {
        switch (b) {
            case VideoSize.QCIF /*0*/:
                return Framedata.Framedata.CONTINUOUS;
            case VideoSize.CIF /*1*/:
                return Framedata.Framedata.TEXT;
            case VideoSize.HVGA /*2*/:
                return Framedata.Framedata.BINARY;
            case Version.API08_FROYO_22 /*8*/:
                return Framedata.Framedata.CLOSING;
            case Version.API09_GINGERBREAD_23 /*9*/:
                return Framedata.Framedata.PING;
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                return Framedata.Framedata.PONG;
            default:
                throw new InvalidFrameException("unknow optcode " + ((short) b));
        }
    }

    private byte[] m5192a(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((int) (j >>> (i2 - (i3 * 8))));
        }
        return bArr;
    }

    public static int m5193b(Handshakedata handshakedata) {
        int i = -1;
        String b = handshakedata.m5279b("Sec-WebSocket-Version");
        if (b.length() > 0) {
            try {
                i = new Integer(b.trim()).intValue();
            } catch (NumberFormatException e) {
            }
        }
        return i;
    }

    public ByteBuffer m5194a(Framedata framedata) {
        int i = -128;
        int i2 = 0;
        ByteBuffer c = framedata.m5252c();
        int i3 = this.d == WebSocket.CLIENT ? 1 : 0;
        int i4 = c.remaining() <= 125 ? 1 : c.remaining() <= InBandBytestreamManager.MAXIMUM_BLOCK_SIZE ? 2 : 8;
        ByteBuffer allocate = ByteBuffer.allocate(((i3 != 0 ? 4 : 0) + ((i4 > 1 ? i4 + 1 : i4) + 1)) + c.remaining());
        allocate.put((byte) (((byte) (framedata.m5253d() ? -128 : 0)) | m5189a(framedata.m5255f())));
        byte[] a = m5192a((long) c.remaining(), i4);
        if (f4029e || a.length == i4) {
            if (i4 == 1) {
                byte b = a[0];
                if (i3 == 0) {
                    i = 0;
                }
                allocate.put((byte) (b | i));
            } else if (i4 == 2) {
                if (i3 == 0) {
                    i = 0;
                }
                allocate.put((byte) (i | 126));
                allocate.put(a);
            } else if (i4 == 8) {
                if (i3 == 0) {
                    i = 0;
                }
                allocate.put((byte) (i | 127));
                allocate.put(a);
            } else {
                throw new RuntimeException("Size representation not supported/specified");
            }
            if (i3 != 0) {
                ByteBuffer allocate2 = ByteBuffer.allocate(4);
                allocate2.putInt(this.f4032h.nextInt());
                allocate.put(allocate2.array());
                while (i2 < c.limit()) {
                    allocate.put((byte) (c.get() ^ allocate2.get(i2 % 4)));
                    i2++;
                }
            } else {
                allocate.put(c);
            }
            if (f4029e || allocate.remaining() == 0) {
                allocate.flip();
                return allocate;
            }
            throw new AssertionError(allocate.remaining());
        }
        throw new AssertionError();
    }

    public List<Framedata> m5195a(String str, boolean z) {
        FrameBuilder framedataImpl1 = new FramedataImpl1();
        try {
            framedataImpl1.m5258a(ByteBuffer.wrap(Charsetfunctions.m5315a(str)));
            framedataImpl1.m5260a(true);
            framedataImpl1.m5259a(Framedata.Framedata.TEXT);
            framedataImpl1.m5261b(z);
            return Collections.singletonList(framedataImpl1);
        } catch (Throwable e) {
            throw new NotSendableException(e);
        }
    }

    public List<Framedata> m5196a(ByteBuffer byteBuffer, boolean z) {
        FrameBuilder framedataImpl1 = new FramedataImpl1();
        try {
            framedataImpl1.m5258a(byteBuffer);
            framedataImpl1.m5260a(true);
            framedataImpl1.m5259a(Framedata.Framedata.BINARY);
            framedataImpl1.m5261b(z);
            return Collections.singletonList(framedataImpl1);
        } catch (Throwable e) {
            throw new NotSendableException(e);
        }
    }

    public Draft m5197a(ClientHandshake clientHandshake) throws InvalidHandshakeException {
        int b = Draft_10.m5193b(clientHandshake);
        return (b == 7 || b == 8) ? m5183a((Handshakedata) clientHandshake) ? Draft.MATCHED : Draft.NOT_MATCHED : Draft.NOT_MATCHED;
    }

    public Draft m5198a(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException {
        if (!clientHandshake.m5281c("Sec-WebSocket-Key") || !serverHandshake.m5281c("Sec-WebSocket-Accept")) {
            return Draft.NOT_MATCHED;
        }
        return m5190a(clientHandshake.m5279b("Sec-WebSocket-Key")).equals(serverHandshake.m5279b("Sec-WebSocket-Accept")) ? Draft.MATCHED : Draft.NOT_MATCHED;
    }

    public ClientHandshakeBuilder m5199a(ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.m5284a(HttpHeaders.UPGRADE, WebsocketTransport.TRANSPORT_NAME);
        clientHandshakeBuilder.m5284a(HTTP.CONN_DIRECTIVE, HttpHeaders.UPGRADE);
        clientHandshakeBuilder.m5284a("Sec-WebSocket-Version", "8");
        byte[] bArr = new byte[16];
        this.f4032h.nextBytes(bArr);
        clientHandshakeBuilder.m5284a("Sec-WebSocket-Key", Base64.m5303a(bArr));
        return clientHandshakeBuilder;
    }

    public HandshakeBuilder m5200a(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.m5284a(HttpHeaders.UPGRADE, WebsocketTransport.TRANSPORT_NAME);
        serverHandshakeBuilder.m5284a(HTTP.CONN_DIRECTIVE, clientHandshake.m5279b(HTTP.CONN_DIRECTIVE));
        serverHandshakeBuilder.m5296a("Switching Protocols");
        String b = clientHandshake.m5279b("Sec-WebSocket-Key");
        if (b == null) {
            throw new InvalidHandshakeException("missing Sec-WebSocket-Key");
        }
        serverHandshakeBuilder.m5284a("Sec-WebSocket-Accept", m5190a(b));
        return serverHandshakeBuilder;
    }

    public void m5201a() {
        this.f4030f = null;
    }

    public Draft m5202b() {
        return Draft.TWOWAY;
    }

    public List<Framedata> m5203c(ByteBuffer byteBuffer) throws LimitExedeedException, InvalidDataException {
        List<Framedata> linkedList = new LinkedList();
        if (this.f4030f != null) {
            try {
                byteBuffer.mark();
                int remaining = byteBuffer.remaining();
                int remaining2 = this.f4030f.remaining();
                if (remaining2 > remaining) {
                    this.f4030f.put(byteBuffer.array(), byteBuffer.position(), remaining);
                    byteBuffer.position(remaining + byteBuffer.position());
                    return Collections.emptyList();
                }
                this.f4030f.put(byteBuffer.array(), byteBuffer.position(), remaining2);
                byteBuffer.position(byteBuffer.position() + remaining2);
                linkedList.add(m5205e((ByteBuffer) this.f4030f.duplicate().position(0)));
                this.f4030f = null;
            } catch (Draft_10 e) {
                this.f4030f.limit();
                r0 = ByteBuffer.allocate(m5171a(e.m5188a()));
                if (f4029e || r0.limit() > this.f4030f.limit()) {
                    ByteBuffer allocate;
                    this.f4030f.rewind();
                    allocate.put(this.f4030f);
                    this.f4030f = allocate;
                    return m5203c(byteBuffer);
                }
                throw new AssertionError();
            }
        }
        while (byteBuffer.hasRemaining()) {
            byteBuffer.mark();
            try {
                linkedList.add(m5205e(byteBuffer));
            } catch (Draft_10 e2) {
                byteBuffer.reset();
                this.f4030f = ByteBuffer.allocate(m5171a(e2.m5188a()));
                this.f4030f.put(byteBuffer);
            }
        }
        return linkedList;
    }

    public Draft m5204c() {
        return new Draft_10();
    }

    public Framedata m5205e(ByteBuffer byteBuffer) throws Draft_10, InvalidDataException {
        int i = 2;
        int i2 = 0;
        int remaining = byteBuffer.remaining();
        if (remaining < 2) {
            throw new Draft_10(this, 2);
        }
        byte b = byteBuffer.get();
        boolean z = (b >> 8) != 0;
        byte b2 = (byte) ((b & 127) >> 4);
        if (b2 != null) {
            throw new InvalidFrameException("bad rsv " + b2);
        }
        byte b3 = byteBuffer.get();
        int i3 = (b3 & -128) != 0 ? 1 : 0;
        int i4 = (byte) (b3 & 127);
        Framedata.Framedata a = m5191a((byte) (b & 15));
        if (z || !(a == Framedata.Framedata.PING || a == Framedata.Framedata.PONG || a == Framedata.Framedata.CLOSING)) {
            int i5;
            if (i4 < 0 || i4 > 125) {
                if (a == Framedata.Framedata.PING || a == Framedata.Framedata.PONG || a == Framedata.Framedata.CLOSING) {
                    throw new InvalidFrameException("more than 125 octets");
                } else if (i4 == 126) {
                    if (remaining < 4) {
                        throw new Draft_10(this, 4);
                    }
                    byte[] bArr = new byte[3];
                    bArr[1] = byteBuffer.get();
                    bArr[2] = byteBuffer.get();
                    i4 = new BigInteger(bArr).intValue();
                    i = 4;
                } else if (remaining < 10) {
                    throw new Draft_10(this, 10);
                } else {
                    byte[] bArr2 = new byte[8];
                    for (i5 = 0; i5 < 8; i5++) {
                        bArr2[i5] = byteBuffer.get();
                    }
                    long longValue = new BigInteger(bArr2).longValue();
                    if (longValue > 2147483647L) {
                        throw new LimitExedeedException("Payloadsize is to big...");
                    }
                    i = 10;
                    i4 = (int) longValue;
                }
            }
            i5 = ((i3 != 0 ? 4 : 0) + i) + i4;
            if (remaining < i5) {
                throw new Draft_10(this, i5);
            }
            Framedata closeFrameBuilder;
            ByteBuffer allocate = ByteBuffer.allocate(m5171a(i4));
            if (i3 != 0) {
                byte[] bArr3 = new byte[4];
                byteBuffer.get(bArr3);
                while (i2 < i4) {
                    allocate.put((byte) (byteBuffer.get() ^ bArr3[i2 % 4]));
                    i2++;
                }
            } else {
                allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
                byteBuffer.position(byteBuffer.position() + allocate.limit());
            }
            if (a == Framedata.Framedata.CLOSING) {
                closeFrameBuilder = new CloseFrameBuilder();
            } else {
                closeFrameBuilder = new FramedataImpl1();
                closeFrameBuilder.m5260a(z);
                closeFrameBuilder.m5259a(a);
            }
            allocate.flip();
            closeFrameBuilder.m5258a(allocate);
            return closeFrameBuilder;
        }
        throw new InvalidFrameException("control frames may no be fragmented");
    }
}
