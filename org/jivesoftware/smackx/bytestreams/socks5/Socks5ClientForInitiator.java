package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost;

class Socks5ClientForInitiator extends Socks5Client {
    private XMPPConnection connection;
    private String sessionID;
    private String target;

    public Socks5ClientForInitiator(StreamHost streamHost, String str, XMPPConnection xMPPConnection, String str2, String str3) {
        super(streamHost, str);
        this.connection = xMPPConnection;
        this.sessionID = str2;
        this.target = str3;
    }

    private void activate() throws NoResponseException, XMPPErrorException, NotConnectedException {
        this.connection.createPacketCollectorAndSend(createStreamHostActivation()).nextResultOrThrow();
    }

    private Bytestream createStreamHostActivation() {
        Bytestream bytestream = new Bytestream(this.sessionID);
        bytestream.setMode(null);
        bytestream.setType(Type.set);
        bytestream.setTo(this.streamHost.getJID());
        bytestream.setToActivate(this.target);
        return bytestream;
    }

    public Socket getSocket(int i) throws IOException, InterruptedException, TimeoutException, XMPPException, SmackException {
        Socket socket;
        if (this.streamHost.getJID().equals(this.connection.getUser())) {
            socket = Socks5Proxy.getSocks5Proxy().getSocket(this.digest);
            if (socket == null) {
                throw new SmackException("target is not connected to SOCKS5 proxy");
            }
        }
        socket = super.getSocket(i);
        try {
            activate();
        } catch (XMPPException e) {
            socket.close();
            throw e;
        } catch (NoResponseException e2) {
            socket.close();
            throw e2;
        }
        return socket;
    }
}
