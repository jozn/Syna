package android.support.v4.p007b;

import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: android.support.v4.b.a */
public class ParcelableCompat {

    /* renamed from: android.support.v4.b.a.a */
    static class ParcelableCompat<T> implements Creator<T> {
        final ParcelableCompatCreatorCallbacks<T> f341a;

        public ParcelableCompat(ParcelableCompatCreatorCallbacks<T> parcelableCompatCreatorCallbacks) {
            this.f341a = parcelableCompatCreatorCallbacks;
        }

        public T createFromParcel(Parcel parcel) {
            return this.f341a.m374a(parcel, null);
        }

        public T[] newArray(int i) {
            return this.f341a.m375a(i);
        }
    }

    public static <T> Creator<T> m373a(ParcelableCompatCreatorCallbacks<T> parcelableCompatCreatorCallbacks) {
        if (VERSION.SDK_INT >= 13) {
            ParcelableCompatHoneycombMR2.m376a(parcelableCompatCreatorCallbacks);
        }
        return new ParcelableCompat(parcelableCompatCreatorCallbacks);
    }
}
