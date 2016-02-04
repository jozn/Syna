package mobi.mmdt.ott.asmackengine.core;

import android.util.Log;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import mobi.mmdt.ott.asmackengine.groupchat.Group;
import mobi.mmdt.ott.asmackengine.profile.Profile;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.Roster.SubscriptionMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jivesoftware.smack.util.TLSUtils;
import org.jivesoftware.smackx.iqlast.LastActivityManager;
import org.jivesoftware.smackx.ping.PingFailedListener;
import org.jivesoftware.smackx.ping.PingManager;

/* renamed from: mobi.mmdt.ott.asmackengine.core.a */
public class Core {
    private static Core f3267a;
    private CoreListener f3268b;
    private XMPPTCPConnection f3269c;
    private XMPPTCPConnectionConfiguration f3270d;
    private int f3271e;
    private String f3272f;
    private String f3273g;
    private String f3274h;
    private String f3275i;
    private String f3276j;
    private boolean f3277k;
    private HostnameVerifier f3278l;
    private long f3279m;
    private boolean f3280n;
    private ConnectionListener f3281o;
    private PingFailedListener f3282p;

    static {
        Roster.setDefaultSubscriptionMode(SubscriptionMode.accept_all);
        LastActivityManager.setEnabledPerDefault(true);
        XMPPTCPConnection.setUseStreamManagementDefault(true);
        XMPPTCPConnection.setUseStreamManagementResumptiodDefault(true);
        AbstractXMPPConnection.setReplyToUnknownIqDefault(true);
    }

    private Core() {
        this.f3277k = false;
        this.f3278l = new Core(this);
        this.f3279m = -1;
        this.f3280n = false;
        this.f3281o = new Core$2(this);
        this.f3282p = new Core$3(this);
    }

    public static Core m4198a() {
        if (f3267a == null) {
            f3267a = new Core();
        }
        return f3267a;
    }

    private void m4202i() throws SmackException, IOException, XMPPException, KeyManagementException, KeyStoreException, NoSuchAlgorithmException {
        GeneralSecurityException e;
        Exception e2;
        if (this.f3274h == null || this.f3275i == null || this.f3273g == null || this.f3272f == null || this.f3276j == null) {
            throw new NullPointerException();
        }
        try {
            m4214h();
        } catch (NotConnectedException e3) {
            e3.printStackTrace();
        }
        if (this.f3270d == null) {
            m4204k();
            try {
                this.f3270d = ((Builder) ((Builder) ((Builder) ((Builder) ((Builder) ((Builder) ((Builder) ((Builder) XMPPTCPConnectionConfiguration.builder().setSendPresence(true)).setCompressionEnabled(false).setDebuggerEnabled(false)).setSecurityMode(SecurityMode.required)).setCustomSSLContext(m4203j())).setHost(this.f3272f)).setPort(this.f3271e)).setServiceName(this.f3273g)).setHostnameVerifier(this.f3278l)).build();
            } catch (KeyManagementException e4) {
                e = e4;
                e.printStackTrace();
                if (this.f3269c == null) {
                    this.f3269c = new XMPPTCPConnection(this.f3270d);
                    this.f3269c.addConnectionListener(this.f3281o);
                    Group.m4235a().m4245a(this.f3269c);
                }
                Log.d("AsmackEngine", "Try to connect to " + this.f3272f + " " + this.f3271e);
                this.f3269c.connect();
                Thread.sleep(300);
                Log.d("AsmackEngine", "Connected to " + this.f3272f + " " + this.f3271e);
                this.f3269c.login(this.f3274h, this.f3275i, this.f3276j);
                Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
                Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
                Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
                Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
            } catch (KeyStoreException e5) {
                e = e5;
                e.printStackTrace();
                if (this.f3269c == null) {
                    this.f3269c = new XMPPTCPConnection(this.f3270d);
                    this.f3269c.addConnectionListener(this.f3281o);
                    Group.m4235a().m4245a(this.f3269c);
                }
                Log.d("AsmackEngine", "Try to connect to " + this.f3272f + " " + this.f3271e);
                this.f3269c.connect();
                Thread.sleep(300);
                Log.d("AsmackEngine", "Connected to " + this.f3272f + " " + this.f3271e);
                this.f3269c.login(this.f3274h, this.f3275i, this.f3276j);
                Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
                Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
                Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
                Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
            } catch (NoSuchAlgorithmException e6) {
                e = e6;
                e.printStackTrace();
                if (this.f3269c == null) {
                    this.f3269c = new XMPPTCPConnection(this.f3270d);
                    this.f3269c.addConnectionListener(this.f3281o);
                    Group.m4235a().m4245a(this.f3269c);
                }
                Log.d("AsmackEngine", "Try to connect to " + this.f3272f + " " + this.f3271e);
                this.f3269c.connect();
                Thread.sleep(300);
                Log.d("AsmackEngine", "Connected to " + this.f3272f + " " + this.f3271e);
                this.f3269c.login(this.f3274h, this.f3275i, this.f3276j);
                Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
                Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
                Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
                Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
            }
        }
        if (this.f3269c == null) {
            this.f3269c = new XMPPTCPConnection(this.f3270d);
            this.f3269c.addConnectionListener(this.f3281o);
            Group.m4235a().m4245a(this.f3269c);
        }
        Log.d("AsmackEngine", "Try to connect to " + this.f3272f + " " + this.f3271e);
        try {
            this.f3269c.connect();
        } catch (SmackException e7) {
            e2 = e7;
            e2.printStackTrace();
            Thread.sleep(300);
            Log.d("AsmackEngine", "Connected to " + this.f3272f + " " + this.f3271e);
            this.f3269c.login(this.f3274h, this.f3275i, this.f3276j);
            Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
            Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
            Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
            Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
        } catch (IOException e8) {
            e2 = e8;
            e2.printStackTrace();
            Thread.sleep(300);
            Log.d("AsmackEngine", "Connected to " + this.f3272f + " " + this.f3271e);
            this.f3269c.login(this.f3274h, this.f3275i, this.f3276j);
            Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
            Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
            Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
            Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
        } catch (XMPPException e9) {
            e2 = e9;
            e2.printStackTrace();
            Thread.sleep(300);
            Log.d("AsmackEngine", "Connected to " + this.f3272f + " " + this.f3271e);
            this.f3269c.login(this.f3274h, this.f3275i, this.f3276j);
            Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
            Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
            Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
            Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e10) {
            e10.printStackTrace();
        }
        Log.d("AsmackEngine", "Connected to " + this.f3272f + " " + this.f3271e);
        try {
            this.f3269c.login(this.f3274h, this.f3275i, this.f3276j);
        } catch (XMPPException e11) {
            e2 = e11;
            e2.printStackTrace();
            Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
            Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
            Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
            Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
        } catch (SmackException e12) {
            e2 = e12;
            e2.printStackTrace();
            Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
            Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
            Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
            Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
        } catch (IOException e13) {
            e2 = e13;
            e2.printStackTrace();
            Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
            Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
            Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
            Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
        }
        Log.d("AsmackEngine", "Authenticated to " + this.f3272f + " " + this.f3271e);
        Roster.getInstanceFor(this.f3269c).setSubscriptionMode(SubscriptionMode.accept_all);
        Roster.getInstanceFor(Core.m4198a().m4210d()).setRosterLoadedAtLogin(true);
        Roster.getInstanceFor(this.f3269c).addRosterListener(Profile.m4265a().m4272b());
    }

    private SSLContext m4203j() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        Core core = new Core(this);
        SSLContext instance = SSLContext.getInstance(TLSUtils.TLS);
        instance.init(null, new TrustManager[]{core}, null);
        return instance;
    }

    private void m4204k() {
    }

    public void m4205a(String str, String str2, String str3, String str4, String str5, int i) {
        this.f3274h = str;
        this.f3275i = str2;
        this.f3273g = str4;
        this.f3272f = str5;
        this.f3271e = i;
        this.f3276j = str3;
    }

    public void m4206a(CoreListener coreListener) {
        this.f3268b = coreListener;
    }

    public boolean m4207a(boolean z) {
        if (this.f3269c == null || !this.f3269c.isConnected() || !this.f3269c.isAuthenticated()) {
            return false;
        }
        if (!z) {
            return true;
        }
        try {
            if (System.currentTimeMillis() - this.f3279m < 2500 && this.f3280n) {
                return true;
            }
            if (PingManager.getInstanceFor(this.f3269c).pingMyServer()) {
                this.f3280n = true;
                this.f3279m = System.currentTimeMillis();
                return true;
            }
            this.f3279m = System.currentTimeMillis();
            this.f3280n = false;
            return false;
        } catch (NotConnectedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String m4208b() {
        return this.f3274h;
    }

    public String m4209c() {
        return this.f3273g;
    }

    public XMPPConnection m4210d() {
        return this.f3269c;
    }

    public boolean m4211e() {
        return this.f3277k;
    }

    public boolean m4212f() {
        return (this.f3274h == null || this.f3275i == null || this.f3273g == null || this.f3272f == null) ? false : true;
    }

    public synchronized void m4213g() throws SmackException, IOException, XMPPException, GeneralSecurityException {
        GeneralSecurityException e;
        this.f3277k = true;
        try {
            m4202i();
            this.f3277k = false;
        } catch (KeyManagementException e2) {
            e = e2;
            this.f3277k = false;
            e.printStackTrace();
            throw e;
        } catch (KeyStoreException e3) {
            e = e3;
            this.f3277k = false;
            e.printStackTrace();
            throw e;
        } catch (NoSuchAlgorithmException e4) {
            e = e4;
            this.f3277k = false;
            e.printStackTrace();
            throw e;
        } catch (Exception e5) {
            this.f3277k = false;
            e5.printStackTrace();
            throw e5;
        }
    }

    public synchronized void m4214h() throws NotConnectedException {
        if (this.f3269c != null) {
            this.f3269c.disconnect();
            this.f3269c = null;
            this.f3270d = null;
            Thread thread = new Thread(new Core(this));
            Thread thread2 = new Thread(new Core(this));
            Thread thread3 = new Thread(new Core(this));
            thread.setPriority(1);
            thread2.setPriority(1);
            thread3.setPriority(1);
            thread.start();
            thread2.start();
            thread3.start();
        }
    }
}
