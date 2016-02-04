package io.socket;

import java.io.IOException;

interface IOTransport {
    boolean canSendBulk();

    void connect();

    void disconnect();

    String getName();

    void invalidate();

    void send(String str) throws Exception;

    void sendBulk(String[] strArr) throws IOException;
}
