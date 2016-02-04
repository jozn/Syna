package mobi.mmdt.ott.asmackengine.groupchat;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mobi.mmdt.ott.asmackengine.core.Core;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.jivesoftware.smackx.bookmarks.BookmarkManager;
import org.jivesoftware.smackx.bookmarks.BookmarkedConference;
import org.jivesoftware.smackx.chatstates.ChatState;
import org.jivesoftware.smackx.chatstates.packet.ChatStateExtension;
import org.jivesoftware.smackx.muc.Affiliate;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.muc.MultiUserChatManager;
import org.jivesoftware.smackx.xdata.Form;

/* renamed from: mobi.mmdt.ott.asmackengine.groupchat.a */
public class Group {
    private static Group f3295a;
    private GroupListener f3296b;
    private HashMap<String, MultiUserChat> f3297c;
    private InvitationListener f3298d;

    private Group() {
        this.f3296b = null;
        this.f3297c = new HashMap();
        this.f3298d = new Group$1(this);
    }

    public static Group m4235a() {
        if (f3295a == null) {
            f3295a = new Group();
        }
        return f3295a;
    }

    private void m4237a(int i, String str, String str2, String str3) throws NotConnectedException {
        ((MultiUserChat) this.f3297c.get(str)).invite(str2, str3);
        Thread thread = new Thread(new Group(this, str, str2, i, str3));
        thread.setPriority(1);
        thread.start();
    }

    public String m4239a(String str, String str2, String str3, Map<String, String> map) throws NotConnectedException, XMPPException {
        if (Core.m4198a().m4207a(true)) {
            MultiUserChat multiUserChat = (MultiUserChat) this.f3297c.get(str);
            if (multiUserChat != null) {
                Message message = new Message(str, Type.groupchat);
                message.setBody(str2);
                if (str3 != null) {
                    message.setStanzaId(str3);
                }
                for (String str4 : map.keySet()) {
                    String str5 = (String) map.get(str4);
                    if (str5 != null) {
                        message.addBody(str4, str5);
                    }
                }
                multiUserChat.sendMessage(message);
                return str3;
            }
            throw new NotConnectedException();
        }
        throw new NotConnectedException();
    }

    public void m4240a(String str) throws XMPPException, SmackException {
        Log.d("Group", "Remove bookmark group " + str + " " + m4253d(str));
        if (Core.m4198a().m4207a(false)) {
            BookmarkManager.getBookmarkManager(Core.m4198a().m4210d()).removeBookmarkedConference(str);
            return;
        }
        throw new NotConnectedException();
    }

    public void m4241a(String str, String str2) throws XMPPException, SmackException {
        if (Core.m4198a().m4207a(false)) {
            Log.d("Group", "add bookmark group " + str2 + " " + str);
            BookmarkManager.getBookmarkManager(Core.m4198a().m4210d()).addBookmarkedConference(str, str2, true, null, null);
            return;
        }
        throw new NotConnectedException();
    }

    public void m4242a(String str, String str2, String str3) throws NotConnectedException {
        if (Core.m4198a().m4207a(true)) {
            Log.d("AsmackEngine", "Invite to chat : " + str + " " + str3 + " " + str2);
            m4237a(0, str, str2, str3);
            return;
        }
        throw new NotConnectedException();
    }

    public void m4243a(String str, boolean z) throws XMPPException, SmackException {
        if (Core.m4198a().m4207a(false)) {
            Log.d("AsmackEngine", "Join Group : " + str + " " + " " + z + " ");
            MultiUserChat multiUserChat = this.f3297c.containsKey(str) ? (MultiUserChat) this.f3297c.get(str) : MultiUserChatManager.getInstanceFor(Core.m4198a().m4210d()).getMultiUserChat(str);
            if (!multiUserChat.isJoined()) {
                multiUserChat.addMessageListener(new MyPacketListener(this.f3296b));
                DiscussionHistory discussionHistory = new DiscussionHistory();
                discussionHistory.setMaxStanzas(25);
                multiUserChat.join(Core.m4198a().m4208b(), null, discussionHistory, 20000);
                if (z) {
                    m4241a(m4253d(str), str);
                }
                this.f3297c.put(str, multiUserChat);
                return;
            }
            return;
        }
        throw new NotConnectedException();
    }

    public void m4244a(GroupListener groupListener) {
        this.f3296b = groupListener;
    }

    public void m4245a(XMPPConnection xMPPConnection) {
        MultiUserChatManager.getInstanceFor(Core.m4198a().m4210d()).addInvitationListener(this.f3298d);
    }

    public void m4246a(ChatState chatState, String str) throws NotConnectedException, XMPPException {
        Message message = new Message();
        message.setType(Type.groupchat);
        message.setFrom(new StringBuilder(String.valueOf(Core.m4198a().m4208b())).append("@").append(Core.m4198a().m4209c()).append("/Smack").toString());
        message.setTo(str);
        message.addExtension(new ChatStateExtension(chatState));
        MultiUserChat multiUserChat = (MultiUserChat) this.f3297c.get(str);
        if (multiUserChat != null) {
            multiUserChat.sendMessage(message);
        }
    }

    public void m4247b() {
        this.f3297c.clear();
    }

    public void m4248b(String str, String str2) throws NoResponseException, SmackException, XMPPException {
        if (Core.m4198a().m4207a(true)) {
            Log.d("AsmackEngine", "Create Group ....  " + str + " " + str2);
            MultiUserChat multiUserChat = MultiUserChatManager.getInstanceFor(Core.m4198a().m4210d()).getMultiUserChat(str);
            multiUserChat.addMessageListener(new MyPacketListener(this.f3296b));
            multiUserChat.create(Core.m4198a().m4208b());
            Form createAnswerForm = multiUserChat.getConfigurationForm().createAnswerForm();
            createAnswerForm.setAnswer("muc#roomconfig_persistentroom", true);
            createAnswerForm.setAnswer("muc#roomconfig_roomdesc", str2);
            createAnswerForm.setAnswer("muc#roomconfig_membersonly", true);
            List arrayList = new ArrayList();
            arrayList.add("anyone");
            createAnswerForm.setAnswer("muc#roomconfig_whois", arrayList);
            createAnswerForm.setAnswer("muc#roomconfig_allowinvites", true);
            createAnswerForm.setAnswer("muc#roomconfig_enablelogging", true);
            multiUserChat.sendConfigurationForm(createAnswerForm);
            if (!multiUserChat.isJoined()) {
                DiscussionHistory discussionHistory = new DiscussionHistory();
                discussionHistory.setMaxStanzas(25);
                multiUserChat.addMessageListener(new MyPacketListener(this.f3296b));
                multiUserChat.join(Core.m4198a().m4208b(), null, discussionHistory, 20000);
            }
            m4241a(str2, str);
            this.f3297c.put(str, multiUserChat);
            return;
        }
        throw new NotConnectedException();
    }

    public String[] m4249b(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        int i = 0;
        if (Core.m4198a().m4207a(false)) {
            MultiUserChat multiUserChat = (MultiUserChat) this.f3297c.get(str);
            if (multiUserChat == null) {
                return new String[0];
            }
            Collection<Affiliate> owners = multiUserChat.getOwners();
            String[] strArr = new String[owners.size()];
            for (Affiliate jid : owners) {
                int i2 = i + 1;
                strArr[i] = jid.getJid();
                i = i2;
            }
            return strArr;
        }
        throw new NotConnectedException();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m4250c() {
        /*
        r7 = this;
        r0 = 0;
        r1 = r7.f3297c;
        r1.clear();
        r1 = r7.m4254d();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r2 = new java.util.ArrayList;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r2.<init>();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r3 = new java.util.ArrayList;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r3.<init>();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r4 = r1.length;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        if (r4 <= 0) goto L_0x002e;
    L_0x0017:
        r4 = r1.length;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        if (r0 < r4) goto L_0x002f;
    L_0x001a:
        r1 = r3.iterator();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
    L_0x001e:
        r0 = r1.hasNext();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        if (r0 != 0) goto L_0x00a6;
    L_0x0024:
        r1 = r2.iterator();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
    L_0x0028:
        r0 = r1.hasNext();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        if (r0 != 0) goto L_0x00e4;
    L_0x002e:
        return;
    L_0x002f:
        r4 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r4 = r4.getJid();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        if (r4 == 0) goto L_0x0071;
    L_0x0037:
        r4 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r4 = r4.getName();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        if (r4 != 0) goto L_0x0071;
    L_0x003f:
        r4 = "Group";
        r5 = new java.lang.StringBuilder;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = "should delete Bookmark group ";
        r5.<init>(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r6.getJid();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r5 = r5.append(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = " ";
        r5 = r5.append(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r6.getName();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r5 = r5.append(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r5 = r5.toString();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        android.util.Log.d(r4, r5);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r4 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r2.add(r4);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
    L_0x006e:
        r0 = r0 + 1;
        goto L_0x0017;
    L_0x0071:
        r4 = "Group";
        r5 = new java.lang.StringBuilder;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = "should join Bookmark group ";
        r5.<init>(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r6.getJid();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r5 = r5.append(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = " ";
        r5 = r5.append(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r6 = r6.getName();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r5 = r5.append(r6);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r5 = r5.toString();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        android.util.Log.d(r4, r5);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r4 = r1[r0];	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r3.add(r4);	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        goto L_0x006e;
    L_0x00a1:
        r0 = move-exception;
    L_0x00a2:
        r0.printStackTrace();
        goto L_0x002e;
    L_0x00a6:
        r0 = r1.next();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r0 = (org.jivesoftware.smackx.bookmarks.BookmarkedConference) r0;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r3 = "Group";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00dc }
        r5 = "join Bookmark group ";
        r4.<init>(r5);	 Catch:{ Exception -> 0x00dc }
        r5 = r0.getJid();	 Catch:{ Exception -> 0x00dc }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00dc }
        r5 = " ";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00dc }
        r5 = r0.getName();	 Catch:{ Exception -> 0x00dc }
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00dc }
        r4 = r4.toString();	 Catch:{ Exception -> 0x00dc }
        android.util.Log.d(r3, r4);	 Catch:{ Exception -> 0x00dc }
        r0 = r0.getJid();	 Catch:{ Exception -> 0x00dc }
        r3 = 0;
        r7.m4243a(r0, r3);	 Catch:{ Exception -> 0x00dc }
        goto L_0x001e;
    L_0x00dc:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        goto L_0x001e;
    L_0x00e2:
        r0 = move-exception;
        goto L_0x00a2;
    L_0x00e4:
        r0 = r1.next();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r0 = (org.jivesoftware.smackx.bookmarks.BookmarkedConference) r0;	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        r2 = "Group";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0119 }
        r4 = "leave Bookmark group";
        r3.<init>(r4);	 Catch:{ Exception -> 0x0119 }
        r4 = r0.getJid();	 Catch:{ Exception -> 0x0119 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0119 }
        r4 = " ";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0119 }
        r4 = r0.getName();	 Catch:{ Exception -> 0x0119 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0119 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0119 }
        android.util.Log.d(r2, r3);	 Catch:{ Exception -> 0x0119 }
        r0 = r0.getJid();	 Catch:{ Exception -> 0x0119 }
        r7.m4251c(r0);	 Catch:{ Exception -> 0x0119 }
        goto L_0x0028;
    L_0x0119:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ XMPPException -> 0x00a1, SmackException -> 0x00e2 }
        goto L_0x0028;
        */
        throw new UnsupportedOperationException("Method not decompiled: mobi.mmdt.ott.asmackengine.groupchat.a.c():void");
    }

    public void m4251c(String str) throws XMPPException, SmackException {
        if (Core.m4198a().m4207a(true)) {
            MultiUserChat multiUserChat = (MultiUserChat) this.f3297c.remove(str);
            MultiUserChat multiUserChat2 = multiUserChat == null ? MultiUserChatManager.getInstanceFor(Core.m4198a().m4210d()).getMultiUserChat(str) : multiUserChat;
            try {
                multiUserChat2.revokeMembership(new StringBuilder(String.valueOf(Core.m4198a().m4208b())).append("@").append(Core.m4198a().m4209c()).toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                multiUserChat2.leave();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                m4240a(str);
                return;
            } catch (Exception e22) {
                e22.printStackTrace();
                return;
            }
        }
        throw new NotConnectedException();
    }

    public void m4252c(String str, String str2) throws XMPPErrorException, NoResponseException, NotConnectedException {
        if (Core.m4198a().m4207a(false)) {
            ((MultiUserChat) this.f3297c.get(str)).grantOwnership(str2);
            return;
        }
        throw new NotConnectedException();
    }

    public String m4253d(String str) throws NotConnectedException, NoResponseException, XMPPErrorException {
        if (Core.m4198a().m4207a(false)) {
            return MultiUserChatManager.getInstanceFor(Core.m4198a().m4210d()).getRoomInfo(str).getDescription();
        }
        throw new NotConnectedException();
    }

    public BookmarkedConference[] m4254d() throws XMPPException, SmackException {
        int i = 0;
        if (Core.m4198a().m4207a(false)) {
            Collection bookmarkedConferences = BookmarkManager.getBookmarkManager(Core.m4198a().m4210d()).getBookmarkedConferences();
            BookmarkedConference[] bookmarkedConferenceArr = (BookmarkedConference[]) bookmarkedConferences.toArray(new BookmarkedConference[bookmarkedConferences.size()]);
            while (i < bookmarkedConferenceArr.length) {
                Log.d("Group", "get bookmark group " + bookmarkedConferenceArr[i].getJid() + " " + bookmarkedConferenceArr[i].getName());
                i++;
            }
            return bookmarkedConferenceArr;
        }
        throw new NotConnectedException();
    }
}
