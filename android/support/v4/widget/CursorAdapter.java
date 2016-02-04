package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.widget.CursorFilter.CursorFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

/* renamed from: android.support.v4.widget.d */
public abstract class CursorAdapter extends BaseAdapter implements CursorFilter, Filterable {
    protected boolean f677a;
    protected boolean f678b;
    protected Cursor f679c;
    protected Context f680d;
    protected int f681e;
    protected CursorAdapter f682f;
    protected DataSetObserver f683g;
    protected CursorFilter f684h;
    protected FilterQueryProvider f685i;

    /* renamed from: android.support.v4.widget.d.a */
    private class CursorAdapter extends ContentObserver {
        final /* synthetic */ CursorAdapter f675a;

        public CursorAdapter(CursorAdapter cursorAdapter) {
            this.f675a = cursorAdapter;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            this.f675a.m1313b();
        }
    }

    /* renamed from: android.support.v4.widget.d.b */
    private class CursorAdapter extends DataSetObserver {
        final /* synthetic */ CursorAdapter f676a;

        private CursorAdapter(CursorAdapter cursorAdapter) {
            this.f676a = cursorAdapter;
        }

        public void onChanged() {
            this.f676a.f677a = true;
            this.f676a.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.f676a.f677a = false;
            this.f676a.notifyDataSetInvalidated();
        }
    }

    public CursorAdapter(Context context, Cursor cursor, boolean z) {
        m1308a(context, cursor, z ? 1 : 2);
    }

    public Cursor m1305a() {
        return this.f679c;
    }

    public Cursor m1306a(CharSequence charSequence) {
        return this.f685i != null ? this.f685i.runQuery(charSequence) : this.f679c;
    }

    public abstract View m1307a(Context context, Cursor cursor, ViewGroup viewGroup);

    void m1308a(Context context, Cursor cursor, int i) {
        boolean z = true;
        if ((i & 1) == 1) {
            i |= 2;
            this.f678b = true;
        } else {
            this.f678b = false;
        }
        if (cursor == null) {
            z = false;
        }
        this.f679c = cursor;
        this.f677a = z;
        this.f680d = context;
        this.f681e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f682f = new CursorAdapter(this);
            this.f683g = new CursorAdapter();
        } else {
            this.f682f = null;
            this.f683g = null;
        }
        if (z) {
            if (this.f682f != null) {
                cursor.registerContentObserver(this.f682f);
            }
            if (this.f683g != null) {
                cursor.registerDataSetObserver(this.f683g);
            }
        }
    }

    public void m1309a(Cursor cursor) {
        Cursor b = m1311b(cursor);
        if (b != null) {
            b.close();
        }
    }

    public abstract void m1310a(View view, Context context, Cursor cursor);

    public Cursor m1311b(Cursor cursor) {
        if (cursor == this.f679c) {
            return null;
        }
        Cursor cursor2 = this.f679c;
        if (cursor2 != null) {
            if (this.f682f != null) {
                cursor2.unregisterContentObserver(this.f682f);
            }
            if (this.f683g != null) {
                cursor2.unregisterDataSetObserver(this.f683g);
            }
        }
        this.f679c = cursor;
        if (cursor != null) {
            if (this.f682f != null) {
                cursor.registerContentObserver(this.f682f);
            }
            if (this.f683g != null) {
                cursor.registerDataSetObserver(this.f683g);
            }
            this.f681e = cursor.getColumnIndexOrThrow("_id");
            this.f677a = true;
            notifyDataSetChanged();
            return cursor2;
        }
        this.f681e = -1;
        this.f677a = false;
        notifyDataSetInvalidated();
        return cursor2;
    }

    public View m1312b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return m1307a(context, cursor, viewGroup);
    }

    protected void m1313b() {
        if (this.f678b && this.f679c != null && !this.f679c.isClosed()) {
            this.f677a = this.f679c.requery();
        }
    }

    public CharSequence m1314c(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    public int getCount() {
        return (!this.f677a || this.f679c == null) ? 0 : this.f679c.getCount();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f677a) {
            return null;
        }
        this.f679c.moveToPosition(i);
        if (view == null) {
            view = m1312b(this.f680d, this.f679c, viewGroup);
        }
        m1310a(view, this.f680d, this.f679c);
        return view;
    }

    public Filter getFilter() {
        if (this.f684h == null) {
            this.f684h = new CursorFilter(this);
        }
        return this.f684h;
    }

    public Object getItem(int i) {
        if (!this.f677a || this.f679c == null) {
            return null;
        }
        this.f679c.moveToPosition(i);
        return this.f679c;
    }

    public long getItemId(int i) {
        return (this.f677a && this.f679c != null && this.f679c.moveToPosition(i)) ? this.f679c.getLong(this.f681e) : 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f677a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.f679c.moveToPosition(i)) {
            if (view == null) {
                view = m1307a(this.f680d, this.f679c, viewGroup);
            }
            m1310a(view, this.f680d, this.f679c);
            return view;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
    }

    public boolean hasStableIds() {
        return true;
    }
}
