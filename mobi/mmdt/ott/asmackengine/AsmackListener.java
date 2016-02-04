package mobi.mmdt.ott.asmackengine;

import java.util.Map;
import mobi.mmdt.ott.asmackengine.chat.Chat.Chat;
import mobi.mmdt.ott.asmackengine.profile.Profile.Profile;

/* renamed from: mobi.mmdt.ott.asmackengine.f */
public interface AsmackListener {
    void m4225a();

    void m4226a(String str, String str2);

    void m4227a(String str, String str2, String str3);

    void m4228a(String str, String str2, String str3, String str4, Map<String, String> map);

    void m4229a(String str, String str2, String str3, Map<String, String> map);

    void m4230a(String str, Profile profile);

    void m4231a(boolean z, String str, String str2, Chat chat);

    void m4232b();
}
