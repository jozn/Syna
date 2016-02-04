package org.jivesoftware.smackx.bytestreams.socks5;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;
import org.jivesoftware.smack.AbstractConnectionClosedListener;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.FeatureNotSupportedException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.bytestreams.BytestreamListener;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream;
import org.jivesoftware.smackx.bytestreams.socks5.packet.Bytestream.StreamHost;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverItems.Item;

public final class Socks5BytestreamManager {
    private static final String SESSION_ID_PREFIX = "js5_";
    private static final Map<XMPPConnection, Socks5BytestreamManager> managers;
    private static final Random randomGenerator;
    private final List<BytestreamListener> allRequestListeners;
    private final XMPPConnection connection;
    private List<String> ignoredBytestreamRequests;
    private final InitiationListener initiationListener;
    private String lastWorkingProxy;
    private final List<String> proxyBlacklist;
    private int proxyConnectionTimeout;
    private boolean proxyPrioritizationEnabled;
    private int targetResponseTimeout;
    private final Map<String, BytestreamListener> userListeners;

    /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager.1 */
    final class C01991 implements ConnectionCreationListener {

        /* renamed from: org.jivesoftware.smackx.bytestreams.socks5.Socks5BytestreamManager.1.1 */
        class C01981 extends AbstractConnectionClosedListener {
            final /* synthetic */ XMPPConnection val$connection;

            C01981(XMPPConnection xMPPConnection) {
                this.val$connection = xMPPConnection;
            }

            public void connectionTerminated() {
                Socks5BytestreamManager.getBytestreamManager(this.val$connection).disableService();
            }

            public void reconnectionSuccessful() {
                Socks5BytestreamManager.getBytestreamManager(this.val$connection);
            }
        }

        C01991() {
        }

        public void m5863a(XMPPConnection xMPPConnection) {
            Socks5BytestreamManager.getBytestreamManager(xMPPConnection);
            xMPPConnection.addConnectionListener(new C01981(xMPPConnection));
        }
    }

    static {
        XMPPConnectionRegistry.addConnectionCreationListener(new C01991());
        randomGenerator = new Random();
        managers = new HashMap();
    }

    private Socks5BytestreamManager(XMPPConnection xMPPConnection) {
        this.userListeners = new ConcurrentHashMap();
        this.allRequestListeners = Collections.synchronizedList(new LinkedList());
        this.targetResponseTimeout = 10000;
        this.proxyConnectionTimeout = 10000;
        this.proxyBlacklist = Collections.synchronizedList(new LinkedList());
        this.lastWorkingProxy = null;
        this.proxyPrioritizationEnabled = true;
        this.ignoredBytestreamRequests = Collections.synchronizedList(new LinkedList());
        this.connection = xMPPConnection;
        this.initiationListener = new InitiationListener(this);
    }

    private void activate() {
        this.connection.registerIQRequestHandler(this.initiationListener);
        enableService();
    }

    private Bytestream createBytestreamInitiation(String str, String str2, List<StreamHost> list) {
        Bytestream bytestream = new Bytestream(str);
        for (StreamHost addStreamHost : list) {
            bytestream.addStreamHost(addStreamHost);
        }
        bytestream.setType(Type.set);
        bytestream.setTo(str2);
        return bytestream;
    }

    private Bytestream createStreamHostRequest(String str) {
        Bytestream bytestream = new Bytestream();
        bytestream.setType(Type.get);
        bytestream.setTo(str);
        return bytestream;
    }

    private List<String> determineProxies() throws NoResponseException, XMPPErrorException, NotConnectedException {
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(this.connection);
        List<String> arrayList = new ArrayList();
        for (Item item : instanceFor.discoverItems(this.connection.getServiceName()).getItems()) {
            if (!this.proxyBlacklist.contains(item.getEntityID())) {
                try {
                    if (instanceFor.discoverInfo(item.getEntityID()).hasIdentity("proxy", "bytestreams")) {
                        arrayList.add(item.getEntityID());
                    } else {
                        this.proxyBlacklist.add(item.getEntityID());
                    }
                } catch (NoResponseException e) {
                    this.proxyBlacklist.add(item.getEntityID());
                } catch (XMPPErrorException e2) {
                    this.proxyBlacklist.add(item.getEntityID());
                }
            }
        }
        return arrayList;
    }

    private List<StreamHost> determineStreamHostInfos(List<String> list) {
        List<StreamHost> arrayList = new ArrayList();
        Collection localStreamHost = getLocalStreamHost();
        if (localStreamHost != null) {
            arrayList.addAll(localStreamHost);
        }
        for (String str : list) {
            try {
                arrayList.addAll(((Bytestream) this.connection.createPacketCollectorAndSend(createStreamHostRequest(str)).nextResultOrThrow()).getStreamHosts());
            } catch (Exception e) {
                this.proxyBlacklist.add(str);
            }
        }
        return arrayList;
    }

    private void enableService() {
        ServiceDiscoveryManager.getInstanceFor(this.connection).addFeature(Bytestream.NAMESPACE);
    }

    public static synchronized Socks5BytestreamManager getBytestreamManager(XMPPConnection xMPPConnection) {
        Socks5BytestreamManager socks5BytestreamManager;
        synchronized (Socks5BytestreamManager.class) {
            if (xMPPConnection == null) {
                socks5BytestreamManager = null;
            } else {
                socks5BytestreamManager = (Socks5BytestreamManager) managers.get(xMPPConnection);
                if (socks5BytestreamManager == null) {
                    socks5BytestreamManager = new Socks5BytestreamManager(xMPPConnection);
                    managers.put(xMPPConnection, socks5BytestreamManager);
                    socks5BytestreamManager.activate();
                }
            }
        }
        return socks5BytestreamManager;
    }

    private List<StreamHost> getLocalStreamHost() {
        Socks5Proxy socks5Proxy = Socks5Proxy.getSocks5Proxy();
        if (!socks5Proxy.isRunning()) {
            return null;
        }
        List<String> localAddresses = socks5Proxy.getLocalAddresses();
        if (localAddresses.isEmpty()) {
            return null;
        }
        int port = socks5Proxy.getPort();
        List<StreamHost> arrayList = new ArrayList();
        for (String str : localAddresses) {
            for (String startsWith : new String[]{"127.0.0.1", "0:0:0:0:0:0:0:1", "::1"}) {
                if (str.startsWith(startsWith)) {
                    break;
                }
            }
            arrayList.add(new StreamHost(this.connection.getUser(), str, port));
        }
        return arrayList;
    }

    private String getNextSessionID() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SESSION_ID_PREFIX);
        stringBuilder.append(Math.abs(randomGenerator.nextLong()));
        return stringBuilder.toString();
    }

    private boolean supportsSocks5(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return ServiceDiscoveryManager.getInstanceFor(this.connection).supportsFeature(str, Bytestream.NAMESPACE);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.add(bytestreamListener);
    }

    public void addIncomingBytestreamListener(BytestreamListener bytestreamListener, String str) {
        this.userListeners.put(str, bytestreamListener);
    }

    public synchronized void disableService() {
        this.connection.unregisterIQRequestHandler(this.initiationListener);
        this.initiationListener.shutdown();
        this.allRequestListeners.clear();
        this.userListeners.clear();
        this.lastWorkingProxy = null;
        this.proxyBlacklist.clear();
        this.ignoredBytestreamRequests.clear();
        managers.remove(this.connection);
        if (managers.size() == 0) {
            Socks5Proxy.getSocks5Proxy().stop();
        }
        ServiceDiscoveryManager instanceFor = ServiceDiscoveryManager.getInstanceFor(this.connection);
        if (instanceFor != null) {
            instanceFor.removeFeature(Bytestream.NAMESPACE);
        }
    }

    public Socks5BytestreamSession establishSession(String str) throws XMPPException, IOException, InterruptedException, SmackException {
        return establishSession(str, getNextSessionID());
    }

    public Socks5BytestreamSession establishSession(String str, String str2) throws IOException, InterruptedException, NoResponseException, SmackException, XMPPException {
        if (supportsSocks5(str)) {
            XMPPErrorException xMPPErrorException;
            List arrayList = new ArrayList();
            try {
                arrayList.addAll(determineProxies());
                xMPPErrorException = null;
            } catch (XMPPErrorException e) {
                xMPPErrorException = e;
            }
            List<Object> determineStreamHostInfos = determineStreamHostInfos(arrayList);
            if (!determineStreamHostInfos.isEmpty()) {
                String a = Socks5Utils.m5864a(str2, this.connection.getUser(), str);
                if (this.proxyPrioritizationEnabled && this.lastWorkingProxy != null) {
                    for (Object obj : determineStreamHostInfos) {
                        if (obj.getJID().equals(this.lastWorkingProxy)) {
                            break;
                        }
                    }
                    Object obj2 = null;
                    if (obj2 != null) {
                        determineStreamHostInfos.remove(obj2);
                        determineStreamHostInfos.add(0, obj2);
                    }
                }
                Socks5Proxy socks5Proxy = Socks5Proxy.getSocks5Proxy();
                try {
                    socks5Proxy.addTransfer(a);
                    IQ createBytestreamInitiation = createBytestreamInitiation(str2, str, determineStreamHostInfos);
                    StreamHost streamHost = createBytestreamInitiation.getStreamHost(((Bytestream) this.connection.createPacketCollectorAndSend(createBytestreamInitiation).nextResultOrThrow((long) getTargetResponseTimeout())).getUsedHost().getJID());
                    if (streamHost == null) {
                        throw new SmackException("Remote user responded with unknown host");
                    }
                    Socket socket = new Socks5ClientForInitiator(streamHost, a, this.connection, str2, str).getSocket(getProxyConnectionTimeout());
                    this.lastWorkingProxy = streamHost.getJID();
                    Socks5BytestreamSession socks5BytestreamSession = new Socks5BytestreamSession(socket, streamHost.getJID().equals(this.connection.getUser()));
                    socks5Proxy.removeTransfer(a);
                    return socks5BytestreamSession;
                } catch (TimeoutException e2) {
                    throw new IOException("Timeout while connecting to SOCKS5 proxy");
                } catch (Throwable th) {
                    socks5Proxy.removeTransfer(a);
                }
            } else if (xMPPErrorException != null) {
                throw xMPPErrorException;
            } else {
                throw new SmackException("no SOCKS5 proxies available");
            }
        }
        throw new FeatureNotSupportedException("SOCKS5 Bytestream", str);
    }

    protected List<BytestreamListener> getAllRequestListeners() {
        return this.allRequestListeners;
    }

    protected XMPPConnection getConnection() {
        return this.connection;
    }

    protected List<String> getIgnoredBytestreamRequests() {
        return this.ignoredBytestreamRequests;
    }

    public int getProxyConnectionTimeout() {
        if (this.proxyConnectionTimeout <= 0) {
            this.proxyConnectionTimeout = 10000;
        }
        return this.proxyConnectionTimeout;
    }

    public int getTargetResponseTimeout() {
        if (this.targetResponseTimeout <= 0) {
            this.targetResponseTimeout = 10000;
        }
        return this.targetResponseTimeout;
    }

    protected BytestreamListener getUserListener(String str) {
        return (BytestreamListener) this.userListeners.get(str);
    }

    public void ignoreBytestreamRequestOnce(String str) {
        this.ignoredBytestreamRequests.add(str);
    }

    public boolean isProxyPrioritizationEnabled() {
        return this.proxyPrioritizationEnabled;
    }

    public void removeIncomingBytestreamListener(String str) {
        this.userListeners.remove(str);
    }

    public void removeIncomingBytestreamListener(BytestreamListener bytestreamListener) {
        this.allRequestListeners.remove(bytestreamListener);
    }

    protected void replyRejectPacket(IQ iq) throws NotConnectedException {
        this.connection.sendStanza(IQ.createErrorResponse(iq, new XMPPError(Condition.not_acceptable)));
    }

    public void setProxyConnectionTimeout(int i) {
        this.proxyConnectionTimeout = i;
    }

    public void setProxyPrioritizationEnabled(boolean z) {
        this.proxyPrioritizationEnabled = z;
    }

    public void setTargetResponseTimeout(int i) {
        this.targetResponseTimeout = i;
    }
}
