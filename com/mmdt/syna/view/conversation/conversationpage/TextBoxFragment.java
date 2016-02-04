package com.mmdt.syna.view.conversation.conversationpage;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import com.viewpagerindicator.R.R;
import mobi.mmdt.ott.asmackengine.chat.Chat.Chat;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.y */
public class TextBoxFragment extends Fragment {
    private boolean f2279Y;
    private boolean f2280Z;
    TextWatcher f2281a;
    private TextBoxFragment f2282b;
    private View f2283c;
    private EditText f2284d;
    private ImageButton f2285e;
    private ImageButton f2286f;
    private ImageButton f2287g;
    private boolean f2288h;
    private boolean f2289i;

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.y.a */
    public interface TextBoxFragment {
        void m2974a(Chat chat);

        void m2975c();

        void m2976c(String str);

        void c_();

        void m2977d();

        void d_();
    }

    public TextBoxFragment() {
        this.f2288h = false;
        this.f2289i = false;
        this.f2279Y = true;
        this.f2280Z = true;
        this.f2281a = new TextBoxFragment(this);
    }

    public TextBoxFragment(boolean z, boolean z2) {
        this.f2288h = false;
        this.f2289i = false;
        this.f2279Y = true;
        this.f2280Z = true;
        this.f2281a = new TextBoxFragment(this);
        this.f2279Y = z;
        this.f2280Z = z2;
    }

    private void m3243J() {
        this.f2284d = (EditText) this.f2283c.findViewById(2131427480);
        this.f2287g = (ImageButton) this.f2283c.findViewById(R.imageButton1);
        this.f2285e = (ImageButton) this.f2283c.findViewById(2131427619);
        m3267a(this.f2279Y);
        m3270f(this.f2280Z);
        this.f2286f = (ImageButton) this.f2283c.findViewById(2131427618);
        this.f2284d.addTextChangedListener(this.f2281a);
        new InputFilter[1][0] = new LengthFilter(1024);
        this.f2287g.setOnClickListener(new ah(this));
        this.f2285e.setOnClickListener(new aj(this));
        this.f2286f.setOnClickListener(new ak(this));
    }

    public void m3256D() {
        m91h().runOnUiThread(new ao(this));
    }

    public void m3257E() {
        m91h().runOnUiThread(new ap(this));
    }

    public void m3258F() {
        m91h().runOnUiThread(new ad(this));
    }

    public void m3259G() {
        m91h().runOnUiThread(new ae(this));
    }

    public void m3260H() {
        m91h().runOnUiThread(new af(this));
    }

    public void m3261I() {
        m91h().runOnUiThread(new ag(this));
    }

    public View m3262a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2283c = layoutInflater.inflate(2130903114, viewGroup, false);
        m3243J();
        return this.f2283c;
    }

    public String m3263a() {
        return this.f2284d.getText().toString();
    }

    public void m3264a(Activity activity) {
        super.m57a(activity);
        try {
            this.f2282b = (TextBoxFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ITextBoxInteractionListener");
        }
    }

    public void m3265a(Bundle bundle) {
        super.m61a(bundle);
    }

    public void m3266a(String str) {
        m91h().runOnUiThread(new ac(this, str));
    }

    public void m3267a(boolean z) {
        m91h().runOnUiThread(new al(this, z));
    }

    public void m3268b() {
        m91h().runOnUiThread(new an(this));
    }

    public void m3269d(Bundle bundle) {
        super.m80d(bundle);
    }

    public void m3270f(boolean z) {
        m91h().runOnUiThread(new am(this, z));
    }
}
