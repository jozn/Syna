package org.jivesoftware.smackx.xhtmlim;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xhtmlim.packet.XHTMLExtension;

public class XHTMLManager {

    /* renamed from: org.jivesoftware.smackx.xhtmlim.XHTMLManager.1 */
    final class C02781 implements ConnectionCreationListener {
        C02781() {
        }

        public void m6021a(XMPPConnection xMPPConnection) {
            XHTMLManager.m6022a(xMPPConnection, true);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C02781());
    }

    public static synchronized void m6022a(XMPPConnection xMPPConnection, boolean z) {
        synchronized (XHTMLManager.class) {
            if (m6023a(xMPPConnection) != z) {
                if (z) {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(XHTMLExtension.NAMESPACE);
                } else {
                    ServiceDiscoveryManager.getInstanceFor(xMPPConnection).removeFeature(XHTMLExtension.NAMESPACE);
                }
            }
        }
    }

    public static boolean m6023a(XMPPConnection xMPPConnection) {
        return ServiceDiscoveryManager.getInstanceFor(xMPPConnection).includesFeature(XHTMLExtension.NAMESPACE);
    }
}
