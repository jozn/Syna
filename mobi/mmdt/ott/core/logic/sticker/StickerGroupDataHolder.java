package mobi.mmdt.ott.core.logic.sticker;

import android.graphics.Bitmap;
import java.util.ArrayList;

/* renamed from: mobi.mmdt.ott.core.logic.sticker.e */
public class StickerGroupDataHolder {
    private String f3825a;
    private int f3826b;
    private int f3827c;
    private String f3828d;
    private String f3829e;
    private String f3830f;
    private String f3831g;
    private String f3832h;
    private String f3833i;
    private ArrayList<StickerItemDataHolder> f3834j;
    private Bitmap f3835k;

    public StickerGroupDataHolder(String str, int i, int i2, String str2, String str3, String str4, String str5, String str6, String str7, ArrayList<StickerItemDataHolder> arrayList, Bitmap bitmap) {
        this.f3825a = str;
        this.f3826b = i;
        this.f3827c = i2;
        this.f3828d = str2;
        this.f3829e = str3;
        this.f3830f = str4;
        this.f3831g = str5;
        this.f3832h = str6;
        this.f3833i = str7;
        this.f3834j = arrayList;
        this.f3835k = bitmap;
    }

    public String m4853a() {
        return this.f3833i;
    }

    public String m4854b() {
        return this.f3829e;
    }

    public String m4855c() {
        return this.f3830f;
    }

    public String m4856d() {
        return this.f3831g;
    }

    public int m4857e() {
        return this.f3826b;
    }

    public String m4858f() {
        return this.f3828d;
    }

    public int m4859g() {
        return this.f3827c;
    }

    public String m4860h() {
        return this.f3832h;
    }

    public String m4861i() {
        return this.f3825a;
    }

    public ArrayList<StickerItemDataHolder> m4862j() {
        return this.f3834j;
    }
}
