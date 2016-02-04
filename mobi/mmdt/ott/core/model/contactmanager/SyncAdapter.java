package mobi.mmdt.ott.core.model.contactmanager;

import android.accounts.Account;
import android.accounts.NetworkErrorException;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.SyncResult;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import mobi.mmdt.ott.core.UiRequests;
import mobi.mmdt.ott.core.model.database.p060a.AndroidContacts;
import mobi.mmdt.ott.core.p051a.Statics;
import org.p074b.JSONException;

/* renamed from: mobi.mmdt.ott.core.model.contactmanager.b */
public class SyncAdapter extends AbstractThreadedSyncAdapter {
    public static HashMap<String, User> f3871a;
    private final Context f3872b;

    public SyncAdapter(Context context, boolean z) {
        super(context, z);
        this.f3872b = context;
    }

    private long m4920a(long j) {
        Cursor query = this.f3872b.getContentResolver().query(RawContacts.CONTENT_URI, new String[]{"_id"}, "account_type='" + UiRequests.m4501b(this.f3872b) + "' AND " + "sync1" + "=?", new String[]{String.valueOf(j)}, null);
        try {
            long j2 = query.moveToFirst() ? query.getLong(0) : 0;
            if (query != null) {
                query.close();
            }
            return j2;
        } catch (Throwable th) {
            if (query != null) {
                query.close();
            }
        }
    }

    private ArrayList<ContentProviderOperation> m4921a(Account account, User user, String str) {
        if (m4920a(user.m4923a()) != 0) {
            return null;
        }
        ArrayList<ContentProviderOperation> arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI).withValue("sourceid", Integer.valueOf(0)).withValue("sync1", Long.valueOf(user.m4923a())).withValue("account_type", UiRequests.m4501b(this.f3872b)).withValue("account_name", user.m4925c()).build());
        if (user.m4924b() != null && user.m4924b().trim().length() > 0) {
            arrayList.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", user.m4924b()).build());
        }
        if (user.m4925c() != null && user.m4925c().trim().length() > 0) {
            arrayList.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", str).withValue("data2", Integer.valueOf(2)).build());
        }
        arrayList.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/vnd.com.mmdt.sync.profile").withValue("data2", user.m4924b()).withValue("data3", user.m4925c()).build());
        if (arrayList.isEmpty()) {
            return arrayList;
        }
        try {
            this.f3872b.getContentResolver().applyBatch("com.android.contacts", arrayList);
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    private void m4922a(Account account) throws GeneralSecurityException, NetworkErrorException, NameNotFoundException, JSONException, IOException {
        HashMap a;
        if (f3871a == null) {
            a = Statics.m4454a(this.f3872b, AndroidContacts.m4943a(getContext()));
        } else {
            a = f3871a;
        }
        f3871a = null;
        String[] strArr = (String[]) a.keySet().toArray(new String[a.size()]);
        for (int i = 0; i < strArr.length; i++) {
            m4921a(account, new User(Long.parseLong(strArr[i]), ((User) a.get(strArr[i])).m4924b(), strArr[i], null, null, 0), ((User) a.get(strArr[i])).m4925c());
        }
    }

    public void onPerformSync(Account account, Bundle bundle, String str, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        try {
            m4922a(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
