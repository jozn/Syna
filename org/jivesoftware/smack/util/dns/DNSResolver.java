package org.jivesoftware.smack.util.dns;

import java.util.List;

/* renamed from: org.jivesoftware.smack.util.dns.a */
public interface DNSResolver {
    List<SRVRecord> lookupSRVRecords(String str) throws Exception;
}
