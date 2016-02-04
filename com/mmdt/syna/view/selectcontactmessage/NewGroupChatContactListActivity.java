package com.mmdt.syna.view.selectcontactmessage;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.devspark.robototextview.widget.RobotoButton;
import com.mmdt.syna.view.components.p025a.CustomDialog;
import com.mmdt.syna.view.conversation.conversationpage.activities.GroupConversationActivity;
import com.mmdt.syna.view.conversation.conversationpage.activities.MultipleRecipientMessageActivity;
import com.mmdt.syna.view.conversation.conversationpage.activities.SingleConversationActivity;
import com.mmdt.syna.view.selectcontactmessage.NewGroupChatContactsListFragment.NewGroupChatContactsListFragment;
import java.util.ArrayList;
import mobi.mmdt.ott.core.logic.p055d.XmppManager;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.p051a.Statics;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.linphone.mediastream.Log;

public class NewGroupChatContactListActivity extends FragmentActivity implements NewGroupChatContactsListFragment {
    private int f2895n;
    private boolean f2896o;
    private int f2897p;
    private String f2898q;
    private String f2899r;
    private EditText f2900s;
    private NewGroupChatContactsListFragment f2901t;
    private ActionBar f2902u;
    private SelectedContactsFragment f2903v;
    private MenuItem f2904w;
    private MenuItem f2905x;
    private String f2906y;
    private String f2907z;

    public NewGroupChatContactListActivity() {
        this.f2895n = 0;
        this.f2896o = false;
        this.f2897p = 1;
        this.f2898q = "";
        this.f2899r = "";
        this.f2906y = null;
        this.f2907z = null;
    }

    private void m3756b(String[] strArr) {
        Dialog f = CustomDialog.m2743f(this, getString(2131493662));
        f.setCancelable(false);
        f.findViewById(2131427735).setOnClickListener(new NewGroupChatContactListActivity(this, f));
        RobotoButton robotoButton = (RobotoButton) f.findViewById(2131427736);
        EditText editText = (EditText) f.findViewById(2131427737);
        ((TextView) f.findViewById(2131427467)).setText(2131493662);
        editText.setHint(2131493663);
        editText.setText("");
        robotoButton.setOnClickListener(new NewGroupChatContactListActivity(this, f, editText, strArr));
        editText.addTextChangedListener(new NewGroupChatContactListActivity(this, robotoButton));
        robotoButton.setEnabled(false);
        f.show();
    }

    private void m3758c(String str) {
        String[] f;
        try {
            f = XmppManager.m4723a(getApplicationContext()).m4747f(str);
        } catch (NoResponseException e) {
            e.printStackTrace();
            f = null;
        } catch (XMPPErrorException e2) {
            e2.printStackTrace();
            f = null;
        } catch (NotConnectedException e3) {
            e3.printStackTrace();
            f = null;
        }
        if (f == null) {
            f = new String[0];
        }
        this.f2901t = new NewGroupChatContactsListFragment(this.f2895n, f);
        FragmentTransaction a = m121e().m249a();
        a.m202b(2131427497, this.f2901t);
        a.m196a(4099);
        a.m195a();
    }

    private void m3761h() {
        this.f2903v = new SelectedContactsFragment();
        FragmentTransaction a = m121e().m249a();
        a.m202b(2131427495, this.f2903v);
        a.m196a(4099);
        a.m195a();
    }

    private void m3762i() {
        if (this.f2895n == 0) {
            this.f2901t = new NewGroupChatContactsListFragment(this.f2895n);
        } else if (this.f2895n == 1) {
            String[] f;
            try {
                f = XmppManager.m4723a(getApplicationContext()).m4747f(this.f2906y);
            } catch (NoResponseException e) {
                e.printStackTrace();
                f = null;
            } catch (XMPPErrorException e2) {
                e2.printStackTrace();
                f = null;
            } catch (NotConnectedException e3) {
                e3.printStackTrace();
                f = null;
            }
            if (f == null) {
                f = new String[0];
            }
            this.f2901t = new NewGroupChatContactsListFragment(this.f2895n, f);
        }
        FragmentTransaction a = m121e().m249a();
        a.m202b(2131427497, this.f2901t);
        a.m196a(4099);
        a.m195a();
    }

    private void m3763j() {
        this.f2900s = (EditText) findViewById(2131427496);
        this.f2901t = new NewGroupChatContactsListFragment();
        this.f2900s.addTextChangedListener(new NewGroupChatContactListActivity(this));
    }

    private Activity m3764k() {
        return this;
    }

    public void m3765a(Uri uri) {
        String d = SynaContacts.m5001a((Context) this, Long.parseLong(uri.getLastPathSegment())).m4976d();
        Intent intent = new Intent();
        if (this.f2895n == 0 || this.f2895n == 1 || this.f2895n == 2) {
            intent = new Intent(this, GroupConversationActivity.class);
        } else if (this.f2895n == 3) {
            intent = new Intent(this, SingleConversationActivity.class);
        }
        intent.putExtra("party", d);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_TYPE", this.f2897p);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE", this.f2898q);
        intent.putExtra("KEY_TO_FORWARD_MESSAGE_BIND_DATA", this.f2899r);
        startActivity(intent);
        finish();
    }

    public void m3766a(SelectedContactsDataHolder selectedContactsDataHolder) {
        this.f2903v.m3839a(selectedContactsDataHolder);
    }

    public void m3767a(String[] strArr) {
        Intent intent = new Intent(this, MultipleRecipientMessageActivity.class);
        intent.putExtra("multi_message_numbers", strArr);
        startActivity(intent);
    }

    public void m3768b(String str) {
        this.f2903v.m3840a(str);
    }

    public void m3769f() {
        runOnUiThread(new NewGroupChatContactListActivity(this));
    }

    public void m3770g() {
    }

    public void onBackPressed() {
        m3769f();
        finish();
        overridePendingTransition(0, 0);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2895n = getIntent().getIntExtra("NewGroupChatContactListActivity_start_mode", 0);
        setContentView(2130903076);
        this.f2902u = getActionBar();
        this.f2902u.setDisplayHomeAsUpEnabled(true);
        this.f2902u.setIcon(2130838024);
        m3763j();
        if (this.f2895n == 0) {
            this.f2902u.setTitle(2131493660);
            m3762i();
            m3761h();
        } else if (this.f2895n == 1) {
            this.f2902u.setTitle(2131493660);
            this.f2906y = getIntent().getStringExtra("NewGroupChatContactListActivity_GROUP_ID");
            this.f2907z = getIntent().getStringExtra("NewGroupChatContactListActivity_room_name");
            m3762i();
            m3761h();
        } else if (this.f2895n == 2) {
            this.f2902u.setTitle(2131493661);
            findViewById(2131427495).setVisibility(8);
            findViewById(2131427496).setVisibility(8);
            this.f2906y = getIntent().getStringExtra("NewGroupChatContactListActivity_GROUP_ID");
            m3758c(this.f2906y);
        } else if (this.f2895n == 3) {
            this.f2902u.setTitle(2131493660);
            m3762i();
            m3761h();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(2131689479, menu);
        this.f2904w = menu.findItem(2131427862);
        this.f2905x = menu.findItem(2131427863);
        if (this.f2895n == 1) {
            this.f2904w.setVisible(false);
            this.f2905x.setVisible(true);
        } else if (this.f2895n == 0) {
            this.f2904w.setVisible(true);
            this.f2905x.setVisible(false);
        } else if (this.f2895n == 2) {
            this.f2904w.setVisible(false);
            this.f2905x.setVisible(false);
        } else if (this.f2895n == 3) {
            this.f2904w.setVisible(true);
            this.f2905x.setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        ArrayList D;
        switch (menuItem.getItemId()) {
            case 16908332:
                m3769f();
                finish();
                overridePendingTransition(0, 0);
                return true;
            case 2131427862:
                m3769f();
                Log.m6028e("CreateGroup", "NewGroupChatContactListActivity");
                D = this.f2901t.m3793D();
                String[] strArr = (String[]) D.toArray(new String[D.size()]);
                if (D.size() > 0) {
                    if (this.f2895n != 0) {
                        if (this.f2895n == 3) {
                            m3767a(strArr);
                            break;
                        }
                    }
                    m3756b(strArr);
                    break;
                }
                m3769f();
                Statics.m4457a((Context) this, getString(2131493664), 0);
                break;
                break;
            case 2131427863:
                m3769f();
                D = this.f2901t.m3793D();
                if (D.size() > 0) {
                    if (!XmppManager.m4723a((Context) this).m4737a(true)) {
                        CustomDialog.m2737a(this, getString(2131493497)).show();
                        break;
                    }
                    new Thread(new NewGroupChatContactListActivity(this, D)).start();
                    break;
                }
                m3769f();
                Statics.m4457a((Context) this, getString(2131493664), 0);
                break;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean onSearchRequested() {
        return !this.f2896o && super.onSearchRequested();
    }
}
