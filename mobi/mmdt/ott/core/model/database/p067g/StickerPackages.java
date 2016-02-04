package mobi.mmdt.ott.core.model.database.p067g;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import org.jivesoftware.smackx.xdata.FormField;

/* renamed from: mobi.mmdt.ott.core.model.database.g.a */
public class StickerPackages {
    public static Uri m5131a(Context context, int i, String str, String str2, String str3, String str4, int i2, String str5, String str6, String str7) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("stickers_count", Integer.valueOf(i2));
        if (str6 != null) {
            contentValues.put("designer", str6);
        }
        if (str5 != null) {
            contentValues.put(FormField.ELEMENT, str5);
        }
        if (str3 != null) {
            contentValues.put("price", str3);
        }
        if (str != null) {
            contentValues.put("version", str);
        }
        contentValues.put("package_number", Integer.valueOf(i));
        if (str2 != null) {
            contentValues.put("name", str2);
        }
        if (str4 != null) {
            contentValues.put("package_thumbnail_image_name", str4);
        }
        if (str7 != null) {
            contentValues.put("description", str7);
        }
        return context.getContentResolver().insert(DatabaseContentProvider.m4934h(context), contentValues);
    }

    public static boolean m5132a(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4934h(context), null, "package_number =? ", new String[]{new StringBuilder(String.valueOf(str)).toString()}, null);
        boolean moveToFirst = query.moveToFirst();
        query.close();
        return moveToFirst;
    }

    public static StickerPackagesRow[] m5133a(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4934h(context), null, null, null, null);
        if (query.moveToFirst()) {
            int i = 0;
            do {
                i++;
            } while (query.moveToNext());
            StickerPackagesRow[] stickerPackagesRowArr = new StickerPackagesRow[i];
            query.moveToFirst();
            int i2 = 0;
            while (true) {
                stickerPackagesRowArr[i2] = new StickerPackagesRow(query.getString(query.getColumnIndex("version")), query.getInt(query.getColumnIndex("package_number")), query.getInt(query.getColumnIndex("stickers_count")), query.getString(query.getColumnIndex("price")), query.getString(query.getColumnIndex("designer")), query.getString(query.getColumnIndex(FormField.ELEMENT)), query.getString(query.getColumnIndex("name")), query.getString(query.getColumnIndex("package_thumbnail_image_name")), query.getString(query.getColumnIndex("description")));
                int i3 = i2 + 1;
                if (query.moveToNext()) {
                    i2 = i3;
                } else {
                    query.close();
                    return stickerPackagesRowArr;
                }
            }
        }
        StickerPackagesRow[] stickerPackagesRowArr2 = new StickerPackagesRow[0];
        query.close();
        return stickerPackagesRowArr2;
    }
}
