package org.jivesoftware.smackx.carbons;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.carbons.packet.Carbon.Disable;
import org.jivesoftware.smackx.carbons.packet.Carbon.Enable;
import org.jivesoftware.smackx.carbons.packet.CarbonExtension;
import org.jivesoftware.smackx.carbons.packet.CarbonExtension.Private;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

public class CarbonManager extends Manager {
    private static Map<XMPPConnection, CarbonManager> INSTANCES;
    private volatile boolean enabled_state;

    /* renamed from: org.jivesoftware.smackx.carbons.CarbonManager.1 */
    final class C02091 implements ConnectionCreationListener {
        C02091() {
        }

        public void m5870a(XMPPConnection xMPPConnection) {
            CarbonManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.carbons.CarbonManager.2 */
    class C02102 implements StanzaListener {
        final /* synthetic */ boolean val$new_state;

        C02102(boolean z) {
            this.val$new_state = z;
        }

        public void processPacket(Stanza stanza) {
            CarbonManager.this.enabled_state = this.val$new_state;
        }
    }

    static {
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C02091());
    }

    private CarbonManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.enabled_state = false;
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(CarbonExtension.NAMESPACE);
    }

    private static IQ carbonsEnabledIQ(boolean z) {
        return z ? new Enable() : new Disable();
    }

    @Deprecated
    public static void disableCarbons(Message message) {
        message.addExtension(Private.INSTANCE);
    }

    public static synchronized CarbonManager getInstanceFor(XMPPConnection xMPPConnection) {
        CarbonManager carbonManager;
        synchronized (CarbonManager.class) {
            carbonManager = (CarbonManager) INSTANCES.get(xMPPConnection);
            if (carbonManager == null) {
                carbonManager = new CarbonManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, carbonManager);
            }
        }
        return carbonManager;
    }

    public void disableCarbons() throws XMPPException, SmackException {
        setCarbonsEnabled(false);
    }

    public void enableCarbons() throws XMPPException, SmackException {
        setCarbonsEnabled(true);
    }

    public boolean getCarbonsEnabled() {
        return this.enabled_state;
    }

    public boolean isSupportedByServer() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(connection()).serverSupportsFeature(CarbonExtension.NAMESPACE);
    }

    public void sendCarbonsEnabled(boolean z) throws NotConnectedException {
        connection().sendIqWithResponseCallback(carbonsEnabledIQ(z), new C02102(z));
    }

    public synchronized void setCarbonsEnabled(boolean z) throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (this.enabled_state != z) {
            connection().createPacketCollectorAndSend(carbonsEnabledIQ(z)).nextResultOrThrow();
            this.enabled_state = z;
        }
    }
}
