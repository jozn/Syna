package com.mmdt.syna.view.stickermarket;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.p043b.p044a.AmirWebserviceException;
import mobi.mmdt.ott.p043b.p044a.p045a.StickerWebservices;
import mobi.mmdt.ott.p043b.p044a.p045a.p046a.StickerCollectionResult;
import mobi.mmdt.ott.p043b.p048c.WebServiceManager;
import org.apache.http.protocol.HTTP;
import org.catrobat.paintroid.R.R;
import org.linphone.core.VideoSize;
import org.p074b.JSONException;

/* renamed from: com.mmdt.syna.view.stickermarket.r */
public class StickerMarketListFragment extends Fragment {
    private View f3092a;
    private ListView f3093b;
    private StickerMarketListFragment f3094c;
    private ArrayList<StickerCollectionResult> f3095d;
    private boolean f3096e;
    private int f3097f;
    private StickerMarketListFragment f3098g;
    private ProgressBar f3099h;

    /* renamed from: com.mmdt.syna.view.stickermarket.r.b */
    public interface StickerMarketListFragment {
        void m3863b(String str);
    }

    /* renamed from: com.mmdt.syna.view.stickermarket.r.a */
    private class StickerMarketListFragment extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ StickerMarketListFragment f3081a;
        private String f3082b;

        private StickerMarketListFragment(StickerMarketListFragment stickerMarketListFragment) {
            this.f3081a = stickerMarketListFragment;
        }

        protected Void m3919a(Object... objArr) {
            StickerCollectionResult[] a;
            Exception e;
            int i = 0;
            this.f3082b = (String) objArr[0];
            try {
                a = StickerWebservices.m4305a(this.f3081a.m91h(), AppSettings.m4867a(this.f3081a.m91h()).m4915y(), AppSettings.m4867a(this.f3081a.m91h()).m4897j(), AppSettings.m4867a(this.f3081a.m91h()).m4901l(), AppSettings.m4867a(this.f3081a.m91h()).m4889f()).m4312b("20", "0", this.f3082b).m4288a();
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                a = null;
            } catch (IOException e3) {
                e = e3;
                e.printStackTrace();
                a = null;
            } catch (AmirWebserviceException e4) {
                e = e4;
                e.printStackTrace();
                a = null;
            } catch (NameNotFoundException e5) {
                e5.printStackTrace();
                a = null;
            } catch (NumberFormatException e6) {
                e6.printStackTrace();
                a = null;
            } catch (GeneralSecurityException e7) {
                e7.printStackTrace();
                a = null;
            }
            this.f3081a.m3928a(true);
            if (a != null) {
                int length = a.length;
                while (i < length) {
                    this.f3081a.f3095d.add(a[i]);
                    i++;
                }
            }
            return null;
        }

        protected void m3920a(Void voidR) {
            this.f3081a.f3099h.setVisibility(8);
            this.f3081a.f3094c.notifyDataSetChanged();
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m3919a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3920a((Void) obj);
        }
    }

    /* renamed from: com.mmdt.syna.view.stickermarket.r.c */
    public class StickerMarketListFragment extends BaseAdapter {
        final /* synthetic */ StickerMarketListFragment f3089a;
        private LayoutInflater f3090b;
        private ArrayList<StickerCollectionResult> f3091c;

        /* renamed from: com.mmdt.syna.view.stickermarket.r.c.a */
        class StickerMarketListFragment {
            ProgressBar f3083a;
            ImageView f3084b;
            TextView f3085c;
            TextView f3086d;
            TextView f3087e;
            final /* synthetic */ StickerMarketListFragment f3088f;

            StickerMarketListFragment(StickerMarketListFragment stickerMarketListFragment) {
                this.f3088f = stickerMarketListFragment;
            }
        }

        public StickerMarketListFragment(StickerMarketListFragment stickerMarketListFragment, Context context, ArrayList<StickerCollectionResult> arrayList) {
            this.f3089a = stickerMarketListFragment;
            this.f3091c = new ArrayList();
            this.f3091c = arrayList;
            this.f3090b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.f3091c.size();
        }

        public Object getItem(int i) {
            return this.f3091c.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            StickerMarketListFragment stickerMarketListFragment;
            if (view == null) {
                view = this.f3090b.inflate(2130903206, viewGroup, false);
                stickerMarketListFragment = new StickerMarketListFragment(this);
                stickerMarketListFragment.f3084b = (ImageView) view.findViewById(2131427813);
                stickerMarketListFragment.f3085c = (TextView) view.findViewById(2131427814);
                stickerMarketListFragment.f3086d = (TextView) view.findViewById(2131427815);
                stickerMarketListFragment.f3087e = (TextView) view.findViewById(2131427816);
                stickerMarketListFragment.f3083a = (ProgressBar) view.findViewById(R.progressBar);
                view.setTag(stickerMarketListFragment);
            } else {
                stickerMarketListFragment = (StickerMarketListFragment) view.getTag();
            }
            Picasso.with(this.f3089a.m91h()).load(((StickerCollectionResult) this.f3091c.get(i)).m4286d()).into(stickerMarketListFragment.f3084b, new StickerMarketListFragment(this, stickerMarketListFragment.f3083a));
            stickerMarketListFragment.f3085c.setText(((StickerCollectionResult) this.f3091c.get(i)).m4287e());
            stickerMarketListFragment.f3086d.setText(Double.parseDouble(((StickerCollectionResult) this.f3091c.get(i)).m4285c()) == 0.0d ? this.f3089a.m54a(2131493656) : new StringBuilder(String.valueOf(WebServiceManager.m4379a(((StickerCollectionResult) this.f3091c.get(i)).m4285c()))).append(" IRR").toString());
            stickerMarketListFragment.f3087e.setText(((StickerCollectionResult) this.f3091c.get(i)).m4283a());
            return view;
        }
    }

    public StickerMarketListFragment() {
        this.f3095d = new ArrayList();
        this.f3096e = false;
        this.f3097f = 0;
    }

    public StickerMarketListFragment(int i) {
        this.f3095d = new ArrayList();
        this.f3096e = false;
        this.f3097f = 0;
        this.f3097f = i;
    }

    private void m3923b() {
        this.f3093b = (ListView) this.f3092a.findViewById(16908298);
        this.f3099h = (ProgressBar) this.f3092a.findViewById(2131427823);
        this.f3094c = new StickerMarketListFragment(this, m91h(), this.f3095d);
        this.f3093b.setAdapter(this.f3094c);
        this.f3093b.setDividerHeight(1);
        this.f3093b.setOnItemClickListener(new StickerMarketListFragment(this));
    }

    public View m3926a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3092a = layoutInflater.inflate(2130903202, viewGroup, false);
        m3923b();
        return this.f3092a;
    }

    public void m3927a(Activity activity) {
        super.m57a(activity);
        try {
            this.f3098g = (StickerMarketListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnStickerMarketListInteractionListener");
        }
    }

    public void m3928a(boolean z) {
        this.f3096e = z;
    }

    public boolean m3929a() {
        return this.f3096e;
    }

    public void m3930d(Bundle bundle) {
        super.m80d(bundle);
        String str = HTTP.DATE_HEADER;
        switch (this.f3097f) {
            case VideoSize.QCIF /*0*/:
                str = HTTP.DATE_HEADER;
                break;
            case VideoSize.CIF /*1*/:
                str = "Star";
                break;
            case VideoSize.HVGA /*2*/:
                str = "Free";
                break;
            default:
                str = HTTP.DATE_HEADER;
                break;
        }
        if (!m3929a()) {
            new StickerMarketListFragment().execute(new Object[]{str});
        }
    }

    public void m3931p() {
        super.m102p();
        if (!this.f3095d.isEmpty()) {
            this.f3099h.setVisibility(8);
        }
    }
}
