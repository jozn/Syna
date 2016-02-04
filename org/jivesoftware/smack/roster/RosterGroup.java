package org.jivesoftware.smack.roster;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.roster.packet.RosterPacket;
import org.jivesoftware.smack.roster.packet.RosterPacket.Item;
import org.p075c.p076a.XmppStringUtils;

public class RosterGroup {
    private final XMPPConnection connection;
    private final Set<RosterEntry> entries;
    private final String name;

    RosterGroup(String str, XMPPConnection xMPPConnection) {
        this.name = str;
        this.connection = xMPPConnection;
        this.entries = new LinkedHashSet();
    }

    public void addEntry(RosterEntry rosterEntry) throws NoResponseException, XMPPErrorException, NotConnectedException {
        PacketCollector packetCollector = null;
        synchronized (this.entries) {
            if (!this.entries.contains(rosterEntry)) {
                IQ rosterPacket = new RosterPacket();
                rosterPacket.setType(Type.set);
                Item toRosterItem = RosterEntry.toRosterItem(rosterEntry);
                toRosterItem.addGroupName(getName());
                rosterPacket.addRosterItem(toRosterItem);
                packetCollector = this.connection.createPacketCollectorAndSend(rosterPacket);
            }
        }
        if (packetCollector != null) {
            packetCollector.nextResultOrThrow();
        }
    }

    void addEntryLocal(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            this.entries.remove(rosterEntry);
            this.entries.add(rosterEntry);
        }
    }

    public boolean contains(String str) {
        return getEntry(str) != null;
    }

    public boolean contains(RosterEntry rosterEntry) {
        boolean contains;
        synchronized (this.entries) {
            contains = this.entries.contains(rosterEntry);
        }
        return contains;
    }

    public List<RosterEntry> getEntries() {
        List arrayList;
        synchronized (this.entries) {
            arrayList = new ArrayList(this.entries);
        }
        return arrayList;
    }

    public RosterEntry getEntry(String str) {
        if (str == null) {
            return null;
        }
        String toLowerCase = XmppStringUtils.m5404d(str).toLowerCase(Locale.US);
        synchronized (this.entries) {
            for (RosterEntry rosterEntry : this.entries) {
                if (rosterEntry.getUser().equals(toLowerCase)) {
                    return rosterEntry;
                }
            }
            return null;
        }
    }

    public int getEntryCount() {
        int size;
        synchronized (this.entries) {
            size = this.entries.size();
        }
        return size;
    }

    public String getName() {
        return this.name;
    }

    public void removeEntry(RosterEntry rosterEntry) throws NoResponseException, XMPPErrorException, NotConnectedException {
        PacketCollector packetCollector = null;
        synchronized (this.entries) {
            if (this.entries.contains(rosterEntry)) {
                IQ rosterPacket = new RosterPacket();
                rosterPacket.setType(Type.set);
                Item toRosterItem = RosterEntry.toRosterItem(rosterEntry);
                toRosterItem.removeGroupName(getName());
                rosterPacket.addRosterItem(toRosterItem);
                packetCollector = this.connection.createPacketCollectorAndSend(rosterPacket);
            }
        }
        if (packetCollector != null) {
            packetCollector.nextResultOrThrow();
        }
    }

    void removeEntryLocal(RosterEntry rosterEntry) {
        synchronized (this.entries) {
            if (this.entries.contains(rosterEntry)) {
                this.entries.remove(rosterEntry);
            }
        }
    }

    public void setName(String str) throws NotConnectedException, NoResponseException, XMPPErrorException {
        synchronized (this.entries) {
            for (RosterEntry rosterEntry : this.entries) {
                IQ rosterPacket = new RosterPacket();
                rosterPacket.setType(Type.set);
                Item toRosterItem = RosterEntry.toRosterItem(rosterEntry);
                toRosterItem.removeGroupName(this.name);
                toRosterItem.addGroupName(str);
                rosterPacket.addRosterItem(toRosterItem);
                this.connection.createPacketCollectorAndSend(rosterPacket).nextResultOrThrow();
            }
        }
    }
}
