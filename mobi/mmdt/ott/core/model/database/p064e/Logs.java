package mobi.mmdt.ott.core.model.database.p064e;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

/* renamed from: mobi.mmdt.ott.core.model.database.e.b */
public class Logs {
    public static long m5065a(Context context, long j) {
        return (long) context.getContentResolver().delete(Uri.parse(DatabaseContentProvider.m4928b(context) + "/" + j), null, null);
    }

    public static long m5066a(Context context, Uri uri, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(i));
        return (long) context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static Uri m5067a(Context context, int i, int i2, String str, long j, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("phonenumber", str);
        contentValues.put(Status.ELEMENT, Integer.valueOf(i));
        contentValues.put("type", Integer.valueOf(i2));
        contentValues.put("date", Long.valueOf(j));
        if (str2 != null) {
            contentValues.put("description", str2);
        }
        return context.getContentResolver().insert(DatabaseContentProvider.m4928b(context), contentValues);
    }

    public static CursorLoader m5068a(Context context, int i) {
        Uri b = DatabaseContentProvider.m4928b(context);
        String[] strArr = new String[]{"logs.phonenumber as asphonenumber", "type", "logs._id", Status.ELEMENT, "date", "nick_name", "presnece_status", "local_avatar_address", "server_avatar_address"};
        String[] strArr2 = new String[]{"2"};
        String str = "date DESC  limit " + i;
        return new CursorLoader(context, b, strArr, "type =? ", strArr2, str);
    }

    public static void m5069a(Context context) {
        context.getContentResolver().delete(DatabaseContentProvider.m4928b(context), null, null);
    }

    public static long m5070b(Context context) {
        return (long) context.getContentResolver().delete(DatabaseContentProvider.m4928b(context), null, null);
    }

    public static CursorLoader m5071b(Context context, int i) {
        Uri b = DatabaseContentProvider.m4928b(context);
        String[] strArr = new String[]{"logs.phonenumber as asphonenumber", "type", "logs._id", Status.ELEMENT, "date", "nick_name", "presnece_status", "local_avatar_address", "server_avatar_address"};
        String[] strArr2 = new String[]{"2", "3", "2"};
        String str = "date DESC  limit " + i;
        return new CursorLoader(context, b, strArr, "(status =? OR status =? ) AND type =? ", strArr2, str);
    }

    public static int m5072c(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(2));
        return context.getContentResolver().update(DatabaseContentProvider.m4928b(context), contentValues, "type =? and status =? ", new String[]{"2", "3"});
    }

    public static LogRow[] m5073d(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4928b(context), new String[]{"logs.phonenumber as asphonenumber", Status.ELEMENT, "date"}, "type =? and status =? ", new String[]{"2", "3"}, "date DESC");
        LogRow[] logRowArr = new LogRow[query.getCount()];
        if (query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("asphonenumber");
            int columnIndex2 = query.getColumnIndex("date");
            int i = 0;
            while (true) {
                int i2 = i + 1;
                logRowArr[i] = new LogRow(query.getString(columnIndex), query.getLong(columnIndex2));
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return logRowArr;
    }
}
