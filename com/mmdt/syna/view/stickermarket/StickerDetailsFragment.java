package com.mmdt.syna.view.stickermarket;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mmdt.syna.view.components.p027c.MyToast;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.security.GeneralSecurityException;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.p043b.p044a.AmirWebserviceException;
import mobi.mmdt.ott.p043b.p044a.p045a.StickerWebservices;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerPackageDetailResult;
import org.p074b.JSONException;

/* renamed from: com.mmdt.syna.view.stickermarket.f */
public class StickerDetailsFragment extends Fragment {
    private View f3040a;
    private String f3041b;
    private TextView f3042c;
    private TextView f3043d;
    private TextView f3044e;
    private ImageView f3045f;
    private ImageView f3046g;
    private ProgressBar f3047h;
    private ProgressBar f3048i;
    private Button f3049j;
    private StickerDetailsFragment f3050k;

    /* renamed from: com.mmdt.syna.view.stickermarket.f.b */
    public interface StickerDetailsFragment {
        void m3847b(String str);

        void m3848f();
    }

    /* renamed from: com.mmdt.syna.view.stickermarket.f.a */
    private class StickerDetailsFragment extends AsyncTask<Object, Void, StickerPackageDetailResult> {
        final /* synthetic */ StickerDetailsFragment f3038a;
        private String f3039b;

        private StickerDetailsFragment(StickerDetailsFragment stickerDetailsFragment) {
            this.f3038a = stickerDetailsFragment;
        }

        protected StickerPackageDetailResult m3888a(Object... objArr) {
            Exception e;
            this.f3039b = (String) objArr[0];
            try {
                return StickerWebservices.m4305a(this.f3038a.getActivity(), AppSettings.m4867a(this.f3038a.getActivity()).m4915y(), AppSettings.m4867a(this.f3038a.getActivity()).m4897j(), AppSettings.m4867a(this.f3038a.getActivity()).m4901l(), AppSettings.m4867a(this.f3038a.getActivity()).m4889f()).m4311a(this.f3039b);
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return null;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                return null;
            } catch (AmirWebserviceException e4) {
                e = e4;
                e.printStackTrace();
                return null;
            } catch (NameNotFoundException e5) {
                e5.printStackTrace();
                return null;
            } catch (NumberFormatException e6) {
                e6.printStackTrace();
                return null;
            } catch (GeneralSecurityException e7) {
                e7.printStackTrace();
                return null;
            }
        }

        protected void m3889a(StickerPackageDetailResult stickerPackageDetailResult) {
            if (stickerPackageDetailResult != null) {
                this.f3038a.f3042c.setText(stickerPackageDetailResult.m4294e());
                this.f3038a.f3043d.setText(stickerPackageDetailResult.m4290a());
                this.f3038a.f3044e.setText(stickerPackageDetailResult.m4291b());
                Picasso.with(this.f3038a.getActivity()).load(stickerPackageDetailResult.m4293d()).into(this.f3038a.f3045f, new StickerDetailsFragment(this));
                Picasso.with(this.f3038a.getActivity()).load(stickerPackageDetailResult.m4292c()).into(this.f3038a.f3046g, new StickerDetailsFragment(this));
                return;
            }
            MyToast.m2755a(this.f3038a.getActivity(), "No data to show.", 0);
            this.f3038a.f3050k.m3848f();
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m3888a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3889a((StickerPackageDetailResult) obj);
        }
    }

    public StickerDetailsFragment(String str) {
        this.f3041b = str;
    }

    private void m3891a(double d) {
        Display defaultDisplay = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int i = (point.x * 90) / 100;
        this.f3046g.setLayoutParams(new LayoutParams(i, (int) (((double) i) * d)));
    }

    private void m3896d() {
        this.f3042c = (TextView) this.f3040a.findViewById(2131427814);
        this.f3043d = (TextView) this.f3040a.findViewById(2131427815);
        this.f3044e = (TextView) this.f3040a.findViewById(2131427816);
        this.f3045f = (ImageView) this.f3040a.findViewById(2131427813);
        this.f3046g = (ImageView) this.f3040a.findViewById(2131427819);
        this.f3048i = (ProgressBar) this.f3040a.findViewById(2131427817);
        this.f3047h = (ProgressBar) this.f3040a.findViewById(2131427818);
        this.f3049j = (Button) this.f3040a.findViewById(2131427820);
        this.f3049j.setOnClickListener(new StickerDetailsFragment(this));
        new Thread(new StickerDetailsFragment(this)).start();
    }

    public void m3903a() {
        if (getActivity() != null && this.f3049j != null) {
            getActivity().runOnUiThread(new StickerDetailsFragment(this));
        }
    }

    public void m3904b() {
        if (getActivity() != null && this.f3049j != null) {
            getActivity().runOnUiThread(new StickerDetailsFragment(this));
        }
    }

    public void m3905c() {
        if (getActivity() != null && this.f3049j != null) {
            getActivity().runOnUiThread(new StickerDetailsFragment(this));
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        new StickerDetailsFragment().execute(new Object[]{this.f3041b});
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f3050k = (StickerDetailsFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnStickerDetailsInteractionListener");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3040a = layoutInflater.inflate(2130903199, viewGroup, false);
        m3896d();
        return this.f3040a;
    }
}
