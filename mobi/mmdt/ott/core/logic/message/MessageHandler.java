package mobi.mmdt.ott.core.logic.message;

import android.app.Activity;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import javax.crypto.IllegalBlockSizeException;
import mobi.mmdt.ott.core.logic.message.p032a.AMRRecorder;
import mobi.mmdt.ott.core.logic.message.p032a.Player;
import mobi.mmdt.ott.core.logic.message.p032a.VoiceListener;
import mobi.mmdt.ott.core.logic.p055d.MessageManager;
import mobi.mmdt.ott.core.p051a.FileManager;
import mobi.mmdt.ott.core.p051a.p052a.MemoryException;
import mobi.mmdt.p041a.SDCardTools;

/* renamed from: mobi.mmdt.ott.core.logic.message.a */
public class MessageHandler {
    private Activity f3752a;
    private String[] f3753b;
    private Player f3754c;
    private int f3755d;
    private AMRRecorder f3756e;
    private boolean f3757f;
    private Timer f3758g;
    private boolean f3759h;

    public MessageHandler(Activity activity, boolean z, String[] strArr) {
        this.f3755d = -1;
        this.f3757f = false;
        this.f3758g = new Timer();
        this.f3759h = false;
        this.f3752a = activity;
        this.f3753b = strArr;
        this.f3759h = z;
        MessageManager.m4703a(this.f3752a).m4710a(this.f3753b[0]);
        new Thread(new MessageHandler(this)).start();
    }

    public void m4788a() {
        MessageManager.m4703a(this.f3752a).m4710a(null);
        if (this.f3754c != null && this.f3755d == 1) {
            this.f3754c.m4780b();
            this.f3755d = 2;
        }
        new Thread(new MessageHandler(this)).start();
    }

    public void m4789a(long j) throws MemoryException {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            throw new MemoryException(1);
        } else if (SDCardTools.m4107a() < 5) {
            throw new MemoryException(2);
        } else {
            Thread thread = new Thread(new MessageHandler(this, j));
            thread.setPriority(1);
            thread.start();
        }
    }

    public void m4790a(long j, String str) {
        if (this.f3754c == null) {
            this.f3755d = 2;
        } else if (this.f3755d == 1) {
            this.f3754c.m4780b();
            this.f3755d = 2;
        } else if (this.f3755d == 3) {
            this.f3754c.m4779a();
            this.f3755d = 1;
            return;
        }
        this.f3754c = new Player(this.f3752a, (int) j, str, null);
        this.f3754c.m4781c();
        this.f3754c.m4779a();
        this.f3755d = 1;
    }

    public void m4791a(String str) {
        Thread thread = new Thread(new MessageHandler(this, str));
        thread.setPriority(1);
        thread.start();
    }

    public void m4792a(String str, String str2) throws IllegalBlockSizeException, FileNotFoundException {
        String str3 = this.f3753b[0];
        File file = new File(str2);
        if (!file.exists()) {
            throw new FileNotFoundException();
        } else if (file.length() > 20971520) {
            throw new IllegalBlockSizeException();
        } else {
            Thread thread = new Thread(new MessageHandler(this, str, str2, str3));
            thread.setPriority(1);
            thread.start();
        }
    }

    public void m4793a(VoiceListener voiceListener) {
        try {
            String e = FileManager.m4437a(this.f3752a).m4446e(new StringBuilder(String.valueOf(Calendar.getInstance(TimeZone.getDefault()).get(1))).append("_").append(Calendar.getInstance(TimeZone.getDefault()).get(2)).append("_").append(Calendar.getInstance(TimeZone.getDefault()).get(5)).append(".amr").toString());
            if (this.f3757f) {
                this.f3756e.m4774b();
                this.f3757f = false;
                this.f3758g.cancel();
            }
            this.f3757f = true;
            this.f3756e = new AMRRecorder(this.f3752a, -1, e, voiceListener);
            this.f3756e.m4775c();
            this.f3756e.m4773a();
            this.f3758g = new Timer();
            this.f3758g.schedule(new MessageHandler(this), 60000);
        } catch (Exception e2) {
            e2.printStackTrace();
            voiceListener.m3062a(-1, e2);
        }
    }

    public void m4794b() {
        if (this.f3754c != null) {
            this.f3754c.m4780b();
            this.f3755d = 2;
        }
    }

    public void m4795b(long j) {
        Thread thread = new Thread(new MessageHandler(this, j));
        thread.setPriority(1);
        thread.start();
    }

    public void m4796c() {
        if (this.f3757f) {
            this.f3756e.m4774b();
            this.f3757f = false;
            this.f3758g.cancel();
        }
    }

    public String m4797d() {
        return this.f3756e.m4772d();
    }
}
