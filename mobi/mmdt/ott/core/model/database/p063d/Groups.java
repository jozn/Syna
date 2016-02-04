package mobi.mmdt.ott.core.model.database.p063d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;

/* renamed from: mobi.mmdt.ott.core.model.database.d.b */
public class Groups {
    public static int m5044a(Context context, String str, String str2, String str3, Uri uri, String str4, String str5, int i, int i2, String str6) {
        ContentValues contentValues = new ContentValues();
        if (i != -1) {
            contentValues.put("user_role", Integer.valueOf(i));
        }
        if (str3 != null) {
            contentValues.put("group_nick_name", str3);
        }
        if (str5 != null) {
            contentValues.put("descripton", str5);
        }
        if (str2 != null) {
            contentValues.put("room_subject_group", str2);
        }
        if (uri != null) {
            contentValues.put("local_picture", uri);
        }
        if (str4 != null) {
            contentValues.put("server_picture", str4);
        }
        if (i2 != -1) {
            contentValues.put("user_privilage", Integer.valueOf(i2));
        }
        if (str6 != null) {
            contentValues.put("followers_count", str6);
        }
        return context.getContentResolver().update(DatabaseContentProvider.m4931e(context), contentValues, "group_id =? ", new String[]{str});
    }

    public static int m5045a(Context context, String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("is_followed_by_me", "true");
        } else {
            contentValues.put("is_followed_by_me", "false");
        }
        return context.getContentResolver().update(DatabaseContentProvider.m4931e(context), contentValues, "group_id =? ", new String[]{str});
    }

    public static Uri m5046a(Context context, String str, String str2, String str3, Uri uri, String str4, String str5, int i, int i2, boolean z, boolean z2, boolean z3, String str6) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("group_id", str);
        contentValues.put("followers_count", str6);
        contentValues.put("descripton", str5);
        contentValues.put("room_subject_group", str2);
        contentValues.put("local_picture", uri);
        contentValues.put("server_picture", str4);
        contentValues.put("user_privilage", Integer.valueOf(i2));
        contentValues.put("user_role", Integer.valueOf(i));
        contentValues.put("group_nick_name", str3);
        if (z) {
            contentValues.put("is_followed_by_me", "true");
        } else {
            contentValues.put("is_followed_by_me", "false");
        }
        if (z2) {
            contentValues.put("is_room_mute", "true");
        } else {
            contentValues.put("is_room_mute", "false");
        }
        if (z3) {
            contentValues.put("is_room_public", "true");
        } else {
            contentValues.put("is_room_public", "false");
        }
        return context.getContentResolver().insert(DatabaseContentProvider.m4931e(context), contentValues);
    }

    public static CursorLoader m5047a(Context context, Uri uri) {
        return new CursorLoader(context, uri, null, "is_room_public =? ", new String[]{"true"}, "room_subject_group");
    }

    public static void m5048a(Context context) {
        context.getContentResolver().delete(DatabaseContentProvider.m4931e(context), null, null);
    }

    public static boolean m5049a(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4931e(context), null, "group_id =? ", new String[]{str}, null);
        boolean equals = query.moveToFirst() ? query.getString(query.getColumnIndex("is_room_mute")).equals("true") : false;
        query.close();
        return equals;
    }

    public static int m5050b(Context context, String str, boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("is_room_mute", "true");
        } else {
            contentValues.put("is_room_mute", "false");
        }
        return context.getContentResolver().update(DatabaseContentProvider.m4931e(context), contentValues, "group_id =? ", new String[]{str});
    }

    public static CursorLoader m5051b(Context context, Uri uri) {
        return new CursorLoader(context, uri, null, "is_room_public =? ", new String[]{"false"}, "room_subject_group");
    }

    public static boolean m5052b(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4931e(context), null, "group_id =? ", new String[]{str}, null);
        boolean equals = query.moveToFirst() ? query.getString(query.getColumnIndex("is_room_public")).equals("true") : false;
        query.close();
        return equals;
    }

    public static GroupRow[] m5053b(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4931e(context), null, "is_room_public =? ", new String[]{"true"}, null);
        GroupRow[] groupRowArr = new GroupRow[query.getCount()];
        int columnIndex = query.getColumnIndex("_id");
        int columnIndex2 = query.getColumnIndex("group_id");
        int columnIndex3 = query.getColumnIndex("server_picture");
        int columnIndex4 = query.getColumnIndex("local_picture");
        int columnIndex5 = query.getColumnIndex("descripton");
        int columnIndex6 = query.getColumnIndex("user_role");
        int columnIndex7 = query.getColumnIndex("user_privilage");
        int columnIndex8 = query.getColumnIndex("is_room_mute");
        int columnIndex9 = query.getColumnIndex("group_nick_name");
        int columnIndex10 = query.getColumnIndex("room_subject_group");
        int columnIndex11 = query.getColumnIndex("is_followed_by_me");
        int columnIndex12 = query.getColumnIndex("is_room_public");
        int columnIndex13 = query.getColumnIndex("followers_count");
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                boolean equals = query.getString(columnIndex8).equals("true");
                int i2 = i + 1;
                groupRowArr[i] = new GroupRow(query.getLong(columnIndex), query.getString(columnIndex2), query.getString(columnIndex9), query.getString(columnIndex5), query.getString(columnIndex10), query.getString(columnIndex3), Uri.parse(query.getString(columnIndex4)), query.getInt(columnIndex7), query.getInt(columnIndex6), query.getString(columnIndex11).equals("true"), query.getString(columnIndex12).equals("true"), equals, query.getString(columnIndex13));
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return groupRowArr;
    }

    public static boolean m5054c(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4931e(context), null, "group_id =? ", new String[]{str}, null);
        if (query.getCount() <= 0) {
            return false;
        }
        query.close();
        return true;
    }

    public static String[] m5055c(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4931e(context), null, null, null, null);
        String[] strArr = new String[query.getCount()];
        int columnIndex = query.getColumnIndex("group_id");
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                strArr[i] = query.getString(columnIndex);
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return strArr;
    }

    public static GroupRow m5056d(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4931e(context), null, "group_id =? ", new String[]{str}, null);
        GroupRow groupRow = null;
        int columnIndex = query.getColumnIndex("_id");
        int columnIndex2 = query.getColumnIndex("server_picture");
        int columnIndex3 = query.getColumnIndex("local_picture");
        int columnIndex4 = query.getColumnIndex("descripton");
        int columnIndex5 = query.getColumnIndex("user_role");
        int columnIndex6 = query.getColumnIndex("user_privilage");
        int columnIndex7 = query.getColumnIndex("is_room_mute");
        int columnIndex8 = query.getColumnIndex("group_nick_name");
        int columnIndex9 = query.getColumnIndex("room_subject_group");
        int columnIndex10 = query.getColumnIndex("is_followed_by_me");
        int columnIndex11 = query.getColumnIndex("is_room_public");
        int columnIndex12 = query.getColumnIndex("followers_count");
        if (query.moveToFirst()) {
            boolean equals = query.getString(columnIndex7).equals("true");
            String str2 = str;
            groupRow = new GroupRow(query.getLong(columnIndex), str2, query.getString(columnIndex8), query.getString(columnIndex4), query.getString(columnIndex9), query.getString(columnIndex2), Uri.parse(query.getString(columnIndex3)), query.getInt(columnIndex6), query.getInt(columnIndex5), query.getString(columnIndex10).equals("true"), query.getString(columnIndex11).equals("true"), equals, query.getString(columnIndex12));
        }
        query.close();
        return groupRow;
    }

    public static String[] m5057d(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4931e(context), new String[]{"group_id"}, "is_room_public =? ", new String[]{"true"}, null);
        String[] strArr = new String[query.getCount()];
        int columnIndex = query.getColumnIndex("group_id");
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                strArr[i] = query.getString(columnIndex);
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return strArr;
    }
}
