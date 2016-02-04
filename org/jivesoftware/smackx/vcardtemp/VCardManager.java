package org.jivesoftware.smackx.vcardtemp;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;

public class VCardManager extends Manager {
    public static final String ELEMENT = "vCard";
    private static final Map<XMPPConnection, VCardManager> INSTANCES;
    public static final String NAMESPACE = "vcard-temp";

    /* renamed from: org.jivesoftware.smackx.vcardtemp.VCardManager.1 */
    final class C02711 implements ConnectionCreationListener {
        C02711() {
        }

        public void m6008a(XMPPConnection xMPPConnection) {
            VCardManager.getInstanceFor(xMPPConnection);
        }
    }

    static {
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C02711());
    }

    private VCardManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(NAMESPACE);
    }

    public static synchronized VCardManager getInstanceFor(XMPPConnection xMPPConnection) {
        VCardManager vCardManager;
        synchronized (VCardManager.class) {
            vCardManager = (VCardManager) INSTANCES.get(xMPPConnection);
            if (vCardManager == null) {
                vCardManager = new VCardManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, vCardManager);
            }
        }
        return vCardManager;
    }

    @Deprecated
    public static boolean isSupported(String str, XMPPConnection xMPPConnection) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getInstanceFor(xMPPConnection).isSupported(str);
    }

    public boolean isSupported(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, NAMESPACE);
    }

    public VCard loadVCard() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return loadVCard(null);
    }

    public VCard loadVCard(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ vCard = new VCard();
        vCard.setTo(str);
        return (VCard) connection().createPacketCollectorAndSend(vCard).nextResultOrThrow();
    }

    public void saveVCard(VCard vCard) throws NoResponseException, XMPPErrorException, NotConnectedException {
        vCard.setTo(null);
        vCard.setType(Type.set);
        vCard.setStanzaId(StanzaIdUtil.newStanzaId());
        connection().createPacketCollectorAndSend(vCard).nextResultOrThrow();
    }
}
