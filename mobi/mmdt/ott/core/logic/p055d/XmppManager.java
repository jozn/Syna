package mobi.mmdt.ott.core.logic.p055d;

import java.io.IOException;
import java.util.Map;
import mobi.mmdt.ott.asmackengine.AsmackListener;
import mobi.mmdt.ott.asmackengine.chat.Chat.Chat;
import mobi.mmdt.ott.asmackengine.profile.Profile.Profile;
import mobi.mmdt.ott.core.model.database.p065f.p066a.ChatStates;
import mobi.mmdt.ott.core.p051a.CountryTools;
import org.linphone.core.VideoSize;

/* renamed from: mobi.mmdt.ott.core.logic.d.l */
class XmppManager implements AsmackListener {
    private static /* synthetic */ int[] f3727b;
    final /* synthetic */ XmppManager f3728a;

    XmppManager(XmppManager xmppManager) {
        this.f3728a = xmppManager;
    }

    static /* synthetic */ int[] m4753c() {
        int[] iArr = f3727b;
        if (iArr == null) {
            iArr = new int[Profile.values().length];
            try {
                iArr[Profile.AVAILABLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Profile.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Profile.SUBSCRIBE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Profile.SUBSCRIBED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[Profile.UNAVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[Profile.UNSUBSCRIBE.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[Profile.UNSUBSCRIBED.ordinal()] = 6;
            } catch (NoSuchFieldError e7) {
            }
            f3727b = iArr;
        }
        return iArr;
    }

    public void m4754a() {
        if (this.f3728a.f3714e != null) {
            this.f3728a.f3714e.m4657a();
        }
    }

    public void m4755a(String str, String str2) {
        if (this.f3728a.f3714e != null) {
            MessageManager.m4703a(this.f3728a.f3712c).m4711a(CountryTools.m4416a().m4435h(str2.split("@")[0]), str);
        }
    }

    public void m4756a(String str, String str2, String str3) {
        this.f3728a.m4736a(str.split("@")[0], false);
    }

    public void m4757a(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.f3728a.f3714e != null) {
            String str5 = str2.split("@")[0];
            String str6 = str.split("@")[0];
            if (str5.matches("[0-9]+")) {
                str5 = CountryTools.m4416a().m4435h(str5);
            }
            try {
                MessageManager.m4703a(this.f3728a.f3712c).m4712a(str6, str5, str4, str3, (Map) map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void m4758a(String str, String str2, String str3, Map<String, String> map) {
        if (this.f3728a.f3714e != null) {
            String str4 = str.split("@")[0];
            if (str4.matches("[0-9]+")) {
                str4 = CountryTools.m4416a().m4435h(str4);
            }
            try {
                MessageManager.m4703a(this.f3728a.f3712c).m4712a(str4, str4, str3, str2, (Map) map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void m4759a(String str, Profile profile) {
        String h = CountryTools.m4416a().m4435h(str.split("@")[0]);
        if (this.f3728a.f3714e != null) {
            switch (XmppManager.m4753c()[profile.ordinal()]) {
                case VideoSize.CIF /*1*/:
                    this.f3728a.f3714e.m4658a(h, 1);
                default:
                    this.f3728a.f3714e.m4658a(h, 2);
            }
        }
    }

    public void m4760a(boolean z, String str, String str2, Chat chat) {
        String str3;
        String h;
        long a;
        if (z) {
            str3 = str.split("@")[0];
            h = CountryTools.m4416a().m4435h(str2.split("@")[0]);
            a = ChatStates.m5077a(this.f3728a.f3712c, str3);
            if (a == -1) {
                ChatStates.m5078a(this.f3728a.f3712c, str3, h, chat.toString());
                return;
            } else {
                ChatStates.m5076a(this.f3728a.f3712c, a, str3, h, chat.toString());
                return;
            }
        }
        str3 = CountryTools.m4416a().m4435h(str.split("@")[0]);
        h = CountryTools.m4416a().m4435h(str2.split("@")[0]);
        a = ChatStates.m5077a(this.f3728a.f3712c, h);
        if (a == -1) {
            ChatStates.m5078a(this.f3728a.f3712c, str3, h, chat.toString());
        } else {
            ChatStates.m5076a(this.f3728a.f3712c, a, str3, h, chat.toString());
        }
    }

    public void m4761b() {
        try {
            if (this.f3728a.f3714e != null) {
                this.f3728a.f3714e.m4659b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread thread = new Thread(new XmppManager(this));
            thread.setPriority(1);
            thread.start();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
