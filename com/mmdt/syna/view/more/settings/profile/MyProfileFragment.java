package com.mmdt.syna.view.more.settings.profile;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.mmdt.syna.view.more.settings.profile.n */
class MyProfileFragment implements OnClickListener {
    final /* synthetic */ MyProfileFragment f2765a;
    private final /* synthetic */ Dialog f2766b;

    MyProfileFragment(MyProfileFragment myProfileFragment, Dialog dialog) {
        this.f2765a = myProfileFragment;
        this.f2766b = dialog;
    }

    public void onClick(View view) {
        this.f2766b.dismiss();
    }
}
