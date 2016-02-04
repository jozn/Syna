package com.mmdt.syna.view.conversation.conversationpage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.x */
public class ExtraPaneFragment extends Fragment {
    private ExtraPaneFragment f2277a;
    private View f2278b;

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.x.a */
    public interface ExtraPaneFragment {
    }

    private void m3236a() {
    }

    public View m3237a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2278b = layoutInflater.inflate(2130903142, viewGroup, false);
        m3236a();
        return this.f2278b;
    }

    public void m3238a(Activity activity) {
        super.m57a(activity);
        try {
            this.f2277a = (ExtraPaneFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnExtraPaneInteractionListener");
        }
    }

    public void m3239a(Bundle bundle) {
        super.m61a(bundle);
    }

    public void m3240d(Bundle bundle) {
        super.m80d(bundle);
    }

    public void m3241e(Bundle bundle) {
        super.m85e(bundle);
    }

    public void m3242q() {
        super.m103q();
    }
}
