package mobi.mmdt.ott.p042a;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.linphone.LinphoneManager;
import org.linphone.LinphoneManager.EcCalibrationListener;
import org.linphone.LinphonePreferences;
import org.linphone.LinphoneSimpleListener.ConnectivityChangedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnAudioChangedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnAudioChangedListener.AudioState;
import org.linphone.LinphoneSimpleListener.LinphoneOnCallEncryptionChangedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnCallStateChangedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnComposingReceivedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnDTMFReceivedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnGlobalStateChangedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnMessageReceivedListener;
import org.linphone.LinphoneSimpleListener.LinphoneOnRegistrationStateChangedListener;
import org.linphone.LinphoneUtils;
import org.linphone.SipLibService;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneAddress.TransportType;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneChatMessage;
import org.linphone.core.LinphoneChatRoom;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCore.EcCalibratorStatus;
import org.linphone.core.LinphoneCore.GlobalState;
import org.linphone.core.LinphoneCore.RegistrationState;
import org.linphone.core.LinphoneCoreException;
import org.linphone.core.PayloadType;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.video.capture.hwconf.Hacks;

/* renamed from: mobi.mmdt.ott.a.b */
public class SipLinphone implements EcCalibrationListener, ConnectivityChangedListener, LinphoneOnAudioChangedListener, LinphoneOnCallEncryptionChangedListener, LinphoneOnCallStateChangedListener, LinphoneOnComposingReceivedListener, LinphoneOnDTMFReceivedListener, LinphoneOnGlobalStateChangedListener, LinphoneOnMessageReceivedListener, LinphoneOnRegistrationStateChangedListener {
    private static SipLinphone f3231a;
    private SipLinphone f3232b;
    private LinphonePreferences f3233c;
    private Context f3234d;
    private boolean f3235e;
    private ArrayList<Listener> f3236f;

    /* renamed from: mobi.mmdt.ott.a.b.a */
    private class SipLinphone extends Thread {
        final /* synthetic */ SipLinphone f3230a;

        private SipLinphone(SipLinphone sipLinphone) {
            this.f3230a = sipLinphone;
        }

        public void run() {
            while (!SipLibService.isReady()) {
                try {
                    SipLinphone.sleep(30);
                } catch (InterruptedException e) {
                    throw new RuntimeException("waiting thread sleep() has been interrupted");
                }
            }
            this.f3230a.m4129p();
            this.f3230a.f3232b = null;
        }
    }

    private SipLinphone(Context context, Listener listener) {
        this.f3235e = false;
        this.f3236f = new ArrayList();
        if (listener != null) {
            m4133a(listener);
        }
        this.f3234d = context;
        m4128o();
        this.f3233c = LinphonePreferences.instance();
    }

    public static SipLinphone m4123a(Context context, Listener listener) {
        if (f3231a == null) {
            f3231a = new SipLinphone(context, listener);
        }
        return f3231a;
    }

    public static boolean m4126a() {
        return f3231a != null;
    }

    private void m4127c(boolean z) {
        LinphoneManager.getLc().enableEchoCancellation(z);
        LinphoneManager.getLc().enableEchoLimiter(z);
    }

    private void m4128o() {
        if (SipLibService.isReady()) {
            m4129p();
            return;
        }
        this.f3234d.startService(new Intent("android.intent.action.MAIN").setClass(this.f3234d, SipLibService.class));
        this.f3232b = new SipLinphone();
        this.f3232b.start();
    }

    private void m4129p() {
        LinphoneManager.removeListener(this);
        LinphoneManager.addListener(this);
        m4130q();
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4113a();
        }
    }

    private void m4130q() {
        m4135a(new Vocoder[]{new Vocoder("G729", 8000), new Vocoder("AMR", 8000)});
        if (Hacks.hasBuiltInEchoCanceller()) {
            m4127c(false);
        }
    }

    public void m4131a(String str, int i, String str2, String str3, int i2, int i3, boolean z) throws LinphoneCoreException {
        String stringBuilder = new StringBuilder(String.valueOf(str)).append(":").append(i).toString();
        if (LinphonePreferences.instance().getAccountCount() != 0) {
            for (int i4 = 0; i4 < LinphonePreferences.instance().getAccountCount(); i4++) {
                try {
                    LinphonePreferences.instance().deleteAccount(i4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.f3233c.useRandomPort(true);
        this.f3233c.setDebugEnabled(z);
        this.f3233c.setAutoStart(false);
        this.f3233c.setBackgroundModeEnabled(true);
        this.f3233c.setWifiOnlyEnabled(Boolean.valueOf(false));
        switch (i3) {
            case VideoSize.QCIF /*0*/:
                this.f3233c.setNewAccountTransport(TransportType.LinphoneTransportTcp);
                break;
            case VideoSize.CIF /*1*/:
                this.f3233c.setNewAccountTransport(TransportType.LinphoneTransportTls);
                break;
            case VideoSize.HVGA /*2*/:
                this.f3233c.setNewAccountTransport(TransportType.LinphoneTransportUdp);
                break;
            default:
                this.f3233c.setNewAccountTransport(TransportType.LinphoneTransportUdp);
                break;
        }
        this.f3233c.setNewAccountUsername(str2);
        this.f3233c.setNewAccountDomain(stringBuilder);
        this.f3233c.setNewAccountPassword(str3);
        this.f3233c.setNewAccountExpires(i2);
        this.f3233c.saveNewAccount();
        m4139d();
    }

    public void m4132a(String str, String str2) {
        LinphoneManager.getInstance().newOutgoingCall(str, str2);
    }

    public void m4133a(Listener listener) {
        this.f3236f.add(listener);
    }

    public void m4134a(boolean z) {
        LinphoneManager.getLc().muteMic(z);
    }

    public void m4135a(Vocoder[] vocoderArr) {
        for (PayloadType payloadType : LinphoneManager.getLc().getAudioCodecs()) {
            try {
                LinphoneManager.getLcIfManagerNotDestroyedOrNull().enablePayloadType(payloadType, false);
                int i = 0;
                while (i < vocoderArr.length) {
                    if (payloadType.getMime().equals(vocoderArr[i].m4150a()) && payloadType.getRate() == vocoderArr[i].m4151b()) {
                        if (payloadType.getMime().equals("AMR")) {
                            Log.d("SunLinphone", "AMR " + vocoderArr[i].m4150a() + " " + vocoderArr[i].m4151b());
                        } else if (payloadType.getMime().equals("G729")) {
                            Log.d("SunLinphone", "G729 " + vocoderArr[i].m4150a() + " " + vocoderArr[i].m4151b());
                        } else {
                            Log.d("SunLinphone", payloadType.getMime() + " " + vocoderArr[i].m4150a() + " " + vocoderArr[i].m4151b());
                        }
                        LinphoneManager.getLcIfManagerNotDestroyedOrNull().enablePayloadType(payloadType, true);
                        Log.d("SunLinphone", "Accepted Codec : " + payloadType.getMime() + " " + payloadType.getRate() + " ");
                    } else {
                        i++;
                    }
                }
            } catch (LinphoneCoreException e) {
                e.printStackTrace();
            }
        }
    }

    public void m4136b() {
        LinphoneCall j = m4145j();
        LinphoneCore lc = LinphoneManager.getLc();
        if (j == null || !LinphoneUtils.isCallRunning(j)) {
            List callsInState = LinphoneUtils.getCallsInState(lc, Arrays.asList(new State[]{State.Paused}));
            if (callsInState.size() == 1) {
                LinphoneCall linphoneCall = (LinphoneCall) callsInState.get(0);
                if ((j != null && linphoneCall.equals(j)) || j == null) {
                    lc.resumeCall(linphoneCall);
                }
            } else if (j != null) {
                lc.resumeCall(j);
            }
        } else if (j.isInConference()) {
            lc.removeFromConference(j);
            if (lc.getConferenceSize() <= 1) {
                lc.leaveConference();
            }
        } else {
            lc.pauseCall(j);
        }
    }

    public void m4137b(boolean z) {
        if (z) {
            LinphoneManager.getInstance().routeAudioToSpeaker();
            LinphoneManager.getLc().enableSpeaker(z);
            return;
        }
        LinphoneManager.getInstance().routeAudioToReceiver();
    }

    public void m4138c() {
        LinphoneManager.getInstance().refreshRegister();
    }

    public void m4139d() {
        if (LinphonePreferences.instance().getAccountCount() != 0) {
            for (int i = 1; i < LinphonePreferences.instance().getAccountCount(); i++) {
                try {
                    this.f3233c.setAccountEnabled(i, true);
                } catch (Exception e) {
                    try {
                        e.printStackTrace();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            }
            m4138c();
        }
    }

    public void m4140e() {
        if (LinphonePreferences.instance().getAccountCount() != 0) {
            int i = 1;
            while (i < LinphonePreferences.instance().getAccountCount()) {
                try {
                    try {
                        this.f3233c.setAccountEnabled(i, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    i++;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            this.f3233c.setAccountEnabled(0, false);
            m4138c();
        }
    }

    public void m4141f() throws LinphoneCoreException {
        if (LinphonePreferences.instance().getAccountCount() != 0) {
            for (int i = 0; i < LinphonePreferences.instance().getAccountCount(); i++) {
                try {
                    LinphonePreferences.instance().deleteAccount(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean m4142g() throws LinphoneCoreException {
        return LinphoneManager.getInstance().acceptCallIfIncomingPending();
    }

    public boolean m4143h() {
        return this.f3235e;
    }

    public void m4144i() {
        LinphoneCore lc = LinphoneManager.getLc();
        LinphoneCall currentCall = lc.getCurrentCall();
        if (currentCall != null) {
            lc.terminateCall(currentCall);
        } else if (lc.isInConference()) {
            lc.terminateConference();
        } else {
            lc.terminateAllCalls();
        }
    }

    public LinphoneCall m4145j() {
        return LinphoneManager.getLc().getCurrentCall();
    }

    public int m4146k() {
        LinphoneCall currentCall = LinphoneManager.getInstance().getCurrentCall();
        return currentCall != null ? currentCall.getDuration() : -1;
    }

    public float m4147l() {
        LinphoneCall currentCall = LinphoneManager.getInstance().getCurrentCall();
        return currentCall != null ? currentCall.getCurrentQuality() : -1.0f;
    }

    public synchronized void m4148m() {
        this.f3234d.stopService(new Intent("android.intent.action.MAIN").setClass(this.f3234d, SipLibService.class));
    }

    public Vocoder[] m4149n() {
        LinphoneCore lc = LinphoneManager.getLc();
        Vocoder[] vocoderArr = new Vocoder[lc.getAudioCodecs().length];
        for (int i = 0; i < vocoderArr.length; i++) {
            vocoderArr[i] = new Vocoder(lc.getAudioCodecs()[i].getMime(), lc.getAudioCodecs()[i].getRate());
        }
        return vocoderArr;
    }

    public void onAudioStateChanged(AudioState audioState) {
        Log.d("SunLinphone", "onAudioStateChanged : <" + audioState + ">");
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4115a(audioState);
        }
    }

    public void onCallEncryptionChanged(LinphoneCall linphoneCall, boolean z, String str) {
        Log.d("SunLinphone", "onCallEncryptionChanged : <>");
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4119a(linphoneCall, z, str);
        }
    }

    public void onCallStateChanged(LinphoneCall linphoneCall, State state, String str) {
        Log.d("SunLinphone", "onCallStateChanged : <" + linphoneCall.getState().toString() + " " + linphoneCall.getRemoteAddress() + " " + linphoneCall.getDuration() + " " + state.toString() + " " + str + ">");
        if (linphoneCall.getCurrentParamsCopy() != null) {
            Log.d("SunLinphone", "Sunlin : " + linphoneCall.getCurrentParamsCopy().getUsedAudioCodec());
        }
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4118a(linphoneCall, state, str);
        }
    }

    public void onComposingReceived(LinphoneChatRoom linphoneChatRoom) {
        Log.d("SunLinphone", "onComposingReceived : <>");
    }

    public void onConnectivityChanged(Context context, NetworkInfo networkInfo, ConnectivityManager connectivityManager) {
        Log.d("SunLinphone", "onConnectivityChanged : <>");
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4114a(context, networkInfo, connectivityManager);
        }
    }

    public void onDTMFReceived(LinphoneCall linphoneCall, int i) {
        Log.d("SunLinphone", "onDTMFReceived : <>");
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4117a(linphoneCall, i);
        }
    }

    public void onEcCalibrationStatus(EcCalibratorStatus ecCalibratorStatus, int i) {
        Log.d("SunLinphone", "onEcCalibrationStatus : <" + ecCalibratorStatus + " " + i + ">");
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4120a(ecCalibratorStatus, i);
        }
    }

    public void onGlobalStateChanged(GlobalState globalState, String str) {
        Log.d("SunLinphone", "onGlobalStateChanged : <" + globalState + ">");
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4121a(globalState, str);
        }
    }

    public void onMessageReceived(LinphoneAddress linphoneAddress, LinphoneChatMessage linphoneChatMessage, int i) {
        Log.d("SunLinphone", "onMessageReceived : <" + linphoneAddress.getDisplayName() + " " + linphoneAddress.getUserName() + " " + linphoneChatMessage.toString() + " " + i + ">");
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4116a(linphoneAddress, linphoneChatMessage, i);
        }
    }

    public void onRegistrationStateChanged(RegistrationState registrationState) {
        Log.d("SunLinphone", "onRegistrationStateChanged : <" + registrationState.toString() + ">");
        if (registrationState == RegistrationState.RegistrationOk) {
            this.f3235e = true;
        } else {
            this.f3235e = false;
        }
        Iterator it = this.f3236f.iterator();
        while (it.hasNext()) {
            ((Listener) it.next()).m4122a(registrationState);
        }
    }
}
