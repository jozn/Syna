package io.socket;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import org.p074b.JSONObject;

public class SocketIO {
    private IOCallback callback;
    private IOConnection connection;
    private Properties headers;
    private String namespace;
    private URL url;

    public SocketIO() {
        this.headers = new Properties();
    }

    public SocketIO(String str) throws MalformedURLException {
        this.headers = new Properties();
        if (str == null) {
            throw new RuntimeException("url may not be null.");
        }
        setAndConnect(new URL(str), null);
    }

    public SocketIO(String str, IOCallback iOCallback) throws MalformedURLException {
        this.headers = new Properties();
        connect(str, iOCallback);
    }

    public SocketIO(String str, Properties properties) throws MalformedURLException {
        this.headers = new Properties();
        if (str == null) {
            throw new RuntimeException("url may not be null.");
        }
        if (properties != null) {
            this.headers = properties;
        }
        setAndConnect(new URL(str), null);
    }

    public SocketIO(URL url) {
        this.headers = new Properties();
        setAndConnect(url, null);
    }

    public SocketIO(URL url, IOCallback iOCallback) {
        this.headers = new Properties();
        if (!setAndConnect(url, iOCallback)) {
            throw new RuntimeException("url and callback may not be null.");
        }
    }

    private boolean setAndConnect(URL url, IOCallback iOCallback) {
        if (this.connection != null) {
            throw new RuntimeException("You can connect your SocketIO instance only once. Use a fresh instance instead.");
        } else if (this.url != null && url != null) {
            return false;
        } else {
            if (this.callback != null && iOCallback != null) {
                return false;
            }
            if (url != null) {
                this.url = url;
            }
            if (iOCallback != null) {
                this.callback = iOCallback;
            }
            if (this.callback == null || this.url == null) {
                return false;
            }
            String stringBuilder = new StringBuilder(String.valueOf(this.url.getProtocol())).append("://").append(this.url.getAuthority()).toString();
            this.namespace = this.url.getPath();
            if (this.namespace.equals("/")) {
                this.namespace = "";
            }
            this.connection = IOConnection.register(stringBuilder, this);
            return true;
        }
    }

    public static void setDefaultSSLSocketFactory(SSLContext sSLContext) {
        IOConnection.setSslContext(sSLContext);
    }

    public SocketIO addHeader(String str, String str2) {
        if (this.connection != null) {
            throw new RuntimeException("You may only set headers before connecting.\n Try to use new SocketIO().addHeader(key, value).connect(host, callback) instead of SocketIO(host, callback).addHeader(key, value)");
        }
        this.headers.setProperty(str, str2);
        return this;
    }

    public void connect(IOCallback iOCallback) {
        if (!setAndConnect(null, iOCallback)) {
            if (iOCallback == null) {
                throw new RuntimeException("callback may not be null.");
            } else if (this.url == null) {
                throw new RuntimeException("connect(IOCallback) can only be invoked after SocketIO(String) or SocketIO(URL)");
            }
        }
    }

    public void connect(String str, IOCallback iOCallback) throws MalformedURLException {
        if (!setAndConnect(new URL(str), iOCallback)) {
            if (str == null || iOCallback == null) {
                throw new RuntimeException("url and callback may not be null.");
            }
            throw new RuntimeException("connect(String, IOCallback) can only be invoked after SocketIO()");
        }
    }

    public void connect(URL url, IOCallback iOCallback) {
        if (!setAndConnect(url, iOCallback)) {
            if (url == null || iOCallback == null) {
                throw new RuntimeException("url and callback may not be null.");
            }
            throw new RuntimeException("connect(URL, IOCallback) can only be invoked after SocketIO()");
        }
    }

    public void disconnect() {
        this.connection.unregister(this);
    }

    public void emit(String str, IOAcknowledge iOAcknowledge, Object... objArr) {
        this.connection.emit(this, str, iOAcknowledge, objArr);
    }

    public void emit(String str, Object... objArr) {
        this.connection.emit(this, str, null, objArr);
    }

    public IOCallback getCallback() {
        return this.callback;
    }

    public String getHeader(String str) {
        return this.headers.contains(str) ? this.headers.getProperty(str) : null;
    }

    public Properties getHeaders() {
        return this.headers;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getTransport() {
        IOTransport transport = this.connection.getTransport();
        return transport != null ? transport.getName() : null;
    }

    public boolean isConnected() {
        return this.connection != null && this.connection.isConnected();
    }

    public void reconnect() {
        this.connection.reconnect();
    }

    public void send(IOAcknowledge iOAcknowledge, String str) {
        this.connection.send(this, iOAcknowledge, str);
    }

    public void send(IOAcknowledge iOAcknowledge, JSONObject jSONObject) {
        this.connection.send(this, iOAcknowledge, jSONObject);
    }

    public void send(String str) {
        this.connection.send(this, null, str);
    }

    public void send(JSONObject jSONObject) {
        this.connection.send(this, null, jSONObject);
    }

    void setHeaders(Properties properties) {
        this.headers = properties;
    }
}
