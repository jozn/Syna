package org.jivesoftware.smack.util.dns.minidns;

import java.util.LinkedList;
import java.util.List;
import org.jivesoftware.smack.initializer.SmackInitializer;
import org.jivesoftware.smack.util.DNSUtil;
import org.jivesoftware.smack.util.dns.DNSResolver;
import org.jivesoftware.smack.util.dns.SRVRecord;
import org.p075c.p076a.p077a.ExpirationCache;
import p000a.p001a.p002a.Client;
import p000a.p001a.p002a.DNSCache;
import p000a.p001a.p002a.DNSMessage;
import p000a.p001a.p002a.Question;
import p000a.p001a.p002a.Record;
import p000a.p001a.p002a.p003a.SRV;

public class MiniDnsResolver implements SmackInitializer, DNSResolver {
    private static final long ONE_DAY = 86400000;
    private static final ExpirationCache<Question, DNSMessage> cache;
    private static final MiniDnsResolver instance;
    private final Client client;

    /* renamed from: org.jivesoftware.smack.util.dns.minidns.MiniDnsResolver.1 */
    class C01881 implements DNSCache {
        C01881() {
        }

        public DNSMessage get(Question question) {
            return (DNSMessage) MiniDnsResolver.cache.get(question);
        }

        public void put(Question question, DNSMessage dNSMessage) {
            long j = MiniDnsResolver.ONE_DAY;
            for (Record record : dNSMessage.m30d()) {
                if (record.m42a(question)) {
                    j = record.m43b();
                    break;
                }
            }
            MiniDnsResolver.cache.m5381a(question, dNSMessage, j);
        }
    }

    static {
        instance = new MiniDnsResolver();
        cache = new ExpirationCache(10, ONE_DAY);
    }

    public MiniDnsResolver() {
        this.client = new Client(new C01881());
    }

    public static DNSResolver getInstance() {
        return instance;
    }

    public static void setup() {
        DNSUtil.setDNSResolver(getInstance());
    }

    public List<Exception> initialize() {
        setup();
        return null;
    }

    public List<SRVRecord> lookupSRVRecords(String str) {
        List<SRVRecord> linkedList = new LinkedList();
        DNSMessage a = this.client.m12a(str, Record.Record.SRV, Record.Record.IN);
        if (a == null) {
            return linkedList;
        }
        for (Record a2 : a.m30d()) {
            SRV srv = (SRV) a2.m40a();
            linkedList.add(new SRVRecord(srv.m8d(), srv.m7c(), srv.m4a(), srv.m6b()));
        }
        return linkedList;
    }
}
