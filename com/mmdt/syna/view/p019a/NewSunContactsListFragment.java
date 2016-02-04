package com.mmdt.syna.view.p019a;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderManager;
import android.support.v4.content.Loader;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AlphabetIndexer;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshAdapterViewBase;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshListView;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.p051a.FileManager;
import mobi.mmdt.p041a.HasVersion;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smackx.search.UserSearch;
import org.linphone.C0282R;
import org.linphone.core.VideoSize;

/* renamed from: com.mmdt.syna.view.a.t */
public class NewSunContactsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private ImageLoader f1661Y;
    private String f1662Z;
    private OnContactsInteractionListener aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private View ae;
    private PullToRefreshAdapterViewBase<ListView> af;
    private String ag;
    private String ah;
    private int[] ai;
    private NewSunContactsListFragment f1663i;

    /* renamed from: com.mmdt.syna.view.a.t.a */
    private class NewSunContactsListFragment extends CursorAdapter implements SectionIndexer {
        final /* synthetic */ NewSunContactsListFragment f1657a;
        private LayoutInflater f1658b;
        private AlphabetIndexer f1659c;
        private TextAppearanceSpan f1660d;

        /* renamed from: com.mmdt.syna.view.a.t.a.a */
        private class NewSunContactsListFragment {
            TextView f1650a;
            TextView f1651b;
            ImageView f1652c;
            ImageView f1653d;
            View f1654e;
            View f1655f;
            final /* synthetic */ NewSunContactsListFragment f1656g;

            private NewSunContactsListFragment(NewSunContactsListFragment newSunContactsListFragment) {
                this.f1656g = newSunContactsListFragment;
            }
        }

        public NewSunContactsListFragment(NewSunContactsListFragment newSunContactsListFragment, Context context) {
            this.f1657a = newSunContactsListFragment;
            super(context, null, 0);
            this.f1658b = LayoutInflater.from(context);
            this.f1659c = new AlphabetIndexer(null, 4, context.getString(2131493548));
            this.f1660d = new TextAppearanceSpan(newSunContactsListFragment.m91h(), 2131558580);
        }

        private int m2520a(String str) {
            return !TextUtils.isEmpty(this.f1657a.f1662Z) ? str.toLowerCase(Locale.getDefault()).indexOf(this.f1657a.f1662Z.toLowerCase(Locale.getDefault())) : -1;
        }

        public void bindView(View view, Context context, Cursor cursor) {
            int i = 1;
            NewSunContactsListFragment newSunContactsListFragment = (NewSunContactsListFragment) view.getTag();
            Object string = cursor.getString(3);
            CharSequence string2 = cursor.getString(1);
            int a = m2520a(string2);
            int position = cursor.getPosition();
            if (this.f1657a.ai.length >= position + 1) {
                switch (this.f1657a.ai[position]) {
                    case VideoSize.QCIF /*0*/:
                        this.f1657a.ah = this.f1657a.ag;
                        this.f1657a.ag = cursor.getString(cursor.getColumnIndex("new_syna_user"));
                        break;
                    case VideoSize.CIF /*1*/:
                        this.f1657a.ah = this.f1657a.ag;
                        this.f1657a.ag = cursor.getString(cursor.getColumnIndex("new_syna_user"));
                        i = 0;
                        break;
                    default:
                        this.f1657a.ah = this.f1657a.ag;
                        newSunContactsListFragment.f1655f.setBackgroundColor(this.f1657a.m91h().getResources().getColor(C0282R.color.transparent));
                        int i2 = position == 0 ? 0 : !this.f1657a.ag.equalsIgnoreCase(this.f1657a.ah) ? 1 : 0;
                        if (i2 != 0) {
                            int[] b = this.f1657a.ai;
                            if (i2 != 0) {
                                i = 0;
                            }
                            b[position] = i;
                        }
                        i = i2;
                        break;
                }
                this.f1657a.ag = cursor.getString(cursor.getColumnIndex("new_syna_user"));
                newSunContactsListFragment.f1653d.setVisibility(8);
                if (this.f1657a.ag.equals("true")) {
                    newSunContactsListFragment.f1655f.setBackgroundColor(this.f1657a.m91h().getResources().getColor(2131230806));
                    newSunContactsListFragment.f1653d.setVisibility(0);
                }
                if (i != 0) {
                    newSunContactsListFragment.f1654e.setVisibility(8);
                } else {
                    newSunContactsListFragment.f1654e.setVisibility(8);
                }
                if (a == -1) {
                    newSunContactsListFragment.f1650a.setText(string2);
                    if (TextUtils.isEmpty(this.f1657a.f1662Z)) {
                        newSunContactsListFragment.f1651b.setVisibility(8);
                    } else {
                        newSunContactsListFragment.f1651b.setVisibility(0);
                    }
                } else {
                    CharSequence spannableString = new SpannableString(string2);
                    spannableString.setSpan(this.f1660d, a, this.f1657a.f1662Z.length() + a, 0);
                    newSunContactsListFragment.f1650a.setText(spannableString);
                    newSunContactsListFragment.f1651b.setVisibility(8);
                }
                this.f1657a.f1661Y.m2484a(string, newSunContactsListFragment.f1652c);
            }
        }

        public void changeCursor(Cursor cursor) {
            super.changeCursor(cursor);
            this.f1657a.ai = cursor == null ? null : new int[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                this.f1657a.ai[i] = -1;
            }
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public int getPositionForSection(int i) {
            return getCursor() == null ? 0 : this.f1659c.getPositionForSection(i);
        }

        public int getSectionForPosition(int i) {
            return getCursor() == null ? 0 : this.f1659c.getSectionForPosition(i);
        }

        public Object[] getSections() {
            return this.f1659c.getSections();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f1658b.inflate(2130903099, viewGroup, false);
            this.f1657a.ai = cursor == null ? null : new int[cursor.getCount()];
            for (int i = 0; i < cursor.getCount(); i++) {
                this.f1657a.ai[i] = -1;
            }
            NewSunContactsListFragment newSunContactsListFragment = new NewSunContactsListFragment();
            newSunContactsListFragment.f1650a = (TextView) inflate.findViewById(16908308);
            newSunContactsListFragment.f1651b = (TextView) inflate.findViewById(16908309);
            newSunContactsListFragment.f1652c = (ImageView) inflate.findViewById(16908294);
            newSunContactsListFragment.f1653d = (ImageView) inflate.findViewById(2131427573);
            newSunContactsListFragment.f1654e = inflate.findViewById(2131427572);
            newSunContactsListFragment.f1655f = inflate;
            inflate.setTag(newSunContactsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            this.f1659c.setCursor(cursor);
            return super.swapCursor(cursor);
        }
    }

    public NewSunContactsListFragment() {
        this.ab = 0;
        this.ag = null;
        this.ah = null;
    }

    public NewSunContactsListFragment(OnContactsInteractionListener onContactsInteractionListener) {
        this.ab = 0;
        this.ag = null;
        this.ah = null;
        this.aa = onContactsInteractionListener;
    }

    private void m2521E() {
        this.af = (PullToRefreshListView) this.ae.findViewById(2131427569);
        this.af.setOnRefreshListener(new NewSunContactsListFragment(this));
    }

    private void m2522F() {
        this.aa.m2490F();
        m2538a().clearChoices();
    }

    private int m2523G() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m2525a(String str, int i) {
        AssetFileDescriptor openAssetFileDescriptor;
        FileNotFoundException e;
        Throwable th;
        Bitmap bitmap = null;
        if (m97k() && m91h() != null) {
            try {
                Uri parse = HasVersion.m4098a() ? Uri.parse(str) : str.contains(FileManager.m4436a()) ? Uri.parse(str) : Uri.withAppendedPath(Uri.withAppendedPath(Contacts.CONTENT_URI, str), "photo");
                openAssetFileDescriptor = m91h().getContentResolver().openAssetFileDescriptor(parse, AckRequest.ELEMENT);
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
                        Log.d("SunContactsListFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
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
                Log.d("SunContactsListFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
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

    public void m2535D() {
        try {
            if (this.af != null) {
                this.af.m2811q();
            }
            if (m97k()) {
                for (int i = 0; i < this.ai.length; i++) {
                    this.ai[i] = -1;
                }
                m100n().m146b(2, null, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Loader<Cursor> m2536a(int i, Bundle bundle) {
        if (i != 2) {
            return null;
        }
        return SynaContacts.m5006b(m91h(), this.f1662Z == null ? DatabaseContentProvider.m4927a(m91h()) : Uri.withAppendedPath(DatabaseContentProvider.m4935i(m91h()), Uri.encode(this.f1662Z)));
    }

    public View m2537a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ae = layoutInflater.inflate(2130903097, viewGroup, false);
        m2521E();
        return this.ae;
    }

    public ListView m2538a() {
        return (ListView) this.af.m2805k();
    }

    public void m2539a(Bundle bundle) {
        super.m61a(bundle);
        this.ad = m93i().getBoolean(2131165250);
        m76c(true);
        this.f1663i = new NewSunContactsListFragment(this, m91h());
        if (bundle != null) {
            this.f1662Z = bundle.getString(UserSearch.ELEMENT);
            this.ab = bundle.getInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", 0);
        }
        this.f1661Y = new NewSunContactsListFragment(this, m91h(), m2523G());
        this.f1661Y.m2482a(2130838061);
        this.f1661Y.m2483a(m91h().getFragmentManager(), 0.1f);
    }

    public void m2540a(Loader<Cursor> loader) {
        if (loader.m479k() == 2) {
            this.f1663i.swapCursor(null);
        }
    }

    public void m2541a(Loader<Cursor> loader, Cursor cursor) {
        if (loader.m479k() == 2) {
            this.f1663i.swapCursor(cursor);
            if (this.ad && !TextUtils.isEmpty(this.f1662Z) && this.ac) {
                if (cursor == null || !cursor.moveToPosition(this.ab)) {
                    m2522F();
                } else {
                    this.aa.m2493a(Uri.withAppendedPath(Contacts.CONTENT_URI, String.valueOf(cursor.getLong(0))), true);
                    m2538a().setItemChecked(this.ab, true);
                }
                this.ab = 0;
                this.ac = false;
            }
        }
    }

    public void m2543a(String str) {
        if (this.f1662Z != null || str != null) {
            if (this.f1662Z == null || !this.f1662Z.equals(str)) {
                this.f1662Z = str;
                this.ac = true;
                m100n().m146b(2, null, this);
            }
        }
    }

    public void m2544d() {
        super.m79d();
    }

    public void m2545d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f1663i);
        m2538a().setOnItemClickListener(this);
        m2538a().setOnScrollListener(new NewSunContactsListFragment(this));
        m2538a().setFooterDividersEnabled(false);
        m2538a().setDividerHeight(1);
        m2538a().addFooterView(new TextView(m91h()));
        if (this.ad) {
            m2538a().setChoiceMode(1);
        }
        if (this.ab == 0) {
            m100n().m144a(2, null, this);
        }
        m2538a().setOnTouchListener(new NewSunContactsListFragment(this));
    }

    public void m2546e(Bundle bundle) {
        super.m85e(bundle);
        if (!TextUtils.isEmpty(this.f1662Z)) {
            bundle.putString(UserSearch.ELEMENT, this.f1662Z);
            bundle.putInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", m2538a().getCheckedItemPosition());
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = this.f1663i.getCursor();
        cursor.moveToPosition(i - 1);
        this.aa.m2493a(Uri.parse(DatabaseContentProvider.m4927a(m91h()) + "/" + cursor.getLong(0)), true);
        if (this.ad) {
            m2538a().setItemChecked(i, true);
        }
    }

    public void m2547p() {
        super.m102p();
        if (this.ai != null) {
            for (int i = 0; i < this.ai.length; i++) {
                this.ai[i] = -1;
            }
            m100n().m146b(2, null, this);
        }
    }

    public void m2548q() {
        super.m103q();
        this.f1661Y.m2486b(false);
    }
}
