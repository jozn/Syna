package mobi.mmdt.ott.core.logic.message;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import mobi.mmdt.ott.asmackengine.chat.Chat.Chat;
import mobi.mmdt.ott.core.logic.p055d.MessageManager;
import mobi.mmdt.ott.core.logic.p055d.XmppListenr;
import mobi.mmdt.ott.core.logic.p055d.XmppManager;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p062c.FileRow;
import mobi.mmdt.ott.core.model.database.p062c.Files;
import mobi.mmdt.ott.core.model.database.p064e.Logs;
import mobi.mmdt.ott.core.model.database.p065f.MessageRow;
import mobi.mmdt.ott.core.model.database.p065f.Messages;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.FileManager;
import mobi.mmdt.ott.core.p051a.Statics;
import mobi.mmdt.ott.core.p051a.p052a.MemoryException;
import mobi.mmdt.p041a.DateAndTime;
import mobi.mmdt.p041a.FileUtility;
import mobi.mmdt.p041a.SDCardTools;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: mobi.mmdt.ott.core.logic.message.i */
public class MessageService {
    private static MessageService f3773b;
    long f3774a;
    private ServiceConnection f3775c;
    private Context f3776d;
    private TransmitterService f3777e;
    private XmppListenr f3778f;

    static {
        f3773b = null;
    }

    private MessageService(Context context) {
        this.f3775c = new MessageService(this);
        this.f3774a = 0;
        this.f3778f = new MessageService(this);
        this.f3776d = context;
        try {
            this.f3776d.bindService(new Intent(this.f3776d, TransmitterService.class), this.f3775c, 1);
        } catch (Exception e) {
        }
        try {
            Thread thread = new Thread(new MessageService(this));
            thread.setPriority(1);
            thread.start();
        } catch (Exception e2) {
        }
    }

    public static MessageService m4799a(Context context) {
        if (f3773b == null) {
            f3773b = new MessageService(context);
        }
        return f3773b;
    }

    private void m4800a(Uri uri) {
        MessageRow b = Messages.m5109b(this.f3776d, uri);
        if (b.m5093j() == 1) {
            if (b.m5087d() == 3 && Files.m5036c(this.f3776d, Uri.parse(DatabaseContentProvider.m4930d(this.f3776d) + "/" + b.m5088e())).m5021b() == 1) {
                try {
                    m4806a(b.m5091h());
                } catch (MemoryException e) {
                    e.printStackTrace();
                }
            }
        } else if ((b.m5087d() == 3 || b.m5087d() == 2 || b.m5087d() == 4) && Files.m5036c(this.f3776d, Uri.parse(DatabaseContentProvider.m4930d(this.f3776d) + "/" + b.m5088e())).m5021b() == 1) {
            try {
                m4806a(b.m5091h());
            } catch (MemoryException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void m4802b() {
        if (!AppSettings.m4867a(this.f3776d).m4911u()) {
            try {
                Statics.m4466f(this.f3776d);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!AppSettings.m4867a(this.f3776d).m4911u()) {
            try {
                Statics.m4464d(this.f3776d);
            } catch (ClassCastException e2) {
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        if (!AppSettings.m4867a(this.f3776d).m4911u()) {
            try {
                Statics.m4465e(this.f3776d);
            } catch (Exception e32) {
                e32.printStackTrace();
            }
        }
    }

    public synchronized void m4805a() {
        if (XmppManager.m4723a(this.f3776d).m4737a(true)) {
            Uri[] b = Messages.m5110b(this.f3776d, DateAndTime.m4084a() - 600000);
            for (int length = b.length - 1; length > -1; length--) {
                MessageRow b2 = Messages.m5109b(this.f3776d, b[length]);
                try {
                    FileRow c;
                    switch (b2.m5087d()) {
                        case VideoSize.CIF /*1*/:
                            if (!b2.m5085b()) {
                                MessageManager.m4703a(this.f3776d).m4707a(b2.m5086c(), b2.m5089f(), "SIMPLE_CHAT", new String(b2.m5090g()), Long.parseLong(b2.m5084a()));
                                break;
                            } else {
                                MessageManager.m4703a(this.f3776d).m4707a(b2.m5086c(), b2.m5089f(), "GROUP_CHAT", new String(b2.m5090g()), Long.parseLong(b2.m5084a()));
                                break;
                            }
                        case VideoSize.HVGA /*2*/:
                            c = Files.m5036c(this.f3776d, Uri.parse(DatabaseContentProvider.m4930d(this.f3776d) + "/" + b2.m5088e()));
                            MessageService.m4799a(this.f3776d).m4812a(new String(b2.m5090g()), b2.m5086c(), c.m5022c(), c.m5024e(), c.m5025f(), "", b2.m5085b(), Uri.parse(DatabaseContentProvider.m4929c(this.f3776d) + "/" + b2.m5091h()), new StringBuilder(String.valueOf(b2.m5084a())).toString(), b2.m5089f());
                            break;
                        case Version.API03_CUPCAKE_15 /*3*/:
                            FileRow c2 = Files.m5036c(this.f3776d, Uri.parse(DatabaseContentProvider.m4930d(this.f3776d) + "/" + b2.m5088e()));
                            MessageService.m4799a(this.f3776d).m4811a(new String(b2.m5090g()), b2.m5086c(), c2.m5022c(), c2.m5024e(), c2.m5025f(), c2.m5020a(), b2.m5085b(), Uri.parse(DatabaseContentProvider.m4929c(this.f3776d) + "/" + b2.m5091h()), new StringBuilder(String.valueOf(b2.m5084a())).toString(), b2.m5089f());
                            break;
                        case Version.API04_DONUT_16 /*4*/:
                            c = Files.m5036c(this.f3776d, Uri.parse(DatabaseContentProvider.m4930d(this.f3776d) + "/" + b2.m5088e()));
                            MessageService.m4799a(this.f3776d).m4819b(new String(b2.m5090g()), b2.m5086c(), c.m5022c(), c.m5024e(), c.m5025f(), "", b2.m5085b(), Uri.parse(DatabaseContentProvider.m4929c(this.f3776d) + "/" + b2.m5091h()), new StringBuilder(String.valueOf(b2.m5084a())).toString(), b2.m5089f());
                            break;
                    }
                    MessageService.m4799a(this.f3776d).m4809a(new StringBuilder(String.valueOf(b2.m5091h())).toString(), 7500);
                    Messages.m5094a(this.f3776d, b[length], 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Uri[] c3 = Messages.m5114c(this.f3776d, DateAndTime.m4084a() - 600000);
        for (Uri a : c3) {
            Messages.m5094a(this.f3776d, a, 1);
        }
    }

    public void m4806a(long j) throws MemoryException {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            throw new MemoryException(1);
        } else if (SDCardTools.m4107a() < 10) {
            throw new MemoryException(2);
        } else {
            Thread thread = new Thread(new MessageService(this, j));
            thread.setPriority(1);
            thread.start();
        }
    }

    public synchronized void m4807a(Context context, String str, boolean z) {
        Thread thread = new Thread(new MessageService(this, context, z, str));
        thread.setPriority(1);
        thread.start();
    }

    public void m4808a(String str) {
    }

    public void m4809a(String str, long j) {
        new Timer().schedule(new MessageService(this, str), j);
    }

    public void m4810a(String str, String str2) {
        long a = DateAndTime.m4084a();
        String str3 = "AND_" + AppSettings.m4867a(this.f3776d).m4897j() + "_" + a;
        Uri a2 = Messages.m5100a(this.f3776d, str3, new StringBuilder(String.valueOf(AppSettings.m4867a(this.f3776d).m4905o())).append(AppSettings.m4867a(this.f3776d).m4903m()).toString(), str2, 0, a, str.getBytes(), 5, new StringBuilder(String.valueOf(a)).toString(), true);
        Thread thread = new Thread(new MessageService(this, str2, a, str3, str, a2));
        m4800a(a2);
        if (thread != null) {
            thread.setPriority(1);
            thread.start();
        }
    }

    public void m4811a(String str, String str2, String str3, int i, String str4, int i2, boolean z, Uri uri, String str5, String str6) throws NotConnectedException {
        String b;
        if (z) {
            try {
                b = MessageManager.m4703a(this.f3776d).m4713b(str2, str6, "GROUP_CHAT", str, Long.parseLong(str5), str3, (long) i, (long) i2, str4);
            } catch (NotConnectedException e) {
                e.printStackTrace();
                throw new NotConnectedException();
            }
        }
        b = MessageManager.m4703a(this.f3776d).m4713b(str2, str6, "SIMPLE_CHAT", str, Long.parseLong(str5), str3, (long) i, (long) i2, str4);
        Messages.m5095a(this.f3776d, uri, b);
    }

    public void m4812a(String str, String str2, String str3, int i, String str4, String str5, boolean z, Uri uri, String str6, String str7) throws NotConnectedException {
        String a;
        if (z) {
            try {
                a = MessageManager.m4703a(this.f3776d).m4708a(str2, str7, "GROUP_CHAT", str, Long.parseLong(str6), str3, (long) i, 0, str4);
            } catch (NotConnectedException e) {
                e.printStackTrace();
                throw new NotConnectedException();
            }
        }
        a = MessageManager.m4703a(this.f3776d).m4708a(str2, str7, "SIMPLE_CHAT", str, Long.parseLong(str6), str3, (long) i, 0, str4);
        Messages.m5095a(this.f3776d, uri, a);
    }

    public void m4813a(String str, String str2, String str3, boolean z) {
        int i;
        Object obj;
        String stringBuilder;
        String str4;
        long a = DateAndTime.m4084a();
        File file = new File(str2);
        int i2 = -1;
        if (FileUtility.m4096d(str2)) {
            i2 = Statics.m4452a(this.f3776d, str2);
        }
        String str5 = "AND_" + AppSettings.m4867a(this.f3776d).m4897j() + "_" + a;
        if (FileUtility.m4096d(file.getName())) {
            i = 3;
            obj = null;
        } else if (FileUtility.m4097e(file.getName())) {
            i = 4;
            r5 = Uri.fromFile(new File(FileManager.m4437a(this.f3776d).m4440a(file.getName(), str2)));
        } else if (FileUtility.m4093a(file.getName())) {
            i = 2;
            r5 = Uri.fromFile(new File(FileManager.m4437a(this.f3776d).m4440a(file.getName(), str2)));
        } else {
            i = 0;
            obj = null;
        }
        Uri a2 = Files.m5030a(this.f3776d, file.getName(), Uri.fromFile(file), obj, (long) ((int) file.length()), "", 1, i2, 2, 0, "");
        if (z) {
            stringBuilder = new StringBuilder(String.valueOf(AppSettings.m4867a(this.f3776d).m4905o())).append(AppSettings.m4867a(this.f3776d).m4903m()).toString();
            str4 = str3;
        } else {
            str4 = str3;
            stringBuilder = str3;
        }
        Uri a3 = Messages.m5099a(this.f3776d, str5, stringBuilder, str4, 0, a, str.getBytes(), 1, Integer.parseInt(a2.getLastPathSegment()), i, new StringBuilder(String.valueOf(a)).toString(), z);
        Logs.m5067a(this.f3776d, 4, 1, str3, a, "");
        m4800a(a3);
    }

    public void m4814a(String str, Chat chat, boolean z) {
        try {
            XmppManager.m4723a(this.f3776d).m4732a(str, chat, z);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m4815a(String str, boolean z) {
        XmppManager.m4723a(this.f3776d).m4736a(str, z);
    }

    public void m4816b(long j) {
        Thread thread = new Thread(new MessageService(this, j));
        thread.setPriority(1);
        thread.start();
    }

    public void m4817b(String str) throws IOException {
    }

    public void m4818b(String str, String str2) {
        m4807a(this.f3776d, str2, true);
        long a = DateAndTime.m4084a();
        String str3 = "AND_" + AppSettings.m4867a(this.f3776d).m4897j() + "_" + a;
        Uri a2 = Messages.m5100a(this.f3776d, str3, str2, str2, 0, a, str.getBytes(), 5, new StringBuilder(String.valueOf(a)).toString(), false);
        Thread thread = new Thread(new MessageService(this, str2, a, str3, str, a2));
        m4800a(a2);
        if (thread != null) {
            thread.setPriority(1);
            thread.start();
        }
    }

    public void m4819b(String str, String str2, String str3, int i, String str4, String str5, boolean z, Uri uri, String str6, String str7) throws NotConnectedException {
        String a;
        if (z) {
            try {
                a = MessageManager.m4703a(this.f3776d).m4709a(str2, str7, "GROUP_CHAT", str, Long.parseLong(str6), str3, (long) i, str4);
            } catch (NotConnectedException e) {
                e.printStackTrace();
                throw new NotConnectedException();
            }
        }
        a = MessageManager.m4703a(this.f3776d).m4709a(str2, str7, "SIMPLE_CHAT", str, Long.parseLong(str6), str3, (long) i, str4);
        Messages.m5095a(this.f3776d, uri, a);
    }
}
