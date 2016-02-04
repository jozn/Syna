package com.mmdt.syna.view.registeration.registernumber;

import com.mmdt.syna.view.components.p025a.CustomDialog;

/* renamed from: com.mmdt.syna.view.registeration.registernumber.m */
class RegistrationFragment implements Runnable {
    final /* synthetic */ RegistrationFragment f2882a;

    RegistrationFragment(RegistrationFragment registrationFragment) {
        this.f2882a = registrationFragment;
    }

    public void run() {
        CustomDialog.m2737a(this.f2882a.f2879a.getActivity(), this.f2882a.f2879a.getString(2131493494)).show();
    }
}
Selected(AdapterView<?> adapterView, View view, int i, long j) {
        this.f2863a.f2861d = CountryTools.m4416a().m4428d((String) this.f2863a.f2860c.getItemAtPosition(i));
        this.f2863a.f2859b.setText(new StringBuilder(String.valueOf(this.f2863a.f2861d)).append("-").toString());
        this.f2863a.f2859b.setSelection(this.f2863a.f2861d.length() + 1);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
