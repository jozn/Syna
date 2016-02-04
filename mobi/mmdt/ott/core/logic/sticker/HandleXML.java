package mobi.mmdt.ott.core.logic.sticker;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import mobi.mmdt.ott.core.p051a.FileManager;
import org.jivesoftware.smackx.xdata.FormField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* renamed from: mobi.mmdt.ott.core.logic.sticker.c */
public class HandleXML {
    public static synchronized StickerGroupDataHolder m4851a(Context context, File file, String str) {
        StickerGroupDataHolder stickerGroupDataHolder;
        synchronized (HandleXML.class) {
            try {
                Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
                parse.getDocumentElement().normalize();
                Element documentElement = parse.getDocumentElement();
                String attribute = documentElement.getAttribute("version");
                String attribute2 = documentElement.getAttribute("package_number");
                String attribute3 = documentElement.getAttribute("sticker_count");
                String attribute4 = documentElement.getAttribute("price");
                String attribute5 = documentElement.getAttribute("designer");
                String attribute6 = documentElement.getAttribute(FormField.ELEMENT);
                String attribute7 = documentElement.getAttribute("name");
                String attribute8 = documentElement.getAttribute("thumbnail");
                String attribute9 = documentElement.getAttribute("description");
                ArrayList arrayList = new ArrayList();
                NodeList elementsByTagName = parse.getElementsByTagName("StickerItem");
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    Node item = elementsByTagName.item(i);
                    if (item.getNodeType() == (short) 1) {
                        Element element = (Element) item;
                        String attribute10 = element.getAttribute("sticker_number");
                        String attribute11 = element.getAttribute("ver-span");
                        String attribute12 = element.getAttribute("hor-span");
                        String attribute13 = element.getAttribute("x-axis");
                        String attribute14 = element.getAttribute("y-axis");
                        String textContent = element.getElementsByTagName("original-image-name").item(0).getTextContent();
                        String textContent2 = element.getElementsByTagName("thumbnail-image-name").item(0).getTextContent();
                        arrayList.add(new StickerItemDataHolder(attribute2, attribute10, attribute11, attribute12, attribute13, attribute14, textContent, textContent2, FileManager.m4437a(context).m4443b(attribute2, textContent2)));
                    }
                }
                File file2 = new File(FileManager.m4437a(context).m4443b(attribute2, attribute8));
                Log.d("FFF", "imgFile " + file2);
                Log.d("FFF", "imgFile " + file2.exists());
                if (file2 != null && file2.exists()) {
                    attribute2 = attribute;
                    stickerGroupDataHolder = new StickerGroupDataHolder(attribute2, Integer.parseInt(attribute2), Integer.parseInt(attribute3), attribute4, attribute5, attribute6, attribute7, attribute8, attribute9, arrayList, BitmapFactory.decodeFile(file2.getAbsolutePath()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            stickerGroupDataHolder = null;
        }
        return stickerGroupDataHolder;
    }
}
