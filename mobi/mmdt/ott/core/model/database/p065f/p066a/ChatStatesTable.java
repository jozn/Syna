package mobi.mmdt.ott.core.model.database.p065f.p066a;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.f.a.c */
public class ChatStatesTable {
    public static void m5082a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE chat_states (_id INTEGER PRIMARY KEY AUTOINCREMENT,column_party TEXT,column_sender TEXT,column_state TEXT);");
    }

    public static void m5083a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.w(ChatStatesTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS chat_states");
        ChatStatesTable.m5082a(sQLiteDatabase);
    }
}
