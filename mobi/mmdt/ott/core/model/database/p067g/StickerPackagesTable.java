package mobi.mmdt.ott.core.model.database.p067g;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.g.c */
public class StickerPackagesTable {
    public static void m5136a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE sticker_packages (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,hidden TEXT DEFAULT false,package_number INTEGER,stickers_count INTEGER,price TEXT,designer TEXT,field TEXT,package_thumbnail_image_name TEXT,version TEXT,description TEXT, UNIQUE (package_number));");
    }

    public static void m5137a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != 9 || i2 != 10) {
            Log.w(StickerPackagesTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sticker_packages");
            StickerPackagesTable.m5136a(sQLiteDatabase);
        }
    }
}
