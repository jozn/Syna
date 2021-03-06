package org.jivesoftware.smack.im;

import org.jivesoftware.smack.initializer.UrlInitializer;

public class SmackImInitializer extends UrlInitializer {
    protected String getConfigUrl() {
        return "classpath:org.jivesoftware.smack.im/smackim.xml";
    }

    protected String getProvidersUrl() {
        return "classpath:org.jivesoftware.smack.im/smackim.providers";
    }
}
