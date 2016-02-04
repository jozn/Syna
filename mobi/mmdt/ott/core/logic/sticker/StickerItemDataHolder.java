package mobi.mmdt.ott.core.logic.sticker;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: mobi.mmdt.ott.core.logic.sticker.f */
class StickerItemDataHolder implements Creator<StickerItemDataHolder> {
    StickerItemDataHolder() {
    }

    public StickerItemDataHolder m4863a(Parcel parcel) {
        return new StickerItemDataHolder(parcel);
    }

    public StickerItemDataHolder[] m4864a(int i) {
        return new StickerItemDataHolder[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4863a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4864a(i);
    }
}
