package org.jivesoftware.smackx.muc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.PresenceListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.MessageWithSubjectFilter;
import org.jivesoftware.smack.filter.NotFilter;
import org.jivesoftware.smack.filter.StanzaExtensionFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.filter.ToFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Mode;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity;
import org.jivesoftware.smackx.iqregister.packet.Registration;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCOwner;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.privacy.packet.PrivacyItem;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.linphone.core.VideoSize;

public class MultiUserChat {
    private static final Logger LOGGER;
    private final XMPPConnection connection;
    private final StanzaListener declinesListener;
    private final StanzaFilter fromRoomFilter;
    private final StanzaFilter fromRoomGroupchatFilter;
    private final Set<InvitationRejectionListener> invitationRejectionListeners;
    private boolean joined;
    private PacketCollector messageCollector;
    private final StanzaListener messageListener;
    private final Set<MessageListener> messageListeners;
    private final MultiUserChatManager multiUserChatManager;
    private String nickname;
    private final Map<String, Presence> occupantsMap;
    private final Set<ParticipantStatusListener> participantStatusListeners;
    private final StanzaListener presenceInterceptor;
    private final Set<PresenceListener> presenceInterceptors;
    private final StanzaListener presenceListener;
    private final Set<PresenceListener> presenceListeners;
    private final String room;
    private String subject;
    private final StanzaListener subjectListener;
    private final Set<SubjectUpdatedListener> subjectUpdatedListeners;
    private final Set<UserStatusListener> userStatusListeners;

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.1 */
    class C02371 implements StanzaListener {
        C02371() {
        }

        public void processPacket(Stanza stanza) throws NotConnectedException {
            Message message = (Message) stanza;
            for (MessageListener processMessage : MultiUserChat.this.messageListeners) {
                processMessage.processMessage(message);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.2 */
    class C02382 implements StanzaListener {
        C02382() {
        }

        public void processPacket(Stanza stanza) {
            Message message = (Message) stanza;
            MultiUserChat.this.subject = message.getSubject();
            for (SubjectUpdatedListener a : MultiUserChat.this.subjectUpdatedListeners) {
                a.m5957a(MultiUserChat.this.subject, message.getFrom());
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.3 */
    class C02393 implements StanzaListener {
        C02393() {
        }

        public void processPacket(Stanza stanza) {
            Presence presence = (Presence) stanza;
            String from = presence.getFrom();
            String str = MultiUserChat.this.room + "/" + MultiUserChat.this.nickname;
            boolean equals = presence.getFrom().equals(str);
            switch (C02437.$SwitchMap$org$jivesoftware$smack$packet$Presence$Type[presence.getType().ordinal()]) {
                case VideoSize.CIF /*1*/:
                    if (((Presence) MultiUserChat.this.occupantsMap.put(from, presence)) == null) {
                        if (!equals) {
                            for (ParticipantStatusListener a : MultiUserChat.this.participantStatusListeners) {
                                a.m5901a(from);
                            }
                            break;
                        }
                    }
                    MUCUser from2 = MUCUser.from(stanza);
                    MUCAffiliation affiliation = from2.getItem().getAffiliation();
                    MUCRole role = from2.getItem().getRole();
                    MUCUser from3 = MUCUser.from(stanza);
                    MUCAffiliation affiliation2 = from3.getItem().getAffiliation();
                    MultiUserChat.this.checkRoleModifications(role, from3.getItem().getRole(), equals, from);
                    MultiUserChat.this.checkAffiliationModifications(affiliation, affiliation2, equals, from);
                    break;
                    break;
                case VideoSize.HVGA /*2*/:
                    MultiUserChat.this.occupantsMap.remove(from);
                    MUCUser from4 = MUCUser.from(stanza);
                    if (from4 == null || !from4.hasStatus()) {
                        if (!equals) {
                            for (ParticipantStatusListener a2 : MultiUserChat.this.participantStatusListeners) {
                                a2.m5904b(from);
                            }
                            break;
                        }
                    }
                    MultiUserChat.this.checkPresenceCode(from4.getStatus(), presence.getFrom().equals(str), from4, from);
                    break;
                    break;
            }
            for (PresenceListener a3 : MultiUserChat.this.presenceListeners) {
                a3.m5824a(presence);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.4 */
    class C02404 implements StanzaListener {
        C02404() {
        }

        public void processPacket(Stanza stanza) {
            MUCUser from = MUCUser.from(stanza);
            if (from.getDecline() != null) {
                MultiUserChat.this.fireInvitationRejectionListeners(from.getDecline().getFrom(), from.getDecline().getReason());
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.5 */
    class C02415 implements StanzaListener {
        C02415() {
        }

        public void processPacket(Stanza stanza) {
            Presence presence = (Presence) stanza;
            for (PresenceListener a : MultiUserChat.this.presenceInterceptors) {
                a.m5824a(presence);
            }
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.6 */
    class C02426 implements StanzaFilter {
        final /* synthetic */ String val$subject;

        C02426(String str) {
            this.val$subject = str;
        }

        public boolean accept(Stanza stanza) {
            return this.val$subject.equals(((Message) stanza).getSubject());
        }
    }

    /* renamed from: org.jivesoftware.smackx.muc.MultiUserChat.7 */
    /* synthetic */ class C02437 {
        static final /* synthetic */ int[] $SwitchMap$org$jivesoftware$smack$packet$Presence$Type;

        static {
            $SwitchMap$org$jivesoftware$smack$packet$Presence$Type = new int[Type.values().length];
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.available.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$org$jivesoftware$smack$packet$Presence$Type[Type.unavailable.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        LOGGER = Logger.getLogger(MultiUserChat.class.getName());
    }

    MultiUserChat(XMPPConnection xMPPConnection, String str, MultiUserChatManager multiUserChatManager) {
        this.occupantsMap = new ConcurrentHashMap();
        this.invitationRejectionListeners = new CopyOnWriteArraySet();
        this.subjectUpdatedListeners = new CopyOnWriteArraySet();
        this.userStatusListeners = new CopyOnWriteArraySet();
        this.participantStatusListeners = new CopyOnWriteArraySet();
        this.messageListeners = new CopyOnWriteArraySet();
        this.presenceListeners = new CopyOnWriteArraySet();
        this.presenceInterceptors = new CopyOnWriteArraySet();
        this.nickname = null;
        this.joined = false;
        this.connection = xMPPConnection;
        this.room = str.toLowerCase(Locale.US);
        this.multiUserChatManager = multiUserChatManager;
        this.fromRoomFilter = FromMatchesFilter.create(str);
        this.fromRoomGroupchatFilter = new AndFilter(this.fromRoomFilter, MessageTypeFilter.GROUPCHAT);
        this.messageListener = new C02371();
        this.subjectListener = new C02382();
        this.presenceListener = new C02393();
        this.declinesListener = new C02404();
        this.presenceInterceptor = new C02415();
    }

    private void changeAffiliationByAdmin(String str, MUCAffiliation mUCAffiliation) throws NoResponseException, XMPPErrorException, NotConnectedException {
        changeAffiliationByAdmin(str, mUCAffiliation, null);
    }

    private void changeAffiliationByAdmin(String str, MUCAffiliation mUCAffiliation, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        mUCAdmin.addItem(new MUCItem(mUCAffiliation, str, str2));
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    private void changeAffiliationByAdmin(Collection<String> collection, MUCAffiliation mUCAffiliation) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        for (String mUCItem : collection) {
            mUCAdmin.addItem(new MUCItem(mUCAffiliation, mUCItem));
        }
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    private void changeRole(String str, MUCRole mUCRole, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        mUCAdmin.addItem(new MUCItem(mUCRole, str, str2));
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    private void changeRole(Collection<String> collection, MUCRole mUCRole) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.set);
        for (String mUCItem : collection) {
            mUCAdmin.addItem(new MUCItem(mUCRole, mUCItem));
        }
        this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
    }

    private void checkAffiliationModifications(MUCAffiliation mUCAffiliation, MUCAffiliation mUCAffiliation2, boolean z, String str) {
        if (!"owner".equals(mUCAffiliation) || "owner".equals(mUCAffiliation2)) {
            if (!"admin".equals(mUCAffiliation) || "admin".equals(mUCAffiliation2)) {
                if ("member".equals(mUCAffiliation) && !"member".equals(mUCAffiliation2)) {
                    if (z) {
                        for (UserStatusListener d : this.userStatusListeners) {
                            d.m5936d();
                        }
                    } else {
                        for (ParticipantStatusListener f : this.participantStatusListeners) {
                            f.m5909f(str);
                        }
                    }
                }
            } else if (z) {
                for (UserStatusListener d2 : this.userStatusListeners) {
                    d2.m5942j();
                }
            } else {
                for (ParticipantStatusListener f2 : this.participantStatusListeners) {
                    f2.m5915l(str);
                }
            }
        } else if (z) {
            for (UserStatusListener d22 : this.userStatusListeners) {
                d22.m5940h();
            }
        } else {
            for (ParticipantStatusListener f22 : this.participantStatusListeners) {
                f22.m5913j(str);
            }
        }
        if ("owner".equals(mUCAffiliation) || !"owner".equals(mUCAffiliation2)) {
            if ("admin".equals(mUCAffiliation) || !"admin".equals(mUCAffiliation2)) {
                if (!"member".equals(mUCAffiliation) && "member".equals(mUCAffiliation2)) {
                    if (z) {
                        for (UserStatusListener d222 : this.userStatusListeners) {
                            d222.m5935c();
                        }
                        return;
                    }
                    for (ParticipantStatusListener f222 : this.participantStatusListeners) {
                        f222.m5908e(str);
                    }
                }
            } else if (z) {
                for (UserStatusListener d2222 : this.userStatusListeners) {
                    d2222.m5941i();
                }
            } else {
                for (ParticipantStatusListener f2222 : this.participantStatusListeners) {
                    f2222.m5914k(str);
                }
            }
        } else if (z) {
            for (UserStatusListener d22222 : this.userStatusListeners) {
                d22222.m5939g();
            }
        } else {
            for (ParticipantStatusListener f22222 : this.participantStatusListeners) {
                f22222.m5912i(str);
            }
        }
    }

    private void checkPresenceCode(Set<Status> set, boolean z, MUCUser mUCUser, String str) {
        if (set.contains(Status.KICKED_307)) {
            if (z) {
                this.joined = false;
                for (UserStatusListener a : this.userStatusListeners) {
                    a.m5932a(mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
                this.occupantsMap.clear();
                this.nickname = null;
                userHasLeft();
            } else {
                for (ParticipantStatusListener a2 : this.participantStatusListeners) {
                    a2.m5903a(str, mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
            }
        }
        if (set.contains(Status.BANNED_301)) {
            if (z) {
                this.joined = false;
                for (UserStatusListener a3 : this.userStatusListeners) {
                    a3.m5934b(mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
                this.occupantsMap.clear();
                this.nickname = null;
                userHasLeft();
            } else {
                for (ParticipantStatusListener a22 : this.participantStatusListeners) {
                    a22.m5905b(str, mUCUser.getItem().getActor(), mUCUser.getItem().getReason());
                }
            }
        }
        if (set.contains(Status.REMOVED_AFFIL_CHANGE_321) && z) {
            this.joined = false;
            for (UserStatusListener a32 : this.userStatusListeners) {
                a32.m5936d();
            }
            this.occupantsMap.clear();
            this.nickname = null;
            userHasLeft();
        }
        if (set.contains(Status.NEW_NICKNAME_303)) {
            for (ParticipantStatusListener a222 : this.participantStatusListeners) {
                a222.m5902a(str, mUCUser.getItem().getNick());
            }
        }
    }

    private void checkRoleModifications(MUCRole mUCRole, MUCRole mUCRole2, boolean z, String str) {
        if (("visitor".equals(mUCRole) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole)) && "participant".equals(mUCRole2)) {
            if (z) {
                for (UserStatusListener a : this.userStatusListeners) {
                    a.m5931a();
                }
            } else {
                for (ParticipantStatusListener c : this.participantStatusListeners) {
                    c.m5906c(str);
                }
            }
        } else if ("participant".equals(mUCRole) && ("visitor".equals(mUCRole2) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole2))) {
            if (z) {
                for (UserStatusListener a2 : this.userStatusListeners) {
                    a2.m5933b();
                }
            } else {
                for (ParticipantStatusListener c2 : this.participantStatusListeners) {
                    c2.m5907d(str);
                }
            }
        }
        if (!"moderator".equals(mUCRole) && "moderator".equals(mUCRole2)) {
            if ("visitor".equals(mUCRole) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole)) {
                if (z) {
                    for (UserStatusListener a22 : this.userStatusListeners) {
                        a22.m5931a();
                    }
                } else {
                    for (ParticipantStatusListener c22 : this.participantStatusListeners) {
                        c22.m5906c(str);
                    }
                }
            }
            if (z) {
                for (UserStatusListener a222 : this.userStatusListeners) {
                    a222.m5937e();
                }
                return;
            }
            for (ParticipantStatusListener c222 : this.participantStatusListeners) {
                c222.m5910g(str);
            }
        } else if ("moderator".equals(mUCRole) && !"moderator".equals(mUCRole2)) {
            if ("visitor".equals(mUCRole2) || PrivacyItem.SUBSCRIPTION_NONE.equals(mUCRole2)) {
                if (z) {
                    for (UserStatusListener a2222 : this.userStatusListeners) {
                        a2222.m5933b();
                    }
                } else {
                    for (ParticipantStatusListener c2222 : this.participantStatusListeners) {
                        c2222.m5907d(str);
                    }
                }
            }
            if (z) {
                for (UserStatusListener a22222 : this.userStatusListeners) {
                    a22222.m5938f();
                }
                return;
            }
            for (ParticipantStatusListener c22222 : this.participantStatusListeners) {
                c22222.m5911h(str);
            }
        }
    }

    private Presence enter(String str, String str2, DiscussionHistory discussionHistory, long j) throws NotConnectedException, NoResponseException, XMPPErrorException {
        XMPPErrorException e;
        StringUtils.requireNotNullOrEmpty(str, "Nickname must not be null or blank.");
        Stanza presence = new Presence(Type.available);
        presence.setTo(this.room + "/" + str);
        ExtensionElement mUCInitialPresence = new MUCInitialPresence();
        if (str2 != null) {
            mUCInitialPresence.setPassword(str2);
        }
        if (discussionHistory != null) {
            mUCInitialPresence.setHistory(discussionHistory.getMUCHistory());
        }
        presence.addExtension(mUCInitialPresence);
        StanzaFilter andFilter = new AndFilter(FromMatchesFilter.createFull(this.room + "/" + str), new StanzaTypeFilter(Presence.class));
        this.connection.addSyncStanzaListener(this.messageListener, this.fromRoomGroupchatFilter);
        this.connection.addSyncStanzaListener(this.presenceListener, new AndFilter(this.fromRoomFilter, StanzaTypeFilter.PRESENCE));
        this.connection.addSyncStanzaListener(this.subjectListener, new AndFilter(this.fromRoomFilter, MessageWithSubjectFilter.INSTANCE));
        this.connection.addSyncStanzaListener(this.declinesListener, new AndFilter(new StanzaExtensionFilter(DataForm.ELEMENT, MUCUser.NAMESPACE), new NotFilter(MessageTypeFilter.ERROR)));
        this.connection.addPacketInterceptor(this.presenceInterceptor, new AndFilter(new ToFilter(this.room), StanzaTypeFilter.PRESENCE));
        this.messageCollector = this.connection.createPacketCollector(this.fromRoomGroupchatFilter);
        try {
            Presence presence2 = (Presence) this.connection.createPacketCollectorAndSend(andFilter, presence).nextResultOrThrow(j);
            this.nickname = str;
            this.joined = true;
            this.multiUserChatManager.addJoinedRoom(this.room);
            return presence2;
        } catch (NoResponseException e2) {
            e = e2;
            removeConnectionCallbacks();
            throw e;
        } catch (XMPPErrorException e3) {
            e = e3;
            removeConnectionCallbacks();
            throw e;
        }
    }

    private void fireInvitationRejectionListeners(String str, String str2) {
        synchronized (this.invitationRejectionListeners) {
            InvitationRejectionListener[] invitationRejectionListenerArr = new InvitationRejectionListener[this.invitationRejectionListeners.size()];
            this.invitationRejectionListeners.toArray(invitationRejectionListenerArr);
        }
        for (InvitationRejectionListener a : invitationRejectionListenerArr) {
            a.m5956a(str, str2);
        }
    }

    private List<Affiliate> getAffiliatesByAdmin(MUCAffiliation mUCAffiliation) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.get);
        mUCAdmin.addItem(new MUCItem(mUCAffiliation));
        MUCAdmin mUCAdmin2 = (MUCAdmin) this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
        List<Affiliate> arrayList = new ArrayList();
        for (MUCItem affiliate : mUCAdmin2.getItems()) {
            arrayList.add(new Affiliate(affiliate));
        }
        return arrayList;
    }

    private List<Occupant> getOccupants(MUCRole mUCRole) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.setTo(this.room);
        mUCAdmin.setType(IQ.Type.get);
        mUCAdmin.addItem(new MUCItem(mUCRole));
        MUCAdmin mUCAdmin2 = (MUCAdmin) this.connection.createPacketCollectorAndSend(mUCAdmin).nextResultOrThrow();
        List<Occupant> arrayList = new ArrayList();
        for (MUCItem occupant : mUCAdmin2.getItems()) {
            arrayList.add(new Occupant(occupant));
        }
        return arrayList;
    }

    private void removeConnectionCallbacks() {
        this.connection.removeSyncStanzaListener(this.messageListener);
        this.connection.removeSyncStanzaListener(this.presenceListener);
        this.connection.removeSyncStanzaListener(this.declinesListener);
        this.connection.removePacketInterceptor(this.presenceInterceptor);
        if (this.messageCollector != null) {
            this.messageCollector.cancel();
            this.messageCollector = null;
        }
    }

    private synchronized void userHasLeft() {
        this.multiUserChatManager.removeJoinedRoom(this.room);
        removeConnectionCallbacks();
    }

    public boolean addInvitationRejectionListener(InvitationRejectionListener invitationRejectionListener) {
        return this.invitationRejectionListeners.add(invitationRejectionListener);
    }

    public boolean addMessageListener(MessageListener messageListener) {
        return this.messageListeners.add(messageListener);
    }

    public boolean addParticipantListener(PresenceListener presenceListener) {
        return this.presenceListeners.add(presenceListener);
    }

    public boolean addParticipantStatusListener(ParticipantStatusListener participantStatusListener) {
        return this.participantStatusListeners.add(participantStatusListener);
    }

    public void addPresenceInterceptor(PresenceListener presenceListener) {
        this.presenceInterceptors.add(presenceListener);
    }

    public boolean addSubjectUpdatedListener(SubjectUpdatedListener subjectUpdatedListener) {
        return this.subjectUpdatedListeners.add(subjectUpdatedListener);
    }

    public boolean addUserStatusListener(UserStatusListener userStatusListener) {
        return this.userStatusListeners.add(userStatusListener);
    }

    public void banUser(String str, String str2) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin(str, MUCAffiliation.outcast, str2);
    }

    public void banUsers(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.outcast);
    }

    public void changeAvailabilityStatus(String str, Mode mode) throws NotConnectedException {
        StringUtils.requireNotNullOrEmpty(this.nickname, "Nickname must not be null or blank.");
        if (this.joined) {
            Stanza presence = new Presence(Type.available);
            presence.setStatus(str);
            presence.setMode(mode);
            presence.setTo(this.room + "/" + this.nickname);
            this.connection.sendStanza(presence);
            return;
        }
        throw new IllegalStateException("Must be logged into the room to change the availability status.");
    }

    public void changeNickname(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        StringUtils.requireNotNullOrEmpty(str, "Nickname must not be null or blank.");
        if (this.joined) {
            Stanza presence = new Presence(Type.available);
            presence.setTo(this.room + "/" + str);
            this.connection.createPacketCollectorAndSend(new AndFilter(FromMatchesFilter.createFull(this.room + "/" + str), new StanzaTypeFilter(Presence.class)), presence).nextResultOrThrow();
            this.nickname = str;
            return;
        }
        throw new IllegalStateException("Must be logged into the room to change nickname.");
    }

    public void changeSubject(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        Stanza createMessage = createMessage();
        createMessage.setSubject(str);
        this.connection.createPacketCollectorAndSend(new AndFilter(this.fromRoomGroupchatFilter, new C02426(str)), createMessage).nextResultOrThrow();
    }

    public synchronized void create(String str) throws NoResponseException, XMPPErrorException, SmackException {
        if (this.joined) {
            throw new IllegalStateException("Creation failed - User already joined the room.");
        } else if (!createOrJoin(str)) {
            leave();
            throw new SmackException("Creation failed - Missing acknowledge of room creation.");
        }
    }

    public Message createMessage() {
        return new Message(this.room, Message.Type.groupchat);
    }

    public synchronized boolean createOrJoin(String str) throws NoResponseException, XMPPErrorException, SmackException {
        return createOrJoin(str, null, null, this.connection.getPacketReplyTimeout());
    }

    public synchronized boolean createOrJoin(String str, String str2, DiscussionHistory discussionHistory, long j) throws NoResponseException, XMPPErrorException, SmackException {
        boolean z;
        if (this.joined) {
            throw new IllegalStateException("Creation failed - User already joined the room.");
        }
        MUCUser from = MUCUser.from(enter(str, str2, discussionHistory, j));
        z = from != null && from.getStatus().contains(Status.ROOM_CREATED_201);
        return z;
    }

    public Chat createPrivateChat(String str, ChatMessageListener chatMessageListener) {
        return ChatManager.getInstanceFor(this.connection).createChat(str, chatMessageListener);
    }

    public void destroy(String str, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.set);
        Destroy destroy = new Destroy();
        destroy.setReason(str);
        destroy.setJid(str2);
        mUCOwner.setDestroy(destroy);
        this.connection.createPacketCollectorAndSend(mUCOwner).nextResultOrThrow();
        this.occupantsMap.clear();
        this.nickname = null;
        this.joined = false;
        userHasLeft();
    }

    public List<Affiliate> getAdmins() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getAffiliatesByAdmin(MUCAffiliation.admin);
    }

    public Form getConfigurationForm() throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.get);
        return Form.getFormFrom((IQ) this.connection.createPacketCollectorAndSend(mUCOwner).nextResultOrThrow());
    }

    public List<Affiliate> getMembers() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getAffiliatesByAdmin(MUCAffiliation.member);
    }

    public List<Occupant> getModerators() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getOccupants(MUCRole.moderator);
    }

    public String getNickname() {
        return this.nickname;
    }

    public Occupant getOccupant(String str) {
        Presence presence = (Presence) this.occupantsMap.get(str);
        return presence != null ? new Occupant(presence) : null;
    }

    public Presence getOccupantPresence(String str) {
        return (Presence) this.occupantsMap.get(str);
    }

    public List<String> getOccupants() {
        return Collections.unmodifiableList(new ArrayList(this.occupantsMap.keySet()));
    }

    public int getOccupantsCount() {
        return this.occupantsMap.size();
    }

    public List<Affiliate> getOutcasts() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getAffiliatesByAdmin(MUCAffiliation.outcast);
    }

    public List<Affiliate> getOwners() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getAffiliatesByAdmin(MUCAffiliation.owner);
    }

    public List<Occupant> getParticipants() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getOccupants(MUCRole.participant);
    }

    public Form getRegistrationForm() throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ registration = new Registration();
        registration.setType(IQ.Type.get);
        registration.setTo(this.room);
        return Form.getFormFrom((IQ) this.connection.createPacketCollectorAndSend(registration).nextResultOrThrow());
    }

    public String getReservedNickname() throws SmackException {
        try {
            Iterator it = ServiceDiscoveryManager.getInstanceFor(this.connection).discoverInfo(this.room, "x-roomuser-item").getIdentities().iterator();
            if (it.hasNext()) {
                return ((Identity) it.next()).getName();
            }
        } catch (Throwable e) {
            LOGGER.log(Level.SEVERE, "Error retrieving room nickname", e);
        }
        return null;
    }

    public String getRoom() {
        return this.room;
    }

    public String getSubject() {
        return this.subject;
    }

    public void grantAdmin(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin(str, MUCAffiliation.admin);
    }

    public void grantAdmin(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.admin);
    }

    public void grantMembership(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin(str, MUCAffiliation.member, null);
    }

    public void grantMembership(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.member);
    }

    public void grantModerator(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(str, MUCRole.moderator, null);
    }

    public void grantModerator(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(collection, MUCRole.moderator);
    }

    public void grantOwnership(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin(str, MUCAffiliation.owner, null);
    }

    public void grantOwnership(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.owner);
    }

    public void grantVoice(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(str, MUCRole.participant, null);
    }

    public void grantVoice(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(collection, MUCRole.participant);
    }

    public void invite(String str, String str2) throws NotConnectedException {
        invite(new Message(), str, str2);
    }

    public void invite(Message message, String str, String str2) throws NotConnectedException {
        message.setTo(this.room);
        ExtensionElement mUCUser = new MUCUser();
        Invite invite = new Invite();
        invite.setTo(str);
        invite.setReason(str2);
        mUCUser.setInvite(invite);
        message.addExtension(mUCUser);
        this.connection.sendStanza(message);
    }

    public boolean isJoined() {
        return this.joined;
    }

    public void join(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        join(str, null, null, this.connection.getPacketReplyTimeout());
    }

    public void join(String str, String str2) throws XMPPErrorException, SmackException {
        join(str, str2, null, this.connection.getPacketReplyTimeout());
    }

    public synchronized void join(String str, String str2, DiscussionHistory discussionHistory, long j) throws XMPPErrorException, NoResponseException, NotConnectedException {
        if (this.joined) {
            leave();
        }
        enter(str, str2, discussionHistory, j);
    }

    public void kickParticipant(String str, String str2) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(str, MUCRole.none, str2);
    }

    public synchronized void leave() throws NotConnectedException {
        if (this.joined) {
            Stanza presence = new Presence(Type.unavailable);
            presence.setTo(this.room + "/" + this.nickname);
            this.connection.sendStanza(presence);
            this.occupantsMap.clear();
            this.nickname = null;
            this.joined = false;
            userHasLeft();
        }
    }

    public Message nextMessage() throws MUCNotJoinedException {
        if (this.messageCollector != null) {
            return (Message) this.messageCollector.nextResult();
        }
        throw new MUCNotJoinedException(this);
    }

    public Message nextMessage(long j) throws MUCNotJoinedException {
        if (this.messageCollector != null) {
            return (Message) this.messageCollector.nextResult(j);
        }
        throw new MUCNotJoinedException(this);
    }

    public Message pollMessage() throws MUCNotJoinedException {
        if (this.messageCollector != null) {
            return (Message) this.messageCollector.pollResult();
        }
        throw new MUCNotJoinedException(this);
    }

    public boolean removeInvitationRejectionListener(InvitationRejectionListener invitationRejectionListener) {
        return this.invitationRejectionListeners.remove(invitationRejectionListener);
    }

    public boolean removeMessageListener(MessageListener messageListener) {
        return this.messageListeners.remove(messageListener);
    }

    public boolean removeParticipantListener(PresenceListener presenceListener) {
        return this.presenceListeners.remove(presenceListener);
    }

    public boolean removeParticipantStatusListener(ParticipantStatusListener participantStatusListener) {
        return this.participantStatusListeners.remove(participantStatusListener);
    }

    public void removePresenceInterceptor(StanzaListener stanzaListener) {
        this.presenceInterceptors.remove(stanzaListener);
    }

    public boolean removeSubjectUpdatedListener(SubjectUpdatedListener subjectUpdatedListener) {
        return this.subjectUpdatedListeners.remove(subjectUpdatedListener);
    }

    public boolean removeUserStatusListener(UserStatusListener userStatusListener) {
        return this.userStatusListeners.remove(userStatusListener);
    }

    public void requestVoice() throws NotConnectedException {
        ExtensionElement dataForm = new DataForm(DataForm.Type.submit);
        FormField formField = new FormField(FormField.FORM_TYPE);
        formField.addValue("http://jabber.org/protocol/muc#request");
        dataForm.addField(formField);
        formField = new FormField("muc#role");
        formField.setType(FormField.Type.text_single);
        formField.setLabel("Requested role");
        formField.addValue("participant");
        dataForm.addField(formField);
        Stanza message = new Message(this.room);
        message.addExtension(dataForm);
        this.connection.sendStanza(message);
    }

    public void revokeAdmin(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin(str, MUCAffiliation.member);
    }

    public void revokeAdmin(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.admin);
    }

    public void revokeMembership(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin(str, MUCAffiliation.none, null);
    }

    public void revokeMembership(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.none);
    }

    public void revokeModerator(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(str, MUCRole.participant, null);
    }

    public void revokeModerator(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(collection, MUCRole.participant);
    }

    public void revokeOwnership(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin(str, MUCAffiliation.admin, null);
    }

    public void revokeOwnership(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeAffiliationByAdmin((Collection) collection, MUCAffiliation.admin);
    }

    public void revokeVoice(String str) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(str, MUCRole.visitor, null);
    }

    public void revokeVoice(Collection<String> collection) throws XMPPErrorException, NoResponseException, NotConnectedException {
        changeRole(collection, MUCRole.visitor);
    }

    public void sendConfigurationForm(Form form) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ mUCOwner = new MUCOwner();
        mUCOwner.setTo(this.room);
        mUCOwner.setType(IQ.Type.set);
        mUCOwner.addExtension(form.getDataFormToSend());
        this.connection.createPacketCollectorAndSend(mUCOwner).nextResultOrThrow();
    }

    public void sendMessage(String str) throws NotConnectedException {
        Stanza createMessage = createMessage();
        createMessage.setBody(str);
        this.connection.sendStanza(createMessage);
    }

    public void sendMessage(Message message) throws NotConnectedException {
        message.setTo(this.room);
        message.setType(Message.Type.groupchat);
        this.connection.sendStanza(message);
    }

    public void sendRegistrationForm(Form form) throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ registration = new Registration();
        registration.setType(IQ.Type.set);
        registration.setTo(this.room);
        registration.addExtension(form.getDataFormToSend());
        this.connection.createPacketCollectorAndSend(registration).nextResultOrThrow();
    }

    public String toString() {
        return "MUC: " + this.room + "(" + this.connection.getUser() + ")";
    }
}
