package com.mmdt.syna.view.p022b;

import android.content.Context;
import android.graphics.Bitmap;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;

/* renamed from: com.mmdt.syna.view.b.b */
class ConversationsListFragment extends ImageLoader {
    final /* synthetic */ ConversationsListFragment f1682a;

    ConversationsListFragment(ConversationsListFragment conversationsListFragment, Context context, int i) {
        this.f1682a = conversationsListFragment;
        super(context, i);
    }

    protected Bitmap m2571a(Object obj) {
        return this.f1682a.m2558a((String) obj, m2480a());
    }
}
 this.f1683a.f1679Y.m2486b(true);
        } else {
            this.f1683a.f1679Y.m2486b(false);
        }
    }
}
;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mmdt.syna.view.conversation.conversationpage.activities.GroupConversationActivity;
import com.mmdt.syna.view.conversation.conversationpage.activities.SingleConversationActivity;
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
/* renamed from: com.mmdt.syna.view.b.a */
public class ConversationsListFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private ImageLoader f1679Y;
    private View f1680Z;
    private ConversationsListFragment f1681i;

    /* renamed from: com.mmdt.syna.view.b.a.a */
    private class ConversationsListFragment extends CursorAdapter {
        final /* synthetic */ ConversationsListFragment f1675a;
        private LayoutInflater f1676b;
        private HashMap<String, Boolean> f1677c;
        private TextAppearanceSpan f1678d;

        /* renamed from: com.mmdt.syna.view.b.a.a.a */
        private class ConversationsListFragment {
            TextView f1668a;
            TextView f1669b;
            TextView f1670c;
            ImageView f1671d;
            ImageView f1672e;
            RelativeLayout f1673f;
            final /* synthetic */ ConversationsListFragment f1674g;

            private ConversationsListFragment(ConversationsListFragment conversationsListFragment) {
                this.f1674g = conversationsListFragment;
            }
        }

        public ConversationsListFragment(ConversationsListFragment conversationsListFragment, Context context) {
            this.f1675a = conversationsListFragment;
            super(context, null, 0);
            this.f1677c = new HashMap();
            this.f1676b = LayoutInflater.from(context);
            this.f1678d = new TextAppearanceSpan(conversationsListFragment.m91h(), 2131558581);
        }

        public HashMap<String, Boolean> m2551a() {
            return this.f1677c;
        }

        public void m2552a(String str) {
            this.f1677c.remove(str);
            notifyDataSetChanged();
        }

        public void m2553a(String str, boolean z) {
            this.f1677c.put(str, Boolean.valueOf(z));
            notifyDataSetChanged();
        }

        public void m2554b() {
            this.f1677c = new HashMap();
            notifyDataSetChanged();
        }

        public void bindView(View view, Context context, Cursor cursor) {
            Object obj;
            ConversationsListFragment conversationsListFragment = (ConversationsListFragment) view.getTag();
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
            CharSequence string6 = cursor.getString(cursor.getColumnIndex("room_subject_group"));
            if (equals) {
                if (string6 == null || string6.length() <= 0) {
                    string6 = "Group";
                }
                conversationsListFragment.f1668a.setText(string6);
            } else if (string != null) {
                String str = "";
                if (string2 != null) {
                    string6 = string2;
                } else {
                    obj = string;
                }
                if (string6 == null || string6.length() <= 0) {
                    obj = string;
                }
                conversationsListFragment.f1668a.setText(string6);
            } else {
                return;
            }
            obj = null;
            if (blob.length < 33 && StickerManager.m4025a(new String(blob))) {
                obj = 1;
            }
            if (obj != null) {
                conversationsListFragment.f1669b.setVisibility(0);
                conversationsListFragment.f1669b.setText("(Sticker)");
            } else {
                conversationsListFragment.f1669b.setVisibility(0);
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
                    TextLoader.m4005a(context).m4016a(new StringBuilder(String.valueOf(string)).append("_").append(a).toString(), obj2, conversationsListFragment.f1669b, true, false);
                } else {
                    TextLoader.m4005a(context).m4016a(new StringBuilder(String.valueOf(string)).append("_").append(a).toString(), blob, conversationsListFragment.f1669b, true, false);
                }
            }
            if (i2 == 3) {
                conversationsListFragment.f1672e.setVisibility(0);
            } else {
                conversationsListFragment.f1672e.setVisibility(8);
            }
            conversationsListFragment.f1670c.setText(TimeUtils.m4028a(context, a));
            if (string3 != null && !string3.equals("null")) {
                this.f1675a.f1679Y.m2484a(string3, conversationsListFragment.f1671d);
            } else if (equals) {
                Picasso.with(context).load(2130838071).into(conversationsListFragment.f1671d);
            } else if (string.matches("[0-9]+")) {
                this.f1675a.f1679Y.m2484a(null, conversationsListFragment.f1671d);
            } else {
                Picasso.with(context).load(2130838100).into(conversationsListFragment.f1671d);
            }
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f1676b.inflate(2130903113, viewGroup, false);
            ConversationsListFragment conversationsListFragment = new ConversationsListFragment();
            conversationsListFragment.f1668a = (TextView) inflate.findViewById(2131427613);
            conversationsListFragment.f1669b = (TextView) inflate.findViewById(2131427615);
            conversationsListFragment.f1670c = (TextView) inflate.findViewById(2131427614);
            conversationsListFragment.f1673f = (RelativeLayout) inflate.findViewById(2131427609);
            conversationsListFragment.f1671d = (ImageView) inflate.findViewById(2131427611);
            conversationsListFragment.f1672e = (ImageView) inflate.findViewById(2131427616);
            inflate.setTag(conversationsListFragment);
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            return super.swapCursor(cursor);
        }
    }

    private void m2555D() {
    }

    private int m2556E() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m2558a(String str, int i) {
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

    private void m2560a(String str, String str2, boolean z) {
        Intent intent = z ? new Intent(m91h(), GroupConversationActivity.class) : new Intent(m91h(), SingleConversationActivity.class);
        intent.putExtra("party", str);
        intent.putExtra("party_name", str2);
        m59a(intent);
        m91h().overridePendingTransition(0, 0);
    }

    public Loader<Cursor> m2562a(int i, Bundle bundle) {
        return Messages.m5113c(m91h());
    }

    public View m2563a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f1680Z = layoutInflater.inflate(2130903115, viewGroup, false);
        m2555D();
        return this.f1680Z;
    }

    public void m2564a(Bundle bundle) {
        super.m61a(bundle);
        this.f1681i = new ConversationsListFragment(this, m91h());
        this.f1679Y = new ConversationsListFragment(this, m91h(), m2556E());
        this.f1679Y.m2482a(2130838061);
        this.f1679Y.m2483a(m91h().getFragmentManager(), 0.1f);
        TextLoader.m4005a(m91h()).m4014a(m91h().getFragmentManager(), 0.5f);
    }

    public void m2565a(Loader<Cursor> loader) {
        this.f1681i.swapCursor(null);
    }

    public void m2566a(Loader<Cursor> loader, Cursor cursor) {
        this.f1681i.swapCursor(cursor);
    }

    public boolean m2568b(MenuItem menuItem) {
        return true;
    }

    public void m2569d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.f1681i);
        m364a().setOnItemClickListener(this);
        m364a().setChoiceMode(3);
        m364a().setOnScrollListener(new ConversationsListFragment(this));
        m364a().setDividerHeight(1);
        m100n().m144a(0, null, this);
        m364a().setMultiChoiceModeListener(new ConversationsListFragment(this));
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
        Cursor cursor = this.f1681i.getCursor();
        cursor.moveToPosition(i);
        boolean equals = cursor.getString(cursor.getColumnIndex("is_group")).equals("true");
        m2560a(cursor.getString(cursor.getColumnIndex("party")), equals ? cursor.getString(cursor.getColumnIndex("room_subject_group")) : cursor.getString(cursor.getColumnIndex("nick_name")), equals);
    }

    public void m2570q() {
        super.m103q();
        this.f1679Y.m2486b(false);
    }
}
