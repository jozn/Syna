package com.mmdt.syna.view.conversation.publicchat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.mmdt.syna.view.conversation.conversationpage.activities.PublicChatConversationActivity;
import com.mmdt.syna.view.tools.TimeUtils;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import com.mmdt.syna.view.tools.p020a.p036c.TextLoader;
import com.mmdt.syna.view.tools.p038c.StickerManager;
import com.squareup.picasso.Picasso;
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
/* renamed from: com.mmdt.syna.view.conversation.publicchat.h */
public class PublicChatsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private ImageLoader f2332Y;
    private View f2333Z;
    private PublicChatsListFragment f2334i;

    /* renamed from: com.mmdt.syna.view.conversation.publicchat.h.a */
    private class PublicChatsListFragment extends CursorAdapter {
        final /* synthetic */ PublicChatsListFragment f2328a;
        private LayoutInflater f2329b;
        private HashMap<String, String> f2330c;
        private TextAppearanceSpan f2331d;

        /* renamed from: com.mmdt.syna.view.conversation.publicchat.h.a.a */
        private class PublicChatsListFragment {
            TextView f2319a;
            TextView f2320b;
            TextView f2321c;
            TextView f2322d;
            TextView f2323e;
            ImageView f2324f;
            ImageView f2325g;
            RelativeLayout f2326h;
            final /* synthetic */ PublicChatsListFragment f2327i;

            private PublicChatsListFragment(PublicChatsListFragment publicChatsListFragment) {
                this.f2327i = publicChatsListFragment;
            }
        }

        public PublicChatsListFragment(PublicChatsListFragment publicChatsListFragment, Context context) {
            this.f2328a = publicChatsListFragment;
            super(context, null, 0);
            this.f2330c = new HashMap();
            this.f2329b = LayoutInflater.from(context);
            this.f2331d = new TextAppearanceSpan(publicChatsListFragment.m91h(), 2131558581);
        }

        public HashMap<String, String> m3298a() {
            return this.f2330c;
        }

        public void m3299a(String str) {
            this.f2330c.remove(str);
            notifyDataSetChanged();
        }

        public void m3300a(String str, String str2) {
            this.f2330c.put(str, str2);
            notifyDataSetChanged();
        }

        public void m3301b() {
            this.f2330c = new HashMap();
            notifyDataSetChanged();
        }

        public void bindView(View view, Context context, Cursor cursor) {
            PublicChatsListFragment publicChatsListFragment = (PublicChatsListFragment) view.getTag();
            String string = cursor.getString(cursor.getColumnIndex("party"));
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
            cursor.getString(cursor.getColumnIndex("room_subject_group"));
            CharSequence string6 = cursor.getString(cursor.getColumnIndex("group_nick_name"));
            CharSequence string7 = cursor.getString(cursor.getColumnIndex("followers_count"));
            if (equals) {
                if (string6 == null || string6.length() <= 0) {
                    string6 = "Group";
                }
                publicChatsListFragment.f2319a.setText(string6);
            }
            publicChatsListFragment.f2322d.setText(string7);
            publicChatsListFragment.f2320b.setSingleLine();
            Object obj = null;
            if (blob.length < 33 && StickerManager.m4025a(new String(blob))) {
                obj = 1;
            }
            if (obj != null) {
                publicChatsListFragment.f2320b.setVisibility(0);
                publicChatsListFragment.f2320b.setText("(Sticker)");
            } else {
                publicChatsListFragment.f2320b.setVisibility(0);
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
                    TextLoader.m4005a(context).m4016a(new StringBuilder(String.valueOf(string)).append("_").append(a).toString(), obj2, publicChatsListFragment.f2320b, true, false);
                } else {
                    TextLoader.m4005a(context).m4016a(new StringBuilder(String.valueOf(string)).append("_").append(a).toString(), blob, publicChatsListFragment.f2320b, true, false);
                }
            }
            if (i2 == 3) {
                publicChatsListFragment.f2325g.setVisibility(0);
            } else {
                publicChatsListFragment.f2325g.setVisibility(8);
            }
            publicChatsListFragment.f2321c.setVisibility(0);
            publicChatsListFragment.f2323e.setVisibility(8);
            publicChatsListFragment.f2321c.setText(TimeUtils.m4028a(context, a));
            if (string3 != null && !string3.equals("null")) {
                this.f2328a.f2332Y.m2484a(string3, publicChatsListFragment.f2324f);
            } else if (equals) {
                Picasso.with(context).load(2130838071).into(publicChatsListFragment.f2324f);
            } else if (string.matches("[0-9]+")) {
                this.f2328a.f2332Y.m2484a(null, publicChatsListFragment.f2324f);
            } else {
                Picasso.with(context).load(2130838100).into(publicChatsListFragment.f2324f);
            }
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2329b.inflate(2130903173, viewGroup, false);
            PublicChatsListFragment publicChatsListFragment = new PublicChatsListFragment();
            publicChatsListFragment.f2319a = (TextView) inflate.findViewById(2131427613);
            publicChatsListFragment.f2320b = (TextView) inflate.findViewById(2131427615);
            publicChatsListFragment.f2321c = (TextView) inflate.findViewById(2131427614);
            publicChatsListFragment.f2322d = (TextView) inflate.findViewById(2131427780);
            publicChatsListFragment.f2323e = (TextView) inflate.findViewById(2131427779);
            publicChatsListFragment.f2326h = (RelativeLayout) inflate.findViewById(2131427609);
            publicChatsListFragment.f2324f = (ImageView) inflate.findViewById(2131427611);
            publicChatsListFragment.f2325g = (ImageView) inflate.findViewById(2131427616);
            inflate.setTag(publicChatsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            return super.swapCursor(cursor);
        }
    }

    private void m3302D() {
    }

    private int m3303E() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3305a(String str, int i) {
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

    private void m3307a(String str, String str2, int i) {
        Intent intent = new Intent(m91h(), PublicChatConversationActivity.class);
        intent.putExtra("party", str);
        intent.putExtra("party_name", str2);
        intent.putExtra("user_privilage", i);
        m59a(intent);
        m91h().overridePendingTransition(0, 0);
    }

    public Loader<Cursor> m3309a(int i, Bundle bundle) {
        return Messages.m5107b(m91h());
    }

    public View m3310a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2333Z = layoutInflater.inflate(2130903174, viewGroup, false);
        m3302D();
        return this.f2333Z;
    }

    public void m3311a(Bundle bundle) {
        super.m61a(bundle);
        this.f2334i = new PublicChatsListFragment(this, m91h());
        this.f2332Y = new PublicChatsListFragment(this, m91h(), m3303E());
        this.f2332Y.m2482a(2130838061);
        this.f2332Y.m2483a(m91h().getFragmentManager(), 0.1f);
        TextLoader.m4005a(m91h()).m4014a(m91h().getFragmentManager(), 0.5f);
        new Thread(new PublicChatsListFragment(this)).start();
    }

    public void m3312a(Loader<Cursor> loader) {
        this.f2334i.swapCursor(null);
    }

    public void m3313a(Loader<Cursor> loader, Cursor cursor) {
        this.f2334i.swapCursor(cursor);
    }

    public boolean m3315b(MenuItem menuItem) {
        return true;
    }

    public void m3316d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f2334i);
        m364a().setOnItemClickListener(this);
        m364a().setChoiceMode(3);
        m364a().setOnScrollListener(new PublicChatsListFragment(this));
        m364a().setDividerHeight(1);
        m100n().m144a(0, null, this);
        m364a().setMultiChoiceModeListener(new PublicChatsListFragment(this));
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
        Cursor cursor = this.f2334i.getCursor();
        cursor.moveToPosition(i);
        m3307a(cursor.getString(cursor.getColumnIndex("party")), cursor.getString(cursor.getColumnIndex("group_nick_name")), cursor.getInt(cursor.getColumnIndex("user_privilage")));
    }

    public void m3317q() {
        super.m103q();
        this.f2332Y.m2486b(false);
    }
}
