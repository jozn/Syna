package mobi.mmdt.ott.asmackengine.profile;

import mobi.mmdt.ott.asmackengine.core.Core;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.SmackException.NotLoggedInException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.roster.packet.RosterPacket.ItemType;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: mobi.mmdt.ott.asmackengine.profile.a */
public class Profile {
    private static Profile f3319a;
    private static /* synthetic */ int[] f3320d;
    private ProfileListener f3321b;
    private RosterListener f3322c;

    /* renamed from: mobi.mmdt.ott.asmackengine.profile.a.a */
    public enum Profile {
        AVAILABLE,
        ERROR,
        SUBSCRIBE,
        SUBSCRIBED,
        UNSUBSCRIBE,
        UNSUBSCRIBED,
        UNAVAILABLE
    }

    private Profile() {
        this.f3322c = new Profile$1(this);
    }

    public static Profile m4265a() {
        if (f3319a == null) {
            f3319a = new Profile();
        }
        return f3319a;
    }

    private void m4267a(String str, int i) throws XMPPException, NotLoggedInException, NoResponseException, NotConnectedException {
        if (Core.m4198a().m4207a(false)) {
            RosterEntry entry = Roster.getInstanceFor(Core.m4198a().m4210d()).getEntry(str);
            if (entry == null) {
                Roster.getInstanceFor(Core.m4198a().m4210d()).createEntry(str, null, new String[0]);
                if (i != 1) {
                    m4267a(str, 1);
                    return;
                }
                return;
            } else if (entry.getType().equals(ItemType.from) || entry.getType().equals(ItemType.none)) {
                Stanza presence = new Presence(Type.subscribe);
                presence.setTo(str);
                Core.m4198a().m4210d().sendStanza(presence);
                if (i != 2) {
                    m4267a(str, 2);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        throw new NotConnectedException();
    }

    static /* synthetic */ int[] m4268g() {
        int[] iArr = f3320d;
        if (iArr == null) {
            iArr = new int[Type.values().length];
            try {
                iArr[Type.available.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Type.error.ordinal()] = 7;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Type.probe.ordinal()] = 8;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Type.subscribe.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Type.subscribed.ordinal()] = 4;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Type.unavailable.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[Type.unsubscribe.ordinal()] = 5;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[Type.unsubscribed.ordinal()] = 6;
            } catch (NoSuchFieldError e8) {
            }
            f3320d = iArr;
        }
        return iArr;
    }

    public Profile m4269a(String str) {
        if (!Core.m4198a().m4207a(false)) {
            return Profile.UNAVAILABLE;
        }
        if (Core.m4198a().m4210d().getUser().contains(str)) {
            return Profile.AVAILABLE;
        }
        try {
            m4267a(str, 0);
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (NotLoggedInException e2) {
            e2.printStackTrace();
        } catch (NoResponseException e3) {
            e3.printStackTrace();
        } catch (NotConnectedException e4) {
            e4.printStackTrace();
        }
        switch (Profile.m4268g()[Roster.getInstanceFor(Core.m4198a().m4210d()).getPresence(str).getType().ordinal()]) {
            case VideoSize.CIF /*1*/:
                return Profile.AVAILABLE;
            case VideoSize.HVGA /*2*/:
                return Profile.UNAVAILABLE;
            case Version.API03_CUPCAKE_15 /*3*/:
                return Profile.SUBSCRIBE;
            case Version.API04_DONUT_16 /*4*/:
                return Profile.SUBSCRIBED;
            case Version.API05_ECLAIR_20 /*5*/:
                return Profile.UNSUBSCRIBE;
            case Version.API06_ECLAIR_201 /*6*/:
                return Profile.UNSUBSCRIBED;
            case Version.API07_ECLAIR_21 /*7*/:
                return Profile.ERROR;
            default:
                return Profile.ERROR;
        }
    }

    public void m4270a(ProfileListener profileListener) {
        this.f3321b = profileListener;
    }

    public void m4271a(VCard vCard) throws NotConnectedException, NoResponseException, XMPPErrorException {
        if (Core.m4198a().m4207a(false)) {
            long packetReplyTimeout = Core.m4198a().m4210d().getPacketReplyTimeout();
            try {
                VCardManager.getInstanceFor(Core.m4198a().m4210d()).saveVCard(vCard);
                Core.m4198a().m4210d().setPacketReplyTimeout(packetReplyTimeout);
                return;
            } catch (NotConnectedException e) {
                e.printStackTrace();
                Core.m4198a().m4210d().setPacketReplyTimeout(packetReplyTimeout);
                throw e;
            } catch (NoResponseException e2) {
                e2.printStackTrace();
                Core.m4198a().m4210d().setPacketReplyTimeout(packetReplyTimeout);
                throw e2;
            } catch (XMPPErrorException e3) {
                e3.printStackTrace();
                Core.m4198a().m4210d().setPacketReplyTimeout(packetReplyTimeout);
                throw e3;
            }
        }
        throw new NotConnectedException();
    }

    public RosterListener m4272b() {
        return this.f3322c;
    }

    public VCard m4273b(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (Core.m4198a().m4207a(false)) {
            long packetReplyTimeout = Core.m4198a().m4210d().getPacketReplyTimeout();
            VCard vCard = new VCard();
            Core.m4198a().m4210d().setPacketReplyTimeout(7000);
            try {
                vCard = VCardManager.getInstanceFor(Core.m4198a().m4210d()).loadVCard(str);
            } catch (Exception e) {
            }
            Core.m4198a().m4210d().setPacketReplyTimeout(packetReplyTimeout);
            return vCard;
        }
        throw new NotConnectedException();
    }

    public void m4274c() {
        Thread thread = new Thread(new Profile(this));
        thread.setPriority(1);
        thread.start();
    }

    public void m4275d() {
    }

    public VCard m4276e() throws NotConnectedException, NoResponseException, XMPPException {
        if (Core.m4198a().m4207a(false)) {
            VCard vCard = new VCard();
            Core.m4198a().m4210d().getPacketReplyTimeout();
            Core.m4198a().m4210d().setPacketReplyTimeout(7000);
            try {
                vCard = VCardManager.getInstanceFor(Core.m4198a().m4210d()).loadVCard();
                Core.m4198a().m4210d().setPacketReplyTimeout(7000);
                return vCard;
            } catch (ClassCastException e) {
                Core.m4198a().m4210d().setPacketReplyTimeout(7000);
                return vCard;
            } catch (XMPPException e2) {
                e2.printStackTrace();
                Core.m4198a().m4210d().setPacketReplyTimeout(7000);
                throw e2;
            } catch (NoResponseException e3) {
                e3.printStackTrace();
                Core.m4198a().m4210d().setPacketReplyTimeout(7000);
                throw e3;
            } catch (NotConnectedException e4) {
                e4.printStackTrace();
                Core.m4198a().m4210d().setPacketReplyTimeout(7000);
                throw e4;
            } catch (Exception e5) {
                e5.printStackTrace();
                Core.m4198a().m4210d().setPacketReplyTimeout(7000);
                throw e5;
            }
        }
        throw new NotConnectedException();
    }

    public void m4277f() {
        Stanza presence = new Presence(Type.available);
        presence.setMode(Mode.available);
        try {
            Core.m4198a().m4210d().sendStanza(presence);
        } catch (NotConnectedException e) {
            e.printStackTrace();
        }
    }
}
