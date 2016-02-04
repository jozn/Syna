package io.socket;

import org.p074b.JSONObject;

public interface IOCallback {
    void on(String str, IOAcknowledge iOAcknowledge, Object... objArr);

    void onConnect();

    void onDisconnect();

    void onError(SocketIOException socketIOException);

    void onMessage(String str, IOAcknowledge iOAcknowledge);

    void onMessage(JSONObject jSONObject, IOAcknowledge iOAcknowledge);
}
