package org.p039a.p069b;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import org.p039a.WebSocket.WebSocket;
import org.p039a.p070c.IncompleteHandshakeException;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p070c.InvalidHandshakeException;
import org.p039a.p070c.LimitExedeedException;
import org.p039a.p071d.Framedata;
import org.p039a.p072e.ClientHandshake;
import org.p039a.p072e.ClientHandshakeBuilder;
import org.p039a.p072e.HandshakeBuilder;
import org.p039a.p072e.HandshakeImpl1Client;
import org.p039a.p072e.HandshakeImpl1Server;
import org.p039a.p072e.Handshakedata;
import org.p039a.p072e.ServerHandshake;
import org.p039a.p072e.ServerHandshakeBuilder;
import org.p039a.p073f.Charsetfunctions;

/* renamed from: org.a.b.a */
public abstract class Draft {
    public static int f4023a;
    public static int f4024b;
    public static final byte[] f4025c;
    protected WebSocket f4026d;

    /* renamed from: org.a.b.a.a */
    public enum Draft {
        NONE,
        ONEWAY,
        TWOWAY
    }

    /* renamed from: org.a.b.a.b */
    public enum Draft {
        MATCHED,
        NOT_MATCHED
    }

    static {
        f4023a = 1000;
        f4024b = 64;
        f4025c = Charsetfunctions.m5315a("<policy-file-request/>\u0000");
    }

    public Draft() {
        this.f4026d = null;
    }

    public static ByteBuffer m5168a(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        int i = 48;
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            allocate.put(b);
            if (i == 13 && b == 10) {
                allocate.limit(allocate.position() - 2);
                allocate.position(0);
                return allocate;
            }
            i = b;
        }
        byteBuffer.position(byteBuffer.position() - allocate.position());
        return null;
    }

    public static HandshakeBuilder m5169a(ByteBuffer byteBuffer, WebSocket webSocket) throws InvalidHandshakeException, IncompleteHandshakeException {
        String b = Draft.m5170b(byteBuffer);
        if (b == null) {
            throw new IncompleteHandshakeException(byteBuffer.capacity() + 128);
        }
        String[] split = b.split(" ", 3);
        if (split.length != 3) {
            throw new InvalidHandshakeException();
        }
        HandshakeBuilder handshakeImpl1Server;
        if (webSocket == WebSocket.CLIENT) {
            handshakeImpl1Server = new HandshakeImpl1Server();
            ServerHandshakeBuilder serverHandshakeBuilder = (ServerHandshakeBuilder) handshakeImpl1Server;
            serverHandshakeBuilder.m5297a(Short.parseShort(split[1]));
            serverHandshakeBuilder.m5296a(split[2]);
        } else {
            handshakeImpl1Server = new HandshakeImpl1Client();
            handshakeImpl1Server.m5286a(split[1]);
        }
        b = Draft.m5170b(byteBuffer);
        while (b != null && b.length() > 0) {
            String[] split2 = b.split(":", 2);
            if (split2.length != 2) {
                throw new InvalidHandshakeException("not an http header");
            }
            handshakeImpl1Server.m5284a(split2[0], split2[1].replaceFirst("^ +", ""));
            b = Draft.m5170b(byteBuffer);
        }
        if (b != null) {
            return handshakeImpl1Server;
        }
        throw new IncompleteHandshakeException();
    }

    public static String m5170b(ByteBuffer byteBuffer) {
        ByteBuffer a = Draft.m5168a(byteBuffer);
        return a == null ? null : Charsetfunctions.m5314a(a.array(), 0, a.limit());
    }

    public int m5171a(int i) throws LimitExedeedException, InvalidDataException {
        if (i >= 0) {
            return i;
        }
        throw new InvalidDataException(1002, "Negative count");
    }

    public abstract ByteBuffer m5172a(Framedata framedata);

    public abstract List<Framedata> m5173a(String str, boolean z);

    public abstract List<Framedata> m5174a(ByteBuffer byteBuffer, boolean z);

    public List<ByteBuffer> m5175a(Handshakedata handshakedata, WebSocket webSocket) {
        return m5176a(handshakedata, webSocket, true);
    }

    public List<ByteBuffer> m5176a(Handshakedata handshakedata, WebSocket webSocket, boolean z) {
        StringBuilder stringBuilder = new StringBuilder(100);
        if (handshakedata instanceof ClientHandshake) {
            stringBuilder.append("GET ");
            stringBuilder.append(((ClientHandshake) handshakedata).m5283a());
            stringBuilder.append(" HTTP/1.1");
        } else if (handshakedata instanceof ServerHandshake) {
            stringBuilder.append("HTTP/1.1 101 " + ((ServerHandshake) handshakedata).m5295a());
        } else {
            throw new RuntimeException("unknow role");
        }
        stringBuilder.append("\r\n");
        Iterator b = handshakedata.m5280b();
        while (b.hasNext()) {
            String str = (String) b.next();
            String b2 = handshakedata.m5279b(str);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(b2);
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\n");
        byte[] b3 = Charsetfunctions.m5316b(stringBuilder.toString());
        byte[] c = z ? handshakedata.m5282c() : null;
        ByteBuffer allocate = ByteBuffer.allocate((c == null ? 0 : c.length) + b3.length);
        allocate.put(b3);
        if (c != null) {
            allocate.put(c);
        }
        allocate.flip();
        return Collections.singletonList(allocate);
    }

    public abstract Draft m5177a(ClientHandshake clientHandshake) throws InvalidHandshakeException;

    public abstract Draft m5178a(ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidHandshakeException;

    public abstract ClientHandshakeBuilder m5179a(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException;

    public abstract HandshakeBuilder m5180a(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException;

    public abstract void m5181a();

    public void m5182a(WebSocket webSocket) {
        this.f4026d = webSocket;
    }

    protected boolean m5183a(Handshakedata handshakedata) {
        return handshakedata.m5279b(HttpHeaders.UPGRADE).equalsIgnoreCase(WebsocketTransport.TRANSPORT_NAME) && handshakedata.m5279b(HTTP.CONN_DIRECTIVE).toLowerCase(Locale.ENGLISH).contains("upgrade");
    }

    public abstract Draft m5184b();

    public abstract List<Framedata> m5185c(ByteBuffer byteBuffer) throws InvalidDataException;

    public abstract Draft m5186c();

    public Handshakedata m5187d(ByteBuffer byteBuffer) throws InvalidHandshakeException {
        return Draft.m5169a(byteBuffer, this.f4026d);
    }
}
