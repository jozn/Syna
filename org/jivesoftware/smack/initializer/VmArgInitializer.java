package org.jivesoftware.smack.initializer;

import java.util.Collections;
import java.util.List;

public class VmArgInitializer extends UrlInitializer {
    protected String m5825a() {
        return System.getProperty("smack.provider.file");
    }

    public List<Exception> initialize() {
        if (m5825a() != null) {
            super.initialize();
        }
        return Collections.emptyList();
    }
}
