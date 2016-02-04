package com.mmdt.syna.view.conversation.conversationpage.p031b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mmdt.syna.view.conversation.conversationpage.p030a.EmoticonFragment;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.StickersIndicatorItemDataHolder;
import java.io.File;
import java.util.ArrayList;
import mobi.mmdt.ott.core.model.database.p067g.StickerPackagesRow;
import mobi.mmdt.ott.core.p051a.FileManager;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.e */
public class StickersPaneFragment extends Fragment {
    private boolean f2178a;
    private View f2179b;
    private StickersPaneFragment f2180c;
    private ViewPager f2181d;
    private PageIndicator f2182e;
    private ImageView f2183f;
    private ArrayList<StickersIndicatorItemDataHolder> f2184g;
    private ArrayList<Fragment> f2185h;

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.e.a */
    class StickersPaneFragment extends FragmentPagerAdapter implements IconPagerAdapter {
        final /* synthetic */ StickersPaneFragment f2177a;

        public StickersPaneFragment(StickersPaneFragment stickersPaneFragment, FragmentManager fragmentManager) {
            this.f2177a = stickersPaneFragment;
            super(fragmentManager);
        }

        public Fragment m3132a(int i) {
            return (Fragment) this.f2177a.f2185h.get(i);
        }

        public int m3133b() {
            return this.f2177a.f2185h.size();
        }

        public CharSequence m3134c(int i) {
            return new StringBuilder(String.valueOf(((StickersIndicatorItemDataHolder) this.f2177a.f2184g.get(i)).m4049a())).toString();
        }

        public StickersIndicatorItemDataHolder m3135e(int i) {
            return (StickersIndicatorItemDataHolder) this.f2177a.f2184g.get(i % this.f2177a.f2184g.size());
        }
    }

    public StickersPaneFragment() {
        this.f2178a = false;
        this.f2184g = new ArrayList();
        this.f2185h = new ArrayList();
    }

    public StickersPaneFragment(boolean z) {
        this.f2178a = false;
        this.f2184g = new ArrayList();
        this.f2185h = new ArrayList();
        this.f2178a = z;
    }

    private void m3138a() {
        this.f2180c = new StickersPaneFragment(this, m95j());
        m3139a(0, 1, m3143b(2130838460));
        this.f2181d = (ViewPager) this.f2179b.findViewById(2131427494);
        this.f2181d.setAdapter(this.f2180c);
        this.f2182e = (IconPageIndicator) this.f2179b.findViewById(2131427738);
        this.f2182e.setViewPager(this.f2181d);
        this.f2183f = (ImageView) this.f2179b.findViewById(2131427825);
        this.f2183f.setOnClickListener(new StickersPaneFragment(this));
        if (this.f2178a) {
            this.f2183f.setVisibility(8);
            this.f2179b.findViewById(2131427826).setVisibility(8);
        }
    }

    private void m3139a(int i, int i2, Bitmap bitmap) {
        this.f2184g.add(new StickersIndicatorItemDataHolder(i, i2, bitmap));
        this.f2185h.add(new EmoticonFragment());
    }

    private void m3142a(StickerPackagesRow stickerPackagesRow) {
        Bitmap bitmap = null;
        File file = new File(FileManager.m4437a(m91h()).m4443b(new StringBuilder(String.valueOf(stickerPackagesRow.m5134a())).toString(), stickerPackagesRow.m5135b()));
        if (file != null && file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        }
        this.f2184g.add(new StickersIndicatorItemDataHolder(this.f2184g.size(), stickerPackagesRow.m5134a(), bitmap));
        this.f2185h.add(new StickerFragment(stickerPackagesRow.m5134a()));
    }

    private Bitmap m3143b(int i) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeResource(m91h().getResources(), i);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public View m3148a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2179b = layoutInflater.inflate(2130903207, viewGroup, false);
        m3138a();
        return this.f2179b;
    }

    public void m3149a(Bundle bundle) {
        super.m61a(bundle);
    }

    public void m3150d(Bundle bundle) {
        super.m80d(bundle);
    }

    public void m3151e(Bundle bundle) {
        super.m85e(bundle);
    }

    public void m3152p() {
        super.m102p();
        new Thread(new StickersPaneFragment(this)).start();
    }

    public void m3153q() {
        super.m103q();
    }
}
