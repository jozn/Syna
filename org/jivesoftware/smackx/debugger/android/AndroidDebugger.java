package org.jivesoftware.smackx.debugger.android;

import android.util.Log;
import org.jivesoftware.smack.debugger.AbstractDebugger;

public class AndroidDebugger extends AbstractDebugger {
    protected void log(String str) {
        Log.d("SMACK", str);
    }
}
