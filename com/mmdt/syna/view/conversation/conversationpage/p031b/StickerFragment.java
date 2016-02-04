package com.mmdt.syna.view.conversation.conversationpage.p031b;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mobi.mmdt.ott.core.logic.sticker.StickerItemDataHolder;
import mobi.mmdt.ott.core.model.database.p068h.StickerRow;
import mobi.mmdt.ott.core.model.database.p068h.Stickers;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.core.p051a.FileManager;
import mobi.mmdt.p041a.HasVersion;
import org.catrobat.paintroid.R.R;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.a */
public class StickerFragment extends Fragment implements OnItemClickListener {
    private AsymmetricGridViewAdapter<StickerItemDataHolder> f2164Y;
    private ImageLoader f2165a;
    private StickerFragment f2166b;
    private View f2167c;
    private AsymmetricGridView f2168d;
    private FrameLayout f2169e;
    private ArrayList<StickerItemDataHolder> f2170f;
    private ListAdapter f2171g;
    private int f2172h;
    private int f2173i;

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.a.b */
    public interface StickerFragment {
        void m2971f(String str);
    }

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.a.a */
    public class StickerFragment extends ArrayAdapter<StickerItemDataHolder> implements WrapperListAdapter {
        final /* synthetic */ StickerFragment f2161a;
        private final AsymmetricGridViewAdapter<StickerItemDataHolder> f2162b;
        private LayoutInflater f2163c;

        /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.a.a.a */
        class StickerFragment {
            ImageView f2159a;
            final /* synthetic */ StickerFragment f2160b;

            StickerFragment(StickerFragment stickerFragment) {
                this.f2160b = stickerFragment;
            }
        }

        public StickerFragment(StickerFragment stickerFragment, Context context, AsymmetricGridView asymmetricGridView, List<StickerItemDataHolder> list) {
            this.f2161a = stickerFragment;
            super(context, 0, list);
            this.f2163c = (LayoutInflater) context.getSystemService("layout_inflater");
            this.f2162b = new StickerFragment(this, context, asymmetricGridView, list);
        }

        @SuppressLint({"ViewHolder"})
        public View getView(int i, View view, ViewGroup viewGroup) {
            StickerItemDataHolder stickerItemDataHolder = (StickerItemDataHolder) getItem(i);
            View inflate = this.f2163c.inflate(2130903200, viewGroup, false);
            StickerFragment stickerFragment = new StickerFragment(this);
            stickerFragment.f2159a = (ImageView) inflate.findViewById(R.image);
            inflate.setTag(stickerFragment);
            if (stickerItemDataHolder.m4844k() != null) {
                this.f2161a.f2165a.m2484a(Uri.fromFile(new File(stickerItemDataHolder.m4844k())), stickerFragment.f2159a);
            }
            return inflate;
        }

        public ListAdapter getWrappedAdapter() {
            return this.f2162b;
        }
    }

    public StickerFragment() {
        this.f2170f = new ArrayList();
        this.f2172h = 0;
    }

    public StickerFragment(int i) {
        this.f2170f = new ArrayList();
        this.f2172h = 0;
        this.f2173i = i;
    }

    private Bitmap m3115a(String str, int i) {
        Throwable th;
        Bitmap bitmap = null;
        if (m97k() && m91h() != null) {
            AssetFileDescriptor openAssetFileDescriptor;
            try {
                openAssetFileDescriptor = m91h().getContentResolver().openAssetFileDescriptor(HasVersion.m4098a() ? Uri.parse(str) : Uri.parse(str), AckRequest.ELEMENT);
                try {
                    FileDescriptor fileDescriptor = openAssetFileDescriptor.getFileDescriptor();
                    if (fileDescriptor != null) {
                        bitmap = ImageLoader.m2471a(fileDescriptor, i, i);
                        if (openAssetFileDescriptor != null) {
                            try {
                                openAssetFileDescriptor.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e3) {
                    if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    return bitmap;
                } catch (Throwable th2) {
                    th = th2;
                    if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                openAssetFileDescriptor = null;
                if (openAssetFileDescriptor != null) {
                    openAssetFileDescriptor.close();
                }
                return bitmap;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                openAssetFileDescriptor = null;
                th = th4;
                if (openAssetFileDescriptor != null) {
                    openAssetFileDescriptor.close();
                }
                throw th;
            }
        }
        return bitmap;
    }

    private void m3117a() {
        this.f2168d = new AsymmetricGridView(m91h());
        this.f2171g = new StickerFragment(this, m91h(), this.f2168d, new ArrayList());
        if (this.f2171g instanceof WrapperListAdapter) {
            this.f2164Y = (AsymmetricGridViewAdapter) ((WrapperListAdapter) this.f2171g).getWrappedAdapter();
        } else {
            this.f2164Y = (AsymmetricGridViewAdapter) this.f2171g;
        }
        this.f2168d.setRequestedColumnCount(5);
        this.f2168d.setRequestedHorizontalSpacing(Utils.m2312a(m91h(), 1.0f));
        this.f2168d.setAdapter(this.f2171g);
        this.f2168d.setDebugging(AppSettings.m4867a(m91h()).m4910t());
        this.f2168d.setDivider(m91h().getResources().getDrawable(2130838515));
        this.f2168d.setOnItemClickListener(this);
        m91h().runOnUiThread(new StickerFragment(this));
        this.f2167c = this.f2168d;
    }

    private String m3119b(int i) {
        String str = "";
        return i > 10 ? i : i < 10 ? "0" + i : i;
    }

    private String m3120c(int i) {
        String str = "";
        return (i <= 0 || i >= 10) ? (i < 10 || i >= 100) ? i : "0" + i : "00" + i;
    }

    public View m3122a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2169e = new FrameLayout(m91h());
        m3117a();
        this.f2169e.addView(this.f2167c);
        return this.f2169e;
    }

    public void m3123a(Activity activity) {
        super.m57a(activity);
        try {
            this.f2166b = (StickerFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnStickerInteractionListener");
        }
    }

    public void m3124a(Bundle bundle) {
        super.m61a(bundle);
        for (StickerRow stickerRow : Stickers.m5148a(m91h(), this.f2173i)) {
            String b = FileManager.m4437a(m91h()).m4443b(new StringBuilder(String.valueOf(stickerRow.m5140c())).toString(), stickerRow.m5142e());
            if (b != null) {
                this.f2170f.add(new StickerItemDataHolder(stickerRow.m5140c(), stickerRow.m5141d(), stickerRow.m5143f(), stickerRow.m5138a(), stickerRow.m5144g(), stickerRow.m5145h(), stickerRow.m5139b(), stickerRow.m5142e(), b));
            }
        }
        this.f2165a = new StickerFragment(this, m91h(), 40);
        this.f2165a.m2482a(2130838515);
        this.f2165a.m2483a(m91h().getFragmentManager(), 0.1f);
    }

    public void m3125d(Bundle bundle) {
        super.m80d(bundle);
    }

    public void m3126e(Bundle bundle) {
        super.m85e(bundle);
        bundle.putInt("currentOffset", this.f2172h);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f2169e.removeAllViews();
        m3117a();
        this.f2169e.addView(this.f2167c);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        StickerItemDataHolder stickerItemDataHolder = (StickerItemDataHolder) adapterView.getItemAtPosition(i);
        this.f2166b.m2971f("b_" + m3120c(stickerItemDataHolder.m4843j()) + "_sticker_" + m3119b(stickerItemDataHolder.m4838e()));
    }

    public void m3127q() {
        super.m103q();
        this.f2165a.m2486b(false);
    }
}
