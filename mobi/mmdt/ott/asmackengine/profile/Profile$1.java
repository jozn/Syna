package mobi.mmdt.ott.asmackengine.profile;

import android.util.Log;
import java.util.Collection;
import mobi.mmdt.ott.asmackengine.profile.Profile.Profile;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smack.roster.RosterListener;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

class Profile$1 implements RosterListener {
    private static /* synthetic */ int[] f3309b;
    final /* synthetic */ Profile f3310a;

    Profile$1(Profile profile) {
        this.a = profile;
    }

    static /* synthetic */ int[] m4260a() {
        int[] iArr = b;
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
            b = iArr;
        }
        return iArr;
    }

    public void m4261a(Collection<String> collection) {
        Log.d("AsmackEngine", "entriesUpdated");
    }

    public void m4262a(Presence presence) {
        if (this.a.f3321b != null) {
            switch (m4260a()[presence.getType().ordinal()]) {
                case VideoSize.CIF /*1*/:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.AVAILABLE);
                    break;
                case VideoSize.HVGA /*2*/:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.UNAVAILABLE);
                    break;
                case Version.API03_CUPCAKE_15 /*3*/:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.SUBSCRIBE);
                    break;
                case Version.API04_DONUT_16 /*4*/:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.SUBSCRIBED);
                    break;
                case Version.API05_ECLAIR_20 /*5*/:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.UNSUBSCRIBE);
                    break;
                case Version.API06_ECLAIR_201 /*6*/:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.UNSUBSCRIBED);
                    break;
                case Version.API07_ECLAIR_21 /*7*/:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.ERROR);
                    break;
                default:
                    this.a.f3321b.m4181a(presence.getFrom(), Profile.ERROR);
                    break;
            }
        }
        Log.d("AsmackEngine", "presenceChanged " + presence.getType());
    }

    public void m4263b(Collection<String> collection) {
        Log.d("AsmackEngine", "entriesDeleted");
    }

    public void m4264c(Collection<String> collection) {
        Log.d("AsmackEngine", "entriesAdded");
    }
}
