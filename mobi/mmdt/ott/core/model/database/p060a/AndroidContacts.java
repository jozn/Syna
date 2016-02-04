package mobi.mmdt.ott.core.model.database.p060a;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v4.content.CursorLoader;
import java.util.HashMap;
import mobi.mmdt.ott.core.model.contactmanager.User;
import mobi.mmdt.ott.core.model.database.CacheMaps;
import mobi.mmdt.ott.core.p051a.CountryTools;
import mobi.mmdt.p041a.HasVersion;

/* renamed from: mobi.mmdt.ott.core.model.database.a.b */
public class AndroidContacts {
    public static CursorLoader m4942a(Context context, Uri uri) {
        return new CursorLoader(context, uri, AndroidContactsQuery.f3891e, AndroidContactsQuery.f3889c, null, AndroidContactsQuery.f3890d);
    }

    public static HashMap<String, User> m4943a(Context context) {
        HashMap<String, User> hashMap = null;
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = Phone.CONTENT_URI;
        String[] strArr = new String[2];
        strArr[0] = "data1";
        strArr[1] = HasVersion.m4098a() ? "display_name" : "display_name";
        Cursor query = contentResolver.query(uri, strArr, null, null, null);
        if (query != null) {
            hashMap = new HashMap();
            strArr = new String[query.getCount()];
            String[] strArr2 = new String[query.getCount()];
            String[] strArr3 = new String[query.getCount()];
            if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex("data1");
                int columnIndex2 = query.getColumnIndex(HasVersion.m4098a() ? "display_name" : "display_name");
                int i = 0;
                do {
                    strArr[i] = query.getString(columnIndex);
                    strArr2[i] = query.getString(columnIndex2);
                    strArr3[i] = CountryTools.m4416a().m4422b(context, query.getString(columnIndex));
                    User user = new User();
                    user.f3874b = query.getString(columnIndex2);
                    user.f3875c = query.getString(columnIndex);
                    hashMap.put(strArr3[i], user);
                    i++;
                } while (query.moveToNext());
            }
            query.close();
        }
        return hashMap;
    }

    public static AndroidContact m4944a(Context context, long j, boolean z) {
        String[] a;
        if (z) {
            a = CacheMaps.m4958a(context).m4964a(j);
            AndroidRow b = CacheMaps.m4958a(context).m4966b(j);
            if (b == null) {
                return null;
            }
            String b2 = b.m4954b();
            boolean a2 = b.m4953a();
            Uri uri = null;
            if (b.m4955c() != null) {
                uri = Uri.parse(b.m4955c());
            }
            return new AndroidContact((int) j, a, b2, a2, uri);
        }
        int columnIndex;
        int columnIndex2;
        String str = null;
        int i = 0;
        Uri uri2 = null;
        Cursor query = context.getContentResolver().query(Contacts.CONTENT_URI, null, "_id=?", new String[]{new StringBuilder(String.valueOf(j)).toString()}, "display_name COLLATE NOCASE ASC");
        if (query.moveToFirst()) {
            columnIndex = query.getColumnIndex("starred");
            columnIndex2 = query.getColumnIndex(HasVersion.m4098a() ? "photo_thumb_uri" : "_id");
            String string = query.getString(query.getColumnIndex(HasVersion.m4098a() ? "display_name" : "display_name"));
            columnIndex = query.getInt(columnIndex);
            String string2 = query.getString(columnIndex2);
            if (string2 != null) {
                uri2 = Uri.parse(string2);
                i = columnIndex;
                str = string;
            } else {
                i = columnIndex;
                str = string;
            }
        }
        query.close();
        query = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{"data1", "data2", "contact_id"}, "contact_id =? ", new String[]{new StringBuilder(String.valueOf(j)).toString()}, null);
        columnIndex2 = query.getColumnIndex("data1");
        a = new String[query.getCount()];
        int i2 = 0;
        if (query.moveToFirst()) {
            while (true) {
                columnIndex = i2 + 1;
                a[i2] = CountryTools.m4416a().m4422b(context, query.getString(columnIndex2));
                if (!query.moveToNext()) {
                    break;
                }
                i2 = columnIndex;
            }
        }
        query.close();
        a2 = false;
        if (i == 1) {
            a2 = true;
        }
        return new AndroidContact((int) j, a, str, a2, uri2);
    }

    public static AndroidContact m4945a(Context context, String str) {
        AndroidContact androidContact = null;
        Cursor query = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{"contact_id", "data1"}, "data1 LIKE '%" + str + "%'", null, null);
        int columnIndex = query.getColumnIndex("contact_id");
        if (query.moveToFirst()) {
            androidContact = AndroidContacts.m4944a(context, query.getLong(columnIndex), true);
        }
        query.close();
        return androidContact;
    }

    public static AndroidContact m4946a(Context context, String str, boolean z) {
        return CacheMaps.m4958a(context).m4961a(str) == null ? null : AndroidContacts.m4944a(context, (long) CacheMaps.m4958a(context).m4961a(str).m4956d(), z);
    }

    public static AndroidContact[] m4947b(Context context, String str) {
        Cursor query = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{"contact_id", "data1"}, "data1 LIKE '%" + str + "%'", null, null);
        AndroidContact[] androidContactArr = new AndroidContact[query.getCount()];
        int columnIndex = query.getColumnIndex("contact_id");
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                androidContactArr[i] = AndroidContacts.m4944a(context, query.getLong(columnIndex), true);
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return androidContactArr;
    }

    public static int m4948c(Context context, String str) {
        Cursor query = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{"data1"}, "data1 LIKE '%" + str + "%'", null, null);
        int count = query.getCount();
        query.close();
        return count;
    }

    public static String m4949d(Context context, String str) {
        return CacheMaps.m4958a(context).m4965b(str);
    }

    public static AndroidDisplay m4950e(Context context, String str) {
        AndroidDisplay androidDisplay = null;
        Cursor query = context.getContentResolver().query(Contacts.CONTENT_URI, null, "_id =?", new String[]{str}, null);
        if (query.moveToFirst()) {
            androidDisplay = new AndroidDisplay(query.getString(query.getColumnIndex(HasVersion.m4098a() ? "photo_thumb_uri" : "_id")), query.getString(query.getColumnIndex(HasVersion.m4098a() ? "display_name" : "display_name")));
        }
        query.close();
        return androidDisplay;
    }
}
