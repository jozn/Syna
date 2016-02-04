package com.mmdt.syna.view.forward;

import android.annotation.SuppressLint;
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
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import com.mmdt.syna.view.tools.p020a.p036c.TextLoader;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.database.p063d.Groups;
import mobi.mmdt.p041a.HasVersion;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;

@SuppressLint({"ValidFragment"})
/* renamed from: com.mmdt.syna.view.forward.e */
public class GroupsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private GroupsListFragment f2446Y;
    private ImageLoader f2447Z;
    private View aa;
    public GroupsListFragment f2448i;

    /* renamed from: com.mmdt.syna.view.forward.e.b */
    public interface GroupsListFragment {
        void m3373a(String str, String str2, boolean z);
    }

    /* renamed from: com.mmdt.syna.view.forward.e.a */
    private class GroupsListFragment extends CursorAdapter {
        final /* synthetic */ GroupsListFragment f2442a;
        private LayoutInflater f2443b;
        private HashMap<String, Boolean> f2444c;
        private TextAppearanceSpan f2445d;

        /* renamed from: com.mmdt.syna.view.forward.e.a.a */
        private class GroupsListFragment {
            TextView f2435a;
            TextView f2436b;
            TextView f2437c;
            ImageView f2438d;
            ImageView f2439e;
            RelativeLayout f2440f;
            final /* synthetic */ GroupsListFragment f2441g;

            private GroupsListFragment(GroupsListFragment groupsListFragment) {
                this.f2441g = groupsListFragment;
            }
        }

        public GroupsListFragment(GroupsListFragment groupsListFragment, Context context) {
            this.f2442a = groupsListFragment;
            super(context, null, 0);
            this.f2444c = new HashMap();
            this.f2443b = LayoutInflater.from(context);
            this.f2445d = new TextAppearanceSpan(groupsListFragment.m91h(), 2131558581);
        }

        public HashMap<String, Boolean> m3408a() {
            return this.f2444c;
        }

        public void m3409a(String str) {
            this.f2444c.remove(str);
            notifyDataSetChanged();
        }

        public void m3410a(String str, boolean z) {
            this.f2444c.put(str, Boolean.valueOf(z));
            notifyDataSetChanged();
        }

        public void m3411b() {
            this.f2444c = new HashMap();
            notifyDataSetChanged();
        }

        public void bindView(View view, Context context, Cursor cursor) {
            GroupsListFragment groupsListFragment = (GroupsListFragment) view.getTag();
            Object string = cursor.getString(cursor.getColumnIndex("local_picture"));
            CharSequence string2 = cursor.getString(cursor.getColumnIndex("room_subject_group"));
            if (string2 == null || string2.length() <= 0) {
                string2 = "Group";
            }
            groupsListFragment.f2435a.setText(string2);
            groupsListFragment.f2436b.setVisibility(0);
            if (string == null || string.equals("null")) {
                groupsListFragment.f2438d.setImageResource(2130838071);
            } else {
                this.f2442a.f2447Z.m2484a(string, groupsListFragment.f2438d);
            }
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2443b.inflate(2130903113, viewGroup, false);
            GroupsListFragment groupsListFragment = new GroupsListFragment();
            groupsListFragment.f2435a = (TextView) inflate.findViewById(2131427613);
            groupsListFragment.f2436b = (TextView) inflate.findViewById(2131427615);
            groupsListFragment.f2437c = (TextView) inflate.findViewById(2131427614);
            groupsListFragment.f2440f = (RelativeLayout) inflate.findViewById(2131427609);
            groupsListFragment.f2438d = (ImageView) inflate.findViewById(2131427611);
            groupsListFragment.f2439e = (ImageView) inflate.findViewById(2131427616);
            inflate.setTag(groupsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            return super.swapCursor(cursor);
        }
    }

    private void m3412D() {
    }

    private int m3413E() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3415a(String str, int i) {
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

    public Loader<Cursor> m3418a(int i, Bundle bundle) {
        return Groups.m5051b(m91h(), DatabaseContentProvider.m4931e(m91h()));
    }

    public View m3419a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.aa = layoutInflater.inflate(2130903115, viewGroup, false);
        m3412D();
        return this.aa;
    }

    public void m3420a(Activity activity) {
        super.m57a(activity);
        try {
            this.f2448i = (GroupsListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnContactsInteractionListener");
        }
    }

    public void m3421a(Bundle bundle) {
        super.m61a(bundle);
        this.f2446Y = new GroupsListFragment(this, m91h());
        this.f2447Z = new GroupsListFragment(this, m91h(), m3413E());
        this.f2447Z.m2482a(2130838061);
        this.f2447Z.m2483a(m91h().getFragmentManager(), 0.1f);
        TextLoader.m4005a(m91h()).m4014a(m91h().getFragmentManager(), 0.5f);
    }

    public void m3422a(Loader<Cursor> loader) {
        this.f2446Y.swapCursor(null);
    }

    public void m3423a(Loader<Cursor> loader, Cursor cursor) {
        this.f2446Y.swapCursor(cursor);
    }

    public boolean m3425b(MenuItem menuItem) {
        return true;
    }

    public void m3426d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f2446Y);
        m364a().setOnItemClickListener(this);
        m364a().setChoiceMode(3);
        m364a().setOnScrollListener(new GroupsListFragment(this));
        m364a().setDividerHeight(1);
        m100n().m144a(0, null, this);
        m364a().setMultiChoiceModeListener(new GroupsListFragment(this));
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        if (view.getId() == 16908298) {
            Cursor cursor = (Cursor) m368b().getItem(((AdapterContextMenuInfo) contextMenuInfo).position);
            CharSequence string = cursor.getString(cursor.getColumnIndex("party"));
            ContactShow a = SynaContacts.m5002a(m91h(), (String) string, false);
            String str = "";
            if (!(a == null || a.m5062c() == null)) {
                string = a.m5062c();
            }
            contextMenu.setHeaderTitle(string);
            String[] stringArray = m93i().getStringArray(2131623943);
            contextMenu.add(0, 1, 0, stringArray[0]);
            contextMenu.add(0, 2, 1, stringArray[1]);
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Cursor cursor = this.f2446Y.getCursor();
        cursor.moveToPosition(i);
        this.f2448i.m3373a(cursor.getString(cursor.getColumnIndex("group_id")), cursor.getString(cursor.getColumnIndex("room_subject_group")), true);
    }

    public void m3427q() {
        super.m103q();
        this.f2447Z.m2486b(false);
    }
}
