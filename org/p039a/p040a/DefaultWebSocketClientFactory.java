package org.p039a.p040a;

import java.net.Socket;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import org.p039a.WebSocket;
import org.p039a.WebSocketAdapter;
import org.p039a.WebSocketImpl;
import org.p039a.p040a.WebSocketClient.WebSocketClient;
import org.p039a.p069b.Draft;

/* renamed from: org.a.a.c */
public class DefaultWebSocketClientFactory implements WebSocketClient {
    private final WebSocketClient f4013a;

    public DefaultWebSocketClientFactory(WebSocketClient webSocketClient) {
        this.f4013a = webSocketClient;
    }

    public ByteChannel m5165a(SocketChannel socketChannel, SelectionKey selectionKey, String str, int i) {
        return selectionKey == null ? socketChannel : socketChannel;
    }

    public WebSocket m5166a(WebSocketAdapter webSocketAdapter, Draft draft, Socket socket) {
        return new WebSocketImpl(this.f4013a, draft);
    }
}
