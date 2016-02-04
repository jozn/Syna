package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.Loader.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

/* renamed from: android.support.v4.content.e */
public class CursorLoader extends AsyncTaskLoader<Cursor> {
    final Loader f413f;
    Uri f414g;
    String[] f415h;
    String f416i;
    String[] f417j;
    String f418k;
    Cursor f419l;

    public CursorLoader(Context context, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        super(context);
        this.f413f = new Loader(this);
        this.f414g = uri;
        this.f415h = strArr;
        this.f416i = str;
        this.f417j = strArr2;
        this.f418k = str2;
    }

    public void m504a(Cursor cursor) {
        if (!m482n()) {
            Cursor cursor2 = this.f419l;
            this.f419l = cursor;
            if (m480l()) {
                super.m473b(cursor);
            }
            if (cursor2 != null && cursor2 != cursor && !cursor2.isClosed()) {
                cursor2.close();
            }
        } else if (cursor != null) {
            cursor.close();
        }
    }

    public /* synthetic */ void m505a(Object obj) {
        m507b((Cursor) obj);
    }

    public void m506a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.m496a(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("mUri=");
        printWriter.println(this.f414g);
        printWriter.print(str);
        printWriter.print("mProjection=");
        printWriter.println(Arrays.toString(this.f415h));
        printWriter.print(str);
        printWriter.print("mSelection=");
        printWriter.println(this.f416i);
        printWriter.print(str);
        printWriter.print("mSelectionArgs=");
        printWriter.println(Arrays.toString(this.f417j));
        printWriter.print(str);
        printWriter.print("mSortOrder=");
        printWriter.println(this.f418k);
        printWriter.print(str);
        printWriter.print("mCursor=");
        printWriter.println(this.f419l);
        printWriter.print(str);
        printWriter.print("mContentChanged=");
        printWriter.println(this.s);
    }

    public void m507b(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    public /* synthetic */ void m508b(Object obj) {
        m504a((Cursor) obj);
    }

    public /* synthetic */ Object m509d() {
        return m510f();
    }

    public Cursor m510f() {
        Cursor query = m478j().getContentResolver().query(this.f414g, this.f415h, this.f416i, this.f417j, this.f418k);
        if (query != null) {
            query.getCount();
            query.registerContentObserver(this.f413f);
        }
        return query;
    }

    protected void m511g() {
        if (this.f419l != null) {
            m504a(this.f419l);
        }
        if (m489u() || this.f419l == null) {
            m484p();
        }
    }

    protected void m512h() {
        m498b();
    }

    protected void m513i() {
        super.m477i();
        m512h();
        if (!(this.f419l == null || this.f419l.isClosed())) {
            this.f419l.close();
        }
        this.f419l = null;
    }
}
