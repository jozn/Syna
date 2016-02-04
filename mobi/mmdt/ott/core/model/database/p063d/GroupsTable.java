package mobi.mmdt.ott.core.model.database.p063d;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/* renamed from: mobi.mmdt.ott.core.model.database.d.c */
public class GroupsTable {
    public static void m5058a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE groups (_id INTEGER PRIMARY KEY AUTOINCREMENT,room_subject_group TEXT,followers_count TEXT,group_nick_name TEXT,group_id TEXT unique,is_followed_by_me TEXT,user_privilage INTEGER,descripton TEXT,is_room_public TEXT default false,is_room_mute TEXT default false,user_role INTEGER,local_picture TEXT,server_picture TEXT);");
    }

    public static void m5059a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        Log.w(GroupsTable.class.getName(), "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS groups");
        GroupsTable.m5058a(sQLiteDatabase);
    }
}
