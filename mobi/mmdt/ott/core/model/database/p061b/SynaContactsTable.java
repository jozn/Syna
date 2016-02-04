package mobi.mmdt.ott.core.model.database.p061b;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.b.f */
public class SynaContactsTable {
    public static void m5018a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE sun_contacts (_id INTEGER PRIMARY KEY AUTOINCREMENT,server_avatar_address TEXT,local_avatar_address TEXT,first_name TEXT,last_name TEXT,is_announcer TEXT default false , is_gift_request TEXT default false ,new_syna_user TEXT,nick_name TEXT,xmpp_phone_number TEXT,gender TEXT,email TEXT,presnece_status INTEGER,last_online_received_time INTEGER,phonenumber TEXT);");
    }

    public static void m5019a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if ((i != 6 && i != 7 && i != 9) || (i2 != 7 && i2 != 8 && i2 != 10)) {
            Log.w(SynaContactsTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sun_contacts");
            SynaContactsTable.m5018a(sQLiteDatabase);
        }
    }
}
