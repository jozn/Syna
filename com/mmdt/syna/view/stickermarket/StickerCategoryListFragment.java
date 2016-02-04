package com.mmdt.syna.view.stickermarket;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
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
import org.catrobat.paintroid.R.R;
import org.p074b.JSONException;

/* renamed from: com.mmdt.syna.view.stickermarket.b */
public class StickerCategoryListFragment extends Fragment {
    private View f3026a;
    private ListView f3027b;
    private StickerCategoryListFragment f3028c;
    private ArrayList<StickerCollectionResult> f3029d;
    private boolean f3030e;
    private StickerCategoryListFragment f3031f;
    private ProgressBar f3032g;
    private String f3033h;

    /* renamed from: com.mmdt.syna.view.stickermarket.b.b */
    public interface StickerCategoryListFragment {
        void m3845b(String str);
    }

    /* renamed from: com.mmdt.syna.view.stickermarket.b.a */
    private class StickerCategoryListFragment extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ StickerCategoryListFragment f3014a;
        private String f3015b;

        private StickerCategoryListFragment(StickerCategoryListFragment stickerCategoryListFragment) {
            this.f3014a = stickerCategoryListFragment;
        }

        protected Void m3878a(Object... objArr) {
            StickerCollectionResult[] a;
            Exception e;
            int i = 0;
            this.f3015b = (String) objArr[0];
            try {
                a = StickerWebservices.m4305a(this.f3014a.getActivity(), AppSettings.m4867a(this.f3014a.getActivity()).m4915y(), AppSettings.m4867a(this.f3014a.getActivity()).m4897j(), AppSettings.m4867a(this.f3014a.getActivity()).m4901l(), AppSettings.m4867a(this.f3014a.getActivity()).m4889f()).m4310a("20", "0", this.f3015b).m4288a();
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
            this.f3014a.m3885a(true);
            if (a != null) {
                int length = a.length;
                while (i < length) {
                    this.f3014a.f3029d.add(a[i]);
                    i++;
                }
            }
            return null;
        }

        protected void m3879a(Void voidR) {
            this.f3014a.f3032g.setVisibility(8);
            this.f3014a.f3028c.notifyDataSetChanged();
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m3878a(objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3879a((Void) obj);
        }
    }

    /* renamed from: com.mmdt.syna.view.stickermarket.b.c */
    public class StickerCategoryListFragment extends BaseAdapter {
        final /* synthetic */ StickerCategoryListFragment f3022a;
        private Context f3023b;
        private LayoutInflater f3024c;
        private ArrayList<StickerCollectionResult> f3025d;

        /* renamed from: com.mmdt.syna.view.stickermarket.b.c.a */
        class StickerCategoryListFragment {
            ProgressBar f3016a;
            ImageView f3017b;
            TextView f3018c;
            TextView f3019d;
            TextView f3020e;
            final /* synthetic */ StickerCategoryListFragment f3021f;

            StickerCategoryListFragment(StickerCategoryListFragment stickerCategoryListFragment) {
                this.f3021f = stickerCategoryListFragment;
            }
        }

        public StickerCategoryListFragment(StickerCategoryListFragment stickerCategoryListFragment, Context context, ArrayList<StickerCollectionResult> arrayList) {
            this.f3022a = stickerCategoryListFragment;
            this.f3025d = new ArrayList();
            this.f3023b = context;
            this.f3025d = arrayList;
            this.f3024c = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.f3025d.size();
        }

        public Object getItem(int i) {
            return this.f3025d.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            StickerCategoryListFragment stickerCategoryListFragment;
            if (view == null) {
                view = this.f3024c.inflate(2130903206, viewGroup, false);
                stickerCategoryListFragment = new StickerCategoryListFragment(this);
                stickerCategoryListFragment.f3017b = (ImageView) view.findViewById(2131427813);
                stickerCategoryListFragment.f3018c = (TextView) view.findViewById(2131427814);
                stickerCategoryListFragment.f3019d = (TextView) view.findViewById(2131427815);
                stickerCategoryListFragment.f3020e = (TextView) view.findViewById(2131427816);
                stickerCategoryListFragment.f3016a = (ProgressBar) view.findViewById(R.progressBar);
                view.setTag(stickerCategoryListFragment);
            } else {
                stickerCategoryListFragment = (StickerCategoryListFragment) view.getTag();
            }
            Picasso.with(this.f3022a.getActivity()).load(((StickerCollectionResult) this.f3025d.get(i)).m4286d()).into(stickerCategoryListFragment.f3017b, new StickerCategoryListFragment(this, stickerCategoryListFragment.f3016a));
            stickerCategoryListFragment.f3018c.setText(((StickerCollectionResult) this.f3025d.get(i)).m4287e());
            stickerCategoryListFragment.f3019d.setText(Double.parseDouble(((StickerCollectionResult) this.f3025d.get(i)).m4285c()) == 0.0d ? this.f3022a.getString(2131493656) : new StringBuilder(String.valueOf(WebServiceManager.m4379a(((StickerCollectionResult) this.f3025d.get(i)).m4285c()))).append(" IRR").toString());
            stickerCategoryListFragment.f3020e.setText(((StickerCollectionResult) this.f3025d.get(i)).m4283a());
            return view;
        }
    }

    public StickerCategoryListFragment() {
        this.f3029d = new ArrayList();
        this.f3030e = false;
    }

    public StickerCategoryListFragment(String str) {
        this.f3029d = new ArrayList();
        this.f3030e = false;
        this.f3033h = str;
    }

    private void m3882b() {
        this.f3027b = (ListView) this.f3026a.findViewById(16908298);
        this.f3032g = (ProgressBar) this.f3026a.findViewById(2131427823);
        this.f3028c = new StickerCategoryListFragment(this, getActivity(), this.f3029d);
        this.f3027b.setAdapter(this.f3028c);
        this.f3027b.setDividerHeight(1);
        this.f3027b.setOnItemClickListener(new StickerCategoryListFragment(this));
    }

    public void m3885a(boolean z) {
        this.f3030e = z;
    }

    public boolean m3886a() {
        return this.f3030e;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!m3886a()) {
            new StickerCategoryListFragment().execute(new Object[]{this.f3033h});
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f3031f = (StickerCategoryListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnStickerMarketListInteractionListener");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3026a = layoutInflater.inflate(2130903202, viewGroup, false);
        m3882b();
        return this.f3026a;
    }

    public void onResume() {
        super.onResume();
        if (!this.f3029d.isEmpty()) {
            this.f3032g.setVisibility(8);
        }
    }
}
