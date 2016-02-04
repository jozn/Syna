package org.p039a;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.p039a.WebSocket.WebSocket;
import org.p039a.p069b.Draft;
import org.p039a.p069b.Draft_10;
import org.p039a.p069b.Draft_17;
import org.p039a.p069b.Draft_75;
import org.p039a.p069b.Draft_76;
import org.p039a.p070c.IncompleteHandshakeException;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p070c.InvalidHandshakeException;
import org.p039a.p070c.WebsocketNotConnectedException;
import org.p039a.p071d.CloseFrame;
import org.p039a.p071d.CloseFrameBuilder;
import org.p039a.p071d.Framedata.Framedata;
import org.p039a.p072e.ClientHandshake;
import org.p039a.p072e.ClientHandshakeBuilder;
import org.p039a.p072e.Handshakedata;
import org.p039a.p072e.ServerHandshake;
import org.p039a.p073f.Charsetfunctions;

/* renamed from: org.a.g */
public class WebSocketImpl implements WebSocket {
    public static int f4103a;
    public static boolean f4104b;
    public static final List<Draft> f4105c;
    static final /* synthetic */ boolean f4106h;
    public SelectionKey f4107d;
    public ByteChannel f4108e;
    public final BlockingQueue<ByteBuffer> f4109f;
    public final BlockingQueue<ByteBuffer> f4110g;
    private volatile boolean f4111i;
    private WebSocket f4112j;
    private final WebSocketListener f4113k;
    private List<Draft> f4114l;
    private Draft f4115m;
    private WebSocket f4116n;
    private Framedata f4117o;
    private ByteBuffer f4118p;
    private ClientHandshake f4119q;
    private String f4120r;
    private Integer f4121s;
    private Boolean f4122t;

    static {
        f4106h = !WebSocketImpl.class.desiredAssertionStatus();
        f4103a = 16384;
        f4104b = false;
        f4105c = new ArrayList(4);
        f4105c.add(new Draft_17());
        f4105c.add(new Draft_10());
        f4105c.add(new Draft_76());
        f4105c.add(new Draft_75());
    }

    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft) {
        this.f4111i = false;
        this.f4112j = WebSocket.NOT_YET_CONNECTED;
        this.f4115m = null;
        this.f4117o = null;
        this.f4119q = null;
        this.f4120r = null;
        this.f4121s = null;
        this.f4122t = null;
        if (webSocketListener == null || (draft == null && this.f4116n == WebSocket.SERVER)) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.f4109f = new LinkedBlockingQueue();
        this.f4110g = new LinkedBlockingQueue();
        this.f4113k = webSocketListener;
        this.f4116n = WebSocket.CLIENT;
        if (draft != null) {
            this.f4115m = draft.m5186c();
        }
    }

    @Deprecated
    public WebSocketImpl(WebSocketListener webSocketListener, Draft draft, Socket socket) {
        this(webSocketListener, draft);
    }

    private void m5317a(Collection<org.p039a.p071d.Framedata> collection) {
        if (m5340c()) {
            for (org.p039a.p071d.Framedata a : collection) {
                m5333a(a);
            }
            return;
        }
        throw new WebsocketNotConnectedException();
    }

    private void m5318a(List<ByteBuffer> list) {
        for (ByteBuffer f : list) {
            m5324f(f);
        }
    }

    private void m5319a(Handshakedata handshakedata) {
        if (f4104b) {
            System.out.println("open using draft: " + this.f4115m.getClass().getSimpleName());
        }
        this.f4112j = WebSocket.OPEN;
        try {
            this.f4113k.onWebsocketOpen(this, handshakedata);
        } catch (Exception e) {
            this.f4113k.onWebsocketError(this, e);
        }
    }

    private void m5320c(int i, String str, boolean z) {
        if (this.f4112j != WebSocket.CLOSING && this.f4112j != WebSocket.CLOSED) {
            if (this.f4112j == WebSocket.OPEN) {
                if (i != 1006) {
                    if (this.f4115m.m5184b() != Draft.Draft.NONE) {
                        if (!z) {
                            try {
                                this.f4113k.onWebsocketCloseInitiated(this, i, str);
                            } catch (Exception e) {
                                this.f4113k.onWebsocketError(this, e);
                            }
                        }
                        try {
                            m5333a(new CloseFrameBuilder(i, str));
                        } catch (Exception e2) {
                            this.f4113k.onWebsocketError(this, e2);
                            m5338b(1006, "generated frame is invalid", false);
                        }
                    }
                    m5338b(i, str, z);
                } else if (f4106h || !z) {
                    this.f4112j = WebSocket.CLOSING;
                    m5338b(i, str, false);
                    return;
                } else {
                    throw new AssertionError();
                }
            } else if (i != -3) {
                m5338b(-1, str, false);
            } else if (f4106h || z) {
                m5338b(-3, str, true);
            } else {
                throw new AssertionError();
            }
            if (i == 1002) {
                m5338b(i, str, z);
            }
            this.f4112j = WebSocket.CLOSING;
            this.f4118p = null;
        }
    }

    private boolean m5321c(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        if (this.f4118p == null) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.f4118p.remaining() < byteBuffer.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(this.f4118p.capacity() + byteBuffer.remaining());
                this.f4118p.flip();
                allocate.put(this.f4118p);
                this.f4118p = allocate;
            }
            this.f4118p.put(byteBuffer);
            this.f4118p.flip();
            byteBuffer2 = this.f4118p;
        }
        byteBuffer2.mark();
        try {
            if (this.f4115m == null && m5323e(byteBuffer2) == Draft.Draft.MATCHED) {
                m5324f(ByteBuffer.wrap(Charsetfunctions.m5315a(this.f4113k.getFlashPolicy(this))));
                m5327a(-3, "");
                return false;
            }
            try {
                Handshakedata d;
                if (this.f4116n != WebSocket.SERVER) {
                    if (this.f4116n == WebSocket.CLIENT) {
                        this.f4115m.m5182a(this.f4116n);
                        d = this.f4115m.m5187d(byteBuffer2);
                        if (d instanceof ServerHandshake) {
                            d = (ServerHandshake) d;
                            if (this.f4115m.m5178a(this.f4119q, (ServerHandshake) d) == Draft.Draft.MATCHED) {
                                try {
                                    this.f4113k.onWebsocketHandshakeReceivedAsClient(this, this.f4119q, d);
                                    m5319a(d);
                                    return true;
                                } catch (InvalidDataException e) {
                                    m5338b(e.m5248a(), e.getMessage(), false);
                                    return false;
                                } catch (Exception e2) {
                                    this.f4113k.onWebsocketError(this, e2);
                                    m5338b(-1, e2.getMessage(), false);
                                    return false;
                                }
                            }
                            m5327a(1002, "draft " + this.f4115m + " refuses handshake");
                        } else {
                            m5338b(1002, "Wwrong http function", false);
                            return false;
                        }
                    }
                    return false;
                } else if (this.f4115m == null) {
                    for (Draft c : this.f4114l) {
                        Draft c2 = c.m5186c();
                        try {
                            c2.m5182a(this.f4116n);
                            byteBuffer2.reset();
                            d = c2.m5187d(byteBuffer2);
                            if (d instanceof ClientHandshake) {
                                d = (ClientHandshake) d;
                                if (c2.m5177a((ClientHandshake) d) == Draft.Draft.MATCHED) {
                                    try {
                                        m5318a(c2.m5175a(c2.m5180a((ClientHandshake) d, this.f4113k.onWebsocketHandshakeReceivedAsServer(this, c2, d)), this.f4116n));
                                        this.f4115m = c2;
                                        m5319a(d);
                                        return true;
                                    } catch (InvalidDataException e3) {
                                        m5338b(e3.m5248a(), e3.getMessage(), false);
                                        return false;
                                    } catch (Exception e22) {
                                        this.f4113k.onWebsocketError(this, e22);
                                        m5338b(-1, e22.getMessage(), false);
                                        return false;
                                    }
                                }
                                continue;
                            } else {
                                m5338b(1002, "wrong http function", false);
                                return false;
                            }
                        } catch (InvalidHandshakeException e4) {
                        }
                    }
                    if (this.f4115m == null) {
                        m5327a(1002, "no draft matches");
                    }
                    return false;
                } else {
                    d = this.f4115m.m5187d(byteBuffer2);
                    if (d instanceof ClientHandshake) {
                        d = (ClientHandshake) d;
                        if (this.f4115m.m5177a((ClientHandshake) d) == Draft.Draft.MATCHED) {
                            m5319a(d);
                            return true;
                        }
                        m5327a(1002, "the handshake did finaly not match");
                        return false;
                    }
                    m5338b(1002, "wrong http function", false);
                    return false;
                }
            } catch (InvalidDataException e32) {
                m5332a(e32);
            }
        } catch (IncompleteHandshakeException e5) {
            IncompleteHandshakeException incompleteHandshakeException = e5;
            if (this.f4118p == null) {
                byteBuffer2.reset();
                int a = incompleteHandshakeException.m5247a();
                if (a == 0) {
                    a = byteBuffer2.capacity() + 16;
                } else if (!f4106h && incompleteHandshakeException.m5247a() < byteBuffer2.remaining()) {
                    throw new AssertionError();
                }
                this.f4118p = ByteBuffer.allocate(a);
                this.f4118p.put(byteBuffer);
            } else {
                this.f4118p.position(this.f4118p.limit());
                this.f4118p.limit(this.f4118p.capacity());
            }
        }
    }

    private void m5322d(ByteBuffer byteBuffer) {
        if (!this.f4111i) {
            for (org.p039a.p071d.Framedata framedata : this.f4115m.m5185c(byteBuffer)) {
                if (f4104b) {
                    System.out.println("matched frame: " + framedata);
                }
                if (!this.f4111i) {
                    Framedata f = framedata.m5255f();
                    boolean d = framedata.m5253d();
                    if (f == Framedata.CLOSING) {
                        int a;
                        String b;
                        String str = "";
                        if (framedata instanceof CloseFrame) {
                            CloseFrame closeFrame = (CloseFrame) framedata;
                            a = closeFrame.m5256a();
                            b = closeFrame.m5257b();
                        } else {
                            b = str;
                            a = 1005;
                        }
                        if (this.f4112j == WebSocket.CLOSING) {
                            m5328a(a, b, true);
                        } else if (this.f4115m.m5184b() == Draft.Draft.TWOWAY) {
                            m5320c(a, b, true);
                        } else {
                            m5338b(a, b, false);
                        }
                    } else if (f == Framedata.PING) {
                        this.f4113k.onWebsocketPing(this, framedata);
                    } else if (f == Framedata.PONG) {
                        this.f4113k.onWebsocketPong(this, framedata);
                    } else if (!d || f == Framedata.CONTINUOUS) {
                        if (f != Framedata.CONTINUOUS) {
                            if (this.f4117o != null) {
                                throw new InvalidDataException(1002, "Previous continuous frame sequence not completed.");
                            }
                            this.f4117o = f;
                        } else if (d) {
                            if (this.f4117o == null) {
                                throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                            }
                            this.f4117o = null;
                        } else if (this.f4117o == null) {
                            throw new InvalidDataException(1002, "Continuous frame sequence was not started.");
                        }
                        try {
                            this.f4113k.onWebsocketMessageFragment(this, framedata);
                        } catch (Exception e) {
                            this.f4113k.onWebsocketError(this, e);
                        }
                    } else if (this.f4117o != null) {
                        throw new InvalidDataException(1002, "Continuous frame sequence not completed.");
                    } else if (f == Framedata.TEXT) {
                        try {
                            this.f4113k.onWebsocketMessage((WebSocket) this, Charsetfunctions.m5313a(framedata.m5252c()));
                        } catch (Exception e2) {
                            try {
                                this.f4113k.onWebsocketError(this, e2);
                            } catch (InvalidDataException e3) {
                                this.f4113k.onWebsocketError(this, e3);
                                m5332a(e3);
                                return;
                            }
                        }
                    } else if (f == Framedata.BINARY) {
                        try {
                            this.f4113k.onWebsocketMessage((WebSocket) this, framedata.m5252c());
                        } catch (Exception e22) {
                            this.f4113k.onWebsocketError(this, e22);
                        }
                    } else {
                        throw new InvalidDataException(1002, "non control or continious frame expected");
                    }
                }
                return;
            }
        }
    }

    private Draft.Draft m5323e(ByteBuffer byteBuffer) throws IncompleteHandshakeException {
        byteBuffer.mark();
        if (byteBuffer.limit() > Draft.f4025c.length) {
            return Draft.Draft.NOT_MATCHED;
        }
        if (byteBuffer.limit() < Draft.f4025c.length) {
            throw new IncompleteHandshakeException(Draft.f4025c.length);
        }
        int i = 0;
        while (byteBuffer.hasRemaining()) {
            if (Draft.f4025c[i] != byteBuffer.get()) {
                byteBuffer.reset();
                return Draft.Draft.NOT_MATCHED;
            }
            i++;
        }
        return Draft.Draft.MATCHED;
    }

    private void m5324f(ByteBuffer byteBuffer) {
        if (f4104b) {
            System.out.println("write(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array())) + "}");
        }
        this.f4109f.add(byteBuffer);
        this.f4113k.onWriteDemand(this);
    }

    public InetSocketAddress m5325a() {
        return this.f4113k.getLocalSocketAddress(this);
    }

    public void m5326a(int i) {
        m5320c(i, "", false);
    }

    public void m5327a(int i, String str) {
        m5320c(i, str, false);
    }

    protected synchronized void m5328a(int i, String str, boolean z) {
        if (this.f4112j != WebSocket.CLOSED) {
            if (this.f4107d != null) {
                this.f4107d.cancel();
            }
            if (this.f4108e != null) {
                try {
                    this.f4108e.close();
                } catch (Exception e) {
                    this.f4113k.onWebsocketError(this, e);
                }
            }
            try {
                this.f4113k.onWebsocketClose(this, i, str, z);
            } catch (Exception e2) {
                this.f4113k.onWebsocketError(this, e2);
            }
            if (this.f4115m != null) {
                this.f4115m.m5181a();
            }
            this.f4119q = null;
            this.f4112j = WebSocket.CLOSED;
            this.f4109f.clear();
        }
    }

    protected void m5329a(int i, boolean z) {
        m5328a(i, "", z);
    }

    public void m5330a(String str) throws WebsocketNotConnectedException {
        if (str == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        m5317a(this.f4115m.m5173a(str, this.f4116n == WebSocket.CLIENT));
    }

    public void m5331a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining() && !this.f4111i) {
            if (f4104b) {
                System.out.println("process(" + byteBuffer.remaining() + "): {" + (byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining())) + "}");
            }
            if (this.f4112j == WebSocket.OPEN) {
                m5322d(byteBuffer);
            } else if (m5321c(byteBuffer)) {
                m5322d(byteBuffer);
            }
            if (!f4106h && !m5341d() && !m5342e() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
        }
    }

    public void m5332a(InvalidDataException invalidDataException) {
        m5320c(invalidDataException.m5248a(), invalidDataException.getMessage(), false);
    }

    public void m5333a(org.p039a.p071d.Framedata framedata) {
        if (f4104b) {
            System.out.println("send frame: " + framedata);
        }
        m5324f(this.f4115m.m5172a(framedata));
    }

    public void m5334a(ClientHandshakeBuilder clientHandshakeBuilder) throws InvalidHandshakeException {
        if (f4106h || this.f4112j != WebSocket.CONNECTING) {
            this.f4119q = this.f4115m.m5179a(clientHandshakeBuilder);
            try {
                this.f4113k.onWebsocketHandshakeSentAsClient(this, this.f4119q);
                m5318a(this.f4115m.m5175a(this.f4119q, this.f4116n));
                return;
            } catch (InvalidDataException e) {
                throw new InvalidHandshakeException("Handshake data rejected by client.");
            } catch (Exception e2) {
                this.f4113k.onWebsocketError(this, e2);
                throw new InvalidHandshakeException("rejected because of" + e2);
            }
        }
        throw new AssertionError("shall only be called once");
    }

    public void m5335a(byte[] bArr) throws IllegalArgumentException, WebsocketNotConnectedException {
        m5339b(ByteBuffer.wrap(bArr));
    }

    public void m5336b() {
        if (m5343f() == WebSocket.NOT_YET_CONNECTED) {
            m5329a(-1, true);
        } else if (this.f4111i) {
            m5328a(this.f4121s.intValue(), this.f4120r, this.f4122t.booleanValue());
        } else if (this.f4115m.m5184b() == Draft.Draft.NONE) {
            m5329a(1000, true);
        } else if (this.f4115m.m5184b() != Draft.Draft.ONEWAY) {
            m5329a(1006, true);
        } else if (this.f4116n == WebSocket.SERVER) {
            m5329a(1006, true);
        } else {
            m5329a(1000, true);
        }
    }

    public void m5337b(int i, String str) {
        m5328a(i, str, false);
    }

    protected synchronized void m5338b(int i, String str, boolean z) {
        if (!this.f4111i) {
            this.f4121s = Integer.valueOf(i);
            this.f4120r = str;
            this.f4122t = Boolean.valueOf(z);
            this.f4111i = true;
            this.f4113k.onWriteDemand(this);
            try {
                this.f4113k.onWebsocketClosing(this, i, str, z);
            } catch (Exception e) {
                this.f4113k.onWebsocketError(this, e);
            }
            if (this.f4115m != null) {
                this.f4115m.m5181a();
            }
            this.f4119q = null;
        }
    }

    public void m5339b(ByteBuffer byteBuffer) throws IllegalArgumentException, WebsocketNotConnectedException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
        }
        m5317a(this.f4115m.m5174a(byteBuffer, this.f4116n == WebSocket.CLIENT));
    }

    public boolean m5340c() {
        if (f4106h || this.f4112j != WebSocket.OPEN || !this.f4111i) {
            return this.f4112j == WebSocket.OPEN;
        } else {
            throw new AssertionError();
        }
    }

    public boolean m5341d() {
        return this.f4112j == WebSocket.CLOSING;
    }

    public boolean m5342e() {
        return this.f4111i;
    }

    public WebSocket m5343f() {
        return this.f4112j;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }
}
