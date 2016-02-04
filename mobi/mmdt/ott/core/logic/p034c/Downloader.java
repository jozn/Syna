package mobi.mmdt.ott.core.logic.p034c;

/* renamed from: mobi.mmdt.ott.core.logic.c.b */
class Downloader implements Runnable {
    final /* synthetic */ Downloader f3620a;

    Downloader(Downloader downloader) {
        this.f3620a = downloader;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r12 = this;
        r11 = -1;
        r10 = 0;
        r0 = r12.f3620a;
        r0 = r0.j;
        r1 = r0.iterator();
    L_0x000a:
        r0 = r1.hasNext();
        if (r0 != 0) goto L_0x009c;
    L_0x0010:
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x00ce }
        r1 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r1 = r1.f;	 Catch:{ Exception -> 0x00ce }
        r0.<init>(r1);	 Catch:{ Exception -> 0x00ce }
        r1 = r0.openConnection();	 Catch:{ Exception -> 0x00ce }
        r2 = "RestfulWS";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ce }
        r4 = "get WebService <> URL <";
        r3.<init>(r4);	 Catch:{ Exception -> 0x00ce }
        r4 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = r4.f;	 Catch:{ Exception -> 0x00ce }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00ce }
        r4 = ">";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00ce }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00ce }
        android.util.Log.d(r2, r3);	 Catch:{ Exception -> 0x00ce }
        r1.connect();	 Catch:{ Exception -> 0x00ce }
        r1 = r1.getContentLength();	 Catch:{ Exception -> 0x00ce }
        if (r1 == r11) goto L_0x004a;
    L_0x0044:
        r2 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = (long) r1;	 Catch:{ Exception -> 0x00ce }
        r2.f3618k = r4;	 Catch:{ Exception -> 0x00ce }
    L_0x004a:
        r1 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x00ce }
        r0 = r0.openStream();	 Catch:{ Exception -> 0x00ce }
        r1.<init>(r0);	 Catch:{ Exception -> 0x00ce }
        r2 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00ce }
        r0 = new java.io.File;	 Catch:{ Exception -> 0x00ce }
        r3 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r3 = r3.i;	 Catch:{ Exception -> 0x00ce }
        r0.<init>(r3);	 Catch:{ Exception -> 0x00ce }
        r2.<init>(r0);	 Catch:{ Exception -> 0x00ce }
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r3 = new byte[r0];	 Catch:{ Exception -> 0x00ce }
    L_0x0065:
        r0 = r1.read(r3);	 Catch:{ Exception -> 0x00ce }
        if (r0 != r11) goto L_0x00ab;
    L_0x006b:
        r2.close();	 Catch:{ Exception -> 0x00ce }
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r3 = 100;
        r0.h = r3;	 Catch:{ Exception -> 0x00ce }
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r3 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = r3.f3618k;	 Catch:{ Exception -> 0x00ce }
        r0.f3619l = r4;	 Catch:{ Exception -> 0x00ce }
        r2.flush();	 Catch:{ Exception -> 0x00ce }
        r2.close();	 Catch:{ Exception -> 0x00ce }
        r1.close();	 Catch:{ Exception -> 0x00ce }
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r0 = r0.j;	 Catch:{ Exception -> 0x00ce }
        r1 = r0.iterator();	 Catch:{ Exception -> 0x00ce }
    L_0x0090:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x00ce }
        if (r0 != 0) goto L_0x0152;
    L_0x0096:
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r1 = 0;
        r0.g = r1;	 Catch:{ Exception -> 0x00ce }
    L_0x009b:
        return;
    L_0x009c:
        r0 = r1.next();
        r0 = (mobi.mmdt.ott.core.logic.p034c.LocalTransmitterListener) r0;
        r2 = r12.f3620a;
        r2 = r2.e;
        r0.m3633a(r2);
        goto L_0x000a;
    L_0x00ab:
        r4 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = r4.a;	 Catch:{ Exception -> 0x00ce }
        if (r4 == 0) goto L_0x00f4;
    L_0x00b1:
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r0 = r0.j;	 Catch:{ Exception -> 0x00ce }
        r3 = r0.iterator();	 Catch:{ Exception -> 0x00ce }
    L_0x00b9:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x00ce }
        if (r0 != 0) goto L_0x00e6;
    L_0x00bf:
        r2.flush();	 Catch:{ Exception -> 0x00ce }
        r2.close();	 Catch:{ Exception -> 0x00ce }
        r1.close();	 Catch:{ Exception -> 0x00ce }
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r1 = 0;
        r0.g = r1;	 Catch:{ Exception -> 0x00ce }
        goto L_0x009b;
    L_0x00ce:
        r0 = move-exception;
        r1 = r0;
        r1.printStackTrace();
        r0 = r12.f3620a;
        r0 = r0.j;
        r2 = r0.iterator();
    L_0x00db:
        r0 = r2.hasNext();
        if (r0 != 0) goto L_0x0161;
    L_0x00e1:
        r0 = r12.f3620a;
        r0.g = r10;
        goto L_0x009b;
    L_0x00e6:
        r0 = r3.next();	 Catch:{ Exception -> 0x00ce }
        r0 = (mobi.mmdt.ott.core.logic.p034c.LocalTransmitterListener) r0;	 Catch:{ Exception -> 0x00ce }
        r4 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = r4.e;	 Catch:{ Exception -> 0x00ce }
        r0.m3638c(r4);	 Catch:{ Exception -> 0x00ce }
        goto L_0x00b9;
    L_0x00f4:
        r4 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r6 = r4.f3619l;	 Catch:{ Exception -> 0x00ce }
        r8 = (long) r0;	 Catch:{ Exception -> 0x00ce }
        r6 = r6 + r8;
        r4.f3619l = r6;	 Catch:{ Exception -> 0x00ce }
        r4 = 0;
        r2.write(r3, r4, r0);	 Catch:{ Exception -> 0x00ce }
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = r4.f3619l;	 Catch:{ Exception -> 0x00ce }
        r6 = 100;
        r4 = r4 * r6;
        r6 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r6 = r6.f3618k;	 Catch:{ Exception -> 0x00ce }
        r4 = r4 / r6;
        r4 = (int) r4;	 Catch:{ Exception -> 0x00ce }
        r0.h = r4;	 Catch:{ Exception -> 0x00ce }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00ce }
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r6 = r0.c;	 Catch:{ Exception -> 0x00ce }
        r4 = r4 - r6;
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r0 = r0.b;	 Catch:{ Exception -> 0x00ce }
        r6 = (long) r0;	 Catch:{ Exception -> 0x00ce }
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 <= 0) goto L_0x0065;
    L_0x012a:
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00ce }
        r0.c = r4;	 Catch:{ Exception -> 0x00ce }
        r0 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r0 = r0.j;	 Catch:{ Exception -> 0x00ce }
        r4 = r0.iterator();	 Catch:{ Exception -> 0x00ce }
    L_0x013a:
        r0 = r4.hasNext();	 Catch:{ Exception -> 0x00ce }
        if (r0 == 0) goto L_0x0065;
    L_0x0140:
        r0 = r4.next();	 Catch:{ Exception -> 0x00ce }
        r0 = (mobi.mmdt.ott.core.logic.p034c.LocalTransmitterListener) r0;	 Catch:{ Exception -> 0x00ce }
        r5 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r6 = r5.e;	 Catch:{ Exception -> 0x00ce }
        r5 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r5 = r5.h;	 Catch:{ Exception -> 0x00ce }
        r0.m3634a(r6, r5);	 Catch:{ Exception -> 0x00ce }
        goto L_0x013a;
    L_0x0152:
        r0 = r1.next();	 Catch:{ Exception -> 0x00ce }
        r0 = (mobi.mmdt.ott.core.logic.p034c.LocalTransmitterListener) r0;	 Catch:{ Exception -> 0x00ce }
        r2 = r12.f3620a;	 Catch:{ Exception -> 0x00ce }
        r2 = r2.e;	 Catch:{ Exception -> 0x00ce }
        r0.m3637b(r2);	 Catch:{ Exception -> 0x00ce }
        goto L_0x0090;
    L_0x0161:
        r0 = r2.next();
        r0 = (mobi.mmdt.ott.core.logic.p034c.LocalTransmitterListener) r0;
        r3 = r12.f3620a;
        r4 = r3.e;
        r0.m3635a(r4, r1);
        goto L_0x00db;
        */
        throw new UnsupportedOperationException("Method not decompiled: mobi.mmdt.ott.core.logic.c.b.run():void");
    }
}
