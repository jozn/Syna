package com.mmdt.syna.view.p033d;

import android.content.Context;
import android.graphics.Bitmap;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;

/* renamed from: com.mmdt.syna.view.d.b */
class LogsListFragment extends ImageLoader {
    final /* synthetic */ LogsListFragment f2368a;

    LogsListFragment(LogsListFragment logsListFragment, Context context, int i) {
        this.f2368a = logsListFragment;
        super(context, i);
    }

    protected Bitmap m3348a(Object obj) {
        return this.f2368a.m3328a((String) obj, m2480a());
    }
}
