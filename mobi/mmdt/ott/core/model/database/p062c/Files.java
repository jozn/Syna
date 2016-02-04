package mobi.mmdt.ott.core.model.database.p062c;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;

/* renamed from: mobi.mmdt.ott.core.model.database.c.b */
public class Files {
    public static int m5026a(Context context, Uri uri) {
        return context.getContentResolver().delete(uri, null, null);
    }

    public static int m5027a(Context context, Uri uri, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("audi_intime", Integer.valueOf(i));
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static int m5028a(Context context, Uri uri, Uri uri2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("thumbnailaddress", uri2);
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static int m5029a(Context context, Uri uri, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("server_address", str);
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static Uri m5030a(Context context, String str, String str2, String str3, long j, String str4, int i, int i2, int i3, int i4, String str5) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("filename", str);
        contentValues.put("fileaddress", str2);
        contentValues.put("thumbnailaddress", str3);
        contentValues.put("filelength", Long.valueOf(j));
        contentValues.put("server_address", str4);
        contentValues.put("filestatus", Integer.valueOf(i));
        contentValues.put("multimediaduration", Integer.valueOf(i2));
        contentValues.put("progress", Integer.valueOf(0));
        contentValues.put("audi_intime", Integer.valueOf(i4));
        contentValues.put("server_thumbnail_address", str5);
        contentValues.put("audio_state", Integer.valueOf(i3));
        return context.getContentResolver().insert(DatabaseContentProvider.m4930d(context), contentValues);
    }

    public static void m5031a(Context context) {
        context.getContentResolver().delete(DatabaseContentProvider.m4930d(context), null, null);
    }

    public static Uri[] m5032a(Context context, int i) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4930d(context), new String[]{"_idFiles"}, "filestatus =? ", new String[]{new StringBuilder(String.valueOf(i)).toString()}, null);
        int columnIndex = query.getColumnIndex("_idFiles");
        Uri[] uriArr = new Uri[query.getCount()];
        if (query.moveToFirst()) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                uriArr[i2] = Uri.parse(DatabaseContentProvider.m4930d(context) + "/" + query.getInt(columnIndex));
                if (!query.moveToNext()) {
                    break;
                }
                i2 = i3;
            }
        }
        query.close();
        return uriArr;
    }

    public static int m5033b(Context context, Uri uri, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("audio_state", Integer.valueOf(i));
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static Cursor m5034b(Context context, Uri uri) {
        return context.getContentResolver().query(uri, null, null, null, null);
    }

    public static int m5035c(Context context, Uri uri, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("progress", Integer.valueOf(i));
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static FileRow m5036c(Context context, Uri uri) {
        Cursor b = Files.m5034b(context, uri);
        int columnIndex = b.getColumnIndex("_idFiles");
        int columnIndex2 = b.getColumnIndex("filename");
        int columnIndex3 = b.getColumnIndex("filelength");
        int columnIndex4 = b.getColumnIndex("fileaddress");
        int columnIndex5 = b.getColumnIndex("server_thumbnail_address");
        int columnIndex6 = b.getColumnIndex("server_address");
        int columnIndex7 = b.getColumnIndex("filestatus");
        int columnIndex8 = b.getColumnIndex("thumbnailaddress");
        int columnIndex9 = b.getColumnIndex("multimediaduration");
        int columnIndex10 = b.getColumnIndex("progress");
        int columnIndex11 = b.getColumnIndex("audi_intime");
        int columnIndex12 = b.getColumnIndex("audio_state");
        if (b.moveToFirst()) {
            FileRow fileRow = new FileRow(b.getInt(columnIndex), b.getString(columnIndex2), b.getInt(columnIndex3), b.getString(columnIndex4), b.getString(columnIndex6), b.getInt(columnIndex7), b.getString(columnIndex8), b.getInt(columnIndex9), b.getInt(columnIndex10), b.getInt(columnIndex12), b.getInt(columnIndex11), b.getString(columnIndex5));
            b.close();
            return fileRow;
        }
        b.close();
        return null;
    }

    public static int m5037d(Context context, Uri uri, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("filestatus", Integer.valueOf(i));
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static int m5038e(Context context, Uri uri, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("multimediaduration", Integer.valueOf(i));
        return context.getContentResolver().update(uri, contentValues, null, null);
    }
}
