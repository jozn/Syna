package com.mmdt.syna.view.forward;

import android.content.Context;
import android.graphics.Bitmap;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;

/* renamed from: com.mmdt.syna.view.forward.c */
class ForwardMessageSunContactsListFragment extends ImageLoader {
    final /* synthetic */ ForwardMessageSunContactsListFragment f2433a;

    ForwardMessageSunContactsListFragment(ForwardMessageSunContactsListFragment forwardMessageSunContactsListFragment, Context context, int i) {
        this.f2433a = forwardMessageSunContactsListFragment;
        super(context, i);
    }

    protected Bitmap m3407a(Object obj) {
        return this.f2433a.m3394a((String) obj, m2480a());
    }
}
r;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AlphabetIndexer;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.p041a.HasVersion;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smackx.search.UserSearch;

/* renamed from: com.mmdt.syna.view.forward.b */
public class ForwardMessageSunContactsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private ImageLoader f2430Y;
    private String f2431Z;
    private ForwardMessageSunContactsListFragment aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private View ae;
    private ForwardMessageSunContactsListFragment f2432i;

    /* renamed from: com.mmdt.syna.view.forward.b.b */
    public interface ForwardMessageSunContactsListFragment {
        void m3371a(Uri uri);

        void m3372f();
    }

    /* renamed from: com.mmdt.syna.view.forward.b.a */
    private class ForwardMessageSunContactsListFragment extends CursorAdapter implements SectionIndexer {
        final /* synthetic */ ForwardMessageSunContactsListFragment f2426a;
        private LayoutInflater f2427b;
        private AlphabetIndexer f2428c;
        private TextAppearanceSpan f2429d;

        /* renamed from: com.mmdt.syna.view.forward.b.a.a */
        private class ForwardMessageSunContactsListFragment {
            TextView f2421a;
            TextView f2422b;
            ImageView f2423c;
            ImageView f2424d;
            final /* synthetic */ ForwardMessageSunContactsListFragment f2425e;

            private ForwardMessageSunContactsListFragment(ForwardMessageSunContactsListFragment forwardMessageSunContactsListFragment) {
                this.f2425e = forwardMessageSunContactsListFragment;
            }
        }

        public ForwardMessageSunContactsListFragment(ForwardMessageSunContactsListFragment forwardMessageSunContactsListFragment, Context context) {
            this.f2426a = forwardMessageSunContactsListFragment;
            super(context, null, 0);
            this.f2427b = LayoutInflater.from(context);
            this.f2428c = new AlphabetIndexer(null, 4, context.getString(2131493548));
            this.f2429d = new TextAppearanceSpan(forwardMessageSunContactsListFragment.m91h(), 2131558580);
        }

        private int m3390a(String str) {
            return !TextUtils.isEmpty(this.f2426a.f2431Z) ? str.toLowerCase(Locale.getDefault()).indexOf(this.f2426a.f2431Z.toLowerCase(Locale.getDefault())) : -1;
        }

        public void bindView(View view, Context context, Cursor cursor) {
            ForwardMessageSunContactsListFragment forwardMessageSunContactsListFragment = (ForwardMessageSunContactsListFragment) view.getTag();
            Object string = cursor.getString(3);
            CharSequence string2 = cursor.getString(1);
            int a = m3390a(string2);
            if (a == -1) {
                forwardMessageSunContactsListFragment.f2421a.setText(string2);
                if (TextUtils.isEmpty(this.f2426a.f2431Z)) {
                    forwardMessageSunContactsListFragment.f2422b.setVisibility(8);
                } else {
                    forwardMessageSunContactsListFragment.f2422b.setVisibility(0);
                }
            } else {
                CharSequence spannableString = new SpannableString(string2);
                spannableString.setSpan(this.f2429d, a, this.f2426a.f2431Z.length() + a, 0);
                forwardMessageSunContactsListFragment.f2421a.setText(spannableString);
                forwardMessageSunContactsListFragment.f2422b.setVisibility(8);
            }
            this.f2426a.f2430Y.m2484a(string, forwardMessageSunContactsListFragment.f2423c);
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public int getPositionForSection(int i) {
            return getCursor() == null ? 0 : this.f2428c.getPositionForSection(i);
        }

        public int getSectionForPosition(int i) {
            return getCursor() == null ? 0 : this.f2428c.getSectionForPosition(i);
        }

        public Object[] getSections() {
            return this.f2428c.getSections();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2427b.inflate(2130903098, viewGroup, false);
            ForwardMessageSunContactsListFragment forwardMessageSunContactsListFragment = new ForwardMessageSunContactsListFragment();
            forwardMessageSunContactsListFragment.f2421a = (TextView) inflate.findViewById(16908308);
            forwardMessageSunContactsListFragment.f2422b = (TextView) inflate.findViewById(16908309);
            forwardMessageSunContactsListFragment.f2423c = (ImageView) inflate.findViewById(16908294);
            forwardMessageSunContactsListFragment.f2424d = (ImageView) inflate.findViewById(2131427570);
            inflate.setTag(forwardMessageSunContactsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            this.f2428c.setCursor(cursor);
            return super.swapCursor(cursor);
        }
    }

    public ForwardMessageSunContactsListFragment() {
        this.ab = 0;
    }

    private void m3391D() {
        this.aa.m3372f();
        m364a().clearChoices();
    }

    private int m3392E() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3394a(String str, int i) {
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

    public Loader<Cursor> m3397a(int i, Bundle bundle) {
        if (i != 2) {
            return null;
        }
        return SynaContacts.m4999a(m91h(), this.f2431Z == null ? DatabaseContentProvider.m4927a(m91h()) : Uri.withAppendedPath(DatabaseContentProvider.m4935i(m91h()), Uri.encode(this.f2431Z)));
    }

    public View m3398a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ae = layoutInflater.inflate(2130903170, viewGroup, false);
        return this.ae;
    }

    public void m3399a(Activity activity) {
        super.m57a(activity);
        try {
            this.aa = (ForwardMessageSunContactsListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnContactsInteractionListener");
        }
    }

    public void m3400a(Bundle bundle) {
        super.m61a(bundle);
        this.ad = m93i().getBoolean(2131165250);
        m76c(true);
        this.f2432i = new ForwardMessageSunContactsListFragment(this, m91h());
        if (bundle != null) {
            this.f2431Z = bundle.getString(UserSearch.ELEMENT);
            this.ab = bundle.getInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", 0);
        }
        this.f2430Y = new ForwardMessageSunContactsListFragment(this, m91h(), m3392E());
        this.f2430Y.m2482a(2130838061);
        this.f2430Y.m2483a(m91h().getFragmentManager(), 0.1f);
    }

    public void m3401a(Loader<Cursor> loader) {
        if (loader.m479k() == 2) {
            this.f2432i.swapCursor(null);
        }
    }

    public void m3402a(Loader<Cursor> loader, Cursor cursor) {
        if (loader.m479k() == 2) {
            this.f2432i.swapCursor(cursor);
            if (this.ad && !TextUtils.isEmpty(this.f2431Z) && this.ac) {
                if (cursor == null || !cursor.moveToPosition(this.ab)) {
                    m3391D();
                } else {
                    this.aa.m3371a(Uri.withAppendedPath(Contacts.CONTENT_URI, String.valueOf(cursor.getLong(0))));
                    m364a().setItemChecked(this.ab, true);
                }
                this.ab = 0;
                this.ac = false;
            }
        }
    }

    public void m3404d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f2432i);
        m364a().setOnItemClickListener(this);
        m364a().setOnScrollListener(new ForwardMessageSunContactsListFragment(this));
        m364a().setFooterDividersEnabled(false);
        m364a().setDividerHeight(1);
        m364a().addFooterView(new TextView(m91h()));
        if (this.ad) {
            m364a().setChoiceMode(1);
        }
        if (this.ab == 0) {
            m100n().m144a(2, null, this);
        }
    }

    public void m3405e(Bundle bundle) {
        super.m85e(bundle);
        if (!TextUtils.isEmpty(this.f2431Z)) {
            bundle.putString(UserSearch.ELEMENT, this.f2431Z);
            bundle.putInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", m364a().getCheckedItemPosition());
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = this.f2432i.getCursor();
        cursor.moveToPosition(i);
        this.aa.m3371a(Uri.parse(DatabaseContentProvider.m4927a(m91h()) + "/" + cursor.getLong(0)));
        if (this.ad) {
            m364a().setItemChecked(i, true);
        }
    }

    public void m3406q() {
        super.m103q();
        this.f2430Y.m2486b(false);
    }
}
