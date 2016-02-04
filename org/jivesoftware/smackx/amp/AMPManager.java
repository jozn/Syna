package org.jivesoftware.smackx.amp;

import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smackx.amp.packet.AMPExtension;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;

/* renamed from: org.jivesoftware.smackx.amp.a */
public class AMPManager {
    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new AMPManager$1());
    }

    public static synchronized void m5851a(XMPPConnection xMPPConnection, boolean z) {
        synchronized (AMPManager.class) {
            if (AMPManager.m5852a(xMPPConnection) != z) {
                if (z) {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(AMPExtension.NAMESPACE);
                } else {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).removeFeature(AMPExtension.NAMESPACE);
                }
            }
        }
    }

    public static boolean m5852a(XMPPConnection xMPPConnection) {
        xMPPConnection.getServiceName();
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).includesFeature(AMPExtension.NAMESPACE);
    }

    public static boolean m5853a(XMPPConnection xMPPConnection, String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return AMPManager.m5854a(xMPPConnection, "http://jabber.org/protocol/amp?condition=" + str, AMPExtension.NAMESPACE);
    }

    private static boolean m5854a(XMPPConnection xMPPConnection, String str, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).supportsFeature(str2, str);
    }
}
