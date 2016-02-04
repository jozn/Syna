package org.p039a;

import org.p039a.p069b.Draft;
import org.p039a.p070c.InvalidDataException;
import org.p039a.p071d.Framedata;
import org.p039a.p071d.FramedataImpl1;
import org.p039a.p072e.ClientHandshake;
import org.p039a.p072e.HandshakeImpl1Server;
import org.p039a.p072e.ServerHandshake;
import org.p039a.p072e.ServerHandshakeBuilder;

/* renamed from: org.a.e */
public abstract class WebSocketAdapter implements WebSocketListener {
    public String getFlashPolicy(WebSocket webSocket) {
        return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + webSocket.m5277a().getPort() + "\" /></cross-domain-policy>\u0000";
    }

    public void onWebsocketHandshakeReceivedAsClient(WebSocket webSocket, ClientHandshake clientHandshake, ServerHandshake serverHandshake) throws InvalidDataException {
    }

    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket webSocket, Draft draft, ClientHandshake clientHandshake) throws InvalidDataException {
        return new HandshakeImpl1Server();
    }

    public void onWebsocketHandshakeSentAsClient(WebSocket webSocket, ClientHandshake clientHandshake) throws InvalidDataException {
    }

    public void onWebsocketMessageFragment(WebSocket webSocket, Framedata framedata) {
    }

    public void onWebsocketPing(WebSocket webSocket, Framedata framedata) {
        Framedata framedataImpl1 = new FramedataImpl1(framedata);
        framedataImpl1.m5263a(Framedata.Framedata.PONG);
        webSocket.m5278a(framedataImpl1);
    }

    public void onWebsocketPong(WebSocket webSocket, Framedata framedata) {
    }
}
