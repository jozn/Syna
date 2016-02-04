package com.mmdt.syna.view.selectcontactmessage;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import mobi.mmdt.p041a.HasVersion;
import org.catrobat.paintroid.R.R;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;

/* renamed from: com.mmdt.syna.view.selectcontactmessage.v */
public class SelectedContactsFragment extends Fragment {
    ArrayList<SelectedContactsDataHolder> f2983a;
    private ImageLoader f2984b;
    private View f2985c;
    private SelectedContactsFragment f2986d;
    private GridView f2987e;
    private ViewGroup f2988f;
    private int f2989g;

    /* renamed from: com.mmdt.syna.view.selectcontactmessage.v.a */
    public class SelectedContactsFragment extends BaseAdapter {
        final /* synthetic */ SelectedContactsFragment f2979a;
        private Context f2980b;
        private LayoutInflater f2981c;
        private ArrayList<SelectedContactsDataHolder> f2982d;

        /* renamed from: com.mmdt.syna.view.selectcontactmessage.v.a.a */
        class SelectedContactsFragment {
            ImageView f2977a;
            final /* synthetic */ SelectedContactsFragment f2978b;

            SelectedContactsFragment(SelectedContactsFragment selectedContactsFragment) {
                this.f2978b = selectedContactsFragment;
            }
        }

        public SelectedContactsFragment(SelectedContactsFragment selectedContactsFragment, Context context, ArrayList<SelectedContactsDataHolder> arrayList) {
            this.f2979a = selectedContactsFragment;
            this.f2982d = new ArrayList();
            this.f2980b = context;
            this.f2982d = arrayList;
            this.f2981c = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.f2982d.size();
        }

        public Object getItem(int i) {
            return this.f2982d.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            SelectedContactsFragment selectedContactsFragment;
            if (view == null) {
                view = this.f2981c.inflate(2130903188, viewGroup, false);
                selectedContactsFragment = new SelectedContactsFragment(this);
                selectedContactsFragment.f2977a = (ImageView) view.findViewById(R.image);
                view.setTag(selectedContactsFragment);
                if (this.f2979a.f2989g > 0) {
                    view.getLayoutParams().height = this.f2979a.f2989g;
                }
            } else {
                selectedContactsFragment = (SelectedContactsFragment) view.getTag();
            }
            this.f2979a.f2984b.m2484a(((SelectedContactsDataHolder) this.f2982d.get(i)).m3827b(), selectedContactsFragment.f2977a);
            return view;
        }
    }

    public SelectedContactsFragment() {
        this.f2983a = new ArrayList();
        this.f2989g = -1;
    }

    private int m3828D() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(2131296311, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3831a(String str, int i) {
        AssetFileDescriptor openAssetFileDescriptor;
        FileNotFoundException e;
        Throwable th;
        Bitmap bitmap = null;
        if (m97k() && m91h() != null) {
            try {
                openAssetFileDescriptor = m91h().getContentResolver().openAssetFileDescriptor(HasVersion.m4098a() ? Uri.parse(str) : Uri.parse(str), AckRequest.ELEMENT);
                try {
                    FileDescriptor fileDescriptor = openAssetFileDescriptor.getFileDescriptor();
                    if (fileDescriptor != null) {
                        bitmap = ImageLoader.m2471a(fileDescriptor, i, i);
                        if (openAssetFileDescriptor != null) {
                            try {
                                openAssetFileDescriptor.close();
                            } catch (IOException e2) {
                            }
                        }
                    } else if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (FileNotFoundException e4) {
                    e = e4;
                    try {
                        Log.d("SelectedContactsFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
                        if (openAssetFileDescriptor != null) {
                            try {
                                openAssetFileDescriptor.close();
                            } catch (IOException e5) {
                            }
                        }
                        return bitmap;
                    } catch (Throwable th2) {
                        th = th2;
                        if (openAssetFileDescriptor != null) {
                            try {
                                openAssetFileDescriptor.close();
                            } catch (IOException e6) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (FileNotFoundException e7) {
                e = e7;
                openAssetFileDescriptor = null;
                Log.d("SelectedContactsFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
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
        }
        return bitmap;
    }

    private void m3832a() {
        LayoutParams layoutParams;
        if (m93i().getConfiguration().orientation == 1) {
            layoutParams = this.f2987e.getLayoutParams();
            if (this.f2983a.size() == 0) {
                layoutParams.height = 1;
                return;
            } else if (this.f2983a.size() <= 8) {
                layoutParams.height = this.f2989g;
                return;
            } else {
                layoutParams.height = this.f2989g * 2;
                this.f2987e.setLayoutParams(layoutParams);
                this.f2987e.postDelayed(new SelectedContactsFragment(this), 100);
                return;
            }
        }
        layoutParams = this.f2987e.getLayoutParams();
        if (this.f2983a.size() == 0) {
            layoutParams.height = 1;
        } else if (this.f2983a.size() <= 12) {
            layoutParams.height = this.f2989g;
        } else {
            layoutParams.height = this.f2989g * 2;
            this.f2987e.setLayoutParams(layoutParams);
            this.f2987e.postDelayed(new SelectedContactsFragment(this), 100);
        }
    }

    private void m3834b() {
        Display defaultDisplay = ((WindowManager) m91h().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        if (m93i().getConfiguration().orientation == 1) {
            this.f2989g = point.x / 8;
        } else {
            this.f2989g = point.x / 12;
        }
        this.f2986d = new SelectedContactsFragment(this, m91h(), this.f2983a);
        this.f2987e = (GridView) this.f2985c.findViewById(2131427807);
        this.f2987e.setDrawSelectorOnTop(false);
        this.f2987e.setGravity(17);
        if (m93i().getConfiguration().orientation == 1) {
            this.f2987e.setNumColumns(8);
        } else {
            this.f2987e.setNumColumns(12);
        }
        this.f2987e.setPadding(5, 0, 5, 0);
        this.f2987e.setClipToPadding(true);
        this.f2987e.setStretchMode(2);
        this.f2987e.setAdapter(this.f2986d);
        m3832a();
    }

    public View m3836a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2988f = new FrameLayout(m91h());
        this.f2985c = layoutInflater.inflate(2130903189, viewGroup, false);
        m3834b();
        this.f2988f.addView(this.f2985c);
        return this.f2988f;
    }

    public void m3837a(Activity activity) {
        super.m57a(activity);
    }

    public void m3838a(Bundle bundle) {
        super.m61a(bundle);
        this.f2984b = new SelectedContactsFragment(this, m91h(), m3828D());
        this.f2984b.m2482a(2130838061);
        this.f2984b.m2483a(m91h().getFragmentManager(), 0.1f);
    }

    public void m3839a(SelectedContactsDataHolder selectedContactsDataHolder) {
        Object obj;
        Iterator it = this.f2983a.iterator();
        while (it.hasNext()) {
            if (((SelectedContactsDataHolder) it.next()).m3826a().equals(selectedContactsDataHolder.m3826a())) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj == null) {
            this.f2983a.add(selectedContactsDataHolder);
        }
        m3832a();
        this.f2986d.notifyDataSetChanged();
    }

    public void m3840a(String str) {
        Iterator it = this.f2983a.iterator();
        while (it.hasNext()) {
            if (((SelectedContactsDataHolder) it.next()).m3826a().equals(str)) {
                it.remove();
            }
        }
        m3832a();
        this.f2986d.notifyDataSetChanged();
    }

    public void m3841d(Bundle bundle) {
        super.m80d(bundle);
    }

    public void m3842e(Bundle bundle) {
        super.m85e(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LayoutInflater layoutInflater = (LayoutInflater) m91h().getSystemService("layout_inflater");
        this.f2988f.removeAllViews();
        this.f2985c = layoutInflater.inflate(2130903189, null);
        m3834b();
        this.f2988f.addView(this.f2985c);
    }

    public void m3843q() {
        super.m103q();
        this.f2984b.m2486b(false);
    }
}
