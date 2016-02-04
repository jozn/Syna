package mobi.mmdt.ott.core.model.database.p065f;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.f.c */
public class MessagesTable {
    public static void m5121a(Context context, SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.w(MessagesTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        if (i2 != 6 || i != 1) {
            if ((i != 6 && i != 7 && i != 8 && i != 9) || (i2 != 7 && i2 != 8 && i2 != 9 && i2 != 10)) {
                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS message");
                sQLiteDatabase.execSQL("CREATE TABLE message (_id INTEGER PRIMARY KEY AUTOINCREMENT,file_type INTEGER,xmppid TEXT,party TEXT,fileid INTEGER,phonenumber_message TEXT,send_date TEXT,type INTEGER,date INTEGER,data BLOB,is_group TEXT,status INTEGER);");
            }
        }
    }

    public static void m5122a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE message (_id INTEGER PRIMARY KEY AUTOINCREMENT,file_type INTEGER,xmppid TEXT,party TEXT,fileid INTEGER,phonenumber_message TEXT,send_date TEXT,type INTEGER,date INTEGER,data BLOB,is_group TEXT,status INTEGER);");
    }
}
