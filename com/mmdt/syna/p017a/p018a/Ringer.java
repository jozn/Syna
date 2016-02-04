package com.mmdt.syna.p017a.p018a;

import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import java.util.Calendar;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import org.linphone.core.VideoSize;

/* renamed from: com.mmdt.syna.a.a.i */
class Ringer implements Runnable {
    final /* synthetic */ Ringer f1597a;

    Ringer(Ringer ringer) {
        this.f1597a = ringer;
    }

    public void run() {
        try {
            ((PowerManager) this.f1597a.f1595d.getSystemService("power")).newWakeLock(268435466, "SYNA_WAKE_LOCK_TAG").acquire(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (AppSettings.m4867a(this.f1597a.f1595d).m4894h()) {
                int r = AppSettings.m4867a(this.f1597a.f1595d).m4908r();
                int s = AppSettings.m4867a(this.f1597a.f1595d).m4909s();
                int i = (Calendar.getInstance().get(11) * 60) + Calendar.getInstance().get(12);
                if (i > r && i < s && this.f1597a.f1594c.getRingerMode() == 2) {
                    this.f1597a.f1596e = false;
                    return;
                }
            }
            switch (this.f1597a.f1594c.getRingerMode()) {
                case VideoSize.QCIF /*0*/:
                    this.f1597a.f1596e = false;
                    return;
                case VideoSize.CIF /*1*/:
                    ((Vibrator) this.f1597a.f1595d.getSystemService("vibrator")).vibrate(500);
                    this.f1597a.f1596e = false;
                    return;
                case VideoSize.HVGA /*2*/:
                    Uri defaultUri = RingtoneManager.getDefaultUri(2);
                    this.f1597a.f1593b = new MediaPlayer();
                    this.f1597a.f1593b.setDataSource(this.f1597a.f1595d, defaultUri);
                    this.f1597a.f1593b.setAudioStreamType(5);
                    this.f1597a.f1593b.setLooping(false);
                    this.f1597a.f1593b.setOnPreparedListener(new Ringer(this));
                    this.f1597a.f1593b.setOnCompletionListener(new Ringer(this));
                    this.f1597a.f1593b.setOnErrorListener(new Ringer(this));
                    this.f1597a.f1593b.prepare();
                    return;
                default:
                    return;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f1597a.f1596e = false;
        }
        e2.printStackTrace();
        this.f1597a.f1596e = false;
    }
}
