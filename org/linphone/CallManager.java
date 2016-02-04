package org.linphone;

import org.linphone.core.LinphoneAddress;
import org.linphone.core.LinphoneCall;
import org.linphone.core.LinphoneCallParams;
import org.linphone.core.LinphoneCore;
import org.linphone.core.LinphoneCoreException;
import org.linphone.mediastream.Log;

public class CallManager {
    private static CallManager instance;

    private CallManager() {
    }

    private BandwidthManager bm() {
        return BandwidthManager.getInstance();
    }

    public static final synchronized CallManager getInstance() {
        CallManager callManager;
        synchronized (CallManager.class) {
            if (instance == null) {
                instance = new CallManager();
            }
            callManager = instance;
        }
        return callManager;
    }

    public void inviteAddress(LinphoneAddress linphoneAddress, boolean z, boolean z2) throws LinphoneCoreException {
        LinphoneCore lc = LinphoneManager.getLc();
        LinphoneCallParams createDefaultCallParameters = lc.createDefaultCallParameters();
        bm().updateWithProfileSettings(lc, createDefaultCallParameters);
        if (z && createDefaultCallParameters.getVideoEnabled()) {
            createDefaultCallParameters.setVideoEnabled(true);
        } else {
            createDefaultCallParameters.setVideoEnabled(false);
        }
        if (z2) {
            createDefaultCallParameters.enableLowBandwidth(true);
            Log.m6026d("Low bandwidth enabled in call params");
        }
        lc.inviteAddressWithParams(linphoneAddress, createDefaultCallParameters);
    }

    void reinvite() {
        LinphoneCore lc = LinphoneManager.getLc();
        LinphoneCall currentCall = lc.getCurrentCall();
        if (currentCall == null) {
            Log.m6028e("Trying to reinvite while not in call: doing nothing");
            return;
        }
        LinphoneCallParams currentParamsCopy = currentCall.getCurrentParamsCopy();
        bm().updateWithProfileSettings(lc, currentParamsCopy);
        lc.updateCall(currentCall, currentParamsCopy);
    }

    boolean reinviteWithVideo() {
        LinphoneCore lc = LinphoneManager.getLc();
        LinphoneCall currentCall = lc.getCurrentCall();
        if (currentCall == null) {
            Log.m6028e("Trying to reinviteWithVideo while not in call: doing nothing");
            return false;
        }
        LinphoneCallParams currentParamsCopy = currentCall.getCurrentParamsCopy();
        if (currentParamsCopy.getVideoEnabled()) {
            return false;
        }
        bm().updateWithProfileSettings(lc, currentParamsCopy);
        if (!currentParamsCopy.getVideoEnabled()) {
            return false;
        }
        lc.updateCall(currentCall, currentParamsCopy);
        return true;
    }

    public void updateCall() {
        LinphoneCore lc = LinphoneManager.getLc();
        LinphoneCall currentCall = lc.getCurrentCall();
        if (currentCall == null) {
            Log.m6028e("Trying to updateCall while not in call: doing nothing");
            return;
        }
        bm().updateWithProfileSettings(lc, currentCall.getCurrentParamsCopy());
        lc.updateCall(currentCall, null);
    }
}
