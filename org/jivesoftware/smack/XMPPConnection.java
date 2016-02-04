package org.jivesoftware.smack;

import org.jivesoftware.smack.PacketCollector.Configuration;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;

public interface XMPPConnection {

    public enum FromMode {
        UNCHANGED,
        OMITTED,
        USER
    }

    void addAsyncStanzaListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addConnectionListener(ConnectionListener connectionListener);

    void addOneTimeSyncCallback(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addPacketInterceptor(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addPacketSendingListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    void addSyncStanzaListener(StanzaListener stanzaListener, StanzaFilter stanzaFilter);

    PacketCollector createPacketCollector(Configuration configuration);

    PacketCollector createPacketCollector(StanzaFilter stanzaFilter);

    PacketCollector createPacketCollectorAndSend(StanzaFilter stanzaFilter, Stanza stanza) throws NotConnectedException;

    PacketCollector createPacketCollectorAndSend(IQ iq) throws NotConnectedException;

    int getConnectionCounter();

    <F extends ExtensionElement> F getFeature(String str, String str2);

    long getLastStanzaReceived();

    long getPacketReplyTimeout();

    int getPort();

    String getServiceName();

    String getUser();

    boolean hasFeature(String str, String str2);

    boolean isAnonymous();

    boolean isAuthenticated();

    boolean isConnected();

    boolean isSecureConnection();

    IQRequestHandler registerIQRequestHandler(IQRequestHandler iQRequestHandler);

    void removeConnectionListener(ConnectionListener connectionListener);

    void removePacketCollector(PacketCollector packetCollector);

    void removePacketInterceptor(StanzaListener stanzaListener);

    boolean removeSyncStanzaListener(StanzaListener stanzaListener);

    void send(PlainStreamElement plainStreamElement) throws NotConnectedException;

    void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener) throws NotConnectedException;

    void sendIqWithResponseCallback(IQ iq, StanzaListener stanzaListener, ExceptionCallback exceptionCallback) throws NotConnectedException;

    void sendStanza(Stanza stanza) throws NotConnectedException;

    void setPacketReplyTimeout(long j);

    IQRequestHandler unregisterIQRequestHandler(IQRequestHandler iQRequestHandler);
}
