package mobi.mmdt.ott.core.model.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import org.apache.http.protocol.HTTP;
import org.jivesoftware.smack.packet.Message;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

public class DatabaseContentProvider extends ContentProvider {
    private DatabaseHelper f3879a;

    private UriMatcher m4926a(String str) {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(str, "suncontacts", 1);
        uriMatcher.addURI(str, "suncontacts/filter/*", 3);
        uriMatcher.addURI(str, "suncontacts/#", 2);
        uriMatcher.addURI(str, "logs", 10);
        uriMatcher.addURI(str, "logs/group", 12);
        uriMatcher.addURI(str, "logs/#", 11);
        uriMatcher.addURI(str, "messages", 20);
        uriMatcher.addURI(str, "messages/file_message_join", 30);
        uriMatcher.addURI(str, "messages/file_message_join/#", 31);
        uriMatcher.addURI(str, "messages/conversation", 32);
        uriMatcher.addURI(str, "messages/conversations", 33);
        uriMatcher.addURI(str, "messages/#", 21);
        uriMatcher.addURI(str, "messages_groups", 34);
        uriMatcher.addURI(str, "messages_groups/#", 35);
        uriMatcher.addURI(str, "chat_states", 50);
        uriMatcher.addURI(str, "chat_states/#", 51);
        uriMatcher.addURI(str, "files", 40);
        uriMatcher.addURI(str, "files/#", 41);
        uriMatcher.addURI(str, "stickers", 60);
        uriMatcher.addURI(str, "stickers/#", 61);
        uriMatcher.addURI(str, "sticker_packages", 70);
        uriMatcher.addURI(str, "sticker_packages/#", 71);
        return uriMatcher;
    }

    public static final Uri m4927a(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "suncontacts");
    }

    public static final Uri m4928b(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "logs");
    }

    public static final Uri m4929c(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "messages");
    }

    public static final Uri m4930d(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "files");
    }

    public static final Uri m4931e(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "messages_groups");
    }

    public static final Uri m4932f(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "chat_states");
    }

    public static final Uri m4933g(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "stickers");
    }

    public static final Uri m4934h(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "sticker_packages");
    }

    public static final Uri m4935i(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "suncontacts" + "/" + "filter");
    }

    public static final Uri m4936j(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "messages" + "/" + "file_message_join");
    }

    public static final Uri m4937k(Context context) {
        return Uri.parse("content://" + context.getPackageName() + "/" + "messages" + "/" + "conversations");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int match = m4926a(getContext().getPackageName()).match(uri);
        SQLiteDatabase writableDatabase = this.f3879a.getWritableDatabase();
        String lastPathSegment;
        switch (match) {
            case VideoSize.CIF /*1*/:
                match = writableDatabase.delete("sun_contacts", str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                getContext().getContentResolver().notifyChange(m4928b(getContext()), null);
                break;
            case VideoSize.HVGA /*2*/:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.delete("sun_contacts", "_id=" + lastPathSegment, null) : writableDatabase.delete("sun_contacts", "_id=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                getContext().getContentResolver().notifyChange(m4928b(getContext()), null);
                break;
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                match = writableDatabase.delete("logs", str, strArr);
                break;
            case Version.API11_HONEYCOMB_30 /*11*/:
                lastPathSegment = uri.getLastPathSegment();
                if (!TextUtils.isEmpty(str)) {
                    match = writableDatabase.delete("logs", "_id=" + lastPathSegment + " and " + str, strArr);
                    break;
                }
                match = writableDatabase.delete("logs", "_id=" + lastPathSegment, null);
                break;
            case 20:
                match = writableDatabase.delete(Message.ELEMENT, str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 21:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.delete(Message.ELEMENT, "_id=" + lastPathSegment, null) : writableDatabase.delete(Message.ELEMENT, "_id=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 34:
                match = writableDatabase.delete("groups", str, strArr);
                getContext().getContentResolver().notifyChange(m4931e(getContext()), null);
                break;
            case 35:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.delete("groups", "_id=" + lastPathSegment, null) : writableDatabase.delete("groups", "_id=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4931e(getContext()), null);
                break;
            case 40:
                match = writableDatabase.delete("file", str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 41:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.delete("file", "_idFiles=" + lastPathSegment, null) : writableDatabase.delete("file", "_idFiles=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 50:
                match = writableDatabase.delete("chat_states", str, strArr);
                getContext().getContentResolver().notifyChange(m4932f(getContext()), null);
                break;
            case 51:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.delete("chat_states", "_id=" + lastPathSegment, null) : writableDatabase.delete("chat_states", "_id=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4932f(getContext()), null);
                break;
            case 60:
                match = writableDatabase.delete("stickers", str, strArr);
                break;
            case 70:
                match = writableDatabase.delete("sticker_packages", str, strArr);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return match;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri parse;
        int match = m4926a(getContext().getPackageName()).match(uri);
        SQLiteDatabase writableDatabase = this.f3879a.getWritableDatabase();
        switch (match) {
            case VideoSize.CIF /*1*/:
                parse = Uri.parse(uri + "/" + writableDatabase.insert("sun_contacts", null, contentValues));
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                getContext().getContentResolver().notifyChange(m4928b(getContext()), null);
                break;
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                parse = Uri.parse(uri + "/" + writableDatabase.insert("logs", null, contentValues));
                break;
            case 20:
                parse = Uri.parse(uri + "/" + writableDatabase.insert(Message.ELEMENT, null, contentValues));
                break;
            case 34:
                parse = Uri.parse(uri + "/" + writableDatabase.insert("groups", null, contentValues));
                getContext().getContentResolver().notifyChange(m4931e(getContext()), null);
                break;
            case 40:
                parse = Uri.parse(uri + "/" + writableDatabase.insert("file", null, contentValues));
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 50:
                parse = Uri.parse(uri + "/" + writableDatabase.insert("chat_states", null, contentValues));
                getContext().getContentResolver().notifyChange(m4932f(getContext()), null);
                break;
            case 60:
                parse = Uri.parse(uri + "/" + writableDatabase.insert("stickers", null, contentValues));
                getContext().getContentResolver().notifyChange(m4933g(getContext()), null);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 70:
                parse = Uri.parse(uri + "/" + writableDatabase.insert("sticker_packages", null, contentValues));
                getContext().getContentResolver().notifyChange(m4934h(getContext()), null);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return parse;
    }

    public boolean onCreate() {
        this.f3879a = new DatabaseHelper(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteDatabase writableDatabase = this.f3879a.getWritableDatabase();
        Cursor query;
        switch (m4926a(getContext().getPackageName()).match(uri)) {
            case VideoSize.CIF /*1*/:
                sQLiteQueryBuilder.setTables("sun_contacts");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4927a(getContext()));
                return query;
            case VideoSize.HVGA /*2*/:
                sQLiteQueryBuilder.setTables("sun_contacts");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4927a(getContext()));
                return query;
            case Version.API03_CUPCAKE_15 /*3*/:
                sQLiteQueryBuilder.setTables("sun_contacts");
                if (TextUtils.isEmpty(str)) {
                    query = sQLiteQueryBuilder.query(writableDatabase, strArr, "nick_name LIKE '%" + uri.getLastPathSegment() + "%'", null, null, null, str2);
                    query.setNotificationUri(getContext().getContentResolver(), m4927a(getContext()));
                    return query;
                }
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, "nick_name LIKE '%" + uri.getLastPathSegment() + "%' and " + str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4927a(getContext()));
                return query;
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                sQLiteQueryBuilder.setTables("logs LEFT JOIN sun_contacts ON logs.phonenumber == sun_contacts.phonenumber");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4928b(getContext()));
                return query;
            case Version.API11_HONEYCOMB_30 /*11*/:
                sQLiteQueryBuilder.setTables("logs");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4928b(getContext()));
                return query;
            case Version.API12_HONEYCOMB_MR1_31X /*12*/:
                sQLiteQueryBuilder.setTables("logs");
                query = sQLiteQueryBuilder.query(writableDatabase, null, null, null, "phonenumber", null, "date DESC " + str2);
                query.setNotificationUri(getContext().getContentResolver(), m4928b(getContext()));
                return query;
            case 20:
                sQLiteQueryBuilder.setTables(Message.ELEMENT);
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4929c(getContext()));
                return query;
            case 21:
                sQLiteQueryBuilder.setTables(Message.ELEMENT);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4929c(getContext()));
                return query;
            case 30:
                sQLiteQueryBuilder.setTables("(message LEFT JOIN file ON message.fileid == file._idFiles) LEFT JOIN sun_contacts ON sun_contacts.phonenumber == message.phonenumber_message");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4929c(getContext()));
                return query;
            case 31:
                sQLiteQueryBuilder.setTables("(message LEFT JOIN file ON message.fileid == file._idFiles) LEFT JOIN sun_contacts ON sun_contacts.phonenumber == message.phonenumber_message");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4929c(getContext()));
                return query;
            case HTTP.SP /*32*/:
                sQLiteQueryBuilder.setTables(Message.ELEMENT);
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, "party", null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4929c(getContext()));
                return query;
            case 33:
                sQLiteQueryBuilder.setTables("(( message LEFT JOIN sun_contacts ON message.phonenumber_message == sun_contacts.phonenumber ) ) LEFT JOIN groups ON groups.group_id == message.party");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, "message.party", null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4929c(getContext()));
                return query;
            case 34:
                sQLiteQueryBuilder.setTables("groups");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4931e(getContext()));
                return query;
            case 35:
                sQLiteQueryBuilder.setTables("groups");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4931e(getContext()));
                return query;
            case 40:
                sQLiteQueryBuilder.setTables("file");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4930d(getContext()));
                return query;
            case 41:
                sQLiteQueryBuilder.setTables("file");
                sQLiteQueryBuilder.appendWhere("_idFiles=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4930d(getContext()));
                return query;
            case 50:
                sQLiteQueryBuilder.setTables("chat_states");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4932f(getContext()));
                return query;
            case 51:
                sQLiteQueryBuilder.setTables("chat_states");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4932f(getContext()));
                return query;
            case 60:
                sQLiteQueryBuilder.setTables("stickers");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4933g(getContext()));
                return query;
            case 61:
                sQLiteQueryBuilder.setTables("stickers");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4933g(getContext()));
                return query;
            case 70:
                sQLiteQueryBuilder.setTables("sticker_packages");
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4934h(getContext()));
                return query;
            case 71:
                sQLiteQueryBuilder.setTables("sticker_packages");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                query = sQLiteQueryBuilder.query(writableDatabase, strArr, str, strArr2, null, null, str2);
                query.setNotificationUri(getContext().getContentResolver(), m4934h(getContext()));
                return query;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int match = m4926a(getContext().getPackageName()).match(uri);
        SQLiteDatabase writableDatabase = this.f3879a.getWritableDatabase();
        String lastPathSegment;
        switch (match) {
            case VideoSize.CIF /*1*/:
                match = writableDatabase.update("sun_contacts", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                getContext().getContentResolver().notifyChange(m4928b(getContext()), null);
                break;
            case VideoSize.HVGA /*2*/:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.update("sun_contacts", contentValues, "_id=" + lastPathSegment, null) : writableDatabase.update("sun_contacts", contentValues, "_id=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                getContext().getContentResolver().notifyChange(m4928b(getContext()), null);
                break;
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                match = writableDatabase.update("logs", contentValues, str, strArr);
                break;
            case Version.API11_HONEYCOMB_30 /*11*/:
                lastPathSegment = uri.getLastPathSegment();
                if (!TextUtils.isEmpty(str)) {
                    match = writableDatabase.update("logs", contentValues, "_id=" + lastPathSegment + " and " + str, strArr);
                    break;
                }
                match = writableDatabase.update("logs", contentValues, "_id=" + lastPathSegment, null);
                break;
            case 20:
                match = writableDatabase.update(Message.ELEMENT, contentValues, str, strArr);
                break;
            case 21:
                lastPathSegment = uri.getLastPathSegment();
                if (!TextUtils.isEmpty(str)) {
                    match = writableDatabase.update(Message.ELEMENT, contentValues, "_id=" + lastPathSegment + " and " + str, strArr);
                    break;
                }
                match = writableDatabase.update(Message.ELEMENT, contentValues, "_id=" + lastPathSegment, null);
                break;
            case 34:
                match = writableDatabase.update("groups", contentValues, str, strArr);
                break;
            case 35:
                lastPathSegment = uri.getLastPathSegment();
                if (!TextUtils.isEmpty(str)) {
                    match = writableDatabase.update("groups", contentValues, "_id=" + lastPathSegment + " and " + str, strArr);
                    break;
                }
                match = writableDatabase.update("groups", contentValues, "_id=" + lastPathSegment, null);
                break;
            case 40:
                match = writableDatabase.update("file", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 41:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.update("file", contentValues, "_idFiles=" + lastPathSegment, null) : writableDatabase.update("file", contentValues, "_idFiles=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4929c(getContext()), null);
                break;
            case 50:
                match = writableDatabase.update("chat_states", contentValues, str, strArr);
                getContext().getContentResolver().notifyChange(m4932f(getContext()), null);
                break;
            case 51:
                lastPathSegment = uri.getLastPathSegment();
                match = TextUtils.isEmpty(str) ? writableDatabase.update("chat_states", contentValues, "_id=" + lastPathSegment, null) : writableDatabase.update("chat_states", contentValues, "_id=" + lastPathSegment + " and " + str, strArr);
                getContext().getContentResolver().notifyChange(m4932f(getContext()), null);
                break;
            case 60:
                match = writableDatabase.update("stickers", contentValues, str, strArr);
                break;
            case 70:
                match = writableDatabase.update("sticker_packages", contentValues, str, strArr);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return match;
    }
}
