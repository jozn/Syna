package org.p039a.p040a;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.NotYetConnectedException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import org.apache.http.protocol.HTTP;
import org.p039a.SocketChannelIOHelper;
import org.p039a.WebSocket;
import org.p039a.WebSocketAdapter;
import org.p039a.WebSocketFactory;
import org.p039a.WebSocketImpl;
import org.p039a.WrappedByteChannel;
import org.p039a.p069b.Draft;
import org.p039a.p069b.Draft_10;
import org.p039a.p070c.InvalidHandshakeException;
import org.p039a.p072e.ClientHandshakeBuilder;
import org.p039a.p072e.HandshakeImpl1Client;
import org.p039a.p072e.Handshakedata;
import org.p039a.p072e.ServerHandshake;

/* renamed from: org.a.a.d */
public abstract class WebSocketClient extends WebSocketAdapter implements Runnable {
    static final /* synthetic */ boolean $assertionsDisabled;
    private SocketChannel channel;
    private CountDownLatch closeLatch;
    private WebSocketImpl conn;
    private CountDownLatch connectLatch;
    private Draft draft;
    private Map<String, String> headers;
    private InetSocketAddress proxyAddress;
    private Thread readthread;
    private int timeout;
    protected URI uri;
    private ByteChannel wrappedchannel;
    private Thread writethread;
    private WebSocketClient wsfactory;

    /* renamed from: org.a.a.d.b */
    public interface WebSocketClient extends WebSocketFactory {
        ByteChannel m5161a(SocketChannel socketChannel, SelectionKey selectionKey, String str, int i) throws IOException;
    }

    /* renamed from: org.a.a.d.a */
    public class WebSocketClient extends AbstractClientProxyChannel {
        final /* synthetic */ WebSocketClient f4014b;

        public WebSocketClient(WebSocketClient webSocketClient, ByteChannel byteChannel) {
            this.f4014b = webSocketClient;
            super(byteChannel);
        }

        public String m5167c() {
            StringBuilder stringBuilder = new StringBuilder();
            String host = this.f4014b.uri.getHost();
            stringBuilder.append("CONNECT ");
            stringBuilder.append(host);
            stringBuilder.append(":");
            stringBuilder.append(this.f4014b.getPort());
            stringBuilder.append(" HTTP/1.1\n");
            stringBuilder.append("Host: ");
            stringBuilder.append(host);
            stringBuilder.append("\n");
            return stringBuilder.toString();
        }
    }

    /* renamed from: org.a.a.d.c */
    private class WebSocketClient implements Runnable {
        final /* synthetic */ WebSocketClient f4015a;

        private WebSocketClient(WebSocketClient webSocketClient) {
            this.f4015a = webSocketClient;
        }

        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");
            while (!Thread.interrupted()) {
                try {
                    SocketChannelIOHelper.m5249a(this.f4015a.conn, this.f4015a.wrappedchannel);
                } catch (IOException e) {
                    this.f4015a.conn.m5336b();
                    return;
                } catch (InterruptedException e2) {
                    return;
                }
            }
        }
    }

    static {
        $assertionsDisabled = !WebSocketClient.class.desiredAssertionStatus();
    }

    public WebSocketClient(URI uri) {
        this(uri, new Draft_10());
    }

    public WebSocketClient(URI uri, Draft draft) {
        this(uri, draft, null, 0);
    }

    public WebSocketClient(URI uri, Draft draft, Map<String, String> map, int i) {
        this.uri = null;
        this.conn = null;
        this.channel = null;
        this.wrappedchannel = null;
        this.connectLatch = new CountDownLatch(1);
        this.closeLatch = new CountDownLatch(1);
        this.timeout = 0;
        this.wsfactory = new DefaultWebSocketClientFactory(this);
        this.proxyAddress = null;
        if (uri == null) {
            throw new IllegalArgumentException();
        } else if (draft == null) {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        } else {
            this.uri = uri;
            this.draft = draft;
            this.headers = map;
            this.timeout = i;
            try {
                this.channel = SelectorProvider.provider().openSocketChannel();
                this.channel.configureBlocking(true);
            } catch (Exception e) {
                this.channel = null;
                onWebsocketError(null, e);
            }
            if (this.channel == null) {
                this.conn = (WebSocketImpl) this.wsfactory.m5160a(this, draft, null);
                this.conn.m5327a(-1, "Failed to create or configure SocketChannel.");
                return;
            }
            this.conn = (WebSocketImpl) this.wsfactory.m5160a(this, draft, this.channel.socket());
        }
    }

    private int getPort() {
        int port = this.uri.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.uri.getScheme();
        if (scheme.equals("wss")) {
            return 443;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unkonow scheme" + scheme);
    }

    private final void interruptableRun() {
        if (this.channel != null) {
            try {
                String hostName;
                int port;
                if (this.proxyAddress != null) {
                    hostName = this.proxyAddress.getHostName();
                    port = this.proxyAddress.getPort();
                } else {
                    hostName = this.uri.getHost();
                    port = getPort();
                }
                this.channel.connect(new InetSocketAddress(hostName, port));
                WebSocketImpl webSocketImpl = this.conn;
                ByteChannel createProxyChannel = createProxyChannel(this.wsfactory.m5161a(this.channel, null, hostName, port));
                this.wrappedchannel = createProxyChannel;
                webSocketImpl.f4108e = createProxyChannel;
                this.timeout = 0;
                sendHandshake();
                this.readthread = new Thread(new WebSocketClient());
                this.readthread.start();
                ByteBuffer allocate = ByteBuffer.allocate(WebSocketImpl.f4103a);
                while (this.channel.isOpen()) {
                    try {
                        if (SocketChannelIOHelper.m5250a(allocate, this.conn, this.wrappedchannel)) {
                            this.conn.m5331a(allocate);
                        } else {
                            this.conn.m5336b();
                        }
                        if (this.wrappedchannel instanceof WrappedByteChannel) {
                            WrappedByteChannel wrappedByteChannel = (WrappedByteChannel) this.wrappedchannel;
                            if (wrappedByteChannel.m5154a()) {
                                while (SocketChannelIOHelper.m5251a(allocate, this.conn, wrappedByteChannel)) {
                                    this.conn.m5331a(allocate);
                                }
                                this.conn.m5331a(allocate);
                            } else {
                                continue;
                            }
                        }
                    } catch (CancelledKeyException e) {
                        this.conn.m5336b();
                        return;
                    } catch (IOException e2) {
                        this.conn.m5336b();
                        return;
                    } catch (Exception e3) {
                        onError(e3);
                        this.conn.m5337b(1006, e3.getMessage());
                        return;
                    }
                }
            } catch (Exception e32) {
                onWebsocketError(null, e32);
            } catch (Exception e322) {
                onWebsocketError(this.conn, e322);
                this.conn.m5337b(-1, e322.getMessage());
            }
        }
    }

    private void sendHandshake() throws InvalidHandshakeException {
        String path = this.uri.getPath();
        String query = this.uri.getQuery();
        if (path == null || path.length() == 0) {
            path = "/";
        }
        if (query != null) {
            path = new StringBuilder(String.valueOf(path)).append("?").append(query).toString();
        }
        int port = getPort();
        query = new StringBuilder(String.valueOf(this.uri.getHost())).append(port != 80 ? ":" + port : "").toString();
        ClientHandshakeBuilder handshakeImpl1Client = new HandshakeImpl1Client();
        handshakeImpl1Client.m5294a(path);
        handshakeImpl1Client.m5287a(HTTP.TARGET_HOST, query);
        if (this.headers != null) {
            for (Entry entry : this.headers.entrySet()) {
                handshakeImpl1Client.m5287a((String) entry.getKey(), (String) entry.getValue());
            }
        }
        this.conn.m5334a(handshakeImpl1Client);
    }

    public void close() {
        if (this.writethread != null) {
            this.conn.m5326a(1000);
        }
    }

    public void closeBlocking() throws InterruptedException {
        close();
        this.closeLatch.await();
    }

    public void connect() {
        if (this.writethread != null) {
            throw new IllegalStateException("WebSocketClient objects are not reuseable");
        }
        this.writethread = new Thread(this);
        this.writethread.start();
    }

    public boolean connectBlocking() throws InterruptedException {
        connect();
        this.connectLatch.await();
        return this.conn.m5340c();
    }

    public ByteChannel createProxyChannel(ByteChannel byteChannel) {
        return this.proxyAddress != null ? new WebSocketClient(this, byteChannel) : byteChannel;
    }

    public WebSocket getConnection() {
        return this.conn;
    }

    public Draft getDraft() {
        return this.draft;
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket webSocket) {
        return this.channel != null ? (InetSocketAddress) this.channel.socket().getLocalSocketAddress() : null;
    }

    public WebSocket.WebSocket getReadyState() {
        return this.conn.m5343f();
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket webSocket) {
        return this.channel != null ? (InetSocketAddress) this.channel.socket().getLocalSocketAddress() : null;
    }

    public URI getURI() {
        return this.uri;
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsfactory;
    }

    public abstract void onClose(int i, String str, boolean z);

    public void onCloseInitiated(int i, String str) {
    }

    public void onClosing(int i, String str, boolean z) {
    }

    public abstract void onError(Exception exception);

    public abstract void onMessage(String str);

    public void onMessage(ByteBuffer byteBuffer) {
    }

    public abstract void onOpen(ServerHandshake serverHandshake);

    public final void onWebsocketClose(WebSocket webSocket, int i, String str, boolean z) {
        this.connectLatch.countDown();
        this.closeLatch.countDown();
        if (this.readthread != null) {
            this.readthread.interrupt();
        }
        onClose(i, str, z);
    }

    public void onWebsocketCloseInitiated(WebSocket webSocket, int i, String str) {
        onCloseInitiated(i, str);
    }

    public void onWebsocketClosing(WebSocket webSocket, int i, String str, boolean z) {
        onClosing(i, str, z);
    }

    public final void onWebsocketError(WebSocket webSocket, Exception exception) {
        onError(exception);
    }

    public final void onWebsocketMessage(WebSocket webSocket, String str) {
        onMessage(str);
    }

    public final void onWebsocketMessage(WebSocket webSocket, ByteBuffer byteBuffer) {
        onMessage(byteBuffer);
    }

    public final void onWebsocketOpen(WebSocket webSocket, Handshakedata handshakedata) {
        this.connectLatch.countDown();
        onOpen((ServerHandshake) handshakedata);
    }

    public final void onWriteDemand(WebSocket webSocket) {
    }

    public void run() {
        if (this.writethread == null) {
            this.writethread = Thread.currentThread();
        }
        interruptableRun();
        if (!$assertionsDisabled && this.channel.isOpen()) {
            throw new AssertionError();
        }
    }

    public void send(String str) throws NotYetConnectedException {
        this.conn.m5330a(str);
    }

    public void send(byte[] bArr) throws NotYetConnectedException {
        this.conn.m5335a(bArr);
    }

    public void setProxy(InetSocketAddress inetSocketAddress) {
        this.proxyAddress = inetSocketAddress;
    }

    public final void setWebSocketFactory(WebSocketClient webSocketClient) {
        this.wsfactory = webSocketClient;
    }
}
