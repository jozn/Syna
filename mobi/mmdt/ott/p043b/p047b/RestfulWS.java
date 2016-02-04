package mobi.mmdt.ott.p043b.p047b;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* renamed from: mobi.mmdt.ott.b.b.c */
class RestfulWS implements HostnameVerifier {
    RestfulWS() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
