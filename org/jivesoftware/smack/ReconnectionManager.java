package org.jivesoftware.smack;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPException.StreamErrorException;
import org.jivesoftware.smack.packet.StreamError.Condition;
import org.jivesoftware.smack.util.Async;
import org.linphone.core.VideoSize;

public class ReconnectionManager {
    private static final Map<AbstractXMPPConnection, ReconnectionManager> INSTANCES;
    private static final Logger LOGGER;
    private static int defaultFixedDelay;
    private static ReconnectionPolicy defaultReconnectionPolicy;
    private static boolean enabledPerDefault;
    private boolean automaticReconnectEnabled;
    private final ConnectionListener connectionListener;
    boolean done;
    private volatile int fixedDelay;
    private final int randomBase;
    private volatile ReconnectionPolicy reconnectionPolicy;
    private final Runnable reconnectionRunnable;
    private Thread reconnectionThread;
    private final WeakReference<AbstractXMPPConnection> weakRefConnection;

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.1 */
    final class C01541 implements ConnectionCreationListener {
        C01541() {
        }

        public void m5823a(XMPPConnection xMPPConnection) {
            if (xMPPConnection instanceof AbstractXMPPConnection) {
                ReconnectionManager.getInstanceFor((AbstractXMPPConnection) xMPPConnection);
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.2 */
    class C01552 extends Thread {
        private int attempts;

        C01552() {
            this.attempts = 0;
        }

        private int timeDelay() {
            this.attempts++;
            switch (C01574.f4565x55bc48cf[ReconnectionManager.this.reconnectionPolicy.ordinal()]) {
                case VideoSize.CIF /*1*/:
                    return ReconnectionManager.this.fixedDelay;
                case VideoSize.HVGA /*2*/:
                    return this.attempts > 13 ? (ReconnectionManager.this.randomBase * 6) * 5 : this.attempts > 7 ? ReconnectionManager.this.randomBase * 6 : ReconnectionManager.this.randomBase;
                default:
                    throw new AssertionError("Unknown reconnection policy " + ReconnectionManager.this.reconnectionPolicy);
            }
        }

        public void run() {
            AbstractXMPPConnection abstractXMPPConnection = (AbstractXMPPConnection) ReconnectionManager.this.weakRefConnection.get();
            if (abstractXMPPConnection != null) {
                while (ReconnectionManager.this.isReconnectionPossible(abstractXMPPConnection)) {
                    int timeDelay = timeDelay();
                    while (ReconnectionManager.this.isReconnectionPossible(abstractXMPPConnection) && timeDelay > 0) {
                        try {
                            Thread.sleep(1000);
                            int i = timeDelay - 1;
                            for (ConnectionListener reconnectingIn : abstractXMPPConnection.connectionListeners) {
                                reconnectingIn.reconnectingIn(i);
                            }
                            timeDelay = i;
                        } catch (Throwable e) {
                            ReconnectionManager.LOGGER.log(Level.FINE, "waiting for reconnection interrupted", e);
                        }
                    }
                    for (ConnectionListener reconnectingIn2 : abstractXMPPConnection.connectionListeners) {
                        reconnectingIn2.reconnectingIn(0);
                    }
                    try {
                        if (ReconnectionManager.this.isReconnectionPossible(abstractXMPPConnection)) {
                            abstractXMPPConnection.connect();
                        }
                    } catch (Exception e2) {
                        Exception exception = e2;
                        for (ConnectionListener reconnectingIn22 : abstractXMPPConnection.connectionListeners) {
                            reconnectingIn22.reconnectionFailed(exception);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.3 */
    class C01563 extends AbstractConnectionListener {
        C01563() {
        }

        public void authenticated(XMPPConnection xMPPConnection, boolean z) {
            ReconnectionManager.this.done = false;
        }

        public void connectionClosed() {
            ReconnectionManager.this.done = true;
        }

        public void connectionClosedOnError(Exception exception) {
            ReconnectionManager.this.done = false;
            if (ReconnectionManager.this.isAutomaticReconnectEnabled()) {
                if (exception instanceof StreamErrorException) {
                    if (Condition.conflict == ((StreamErrorException) exception).getStreamError().getCondition()) {
                        return;
                    }
                }
                ReconnectionManager.this.reconnect();
            }
        }
    }

    /* renamed from: org.jivesoftware.smack.ReconnectionManager.4 */
    /* synthetic */ class C01574 {
        static final /* synthetic */ int[] f4565x55bc48cf;

        static {
            f4565x55bc48cf = new int[ReconnectionPolicy.values().length];
            try {
                f4565x55bc48cf[ReconnectionPolicy.FIXED_DELAY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4565x55bc48cf[ReconnectionPolicy.RANDOM_INCREASING_DELAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum ReconnectionPolicy {
        RANDOM_INCREASING_DELAY,
        FIXED_DELAY
    }

    static {
        LOGGER = Logger.getLogger(ReconnectionManager.class.getName());
        INSTANCES = new WeakHashMap();
        XMPPConnectionRegistry.addConnectionCreationListener(new C01541());
        enabledPerDefault = false;
        defaultFixedDelay = 15;
        defaultReconnectionPolicy = ReconnectionPolicy.RANDOM_INCREASING_DELAY;
    }

    private ReconnectionManager(AbstractXMPPConnection abstractXMPPConnection) {
        this.randomBase = new Random().nextInt(13) + 2;
        this.fixedDelay = defaultFixedDelay;
        this.reconnectionPolicy = defaultReconnectionPolicy;
        this.automaticReconnectEnabled = false;
        this.done = false;
        this.connectionListener = new C01563();
        this.weakRefConnection = new WeakReference(abstractXMPPConnection);
        this.reconnectionRunnable = new C01552();
        if (getEnabledPerDefault()) {
            enableAutomaticReconnection();
        }
    }

    public static boolean getEnabledPerDefault() {
        return enabledPerDefault;
    }

    public static synchronized ReconnectionManager getInstanceFor(AbstractXMPPConnection abstractXMPPConnection) {
        ReconnectionManager reconnectionManager;
        synchronized (ReconnectionManager.class) {
            reconnectionManager = (ReconnectionManager) INSTANCES.get(abstractXMPPConnection);
            if (reconnectionManager == null) {
                reconnectionManager = new ReconnectionManager(abstractXMPPConnection);
                INSTANCES.put(abstractXMPPConnection, reconnectionManager);
            }
        }
        return reconnectionManager;
    }

    private boolean isReconnectionPossible(XMPPConnection xMPPConnection) {
        return (this.done || xMPPConnection.isConnected() || !isAutomaticReconnectEnabled()) ? false : true;
    }

    private synchronized void reconnect() {
        XMPPConnection xMPPConnection = (XMPPConnection) this.weakRefConnection.get();
        if (xMPPConnection == null) {
            LOGGER.fine("Connection is null, will not reconnect");
        } else if (this.reconnectionThread == null || !this.reconnectionThread.isAlive()) {
            this.reconnectionThread = Async.m5844a(this.reconnectionRunnable, "Smack Reconnection Manager (" + xMPPConnection.getConnectionCounter() + ')');
        }
    }

    public static void setDefaultFixedDelay(int i) {
        defaultFixedDelay = i;
        setDefaultReconnectionPolicy(ReconnectionPolicy.FIXED_DELAY);
    }

    public static void setDefaultReconnectionPolicy(ReconnectionPolicy reconnectionPolicy) {
        defaultReconnectionPolicy = reconnectionPolicy;
    }

    public static void setEnabledPerDefault(boolean z) {
        enabledPerDefault = z;
    }

    public synchronized void disableAutomaticReconnection() {
        if (this.automaticReconnectEnabled) {
            XMPPConnection xMPPConnection = (XMPPConnection) this.weakRefConnection.get();
            if (xMPPConnection == null) {
                throw new IllegalStateException("Connection instance no longer available");
            }
            xMPPConnection.removeConnectionListener(this.connectionListener);
            this.automaticReconnectEnabled = false;
        }
    }

    public synchronized void enableAutomaticReconnection() {
        if (!this.automaticReconnectEnabled) {
            XMPPConnection xMPPConnection = (XMPPConnection) this.weakRefConnection.get();
            if (xMPPConnection == null) {
                throw new IllegalStateException("Connection instance no longer available");
            }
            xMPPConnection.addConnectionListener(this.connectionListener);
            this.automaticReconnectEnabled = true;
        }
    }

    public boolean isAutomaticReconnectEnabled() {
        return this.automaticReconnectEnabled;
    }

    public void setFixedDelay(int i) {
        this.fixedDelay = i;
        setReconnectionPolicy(ReconnectionPolicy.FIXED_DELAY);
    }

    public void setReconnectionPolicy(ReconnectionPolicy reconnectionPolicy) {
        this.reconnectionPolicy = reconnectionPolicy;
    }
}
