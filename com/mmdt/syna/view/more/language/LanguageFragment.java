package com.mmdt.syna.view.more.language;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import com.mmdt.syna.view.registeration.language.LangSpinnerAdapter;

/* renamed from: com.mmdt.syna.view.more.language.c */
public class LanguageFragment extends Fragment {
    private View f2625a;
    private String f2626b;
    private LanguageFragment f2627c;

    /* renamed from: com.mmdt.syna.view.more.language.c.a */
    public interface LanguageFragment {
        void m3547a(int i);
    }

    public LanguageFragment() {
        this.f2626b = "en-us";
    }

    public LanguageFragment(String str) {
        this.f2626b = "en-us";
        this.f2626b = str;
    }

    private void m3552a() {
        Display defaultDisplay = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = (point.x * 60) / 100;
        ImageView imageView = (ImageView) this.f2625a.findViewById(2131427462);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(13);
        imageView.setLayoutParams(layoutParams);
    }

    private void m3553b() {
        Spinner spinner = (Spinner) this.f2625a.findViewById(2131427734);
        spinner.setAdapter(new LangSpinnerAdapter(getActivity(), 2131427739, new String[]{getActivity().getString(2131493700), getActivity().getString(2131493701)}));
        if (this.f2626b.equals("en-us")) {
            spinner.setSelection(0);
        } else if (this.f2626b.equals("fa")) {
            spinner.setSelection(1);
        }
        spinner.setOnItemSelectedListener(new LanguageFragment(this));
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2627c = (LanguageFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnLanguageInteractionListener");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2625a = layoutInflater.inflate(2130903157, viewGroup, false);
        m3553b();
        m3552a();
        return this.f2625a;
    }
}
