package org.p039a.p069b;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import org.jivesoftware.smack.util.StringUtils;
import org.p039a.WebSocket.WebSocket;
import org.p039a.p069b.Draft.Draft;
import org.p039a.p070c.IncompleteHandshakeException;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p070c.InvalidFrameException;
import org.p039a.p070c.InvalidHandshakeException;
import org.p039a.p071d.CloseFrameBuilder;
import org.p039a.p071d.Framedata;
import org.p039a.p072e.ClientHandshake;
import org.p039a.p072e.ClientHandshakeBuilder;
import org.p039a.p072e.HandshakeBuilder;
import org.p039a.p072e.Handshakedata;
import org.p039a.p072e.ServerHandshake;
import org.p039a.p072e.ServerHandshakeBuilder;

/* renamed from: org.a.b.e */
public class Draft_76 extends Draft_75 {
    private static final byte[] f4038i;
    private boolean f4039h;
    private final Random f4040j;

    static {
        byte[] bArr = new byte[2];
        bArr[0] = (byte) -1;
        f4038i = bArr;
    }

    public Draft_76() {
        this.f4039h = false;
        this.f4040j = new Random();
    }

    private static byte[] m5223a(String str) throws InvalidHandshakeException {
        try {
            long parseLong = Long.parseLong(str.replaceAll("[^0-9]", ""));
            long length = (long) (str.split(" ").length - 1);
            if (length == 0) {
                throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key2/)");
            }
            parseLong = new Long(parseLong / length).longValue();
            return new byte[]{(byte) ((int) (parseLong >> 24)), (byte) ((int) ((parseLong << 8) >> 24)), (byte) ((int) ((parseLong << 16) >> 24)), (byte) ((int) ((parseLong << 24) >> 24))};
        } catch (NumberFormatException e) {
            throw new InvalidHandshakeException("invalid Sec-WebSocket-Key (/key1/ or /key2/)");
        }
    }

    public static byte[] m5224a(String str, String str2, byte[] bArr) throws InvalidHandshakeException {
        byte[] a = Draft_76.m5223a(str);
        byte[] a2 = Draft_76.m5223a(str2);
        try {
            return MessageDigest.getInstance(StringUtils.MD5).digest(new byte[]{a[0], a[1], a[2], a[3], a2[0], a2[1], a2[2], a2[3], bArr[0], bArr[1], bArr[2], bArr[3], bArr[4], bArr[5], bArr[6], bArr[7]});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static String m5225e() {
        Random random = new Random();
        long nextInt = (long) (random.nextInt(12) + 1);
        String l = Long.toString(((long) (random.nextInt(Math.abs(new Long(4294967295L / nextInt).intValue())) + 1)) * nextInt);
        int nextInt2 = random.nextInt(12) + 1;
        for (int i = 0; i < nextInt2; i++) {
            int abs = Math.abs(random.nextInt(l.length()));
            char nextInt3 = (char) (random.nextInt(95) + 33);
            if (nextInt3 >= '0' && nextInt3 <= '9') {
                nextInt3 = (char) (nextInt3 - 15);
            }
            l = abs;
        }
        String str = l;
        for (int i2 = 0; ((long) i2) < nextInt; i2++) {
            str = Math.abs(random.nextInt(str.length() - 1) + 1);
        }
        return str;
    }

    public ByteBuffer m5226a(Framedata framedata) {
        return framedata.m5255f() == Framedata.Framedata.CLOSING ? ByteBuffer.wrap(f4038i) : super.m5209a(framedata);
    }

    public Draft m5227a(ClientHandshake clientHandshake) {
        return (clientHandshake.m5279b(HttpHeaders.UPGRADE).equals("WebSocket") && clientHandshake.m5279b(HTTP.CONN_DIRECTIVE).contains(HttpHeaders.UPGRADE) && clientHandshake.m5279b("Sec-WebSocket-Key1").length() > 0 && !clientHandshake.m5279b("Sec-WebSocket-Key2").isEmpty() && clientHandshake.m5281c("Origin")) ? Draft.MATCHED : Draft.NOT_MATCHED;
    }

    public Draft m5228a(ClientHandshake clientHandshake, ServerHandshake serverHandshake) {
        if (this.f4039h) {
            return Draft.NOT_MATCHED;
        }
        try {
            if (!serverHandshake.m5279b("Sec-WebSocket-Origin").equals(clientHandshake.m5279b("Origin")) || !m5183a((Handshakedata) serverHandshake)) {
                return Draft.NOT_MATCHED;
            }
            byte[] c = serverHandshake.m5282c();
            if (c != null && c.length != 0) {
                return Arrays.equals(c, Draft_76.m5224a(clientHandshake.m5279b("Sec-WebSocket-Key1"), clientHandshake.m5279b("Sec-WebSocket-Key2"), clientHandshake.m5282c())) ? Draft.MATCHED : Draft.NOT_MATCHED;
            } else {
                throw new IncompleteHandshakeException();
            }
        } catch (Throwable e) {
            throw new RuntimeException("bad handshakerequest", e);
        }
    }

    public ClientHandshakeBuilder m5229a(ClientHandshakeBuilder clientHandshakeBuilder) {
        clientHandshakeBuilder.m5284a(HttpHeaders.UPGRADE, "WebSocket");
        clientHandshakeBuilder.m5284a(HTTP.CONN_DIRECTIVE, HttpHeaders.UPGRADE);
        clientHandshakeBuilder.m5284a("Sec-WebSocket-Key1", Draft_76.m5225e());
        clientHandshakeBuilder.m5284a("Sec-WebSocket-Key2", Draft_76.m5225e());
        if (!clientHandshakeBuilder.m5281c("Origin")) {
            clientHandshakeBuilder.m5284a("Origin", "random" + this.f4040j.nextInt());
        }
        byte[] bArr = new byte[8];
        this.f4040j.nextBytes(bArr);
        clientHandshakeBuilder.m5285a(bArr);
        return clientHandshakeBuilder;
    }

    public HandshakeBuilder m5230a(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.m5296a("WebSocket Protocol Handshake");
        serverHandshakeBuilder.m5284a(HttpHeaders.UPGRADE, "WebSocket");
        serverHandshakeBuilder.m5284a(HTTP.CONN_DIRECTIVE, clientHandshake.m5279b(HTTP.CONN_DIRECTIVE));
        serverHandshakeBuilder.m5284a("Sec-WebSocket-Origin", clientHandshake.m5279b("Origin"));
        serverHandshakeBuilder.m5284a("Sec-WebSocket-Location", "ws://" + clientHandshake.m5279b(HTTP.TARGET_HOST) + clientHandshake.m5283a());
        String b = clientHandshake.m5279b("Sec-WebSocket-Key1");
        String b2 = clientHandshake.m5279b("Sec-WebSocket-Key2");
        byte[] c = clientHandshake.m5282c();
        if (b == null || b2 == null || c == null || c.length != 8) {
            throw new InvalidHandshakeException("Bad keys");
        }
        serverHandshakeBuilder.m5285a(Draft_76.m5224a(b, b2, c));
        return serverHandshakeBuilder;
    }

    public Draft m5231b() {
        return Draft.ONEWAY;
    }

    public List<Framedata> m5232c(ByteBuffer byteBuffer) throws InvalidDataException {
        byteBuffer.mark();
        List<Framedata> e = super.m5221e(byteBuffer);
        if (e == null) {
            byteBuffer.reset();
            e = this.f;
            this.e = true;
            if (this.g == null) {
                this.g = ByteBuffer.allocate(2);
                if (byteBuffer.remaining() > this.g.remaining()) {
                    throw new InvalidFrameException();
                }
                this.g.put(byteBuffer);
                if (this.g.hasRemaining()) {
                    this.f = new LinkedList();
                } else if (Arrays.equals(this.g.array(), f4038i)) {
                    e.add(new CloseFrameBuilder(1000));
                } else {
                    throw new InvalidFrameException();
                }
            }
            throw new InvalidFrameException();
        }
        return e;
    }

    public Draft m5233c() {
        return new Draft_76();
    }

    public Handshakedata m5234d(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        Handshakedata a = Draft.m5169a(byteBuffer, this.d);
        if ((a.m5281c("Sec-WebSocket-Key1") || this.d == WebSocket.CLIENT) && !a.m5281c("Sec-WebSocket-Version")) {
            byte[] bArr = new byte[(this.d == WebSocket.SERVER ? 8 : 16)];
            try {
                byteBuffer.get(bArr);
                a.m5285a(bArr);
            } catch (BufferUnderflowException e) {
                throw new IncompleteHandshakeException(byteBuffer.capacity() + 16);
            }
        }
        return a;
    }
}
