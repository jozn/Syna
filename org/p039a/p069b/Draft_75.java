package org.p039a.p069b;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import org.p039a.p069b.Draft.Draft;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p070c.InvalidHandshakeException;
import org.p039a.p070c.NotSendableException;
import org.p039a.p071d.FrameBuilder;
import org.p039a.p071d.Framedata;
import org.p039a.p071d.FramedataImpl1;
import org.p039a.p072e.ClientHandshake;
import org.p039a.p072e.ClientHandshakeBuilder;
import org.p039a.p072e.HandshakeBuilder;
import org.p039a.p072e.Handshakedata;
import org.p039a.p072e.ServerHandshake;
import org.p039a.p072e.ServerHandshakeBuilder;
import org.p039a.p073f.Charsetfunctions;

/* renamed from: org.a.b.d */
public class Draft_75 extends Draft {
    protected boolean f4033e;
    protected List<Framedata> f4034f;
    protected ByteBuffer f4035g;
    private boolean f4036h;
    private final Random f4037i;

    public Draft_75() {
        this.f4033e = false;
        this.f4036h = false;
        this.f4034f = new LinkedList();
        this.f4037i = new Random();
    }

    public ByteBuffer m5209a(Framedata framedata) {
        if (framedata.m5255f() != Framedata.Framedata.TEXT) {
            throw new RuntimeException("only text frames supported");
        }
        ByteBuffer c = framedata.m5252c();
        ByteBuffer allocate = ByteBuffer.allocate(c.remaining() + 2);
        allocate.put((byte) 0);
        c.mark();
        allocate.put(c);
        c.reset();
        allocate.put((byte) -1);
        allocate.flip();
        return allocate;
    }

    public List<Framedata> m5210a(String str, boolean z) {
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

    public List<Framedata> m5211a(ByteBuffer byteBuffer, boolean z) {
        throw new RuntimeException("not yet implemented");
    }

    public Draft m5212a(ClientHandshake clientHandshake) {
        return (clientHandshake.m5281c("Origin") && m5183a((Handshakedata) clientHandshake)) ? Draft.MATCHED : Draft.NOT_MATCHED;
    }

    public Draft m5213a(ClientHandshake clientHandshake, ServerHandshake serverHandshake) {
        return (clientHandshake.m5279b("WebSocket-Origin").equals(serverHandshake.m5279b("Origin")) && m5183a((Handshakedata) serverHandshake)) ? Draft.MATCHED : Draft.NOT_MATCHED;
    }

    public ClientHandshakeBuilder m5214a(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        clientHandshakeBuilder.m5284a(HttpHeaders.UPGRADE, "WebSocket");
        clientHandshakeBuilder.m5284a(HTTP.CONN_DIRECTIVE, HttpHeaders.UPGRADE);
        if (!clientHandshakeBuilder.m5281c("Origin")) {
            clientHandshakeBuilder.m5284a("Origin", "random" + this.f4037i.nextInt());
        }
        return clientHandshakeBuilder;
    }

    public HandshakeBuilder m5215a(ClientHandshake clientHandshake, ServerHandshakeBuilder serverHandshakeBuilder) throws InvalidHandshakeException {
        serverHandshakeBuilder.m5296a("Web Socket Protocol Handshake");
        serverHandshakeBuilder.m5284a(HttpHeaders.UPGRADE, "WebSocket");
        serverHandshakeBuilder.m5284a(HTTP.CONN_DIRECTIVE, clientHandshake.m5279b(HTTP.CONN_DIRECTIVE));
        serverHandshakeBuilder.m5284a("WebSocket-Origin", clientHandshake.m5279b("Origin"));
        serverHandshakeBuilder.m5284a("WebSocket-Location", "ws://" + clientHandshake.m5279b(HTTP.TARGET_HOST) + clientHandshake.m5283a());
        return serverHandshakeBuilder;
    }

    public void m5216a() {
        this.f4033e = false;
        this.f4035g = null;
    }

    public Draft m5217b() {
        return Draft.NONE;
    }

    public List<Framedata> m5218c(ByteBuffer byteBuffer) throws InvalidDataException {
        List<Framedata> e = m5221e(byteBuffer);
        if (e != null) {
            return e;
        }
        throw new InvalidDataException(1002);
    }

    public Draft m5219c() {
        return new Draft_75();
    }

    public ByteBuffer m5220d() {
        return ByteBuffer.allocate(b);
    }

    protected List<Framedata> m5221e(ByteBuffer byteBuffer) throws InvalidDataException {
        while (byteBuffer.hasRemaining()) {
            byte b = byteBuffer.get();
            if (b == null) {
                if (this.f4033e) {
                    return null;
                }
                this.f4033e = true;
            } else if (b == -1) {
                if (!this.f4033e) {
                    return null;
                }
                if (this.f4035g != null) {
                    this.f4035g.flip();
                    FramedataImpl1 framedataImpl1 = new FramedataImpl1();
                    framedataImpl1.m5262a(this.f4035g);
                    framedataImpl1.m5264a(true);
                    framedataImpl1.m5263a(this.f4036h ? Framedata.Framedata.CONTINUOUS : Framedata.Framedata.TEXT);
                    this.f4034f.add(framedataImpl1);
                    this.f4035g = null;
                    byteBuffer.mark();
                }
                this.f4033e = false;
                this.f4036h = false;
            } else if (!this.f4033e) {
                return null;
            } else {
                if (this.f4035g == null) {
                    this.f4035g = m5220d();
                } else if (!this.f4035g.hasRemaining()) {
                    this.f4035g = m5222f(this.f4035g);
                }
                this.f4035g.put(b);
            }
        }
        if (this.f4033e) {
            framedataImpl1 = new FramedataImpl1();
            this.f4035g.flip();
            framedataImpl1.m5262a(this.f4035g);
            framedataImpl1.m5264a(false);
            framedataImpl1.m5263a(this.f4036h ? Framedata.Framedata.CONTINUOUS : Framedata.Framedata.TEXT);
            this.f4036h = true;
            this.f4034f.add(framedataImpl1);
        }
        List<Framedata> list = this.f4034f;
        this.f4034f = new LinkedList();
        this.f4035g = null;
        return list;
    }

    public ByteBuffer m5222f(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.capacity() * 2);
        allocate.put(byteBuffer);
        return allocate;
    }
}
