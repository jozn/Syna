package com.mmdt.syna.view.conversation.conversationpage.p030a;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import com.mmdt.syna.view.tools.p037b.EmoticonManager;
import java.util.ArrayList;
import org.catrobat.paintroid.R.R;

/* renamed from: com.mmdt.syna.view.conversation.conversationpage.a.a */
public class EmoticonFragment extends Fragment {
    private ImageLoader f2004a;
    private EmoticonFragment f2005b;
    private View f2006c;
    private EmoticonFragment f2007d;
    private GridView f2008e;
    private ViewGroup f2009f;

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.a.a.a */
    public class EmoticonFragment extends BaseAdapter {
        final /* synthetic */ EmoticonFragment f2000a;
        private LayoutInflater f2001b;
        private ArrayList<EmoticonsData> f2002c;
        private int f2003d;

        /* renamed from: com.mmdt.syna.view.conversation.conversationpage.a.a.a.a */
        class EmoticonFragment {
            ImageView f1998a;
            final /* synthetic */ EmoticonFragment f1999b;

            EmoticonFragment(EmoticonFragment emoticonFragment) {
                this.f1999b = emoticonFragment;
            }
        }

        public EmoticonFragment(EmoticonFragment emoticonFragment, Context context, ArrayList<EmoticonsData> arrayList, int i) {
            this.f2000a = emoticonFragment;
            this.f2002c = new ArrayList();
            this.f2003d = -1;
            this.f2002c = arrayList;
            this.f2003d = i;
            this.f2001b = (LayoutInflater) context.getSystemService("layout_inflater");
        }

        public int getCount() {
            return this.f2002c.size();
        }

        public Object getItem(int i) {
            return this.f2002c.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            EmoticonFragment emoticonFragment;
            if (view == null) {
                view = this.f2001b.inflate(2130903137, viewGroup, false);
                EmoticonFragment emoticonFragment2 = new EmoticonFragment(this);
                emoticonFragment2.f1998a = (ImageView) view.findViewById(R.image);
                view.setTag(emoticonFragment2);
                emoticonFragment = emoticonFragment2;
            } else {
                emoticonFragment = (EmoticonFragment) view.getTag();
            }
            emoticonFragment.f1998a.setImageResource(((EmoticonsData) this.f2002c.get(i)).m2960a());
            return view;
        }
    }

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.a.a.b */
    public interface EmoticonFragment {
        void m2949e(String str);
    }

    private void m2951a() {
        this.f2008e = new GridView(m91h());
        this.f2008e.setDrawSelectorOnTop(false);
        this.f2008e.setGravity(17);
        if (m93i().getConfiguration().orientation == 1) {
            this.f2008e.setNumColumns(6);
        } else {
            this.f2008e.setNumColumns(8);
        }
        this.f2008e.setPadding(5, 5, 5, 5);
        this.f2008e.setClipToPadding(true);
        this.f2008e.setStretchMode(2);
        this.f2008e.setAdapter(this.f2007d);
        this.f2008e.setOnItemClickListener(new EmoticonFragment(this));
        this.f2006c = this.f2008e;
    }

    private int m2952b() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    public View m2953a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2009f = new FrameLayout(m91h());
        m2951a();
        this.f2009f.addView(this.f2006c);
        return this.f2009f;
    }

    public void m2954a(Activity activity) {
        super.m57a(activity);
        try {
            this.f2005b = (EmoticonFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnEmoticonInteractionListener");
        }
    }

    public void m2955a(Bundle bundle) {
        super.m61a(bundle);
        ArrayList arrayList = new ArrayList();
        for (String str : EmoticonManager.f3155a.keySet()) {
            arrayList.add(new EmoticonsData(str, ((Integer) EmoticonManager.f3155a.get(str)).intValue()));
        }
        Display defaultDisplay = ((WindowManager) m91h().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.f2007d = new EmoticonFragment(this, m91h(), arrayList, m93i().getConfiguration().orientation == 1 ? point.x / 6 : point.x / 8);
        this.f2004a = new EmoticonFragment(this, m91h(), m2952b());
        this.f2004a.m2482a(2130838061);
        this.f2004a.m2483a(m91h().getFragmentManager(), 0.1f);
    }

    public void m2956d(Bundle bundle) {
        super.m80d(bundle);
    }

    public void m2957e(Bundle bundle) {
        super.m85e(bundle);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f2009f.removeAllViews();
        m2951a();
        this.f2009f.addView(this.f2006c);
    }

    public void m2958q() {
        super.m103q();
        this.f2004a.m2486b(false);
    }
}
