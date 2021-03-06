package org.jivesoftware.smackx.iqlast;

import java.util.Map;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.iqlast.packet.LastActivity;
import org.jivesoftware.smackx.search.UserSearch;
import org.linphone.core.VideoSize;

public class LastActivityManager extends Manager {
    private static boolean enabledPerDefault;
    private static final Map<XMPPConnection, LastActivityManager> instances;
    private boolean enabled;
    private volatile long lastMessageSent;

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.1 */
    final class C02301 implements ConnectionCreationListener {
        C02301() {
        }

        public void m5893a(XMPPConnection xMPPConnection) {
            LastActivityManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.2 */
    class C02312 implements StanzaListener {
        C02312() {
        }

        public void processPacket(Stanza stanza) {
            Mode mode = ((Presence) stanza).getMode();
            if (mode != null) {
                switch (C02345.$SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[mode.ordinal()]) {
                    case VideoSize.CIF /*1*/:
                    case VideoSize.HVGA /*2*/:
                        LastActivityManager.this.resetIdleTime();
                    default:
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.3 */
    class C02323 implements StanzaListener {
        C02323() {
        }

        public void processPacket(Stanza stanza) {
            if (((Message) stanza).getType() != Type.error) {
                LastActivityManager.this.resetIdleTime();
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.4 */
    class C02334 extends AbstractIqRequestHandler {
        C02334(String str, String str2, IQ.Type type, IQRequestHandler.Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            if (!LastActivityManager.this.enabled) {
                return IQ.createErrorResponse(iq, new XMPPError(Condition.not_acceptable));
            }
            IQ lastActivity = new LastActivity();
            lastActivity.setType(IQ.Type.result);
            lastActivity.setTo(iq.getFrom());
            lastActivity.setFrom(iq.getTo());
            lastActivity.setStanzaId(iq.getStanzaId());
            lastActivity.setLastActivity(LastActivityManager.this.getIdleTime());
            return lastActivity;
        }
    }

    /* renamed from: org.jivesoftware.smackx.iqlast.LastActivityManager.5 */
    /* synthetic */ class C02345 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode = new int[Mode.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[Mode.available.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Mode[Mode.chat.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        instances = new WeakHashMap();
        enabledPerDefault = true;
        XMPPConnectionRegistry.addConnectionCreationListener(new C02301());
    }

    private LastActivityManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.enabled = false;
        xMPPConnection.addPacketSendingListener(new C02312(), StanzaTypeFilter.PRESENCE);
        xMPPConnection.addPacketSendingListener(new C02323(), StanzaTypeFilter.MESSAGE);
        xMPPConnection.registerIQRequestHandler(new C02334(UserSearch.ELEMENT, LastActivity.NAMESPACE, IQ.Type.get, IQRequestHandler.Mode.async));
        if (enabledPerDefault) {
            enable();
        }
        resetIdleTime();
        instances.put(xMPPConnection, this);
    }

    private long getIdleTime() {
        return (System.currentTimeMillis() - this.lastMessageSent) / 1000;
    }

    public static synchronized LastActivityManager getInstanceFor(XMPPConnection xMPPConnection) {
        LastActivityManager lastActivityManager;
        synchronized (LastActivityManager.class) {
            lastActivityManager = (LastActivityManager) instances.get(xMPPConnection);
            if (lastActivityManager == null) {
                lastActivityManager = new LastActivityManager(xMPPConnection);
            }
        }
        return lastActivityManager;
    }

    private void resetIdleTime() {
        this.lastMessageSent = System.currentTimeMillis();
    }

    public static void setEnabledPerDefault(boolean z) {
        enabledPerDefault = z;
    }

    public synchronized void disable() {
        ServiceDiscoveryManager.getInstanceFor(connection()).removeFeature(LastActivity.NAMESPACE);
        this.enabled = false;
    }

    public synchronized void enable() {
        ServiceDiscoveryManager.getInstanceFor(connection()).addFeature(LastActivity.NAMESPACE);
        this.enabled = true;
    }

    public LastActivity getLastActivity(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return (LastActivity) connection().createPacketCollectorAndSend(new LastActivity(str)).nextResultOrThrow();
    }

    public boolean isLastActivitySupported(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(connection()).supportsFeature(str, LastActivity.NAMESPACE);
    }
}
