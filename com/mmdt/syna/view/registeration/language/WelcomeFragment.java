package com.mmdt.syna.view.registeration.language;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.mmdt.syna.view.forceaction.ForceSettingActivity;
import com.mmdt.syna.view.registeration.termandconditions.TermsAndConditionsActivity;
import mobi.mmdt.p041a.DateAndTime;

/* renamed from: com.mmdt.syna.view.registeration.language.e */
class WelcomeFragment implements OnClickListener {
    final /* synthetic */ WelcomeFragment f2846a;

    WelcomeFragment(WelcomeFragment welcomeFragment) {
        this.f2846a = welcomeFragment;
    }

    public void onClick(View view) {
        if (DateAndTime.m4086a(this.f2846a.getActivity().getApplicationContext()) && DateAndTime.m4087b(this.f2846a.getActivity().getApplicationContext())) {
            Intent intent = new Intent(this.f2846a.getActivity(), TermsAndConditionsActivity.class);
            this.f2846a.getActivity().overridePendingTransition(0, 0);
            this.f2846a.startActivity(intent);
            this.f2846a.getActivity().overridePendingTransition(0, 0);
            this.f2846a.getActivity().finish();
            return;
        }
        intent = new Intent(this.f2846a.getActivity(), ForceSettingActivity.class);
        this.f2846a.getActivity().overridePendingTransition(0, 0);
        this.f2846a.startActivity(intent);
        this.f2846a.getActivity().overridePendingTransition(0, 0);
        this.f2846a.getActivity().finish();
    }
}
