package com.mmdt.syna.view.registeration.termandconditions;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* renamed from: com.mmdt.syna.view.registeration.termandconditions.a */
public class TermsAndConditionsFragment extends Fragment {
    private View f2888a;
    private Button f2889b;
    private CheckBox f2890c;
    private RelativeLayout f2891d;
    private TermsAndConditionsFragment f2892e;

    /* renamed from: com.mmdt.syna.view.registeration.termandconditions.a.a */
    public interface TermsAndConditionsFragment {
        void m3744a();
    }

    private void m3747a() {
        Display defaultDisplay = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        ImageView imageView = (ImageView) this.f2888a.findViewById(2131427533);
        int i = (point.x * 50) / 100;
        int i2 = getResources().getConfiguration().orientation == 1 ? (point.x * 50) / 100 : (point.y * 40) / 100;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i2, i2);
        layoutParams.addRule(14);
        imageView.setLayoutParams(layoutParams);
    }

    private void m3749b() {
        this.f2889b = (Button) this.f2888a.findViewById(2131427473);
        this.f2890c = (CheckBox) this.f2888a.findViewById(2131427546);
        this.f2891d = (RelativeLayout) this.f2888a.findViewById(2131427829);
        this.f2889b.setOnClickListener(new TermsAndConditionsFragment(this));
        this.f2891d.setVisibility(4);
        this.f2890c.setOnCheckedChangeListener(new TermsAndConditionsFragment(this));
        TextView textView = (TextView) this.f2888a.findViewById(2131427467);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(Html.fromHtml(getActivity().getString(2131493594)));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2892e = (TermsAndConditionsFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnTermsAndConditionsInteractionListener");
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m3747a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2888a = layoutInflater.inflate(2130903212, viewGroup, false);
        m3749b();
        m3747a();
        return this.f2888a;
    }
}
