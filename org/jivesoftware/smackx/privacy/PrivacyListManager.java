package org.jivesoftware.smackx.privacy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.IQResultReplyFilter;
import org.jivesoftware.smack.filter.IQTypeFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.iqrequest.AbstractIqRequestHandler;
import org.jivesoftware.smack.iqrequest.IQRequestHandler.Mode;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.privacy.filter.SetActiveListFilter;
import org.jivesoftware.smackx.privacy.filter.SetDefaultListFilter;
import org.jivesoftware.smackx.privacy.packet.Privacy;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jivesoftware.smackx.search.UserSearch;

public class PrivacyListManager extends Manager {
    private static final Map<XMPPConnection, PrivacyListManager> INSTANCES;
    public static final String NAMESPACE = "jabber:iq:privacy";
    public static final StanzaFilter PRIVACY_FILTER;
    private static final StanzaFilter PRIVACY_RESULT;
    private volatile String cachedActiveListName;
    private volatile String cachedDefaultListName;
    private final Set<PrivacyListListener> listeners;

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.1 */
    final class C02541 implements ConnectionCreationListener {
        C02541() {
        }

        public void m5974a(XMPPConnection xMPPConnection) {
            PrivacyListManager.getInstanceFor(xMPPConnection);
        }
    }

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.2 */
    class C02552 extends AbstractIqRequestHandler {
        C02552(String str, String str2, Type type, Mode mode) {
            super(str, str2, type, mode);
        }

        public IQ handleIQRequest(IQ iq) {
            Privacy privacy = (Privacy) iq;
            for (PrivacyListListener privacyListListener : PrivacyListManager.this.listeners) {
                for (Entry entry : privacy.getItemLists().entrySet()) {
                    String str = (String) entry.getKey();
                    List list = (List) entry.getValue();
                    if (list.isEmpty()) {
                        privacyListListener.m5975a(str);
                    } else {
                        privacyListListener.m5976a(str, list);
                    }
                }
            }
            return IQ.createResultIQ(privacy);
        }
    }

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.3 */
    class C02573 implements StanzaListener {

        /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.3.1 */
        class C02561 implements StanzaListener {
            final /* synthetic */ String val$activeListName;
            final /* synthetic */ boolean val$declinceActiveList;

            C02561(boolean z, String str) {
                this.val$declinceActiveList = z;
                this.val$activeListName = str;
            }

            public void processPacket(Stanza stanza) throws NotConnectedException {
                if (this.val$declinceActiveList) {
                    PrivacyListManager.this.cachedActiveListName = null;
                } else {
                    PrivacyListManager.this.cachedActiveListName = this.val$activeListName;
                }
            }
        }

        C02573() {
        }

        public void processPacket(Stanza stanza) throws NotConnectedException {
            XMPPConnection access$100 = PrivacyListManager.this.connection();
            Privacy privacy = (Privacy) stanza;
            access$100.addOneTimeSyncCallback(new C02561(privacy.isDeclineActiveList(), privacy.getActiveName()), new IQResultReplyFilter(privacy, access$100));
        }
    }

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.4 */
    class C02594 implements StanzaListener {

        /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.4.1 */
        class C02581 implements StanzaListener {
            final /* synthetic */ boolean val$declinceDefaultList;
            final /* synthetic */ String val$defaultListName;

            C02581(boolean z, String str) {
                this.val$declinceDefaultList = z;
                this.val$defaultListName = str;
            }

            public void processPacket(Stanza stanza) throws NotConnectedException {
                if (this.val$declinceDefaultList) {
                    PrivacyListManager.this.cachedDefaultListName = null;
                } else {
                    PrivacyListManager.this.cachedDefaultListName = this.val$defaultListName;
                }
            }
        }

        C02594() {
        }

        public void processPacket(Stanza stanza) throws NotConnectedException {
            XMPPConnection access$300 = PrivacyListManager.this.connection();
            Privacy privacy = (Privacy) stanza;
            access$300.addOneTimeSyncCallback(new C02581(privacy.isDeclineDefaultList(), privacy.getDefaultName()), new IQResultReplyFilter(privacy, access$300));
        }
    }

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.5 */
    class C02605 implements StanzaListener {
        C02605() {
        }

        public void processPacket(Stanza stanza) throws NotConnectedException {
            Privacy privacy = (Privacy) stanza;
            String activeName = privacy.getActiveName();
            if (activeName != null) {
                PrivacyListManager.this.cachedActiveListName = activeName;
            }
            activeName = privacy.getDefaultName();
            if (activeName != null) {
                PrivacyListManager.this.cachedDefaultListName = activeName;
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.privacy.PrivacyListManager.6 */
    class C02616 extends AbstractConnectionListener {
        C02616() {
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            if (!z) {
                PrivacyListManager.this.cachedActiveListName = PrivacyListManager.this.cachedDefaultListName = null;
            }
        }
    }

    static {
        PRIVACY_FILTER = new StanzaTypeFilter(Privacy.class);
        PRIVACY_RESULT = new AndFilter(IQTypeFilter.RESULT, PRIVACY_FILTER);
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C02541());
    }

    private PrivacyListManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        this.listeners = new CopyOnWriteArraySet();
        xMPPConnection.registerIQRequestHandler(new C02552(UserSearch.ELEMENT, NAMESPACE, Type.set, Mode.sync));
        xMPPConnection.addPacketSendingListener(new C02573(), SetActiveListFilter.INSTANCE);
        xMPPConnection.addPacketSendingListener(new C02594(), SetDefaultListFilter.INSTANCE);
        xMPPConnection.addSyncStanzaListener(new C02605(), PRIVACY_RESULT);
        xMPPConnection.addConnectionListener(new C02616());
        ServiceDiscoveryManager.getInstanceFor(xMPPConnection).addFeature(NAMESPACE);
    }

    public static synchronized PrivacyListManager getInstanceFor(XMPPConnection xMPPConnection) {
        PrivacyListManager privacyListManager;
        synchronized (PrivacyListManager.class) {
            privacyListManager = (PrivacyListManager) INSTANCES.get(xMPPConnection);
            if (privacyListManager == null) {
                privacyListManager = new PrivacyListManager(xMPPConnection);
                INSTANCES.put(xMPPConnection, privacyListManager);
            }
        }
        return privacyListManager;
    }

    private List<PrivacyItem> getPrivacyListItems(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(str, new ArrayList());
        return getRequest(privacy).getPrivacyList(str);
    }

    private Privacy getPrivacyWithListNames() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getRequest(new Privacy());
    }

    private Privacy getRequest(Privacy privacy) throws NoResponseException, XMPPErrorException, NotConnectedException {
        privacy.setType(Type.get);
        return (Privacy) connection().createPacketCollectorAndSend(privacy).nextResultOrThrow();
    }

    private Stanza setRequest(Privacy privacy) throws NoResponseException, XMPPErrorException, NotConnectedException {
        privacy.setType(Type.set);
        return connection().createPacketCollectorAndSend(privacy).nextResultOrThrow();
    }

    public boolean addListener(PrivacyListListener privacyListListener) {
        return this.listeners.add(privacyListListener);
    }

    public void createPrivacyList(String str, List<PrivacyItem> list) throws NoResponseException, XMPPErrorException, NotConnectedException {
        updatePrivacyList(str, list);
    }

    public void declineActiveList() throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacy = new Privacy();
        privacy.setDeclineActiveList(true);
        setRequest(privacy);
    }

    public void declineDefaultList() throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacy = new Privacy();
        privacy.setDeclineDefaultList(true);
        setRequest(privacy);
    }

    public void deletePrivacyList(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(str, new ArrayList());
        setRequest(privacy);
    }

    public PrivacyList getActiveList() throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacyWithListNames = getPrivacyWithListNames();
        String activeName = privacyWithListNames.getActiveName();
        boolean z = activeName != null && activeName.equals(privacyWithListNames.getDefaultName());
        return new PrivacyList(true, z, activeName, getPrivacyListItems(activeName));
    }

    public String getActiveListName() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return this.cachedActiveListName != null ? this.cachedActiveListName : getPrivacyWithListNames().getActiveName();
    }

    public PrivacyList getDefaultList() throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacyWithListNames = getPrivacyWithListNames();
        String defaultName = privacyWithListNames.getDefaultName();
        boolean z = defaultName != null && defaultName.equals(privacyWithListNames.getActiveName());
        return new PrivacyList(z, true, defaultName, getPrivacyListItems(defaultName));
    }

    public String getDefaultListName() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return this.cachedDefaultListName != null ? this.cachedDefaultListName : getPrivacyWithListNames().getDefaultName();
    }

    public String getEffectiveListName() throws NoResponseException, XMPPErrorException, NotConnectedException {
        String activeListName = getActiveListName();
        return activeListName != null ? activeListName : getDefaultListName();
    }

    public PrivacyList getPrivacyList(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return new PrivacyList(false, false, str, getPrivacyListItems(str));
    }

    public List<PrivacyList> getPrivacyLists() throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacyWithListNames = getPrivacyWithListNames();
        Set<String> privacyListNames = privacyWithListNames.getPrivacyListNames();
        List<PrivacyList> arrayList = new ArrayList(privacyListNames.size());
        for (String str : privacyListNames) {
            arrayList.add(new PrivacyList(str.equals(privacyWithListNames.getActiveName()), str.equals(privacyWithListNames.getDefaultName()), str, getPrivacyListItems(str)));
        }
        return arrayList;
    }

    public boolean isSupported() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(connection()).serverSupportsFeature(NAMESPACE);
    }

    public boolean removeListener(PrivacyListListener privacyListListener) {
        return this.listeners.remove(privacyListListener);
    }

    public void setActiveListName(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacy = new Privacy();
        privacy.setActiveName(str);
        setRequest(privacy);
    }

    public void setDefaultListName(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacy = new Privacy();
        privacy.setDefaultName(str);
        setRequest(privacy);
    }

    public void updatePrivacyList(String str, List<PrivacyItem> list) throws NoResponseException, XMPPErrorException, NotConnectedException {
        Privacy privacy = new Privacy();
        privacy.setPrivacyList(str, list);
        setRequest(privacy);
    }
}
