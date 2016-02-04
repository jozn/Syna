package org.jivesoftware.smackx.xdatalayout;

import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.xdatalayout.packet.DataLayout;

public class XDataLayoutManager {

    /* renamed from: org.jivesoftware.smackx.xdatalayout.XDataLayoutManager.1 */
    final class C02751 implements ConnectionCreationListener {
        C02751() {
        }

        public void m6015a(XMPPConnection xMPPConnection) {
            ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(DataLayout.NAMESPACE);
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C02751());
    }
}
