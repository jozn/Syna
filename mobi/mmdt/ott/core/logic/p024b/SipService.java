package mobi.mmdt.ott.core.logic.p024b;

import android.content.Context;
import java.util.ArrayList;
import mobi.mmdt.ott.p042a.Listener;
import mobi.mmdt.ott.p042a.SipLinphone;
import mobi.mmdt.ott.p042a.Vocoder;
import org.linphone.core.CallDirection;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.VideoSize;

/* renamed from: mobi.mmdt.ott.core.logic.b.e */
public class SipService {
    private static SipService f3588a;
    private ArrayList<CallNotifier> f3589b;
    private SipLinphone f3590c;
    private boolean f3591d;
    private String f3592e;
    private String f3593f;
    private boolean f3594g;
    private int f3595h;
    private int f3596i;
    private int f3597j;
    private String f3598k;
    private boolean f3599l;
    private boolean f3600m;
    private boolean f3601n;
    private boolean f3602o;
    private LinphoneCall f3603p;
    private Context f3604q;
    private Listener f3605r;

    static {
        f3588a = null;
    }

    private SipService(Context context) {
        this.f3589b = new ArrayList();
        this.f3591d = false;
        this.f3592e = "";
        this.f3593f = "";
        this.f3594g = false;
        this.f3595h = -1;
        this.f3596i = 5060;
        this.f3598k = "";
        this.f3599l = false;
        this.f3600m = false;
        this.f3601n = false;
        this.f3602o = false;
        this.f3605r = new SipService(this);
        this.f3604q = context;
        this.f3590c = SipLinphone.m4123a(this.f3604q, this.f3605r);
    }

    private CallState m4590a(State state) {
        return state.equals(State.CallEnd) ? CallState.f3579n : state.equals(State.CallIncomingEarlyMedia) ? CallState.f3582q : state.equals(State.CallReleased) ? CallState.f3584s : state.equals(State.CallUpdatedByRemote) ? CallState.f3581p : state.equals(State.CallUpdating) ? CallState.f3583r : state.equals(State.Connected) ? CallState.f3572g : state.equals(State.Error) ? CallState.f3578m : state.equals(State.Idle) ? CallState.f3566a : state.equals(State.IncomingReceived) ? CallState.f3567b : state.equals(State.OutgoingEarlyMedia) ? CallState.f3571f : state.equals(State.OutgoingInit) ? CallState.f3568c : state.equals(State.OutgoingProgress) ? CallState.f3569d : state.equals(State.OutgoingRinging) ? CallState.f3570e : state.equals(State.Paused) ? CallState.f3575j : state.equals(State.PausedByRemote) ? CallState.f3580o : state.equals(State.Pausing) ? CallState.f3574i : state.equals(State.Refered) ? CallState.f3577l : state.equals(State.Resuming) ? CallState.f3576k : state.equals(State.StreamsRunning) ? CallState.f3573h : null;
    }

    public static SipService m4591a(Context context) {
        if (f3588a == null) {
            f3588a = new SipService(context);
        }
        return f3588a;
    }

    private void m4602r() {
        int i = 2;
        if (this.f3599l) {
            try {
                switch (this.f3597j) {
                    case VideoSize.CIF /*1*/:
                        i = 0;
                        break;
                    case VideoSize.HVGA /*2*/:
                        i = 1;
                        break;
                }
                this.f3590c.m4131a(this.f3598k, this.f3596i, this.f3592e, this.f3593f, this.f3595h, i, this.f3594g);
                return;
            } catch (LinphoneCoreException e) {
                e.printStackTrace();
                return;
            }
        }
        this.f3591d = true;
    }

    private void m4603s() {
        if (this.f3599l) {
            try {
                this.f3590c.m4139d();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m4604a() {
        if (this.f3599l) {
            try {
                this.f3590c.m4140e();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m4605a(String str, int i, String str2, String str3, int i2, int i3, boolean z) {
        if (!this.f3598k.equals(str) || i != this.f3596i || !str2.equals(this.f3592e) || !str3.equals(this.f3593f) || i2 != this.f3597j) {
            this.f3598k = str;
            this.f3596i = i;
            this.f3592e = str2;
            this.f3593f = str3;
            this.f3597j = i2;
            this.f3594g = z;
            this.f3595h = i3;
            m4602r();
            m4603s();
        }
    }

    public void m4606a(String str, String str2) {
        if (this.f3599l) {
            this.f3590c.m4132a(str, str2);
        }
    }

    public void m4607a(CallNotifier callNotifier) {
        this.f3589b.add(callNotifier);
    }

    public void m4608a(boolean z) {
        this.f3602o = z;
        if (this.f3599l) {
            this.f3590c.m4134a(z);
        }
    }

    public void m4609a(Vocoder[] vocoderArr) {
        this.f3590c.m4135a(vocoderArr);
    }

    public void m4610b() {
        if (this.f3599l) {
            try {
                this.f3590c.m4141f();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void m4611b(boolean z) {
        if (this.f3599l) {
            this.f3590c.m4137b(z);
        }
    }

    public synchronized void m4612c() {
        try {
            this.f3590c.m4148m();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f3599l = false;
    }

    public void m4613d() {
        if (this.f3599l) {
            this.f3590c.m4138c();
        }
    }

    public void m4614e() {
        if (this.f3599l) {
            this.f3590c.m4136b();
        }
    }

    public boolean m4615f() {
        if (this.f3599l) {
            try {
                return this.f3590c.m4142g();
            } catch (LinphoneCoreException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Vocoder[] m4616g() {
        return this.f3590c.m4149n();
    }

    public boolean m4617h() {
        return this.f3602o;
    }

    public boolean m4618i() {
        return this.f3600m;
    }

    public boolean m4619j() {
        if (this.f3590c.m4145j() != null) {
            this.f3603p = this.f3590c.m4145j();
        }
        return this.f3603p.getState().equals(State.Paused);
    }

    public String m4620k() {
        if (this.f3590c.m4145j() != null) {
            this.f3603p = this.f3590c.m4145j();
        }
        LinphoneAddress remoteAddress = this.f3603p.getRemoteAddress();
        return remoteAddress.getUserName() != null ? remoteAddress.getUserName() : remoteAddress.getDisplayName() != null ? remoteAddress.getDisplayName() : null;
    }

    public CallState m4621l() {
        if (this.f3590c.m4145j() != null) {
            this.f3603p = this.f3590c.m4145j();
        }
        return this.f3603p != null ? m4590a(this.f3603p.getState()) : null;
    }

    public boolean m4622m() {
        try {
            if (this.f3590c.m4145j() != null) {
                this.f3603p = this.f3590c.m4145j();
            }
            if (this.f3603p != null && this.f3603p.getDirection() == CallDirection.Incoming) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void m4623n() {
        if (this.f3599l) {
            this.f3590c.m4144i();
        }
    }

    public int m4624o() {
        return !this.f3599l ? -1 : this.f3590c.m4146k();
    }

    public float m4625p() {
        return !this.f3599l ? -1.0f : this.f3590c.m4147l();
    }

    public boolean m4626q() {
        return !this.f3599l ? false : this.f3590c.m4143h();
    }
}
