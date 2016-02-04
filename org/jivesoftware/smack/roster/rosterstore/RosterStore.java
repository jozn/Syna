package org.jivesoftware.smack.roster.rosterstore;

import java.util.Collection;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;

/* renamed from: org.jivesoftware.smack.roster.rosterstore.b */
public interface RosterStore {
    boolean addEntry(Item item, String str);

    Collection<Item> getEntries();

    String getRosterVersion();

    boolean removeEntry(String str, String str2);

    boolean resetEntries(Collection<Item> collection, String str);
}
