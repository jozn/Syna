package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.widget.j */
public abstract class ResourceCursorAdapter extends CursorAdapter {
    private int f690j;
    private int f691k;
    private LayoutInflater f692l;

    public ResourceCursorAdapter(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.f691k = i;
        this.f690j = i;
        this.f692l = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View m1351a(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f692l.inflate(this.f690j, viewGroup, false);
    }

    public View m1352b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f692l.inflate(this.f691k, viewGroup, false);
    }
}
