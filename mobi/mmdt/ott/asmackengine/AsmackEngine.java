package mobi.mmdt.ott.asmackengine;

import java.util.Map;
import mobi.mmdt.ott.asmackengine.chat.Chat.Chat;
import mobi.mmdt.ott.asmackengine.chat.ChatListener;

/* renamed from: mobi.mmdt.ott.asmackengine.b */
class AsmackEngine implements ChatListener {
    final /* synthetic */ AsmackEngine f3246a;

    AsmackEngine(AsmackEngine asmackEngine) {
        this.f3246a = asmackEngine;
    }

    public void m4178a(String str, String str2) {
        if (this.f3246a.f3240b != null) {
            this.f3246a.f3240b.m4226a(str, str2);
        }
    }

    public void m4179a(String str, String str2, Map<String, String> map, String str3) {
        if (this.f3246a.f3240b != null) {
            this.f3246a.f3240b.m4229a(str, str2, str3, (Map) map);
        }
    }

    public void m4180a(Chat chat, String str, String str2) {
        if (this.f3246a.f3240b != null) {
            this.f3246a.f3240b.m4231a(false, str, str2, chat);
        }
    }
}
