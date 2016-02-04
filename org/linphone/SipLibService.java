package org.linphone;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import java.lang.reflect.Method;
import mobi.mmdt.ott.p042a.SipLinphone;
import org.linphone.LinphoneManager.NewOutgoingCallUiListener;
import org.linphone.LinphoneSimpleListener.LinphoneServiceListener;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCall.State;
import org.linphone.core.LinphoneCore.GlobalState;
import org.linphone.core.LinphoneCore.RegistrationState;
import org.linphone.core.LinphoneCoreException;
import org.linphone.mediastream.Log;
import org.linphone.mediastream.Version;

public final class SipLibService extends Service implements LinphoneServiceListener {
    private static final int IC_LEVEL_OFFLINE = 3;
    private static final int IC_LEVEL_ORANGE = 0;
    public static final String START_LINPHONE_LOGS = " ==== Phone information dump ====";
    private static SipLibService instance;
    private static final Class<?>[] mSetFgSign;
    private static final Class<?>[] mStartFgSign;
    private static final Class<?>[] mStopFgSign;
    private IncallIconState mCurrentIncallIconState;
    public Handler mHandler;
    private NotificationManager mNM;
    private Method mSetForeground;
    private Object[] mSetForegroundArgs;
    private Method mStartForeground;
    private Object[] mStartForegroundArgs;
    private Method mStopForeground;
    private Object[] mStopForegroundArgs;
    private boolean mTestDelayElapsed;
    private WifiLock mWifiLock;
    private WifiManager mWifiManager;
    private PendingIntent mkeepAlivePendingIntent;

    /* renamed from: org.linphone.SipLibService.1 */
    class C02831 implements Runnable {
        C02831() {
        }

        public void run() {
            SipLibService.this.mTestDelayElapsed = true;
        }
    }

    /* renamed from: org.linphone.SipLibService.2 */
    class C02842 implements Runnable {
        private final /* synthetic */ String val$message;

        C02842(String str) {
            this.val$message = str;
        }

        public void run() {
            if (SipLibService.guiListener() != null) {
                SipLibService.guiListener().onDisplayStatus(this.val$message);
            }
        }
    }

    /* renamed from: org.linphone.SipLibService.3 */
    class C02853 implements Runnable {
        private final /* synthetic */ String val$message;

        C02853(String str) {
            this.val$message = str;
        }

        public void run() {
            if (SipLibService.guiListener() != null) {
                SipLibService.guiListener().onGlobalStateChangedToOn(this.val$message);
            }
        }
    }

    /* renamed from: org.linphone.SipLibService.4 */
    class C02864 implements Runnable {
        private final /* synthetic */ RegistrationState val$state;

        C02864(RegistrationState registrationState) {
            this.val$state = registrationState;
        }

        public void run() {
            if (SipLinphone.m4126a()) {
                SipLinphone.m4123a(SipLibService.this.getApplicationContext(), null).onRegistrationStateChanged(this.val$state);
            }
        }
    }

    /* renamed from: org.linphone.SipLibService.5 */
    class C02875 implements Runnable {
        private final /* synthetic */ LinphoneCall val$call;
        private final /* synthetic */ String val$message;
        private final /* synthetic */ State val$state;

        C02875(LinphoneCall linphoneCall, State state, String str) {
            this.val$call = linphoneCall;
            this.val$state = state;
            this.val$message = str;
        }

        public void run() {
            if (SipLibService.guiListener() != null) {
                SipLibService.guiListener().onCallStateChanged(this.val$call, this.val$state, this.val$message);
            }
        }
    }

    /* renamed from: org.linphone.SipLibService.6 */
    class C02886 implements Runnable {
        C02886() {
        }

        public void run() {
            if (SipLibService.guiListener() != null) {
                SipLibService.guiListener().onAlreadyInCall();
            }
        }
    }

    /* renamed from: org.linphone.SipLibService.7 */
    class C02897 implements Runnable {
        C02897() {
        }

        public void run() {
            if (SipLibService.guiListener() != null) {
                SipLibService.guiListener().onCannotGetCallParameters();
            }
        }
    }

    /* renamed from: org.linphone.SipLibService.8 */
    class C02908 implements Runnable {
        C02908() {
        }

        public void run() {
            if (SipLibService.guiListener() != null) {
                SipLibService.guiListener().onWrongDestinationAddress();
            }
        }
    }

    private enum IncallIconState {
        INCALL,
        PAUSE,
        VIDEO,
        IDLE
    }

    public interface LinphoneGuiListener extends NewOutgoingCallUiListener {
        void onCallStateChanged(LinphoneCall linphoneCall, State state, String str);

        void onDisplayStatus(String str);

        void onGlobalStateChangedToOn(String str);
    }

    static {
        mSetFgSign = new Class[]{Boolean.TYPE};
        mStartFgSign = new Class[]{Integer.TYPE, Notification.class};
        mStopFgSign = new Class[]{Boolean.TYPE};
    }

    public SipLibService() {
        this.mHandler = new Handler();
        this.mTestDelayElapsed = true;
        this.mCurrentIncallIconState = IncallIconState.IDLE;
        this.mSetForegroundArgs = new Object[1];
        this.mStartForegroundArgs = new Object[2];
        this.mStopForegroundArgs = new Object[1];
    }

    private static final LinphoneGuiListener guiListener() {
        return null;
    }

    public static SipLibService instance() {
        if (isReady()) {
            return instance;
        }
        throw new RuntimeException("LinphoneService not instantiated yet");
    }

    public static boolean isReady() {
        return instance != null && instance.mTestDelayElapsed;
    }

    private synchronized void notifyWrapper(int i, Notification notification) {
        if (instance == null || notification == null) {
            Log.m6032i("Service not ready, discarding notification");
        } else {
            this.mNM.notify(i, notification);
        }
    }

    private void resetIntentLaunchedOnNotificationClick() {
        Log.m6028e("SipLibService", "Call incomingReceivedActivity");
    }

    @TargetApi(12)
    private void startWifiLock() {
        this.mWifiLock = this.mWifiManager.createWifiLock(IC_LEVEL_OFFLINE, getPackageName() + "-wifi-call-lock");
        this.mWifiLock.setReferenceCounted(false);
    }

    public void displayMessageNotification(String str, String str2, String str3) {
    }

    void invokeMethod(Method method, Object[] objArr) {
        try {
            method.invoke(this, objArr);
        } catch (Throwable e) {
            Log.m6033w(e, "Unable to invoke method");
        } catch (Throwable e2) {
            Log.m6033w(e2, "Unable to invoke method");
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCallEncryptionChanged(LinphoneCall linphoneCall, boolean z, String str) {
    }

    public void onCallStateChanged(LinphoneCall linphoneCall, State state, String str) {
        if (instance == null) {
            Log.m6032i("Service not ready, discarding call state change to ", state.toString());
            return;
        }
        if (state == State.IncomingReceived) {
            onIncomingReceived();
        }
        if (state == State.CallUpdatedByRemote) {
            boolean videoEnabled = linphoneCall.getRemoteParams().getVideoEnabled();
            boolean videoEnabled2 = linphoneCall.getCurrentParamsCopy().getVideoEnabled();
            boolean shouldAutomaticallyAcceptVideoRequests = LinphonePreferences.instance().shouldAutomaticallyAcceptVideoRequests();
            if (!(!videoEnabled || videoEnabled2 || shouldAutomaticallyAcceptVideoRequests || LinphoneManager.getLc().isInConference())) {
                try {
                    LinphoneManager.getLc().deferCallUpdate(linphoneCall);
                } catch (LinphoneCoreException e) {
                    e.printStackTrace();
                }
            }
        }
        if (state == State.StreamsRunning) {
            if (getResources().getBoolean(C0282R.bool.enable_call_notification)) {
            }
            if (Version.sdkAboveOrEqual(12)) {
                this.mWifiLock.acquire();
            }
        } else if (getResources().getBoolean(C0282R.bool.enable_call_notification)) {
        }
        if ((state == State.CallEnd || state == State.Error) && LinphoneManager.getLc().getCallsNb() < 1 && Version.sdkAboveOrEqual(12)) {
            this.mWifiLock.release();
        }
        this.mHandler.post(new C02875(linphoneCall, state, str));
    }

    public void onCreate() {
        super.onCreate();
        Log.m6032i(START_LINPHONE_LOGS);
        this.mNM = (NotificationManager) getSystemService("notification");
        LinphoneManager.createAndStart(this, this);
        this.mWifiManager = (WifiManager) getSystemService("wifi");
        if (Version.sdkAboveOrEqual(12)) {
            startWifiLock();
        }
        instance = this;
        if (Version.sdkStrictlyBelow(5)) {
            try {
                this.mSetForeground = getClass().getMethod("setForeground", mSetFgSign);
            } catch (Throwable e) {
                Log.m6027e(e, "Couldn't find foreground method");
            }
        } else {
            try {
                this.mStartForeground = getClass().getMethod("startForeground", mStartFgSign);
                this.mStopForeground = getClass().getMethod("stopForeground", mStopFgSign);
            } catch (Throwable e2) {
                Log.m6027e(e2, "Couldn't find startGoreground or stopForeground");
            }
        }
        if (!this.mTestDelayElapsed) {
            this.mHandler.postDelayed(new C02831(), 5000);
        }
        this.mkeepAlivePendingIntent = PendingIntent.getBroadcast(this, IC_LEVEL_ORANGE, new Intent(this, KeepAliveHandler.class), 1073741824);
        ((AlarmManager) getSystemService("alarm")).setRepeating(2, SystemClock.elapsedRealtime() + 1000, 60000, this.mkeepAlivePendingIntent);
    }

    public synchronized void onDestroy() {
        instance = null;
        LinphoneManager.destroy();
        if (Version.sdkAboveOrEqual(12)) {
            this.mWifiLock.release();
        }
        ((AlarmManager) getSystemService("alarm")).cancel(this.mkeepAlivePendingIntent);
        super.onDestroy();
    }

    public void onDisplayStatus(String str) {
        this.mHandler.post(new C02842(str));
    }

    public void onGlobalStateChanged(GlobalState globalState, String str) {
        if (globalState == GlobalState.GlobalOn) {
            this.mHandler.postDelayed(new C02853(str), 50);
        }
    }

    protected void onIncomingReceived() {
        Log.m6028e("SipLibService", "Call incomingReceivedActivity");
    }

    public void onRegistrationStateChanged(RegistrationState registrationState, String str) {
        this.mHandler.post(new C02864(registrationState));
    }

    public void tryingNewOutgoingCallButAlreadyInCall() {
        this.mHandler.post(new C02886());
    }

    public void tryingNewOutgoingCallButCannotGetCallParameters() {
        this.mHandler.post(new C02897());
    }

    public void tryingNewOutgoingCallButWrongDestinationAddress() {
        this.mHandler.post(new C02908());
    }
}
