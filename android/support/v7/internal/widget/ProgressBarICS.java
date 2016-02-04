package android.support.v7.internal.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: android.support.v7.internal.widget.o */
final class ProgressBarICS implements Creator<SavedState> {
    ProgressBarICS() {
    }

    public SavedState m2072a(Parcel parcel) {
        return new SavedState(null);
    }

    public SavedState[] m2073a(int i) {
        return new SavedState[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m2072a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m2073a(i);
    }
}
