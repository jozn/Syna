package p000a.p001a.p002a;

/* renamed from: a.a.a.b */
public interface DNSCache {
    DNSMessage get(Question question);

    void put(Question question, DNSMessage dNSMessage);
}
