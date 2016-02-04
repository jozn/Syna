package mobi.mmdt.ott.core.model.database.p062c;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.c.c */
public class FilesTable {
    public static void m5039a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE file (_idFiles INTEGER PRIMARY KEY AUTOINCREMENT,filename TEXT,server_thumbnail_address TEXT,progress INTEGER,server_address TEXT,fileaddress TEXT,thumbnailaddress TEXT,filestatus INTEGER,filelength INTEGER,audio_state INTEGER,audi_intime INTEGER,multimediaduration INTEGER);");
    }

    public static void m5040a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 != 6 || i != 1) {
            if ((i != 6 && i != 7 && i != 8 && i != 9) || (i2 != 7 && i2 != 8 && i2 != 9 && i2 != 10)) {
                Log.w(FilesTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS file");
                FilesTable.m5039a(sQLiteDatabase);
            }
        }
    }
}
