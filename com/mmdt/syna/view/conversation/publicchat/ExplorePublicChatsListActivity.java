package com.mmdt.syna.view.conversation.publicchat;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.mmdt.syna.view.conversation.publicchat.ExplorePublicChatsListFragment.ExplorePublicChatsListFragment;

public class ExplorePublicChatsListActivity extends FragmentActivity implements ExplorePublicChatsListFragment {
    private ActionBar f2291n;
    private ExplorePublicChatsListFragment f2292o;

    private Activity m3275f() {
        return this;
    }

    public void m3276b(String str) {
        new Thread(new ExplorePublicChatsListActivity(this, str)).start();
    }

    public void m3277c(String str) {
        new Thread(new ExplorePublicChatsListActivity(this, str)).start();
    }

    public void onBackPressed() {
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903140);
        this.f2291n = getActionBar();
        this.f2291n.setDisplayHomeAsUpEnabled(true);
        this.f2291n.setIcon(2130838022);
        this.f2291n.setTitle(2131493590);
        new Thread(new ExplorePublicChatsListActivity(this)).start();
        this.f2292o = new ExplorePublicChatsListFragment();
        FragmentTransaction a = m121e().m249a();
        a.m202b(2131427461, this.f2292o);
        a.m196a(4099);
        a.m195a();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                overridePendingTransition(0, 0);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
