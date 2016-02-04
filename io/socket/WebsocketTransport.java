package io.socket;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import org.p039a.p040a.DefaultSSLWebSocketClientFactory;
import org.p039a.p040a.WebSocketClient;
import org.p039a.p072e.ServerHandshake;

class WebsocketTransport extends WebSocketClient implements IOTransport {
    private static final Pattern PATTERN_HTTP;
    public static final String TRANSPORT_NAME = "websocket";
    private IOConnection connection;

    static {
        PATTERN_HTTP = Pattern.compile("^http");
    }

    public WebsocketTransport(URI uri, IOConnection iOConnection) {
        super(uri);
        this.connection = iOConnection;
        SSLContext sslContext = IOConnection.getSslContext();
        if ("wss".equals(uri.getScheme()) && sslContext != null) {
            setWebSocketFactory(new DefaultSSLWebSocketClientFactory(sslContext));
        }
    }

    public static IOTransport create(URL url, IOConnection iOConnection) {
        return new WebsocketTransport(URI.create(new StringBuilder(String.valueOf(PATTERN_HTTP.matcher(url.toString()).replaceFirst("ws"))).append(IOConnection.SOCKET_IO_1).append(TRANSPORT_NAME).append("/").append(iOConnection.getSessionId()).toString()), iOConnection);
    }

    public boolean canSendBulk() {
        return false;
    }

    public void disconnect() {
        try {
            close();
        } catch (Exception e) {
            this.connection.transportError(e);
        }
    }

    public String getName() {
        return TRANSPORT_NAME;
    }

    public void invalidate() {
        this.connection = null;
    }

    public void onClose(int i, String str, boolean z) {
        if (this.connection != null) {
            this.connection.transportDisconnected();
        }
    }

    public void onError(Exception exception) {
    }

    public void onMessage(String str) {
        if (this.connection != null) {
            this.connection.transportMessage(str);
        }
    }

    public void onOpen(ServerHandshake serverHandshake) {
        if (this.connection != null) {
            this.connection.transportConnected();
        }
    }

    public void sendBulk(String[] strArr) throws IOException {
        throw new RuntimeException("Cannot send Bulk!");
    }
}
