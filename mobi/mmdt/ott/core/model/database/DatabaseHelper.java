package mobi.mmdt.ott.core.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import mobi.mmdt.ott.core.model.database.p061b.SynaContactsTable;
import mobi.mmdt.ott.core.model.database.p062c.FilesTable;
import mobi.mmdt.ott.core.model.database.p063d.GroupsTable;
import mobi.mmdt.ott.core.model.database.p064e.LogsTable;
import mobi.mmdt.ott.core.model.database.p065f.MessagesTable;
import mobi.mmdt.ott.core.model.database.p065f.p066a.ChatStatesTable;
import mobi.mmdt.ott.core.model.database.p067g.StickerPackagesTable;
import mobi.mmdt.ott.core.model.database.p068h.StickersTable;

/* renamed from: mobi.mmdt.ott.core.model.database.e */
public class DatabaseHelper extends SQLiteOpenHelper {
    private Context f3964a;
    private SQLiteDatabase f3965b;

    public DatabaseHelper(Context context) {
        super(context, "mainDatabase.db", null, 10);
        this.f3964a = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.f3965b = sQLiteDatabase;
        SynaContactsTable.m5018a(sQLiteDatabase);
        FilesTable.m5039a(sQLiteDatabase);
        LogsTable.m5074a(sQLiteDatabase);
        MessagesTable.m5122a(sQLiteDatabase);
        GroupsTable.m5058a(sQLiteDatabase);
        ChatStatesTable.m5082a(sQLiteDatabase);
        StickersTable.m5151a(sQLiteDatabase);
        StickerPackagesTable.m5136a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.f3965b = sQLiteDatabase;
        SynaContactsTable.m5019a(sQLiteDatabase, i, i2);
        FilesTable.m5040a(sQLiteDatabase, i, i2);
        LogsTable.m5075a(sQLiteDatabase, i, i2);
        MessagesTable.m5121a(this.f3964a, sQLiteDatabase, i, i2);
        GroupsTable.m5059a(sQLiteDatabase, i, i2);
        ChatStatesTable.m5083a(sQLiteDatabase, i, i2);
        StickersTable.m5152a(sQLiteDatabase, i, i2);
        StickerPackagesTable.m5137a(sQLiteDatabase, i, i2);
    }
}
