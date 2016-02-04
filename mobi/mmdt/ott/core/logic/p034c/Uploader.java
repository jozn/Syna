package mobi.mmdt.ott.core.logic.p034c;

import android.util.Base64;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Iterator;
import mobi.mmdt.ott.core.UiRequests;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.ott.p043b.p048c.WebServiceManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.p074b.JSONObject;

/* renamed from: mobi.mmdt.ott.core.logic.c.g */
class Uploader implements Runnable {
    final /* synthetic */ Uploader f3626a;

    Uploader(Uploader uploader) {
        this.f3626a = uploader;
    }

    public void run() {
        Iterator it = this.f3626a.j.iterator();
        while (it.hasNext()) {
            ((LocalTransmitterListener) it.next()).m3633a(this.f3626a.e);
        }
        try {
            HttpClient defaultHttpClient = new DefaultHttpClient();
            HttpUriRequest httpPost = new HttpPost(this.f3626a.f);
            File file = new File(this.f3626a.i);
            defaultHttpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, WebServiceManager.m4378a(this.f3626a.d, AppSettings.m4867a(this.f3626a.d).m4906p()));
            ContentBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY, Base64.encodeToString(file.getName().getBytes(), 0));
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            create.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            create.addPart("file", fileBody);
            HttpEntity build = create.build();
            defaultHttpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT, WebServiceManager.m4378a(this.f3626a.d, AppSettings.m4867a(this.f3626a.d).m4906p()));
            build.consumeContent();
            httpPost.setEntity(new Uploader(this, build, httpPost));
            httpPost.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
            Log.d("RestfulWS", "post WebService <> URL <" + this.f3626a.f + ">");
            this.f3626a.f3625l = defaultHttpClient.execute(httpPost);
            if (this.f3626a.a) {
                it = this.f3626a.j.iterator();
                while (it.hasNext()) {
                    ((LocalTransmitterListener) it.next()).m3638c(this.f3626a.e);
                }
                this.f3626a.g = false;
                return;
            }
            this.f3626a.h = 100;
            it = this.f3626a.j.iterator();
            while (it.hasNext()) {
                LocalTransmitterListener localTransmitterListener = (LocalTransmitterListener) it.next();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f3626a.f3625l.getEntity().getContent()));
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuilder.append(readLine);
                }
                Log.d("RestfulWS", "Receive WebService <" + stringBuilder.toString() + ">");
                localTransmitterListener.m3636a(UiRequests.m4503c() + "/files2/" + new JSONObject(stringBuilder.toString()).m5364f("file_id"), this.f3626a.e);
            }
            this.f3626a.g = false;
        } catch (Exception e) {
            Exception exception = e;
            exception.printStackTrace();
            Iterator it2 = this.f3626a.j.iterator();
            while (it2.hasNext()) {
                ((LocalTransmitterListener) it2.next()).m3635a(this.f3626a.e, exception);
            }
            this.f3626a.g = false;
        }
    }
}
