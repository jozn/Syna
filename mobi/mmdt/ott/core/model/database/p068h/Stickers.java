package mobi.mmdt.ott.core.model.database.p068h;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;

/* renamed from: mobi.mmdt.ott.core.model.database.h.b */
public class Stickers {
    public static Uri m5146a(Context context, int i, int i2, String str, String str2, String str3, int i3, int i4, int i5, int i6) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ver_span", Integer.valueOf(i3));
        contentValues.put("hor_span", Integer.valueOf(i4));
        contentValues.put("x_axis", Integer.valueOf(i5));
        contentValues.put("y_axis", Integer.valueOf(i6));
        if (str3 != null) {
            contentValues.put("thumbnail_image_name", str3);
        }
        if (str2 != null) {
            contentValues.put("original_image_name", str2);
        }
        if (str != null) {
            contentValues.put("version", str);
        }
        contentValues.put("sticker_number", Integer.valueOf(i2));
        contentValues.put("package_number", Integer.valueOf(i));
        return context.getContentResolver().insert(DatabaseContentProvider.m4933g(context), contentValues);
    }

    public static void m5147a(Context context, int i, int i2) {
        context.getContentResolver().delete(DatabaseContentProvider.m4933g(context), "package_number =? and sticker_number =? ", new String[]{new StringBuilder(String.valueOf(i)).toString(), new StringBuilder(String.valueOf(i2)).toString()});
    }

    public static StickerRow[] m5148a(Context context, int i) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4933g(context), null, "package_number =? ", new String[]{new StringBuilder(String.valueOf(i)).toString()}, "sticker_number ASC ");
        if (query.moveToFirst()) {
            int i2 = 0;
            do {
                i2++;
            } while (query.moveToNext());
            StickerRow[] stickerRowArr = new StickerRow[i2];
            query.moveToFirst();
            int i3 = 0;
            while (true) {
                int i4 = i;
                stickerRowArr[i3] = new StickerRow(i4, query.getInt(query.getColumnIndex("sticker_number")), query.getString(query.getColumnIndex("version")), query.getInt(query.getColumnIndex("ver_span")), query.getInt(query.getColumnIndex("hor_span")), query.getInt(query.getColumnIndex("x_axis")), query.getInt(query.getColumnIndex("y_axis")), query.getString(query.getColumnIndex("original_image_name")), query.getString(query.getColumnIndex("thumbnail_image_name")));
                int i5 = i3 + 1;
                if (query.moveToNext()) {
                    i3 = i5;
                } else {
                    query.close();
                    return stickerRowArr;
                }
            }
        }
        StickerRow[] stickerRowArr2 = new StickerRow[0];
        query.close();
        return stickerRowArr2;
    }

    public static StickerRow m5149b(Context context, int i, int i2) {
        StickerRow stickerRow;
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4933g(context), null, "package_number =? and sticker_number =? ", new String[]{new StringBuilder(String.valueOf(i)).toString(), new StringBuilder(String.valueOf(i2)).toString()}, null);
        if (query.moveToFirst()) {
            int i3 = i;
            int i4 = i2;
            stickerRow = new StickerRow(i3, i4, query.getString(query.getColumnIndex("version")), query.getInt(query.getColumnIndex("ver_span")), query.getInt(query.getColumnIndex("hor_span")), query.getInt(query.getColumnIndex("x_axis")), query.getInt(query.getColumnIndex("y_axis")), query.getString(query.getColumnIndex("original_image_name")), query.getString(query.getColumnIndex("thumbnail_image_name")));
        } else {
            stickerRow = null;
        }
        query.close();
        return stickerRow;
    }

    public static boolean m5150c(Context context, int i, int i2) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4933g(context), null, "package_number =? and sticker_number =? ", new String[]{new StringBuilder(String.valueOf(i)).toString(), new StringBuilder(String.valueOf(i2)).toString()}, null);
        if (query.getCount() > 0) {
            query.close();
            return true;
        }
        query.close();
        return false;
    }
}
