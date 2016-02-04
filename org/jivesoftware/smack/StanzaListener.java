package org.jivesoftware.smack;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Stanza;

/* renamed from: org.jivesoftware.smack.g */
public interface StanzaListener {
    void processPacket(Stanza stanza) throws NotConnectedException;
}
