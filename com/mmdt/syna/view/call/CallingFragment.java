package com.mmdt.syna.view.call;

import android.widget.TextView;
import mobi.mmdt.ott.p043b.p048c.WebServiceManager;

/* renamed from: com.mmdt.syna.view.call.g */
class CallingFragment implements Runnable {
    final /* synthetic */ CallingFragment f1790a;
    private final /* synthetic */ int f1791b;
    private final /* synthetic */ int f1792c;
    private final /* synthetic */ int f1793d;
    private final /* synthetic */ float f1794e;
    private final /* synthetic */ String f1795f;
    private final /* synthetic */ String f1796g;

    CallingFragment(CallingFragment callingFragment, int i, int i2, int i3, float f, String str, String str2) {
        this.f1790a = callingFragment;
        this.f1791b = i;
        this.f1792c = i2;
        this.f1793d = i3;
        this.f1794e = f;
        this.f1795f = str;
        this.f1796g = str2;
    }

    public void run() {
        try {
            this.f1790a.f1773d = (TextView) this.f1790a.f1770a.findViewById(2131427522);
            if (this.f1791b != -1) {
                Object obj;
                this.f1790a.f1773d.setVisibility(0);
                if (this.f1792c > 9) {
                    obj = this.f1792c;
                } else {
                    String str = "0" + this.f1792c;
                }
                this.f1790a.f1773d.setText(new StringBuilder(String.valueOf(obj)).append(":").append(this.f1793d > 9 ? this.f1793d : "0" + this.f1793d).toString());
            }
            if (this.f1794e >= 0.0f && this.f1794e < 1.0f) {
                this.f1790a.f1775f.setVisibility(0);
                this.f1790a.f1775f.setText(new StringBuilder(String.valueOf(this.f1790a.getActivity().getString(2131493650))).append(" ").append(this.f1790a.getActivity().getString(2131493651)).toString());
            } else if (this.f1794e >= 1.0f && this.f1794e < 2.0f) {
                this.f1790a.f1775f.setVisibility(0);
                this.f1790a.f1775f.setText(new StringBuilder(String.valueOf(this.f1790a.getActivity().getString(2131493650))).append(" ").append(this.f1790a.getActivity().getString(2131493652)).toString());
            } else if (this.f1794e >= 2.0f && this.f1794e < 3.0f) {
                this.f1790a.f1775f.setVisibility(0);
                this.f1790a.f1775f.setText(new StringBuilder(String.valueOf(this.f1790a.getActivity().getString(2131493650))).append(" ").append(this.f1790a.getActivity().getString(2131493653)).toString());
            } else if (this.f1794e >= 3.0f && this.f1794e < 4.0f) {
                this.f1790a.f1775f.setVisibility(0);
                this.f1790a.f1775f.setText(new StringBuilder(String.valueOf(this.f1790a.getActivity().getString(2131493650))).append(" ").append(this.f1790a.getActivity().getString(2131493654)).toString());
            } else if (this.f1794e >= 4.0f) {
                this.f1790a.f1775f.setVisibility(0);
                this.f1790a.f1775f.setText(new StringBuilder(String.valueOf(this.f1790a.getActivity().getString(2131493650))).append(" ").append(this.f1790a.getActivity().getString(2131493655)).toString());
            } else {
                this.f1790a.f1775f.setVisibility(8);
            }
            if (!this.f1790a.f1787r.m2647h()) {
                if (!this.f1790a.f1787r.m2646g()) {
                    if (this.f1795f == null || this.f1795f.equals("")) {
                        this.f1790a.f1774e.setText(this.f1790a.getActivity().getString(2131493685));
                    } else {
                        this.f1790a.f1774e.setText(new StringBuilder(String.valueOf(this.f1790a.getActivity().getString(2131493657))).append(" ").append(this.f1796g).append("\n").append(this.f1790a.getActivity().getString(2131493658)).append(" ").append(WebServiceManager.m4379a(this.f1795f)).append("  ").append(this.f1790a.getActivity().getString(2131493684)).toString());
                    }
                    this.f1790a.f1774e.setVisibility(0);
                } else if (this.f1790a.f1774e != null && this.f1790a.getActivity() != null) {
                    this.f1790a.f1774e.setText(this.f1790a.getActivity().getString(2131493581));
                    this.f1790a.f1774e.setVisibility(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
