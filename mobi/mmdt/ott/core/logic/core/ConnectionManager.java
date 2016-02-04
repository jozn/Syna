package mobi.mmdt.ott.core.logic.core;

import android.content.Context;
import android.util.Log;
import java.net.MalformedURLException;
import java.util.ArrayList;
import mobi.mmdt.ott.core.UiRequests;
import mobi.mmdt.ott.core.logic.core.p056a.ChatConfig;
import mobi.mmdt.ott.core.logic.core.p056a.VoipConfig;
import mobi.mmdt.ott.core.logic.p024b.SipService;
import mobi.mmdt.ott.core.logic.p054a.PushListener;
import mobi.mmdt.ott.core.logic.p054a.SocketIoManager;
import mobi.mmdt.ott.core.logic.p055d.XmppManager;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.database.p062c.Files;
import mobi.mmdt.ott.core.model.database.p063d.Groups;
import mobi.mmdt.ott.core.model.database.p064e.Logs;
import mobi.mmdt.ott.core.model.database.p065f.Messages;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.p041a.Connectivity;
import mobi.mmdt.p041a.DateAndTime;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: mobi.mmdt.ott.core.logic.core.a */
public class ConnectionManager {
    public static Context f3660a;
    public static ArrayList<ChatConfig> f3661b;
    public static ArrayList<VoipConfig> f3662c;
    public static boolean f3663d;
    public static boolean f3664e;
    public static PushListener f3665f;
    private static ConnectionManager f3666g;
    private static boolean f3667h;
    private static boolean f3668i;
    private static boolean f3669j;
    private static byte f3670k;
    private static byte f3671l;

    static {
        f3661b = new ArrayList();
        f3662c = new ArrayList();
        f3667h = false;
        f3668i = false;
        f3663d = false;
        f3664e = false;
        f3669j = false;
        f3670k = (byte) 0;
        f3671l = (byte) 0;
        f3665f = new ConnectionManager();
    }

    public ConnectionManager(Context context) {
        Log.e("ConnectionManager", "ConnectionManager");
        f3660a = context;
        SocketIoManager.m4545a(f3660a).m4548a(f3665f);
    }

    public static ConnectionManager m4680a(Context context) {
        if (f3666g == null) {
            f3666g = new ConnectionManager(context);
        }
        return f3666g;
    }

    public static void m4681a() {
        try {
            String[] c = Groups.m5055c(f3660a);
            for (String b : c) {
                try {
                    XmppManager.m4723a(f3660a).m4740b(b);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            SipService.m4591a(f3660a).m4612c();
            SipService.m4591a(f3660a).m4610b();
            SipService.m4591a(f3660a).m4604a();
            AppSettings.m4867a(f3660a).m4869a();
            Messages.m5103a(f3660a);
            Logs.m5069a(f3660a);
            Files.m5031a(f3660a);
            SynaContacts.m5009b(f3660a);
            Groups.m5048a(f3660a);
            UiRequests.m4511f(f3660a);
            UiRequests.m4507d(f3660a);
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        try {
            SipService.m4591a(f3660a).m4612c();
            SipService.m4591a(f3660a).m4610b();
            SipService.m4591a(f3660a).m4604a();
            AppSettings.m4867a(f3660a).m4869a();
            Messages.m5103a(f3660a);
            Logs.m5069a(f3660a);
            Files.m5031a(f3660a);
            SynaContacts.m5009b(f3660a);
            Groups.m5048a(f3660a);
            UiRequests.m4511f(f3660a);
            UiRequests.m4507d(f3660a);
        } catch (Exception e222) {
            e222.printStackTrace();
        }
    }

    public static void m4682a(String str, String str2, String str3, int i, int i2) {
        Log.d("ConnectionManager", "connect voip called. " + str + " " + str2 + " " + i2 + " " + str3 + " " + i);
        SipService.m4591a(f3660a).m4605a(str3, i, str, str2, i2, AppSettings.m4867a(f3660a).m4913w(), AppSettings.m4867a(f3660a).m4910t());
    }

    private static void m4683a(String str, String str2, String str3, String str4, int i) {
        try {
            XmppManager.m4723a(f3660a).m4730a(str, str2, str3, str4, i);
            Log.d("ConnectionManager", "connect chat called. " + str + " " + str2 + " " + str3 + " " + str4 + " " + i);
            if (XmppManager.m4723a(f3660a).m4735a()) {
                XmppManager.m4723a(f3660a).m4739b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void m4684a(boolean z) {
        try {
            if (!f3669j || f3670k > 3) {
                if (f3671l > 5) {
                    f3663d = false;
                }
                if (f3671l > 10) {
                    Thread.sleep(10000);
                    f3669j = false;
                    f3670k = (byte) 0;
                    return;
                }
                f3670k = (byte) 0;
                f3669j = true;
                if (DateAndTime.m4086a(f3660a) && DateAndTime.m4087b(f3660a)) {
                    try {
                        Log.e("ConnectionManager", "check");
                        if (AppSettings.m4867a(f3660a).m4897j() == null || AppSettings.m4867a(f3660a).m4901l() == null || AppSettings.m4867a(f3660a).m4903m() == null) {
                            Log.e("ConnectionManager", "userName or phone or pincode is null");
                            f3669j = false;
                            return;
                        } else if (Connectivity.m4082b(f3660a)) {
                            if (f3661b.size() == 0 || f3662c.size() == 0) {
                                f3671l = (byte) (f3671l + 1);
                                if (f3663d) {
                                    Log.e("ConnectionManager", "try to fetch configs");
                                } else {
                                    Log.e("ConnectionManager", "no config...");
                                    f3663d = true;
                                    SocketIoManager.m4545a(f3660a).m4548a(f3665f);
                                    try {
                                        SocketIoManager.m4545a(f3660a).m4546a();
                                    } catch (MalformedURLException e) {
                                        f3663d = false;
                                        e.printStackTrace();
                                    }
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (!(f3661b.size() == 0 || f3662c.size() == 0 || f3664e)) {
                                boolean a;
                                f3671l = (byte) 0;
                                Thread thread = new Thread(new ConnectionManager());
                                thread.setPriority(1);
                                thread.start();
                                try {
                                    a = XmppManager.m4723a(f3660a).m4737a(z);
                                } catch (Exception e3) {
                                    a = false;
                                }
                                if (a) {
                                    Log.d("ConnectionManager", "Connected to xmpp server");
                                } else {
                                    Log.e("ConnectionManager", "connect to xmpp");
                                    ConnectionManager.m4683a(((ChatConfig) f3661b.get(0)).m4678e(), ((ChatConfig) f3661b.get(0)).m4675b(), ((ChatConfig) f3661b.get(0)).m4679f(), ((ChatConfig) f3661b.get(0)).m4676c(), ((ChatConfig) f3661b.get(0)).m4677d());
                                }
                                switch (((VoipConfig) f3662c.get(0)).m4674a()) {
                                    case VideoSize.CIF /*1*/:
                                        ConnectionManager.m4682a(((VoipConfig) f3662c.get(0)).m4678e(), ((VoipConfig) f3662c.get(0)).m4675b(), ((VoipConfig) f3662c.get(0)).m4676c(), ((VoipConfig) f3662c.get(0)).m4677d(), 0);
                                        break;
                                    case VideoSize.HVGA /*2*/:
                                        ConnectionManager.m4682a(((VoipConfig) f3662c.get(0)).m4678e(), ((VoipConfig) f3662c.get(0)).m4675b(), ((VoipConfig) f3662c.get(0)).m4676c(), ((VoipConfig) f3662c.get(0)).m4677d(), 2);
                                        break;
                                    case Version.API03_CUPCAKE_15 /*3*/:
                                        ConnectionManager.m4682a(((VoipConfig) f3662c.get(0)).m4678e(), ((VoipConfig) f3662c.get(0)).m4675b(), ((VoipConfig) f3662c.get(0)).m4676c(), ((VoipConfig) f3662c.get(0)).m4677d(), 1);
                                        break;
                                    default:
                                        ConnectionManager.m4682a(((VoipConfig) f3662c.get(0)).m4678e(), ((VoipConfig) f3662c.get(0)).m4675b(), ((VoipConfig) f3662c.get(0)).m4676c(), ((VoipConfig) f3662c.get(0)).m4677d(), 0);
                                        break;
                                }
                                if (z) {
                                    Log.e("ConnectionManager", "refresh sip register");
                                    SipService.m4591a(f3660a).m4613d();
                                }
                            }
                            if (f3664e) {
                                f3664e = false;
                            }
                            f3669j = false;
                            return;
                        } else {
                            f3669j = false;
                            return;
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                        f3669j = false;
                    }
                } else {
                    Log.d("ManagerService", "Wrong date and time.");
                    f3669j = false;
                    return;
                }
            }
            f3670k = (byte) (f3670k + 1);
            f3671l = (byte) (f3671l + 1);
        } catch (Exception e42) {
            e42.printStackTrace();
        }
    }

    private static void m4687c() {
        if (f3667h && f3668i) {
            f3667h = false;
            f3668i = false;
            ConnectionManager.m4684a(false);
            SocketIoManager.m4545a(f3660a).m4549b();
        }
    }
}
