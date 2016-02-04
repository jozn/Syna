package com.mmdt.syna.view.contactprofile;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mmdt.syna.view.tools.CallAndMessageHandler;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p060a.AndroidContact;
import mobi.mmdt.ott.core.model.database.p061b.SynaContact;
import mobi.mmdt.p041a.HasVersion;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;

/* renamed from: com.mmdt.syna.view.contactprofile.e */
public class ContactProfileFragment extends Fragment {
    boolean f1978a;
    private View f1979b;
    private boolean f1980c;
    private ImageLoader f1981d;
    private SynaContact f1982e;
    private AndroidContact f1983f;
    private ImageView f1984g;
    private boolean f1985h;
    private ContactProfileFragment f1986i;
    private FrameLayout f1987j;

    /* renamed from: com.mmdt.syna.view.contactprofile.e.b */
    public interface ContactProfileFragment {
        void m2922b(String str);

        void m2923c(String str);

        boolean m2924f();
    }

    /* renamed from: com.mmdt.syna.view.contactprofile.e.a */
    public class ContactProfileFragment extends ContentObserver {
        final /* synthetic */ ContactProfileFragment f1977a;

        public ContactProfileFragment(ContactProfileFragment contactProfileFragment) {
            this.f1977a = contactProfileFragment;
            super(null);
        }

        public void onChange(boolean z) {
            if (this.f1977a.f1986i != null && this.f1977a.f1986i.m2924f()) {
                new Thread(new ContactProfileFragment(this)).start();
            }
            super.onChange(z);
        }
    }

    public ContactProfileFragment() {
        this.f1980c = false;
        this.f1985h = false;
        this.f1978a = false;
    }

    public ContactProfileFragment(boolean z, AndroidContact androidContact, SynaContact synaContact) {
        this.f1980c = false;
        this.f1985h = false;
        this.f1978a = false;
        this.f1980c = z;
        this.f1983f = androidContact;
        this.f1982e = synaContact;
    }

    @TargetApi(14)
    private Bitmap m2938a(String str, int i) {
        AssetFileDescriptor openAssetFileDescriptor;
        FileNotFoundException e;
        Throwable th;
        Bitmap bitmap = null;
        try {
            openAssetFileDescriptor = getActivity().getContentResolver().openAssetFileDescriptor(HasVersion.m4098a() ? Uri.parse(str) : Uri.parse(str), AckRequest.ELEMENT);
            try {
                FileDescriptor fileDescriptor = openAssetFileDescriptor.getFileDescriptor();
                if (fileDescriptor != null) {
                    bitmap = ImageLoader.m2471a(fileDescriptor, i, i);
                    if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } else if (openAssetFileDescriptor != null) {
                    try {
                        openAssetFileDescriptor.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e3) {
                e = e3;
                try {
                    Log.d("ContactProfileFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
                    if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            openAssetFileDescriptor = null;
            Log.d("ContactProfileFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
            if (openAssetFileDescriptor != null) {
                openAssetFileDescriptor.close();
            }
            return bitmap;
        } catch (Throwable th3) {
            openAssetFileDescriptor = null;
            th = th3;
            if (openAssetFileDescriptor != null) {
                openAssetFileDescriptor.close();
            }
            throw th;
        }
        return bitmap;
    }

    private void m2940a() {
        if (this.f1980c) {
            getActivity().getContentResolver().registerContentObserver(Uri.parse(DatabaseContentProvider.m4927a(getActivity()) + "/" + this.f1982e.m4977e()), false, new ContactProfileFragment(this));
            if (this.f1982e.m4974b() == 1) {
                this.f1986i.m2923c(getActivity().getString(2131493634));
            } else if (this.f1982e.m4974b() == 2) {
                this.f1986i.m2923c(getActivity().getString(2131493635));
            } else {
                this.f1986i.m2923c(null);
            }
        } else {
            this.f1986i.m2923c(null);
        }
        this.f1981d = new ContactProfileFragment(this, getActivity(), m2946d());
        this.f1981d.m2482a(2130838061);
        this.f1981d.m2483a(getFragmentManager(), 0.1f);
        this.f1981d.m2485a(false);
        this.f1984g = (ImageView) this.f1979b.findViewById(2131427533);
        TextView textView = (TextView) this.f1979b.findViewById(2131427576);
        if (this.f1980c) {
            if (this.f1982e.m4979g() != null) {
                this.f1986i.m2922b(this.f1982e.m4979g());
            } else if (this.f1982e.m4975c() != null) {
                this.f1986i.m2922b(this.f1982e.m4975c());
            } else {
                this.f1986i.m2922b(this.f1982e.m4976d());
            }
            if (this.f1982e.m4975c() != null) {
                textView.setText(this.f1982e.m4975c());
            } else {
                textView.setText(getActivity().getString(2131493602));
            }
            if (this.f1982e.m4978f() != null) {
                this.f1981d.m2484a(this.f1982e.m4978f(), this.f1984g);
            }
        } else {
            if (this.f1983f == null || this.f1983f.m4939b() == null) {
                this.f1986i.m2922b(this.f1983f.m4941d()[0]);
            } else {
                this.f1986i.m2922b(this.f1983f.m4939b());
            }
            if (this.f1983f.m4939b() != null) {
                textView.setText(this.f1983f.m4939b());
            } else {
                textView.setText(getActivity().getString(2131493602));
            }
            if (this.f1983f.m4940c() != null) {
                this.f1981d.m2484a(this.f1983f.m4940c(), this.f1984g);
            }
        }
        if (!this.f1980c || getResources().getConfiguration().orientation == 2) {
            this.f1979b.findViewById(2131427577).setVisibility(8);
        }
        if (!this.f1980c && getResources().getConfiguration().orientation == 2) {
            this.f1979b.findViewById(2131427580).setVisibility(8);
        }
        m2944c();
        m2943b();
    }

    private void m2943b() {
        Display defaultDisplay = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = point.x;
        ImageView imageView = (ImageView) this.f1979b.findViewById(2131427533);
        if (getResources().getConfiguration().orientation == 1) {
            LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
            ((MarginLayoutParams) layoutParams).setMargins(0, -(i / 5), 0, -(i / 5));
            imageView.setLayoutParams(layoutParams);
            imageView.setOnClickListener(new ContactProfileFragment(this, imageView, i));
        } else {
            imageView.setLayoutParams(new LinearLayout.LayoutParams(i / 4, i / 4));
            imageView.setOnClickListener(new ContactProfileFragment(this));
        }
        ((RelativeLayout) this.f1979b.findViewById(2131427574)).setOnTouchListener(new ContactProfileFragment(this, i, imageView));
    }

    private void m2944c() {
        LinearLayout linearLayout = (LinearLayout) this.f1979b.findViewById(2131427579);
        if (this.f1980c) {
            BaseAdapterForNumbers baseAdapterForNumbers = new BaseAdapterForNumbers(getActivity(), new String[]{this.f1982e.m4976d()}, linearLayout, this.f1980c);
            return;
        }
        baseAdapterForNumbers = new BaseAdapterForNumbers(getActivity(), this.f1983f.m4941d(), linearLayout, this.f1980c);
    }

    private int m2946d() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        return i > i2 ? i : i2;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f1986i = (ContactProfileFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnContactProfileInteractionListener");
        }
    }

    public void onChatClicked() {
        CallAndMessageHandler.m4020b(getActivity(), this.f1982e.m4976d());
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService("layout_inflater");
        this.f1987j.removeAllViews();
        this.f1979b = layoutInflater.inflate(2130903102, null);
        this.f1985h = false;
        m2940a();
        this.f1987j.addView(this.f1979b);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f1987j = new FrameLayout(getActivity());
        this.f1979b = layoutInflater.inflate(2130903102, viewGroup, false);
        m2940a();
        this.f1987j.addView(this.f1979b);
        return this.f1987j;
    }

    public void onFreeCallClicked() {
        CallAndMessageHandler.m4019a(getActivity(), this.f1982e.m4976d(), true);
    }
}
