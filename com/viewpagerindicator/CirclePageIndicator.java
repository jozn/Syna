package com.viewpagerindicator;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.viewpagerindicator.a */
class CirclePageIndicator implements Creator<SavedState> {
    CirclePageIndicator() {
    }

    public SavedState m4042a(Parcel parcel) {
        return new SavedState(null);
    }

    public SavedState[] m4043a(int i) {
        return new SavedState[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4042a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4043a(i);
    }
}
