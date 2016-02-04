package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: android.support.v4.app.u */
final class FragmentTabHost implements Creator<SavedState> {
    FragmentTabHost() {
    }

    public SavedState m359a(Parcel parcel) {
        return new SavedState(null);
    }

    public SavedState[] m360a(int i) {
        return new SavedState[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m359a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m360a(i);
    }
}
