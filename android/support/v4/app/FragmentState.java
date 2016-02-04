package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;

/* compiled from: Fragment */
final class FragmentState implements Parcelable {
    public static final Creator<FragmentState> CREATOR;
    final String f193a;
    final int f194b;
    final boolean f195c;
    final int f196d;
    final int f197e;
    final String f198f;
    final boolean f199g;
    final boolean f200h;
    final Bundle f201i;
    Bundle f202j;
    Fragment f203k;

    static {
        CREATOR = new Fragment();
    }

    public FragmentState(Parcel parcel) {
        boolean z = true;
        this.f193a = parcel.readString();
        this.f194b = parcel.readInt();
        this.f195c = parcel.readInt() != 0;
        this.f196d = parcel.readInt();
        this.f197e = parcel.readInt();
        this.f198f = parcel.readString();
        this.f199g = parcel.readInt() != 0;
        if (parcel.readInt() == 0) {
            z = false;
        }
        this.f200h = z;
        this.f201i = parcel.readBundle();
        this.f202j = parcel.readBundle();
    }

    public FragmentState(Fragment fragment) {
        this.f193a = fragment.getClass().getName();
        this.f194b = fragment.f159o;
        this.f195c = fragment.f168x;
        this.f196d = fragment.f135F;
        this.f197e = fragment.f136G;
        this.f198f = fragment.f137H;
        this.f199g = fragment.f140K;
        this.f200h = fragment.f139J;
        this.f201i = fragment.f161q;
    }

    public Fragment m122a(FragmentActivity fragmentActivity, Fragment fragment) {
        if (this.f203k != null) {
            return this.f203k;
        }
        if (this.f201i != null) {
            this.f201i.setClassLoader(fragmentActivity.getClassLoader());
        }
        this.f203k = Fragment.m47a((Context) fragmentActivity, this.f193a, this.f201i);
        if (this.f202j != null) {
            this.f202j.setClassLoader(fragmentActivity.getClassLoader());
            this.f203k.f157m = this.f202j;
        }
        this.f203k.m56a(this.f194b, fragment);
        this.f203k.f168x = this.f195c;
        this.f203k.f170z = true;
        this.f203k.f135F = this.f196d;
        this.f203k.f136G = this.f197e;
        this.f203k.f137H = this.f198f;
        this.f203k.f140K = this.f199g;
        this.f203k.f139J = this.f200h;
        this.f203k.f131B = fragmentActivity.f178b;
        if (FragmentManager.f287a) {
            Log.v("FragmentManager", "Instantiated fragment " + this.f203k);
        }
        return this.f203k;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.f193a);
        parcel.writeInt(this.f194b);
        parcel.writeInt(this.f195c ? 1 : 0);
        parcel.writeInt(this.f196d);
        parcel.writeInt(this.f197e);
        parcel.writeString(this.f198f);
        parcel.writeInt(this.f199g ? 1 : 0);
        if (!this.f200h) {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeBundle(this.f201i);
        parcel.writeBundle(this.f202j);
    }
}
