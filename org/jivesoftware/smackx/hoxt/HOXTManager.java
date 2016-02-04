package org.jivesoftware.smackx.hoxt;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

public class HOXTManager {
    public static final String NAMESPACE = "urn:xmpp:http";

    /* renamed from: org.jivesoftware.smackx.hoxt.HOXTManager.1 */
    final class C02291 implements ConnectionCreationListener {
        C02291() {
        }

        public void m5891a(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(HOXTManager.NAMESPACE);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C02291());
    }

    public static boolean isSupported(String str, XMPPConnection xMPPConnection) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).supportsFeature(str, NAMESPACE);
    }
}
