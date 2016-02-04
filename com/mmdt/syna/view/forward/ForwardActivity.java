package com.mmdt.syna.view.forward;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import com.mmdt.syna.view.conversation.conversationpage.activities.ForwardGroupConversationActivity;
import com.mmdt.syna.view.conversation.conversationpage.activities.ForwardSingleConversationActivity;
import com.mmdt.syna.view.forward.ForwardMessageSunContactsListFragment.ForwardMessageSunContactsListFragment;
import com.mmdt.syna.view.forward.GroupsListFragment.GroupsListFragment;
import com.mmdt.syna.view.forward.RecentListFragment.RecentListFragment;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import org.linphone.core.VideoSize;

public class ForwardActivity extends FragmentActivity implements TabListener, ForwardMessageSunContactsListFragment, GroupsListFragment, RecentListFragment {
    private boolean f2408n;
    private boolean f2409o;
    private int f2410p;
    private String f2411q;
    private String f2412r;
    private EditText f2413s;
    private ForwardMessageSunContactsListFragment f2414t;
    private GroupsListFragment f2415u;
    private RecentListFragment f2416v;
    private ViewPager f2417w;
    private ActionBar f2418x;
    private C0107a f2419y;

    /* renamed from: com.mmdt.syna.view.forward.ForwardActivity.a */
    public class C0107a extends FragmentPagerAdapter {
        final /* synthetic */ ForwardActivity f2407a;

        public C0107a(ForwardActivity forwardActivity, FragmentManager fragmentManager) {
            this.f2407a = forwardActivity;
            super(fragmentManager);
        }

        public Fragment m3369a(int i) {
            switch (i) {
                case VideoSize.QCIF /*0*/:
                    this.f2407a.f2416v = new RecentListFragment();
                    return this.f2407a.f2416v;
                case VideoSize.CIF /*1*/:
                    this.f2407a.f2415u = new GroupsListFragment();
                    return this.f2407a.f2415u;
                case VideoSize.HVGA /*2*/:
                    this.f2407a.f2414t = new ForwardMessageSunContactsListFragment();
                    return this.f2407a.f2414t;
                default:
                    return null;
            }
        }

        public int m3370b() {
            return 3;
        }
    }

    public ForwardActivity() {
        this.f2408n = false;
        this.f2409o = true;
        this.f2410p = 1;
        this.f2411q = "";
        this.f2412r = "";
    }

    private void m3382g() {
        this.f2413s = (EditText) findViewById(2131427496);
        this.f2414t = new ForwardMessageSunContactsListFragment();
    }

    public void m3383a(Uri uri) {
        String d = SynaContacts.m5001a((Context) this, Long.parseLong(uri.getLastPathSegment())).m4976d();
        Intent intent = new Intent(this, ForwardSingleConversationActivity.class);
        intent.putExtra("party", d);
        ContactShow a = SynaContacts.m5002a((Context) this, d, false);
        if (a != null) {
            intent.putExtra("party_name", a.m5062c());
        } else {
            intent.putExtra("party_name", d);
        }
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_TYPE", this.f2410p);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE", this.f2411q);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_BIND_DATA", this.f2412r);
        startActivity(intent);
        finish();
    }

    public void m3384a(String str, String str2, boolean z) {
        Intent intent = new Intent(this, ForwardGroupConversationActivity.class);
        intent.putExtra("party", str);
        intent.putExtra("party_name", str2);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_TYPE", this.f2410p);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE", this.f2411q);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_BIND_DATA", this.f2412r);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void m3385b(String str, String str2, boolean z) {
        Intent intent = new Intent();
        intent = z ? new Intent(this, ForwardGroupConversationActivity.class) : new Intent(this, ForwardSingleConversationActivity.class);
        intent.putExtra("party", str);
        intent.putExtra("party_name", str2);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_TYPE", this.f2410p);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE", this.f2411q);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_BIND_DATA", this.f2412r);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void m3386f() {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903074);
        this.f2417w = (ViewPager) findViewById(2131427494);
        this.f2418x = getActionBar();
        this.f2419y = new C0107a(this, m121e());
        this.f2417w.setAdapter(this.f2419y);
        this.f2418x.setHomeButtonEnabled(false);
        this.f2418x.setDisplayShowTitleEnabled(false);
        this.f2418x.setDisplayShowHomeEnabled(false);
        this.f2418x.setNavigationMode(2);
        this.f2417w.setOnPageChangeListener(new ForwardActivity(this));
        for (CharSequence text : new String[]{getString(2131493754), getString(2131493753), getString(2131493752)}) {
            this.f2418x.addTab(this.f2418x.newTab().setText(text).setTabListener(this));
        }
        this.f2410p = getIntent().getIntExtra("KEY_TO_FORWARD_MESSAGE_TYPE", 1);
        this.f2411q = getIntent().getStringExtra("KEY_TO_FORWARD_MESSAGE");
        this.f2412r = getIntent().getStringExtra("KEY_TO_FORWARD_MESSAGE_BIND_DATA");
        m3382g();
    }

    public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
        this.f2417w.setCurrentItem(tab.getPosition());
    }

    public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
    }
}
