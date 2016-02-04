package com.devspark.robototextview.p013a;

import android.content.Context;
import android.graphics.Typeface;
import android.util.SparseArray;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: com.devspark.robototextview.a.b */
public class RobotoTypefaceManager {
    private static final SparseArray<Typeface> f1424a;

    static {
        f1424a = new SparseArray(22);
    }

    private static int m2216a(int i, int i2, int i3) {
        if (i == 0) {
            if (i2 == 0) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 4;
                    case VideoSize.CIF /*1*/:
                        return 5;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else if (i2 == 1) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 0;
                    case VideoSize.CIF /*1*/:
                        return 1;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else if (i2 == 2) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 2;
                    case VideoSize.CIF /*1*/:
                        return 3;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else if (i2 == 3) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 6;
                    case VideoSize.CIF /*1*/:
                        return 7;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else if (i2 == 4) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 8;
                    case VideoSize.CIF /*1*/:
                        return 9;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else if (i2 == 5) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 10;
                    case VideoSize.CIF /*1*/:
                        return 11;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else {
                throw new IllegalArgumentException("`textWeight` attribute value " + i2 + " is not supported for this font family " + i);
            }
        } else if (i == 1) {
            if (i2 == 0) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 14;
                    case VideoSize.CIF /*1*/:
                        return 15;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else if (i2 == 2) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 12;
                    case VideoSize.CIF /*1*/:
                        return 13;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else if (i2 == 4) {
                switch (i3) {
                    case VideoSize.QCIF /*0*/:
                        return 16;
                    case VideoSize.CIF /*1*/:
                        return 17;
                    default:
                        throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i + " and textWeight " + i2);
                }
            } else {
                throw new IllegalArgumentException("`textWeight` attribute value " + i2 + " is not supported for this font family " + i);
            }
        } else if (i != 2) {
            throw new IllegalArgumentException("Unknown `fontFamily` attribute value " + i);
        } else if (i3 != 0) {
            throw new IllegalArgumentException("`textStyle` attribute value " + i3 + " is not supported for this fontFamily " + i);
        } else if (i2 == 0) {
            return 20;
        } else {
            if (i2 == 1) {
                return 18;
            }
            if (i2 == 2) {
                return 19;
            }
            if (i2 == 4) {
                return 21;
            }
            throw new IllegalArgumentException("`textWeight` attribute value " + i2 + " is not supported for this font family " + i);
        }
    }

    public static Typeface m2217a(Context context, int i) throws IllegalArgumentException {
        Typeface typeface = (Typeface) f1424a.get(i);
        if (typeface != null) {
            return typeface;
        }
        typeface = RobotoTypefaceManager.m2219b(context, i);
        f1424a.put(i, typeface);
        return typeface;
    }

    public static Typeface m2218a(Context context, int i, int i2, int i3) throws IllegalArgumentException {
        return RobotoTypefaceManager.m2217a(context, RobotoTypefaceManager.m2216a(i, i2, i3));
    }

    private static Typeface m2219b(Context context, int i) throws IllegalArgumentException {
        String str;
        switch (i) {
            case VideoSize.QCIF /*0*/:
                str = "fonts/Roboto-Thin.ttf";
                break;
            case VideoSize.CIF /*1*/:
                str = "fonts/Roboto-ThinItalic.ttf";
                break;
            case VideoSize.HVGA /*2*/:
                str = "fonts/Roboto-Light.ttf";
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                str = "fonts/Roboto-LightItalic.ttf";
                break;
            case Version.API04_DONUT_16 /*4*/:
                str = "fonts/Roboto-Regular.ttf";
                break;
            case Version.API05_ECLAIR_20 /*5*/:
                str = "fonts/Roboto-Italic.ttf";
                break;
            case Version.API06_ECLAIR_201 /*6*/:
                str = "fonts/Roboto-Medium.ttf";
                break;
            case Version.API07_ECLAIR_21 /*7*/:
                str = "fonts/Roboto-MediumItalic.ttf";
                break;
            case Version.API08_FROYO_22 /*8*/:
                str = "fonts/Roboto-Bold.ttf";
                break;
            case Version.API09_GINGERBREAD_23 /*9*/:
                str = "fonts/Roboto-BoldItalic.ttf";
                break;
            case Version.API10_GINGERBREAD_MR1_233 /*10*/:
                str = "fonts/Roboto-Black.ttf";
                break;
            case Version.API11_HONEYCOMB_30 /*11*/:
                str = "fonts/Roboto-BlackItalic.ttf";
                break;
            case Version.API12_HONEYCOMB_MR1_31X /*12*/:
                str = "fonts/RobotoCondensed-Light.ttf";
                break;
            case Version.API13_HONEYCOMB_MR2_32 /*13*/:
                str = "fonts/RobotoCondensed-LightItalic.ttf";
                break;
            case Version.API14_ICE_CREAM_SANDWICH_40 /*14*/:
                str = "fonts/RobotoCondensed-Regular.ttf";
                break;
            case Version.API15_ICE_CREAM_SANDWICH_403 /*15*/:
                str = "fonts/RobotoCondensed-Italic.ttf";
                break;
            case Version.API16_JELLY_BEAN_41 /*16*/:
                str = "fonts/RobotoCondensed-Bold.ttf";
                break;
            case Version.API17_JELLY_BEAN_42 /*17*/:
                str = "fonts/RobotoCondensed-BoldItalic.ttf";
                break;
            case Version.API18_JELLY_BEAN_43 /*18*/:
                str = "fonts/RobotoSlab-Thin.ttf";
                break;
            case Version.API19_KITKAT_44 /*19*/:
                str = "fonts/RobotoSlab-Light.ttf";
                break;
            case 20:
                str = "fonts/RobotoSlab-Regular.ttf";
                break;
            case 21:
                str = "fonts/RobotoSlab-Bold.ttf";
                break;
            default:
                throw new IllegalArgumentException("Unknown `typeface` attribute value " + i);
        }
        return Typeface.createFromAsset(context.getAssets(), str);
    }
}
