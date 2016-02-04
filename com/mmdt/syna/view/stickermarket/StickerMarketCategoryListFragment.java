package com.mmdt.syna.view.stickermarket;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StrickerCategoryResult;

/* renamed from: com.mmdt.syna.view.stickermarket.p */
class StickerMarketCategoryListFragment implements OnItemClickListener {
    final /* synthetic */ StickerMarketCategoryListFragment f3078a;

    StickerMarketCategoryListFragment(StickerMarketCategoryListFragment stickerMarketCategoryListFragment) {
        this.f3078a = stickerMarketCategoryListFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f3078a.f3075f.m3862c(((StrickerCategoryResult) adapterView.getItemAtPosition(i)).m4302b());
    }
}
