package mobi.mmdt.ott.core.logic.p024b;

/* renamed from: mobi.mmdt.ott.core.logic.b.b */
class CallManager implements CallNotifier {
    final /* synthetic */ CallManager f3565a;

    CallManager(CallManager callManager) {
        this.f3565a = callManager;
    }

    public void m4586a(int i, float f) {
        if (this.f3565a.f3559e != null) {
            this.f3565a.f3559e.m2625a(i, f);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m4587a(mobi.mmdt.ott.core.logic.p024b.CallState r9, java.lang.String r10) {
        /*
        r8 = this;
        r1 = 1;
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0.f3556b = r10;	 Catch:{ Exception -> 0x00e2 }
        r0 = mobi.mmdt.ott.core.logic.p024b.CallState.f3567b;	 Catch:{ Exception -> 0x00e2 }
        r0 = r9.equals(r0);	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x0016;
    L_0x000e:
        r0 = mobi.mmdt.ott.core.logic.p024b.CallState.f3568c;	 Catch:{ Exception -> 0x00e2 }
        r0 = r9.equals(r0);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x009d;
    L_0x0016:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3560f;	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x0043;
    L_0x001e:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3558d;	 Catch:{ Exception -> 0x00e2 }
        r1 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r2 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.m4434g(r2);	 Catch:{ Exception -> 0x00e2 }
        r2 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r3 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r3 = r3.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.m4432f(r3);	 Catch:{ Exception -> 0x00e2 }
        mobi.mmdt.ott.core.UiRequests.m4505c(r0, r1, r2);	 Catch:{ Exception -> 0x00e2 }
    L_0x0043:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3562h = r1;	 Catch:{ Exception -> 0x00e2 }
    L_0x0049:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3556b;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.toLowerCase();	 Catch:{ Exception -> 0x00e2 }
        r1 = r0.hashCode();	 Catch:{ Exception -> 0x00e2 }
        switch(r1) {
            case -2120467137: goto L_0x01b0;
            case -2119211739: goto L_0x01c1;
            case -1147311558: goto L_0x01d2;
            case -881026536: goto L_0x01e9;
            case -805912137: goto L_0x0200;
            case -579210487: goto L_0x0217;
            case -443104976: goto L_0x022e;
            case -335770198: goto L_0x023f;
            case -188109835: goto L_0x0256;
            case -153084238: goto L_0x026d;
            case -123687464: goto L_0x0284;
            case 114465400: goto L_0x029b;
            case 279713067: goto L_0x02ac;
            case 1455337362: goto L_0x02b6;
            case 1793542782: goto L_0x02c0;
            case 1847855295: goto L_0x02d7;
            case 1960761768: goto L_0x02e1;
            default: goto L_0x005a;
        };	 Catch:{ Exception -> 0x00e2 }
    L_0x005a:
        r0 = "CallManager";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e2 }
        r2 = "Could not recognized state : ";
        r1.<init>(r2);	 Catch:{ Exception -> 0x00e2 }
        r2 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.f3556b;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x00e2 }
        android.util.Log.d(r0, r1);	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 1;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.f3556b;	 Catch:{ Exception -> 0x00e2 }
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
    L_0x0085:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3559e;	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x009c;
    L_0x008d:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3559e;	 Catch:{ Exception -> 0x00e2 }
        r1 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.f3557c;	 Catch:{ Exception -> 0x00e2 }
        r0.m2626a(r9, r1);	 Catch:{ Exception -> 0x00e2 }
    L_0x009c:
        return;
    L_0x009d:
        r0 = mobi.mmdt.ott.core.logic.p024b.CallState.f3573h;	 Catch:{ Exception -> 0x00e2 }
        r0 = r9.equals(r0);	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x00ad;
    L_0x00a5:
        r0 = mobi.mmdt.ott.core.logic.p024b.CallState.f3572g;	 Catch:{ Exception -> 0x00e2 }
        r0 = r9.equals(r0);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x00e7;
    L_0x00ad:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3560f;	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x00da;
    L_0x00b5:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3558d;	 Catch:{ Exception -> 0x00e2 }
        r1 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r2 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.m4434g(r2);	 Catch:{ Exception -> 0x00e2 }
        r2 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r3 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r3 = r3.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.m4432f(r3);	 Catch:{ Exception -> 0x00e2 }
        mobi.mmdt.ott.core.UiRequests.m4505c(r0, r1, r2);	 Catch:{ Exception -> 0x00e2 }
    L_0x00da:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 1;
        r0.f3562h = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0049;
    L_0x00e2:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x009c;
    L_0x00e7:
        r0 = mobi.mmdt.ott.core.logic.p024b.CallState.f3579n;	 Catch:{ Exception -> 0x00e2 }
        r0 = r9.equals(r0);	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x00f7;
    L_0x00ef:
        r0 = mobi.mmdt.ott.core.logic.p024b.CallState.f3578m;	 Catch:{ Exception -> 0x00e2 }
        r0 = r9.equals(r0);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x0049;
    L_0x00f7:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0.m4567q();	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3560f;	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x0049;
    L_0x0104:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3562h;	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x016c;
    L_0x010c:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.m4576e();	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x016a;
    L_0x0114:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3558d;	 Catch:{ Exception -> 0x00e2 }
        r2 = 2;
        r3 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r4 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r4 = r4.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r3 = r3.m4434g(r4);	 Catch:{ Exception -> 0x00e2 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00e2 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e2 }
        r7 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r7 = r7.m4579h();	 Catch:{ Exception -> 0x00e2 }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ Exception -> 0x00e2 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x00e2 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x00e2 }
        mobi.mmdt.ott.core.model.database.p064e.Logs.m5067a(r0, r1, r2, r3, r4, r6);	 Catch:{ Exception -> 0x00e2 }
    L_0x0143:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3558d;	 Catch:{ Exception -> 0x00e2 }
        r1 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r2 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r1 = r1.m4434g(r2);	 Catch:{ Exception -> 0x00e2 }
        r2 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r3 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r3 = r3.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r2 = r2.m4432f(r3);	 Catch:{ Exception -> 0x00e2 }
        mobi.mmdt.ott.core.UiRequests.m4502b(r0, r1, r2);	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0049;
    L_0x016a:
        r1 = 4;
        goto L_0x0114;
    L_0x016c:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.m4576e();	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x01ae;
    L_0x0174:
        r1 = 3;
    L_0x0175:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3558d;	 Catch:{ Exception -> 0x00e2 }
        r2 = 2;
        r3 = mobi.mmdt.ott.core.p051a.CountryTools.m4416a();	 Catch:{ Exception -> 0x00e2 }
        r4 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r4 = r4.m4584m();	 Catch:{ Exception -> 0x00e2 }
        r3 = r3.m4434g(r4);	 Catch:{ Exception -> 0x00e2 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x00e2 }
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00e2 }
        r7 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r7 = r7.m4579h();	 Catch:{ Exception -> 0x00e2 }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ Exception -> 0x00e2 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x00e2 }
        r6 = r6.toString();	 Catch:{ Exception -> 0x00e2 }
        mobi.mmdt.ott.core.model.database.p064e.Logs.m5067a(r0, r1, r2, r3, r4, r6);	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r0 = r0.f3558d;	 Catch:{ Exception -> 0x00e2 }
        mobi.mmdt.ott.core.UiRequests.m4507d(r0);	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0143;
    L_0x01ae:
        r1 = 5;
        goto L_0x0175;
    L_0x01b0:
        r1 = "call paused by remote";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x01b8:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "on hold.";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x01c1:
        r1 = "call terminated";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x01c9:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "Call finished.";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x01d2:
        r1 = "outgoing call in progress";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x01da:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "Try to make a call...";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x01e9:
        r1 = "incoming call";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x01f1:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "Incomming call ...";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x0200:
        r1 = "busy here";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x0208:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "The subscriber you are calling is busy on another call.";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 1;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x0217:
        r1 = "connected";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x021f:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "In Call";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x022e:
        r1 = "call paused";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x0236:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "on hold.";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x023f:
        r1 = "resuming";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x0247:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "In Call";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x0256:
        r1 = "not found";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x025e:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 1;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "The subscriber you are calling is not online at the moment.";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x026d:
        r1 = "starting outgoing call";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x0275:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "Try to make a call...";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x0284:
        r1 = "remote ringing";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x028c:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "Ringing...";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x029b:
        r1 = "call ended";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x02a3:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "Call finished.";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x02ac:
        r1 = "pausing call";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x0085;
    L_0x02b4:
        goto L_0x005a;
    L_0x02b6:
        r1 = "streams running";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x0085;
    L_0x02be:
        goto L_0x005a;
    L_0x02c0:
        r1 = "call declined.";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x02c8:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "The subscriber rejected your call.";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 1;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
    L_0x02d7:
        r1 = "call released";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x0085;
    L_0x02df:
        goto L_0x005a;
    L_0x02e1:
        r1 = "connected (streams running)";
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005a;
    L_0x02e9:
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = 0;
        r0.f3563i = r1;	 Catch:{ Exception -> 0x00e2 }
        r0 = r8.f3565a;	 Catch:{ Exception -> 0x00e2 }
        r1 = "In Call";
        r0.f3557c = r1;	 Catch:{ Exception -> 0x00e2 }
        goto L_0x0085;
        */
        throw new UnsupportedOperationException("Method not decompiled: mobi.mmdt.ott.core.logic.b.b.a(mobi.mmdt.ott.core.logic.b.d, java.lang.String):void");
    }
}
