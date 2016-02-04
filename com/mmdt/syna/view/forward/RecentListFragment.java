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
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mmdt.syna.view.tools.TimeUtils;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import com.mmdt.syna.view.tools.p020a.p036c.TextLoader;
import com.mmdt.syna.view.tools.p038c.StickerManager;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.database.p065f.Messages;
import mobi.mmdt.p041a.DateAndTime;
import mobi.mmdt.p041a.HasVersion;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

@SuppressLint({"ValidFragment"})
/* renamed from: com.mmdt.syna.view.forward.l */
public class RecentListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private RecentListFragment f2473Y;
    private ImageLoader f2474Z;
    private View aa;
    public RecentListFragment f2475i;

    /* renamed from: com.mmdt.syna.view.forward.l.b */
    public interface RecentListFragment {
        void m3374b(String str, String str2, boolean z);
    }

    /* renamed from: com.mmdt.syna.view.forward.l.a */
    private class RecentListFragment extends CursorAdapter {
        final /* synthetic */ RecentListFragment f2469a;
        private LayoutInflater f2470b;
        private HashMap<String, Boolean> f2471c;
        private TextAppearanceSpan f2472d;

        /* renamed from: com.mmdt.syna.view.forward.l.a.a */
        private class RecentListFragment {
            TextView f2462a;
            TextView f2463b;
            TextView f2464c;
            ImageView f2465d;
            ImageView f2466e;
            RelativeLayout f2467f;
            final /* synthetic */ RecentListFragment f2468g;

            private RecentListFragment(RecentListFragment recentListFragment) {
                this.f2468g = recentListFragment;
            }
        }

        public RecentListFragment(RecentListFragment recentListFragment, Context context) {
            this.f2469a = recentListFragment;
            super(context, null, 0);
            this.f2471c = new HashMap();
            this.f2470b = LayoutInflater.from(context);
            this.f2472d = new TextAppearanceSpan(recentListFragment.m91h(), 2131558581);
        }

        public HashMap<String, Boolean> m3431a() {
            return this.f2471c;
        }

        public void m3432a(String str) {
            this.f2471c.remove(str);
            notifyDataSetChanged();
        }

        public void m3433a(String str, boolean z) {
            this.f2471c.put(str, Boolean.valueOf(z));
            notifyDataSetChanged();
        }

        public void m3434b() {
            this.f2471c = new HashMap();
            notifyDataSetChanged();
        }

        public void bindView(View view, Context context, Cursor cursor) {
            RecentListFragment recentListFragment = (RecentListFragment) view.getTag();
            CharSequence string = cursor.getString(cursor.getColumnIndex("party"));
            int i = cursor.getInt(cursor.getColumnIndex("type"));
            int i2 = cursor.getInt(cursor.getColumnIndex(Status.ELEMENT));
            long a = DateAndTime.m4085a(cursor.getLong(cursor.getColumnIndex("MAX_DATE")));
            Object blob = cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT));
            String string2 = cursor.getString(cursor.getColumnIndex("nick_name"));
            boolean equals = cursor.getString(cursor.getColumnIndex("is_group")).equals("true");
            if (equals) {
                Object string3 = cursor.getString(cursor.getColumnIndex("local_picture"));
            } else {
                String string4 = cursor.getString(cursor.getColumnIndex("local_avatar_address"));
            }
            String string5 = cursor.getString(cursor.getColumnIndex("phonenumber_message"));
            CharSequence string6 = cursor.getString(cursor.getColumnIndex("room_subject_group"));
            if (equals) {
                if (string6 == null || string6.length() <= 0) {
                    string6 = "Group";
                }
                recentListFragment.f2462a.setText(string6);
            } else if (string != null) {
                String str = "";
                string6 = string2 != null ? string2 : string;
                if (string.matches("[0-9]+")) {
                    if (string6 == null || string6.length() <= 0) {
                        string6 = string;
                    }
                    recentListFragment.f2462a.setText(string6);
                } else {
                    recentListFragment.f2462a.setText(string);
                    recentListFragment.f2463b.setVisibility(8);
                    LayoutParams layoutParams = (LayoutParams) recentListFragment.f2462a.getLayoutParams();
                    layoutParams.addRule(15);
                    recentListFragment.f2462a.setLayoutParams(layoutParams);
                }
            } else {
                return;
            }
            Object obj = null;
            if (blob.length < 33 && StickerManager.m4025a(new String(blob))) {
                obj = 1;
            }
            if (obj != null) {
                recentListFragment.f2463b.setVisibility(0);
                recentListFragment.f2463b.setText("(Sticker)");
            } else {
                recentListFragment.f2463b.setVisibility(0);
                if (equals) {
                    obj = new byte[0];
                    if (i == 0) {
                        try {
                            obj = "Me: ".getBytes(StringUtils.UTF8);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        obj = string2 != null ? new StringBuilder(String.valueOf(string2)).append(": ").toString().getBytes(StringUtils.UTF8) : new StringBuilder(String.valueOf(string5)).append(": ").toString().getBytes(StringUtils.UTF8);
                    }
                    Object obj2 = new byte[(obj.length + blob.length)];
                    System.arraycopy(obj, 0, obj2, 0, obj.length);
                    System.arraycopy(blob, 0, obj2, obj.length, blob.length);
                    TextLoader.m4005a(context).m4016a(new StringBuilder(String.valueOf(string)).append("_").append(a).toString(), obj2, recentListFragment.f2463b, true, false);
                } else {
                    TextLoader.m4005a(context).m4016a(new StringBuilder(String.valueOf(string)).append("_").append(a).toString(), blob, recentListFragment.f2463b, true, false);
                }
            }
            if (i2 == 3) {
                recentListFragment.f2466e.setVisibility(0);
            } else {
                recentListFragment.f2466e.setVisibility(8);
            }
            recentListFragment.f2464c.setText(TimeUtils.m4028a(context, a));
            if (string3 != null && !string3.equals("null")) {
                this.f2469a.f2474Z.m2484a(string3, recentListFragment.f2465d);
            } else if (equals) {
                recentListFragment.f2465d.setImageResource(2130838071);
            } else {
                this.f2469a.f2474Z.m2484a(null, recentListFragment.f2465d);
            }
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2470b.inflate(2130903113, viewGroup, false);
            RecentListFragment recentListFragment = new RecentListFragment();
            recentListFragment.f2462a = (TextView) inflate.findViewById(2131427613);
            recentListFragment.f2463b = (TextView) inflate.findViewById(2131427615);
            recentListFragment.f2464c = (TextView) inflate.findViewById(2131427614);
            recentListFragment.f2467f = (RelativeLayout) inflate.findViewById(2131427609);
            recentListFragment.f2465d = (ImageView) inflate.findViewById(2131427611);
            recentListFragment.f2466e = (ImageView) inflate.findViewById(2131427616);
            inflate.setTag(recentListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            return super.swapCursor(cursor);
        }
    }

    private void m3435D() {
    }

    private int m3436E() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3438a(String str, int i) {
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

    public Loader<Cursor> m3441a(int i, Bundle bundle) {
        return Messages.m5101a(m91h(), 10);
    }

    public View m3442a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.aa = layoutInflater.inflate(2130903115, viewGroup, false);
        m3435D();
        return this.aa;
    }

    public void m3443a(Activity activity) {
        super.m57a(activity);
        try {
            this.f2475i = (RecentListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnContactsInteractionListener");
        }
    }

    public void m3444a(Bundle bundle) {
        super.m61a(bundle);
        this.f2473Y = new RecentListFragment(this, m91h());
        this.f2474Z = new RecentListFragment(this, m91h(), m3436E());
        this.f2474Z.m2482a(2130838061);
        this.f2474Z.m2483a(m91h().getFragmentManager(), 0.1f);
        TextLoader.m4005a(m91h()).m4014a(m91h().getFragmentManager(), 0.5f);
    }

    public void m3445a(Loader<Cursor> loader) {
        this.f2473Y.swapCursor(null);
    }

    public void m3446a(Loader<Cursor> loader, Cursor cursor) {
        this.f2473Y.swapCursor(cursor);
    }

    public boolean m3448b(MenuItem menuItem) {
        return true;
    }

    public void m3449d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f2473Y);
        m364a().setOnItemClickListener(this);
        m364a().setChoiceMode(3);
        m364a().setOnScrollListener(new RecentListFragment(this));
        m364a().setDividerHeight(1);
        m100n().m144a(0, null, this);
        m364a().setMultiChoiceModeListener(new RecentListFragment(this));
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
        Cursor cursor = this.f2473Y.getCursor();
        cursor.moveToPosition(i);
        boolean equals = cursor.getString(cursor.getColumnIndex("is_group")).equals("true");
        this.f2475i.m3374b(cursor.getString(cursor.getColumnIndex("party")), equals ? cursor.getString(cursor.getColumnIndex("room_subject_group")) : cursor.getString(cursor.getColumnIndex("nick_name")), equals);
    }

    public void m3450q() {
        super.m103q();
        this.f2474Z.m2486b(false);
    }
}
