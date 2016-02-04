package mobi.mmdt.ott.core.model.database.p064e;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.e.c */
public class LogsTable {
    public static void m5074a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE logs (_id INTEGER PRIMARY KEY AUTOINCREMENT,phonenumber TEXT,description TEXT,type INTEGER,date INTEGER,status INTEGER);");
    }

    public static void m5075a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 != 6 || i != 1) {
            if ((i != 6 && i != 7 && i != 8 && i != 9) || (i2 != 7 && i2 != 8 && i2 != 9 && i2 != 10)) {
                Log.w(LogsTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS logs");
                LogsTable.m5074a(sQLiteDatabase);
            }
        }
    }
}
