package mobi.mmdt.ott.core.model.database.p061b;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import mobi.mmdt.ott.core.model.database.CacheMaps;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p060a.AndroidContact;
import mobi.mmdt.ott.core.model.database.p060a.AndroidContacts;
import mobi.mmdt.ott.core.model.database.p060a.AndroidRow;

/* renamed from: mobi.mmdt.ott.core.model.database.b.d */
public class SynaContacts {
    public static int m4991a(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4927a(context), null, null, null, null);
        int count = query.getCount();
        query.close();
        return count;
    }

    public static int m4992a(Context context, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("presnece_status", Integer.valueOf(i));
        return context.getContentResolver().update(DatabaseContentProvider.m4927a(context), contentValues, null, null);
    }

    public static int m4993a(Context context, long j, int i) {
        if (i == 1) {
            SynaContacts.m4994a(context, j, System.nanoTime() / 1000000);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("presnece_status", Integer.valueOf(i));
        return context.getContentResolver().update(Uri.parse(DatabaseContentProvider.m4927a(context) + "/" + j), contentValues, null, null);
    }

    public static int m4994a(Context context, long j, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("last_online_received_time", Long.valueOf(j2));
        return context.getContentResolver().update(Uri.parse(DatabaseContentProvider.m4927a(context) + "/" + j), contentValues, null, null);
    }

    public static int m4995a(Context context, long j, Uri uri) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("local_avatar_address", uri);
        return context.getContentResolver().update(Uri.parse(DatabaseContentProvider.m4927a(context) + "/" + j), contentValues, null, null);
    }

    public static int m4996a(Context context, long j, String str, String str2, String str3, String str4, String str5, String str6, Uri uri, String str7, Boolean bool, Integer num, long j2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phonenumber", str5);
        if (num != null) {
            contentValues.put("presnece_status", Integer.valueOf(num.intValue()));
            contentValues.put("last_online_received_time", Long.valueOf(j2));
        }
        contentValues.put("first_name", str);
        contentValues.put("last_name", str2);
        contentValues.put("local_avatar_address", uri);
        contentValues.put("server_avatar_address", new StringBuilder(String.valueOf(str7)).toString());
        contentValues.put("email", str4);
        contentValues.put("nick_name", str3);
        contentValues.put("xmpp_phone_number", str6);
        if (bool == null) {
            contentValues.put("gender", "null");
        } else if (bool.booleanValue()) {
            contentValues.put("gender", "male");
        } else {
            contentValues.put("gender", "female");
        }
        return context.getContentResolver().update(Uri.parse(DatabaseContentProvider.m4927a(context) + "/" + j), contentValues, null, null);
    }

    public static long m4997a(Context context, String[] strArr) {
        String str = "";
        if (strArr != null && strArr.length > 0) {
            Object stringBuilder;
            for (int i = 0; i < strArr.length - 1; i++) {
                stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(strArr[i]).append("',").toString();
            }
            str = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(strArr[strArr.length - 1]).append("'").toString();
        }
        return (long) context.getContentResolver().delete(DatabaseContentProvider.m4927a(context), "phonenumber not in (" + str + ")", null);
    }

    public static Uri m4998a(Context context, String str, String str2, String str3, String str4, String str5, String str6, Uri uri, String str7, Boolean bool, int i, long j, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phonenumber", str5);
        contentValues.put("last_online_received_time", Long.valueOf(j));
        contentValues.put("presnece_status", Integer.valueOf(i));
        if (str != null) {
            contentValues.put("first_name", str);
        }
        if (str2 != null) {
            contentValues.put("last_name", str2);
        }
        if (uri != null) {
            contentValues.put("local_avatar_address", uri);
        }
        if (str7 != null) {
            contentValues.put("server_avatar_address", new StringBuilder(String.valueOf(str7)).toString());
        }
        if (str4 != null) {
            contentValues.put("email", str4);
        }
        if (str3 != null) {
            contentValues.put("nick_name", str3);
        }
        if (str6 != null) {
            contentValues.put("xmpp_phone_number", str6);
        }
        if (bool == null) {
            contentValues.put("gender", "null");
        } else if (bool.booleanValue()) {
            contentValues.put("gender", "male");
        } else {
            contentValues.put("gender", "female");
        }
        if (z) {
            contentValues.put("new_syna_user", "true");
        } else {
            contentValues.put("new_syna_user", "false");
        }
        Uri insert = context.getContentResolver().insert(DatabaseContentProvider.m4927a(context), contentValues);
        CacheMaps.m4958a(context).m4963a(str5, Long.parseLong(insert.getLastPathSegment()));
        return insert;
    }

    public static CursorLoader m4999a(Context context, Uri uri) {
        return new CursorLoader(context, uri, SynaContactsQuery.f3928a, "nick_name <> '' ", null, "nick_name");
    }

    public static CursorLoader m5000a(Context context, Uri uri, String[] strArr) {
        String str = "";
        if (strArr != null && strArr.length > 0) {
            Object stringBuilder;
            for (int i = 0; i < strArr.length - 1; i++) {
                stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(strArr[i]).append("',").toString();
            }
            str = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(strArr[strArr.length - 1]).append("'").toString();
        }
        str = "phonenumber in (" + str + ")";
        return new CursorLoader(context, uri, SynaContactsQuery.f3928a, "nick_name <> ''  AND " + str, null, "nick_name");
    }

    public static SynaContact m5001a(Context context, long j) {
        SynaContact synaContact;
        Cursor c = SynaContacts.m5013c(context, j);
        if (c.moveToFirst()) {
            Uri parse;
            Uri uri;
            int columnIndex = c.getColumnIndex("first_name");
            int columnIndex2 = c.getColumnIndex("phonenumber");
            int columnIndex3 = c.getColumnIndex("last_name");
            int columnIndex4 = c.getColumnIndex("local_avatar_address");
            int columnIndex5 = c.getColumnIndex("nick_name");
            int columnIndex6 = c.getColumnIndex("presnece_status");
            int columnIndex7 = c.getColumnIndex("server_avatar_address");
            int i = c.getInt(columnIndex6);
            String string = c.getString(columnIndex2);
            AndroidRow a = CacheMaps.m4958a(context).m4961a(string);
            String string2 = c.getString(columnIndex5);
            String string3 = c.getString(columnIndex);
            String string4 = c.getString(columnIndex3);
            if (string2 == null) {
                if (string3 == null && string4 == null) {
                    string4 = null;
                    if (a == null) {
                        string3 = a.m4955c();
                        if (string3 == null) {
                        }
                        string2 = a.m4954b();
                        uri = parse;
                    } else {
                        string2 = null;
                        uri = null;
                    }
                    string3 = c.getString(columnIndex4);
                    if (string3 == null) {
                    }
                    synaContact = new SynaContact((int) j, string, string2, string4, uri, string3 == null ? Uri.parse(string3) : null, i, c.getString(columnIndex7));
                } else if (string3 == null || string4 == null) {
                    if (string3 != null || string4 == null) {
                        if (string4 == null && string3 != null) {
                            string4 = string3;
                        }
                    }
                    if (a == null) {
                        string3 = a.m4955c();
                        parse = string3 == null ? Uri.parse(string3) : null;
                        string2 = a.m4954b();
                        uri = parse;
                    } else {
                        string2 = null;
                        uri = null;
                    }
                    string3 = c.getString(columnIndex4);
                    synaContact = new SynaContact((int) j, string, string2, string4, uri, string3 == null ? Uri.parse(string3) : null, i, c.getString(columnIndex7));
                } else {
                    string4 = new StringBuilder(String.valueOf(string3)).append(" ").append(string4).toString();
                    if (a == null) {
                        string2 = null;
                        uri = null;
                    } else {
                        string3 = a.m4955c();
                        if (string3 == null) {
                        }
                        string2 = a.m4954b();
                        uri = parse;
                    }
                    string3 = c.getString(columnIndex4);
                    if (string3 == null) {
                    }
                    synaContact = new SynaContact((int) j, string, string2, string4, uri, string3 == null ? Uri.parse(string3) : null, i, c.getString(columnIndex7));
                }
            }
            string4 = string2;
            if (a == null) {
                string2 = null;
                uri = null;
            } else {
                string3 = a.m4955c();
                if (string3 == null) {
                }
                string2 = a.m4954b();
                uri = parse;
            }
            string3 = c.getString(columnIndex4);
            if (string3 == null) {
            }
            synaContact = new SynaContact((int) j, string, string2, string4, uri, string3 == null ? Uri.parse(string3) : null, i, c.getString(columnIndex7));
        } else {
            synaContact = null;
        }
        c.close();
        return synaContact;
    }

    public static ContactShow m5002a(Context context, String str, boolean z) {
        String g;
        Uri uri = null;
        SynaContact d = SynaContacts.m5017d(context, str);
        boolean z2 = false;
        int i = 2;
        if (d != null) {
            z2 = true;
            g = d.m4979g();
            uri = d.m4978f();
            i = d.m4974b();
        } else {
            g = null;
        }
        if (uri == null || g == null || g.equals("")) {
            AndroidContact a = AndroidContacts.m4946a(context, str, z);
            if ((g == null || g.equals("")) && a != null) {
                g = a.m4939b();
            }
            if (uri == null && a != null) {
                uri = a.m4940c();
            }
        }
        if (g == null || g.equals("")) {
            g = str;
        }
        return new ContactShow(uri, g, str, z2, i);
    }

    public static boolean m5003a(Context context, long j, boolean z) {
        return CacheMaps.m4958a(context).m4969c(j);
    }

    public static boolean m5004a(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4927a(context), null, "is_gift_request =? and phonenumber =? ", new String[]{"true", str}, null);
        boolean z = query.getCount() != 0;
        query.close();
        return z;
    }

    public static long m5005b(Context context, long j, boolean z) {
        Cursor c = SynaContacts.m5013c(context, j);
        if (z) {
            CacheMaps.m4958a(context).m4972d(c.getString(c.getColumnIndex("phonenumber")));
        }
        c.close();
        return (long) context.getContentResolver().delete(Uri.parse(DatabaseContentProvider.m4927a(context) + "/" + j), null, null);
    }

    public static CursorLoader m5006b(Context context, Uri uri) {
        return new CursorLoader(context, uri, SynaContactsQuery.f3929b, "nick_name <> '' ", null, "new_syna_user DESC , nick_name");
    }

    public static CursorLoader m5007b(Context context, Uri uri, String[] strArr) {
        String str = "";
        if (strArr != null && strArr.length > 0) {
            Object stringBuilder;
            for (int i = 0; i < strArr.length - 1; i++) {
                stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(strArr[i]).append("',").toString();
            }
            str = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(strArr[strArr.length - 1]).append("'").toString();
        }
        str = "phonenumber not in (" + str + ")";
        return new CursorLoader(context, uri, SynaContactsQuery.f3928a, "nick_name <> ''  AND " + str, null, "nick_name");
    }

    public static SynaContactImage m5008b(Context context, long j) {
        Cursor c = SynaContacts.m5013c(context, j);
        SynaContactImage synaContactImage = null;
        if (c.moveToFirst()) {
            synaContactImage = new SynaContactImage(c.getString(c.getColumnIndex("server_avatar_address")), c.getString(c.getColumnIndex("local_avatar_address")));
        }
        c.close();
        return synaContactImage;
    }

    public static void m5009b(Context context) {
        context.getContentResolver().delete(DatabaseContentProvider.m4927a(context), null, null);
    }

    public static boolean m5010b(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4927a(context), new String[]{"_id"}, "phonenumber=?", new String[]{new StringBuilder(String.valueOf(str)).toString()}, null);
        if (query.moveToFirst()) {
            query.close();
            return true;
        }
        query.close();
        return false;
    }

    public static int m5011c(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_gift_request", "true");
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static long m5012c(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4927a(context), new String[]{"_id"}, "phonenumber=?", new String[]{new StringBuilder(String.valueOf(str)).toString()}, null);
        long j = -1;
        if (query.moveToFirst()) {
            j = query.getLong(query.getColumnIndex("_id"));
        }
        query.close();
        return j;
    }

    private static Cursor m5013c(Context context, long j) {
        return context.getContentResolver().query(DatabaseContentProvider.m4927a(context), null, "_id=?", new String[]{new StringBuilder(String.valueOf(j)).toString()}, null);
    }

    public static SynaContact[] m5014c(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4927a(context), null, "server_avatar_address <>? and local_avatar_address =? ", new String[]{"null", "null"}, null);
        int columnIndex = query.getColumnIndex("_id");
        int columnIndex2 = query.getColumnIndex("first_name");
        int columnIndex3 = query.getColumnIndex("phonenumber");
        int columnIndex4 = query.getColumnIndex("last_name");
        int columnIndex5 = query.getColumnIndex("local_avatar_address");
        int columnIndex6 = query.getColumnIndex("server_avatar_address");
        int columnIndex7 = query.getColumnIndex("nick_name");
        int columnIndex8 = query.getColumnIndex("presnece_status");
        SynaContact[] synaContactArr = new SynaContact[query.getCount()];
        int i = 0;
        if (query.moveToFirst()) {
            do {
                Uri uri;
                int i2 = i;
                String string = query.getString(columnIndex3);
                AndroidRow a = CacheMaps.m4958a(context).m4961a(string);
                String string2 = query.getString(columnIndex7);
                int i3 = query.getInt(columnIndex8);
                String string3 = query.getString(columnIndex2);
                String string4 = query.getString(columnIndex4);
                long j = query.getLong(columnIndex);
                if (string2 != null) {
                    string4 = string2;
                } else if (string3 == null && string4 == null) {
                    string4 = null;
                } else if (string3 != null) {
                    string4 = string4 == null ? string3 : new StringBuilder(String.valueOf(string3)).append(" ").append(string4).toString();
                }
                Uri uri2 = null;
                String str = null;
                if (a != null) {
                    string2 = a.m4955c();
                    if (string2 != null) {
                        uri2 = Uri.parse(string2);
                    }
                    str = a.m4954b();
                    uri = uri2;
                } else {
                    uri = null;
                }
                Uri uri3 = null;
                string3 = query.getString(columnIndex5);
                if (string3 != null) {
                    uri3 = Uri.parse(string3);
                }
                synaContactArr[i2] = new SynaContact((int) j, string, str, string4, uri, uri3, i3, query.getString(columnIndex6));
                i = i2 + 1;
            } while (query.moveToNext());
        }
        query.close();
        return synaContactArr;
    }

    public static int m5015d(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4927a(context), null, null, null, null);
        int count = query.getCount();
        query.close();
        return count;
    }

    public static int m5016d(Context context, Uri uri) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("new_syna_user", "false");
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    private static SynaContact m5017d(Context context, String str) {
        return SynaContacts.m5001a(context, (long) ((int) SynaContacts.m5012c(context, str)));
    }
}
