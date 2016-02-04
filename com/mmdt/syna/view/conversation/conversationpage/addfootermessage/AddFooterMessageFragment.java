package com.mmdt.syna.view.conversation.conversationpage.addfootermessage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.addfootermessage.f */
public class AddFooterMessageFragment extends Fragment {
    private View f2111a;
    private ImageView f2112b;

    private void m3100a() {
    }

    private void m3101a(double d) {
        Display defaultDisplay = ((WindowManager) m91h().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = (point.x * 100) / 100;
        this.f2112b.setLayoutParams(new LayoutParams(i, (int) (((double) i) * d)));
    }

    private void m3102b() {
        this.f2112b = (ImageView) this.f2111a.findViewById(2131427462);
    }

    public View m3103a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2111a = layoutInflater.inflate(2130903084, viewGroup, false);
        m3102b();
        m3100a();
        return this.f2111a;
    }

    public void m3104a(Activity activity) {
        super.m57a(activity);
    }

    public void m3105a(Bitmap bitmap) {
        if (this.f2112b == null) {
            this.f2112b = (ImageView) this.f2111a.findViewById(2131427462);
        }
        this.f2112b.setImageBitmap(bitmap);
        double height = ((double) bitmap.getHeight()) / ((double) bitmap.getWidth());
        if (m91h() != null) {
            m3101a(height);
        }
    }

    public void m3106d(Bundle bundle) {
        super.m80d(bundle);
    }
}
