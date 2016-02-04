package com.mmdt.syna.view.main;

import android.annotation.SuppressLint;
import android.app.ActionBar.Tab;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import org.catrobat.paintroid.R.R;

/* renamed from: com.mmdt.syna.view.main.n */
public class TabUtils {
    @SuppressLint({"InflateParams"})
    public static View m3512a(Context context, int i, int i2) {
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(2130903209, null);
        relativeLayout.setLayoutParams(new LayoutParams(-1, -1));
        ((ImageView) relativeLayout.findViewById(R.tab_icon)).setImageResource(i);
        TabUtils.m3514a((TextView) relativeLayout.findViewById(2131427828), i2);
        return relativeLayout;
    }

    public static void m3513a(Tab tab, int i) {
        if (tab != null) {
            TabUtils.m3514a((TextView) tab.getCustomView().findViewById(2131427828), i);
        }
    }

    private static void m3514a(TextView textView, int i) {
        if (i > 0) {
            textView.setVisibility(0);
            textView.setText(Integer.toString(i));
            return;
        }
        textView.setVisibility(8);
    }
}
