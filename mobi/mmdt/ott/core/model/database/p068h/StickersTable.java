package mobi.mmdt.ott.core.model.database.p068h;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.h.c */
public class StickersTable {
    public static void m5151a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE stickers (_id INTEGER PRIMARY KEY AUTOINCREMENT,sticker_number INTEGER,package_number INTEGER,ver_span INTEGER,hor_span INTEGER,x_axis INTEGER,y_axis INTEGER,original_image_name TEXT,thumbnail_image_name TEXT,version TEXT, UNIQUE (package_number , sticker_number));");
    }

    public static void m5152a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i != 9 || i2 != 10) {
            Log.w(StickersTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS stickers");
            StickersTable.m5151a(sQLiteDatabase);
        }
    }
}
