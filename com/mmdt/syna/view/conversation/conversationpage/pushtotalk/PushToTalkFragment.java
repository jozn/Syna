package com.mmdt.syna.view.conversation.conversationpage.pushtotalk;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.pushtotalk.b */
public class PushToTalkFragment extends Fragment {
    private boolean f2249Y;
    private boolean f2250Z;
    private PushToTalkFragment f2251a;
    private int aa;
    private Thread ab;
    private View f2252b;
    private RelativeLayout f2253c;
    private ImageView f2254d;
    private TextView f2255e;
    private ProgressWheel f2256f;
    private RelativeLayout f2257g;
    private Rect f2258h;
    private boolean f2259i;

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.pushtotalk.b.a */
    public interface PushToTalkFragment {
        void m2972o();

        void m2973p();
    }

    public PushToTalkFragment() {
        this.f2258h = new Rect();
        this.f2259i = false;
        this.f2249Y = false;
        this.aa = 0;
    }

    private void m3209D() {
        this.f2257g = (RelativeLayout) this.f2252b.findViewById(2131427607);
        this.f2256f = (ProgressWheel) this.f2252b.findViewById(2131427787);
        this.f2254d = (ImageView) this.f2252b.findViewById(2131427788);
        this.f2253c = (RelativeLayout) this.f2252b.findViewById(2131427786);
        this.f2255e = (TextView) this.f2252b.findViewById(2131427789);
        this.f2254d.getHitRect(this.f2258h);
        this.f2253c.setOnTouchListener(new PushToTalkFragment(this));
    }

    private void m3210E() {
        if (m93i().getConfiguration().orientation == 1) {
            m91h().setRequestedOrientation(1);
        } else {
            m91h().setRequestedOrientation(0);
        }
    }

    private void m3211F() {
        m91h().setRequestedOrientation(-1);
    }

    private void m3212G() {
        this.f2251a.m2973p();
        m3215J();
        m3210E();
    }

    private void m3213H() {
        this.f2249Y = true;
        this.f2251a.m2972o();
        m3211F();
    }

    private void m3214I() {
        this.f2249Y = false;
        this.f2251a.m2972o();
        m3211F();
    }

    private void m3215J() {
        this.f2250Z = true;
        Runnable pushToTalkFragment = new PushToTalkFragment(this);
        this.aa = 0;
        this.f2256f.m3208a();
        if (this.ab == null) {
            this.ab = new Thread(pushToTalkFragment);
            this.ab.start();
        }
    }

    public View m3229a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2252b = layoutInflater.inflate(2130903177, viewGroup, false);
        m3209D();
        return this.f2252b;
    }

    public void m3230a() {
        this.f2250Z = false;
        this.ab = null;
        m91h().runOnUiThread(new PushToTalkFragment(this));
        this.aa = 0;
    }

    public void m3231a(Activity activity) {
        super.m57a(activity);
        try {
            this.f2251a = (PushToTalkFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement IPushToTalkInteractionListener");
        }
    }

    public void m3232a(Bundle bundle) {
        super.m61a(bundle);
    }

    public boolean m3233b() {
        return this.f2249Y;
    }

    public void m3234d(Bundle bundle) {
        super.m80d(bundle);
    }
}
