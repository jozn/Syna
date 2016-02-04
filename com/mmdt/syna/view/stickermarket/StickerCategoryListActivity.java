package com.mmdt.syna.view.stickermarket;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import com.mmdt.syna.view.stickermarket.StickerCategoryListFragment.StickerCategoryListFragment;

public class StickerCategoryListActivity extends FragmentActivity implements StickerCategoryListFragment {
    private ActionBar f2993n;
    private StickerCategoryListFragment f2994o;

    public void m3846b(String str) {
        Intent intent = new Intent(getApplicationContext(), StickerDetailsActivity.class);
        intent.putExtra("StickerDetailsActivity.KEY_PACKAGE_ID", str);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void onBackPressed() {
        overridePendingTransition(0, 0);
        super.onBackPressed();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903197);
        this.f2993n = getActionBar();
        this.f2993n.setDisplayHomeAsUpEnabled(true);
        this.f2993n.setIcon(2130837959);
        this.f2993n.setTitle(2131493589);
        this.f2994o = new StickerCategoryListFragment(getIntent().getStringExtra("StickerCategoryListActivity.KEY_CATEGORY_ID"));
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(2131427461, this.f2994o);
        beginTransaction.setTransition(4099);
        beginTransaction.commit();
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
