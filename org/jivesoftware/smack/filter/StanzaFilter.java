package org.jivesoftware.smack.filter;

import org.jivesoftware.smack.packet.Stanza;

/* renamed from: org.jivesoftware.smack.filter.a */
public interface StanzaFilter {
    boolean accept(Stanza stanza);
}
