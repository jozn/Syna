package mobi.mmdt.ott.core.logic.p034c;

import android.content.Context;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: mobi.mmdt.ott.core.logic.c.d */
public class StickerDownloader {
    private static StickerDownloader f3621a;
    private ConcurrentHashMap<Integer, Downloader> f3622b;
    private Context f3623c;

    private StickerDownloader(Context context) {
        this.f3622b = new ConcurrentHashMap();
        this.f3623c = context;
    }

    public static StickerDownloader m4646a(Context context) {
        if (f3621a == null) {
            f3621a = new StickerDownloader(context);
        }
        return f3621a;
    }

    public Downloader m4647a(int i) {
        return (Downloader) this.f3622b.remove(Integer.valueOf(i));
    }

    public Downloader m4648a(int i, String str, String str2, LocalTransmitterListener localTransmitterListener) {
        Downloader downloader = new Downloader(this.f3623c, str, (long) i, -1, str2);
        downloader.m4638a(localTransmitterListener);
        this.f3622b.put(Integer.valueOf(i), downloader);
        return downloader;
    }

    public Downloader m4649b(int i) {
        return (Downloader) this.f3622b.get(Integer.valueOf(i));
    }
}
