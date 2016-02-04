package mobi.mmdt.ott.core.model.database.p065f.p066a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;

/* renamed from: mobi.mmdt.ott.core.model.database.f.a.a */
public class ChatStates {
    public static int m5076a(Context context, long j, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("column_party", str);
        contentValues.put("column_sender", str2);
        contentValues.put("column_state", str3);
        return context.getContentResolver().update(DatabaseContentProvider.m4932f(context), contentValues, "_id =? ", new String[]{new StringBuilder(String.valueOf(j)).toString()});
    }

    public static long m5077a(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4932f(context), null, "column_party =? ", new String[]{str}, null);
        long j = query.moveToFirst() ? query.getLong(query.getColumnIndex("_id")) : -1;
        query.close();
        return j;
    }

    public static Uri m5078a(Context context, String str, String str2, String str3) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("column_party", str);
        contentValues.put("column_sender", str2);
        contentValues.put("column_state", str3);
        return context.getContentResolver().insert(DatabaseContentProvider.m4932f(context), contentValues);
    }

    public static ChatStatesRow[] m5079a(Context context, String str, String str2) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4932f(context), null, "column_party =? and column_sender <>? ", new String[]{str, str2}, null);
        int columnIndex = query.getColumnIndex("_id");
        int columnIndex2 = query.getColumnIndex("column_sender");
        int columnIndex3 = query.getColumnIndex("column_party");
        int columnIndex4 = query.getColumnIndex("column_state");
        ChatStatesRow[] chatStatesRowArr = new ChatStatesRow[query.getCount()];
        int i = 0;
        if (query.moveToFirst()) {
            while (true) {
                int i2 = i + 1;
                chatStatesRowArr[i] = new ChatStatesRow(query.getLong(columnIndex), query.getString(columnIndex3), query.getString(columnIndex2), query.getString(columnIndex4));
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        return chatStatesRowArr;
    }
}
