package org.jivesoftware.smackx.pep;

import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.pep.packet.PEPEvent;
import org.jivesoftware.smackx.pep.packet.PEPItem;
import org.jivesoftware.smackx.pep.packet.PEPPubSub;

public class PEPManager {
    private XMPPConnection connection;
    private StanzaFilter packetFilter;
    private StanzaListener packetListener;
    private List<PEPListener> pepListeners;

    /* renamed from: org.jivesoftware.smackx.pep.PEPManager.1 */
    class C02481 implements StanzaListener {
        C02481() {
        }

        public void processPacket(Stanza stanza) {
            Message message = (Message) stanza;
            PEPManager.this.firePEPListeners(message.getFrom(), (PEPEvent) message.getExtension("event", "http://jabber.org/protocol/pubsub#event"));
        }
    }

    public PEPManager(XMPPConnection xMPPConnection) {
        this.pepListeners = new ArrayList();
        this.packetFilter = new StanzaExtensionFilter("event", "http://jabber.org/protocol/pubsub#event");
        this.connection = xMPPConnection;
        init();
    }

    private void firePEPListeners(String str, PEPEvent pEPEvent) {
        synchronized (this.pepListeners) {
            PEPListener[] pEPListenerArr = new PEPListener[this.pepListeners.size()];
            this.pepListeners.toArray(pEPListenerArr);
        }
        for (PEPListener a : pEPListenerArr) {
            a.m5970a(str, pEPEvent);
        }
    }

    private void init() {
        this.packetListener = new C02481();
        this.connection.addSyncStanzaListener(this.packetListener, this.packetFilter);
    }

    public void addPEPListener(PEPListener pEPListener) {
        synchronized (this.pepListeners) {
            if (!this.pepListeners.contains(pEPListener)) {
                this.pepListeners.add(pEPListener);
            }
        }
    }

    public void destroy() {
        if (this.connection != null) {
            this.connection.removeSyncStanzaListener(this.packetListener);
        }
    }

    protected void finalize() throws Throwable {
        destroy();
        super.finalize();
    }

    public void publish(PEPItem pEPItem) throws NotConnectedException {
        Stanza pEPPubSub = new PEPPubSub(pEPItem);
        pEPPubSub.setType(Type.set);
        this.connection.sendStanza(pEPPubSub);
    }

    public void removePEPListener(PEPListener pEPListener) {
        synchronized (this.pepListeners) {
            this.pepListeners.remove(pEPListener);
        }
    }
}
