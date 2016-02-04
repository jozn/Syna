package com.mmdt.syna.view.main;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import com.mmdt.syna.p017a.p018a.MessagesNotificationManager;
import com.mmdt.syna.view.components.p028d.SmartFragmentStatePagerAdapter;
import com.mmdt.syna.view.conversation.publicchat.ExplorePublicChatsListActivity;
import com.mmdt.syna.view.conversation.publicchat.PublicChatsListFragment;
import com.mmdt.syna.view.forceaction.ForceSettingActivity;
import com.mmdt.syna.view.forceaction.ForceUpdateActivity;
import com.mmdt.syna.view.introduction.IntroductionActivity;
import com.mmdt.syna.view.more.MoreActivity;
import com.mmdt.syna.view.p019a.ContactsListMainFragment;
import com.mmdt.syna.view.p022b.ConversationsListFragment;
import com.mmdt.syna.view.p023c.DialpadFragment.DialpadFragment;
import com.mmdt.syna.view.p033d.LogsListMainFragment;
import com.mmdt.syna.view.registeration.accesscode.AccessCodeActivity;
import com.mmdt.syna.view.registeration.language.WelcomeActivity;
import com.mmdt.syna.view.selectcontactmessage.NewGroupChatContactListActivity;
import com.mmdt.syna.view.selectcontactmessage.NewMessageContactListActivity;
import com.mmdt.syna.view.tools.CallAndMessageHandler;
import java.lang.reflect.Field;
import java.util.Locale;
import mobi.mmdt.ott.core.logic.core.NotificationService;
import mobi.mmdt.ott.core.logic.receivers.ScreenChangerBroadcastReceiver;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.Statics;
import mobi.mmdt.p041a.DateAndTime;
import org.linphone.C0282R;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class MainActivity extends FragmentActivity implements TabListener, DialpadFragment {
    private static int f2519L;
    public static boolean f2520n;
    private ContactsListMainFragment f2521A;
    private LogsListMainFragment f2522B;
    private ConversationsListFragment f2523C;
    private PublicChatsListFragment f2524D;
    private Tab f2525E;
    private Tab f2526F;
    private Tab f2527G;
    private Tab f2528H;
    private Tab f2529I;
    private BroadcastReceiver f2530J;
    private ScreenChangerBroadcastReceiver f2531K;
    private C0110c f2532o;
    private ViewPager f2533p;
    private ActionBar f2534q;
    private MenuItem f2535r;
    private MenuItem f2536s;
    private MenuItem f2537t;
    private MenuItem f2538u;
    private MenuItem f2539v;
    private MenuItem f2540w;
    private MenuItem f2541x;
    private MenuItem f2542y;
    private com.mmdt.syna.view.p023c.DialpadFragment f2543z;

    /* renamed from: com.mmdt.syna.view.main.MainActivity.a */
    public enum C0108a {
        DialPad(2130837923),
        Contacts(2130838020),
        Conversations(2130838017),
        Logs(2130837890);
        
        private final int f2516e;

        private C0108a(int i) {
            this.f2516e = i;
        }
    }

    /* renamed from: com.mmdt.syna.view.main.MainActivity.b */
    private class C0109b extends AsyncTask<String, Void, String> {
        final /* synthetic */ MainActivity f2517a;

        private C0109b(MainActivity mainActivity) {
            this.f2517a = mainActivity;
        }

        protected String m3476a(String... strArr) {
            try {
                AppSettings.m4867a(this.f2517a.getApplicationContext()).m4874a(false);
                Statics.m4455a(this.f2517a.getApplicationContext());
                try {
                    this.f2517a.getApplicationContext().startService(new Intent(this.f2517a.getApplicationContext(), NotificationService.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return "";
        }

        protected void m3477a(String str) {
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m3476a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3477a((String) obj);
        }
    }

    /* renamed from: com.mmdt.syna.view.main.MainActivity.c */
    public class C0110c extends SmartFragmentStatePagerAdapter {
        final /* synthetic */ MainActivity f2518a;

        public C0110c(MainActivity mainActivity, FragmentManager fragmentManager) {
            this.f2518a = mainActivity;
            super(fragmentManager);
        }

        public Fragment m3478a(int i) {
            switch (i) {
                case VideoSize.QCIF /*0*/:
                    return this.f2518a.f2543z;
                case VideoSize.CIF /*1*/:
                    return this.f2518a.f2521A;
                case VideoSize.HVGA /*2*/:
                    return this.f2518a.f2523C;
                case Version.API03_CUPCAKE_15 /*3*/:
                    return this.f2518a.f2522B;
                case Version.API04_DONUT_16 /*4*/:
                    return this.f2518a.f2524D;
                default:
                    return this.f2518a.f2543z;
            }
        }

        public int m3479b() {
            return C0108a.values().length;
        }

        public CharSequence m3480c(int i) {
            return MainActivity.m3488d(i);
        }
    }

    static {
        f2520n = false;
        f2519L = 0;
    }

    private static CharSequence m3488d(int i) {
        return C0108a.values()[i].name().toUpperCase(Locale.getDefault());
    }

    private void m3490e(int i) {
        if (this.f2535r != null) {
            this.f2540w.setVisible(true);
            switch (i) {
                case VideoSize.QCIF /*0*/:
                    this.f2535r.setVisible(false);
                    this.f2536s.setVisible(false);
                    this.f2537t.setVisible(false);
                    this.f2538u.setVisible(false);
                    this.f2539v.setVisible(false);
                    this.f2541x.setVisible(false);
                    this.f2542y.setVisible(false);
                case VideoSize.CIF /*1*/:
                    this.f2535r.setVisible(true);
                    this.f2536s.setVisible(true);
                    this.f2537t.setVisible(false);
                    this.f2538u.setVisible(false);
                    this.f2539v.setVisible(false);
                    this.f2541x.setVisible(false);
                    this.f2542y.setVisible(false);
                case VideoSize.HVGA /*2*/:
                    this.f2535r.setVisible(false);
                    this.f2536s.setVisible(false);
                    this.f2537t.setVisible(true);
                    this.f2538u.setVisible(true);
                    this.f2539v.setVisible(true);
                    this.f2541x.setVisible(false);
                    this.f2542y.setVisible(false);
                case Version.API03_CUPCAKE_15 /*3*/:
                    this.f2535r.setVisible(false);
                    this.f2536s.setVisible(false);
                    this.f2537t.setVisible(false);
                    this.f2538u.setVisible(false);
                    this.f2539v.setVisible(false);
                    this.f2541x.setVisible(true);
                    this.f2542y.setVisible(false);
                case Version.API04_DONUT_16 /*4*/:
                    this.f2535r.setVisible(false);
                    this.f2536s.setVisible(false);
                    this.f2537t.setVisible(false);
                    this.f2538u.setVisible(false);
                    this.f2539v.setVisible(false);
                    this.f2541x.setVisible(false);
                    this.f2542y.setVisible(true);
                default:
            }
        }
    }

    private void m3493g() {
        try {
            IntentFilter intentFilter = new IntentFilter("SYNA_MainActivity.IntentFilter");
            this.f2530J = new MainActivity(this);
            registerReceiver(this.f2530J, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m3494h() {
        try {
            Intent intent = new Intent(this, NewMessageContactListActivity.class);
            intent.putExtra("ConversationActivity_start_mode", 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m3496i() {
        try {
            Intent intent = new Intent(this, NewGroupChatContactListActivity.class);
            intent.putExtra("NewGroupChatContactListActivity_start_mode", 0);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m3498j() {
        try {
            Intent intent = new Intent(this, NewGroupChatContactListActivity.class);
            intent.putExtra("NewGroupChatContactListActivity_start_mode", 3);
            startActivity(intent);
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m3501a(int i) {
        try {
            if (this.f2527G != null) {
                TabUtils.m3513a(this.f2527G, i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m3502b(String str) {
        try {
            if (str.length() > 0) {
                CallAndMessageHandler.m4021b(this, str, true);
            } else {
                m3504f();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void b_(String str) {
        try {
            Intent intent = new Intent("android.intent.action.INSERT");
            intent.setType("vnd.android.cursor.dir/contact");
            if (str != null && str.length() > 0) {
                intent.putExtra("phone", str);
            }
            startActivityForResult(intent, 2);
            overridePendingTransition(0, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m3503c(String str) {
        try {
            CallAndMessageHandler.m4020b(this, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void m3504f() {
        try {
            this.f2534q.setSelectedNavigationItem(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 15648) {
            finish();
        }
    }

    public void onBackPressed() {
        if (this.f2534q.getSelectedTab().getPosition() == 1 && this.f2521A.m2507E()) {
            this.f2521A.m2515a(true);
        } else {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.f2531K = new ScreenChangerBroadcastReceiver();
            getApplicationContext().registerReceiver(this.f2531K, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (AppSettings.m4867a(getApplicationContext()).m4907q() > getPackageManager().getPackageInfo(getPackageName(), 0).versionCode) {
                startActivity(new Intent(getApplicationContext(), ForceUpdateActivity.class));
                finish();
                return;
            }
            try {
                new C0109b().execute(new String[0]);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                if (AppSettings.m4867a(getApplicationContext()).m4897j() == null && AppSettings.m4867a(getApplicationContext()).m4903m() == null) {
                    startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    finish();
                } else if (AppSettings.m4867a(getApplicationContext()).m4903m() != null && AppSettings.m4867a(getApplicationContext()).m4897j() == null) {
                    startActivity(new Intent(getApplicationContext(), AccessCodeActivity.class));
                    finish();
                } else if (DateAndTime.m4086a(getApplicationContext()) && DateAndTime.m4087b(getApplicationContext())) {
                    setContentView(C0282R.layout.activity_main);
                    this.f2534q = getActionBar();
                    this.f2534q.setNavigationMode(2);
                    this.f2543z = new com.mmdt.syna.view.p023c.DialpadFragment(this);
                    this.f2521A = new ContactsListMainFragment();
                    this.f2522B = new LogsListMainFragment();
                    this.f2523C = new ConversationsListFragment();
                    this.f2524D = new PublicChatsListFragment();
                    this.f2532o = new C0110c(this, m121e());
                    this.f2533p = (ViewPager) findViewById(2131427494);
                    this.f2533p.setAdapter(this.f2532o);
                    this.f2533p.setOnPageChangeListener(new MainActivity(this));
                    for (int i = 0; i < C0108a.values().length; i++) {
                        Tab tabListener = this.f2534q.newTab().setCustomView(TabUtils.m3512a(this, C0108a.values()[i].f2516e, 0)).setTabListener(this);
                        if (i == 2) {
                            this.f2527G = tabListener;
                        } else if (i == 3) {
                            this.f2528H = tabListener;
                        } else if (i == 1) {
                            this.f2526F = tabListener;
                        } else if (i == 4) {
                            this.f2529I = tabListener;
                        } else if (i == 0) {
                            this.f2525E = tabListener;
                        }
                        this.f2534q.addTab(tabListener);
                    }
                    this.f2534q.setDisplayShowHomeEnabled(false);
                    this.f2534q.setDisplayShowTitleEnabled(false);
                    if (!AppSettings.m4867a(getApplicationContext()).m4912v()) {
                        startActivity(new Intent(getApplicationContext(), IntroductionActivity.class));
                    }
                    try {
                        ViewConfiguration viewConfiguration = ViewConfiguration.get(this);
                        Field declaredField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
                        if (declaredField != null) {
                            declaredField.setAccessible(true);
                            declaredField.setBoolean(viewConfiguration, false);
                        }
                    } catch (Exception e3) {
                    }
                    Bundle extras = getIntent().getExtras();
                    if (extras != null && extras.containsKey("com.mmdt.sipclient.view.MainActivity.JOB_VALUE")) {
                        this.f2533p.post(new MainActivity(this, extras.getInt("com.mmdt.sipclient.view.MainActivity.JOB_VALUE")));
                    }
                    try {
                        Thread thread = new Thread(new MainActivity(this));
                        thread.setPriority(1);
                        thread.start();
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), ForceSettingActivity.class));
                    finish();
                }
            } catch (Exception e222) {
                e222.printStackTrace();
            }
        } catch (NameNotFoundException e4) {
            e4.printStackTrace();
            startActivity(new Intent(getApplicationContext(), ForceUpdateActivity.class));
            finish();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131689477, menu);
        this.f2535r = menu.findItem(2131427853);
        this.f2536s = menu.findItem(2131427854);
        this.f2537t = menu.findItem(2131427855);
        this.f2538u = menu.findItem(2131427856);
        this.f2539v = menu.findItem(2131427857);
        this.f2540w = menu.findItem(2131427860);
        this.f2541x = menu.findItem(2131427858);
        this.f2542y = menu.findItem(2131427859);
        m3490e(this.f2534q.getSelectedTab().getPosition());
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        super.onDestroy();
        AppSettings.m4867a(getApplicationContext()).m4874a(true);
        try {
            unregisterReceiver(this.f2531K);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            startService(new Intent(getApplicationContext(), NotificationService.class));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            unregisterReceiver(this.f2530J);
        } catch (Exception e22) {
            e22.printStackTrace();
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.containsKey("com.mmdt.sipclient.view.MainActivity.JOB_VALUE")) {
                this.f2533p.post(new MainActivity(this, extras.getInt("com.mmdt.sipclient.view.MainActivity.JOB_VALUE")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        try {
            Thread thread;
            switch (menuItem.getItemId()) {
                case 2131427853:
                    runOnUiThread(new MainActivity(this));
                    return true;
                case 2131427854:
                    thread = new Thread(new MainActivity(this));
                    thread.setPriority(1);
                    thread.start();
                    return true;
                case 2131427855:
                    thread = new Thread(new MainActivity(this));
                    thread.setPriority(1);
                    thread.start();
                    return true;
                case 2131427856:
                    thread = new Thread(new MainActivity(this));
                    thread.setPriority(1);
                    thread.start();
                    return true;
                case 2131427857:
                    thread = new Thread(new MainActivity(this));
                    thread.setPriority(1);
                    thread.start();
                    return true;
                case 2131427858:
                    thread = new Thread(new MainActivity(this));
                    thread.setPriority(1);
                    thread.start();
                    return true;
                case 2131427859:
                    startActivity(new Intent(this, ExplorePublicChatsListActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case 2131427860:
                    Intent intent = new Intent(this, MoreActivity.class);
                    overridePendingTransition(0, 0);
                    startActivityForResult(intent, 15648);
                    overridePendingTransition(0, 0);
                    return true;
                default:
                    return super.onOptionsItemSelected(menuItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        e.printStackTrace();
        return false;
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        try {
            MessagesNotificationManager.m2382a(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        try {
            f2519L = tab.getPosition();
            this.f2533p.setCurrentItem(tab.getPosition());
            if (this.f2534q.getSelectedTab().getPosition() != 1 && this.f2521A.m2507E()) {
                this.f2521A.m2515a(false);
            }
            m3490e(tab.getPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }
}
