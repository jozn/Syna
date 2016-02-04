package org.p039a.p069b;

import org.p039a.p069b.Draft.Draft;
import org.p039a.p070c.InvalidHandshakeException;
import org.p039a.p072e.ClientHandshake;
import org.p039a.p072e.ClientHandshakeBuilder;

/* renamed from: org.a.b.c */
public class Draft_17 extends Draft_10 {
    public Draft m5206a(ClientHandshake clientHandshake) throws InvalidHandshakeException {
        return Draft_10.m5193b(clientHandshake) == 13 ? Draft.MATCHED : Draft.NOT_MATCHED;
    }

    public ClientHandshakeBuilder m5207a(ClientHandshakeBuilder clientHandshakeBuilder) {
        super.m5199a(clientHandshakeBuilder);
        clientHandshakeBuilder.m5284a("Sec-WebSocket-Version", "13");
        return clientHandshakeBuilder;
    }

    public Draft m5208c() {
        return new Draft_17();
    }
}
