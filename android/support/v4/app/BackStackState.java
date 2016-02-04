package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.BackStackRecord.BackStackRecord;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;

/* compiled from: BackStackRecord */
final class BackStackState implements Parcelable {
    public static final Creator<BackStackState> CREATOR;
    final int[] f119a;
    final int f120b;
    final int f121c;
    final String f122d;
    final int f123e;
    final int f124f;
    final CharSequence f125g;
    final int f126h;
    final CharSequence f127i;

    static {
        CREATOR = new BackStackRecord();
    }

    public BackStackState(Parcel parcel) {
        this.f119a = parcel.createIntArray();
        this.f120b = parcel.readInt();
        this.f121c = parcel.readInt();
        this.f122d = parcel.readString();
        this.f123e = parcel.readInt();
        this.f124f = parcel.readInt();
        this.f125g = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f126h = parcel.readInt();
        this.f127i = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }

    public BackStackState(FragmentManager fragmentManager, BackStackRecord backStackRecord) {
        int i = 0;
        for (BackStackRecord backStackRecord2 = backStackRecord.f254b; backStackRecord2 != null; backStackRecord2 = backStackRecord2.f244a) {
            if (backStackRecord2.f252i != null) {
                i += backStackRecord2.f252i.size();
            }
        }
        this.f119a = new int[(i + (backStackRecord.f256d * 7))];
        if (backStackRecord.f263k) {
            i = 0;
            for (BackStackRecord backStackRecord3 = backStackRecord.f254b; backStackRecord3 != null; backStackRecord3 = backStackRecord3.f244a) {
                int i2 = i + 1;
                this.f119a[i] = backStackRecord3.f246c;
                int i3 = i2 + 1;
                this.f119a[i2] = backStackRecord3.f247d != null ? backStackRecord3.f247d.f159o : -1;
                int i4 = i3 + 1;
                this.f119a[i3] = backStackRecord3.f248e;
                i2 = i4 + 1;
                this.f119a[i4] = backStackRecord3.f249f;
                i4 = i2 + 1;
                this.f119a[i2] = backStackRecord3.f250g;
                i2 = i4 + 1;
                this.f119a[i4] = backStackRecord3.f251h;
                if (backStackRecord3.f252i != null) {
                    int size = backStackRecord3.f252i.size();
                    i4 = i2 + 1;
                    this.f119a[i2] = size;
                    i2 = 0;
                    while (i2 < size) {
                        i3 = i4 + 1;
                        this.f119a[i4] = ((Fragment) backStackRecord3.f252i.get(i2)).f159o;
                        i2++;
                        i4 = i3;
                    }
                    i = i4;
                } else {
                    i = i2 + 1;
                    this.f119a[i2] = 0;
                }
            }
            this.f120b = backStackRecord.f261i;
            this.f121c = backStackRecord.f262j;
            this.f122d = backStackRecord.f265m;
            this.f123e = backStackRecord.f267o;
            this.f124f = backStackRecord.f268p;
            this.f125g = backStackRecord.f269q;
            this.f126h = backStackRecord.f270r;
            this.f127i = backStackRecord.f271s;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }

    public BackStackRecord m45a(FragmentManager fragmentManager) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        int i = 0;
        int i2 = 0;
        while (i2 < this.f119a.length) {
            BackStackRecord backStackRecord2 = new BackStackRecord();
            int i3 = i2 + 1;
            backStackRecord2.f246c = this.f119a[i2];
            if (FragmentManager.f287a) {
                Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + i + " base fragment #" + this.f119a[i3]);
            }
            int i4 = i3 + 1;
            i2 = this.f119a[i3];
            if (i2 >= 0) {
                backStackRecord2.f247d = (Fragment) fragmentManager.f293f.get(i2);
            } else {
                backStackRecord2.f247d = null;
            }
            i3 = i4 + 1;
            backStackRecord2.f248e = this.f119a[i4];
            i4 = i3 + 1;
            backStackRecord2.f249f = this.f119a[i3];
            i3 = i4 + 1;
            backStackRecord2.f250g = this.f119a[i4];
            int i5 = i3 + 1;
            backStackRecord2.f251h = this.f119a[i3];
            i4 = i5 + 1;
            int i6 = this.f119a[i5];
            if (i6 > 0) {
                backStackRecord2.f252i = new ArrayList(i6);
                i3 = 0;
                while (i3 < i6) {
                    if (FragmentManager.f287a) {
                        Log.v("FragmentManager", "Instantiate " + backStackRecord + " set remove fragment #" + this.f119a[i4]);
                    }
                    i5 = i4 + 1;
                    backStackRecord2.f252i.add((Fragment) fragmentManager.f293f.get(this.f119a[i4]));
                    i3++;
                    i4 = i5;
                }
            }
            backStackRecord.m213a(backStackRecord2);
            i++;
            i2 = i4;
        }
        backStackRecord.f261i = this.f120b;
        backStackRecord.f262j = this.f121c;
        backStackRecord.f265m = this.f122d;
        backStackRecord.f267o = this.f123e;
        backStackRecord.f263k = true;
        backStackRecord.f268p = this.f124f;
        backStackRecord.f269q = this.f125g;
        backStackRecord.f270r = this.f126h;
        backStackRecord.f271s = this.f127i;
        backStackRecord.m220b(1);
        return backStackRecord;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.f119a);
        parcel.writeInt(this.f120b);
        parcel.writeInt(this.f121c);
        parcel.writeString(this.f122d);
        parcel.writeInt(this.f123e);
        parcel.writeInt(this.f124f);
        TextUtils.writeToParcel(this.f125g, parcel, 0);
        parcel.writeInt(this.f126h);
        TextUtils.writeToParcel(this.f127i, parcel, 0);
    }
}
