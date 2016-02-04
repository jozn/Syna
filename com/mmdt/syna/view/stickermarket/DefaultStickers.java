package com.mmdt.syna.view.stickermarket;

import android.content.Context;
import mobi.mmdt.ott.core.logic.sticker.AsyncStickerPackageExtractor;
import mobi.mmdt.ott.core.model.database.p067g.StickerPackages;
import org.apache.http.HttpStatus;

/* renamed from: com.mmdt.syna.view.stickermarket.a */
public class DefaultStickers {
    public static void m3877a(Context context) {
        if (!StickerPackages.m5132a(context, "300")) {
            new AsyncStickerPackageExtractor(context, null).m4849a(context.getResources().openRawResource(2131099658), (int) HttpStatus.SC_MULTIPLE_CHOICES);
        }
    }
}
