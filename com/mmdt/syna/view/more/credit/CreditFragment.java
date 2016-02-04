package com.mmdt.syna.view.more.credit;

import com.mmdt.syna.view.components.p025a.CustomDialog;
import com.mmdt.syna.view.components.p029e.WaitProgressDialog;
import mobi.mmdt.ott.p043b.p048c.p049a.CardInfoResult;
import org.p074b.JSONException;

/* renamed from: com.mmdt.syna.view.more.credit.p */
class CreditFragment implements Runnable {
    final /* synthetic */ CreditFragment f2607a;
    private final /* synthetic */ CardInfoResult f2608b;

    CreditFragment(CreditFragment creditFragment, CardInfoResult cardInfoResult) {
        this.f2607a = creditFragment;
        this.f2608b = cardInfoResult;
    }

    public void run() {
        if (!this.f2607a.f2601a) {
            try {
                CustomDialog.m2737a(this.f2607a.f2603c.f2598a.getActivity(), this.f2608b.m4346c()).show();
            } catch (JSONException e) {
                CustomDialog.m2737a(this.f2607a.f2603c.f2598a.getActivity(), this.f2607a.f2603c.f2598a.getString(2131493494)).show();
                e.printStackTrace();
            }
        }
        WaitProgressDialog.m2759a(this.f2607a.f2603c.f2598a.getActivity()).m2765b();
    }
}
