package com.felipecsl.asymmetricgridview.library.widget;

import android.content.Context;
import android.view.View;

/* renamed from: com.felipecsl.asymmetricgridview.library.widget.g */
public class LinearLayoutPoolObjectFactory implements PoolObjectFactory<IcsLinearLayout> {
    private final Context f1575a;

    public LinearLayoutPoolObjectFactory(Context context) {
        this.f1575a = context;
    }

    public IcsLinearLayout m2371a() {
        return new IcsLinearLayout(this.f1575a, null);
    }

    public /* synthetic */ View m2372b() {
        return m2371a();
    }
}
