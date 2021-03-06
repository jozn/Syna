package org.jivesoftware.smack;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.PlainStreamElement;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.TopLevelStreamElement;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class SynchronizationPoint<E extends Exception> {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final Logger LOGGER;
    private final Condition condition;
    private final AbstractXMPPConnection connection;
    private final Lock connectionLock;
    private E failureException;
    private State state;

    /* renamed from: org.jivesoftware.smack.SynchronizationPoint.1 */
    /* synthetic */ class C01581 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State;

        static {
            $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State = new int[State.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.Failure.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.Initial.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.NoResponse.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[State.RequestSent.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private enum State {
        Initial,
        RequestSent,
        NoResponse,
        Success,
        Failure
    }

    static {
        $assertionsDisabled = !SynchronizationPoint.class.desiredAssertionStatus();
        LOGGER = Logger.getLogger(SynchronizationPoint.class.getName());
    }

    public SynchronizationPoint(AbstractXMPPConnection abstractXMPPConnection) {
        this.connection = abstractXMPPConnection;
        this.connectionLock = abstractXMPPConnection.getConnectionLock();
        this.condition = abstractXMPPConnection.getConnectionLock().newCondition();
        init();
    }

    private void checkForResponse() throws NoResponseException {
        switch (C01581.$SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[this.state.ordinal()]) {
            case VideoSize.HVGA /*2*/:
            case Version.API03_CUPCAKE_15 /*3*/:
            case Version.API04_DONUT_16 /*4*/:
                throw NoResponseException.newWith(this.connection);
            default:
        }
    }

    private void waitForConditionOrTimeout() {
        long toNanos = TimeUnit.MILLISECONDS.toNanos(this.connection.getPacketReplyTimeout());
        while (true) {
            if (this.state != State.RequestSent && this.state != State.Initial) {
                return;
            }
            if (toNanos <= 0) {
                try {
                    this.state = State.NoResponse;
                    return;
                } catch (Throwable e) {
                    LOGGER.log(Level.WARNING, "Thread interrupt while waiting for condition or timeout ignored", e);
                }
            } else {
                toNanos = this.condition.awaitNanos(toNanos);
            }
        }
    }

    public void checkIfSuccessOrWait() throws NoResponseException {
        this.connectionLock.lock();
        try {
            if (this.state != State.Success) {
                waitForConditionOrTimeout();
                this.connectionLock.unlock();
                checkForResponse();
            }
        } finally {
            this.connectionLock.unlock();
        }
    }

    public void checkIfSuccessOrWaitOrThrow() throws NoResponseException, Exception {
        checkIfSuccessOrWait();
        if (this.state == State.Failure) {
            throw this.failureException;
        }
    }

    public void init() {
        this.connectionLock.lock();
        this.state = State.Initial;
        this.failureException = null;
        this.connectionLock.unlock();
    }

    @Deprecated
    public void reportFailure() {
        reportFailure(null);
    }

    public void reportFailure(E e) {
        if ($assertionsDisabled || e != null) {
            this.connectionLock.lock();
            try {
                this.state = State.Failure;
                this.failureException = e;
                this.condition.signalAll();
            } finally {
                this.connectionLock.unlock();
            }
        } else {
            throw new AssertionError();
        }
    }

    public void reportSuccess() {
        this.connectionLock.lock();
        try {
            this.state = State.Success;
            this.condition.signalAll();
        } finally {
            this.connectionLock.unlock();
        }
    }

    public boolean requestSent() {
        this.connectionLock.lock();
        try {
            boolean z = this.state == State.RequestSent;
            this.connectionLock.unlock();
            return z;
        } catch (Throwable th) {
            this.connectionLock.unlock();
        }
    }

    public void sendAndWaitForResponse(TopLevelStreamElement topLevelStreamElement) throws NoResponseException, NotConnectedException {
        if ($assertionsDisabled || this.state == State.Initial) {
            this.connectionLock.lock();
            if (topLevelStreamElement != null) {
                try {
                    if (topLevelStreamElement instanceof Stanza) {
                        this.connection.sendStanza((Stanza) topLevelStreamElement);
                    } else if (topLevelStreamElement instanceof PlainStreamElement) {
                        this.connection.send((PlainStreamElement) topLevelStreamElement);
                    } else {
                        throw new IllegalStateException("Unsupported element type");
                    }
                    this.state = State.RequestSent;
                } catch (Throwable th) {
                    this.connectionLock.unlock();
                }
            }
            waitForConditionOrTimeout();
            this.connectionLock.unlock();
            checkForResponse();
            return;
        }
        throw new AssertionError();
    }

    public void sendAndWaitForResponseOrThrow(PlainStreamElement plainStreamElement) throws Exception, NoResponseException, NotConnectedException {
        sendAndWaitForResponse(plainStreamElement);
        switch (C01581.$SwitchMap$org$jivesoftware$smack$SynchronizationPoint$State[this.state.ordinal()]) {
            case VideoSize.CIF /*1*/:
                if (this.failureException != null) {
                    throw this.failureException;
                }
            default:
        }
    }

    public boolean wasSuccessful() {
        this.connectionLock.lock();
        try {
            boolean z = this.state == State.Success;
            this.connectionLock.unlock();
            return z;
        } catch (Throwable th) {
            this.connectionLock.unlock();
        }
    }
}
