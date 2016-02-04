package com.mmdt.syna.view.conversation.publicchat;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderManager;
import android.support.v4.content.Loader;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import com.mmdt.syna.view.tools.p020a.p036c.TextLoader;
import com.squareup.picasso.Picasso;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p063d.Groups;
import mobi.mmdt.p041a.HasVersion;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;

/* renamed from: com.mmdt.syna.view.conversation.publicchat.d */
public class ExplorePublicChatsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private ImageLoader f2311Y;
    private View f2312Z;
    private ExplorePublicChatsListFragment aa;
    private ExplorePublicChatsListFragment f2313i;

    /* renamed from: com.mmdt.syna.view.conversation.publicchat.d.b */
    public interface ExplorePublicChatsListFragment {
        void m3272b(String str);

        void m3273c(String str);
    }

    /* renamed from: com.mmdt.syna.view.conversation.publicchat.d.a */
    private class ExplorePublicChatsListFragment extends CursorAdapter {
        final /* synthetic */ ExplorePublicChatsListFragment f2307a;
        private LayoutInflater f2308b;
        private HashMap<String, Boolean> f2309c;
        private TextAppearanceSpan f2310d;

        /* renamed from: com.mmdt.syna.view.conversation.publicchat.d.a.a */
        private class ExplorePublicChatsListFragment {
            TextView f2298a;
            TextView f2299b;
            TextView f2300c;
            TextView f2301d;
            TextView f2302e;
            ImageView f2303f;
            ImageView f2304g;
            RelativeLayout f2305h;
            final /* synthetic */ ExplorePublicChatsListFragment f2306i;

            private ExplorePublicChatsListFragment(ExplorePublicChatsListFragment explorePublicChatsListFragment) {
                this.f2306i = explorePublicChatsListFragment;
            }
        }

        public ExplorePublicChatsListFragment(ExplorePublicChatsListFragment explorePublicChatsListFragment, Context context) {
            this.f2307a = explorePublicChatsListFragment;
            super(context, null, 0);
            this.f2309c = new HashMap();
            this.f2308b = LayoutInflater.from(context);
            this.f2310d = new TextAppearanceSpan(explorePublicChatsListFragment.m91h(), 2131558581);
        }

        public void bindView(View view, Context context, Cursor cursor) {
            ExplorePublicChatsListFragment explorePublicChatsListFragment = (ExplorePublicChatsListFragment) view.getTag();
            Object string = cursor.getString(cursor.getColumnIndex("local_picture"));
            CharSequence string2 = cursor.getString(cursor.getColumnIndex("descripton"));
            CharSequence string3 = cursor.getString(cursor.getColumnIndex("group_nick_name"));
            CharSequence string4 = cursor.getString(cursor.getColumnIndex("followers_count"));
            String string5 = cursor.getString(cursor.getColumnIndex("is_followed_by_me"));
            String string6 = cursor.getString(cursor.getColumnIndex("group_id"));
            boolean equals = string5.equals("true");
            if (string3 == null || string3.equals("")) {
                string3 = "Public Group";
            }
            explorePublicChatsListFragment.f2298a.setText(string3);
            explorePublicChatsListFragment.f2301d.setText(string4);
            explorePublicChatsListFragment.f2299b.setText(string2);
            explorePublicChatsListFragment.f2304g.setVisibility(8);
            explorePublicChatsListFragment.f2300c.setVisibility(8);
            explorePublicChatsListFragment.f2302e.setVisibility(0);
            explorePublicChatsListFragment.f2302e.setText(equals ? this.f2307a.m54a(2131493763) : this.f2307a.m54a(2131493764));
            explorePublicChatsListFragment.f2302e.setOnClickListener(new ExplorePublicChatsListFragment(this, equals, string6));
            if (string == null || string.equals("null")) {
                Picasso.with(context).load(2130838071).into(explorePublicChatsListFragment.f2303f);
            } else {
                this.f2307a.f2311Y.m2484a(string, explorePublicChatsListFragment.f2303f);
            }
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2308b.inflate(2130903173, viewGroup, false);
            ExplorePublicChatsListFragment explorePublicChatsListFragment = new ExplorePublicChatsListFragment();
            explorePublicChatsListFragment.f2298a = (TextView) inflate.findViewById(2131427613);
            explorePublicChatsListFragment.f2299b = (TextView) inflate.findViewById(2131427615);
            explorePublicChatsListFragment.f2300c = (TextView) inflate.findViewById(2131427614);
            explorePublicChatsListFragment.f2301d = (TextView) inflate.findViewById(2131427780);
            explorePublicChatsListFragment.f2302e = (TextView) inflate.findViewById(2131427779);
            explorePublicChatsListFragment.f2305h = (RelativeLayout) inflate.findViewById(2131427609);
            explorePublicChatsListFragment.f2303f = (ImageView) inflate.findViewById(2131427611);
            explorePublicChatsListFragment.f2304g = (ImageView) inflate.findViewById(2131427616);
            inflate.setTag(explorePublicChatsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            return super.swapCursor(cursor);
        }
    }

    private void m3279D() {
    }

    private int m3280E() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3282a(String str, int i) {
        FileNotFoundException e;
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
                        e.printStackTrace();
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
                e.printStackTrace();
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

    private void m3285a(String str) {
        this.aa.m3272b(str);
    }

    private void m3287b(String str) {
        this.aa.m3273c(str);
    }

    public Loader<Cursor> m3288a(int i, Bundle bundle) {
        return Groups.m5047a(m91h(), DatabaseContentProvider.m4931e(m91h()));
    }

    public View m3289a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2312Z = layoutInflater.inflate(2130903141, viewGroup, false);
        m3279D();
        return this.f2312Z;
    }

    public void m3290a(Activity activity) {
        super.m57a(activity);
        try {
            this.aa = (ExplorePublicChatsListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnExplorePublicChatsListInteractionListener");
        }
    }

    public void m3291a(Bundle bundle) {
        super.m61a(bundle);
        this.f2313i = new ExplorePublicChatsListFragment(this, m91h());
        this.f2311Y = new ExplorePublicChatsListFragment(this, m91h(), m3280E());
        this.f2311Y.m2482a(2130838061);
        this.f2311Y.m2483a(m91h().getFragmentManager(), 0.1f);
        TextLoader.m4005a(m91h()).m4014a(m91h().getFragmentManager(), 0.5f);
    }

    public void m3292a(Loader<Cursor> loader) {
        this.f2313i.swapCursor(null);
    }

    public void m3293a(Loader<Cursor> loader, Cursor cursor) {
        this.f2313i.swapCursor(cursor);
    }

    public void m3295d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f2313i);
        m364a().setOnItemClickListener(this);
        m364a().setOnScrollListener(new ExplorePublicChatsListFragment(this));
        m364a().setDividerHeight(1);
        m100n().m144a(0, null, this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = this.f2313i.getCursor();
        cursor.moveToPosition(i);
        cursor.getString(cursor.getColumnIndex("group_id"));
    }

    public void m3296q() {
        super.m103q();
        this.f2311Y.m2486b(false);
    }
}
