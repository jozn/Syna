package org.p039a;

import java.net.InetSocketAddress;
import org.p039a.p071d.Framedata;

/* renamed from: org.a.d */
public interface WebSocket {

    /* renamed from: org.a.d.a */
    public enum WebSocket {
        NOT_YET_CONNECTED,
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    /* renamed from: org.a.d.b */
    public enum WebSocket {
        CLIENT,
        SERVER
    }

    InetSocketAddress m5277a();

    void m5278a(Framedata framedata);
}
