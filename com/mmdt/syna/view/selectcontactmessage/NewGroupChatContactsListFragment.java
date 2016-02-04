package com.mmdt.syna.view.selectcontactmessage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.mmdt.syna.view.contactprofile.ContactProfileActivity;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.p041a.HasVersion;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smackx.search.UserSearch;

/* renamed from: com.mmdt.syna.view.selectcontactmessage.k */
public class NewGroupChatContactsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private NewGroupChatContactsListFragment f2948Y;
    private ImageLoader f2949Z;
    private String aa;
    private NewGroupChatContactsListFragment ab;
    private int ac;
    private boolean ad;
    private boolean ae;
    private View af;
    private HashMap<String, Boolean> ag;
    private String[] ah;
    private int f2950i;

    /* renamed from: com.mmdt.syna.view.selectcontactmessage.k.b */
    public interface NewGroupChatContactsListFragment {
        void m3750a(Uri uri);

        void m3751a(SelectedContactsDataHolder selectedContactsDataHolder);

        void m3752b(String str);

        void m3753g();
    }

    /* renamed from: com.mmdt.syna.view.selectcontactmessage.k.a */
    private class NewGroupChatContactsListFragment extends CursorAdapter implements SectionIndexer {
        final /* synthetic */ NewGroupChatContactsListFragment f2944a;
        private LayoutInflater f2945b;
        private AlphabetIndexer f2946c;
        private TextAppearanceSpan f2947d;

        /* renamed from: com.mmdt.syna.view.selectcontactmessage.k.a.a */
        private class NewGroupChatContactsListFragment {
            TextView f2939a;
            TextView f2940b;
            ImageView f2941c;
            CheckBox f2942d;
            final /* synthetic */ NewGroupChatContactsListFragment f2943e;

            private NewGroupChatContactsListFragment(NewGroupChatContactsListFragment newGroupChatContactsListFragment) {
                this.f2943e = newGroupChatContactsListFragment;
            }
        }

        public NewGroupChatContactsListFragment(NewGroupChatContactsListFragment newGroupChatContactsListFragment, Context context) {
            this.f2944a = newGroupChatContactsListFragment;
            super(context, null, 0);
            this.f2945b = LayoutInflater.from(context);
            this.f2946c = new AlphabetIndexer(null, 4, context.getString(2131493548));
            this.f2947d = new TextAppearanceSpan(newGroupChatContactsListFragment.m91h(), 2131558580);
        }

        private int m3782a(String str) {
            return !TextUtils.isEmpty(this.f2944a.aa) ? str.toLowerCase(Locale.getDefault()).indexOf(this.f2944a.aa.toLowerCase(Locale.getDefault())) : -1;
        }

        public void bindView(View view, Context context, Cursor cursor) {
            NewGroupChatContactsListFragment newGroupChatContactsListFragment = (NewGroupChatContactsListFragment) view.getTag();
            Object string = cursor.getString(3);
            String string2 = cursor.getString(1);
            String string3 = cursor.getString(2);
            int a = m3782a(string2);
            if (a == -1) {
                newGroupChatContactsListFragment.f2939a.setText(string2);
                if (TextUtils.isEmpty(this.f2944a.aa)) {
                    newGroupChatContactsListFragment.f2940b.setVisibility(8);
                } else {
                    newGroupChatContactsListFragment.f2940b.setVisibility(0);
                }
            } else {
                CharSequence spannableString = new SpannableString(string2);
                spannableString.setSpan(this.f2947d, a, this.f2944a.aa.length() + a, 0);
                newGroupChatContactsListFragment.f2939a.setText(spannableString);
                newGroupChatContactsListFragment.f2940b.setVisibility(8);
            }
            newGroupChatContactsListFragment.f2942d.setOnCheckedChangeListener(new NewGroupChatContactsListFragment(this, string3, string, string2));
            if (this.f2944a.ag.containsKey(string3) && ((Boolean) this.f2944a.ag.get(string3)).booleanValue()) {
                newGroupChatContactsListFragment.f2942d.setChecked(true);
            } else {
                newGroupChatContactsListFragment.f2942d.setChecked(false);
            }
            this.f2944a.f2949Z.m2484a(string, newGroupChatContactsListFragment.f2941c);
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public int getPositionForSection(int i) {
            return getCursor() == null ? 0 : this.f2946c.getPositionForSection(i);
        }

        public int getSectionForPosition(int i) {
            return (getCursor() == null || getCursor().getCount() == 0) ? 0 : this.f2946c.getSectionForPosition(i);
        }

        public Object[] getSections() {
            return this.f2946c.getSections();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2945b.inflate(2130903091, viewGroup, false);
            NewGroupChatContactsListFragment newGroupChatContactsListFragment = new NewGroupChatContactsListFragment();
            newGroupChatContactsListFragment.f2939a = (TextView) inflate.findViewById(16908308);
            newGroupChatContactsListFragment.f2940b = (TextView) inflate.findViewById(16908309);
            newGroupChatContactsListFragment.f2941c = (ImageView) inflate.findViewById(16908294);
            newGroupChatContactsListFragment.f2942d = (CheckBox) inflate.findViewById(2131427546);
            if (this.f2944a.f2950i == 2) {
                newGroupChatContactsListFragment.f2942d.setVisibility(8);
            }
            inflate.setTag(newGroupChatContactsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            this.f2946c.setCursor(cursor);
            return super.swapCursor(cursor);
        }
    }

    public NewGroupChatContactsListFragment() {
        this.f2950i = 0;
        this.ac = 0;
        this.ag = new HashMap();
    }

    public NewGroupChatContactsListFragment(int i) {
        this.f2950i = 0;
        this.ac = 0;
        this.ag = new HashMap();
        this.f2950i = i;
    }

    public NewGroupChatContactsListFragment(int i, String[] strArr) {
        this.f2950i = 0;
        this.ac = 0;
        this.ag = new HashMap();
        this.f2950i = i;
        this.ah = strArr;
    }

    private void m3784E() {
        this.ab.m3753g();
        m364a().clearChoices();
    }

    private int m3785F() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3787a(String str, int i) {
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
                        Log.d("NewGroupChatContactsListFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
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
                Log.d("NewGroupChatContactsListFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
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

    public ArrayList<String> m3793D() {
        ArrayList<String> arrayList = new ArrayList();
        for (Entry entry : this.ag.entrySet()) {
            if (entry.getValue() != null && ((Boolean) entry.getValue()).booleanValue()) {
                arrayList.add((String) entry.getKey());
            }
        }
        return arrayList;
    }

    public Loader<Cursor> m3794a(int i, Bundle bundle) {
        if (i != 2) {
            return null;
        }
        Uri a = this.aa == null ? DatabaseContentProvider.m4927a(m91h()) : Uri.withAppendedPath(DatabaseContentProvider.m4935i(m91h()), Uri.encode(this.aa));
        return this.f2950i == 2 ? SynaContacts.m5000a(m91h(), a, this.ah) : this.f2950i == 1 ? SynaContacts.m5007b(m91h(), a, this.ah) : SynaContacts.m4999a(m91h(), a);
    }

    public View m3795a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.af = layoutInflater.inflate(2130903170, viewGroup, false);
        return this.af;
    }

    public void m3796a(Activity activity) {
        super.m57a(activity);
        try {
            this.ab = (NewGroupChatContactsListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnContactsInteractionListener");
        }
    }

    public void m3797a(Bundle bundle) {
        super.m61a(bundle);
        this.ae = m93i().getBoolean(2131165250);
        m76c(true);
        this.f2948Y = new NewGroupChatContactsListFragment(this, m91h());
        if (bundle != null) {
            this.aa = bundle.getString(UserSearch.ELEMENT);
            this.ac = bundle.getInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", 0);
        }
        this.f2949Z = new NewGroupChatContactsListFragment(this, m91h(), m3785F());
        this.f2949Z.m2482a(2130838061);
        this.f2949Z.m2483a(m91h().getFragmentManager(), 0.1f);
    }

    public void m3798a(Loader<Cursor> loader) {
        if (loader.m479k() == 2) {
            this.f2948Y.swapCursor(null);
        }
    }

    public void m3799a(Loader<Cursor> loader, Cursor cursor) {
        if (loader.m479k() == 2) {
            this.f2948Y.swapCursor(cursor);
            if (this.ae && !TextUtils.isEmpty(this.aa) && this.ad) {
                if (cursor == null || !cursor.moveToPosition(this.ac)) {
                    m3784E();
                } else {
                    this.ab.m3750a(Uri.withAppendedPath(Contacts.CONTENT_URI, String.valueOf(cursor.getLong(0))));
                    m364a().setItemChecked(this.ac, true);
                }
                this.ac = 0;
                this.ad = false;
            }
        }
    }

    public void m3801a(String str) {
        if (this.aa != null || str != null) {
            if (this.aa == null || !this.aa.equals(str)) {
                this.aa = str;
                this.ad = true;
                m100n().m146b(2, null, this);
            }
        }
    }

    public void m3802d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f2948Y);
        m364a().setOnItemClickListener(this);
        m364a().setOnScrollListener(new NewGroupChatContactsListFragment(this));
        m364a().setFooterDividersEnabled(false);
        m364a().setDividerHeight(1);
        m364a().addFooterView(new TextView(m91h()));
        if (this.ae) {
            m364a().setChoiceMode(1);
        }
        if (this.ac == 0) {
            m100n().m144a(2, null, this);
        }
    }

    public void m3803e(Bundle bundle) {
        super.m85e(bundle);
        if (!TextUtils.isEmpty(this.aa)) {
            bundle.putString(UserSearch.ELEMENT, this.aa);
            bundle.putInt("com.example.android.suncontactslist.ui.SELECTED_ITEM", m364a().getCheckedItemPosition());
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = this.f2948Y.getCursor();
        cursor.moveToPosition(i);
        Uri parse = Uri.parse(DatabaseContentProvider.m4927a(m91h()) + "/" + SynaContacts.m5012c(m91h(), cursor.getString(2)));
        Intent intent = new Intent(m91h(), ContactProfileActivity.class);
        intent.setData(parse);
        intent.putExtra("com.mmdt.sipclient.view.contact.ContactProfileActivity.KEY_IS_SUN_SHOWING", true);
        m91h().startActivity(intent);
        m91h().overridePendingTransition(0, 0);
    }

    public void m3804q() {
        super.m103q();
        this.f2949Z.m2486b(false);
    }
}
