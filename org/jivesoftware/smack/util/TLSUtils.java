package org.jivesoftware.smack.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* renamed from: org.jivesoftware.smack.util.j */
final class TLSUtils implements HostnameVerifier {
    TLSUtils() {
    }

    public boolean verify(String str, SSLSession sSLSession) {
        return true;
    }
}
