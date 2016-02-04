package mobi.mmdt.ott.core.logic.sticker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import mobi.mmdt.ott.core.model.database.p067g.StickerPackages;
import mobi.mmdt.ott.core.model.database.p068h.Stickers;
import mobi.mmdt.ott.core.p051a.FileManager;
import mobi.mmdt.ott.core.p051a.Unzipper;
import mobi.mmdt.ott.p043b.p050d.WebservicesTools;
import mobi.mmdt.p041a.FileUtility;
import org.jivesoftware.smackx.hoxt.packet.AbstractHttpOverXmpp.Xml;

/* renamed from: mobi.mmdt.ott.core.logic.sticker.b */
class AsyncStickerPackageExtractor implements Runnable {
    final /* synthetic */ AsyncStickerPackageExtractor f3822a;
    private final /* synthetic */ InputStream f3823b;
    private final /* synthetic */ int f3824c;

    AsyncStickerPackageExtractor(AsyncStickerPackageExtractor asyncStickerPackageExtractor, InputStream inputStream, int i) {
        this.f3822a = asyncStickerPackageExtractor;
        this.f3823b = inputStream;
        this.f3824c = i;
    }

    public void run() {
        if (this.f3823b != null) {
            try {
                StickerGroupDataHolder stickerGroupDataHolder;
                String i = FileManager.m4437a(this.f3822a.f3820a).m4450i(new StringBuilder(String.valueOf(this.f3824c)).toString());
                File file = new File(i);
                Unzipper.m4490a(this.f3823b, file);
                StickerGroupDataHolder stickerGroupDataHolder2 = null;
                if (file.isDirectory()) {
                    for (File file2 : file.listFiles()) {
                        if (file2.isDirectory()) {
                            file2.renameTo(new File(new StringBuilder(String.valueOf(i)).append(File.separator).append(this.f3824c).append("_").append(WebservicesTools.m4392a(this.f3822a.f3820a)).toString()));
                        } else if (FileUtility.m4095c(file2.getPath()).equals(Xml.ELEMENT)) {
                            stickerGroupDataHolder2 = HandleXML.m4851a(this.f3822a.f3820a, file2, WebservicesTools.m4392a(this.f3822a.f3820a));
                        }
                    }
                    stickerGroupDataHolder = stickerGroupDataHolder2;
                } else {
                    stickerGroupDataHolder = null;
                }
                if (!(stickerGroupDataHolder == null || StickerPackages.m5132a(this.f3822a.f3820a, new StringBuilder(String.valueOf(stickerGroupDataHolder.m4857e())).toString()))) {
                    StickerPackages.m5131a(this.f3822a.f3820a, stickerGroupDataHolder.m4857e(), stickerGroupDataHolder.m4861i(), stickerGroupDataHolder.m4856d(), stickerGroupDataHolder.m4858f(), stickerGroupDataHolder.m4860h(), stickerGroupDataHolder.m4859g(), stickerGroupDataHolder.m4855c(), stickerGroupDataHolder.m4854b(), stickerGroupDataHolder.m4853a());
                    Iterator it = stickerGroupDataHolder.m4862j().iterator();
                    while (it.hasNext()) {
                        StickerItemDataHolder stickerItemDataHolder = (StickerItemDataHolder) it.next();
                        Stickers.m5147a(this.f3822a.f3820a, stickerItemDataHolder.m4843j(), stickerItemDataHolder.m4838e());
                        Stickers.m5146a(this.f3822a.f3820a, stickerItemDataHolder.m4843j(), stickerItemDataHolder.m4838e(), stickerGroupDataHolder.m4861i(), stickerItemDataHolder.m4837d(), stickerItemDataHolder.m4839f(), stickerItemDataHolder.m4840g(), stickerItemDataHolder.m4836c(), stickerItemDataHolder.m4841h(), stickerItemDataHolder.m4842i());
                    }
                }
                if (i != null) {
                    File file3 = new File(i);
                    if (file3 != null && file3.isFile()) {
                        file3.delete();
                    }
                }
                if (this.f3822a.f3821b == null) {
                    return;
                }
                if (stickerGroupDataHolder != null) {
                    this.f3822a.f3821b.m4852a(stickerGroupDataHolder.m4857e());
                } else {
                    this.f3822a.f3821b.m4852a(-1);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
