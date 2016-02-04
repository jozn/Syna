package org.jivesoftware.smack;

/* renamed from: org.jivesoftware.smack.b */
public interface ConnectionListener {
    void authenticated(XMPPConnection xMPPConnection, boolean z);

    void connected(XMPPConnection xMPPConnection);

    void connectionClosed();

    void connectionClosedOnError(Exception exception);

    void reconnectingIn(int i);

    void reconnectionFailed(Exception exception);

    void reconnectionSuccessful();
}
