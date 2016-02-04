package mobi.mmdt.ott.core.model.database.p065f;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import java.util.ArrayList;
import mobi.mmdt.ott.core.model.database.DatabaseContentProvider;
import mobi.mmdt.ott.core.model.database.p062c.Files;
import mobi.mmdt.ott.core.model.database.p063d.Groups;
import mobi.mmdt.p041a.DateAndTime;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;

/* renamed from: mobi.mmdt.ott.core.model.database.f.b */
public class Messages {
    public static int m5094a(Context context, Uri uri, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(i));
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static int m5095a(Context context, Uri uri, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("xmppid", str);
        return context.getContentResolver().update(uri, contentValues, null, null);
    }

    public static long m5096a(Context context, long j) {
        Uri parse = Uri.parse(DatabaseContentProvider.m4929c(context) + "/" + j);
        Uri c = Messages.m5112c(context, parse);
        if (c != null) {
            Files.m5026a(context, c);
        }
        return (long) context.getContentResolver().delete(parse, null, null);
    }

    public static Cursor m5097a(Context context, Uri uri) {
        return context.getContentResolver().query(uri, null, null, null, null);
    }

    public static Uri m5098a(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "xmppid =? ", new String[]{str}, null);
        int columnIndex = query.getColumnIndex("_id");
        if (query.moveToFirst()) {
            columnIndex = query.getInt(columnIndex);
            query.close();
            return Uri.parse(DatabaseContentProvider.m4929c(context) + "/" + columnIndex);
        }
        query.close();
        return null;
    }

    public static Uri m5099a(Context context, String str, String str2, String str3, int i, long j, byte[] bArr, int i2, int i3, int i4, String str4, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataPacketExtension.ELEMENT, bArr);
        contentValues.put("date", Long.valueOf(j));
        contentValues.put("fileid", Integer.valueOf(i3));
        contentValues.put("party", str3);
        contentValues.put("phonenumber_message", str2);
        contentValues.put("party", str3);
        contentValues.put(Status.ELEMENT, Integer.valueOf(i2));
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("xmppid", str);
        contentValues.put("file_type", Integer.valueOf(i4));
        contentValues.put("send_date", str4);
        if (z) {
            contentValues.put("is_group", "true");
        } else {
            contentValues.put("is_group", "false");
        }
        return context.getContentResolver().insert(DatabaseContentProvider.m4929c(context), contentValues);
    }

    public static Uri m5100a(Context context, String str, String str2, String str3, int i, long j, byte[] bArr, int i2, String str4, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataPacketExtension.ELEMENT, bArr);
        contentValues.put("date", Long.valueOf(j));
        contentValues.put("send_date", str4);
        contentValues.put("fileid", Integer.valueOf(-1));
        contentValues.put("phonenumber_message", str2);
        contentValues.put("party", str3);
        contentValues.put(Status.ELEMENT, Integer.valueOf(i2));
        contentValues.put("type", Integer.valueOf(i));
        contentValues.put("file_type", Integer.valueOf(1));
        contentValues.put("xmppid", str);
        if (z) {
            contentValues.put("is_group", "true");
        } else {
            contentValues.put("is_group", "false");
        }
        return context.getContentResolver().insert(DatabaseContentProvider.m4929c(context), contentValues);
    }

    public static CursorLoader m5101a(Context context, int i) {
        return new CursorLoader(context, DatabaseContentProvider.m4937k(context), new String[]{"message._id as _id", "message.phonenumber_message", "type", Status.ELEMENT, DataPacketExtension.ELEMENT, "group_id", "is_group", "party", "is_room_public", "room_subject_group", "local_picture", "server_picture", "descripton", "MAX (send_date) as MAX_DATE", "nick_name", "presnece_status", "local_avatar_address", "server_avatar_address"}, Messages.m5118e(context), null, "send_date DESC LIMIT " + i);
    }

    public static CursorLoader m5102a(Context context, String str, int i) {
        if (str == null) {
            throw new NullPointerException();
        }
        int f = Messages.m5120f(context, str);
        int i2 = f > i ? f - i : 0;
        return new CursorLoader(context, DatabaseContentProvider.m4936j(context), new String[]{"fileaddress", "file_type", "filestatus", "progress", "multimediaduration", "audio_state", "filename", "message._id as _id", "filestatus", "local_avatar_address", "thumbnailaddress", DataPacketExtension.ELEMENT, "send_date", Status.ELEMENT, "type", "message.phonenumber_message", "file_type", "nick_name", "filelength", "filestatus"}, "party =? ", new String[]{str}, "send_date ASC LIMIT " + (1000000 + i) + " OFFSET " + i2);
    }

    public static void m5103a(Context context) {
        context.getContentResolver().delete(DatabaseContentProvider.m4929c(context), null, null);
    }

    public static boolean m5104a(Context context, String str, String str2) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "type =? and xmppid =? and party =? ", new String[]{"1", str, str2}, null);
        boolean z = query.getCount() != 0;
        query.close();
        return z;
    }

    public static boolean m5105a(Context context, String str, String str2, String str3) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "send_date =? and party =? and phonenumber_message =? ", new String[]{str, str2, str3}, null);
        boolean z = query.getCount() != 0;
        query.close();
        return z;
    }

    public static int m5106b(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "party =? and status =? ", new String[]{str, "3"}, null);
        int count = query.getCount();
        query.close();
        return count;
    }

    public static CursorLoader m5107b(Context context) {
        return new CursorLoader(context, DatabaseContentProvider.m4937k(context), new String[]{"message._id as _id", "message.phonenumber_message", "type", Status.ELEMENT, DataPacketExtension.ELEMENT, "group_id", "is_group", "party", "user_privilage", "user_role", "group_nick_name", "followers_count", "room_subject_group", "local_picture", "server_picture", "descripton", "MAX (send_date) as MAX_DATE", "nick_name", "presnece_status", "local_avatar_address", "server_avatar_address"}, "is_room_public =? ", new String[]{"true"}, "send_date DESC ");
    }

    public static ArrayList<TextMessageDataHolder> m5108b(Context context, String str, int i) {
        if (str == null) {
            throw new NullPointerException();
        }
        int i2;
        ArrayList<TextMessageDataHolder> arrayList = new ArrayList();
        int f = Messages.m5120f(context, str);
        int i3 = f > i ? f - i : 0;
        if (i <= 20) {
            i2 = 20;
        } else if (i - f > 30) {
            return arrayList;
        } else {
            i2 = 30;
        }
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4936j(context), null, "party =? ", new String[]{str}, "send_date ASC LIMIT " + i2 + " OFFSET " + i3);
        if (query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("_id");
            int columnIndex2 = query.getColumnIndex(DataPacketExtension.ELEMENT);
            f = query.getColumnIndex("file_type");
            int columnIndex3 = query.getColumnIndex("send_date");
            do {
                if (query.getInt(f) == 1) {
                    arrayList.add(new TextMessageDataHolder(query.getInt(columnIndex), DateAndTime.m4085a(query.getLong(columnIndex3)), query.getBlob(columnIndex2)));
                }
            } while (query.moveToNext());
        }
        query.close();
        return arrayList;
    }

    public static MessageRow m5109b(Context context, Uri uri) {
        Cursor a = Messages.m5097a(context, uri);
        int columnIndex = a.getColumnIndex("_id");
        int columnIndex2 = a.getColumnIndex("phonenumber_message");
        int columnIndex3 = a.getColumnIndex("type");
        int columnIndex4 = a.getColumnIndex("date");
        int columnIndex5 = a.getColumnIndex(DataPacketExtension.ELEMENT);
        int columnIndex6 = a.getColumnIndex(Status.ELEMENT);
        int columnIndex7 = a.getColumnIndex("xmppid");
        int columnIndex8 = a.getColumnIndex("fileid");
        int columnIndex9 = a.getColumnIndex("file_type");
        int columnIndex10 = a.getColumnIndex("party");
        int columnIndex11 = a.getColumnIndex("is_group");
        int columnIndex12 = a.getColumnIndex("send_date");
        if (a.moveToFirst()) {
            boolean z = false;
            if (a.getString(columnIndex11).equals("true")) {
                z = true;
            }
            MessageRow messageRow = new MessageRow(a.getLong(columnIndex), a.getString(columnIndex7), a.getString(columnIndex2), a.getString(columnIndex10), a.getLong(columnIndex4), a.getInt(columnIndex3), a.getInt(columnIndex6), a.getBlob(columnIndex5), a.getInt(columnIndex8), a.getInt(columnIndex9), z, a.getString(columnIndex12));
            a.close();
            return messageRow;
        }
        a.close();
        return null;
    }

    public static Uri[] m5110b(Context context, long j) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "type =?  and status =?  and send_date > ?", new String[]{"0", "5", new StringBuilder(String.valueOf(j)).toString()}, "send_date DESC ");
        Uri[] uriArr = new Uri[query.getCount()];
        int columnIndex = query.getColumnIndex("_id");
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                uriArr[i] = Uri.parse(DatabaseContentProvider.m4929c(context) + "/" + query.getInt(columnIndex));
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return uriArr;
    }

    public static int m5111c(Context context, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Status.ELEMENT, Integer.valueOf(4));
        return context.getContentResolver().update(DatabaseContentProvider.m4929c(context), contentValues, "status =?  and party =?", new String[]{"3", str});
    }

    public static Uri m5112c(Context context, Uri uri) {
        Cursor query = context.getContentResolver().query(uri, null, null, null, null);
        int columnIndex = query.getColumnIndex("fileid");
        if (query.moveToFirst()) {
            columnIndex = query.getInt(columnIndex);
            query.close();
            return columnIndex == -1 ? null : Uri.parse(DatabaseContentProvider.m4930d(context) + "/" + columnIndex);
        } else {
            query.close();
            return null;
        }
    }

    public static CursorLoader m5113c(Context context) {
        return new CursorLoader(context, DatabaseContentProvider.m4937k(context), new String[]{"message._id as _id", "message.phonenumber_message", "type", Status.ELEMENT, DataPacketExtension.ELEMENT, "group_id", "is_room_public", "is_group", "party", "room_subject_group", "local_picture", "server_picture", "descripton", "MAX (send_date) as MAX_DATE", "nick_name", "presnece_status", "local_avatar_address", "server_avatar_address"}, Messages.m5118e(context), null, "send_date DESC ");
    }

    public static Uri[] m5114c(Context context, long j) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "type =?  and status =?  and send_date < ?", new String[]{"0", "5", new StringBuilder(String.valueOf(j)).toString()}, "send_date DESC ");
        Uri[] uriArr = new Uri[query.getCount()];
        int columnIndex = query.getColumnIndex("_id");
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                uriArr[i] = Uri.parse(DatabaseContentProvider.m4929c(context) + "/" + query.getInt(columnIndex));
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return uriArr;
    }

    public static Uri[] m5115c(Context context, String str, int i) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "status =?  and party =?", new String[]{"2", str}, "send_date DESC limit " + i);
        Uri[] uriArr = new Uri[query.getCount()];
        int columnIndex = query.getColumnIndex("_id");
        if (query.moveToFirst()) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                uriArr[i2] = Uri.parse(DatabaseContentProvider.m4929c(context) + "/" + query.getInt(columnIndex));
                if (!query.moveToNext()) {
                    break;
                }
                i2 = i3;
            }
        }
        query.close();
        return uriArr;
    }

    public static long m5116d(Context context, String str) {
        return (long) context.getContentResolver().delete(DatabaseContentProvider.m4929c(context), "party =? ", new String[]{str});
    }

    public static Uri[] m5117d(Context context) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, Messages.m5118e(context) + " and " + Status.ELEMENT + " =? ", new String[]{"3"}, null);
        int columnIndex = query.getColumnIndex("_id");
        Uri[] uriArr = new Uri[query.getCount()];
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                uriArr[i] = Uri.parse(DatabaseContentProvider.m4929c(context) + "/" + query.getInt(columnIndex));
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return uriArr;
    }

    private static String m5118e(Context context) {
        String[] d = Groups.m5057d(context);
        String str = "";
        if (d != null && d.length > 0) {
            Object stringBuilder;
            for (int i = 0; i < d.length - 1; i++) {
                stringBuilder = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(d[i]).append("',").toString();
            }
            str = new StringBuilder(String.valueOf(stringBuilder)).append("'").append(d[d.length - 1]).append("'").toString();
        }
        return "party not in (" + str + ") ";
    }

    public static Uri[] m5119e(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), null, "type =?  and status =?  and party =?", new String[]{"0", "2", str}, "send_date DESC ");
        Uri[] uriArr = new Uri[query.getCount()];
        int columnIndex = query.getColumnIndex("_id");
        if (query.moveToFirst()) {
            int i = 0;
            while (true) {
                int i2 = i + 1;
                uriArr[i] = Uri.parse(DatabaseContentProvider.m4929c(context) + "/" + query.getInt(columnIndex));
                if (!query.moveToNext()) {
                    break;
                }
                i = i2;
            }
        }
        query.close();
        return uriArr;
    }

    private static int m5120f(Context context, String str) {
        Cursor query = context.getContentResolver().query(DatabaseContentProvider.m4929c(context), new String[]{"_id"}, "party =? ", new String[]{str}, null);
        int count = query.getCount();
        query.close();
        return count;
    }
}
