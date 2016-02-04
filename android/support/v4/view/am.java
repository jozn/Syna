package android.support.v4.view;

import android.os.Parcel;
import android.support.v4.p007b.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.ViewPager.SavedState;

/* compiled from: ViewPager */
final class am implements ParcelableCompatCreatorCallbacks<SavedState> {
    am() {
    }

    public /* synthetic */ Object m931a(Parcel parcel, ClassLoader classLoader) {
        return m933b(parcel, classLoader);
    }

    public /* synthetic */ Object[] m932a(int i) {
        return m934b(i);
    }

    public SavedState m933b(Parcel parcel, ClassLoader classLoader) {
        return new SavedState(parcel, classLoader);
    }

    public SavedState[] m934b(int i) {
        return new SavedState[i];
    }
}
