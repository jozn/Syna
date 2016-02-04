package org.jivesoftware.smackx.caps.cache;

import org.jivesoftware.smackx.disco.packet.DiscoverInfo;

/* renamed from: org.jivesoftware.smackx.caps.cache.a */
public interface EntityCapsPersistentCache {
    void addDiscoverInfoByNodePersistent(String str, DiscoverInfo discoverInfo);

    DiscoverInfo lookup(String str);
}
