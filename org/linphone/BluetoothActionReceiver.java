package org.linphone;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.linphone.mediastream.Log;

@TargetApi(11)
public class BluetoothActionReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT")) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getExtras().get("android.bluetooth.device.extra.DEVICE");
            String string = intent.getExtras().getString("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD");
            int i = intent.getExtras().getInt("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE");
            Object[] objArr = (Object[]) intent.getExtras().get("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS");
            String[] strArr = new String[objArr.length];
            String str = "";
            for (int i2 = 0; i2 < objArr.length; i2++) {
                strArr[i2] = (String) objArr[i2];
                str = new StringBuilder(String.valueOf(str)).append(strArr[i2]).append(" ").toString();
            }
            Log.m6026d("Bluetooth headset event from " + bluetoothDevice.getName() + ", command: " + string + " (type: " + i + ") with args: " + str);
        }
    }
}
