package mobi.mmdt.ott.core.model.p059b;

import android.content.Context;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilderFactory;
import mobi.mmdt.ott.core.R.R;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* renamed from: mobi.mmdt.ott.core.model.b.a */
public class RateLoader {
    public static synchronized String m4917a(Context context, String str) {
        String str2;
        synchronized (RateLoader.class) {
            String str3 = "";
            str2 = "";
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.operators_list)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    try {
                        String[] split = readLine.split(",");
                        if (str.startsWith(split[1]) && split[1].length() > r1.length()) {
                            str3 = split[1];
                            str2 = split[0];
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (str2.length() <= 0) {
                    str2 = "NA";
                }
            }
        }
        return str2;
    }

    public static synchronized RateObject m4918a(Context context, String str, String str2) {
        RateObject rateObject;
        synchronized (RateLoader.class) {
            RateObject rateObject2 = new RateObject(str, str2, new HashMap());
            Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(context.getResources().openRawResource(R.rates));
            parse.getDocumentElement().normalize();
            NodeList elementsByTagName = parse.getElementsByTagName("Rate");
            int i = 0;
            while (i < elementsByTagName.getLength()) {
                try {
                    Node item = elementsByTagName.item(i);
                    if (item.getNodeType() == (short) 1) {
                        if (((Element) item).getAttribute("CountryCode").equals(str)) {
                            NodeList childNodes = item.getChildNodes();
                            HashMap hashMap = new HashMap();
                            for (int i2 = 0; i2 < childNodes.getLength(); i2++) {
                                if (childNodes.item(i2).getNodeType() == (short) 1) {
                                    hashMap.put(childNodes.item(i2).getNodeName(), childNodes.item(i2).getTextContent());
                                }
                            }
                            rateObject = new RateObject(str, str2, hashMap);
                        }
                    }
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rateObject = rateObject2;
        }
        return rateObject;
    }
}
