package mobi.mmdt.ott.core.logic.message.p032a;

import android.content.Context;

/* renamed from: mobi.mmdt.ott.core.logic.message.a.c */
public abstract class BaseMedia {
    protected int f3739b;
    protected VoiceListener f3740c;
    protected String f3741d;
    protected Context f3742e;

    protected BaseMedia(Context context, int i, String str, VoiceListener voiceListener) {
        this.f3741d = str;
        this.f3740c = voiceListener;
        this.f3742e = context;
        this.f3739b = i;
    }

    public String m4772d() {
        return this.f3741d;
    }
}
