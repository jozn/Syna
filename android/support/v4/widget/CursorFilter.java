package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

/* renamed from: android.support.v4.widget.e */
class CursorFilter extends Filter {
    CursorFilter f686a;

    /* renamed from: android.support.v4.widget.e.a */
    interface CursorFilter {
        Cursor m1301a();

        Cursor m1302a(CharSequence charSequence);

        void m1303a(Cursor cursor);

        CharSequence m1304c(Cursor cursor);
    }

    CursorFilter(CursorFilter cursorFilter) {
        this.f686a = cursorFilter;
    }

    public CharSequence convertResultToString(Object obj) {
        return this.f686a.m1304c((Cursor) obj);
    }

    protected FilterResults performFiltering(CharSequence charSequence) {
        Cursor a = this.f686a.m1302a(charSequence);
        FilterResults filterResults = new FilterResults();
        if (a != null) {
            filterResults.count = a.getCount();
            filterResults.values = a;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        Cursor a = this.f686a.m1301a();
        if (filterResults.values != null && filterResults.values != a) {
            this.f686a.m1303a((Cursor) filterResults.values);
        }
    }
}
