package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: FragmentManager */
final class FragmentManagerState implements Parcelable {
    public static final Creator<FragmentManagerState> CREATOR;
    FragmentState[] f190a;
    int[] f191b;
    BackStackState[] f192c;

    static {
        CREATOR = new FragmentManager();
    }

    public FragmentManagerState(Parcel parcel) {
        this.f190a = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
        this.f191b = parcel.createIntArray();
        this.f192c = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.f190a, i);
        parcel.writeIntArray(this.f191b);
        parcel.writeTypedArray(this.f192c, i);
    }
}
