package com.mmdt.syna.view.selectcontactmessage;

import android.app.Activity;
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

/* renamed from: com.mmdt.syna.view.selectcontactmessage.r */
public class NewMessageSunContactsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private ImageLoader f2969Y;
    private String f2970Z;
    private NewMessageSunContactsListFragment aa;
    private int ab;
    private boolean ac;
    private boolean ad;
    private View ae;
    private NewMessageSunContactsListFragment f2971i;

    /* renamed from: com.mmdt.syna.view.selectcontactmessage.r.b */
    public interface NewMessageSunContactsListFragment {
        void m3771a(Uri uri);

        void m3772g();
    }

    /* renamed from: com.mmdt.syna.view.selectcontactmessage.r.a */
    private class NewMessageSunContactsListFragment extends CursorAdapter implements SectionIndexer {
        final /* synthetic */ NewMessageSunContactsListFragment f2965a;
        private LayoutInflater f2966b;
        private AlphabetIndexer f2967c;
        private TextAppearanceSpan f2968d;

        /* renamed from: com.mmdt.syna.view.selectcontactmessage.r.a.a */
        private class NewMessageSunContactsListFragment {
            TextView f2960a;
            TextView f2961b;
            ImageView f2962c;
            ImageView f2963d;
            final /* synthetic */ NewMessageSunContactsListFragment f2964e;

            private NewMessageSunContactsListFragment(NewMessageSunContactsListFragment newMessageSunContactsListFragment) {
                this.f2964e = newMessageSunContactsListFragment;
            }
        }

        public NewMessageSunContactsListFragment(NewMessageSunContactsListFragment newMessageSunContactsListFragment, Context context) {
            this.f2965a = newMessageSunContactsListFragment;
            super(context, null, 0);
            this.f2966b = LayoutInflater.from(context);
            this.f2967c = new AlphabetIndexer(null, 4, context.getString(2131493548));
            this.f2968d = new TextAppearanceSpan(newMessageSunContactsListFragment.m91h(), 2131558580);
        }

        private int m3806a(String str) {
            return !TextUtils.isEmpty(this.f2965a.f2970Z) ? str.toLowerCase(Locale.getDefault()).indexOf(this.f2965a.f2970Z.toLowerCase(Locale.getDefault())) : -1;
        }

        public void bindView(View view, Context context, Cursor cursor) {
            NewMessageSunContactsListFragment newMessageSunContactsListFragment = (NewMessageSunContactsListFragment) view.getTag();
            Object string = cursor.getString(3);
            CharSequence string2 = cursor.getString(1);
            int a = m3806a(string2);
            if (a == -1) {
                newMessageSunContactsListFragment.f2960a.setText(string2);
                if (TextUtils.isEmpty(this.f2965a.f2970Z)) {
                    newMessageSunContactsListFragment.f2961b.setVisibility(8);
                } else {
                    newMessageSunContactsListFragment.f2961b.setVisibility(0);
                }
            } else {
                CharSequence spannableString = new SpannableString(string2);
                spannableString.setSpan(this.f2968d, a, this.f2965a.f2970Z.length() + a, 0);
                newMessageSunContactsListFragment.f2960a.setText(spannableString);
                newMessageSunContactsListFragment.f2961b.setVisibility(8);
            }
            this.f2965a.f2969Y.m2484a(string, newMessageSunContactsListFragment.f2962c);
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public int getPositionForSection(int i) {
            return getCursor() == null ? 0 : this.f2967c.getPositionForSection(i);
        }

        public int getSectionForPosition(int i) {
            return (getCursor() == null || getCursor().getCount() == 0) ? 0 : this.f2967c.getSectionForPosition(i);
        }

        public Object[] getSections() {
            return this.f2967c.getSections();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2966b.inflate(2130903098, viewGroup, false);
            NewMessageSunContactsListFragment newMessageSunContactsListFragment = new NewMessageSunContactsListFragment();
            newMessageSunContactsListFragment.f2960a = (TextView) inflate.findViewById(16908308);
            newMessageSunContactsListFragment.f2961b = (TextView) inflate.findViewById(16908309);
            newMessageSunContactsListFragment.f2962c = (ImageView) inflate.findViewById(16908294);
            newMessageSunContactsListFragment.f2963d = (ImageView) inflate.findViewById(2131427570);
            inflate.setTag(newMessageSunContactsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            this.f2967c.setCursor(cursor);
            return super.swapCursor(cursor);
        }
    }

    public NewMessageSunContactsListFragment() {
        this.ab = 0;
    }

    private void m3807D() {
        this.aa.m3772g();
        m364a().clearChoices();
    }

    private int m3808E() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3810a(String str, int i) {
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

    public Loader<Cursor> m3813a(int i, Bundle bundle) {
        if (i != 2) {
            return null;
        }
        return SynaContacts.m4999a(m91h(), this.f2970Z == null ? DatabaseContentProvider.m4927a(m91h()) : Uri.withAppendedPath(DatabaseContentProvider.m4935i(m91h()), Uri.encode(this.f2970Z)));
    }

    public View m3814a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ae = layoutInflater.inflate(2130903170, viewGroup, false);
        return this.ae;
    }

    public void m3815a(Activity activity) {
        super.m57a(activity);
        try {
            this.aa = (NewMessageSunContactsListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnContactsInteractionListener");
        }
    }

    public void m3816a(Bundle bundle) {
        super.m61a(bundle);
        this.ad = m93i().getBoolean(2131165250);
        m76c(true);
        this.f2971i = new NewMessageSunContactsListFragment(this, m91h());
        if (bundle != null) {
            this.f2970Z = bundle.getString(UserSearch.ELEMENT);
            this.ab = bundle.getInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", 0);
        }
        this.f2969Y = new NewMessageSunContactsListFragment(this, m91h(), m3808E());
        this.f2969Y.m2482a(2130838061);
        this.f2969Y.m2483a(m91h().getFragmentManager(), 0.1f);
    }

    public void m3817a(Loader<Cursor> loader) {
        if (loader.m479k() == 2) {
            this.f2971i.swapCursor(null);
        }
    }

    public void m3818a(Loader<Cursor> loader, Cursor cursor) {
        if (loader.m479k() == 2) {
            this.f2971i.swapCursor(cursor);
            if (this.ad && !TextUtils.isEmpty(this.f2970Z) && this.ac) {
                if (cursor == null || !cursor.moveToPosition(this.ab)) {
                    m3807D();
                } else {
                    this.aa.m3771a(Uri.withAppendedPath(Contacts.CONTENT_URI, String.valueOf(cursor.getLong(0))));
                    m364a().setItemChecked(this.ab, true);
                }
                this.ab = 0;
                this.ac = false;
            }
        }
    }

    public void m3820a(String str) {
        this.f2970Z = str;
    }

    public void m3821b(String str) {
        if (this.f2970Z != null || str != null) {
            if (this.f2970Z == null || !this.f2970Z.equals(str)) {
                this.f2970Z = str;
                this.ac = true;
                m100n().m146b(2, null, this);
            }
        }
    }

    public void m3822d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f2971i);
        m364a().setOnItemClickListener(this);
        m364a().setOnScrollListener(new NewMessageSunContactsListFragment(this));
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

    public void m3823e(Bundle bundle) {
        super.m85e(bundle);
        if (!TextUtils.isEmpty(this.f2970Z)) {
            bundle.putString(UserSearch.ELEMENT, this.f2970Z);
            bundle.putInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", m364a().getCheckedItemPosition());
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = this.f2971i.getCursor();
        cursor.moveToPosition(i);
        this.aa.m3771a(Uri.parse(DatabaseContentProvider.m4927a(m91h()) + "/" + cursor.getLong(0)));
        if (this.ad) {
            m364a().setItemChecked(i, true);
        }
    }

    public void m3824q() {
        super.m103q();
        this.f2969Y.m2486b(false);
    }
}
