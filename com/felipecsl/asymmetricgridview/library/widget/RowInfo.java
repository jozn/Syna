package com.felipecsl.asymmetricgridview.library.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.felipecsl.asymmetricgridview.library.widget.i */
class RowInfo implements Creator<RowInfo> {
    RowInfo() {
    }

    public RowInfo m2373a(Parcel parcel) {
        return new RowInfo(parcel);
    }

    public RowInfo[] m2374a(int i) {
        return new RowInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2373a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2374a(i);
    }
}
