package mobi.mmdt.ott.core.logic.p054a;

import java.util.ArrayList;
import mobi.mmdt.ott.core.logic.core.p056a.VoipConfig;
import org.p074b.JSONArray;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.core.logic.a.d */
class SocketIoListener implements Runnable {
    final /* synthetic */ SocketIoListener f3538a;
    private final /* synthetic */ JSONArray f3539b;

    SocketIoListener(SocketIoListener socketIoListener, JSONArray jSONArray) {
        this.f3538a = socketIoListener;
        this.f3539b = jSONArray;
    }

    public void run() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < this.f3539b.m5344a()) {
            try {
                JSONObject jSONObject = (JSONObject) this.f3539b.m5345a(i);
                int c = jSONObject.m5361c("port");
                String g = jSONObject.m5365g("server");
                String g2 = jSONObject.m5365g("user");
                String g3 = jSONObject.m5365g("pass");
                String g4 = jSONObject.m5365g("proto");
                switch (g4.hashCode()) {
                    case 114657:
                        if (!g4.equals("tcp")) {
                            break;
                        }
                        arrayList.add(new VoipConfig(g2, g3, g, c, 3));
                        break;
                    case 114939:
                        if (!g4.equals("tls")) {
                            break;
                        }
                        arrayList.add(new VoipConfig(g2, g3, g, c, 2));
                        break;
                    case 115649:
                        if (!g4.equals("udp")) {
                            break;
                        }
                        arrayList.add(new VoipConfig(g2, g3, g, c, 1));
                        break;
                    default:
                        break;
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.f3538a.f3537b != null) {
            this.f3538a.f3537b.m4519a(arrayList);
        }
    }
}
