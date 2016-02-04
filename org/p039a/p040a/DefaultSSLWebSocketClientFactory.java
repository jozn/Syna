package org.p039a.p040a;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import org.p039a.SSLSocketChannel2;
import org.p039a.WebSocket;
import org.p039a.WebSocketAdapter;
import org.p039a.WebSocketImpl;
import org.p039a.p040a.WebSocketClient.WebSocketClient;
import org.p039a.p069b.Draft;

/* renamed from: org.a.a.b */
public class DefaultSSLWebSocketClientFactory implements WebSocketClient {
    protected SSLContext f4011a;
    protected ExecutorService f4012b;

    public DefaultSSLWebSocketClientFactory(SSLContext sSLContext) {
        this(sSLContext, Executors.newSingleThreadScheduledExecutor());
    }

    public DefaultSSLWebSocketClientFactory(SSLContext sSLContext, ExecutorService executorService) {
        if (sSLContext == null || executorService == null) {
            throw new IllegalArgumentException();
        }
        this.f4011a = sSLContext;
        this.f4012b = executorService;
    }

    public ByteChannel m5162a(SocketChannel socketChannel, SelectionKey selectionKey, String str, int i) throws IOException {
        SSLEngine createSSLEngine = this.f4011a.createSSLEngine(str, i);
        createSSLEngine.setUseClientMode(true);
        return new SSLSocketChannel2(socketChannel, createSSLEngine, this.f4012b, selectionKey);
    }

    public /* synthetic */ WebSocket m5163a(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket) {
        return m5164b(webSocketAdapter, draft, socket);
    }

    public WebSocketImpl m5164b(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket) {
        return new WebSocketImpl(webSocketAdapter, draft, socket);
    }
}
