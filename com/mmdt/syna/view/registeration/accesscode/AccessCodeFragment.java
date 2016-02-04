package com.mmdt.syna.view.registeration.accesscode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/* renamed from: com.mmdt.syna.view.registeration.accesscode.b */
class AccessCodeFragment extends BroadcastReceiver {
    final /* synthetic */ AccessCodeFragment f2816a;

    AccessCodeFragment(AccessCodeFragment accessCodeFragment) {
        this.f2816a = accessCodeFragment;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            Object[] objArr = (Object[]) extras.get("pdus");
            if (objArr.length != 0) {
                SmsMessage[] smsMessageArr = new SmsMessage[objArr.length];
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < objArr.length; i++) {
                    smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
                    stringBuilder.append(smsMessageArr[i].getMessageBody());
                }
                smsMessageArr[0].getOriginatingAddress();
                CharSequence stringBuilder2 = stringBuilder.toString();
                if (stringBuilder2.length() <= 6 && stringBuilder2.substring(0, 1).matches("[0-9]")) {
                    this.f2816a.f2804e.setText(stringBuilder2);
                    this.f2816a.m3697e();
                }
            }
        }
    }
}
