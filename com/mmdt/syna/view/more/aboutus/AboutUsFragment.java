package com.mmdt.syna.view.more.aboutus;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager.NameNotFoundException;
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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* renamed from: com.mmdt.syna.view.more.aboutus.a */
public class AboutUsFragment extends Fragment {
    private View f2570a;

    private void m3524a() {
        Display defaultDisplay = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = (point.x * 60) / 100;
        ImageView imageView = (ImageView) this.f2570a.findViewById(2131427462);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.addRule(13);
        imageView.setLayoutParams(layoutParams);
    }

    private void m3525b() {
        String str;
        TextView textView = (TextView) this.f2570a.findViewById(2131427464);
        textView.setClickable(true);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(Html.fromHtml("<font color=\"#FF7F24\"><a href='http://www.syna-app.ir'>www.syna-app.ir</a></font>"));
        String str2 = "";
        try {
            str = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            str = str2;
        }
        ((TextView) this.f2570a.findViewById(2131427463)).setText("Version: " + str);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2570a = layoutInflater.inflate(2130903066, viewGroup, false);
        m3525b();
        m3524a();
        return this.f2570a;
    }
}
