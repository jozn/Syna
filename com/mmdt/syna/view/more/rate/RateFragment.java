package com.mmdt.syna.view.more.rate;

import android.text.Editable;
import android.text.TextWatcher;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.CountryTools;

/* renamed from: com.mmdt.syna.view.more.rate.g */
class RateFragment implements TextWatcher {
    final /* synthetic */ RateFragment f2653a;

    RateFragment(RateFragment rateFragment) {
        this.f2653a = rateFragment;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.f2653a.f2647l = charSequence.toString();
        if (this.f2653a.f2641f) {
            this.f2653a.f2641f = false;
            return;
        }
        this.f2653a.f2642g = true;
        CharSequence charSequence2 = "";
        String str = "";
        this.f2653a.f2644i = charSequence.toString();
        if (this.f2653a.f2644i.length() > 1) {
            if (this.f2653a.f2644i.startsWith("+")) {
                this.f2653a.f2644i = "00" + this.f2653a.f2644i.substring(1);
            } else if (this.f2653a.f2644i.charAt(0) == '0' && this.f2653a.f2644i.length() > 1 && this.f2653a.f2644i.charAt(1) != '0') {
                this.f2653a.f2644i = new StringBuilder(String.valueOf(AppSettings.m4867a(this.f2653a.getActivity()).m4905o())).append(this.f2653a.f2644i.substring(1)).toString();
            } else if (!this.f2653a.f2644i.startsWith("00")) {
                this.f2653a.f2644i = new StringBuilder(String.valueOf(AppSettings.m4867a(this.f2653a.getActivity()).m4905o())).append(this.f2653a.f2644i).toString();
            }
            if (this.f2653a.f2644i.length() > 1) {
                str = CountryTools.m4416a().m4421a(this.f2653a.f2644i);
                if (str != null) {
                    charSequence2 = CountryTools.m4416a().m4426c(str);
                }
            }
            this.f2653a.f2640e.setText(charSequence2);
            return;
        }
        this.f2653a.f2640e.setText("");
    }
}
