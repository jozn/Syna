package mobi.mmdt.ott.p042a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.linphone.LinphoneSimpleListener.LinphoneOnAudioChangedListener.AudioState;
import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneChatMessage;
import org.linphone.core.LinphoneCore.EcCalibratorStatus;
import org.linphone.core.LinphoneCore.GlobalState;
import org.linphone.core.LinphoneCore.RegistrationState;

/* renamed from: mobi.mmdt.ott.a.a */
public interface Listener {
    void m4113a();

    void m4114a(Context context, NetworkInfo networkInfo, ConnectivityManager connectivityManager);

    void m4115a(AudioState audioState);

    void m4116a(LinphoneAddress linphoneAddress, LinphoneChatMessage linphoneChatMessage, int i);

    void m4117a(LinphoneCall linphoneCall, int i);

    void m4118a(LinphoneCall linphoneCall, State state, String str);

    void m4119a(LinphoneCall linphoneCall, boolean z, String str);

    void m4120a(EcCalibratorStatus ecCalibratorStatus, int i);

    void m4121a(GlobalState globalState, String str);

    void m4122a(RegistrationState registrationState);
}
