package android.support.v4.app;

import android.support.v4.p008c.LogWriter;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: android.support.v4.app.d */
final class BackStackRecord extends FragmentTransaction implements Runnable {
    final FragmentManager f253a;
    BackStackRecord f254b;
    BackStackRecord f255c;
    int f256d;
    int f257e;
    int f258f;
    int f259g;
    int f260h;
    int f261i;
    int f262j;
    boolean f263k;
    boolean f264l;
    String f265m;
    boolean f266n;
    int f267o;
    int f268p;
    CharSequence f269q;
    int f270r;
    CharSequence f271s;

    /* renamed from: android.support.v4.app.d.a */
    static final class BackStackRecord {
        BackStackRecord f244a;
        BackStackRecord f245b;
        int f246c;
        Fragment f247d;
        int f248e;
        int f249f;
        int f250g;
        int f251h;
        ArrayList<Fragment> f252i;

        BackStackRecord() {
        }
    }

    public BackStackRecord(FragmentManager fragmentManager) {
        this.f264l = true;
        this.f267o = -1;
        this.f253a = fragmentManager;
    }

    private void m205a(int i, Fragment fragment, String str, int i2) {
        fragment.f131B = this.f253a;
        if (str != null) {
            if (fragment.f137H == null || str.equals(fragment.f137H)) {
                fragment.f137H = str;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.f137H + " now " + str);
            }
        }
        if (i != 0) {
            if (fragment.f135F == 0 || fragment.f135F == i) {
                fragment.f135F = i;
                fragment.f136G = i;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.f135F + " now " + i);
            }
        }
        BackStackRecord backStackRecord = new BackStackRecord();
        backStackRecord.f246c = i2;
        backStackRecord.f247d = fragment;
        m213a(backStackRecord);
    }

    public int m206a() {
        return m207a(false);
    }

    int m207a(boolean z) {
        if (this.f266n) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManager.f287a) {
            Log.v("FragmentManager", "Commit: " + this);
            m214a("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        this.f266n = true;
        if (this.f263k) {
            this.f267o = this.f253a.m259a(this);
        } else {
            this.f267o = -1;
        }
        this.f253a.m277a((Runnable) this, z);
        return this.f267o;
    }

    public FragmentTransaction m208a(int i) {
        this.f261i = i;
        return this;
    }

    public FragmentTransaction m209a(int i, Fragment fragment) {
        m205a(i, fragment, null, 1);
        return this;
    }

    public FragmentTransaction m210a(int i, Fragment fragment, String str) {
        m205a(i, fragment, str, 1);
        return this;
    }

    public FragmentTransaction m211a(Fragment fragment) {
        BackStackRecord backStackRecord = new BackStackRecord();
        backStackRecord.f246c = 3;
        backStackRecord.f247d = fragment;
        m213a(backStackRecord);
        return this;
    }

    public FragmentTransaction m212a(Fragment fragment, String str) {
        m205a(0, fragment, str, 1);
        return this;
    }

    void m213a(BackStackRecord backStackRecord) {
        if (this.f254b == null) {
            this.f255c = backStackRecord;
            this.f254b = backStackRecord;
        } else {
            backStackRecord.f245b = this.f255c;
            this.f255c.f244a = backStackRecord;
            this.f255c = backStackRecord;
        }
        backStackRecord.f248e = this.f257e;
        backStackRecord.f249f = this.f258f;
        backStackRecord.f250g = this.f259g;
        backStackRecord.f251h = this.f260h;
        this.f256d++;
    }

    public void m214a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        m215a(str, printWriter, true);
    }

    public void m215a(String str, PrintWriter printWriter, boolean z) {
        if (z) {
            printWriter.print(str);
            printWriter.print("mName=");
            printWriter.print(this.f265m);
            printWriter.print(" mIndex=");
            printWriter.print(this.f267o);
            printWriter.print(" mCommitted=");
            printWriter.println(this.f266n);
            if (this.f261i != 0) {
                printWriter.print(str);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString(this.f261i));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString(this.f262j));
            }
            if (!(this.f257e == 0 && this.f258f == 0)) {
                printWriter.print(str);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f257e));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString(this.f258f));
            }
            if (!(this.f259g == 0 && this.f260h == 0)) {
                printWriter.print(str);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString(this.f259g));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString(this.f260h));
            }
            if (!(this.f268p == 0 && this.f269q == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString(this.f268p));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println(this.f269q);
            }
            if (!(this.f270r == 0 && this.f271s == null)) {
                printWriter.print(str);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString(this.f270r));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println(this.f271s);
            }
        }
        if (this.f254b != null) {
            printWriter.print(str);
            printWriter.println("Operations:");
            String str2 = str + "    ";
            int i = 0;
            BackStackRecord backStackRecord = this.f254b;
            while (backStackRecord != null) {
                String str3;
                switch (backStackRecord.f246c) {
                    case VideoSize.QCIF /*0*/:
                        str3 = "NULL";
                        break;
                    case VideoSize.CIF /*1*/:
                        str3 = "ADD";
                        break;
                    case VideoSize.HVGA /*2*/:
                        str3 = "REPLACE";
                        break;
                    case Version.API03_CUPCAKE_15 /*3*/:
                        str3 = "REMOVE";
                        break;
                    case Version.API04_DONUT_16 /*4*/:
                        str3 = "HIDE";
                        break;
                    case Version.API05_ECLAIR_20 /*5*/:
                        str3 = "SHOW";
                        break;
                    case Version.API06_ECLAIR_201 /*6*/:
                        str3 = "DETACH";
                        break;
                    case Version.API07_ECLAIR_21 /*7*/:
                        str3 = "ATTACH";
                        break;
                    default:
                        str3 = "cmd=" + backStackRecord.f246c;
                        break;
                }
                printWriter.print(str);
                printWriter.print("  Op #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.print(str3);
                printWriter.print(" ");
                printWriter.println(backStackRecord.f247d);
                if (z) {
                    if (!(backStackRecord.f248e == 0 && backStackRecord.f249f == 0)) {
                        printWriter.print(str);
                        printWriter.print("enterAnim=#");
                        printWriter.print(Integer.toHexString(backStackRecord.f248e));
                        printWriter.print(" exitAnim=#");
                        printWriter.println(Integer.toHexString(backStackRecord.f249f));
                    }
                    if (!(backStackRecord.f250g == 0 && backStackRecord.f251h == 0)) {
                        printWriter.print(str);
                        printWriter.print("popEnterAnim=#");
                        printWriter.print(Integer.toHexString(backStackRecord.f250g));
                        printWriter.print(" popExitAnim=#");
                        printWriter.println(Integer.toHexString(backStackRecord.f251h));
                    }
                }
                if (backStackRecord.f252i != null && backStackRecord.f252i.size() > 0) {
                    for (int i2 = 0; i2 < backStackRecord.f252i.size(); i2++) {
                        printWriter.print(str2);
                        if (backStackRecord.f252i.size() == 1) {
                            printWriter.print("Removed: ");
                        } else {
                            if (i2 == 0) {
                                printWriter.println("Removed:");
                            }
                            printWriter.print(str2);
                            printWriter.print("  #");
                            printWriter.print(i2);
                            printWriter.print(": ");
                        }
                        printWriter.println(backStackRecord.f252i.get(i2));
                    }
                }
                backStackRecord = backStackRecord.f244a;
                i++;
            }
        }
    }

    public int m216b() {
        return m207a(true);
    }

    public FragmentTransaction m217b(int i, Fragment fragment) {
        return m218b(i, fragment, null);
    }

    public FragmentTransaction m218b(int i, Fragment fragment, String str) {
        if (i == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        m205a(i, fragment, str, 2);
        return this;
    }

    public FragmentTransaction m219b(Fragment fragment) {
        BackStackRecord backStackRecord = new BackStackRecord();
        backStackRecord.f246c = 6;
        backStackRecord.f247d = fragment;
        m213a(backStackRecord);
        return this;
    }

    void m220b(int i) {
        if (this.f263k) {
            if (FragmentManager.f287a) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + i);
            }
            for (BackStackRecord backStackRecord = this.f254b; backStackRecord != null; backStackRecord = backStackRecord.f244a) {
                Fragment fragment;
                if (backStackRecord.f247d != null) {
                    fragment = backStackRecord.f247d;
                    fragment.f130A += i;
                    if (FragmentManager.f287a) {
                        Log.v("FragmentManager", "Bump nesting of " + backStackRecord.f247d + " to " + backStackRecord.f247d.f130A);
                    }
                }
                if (backStackRecord.f252i != null) {
                    for (int size = backStackRecord.f252i.size() - 1; size >= 0; size--) {
                        fragment = (Fragment) backStackRecord.f252i.get(size);
                        fragment.f130A += i;
                        if (FragmentManager.f287a) {
                            Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.f130A);
                        }
                    }
                }
            }
        }
    }

    public void m221b(boolean z) {
        if (FragmentManager.f287a) {
            Log.v("FragmentManager", "popFromBackStack: " + this);
            m214a("  ", null, new PrintWriter(new LogWriter("FragmentManager")), null);
        }
        m220b(-1);
        for (BackStackRecord backStackRecord = this.f255c; backStackRecord != null; backStackRecord = backStackRecord.f245b) {
            Fragment fragment;
            switch (backStackRecord.f246c) {
                case VideoSize.CIF /*1*/:
                    fragment = backStackRecord.f247d;
                    fragment.f145P = backStackRecord.f251h;
                    this.f253a.m273a(fragment, FragmentManager.m257c(this.f261i), this.f262j);
                    break;
                case VideoSize.HVGA /*2*/:
                    fragment = backStackRecord.f247d;
                    if (fragment != null) {
                        fragment.f145P = backStackRecord.f251h;
                        this.f253a.m273a(fragment, FragmentManager.m257c(this.f261i), this.f262j);
                    }
                    if (backStackRecord.f252i == null) {
                        break;
                    }
                    for (int i = 0; i < backStackRecord.f252i.size(); i++) {
                        fragment = (Fragment) backStackRecord.f252i.get(i);
                        fragment.f145P = backStackRecord.f250g;
                        this.f253a.m275a(fragment, false);
                    }
                    break;
                case Version.API03_CUPCAKE_15 /*3*/:
                    fragment = backStackRecord.f247d;
                    fragment.f145P = backStackRecord.f250g;
                    this.f253a.m275a(fragment, false);
                    break;
                case Version.API04_DONUT_16 /*4*/:
                    fragment = backStackRecord.f247d;
                    fragment.f145P = backStackRecord.f250g;
                    this.f253a.m291c(fragment, FragmentManager.m257c(this.f261i), this.f262j);
                    break;
                case Version.API05_ECLAIR_20 /*5*/:
                    fragment = backStackRecord.f247d;
                    fragment.f145P = backStackRecord.f251h;
                    this.f253a.m285b(fragment, FragmentManager.m257c(this.f261i), this.f262j);
                    break;
                case Version.API06_ECLAIR_201 /*6*/:
                    fragment = backStackRecord.f247d;
                    fragment.f145P = backStackRecord.f250g;
                    this.f253a.m297e(fragment, FragmentManager.m257c(this.f261i), this.f262j);
                    break;
                case Version.API07_ECLAIR_21 /*7*/:
                    fragment = backStackRecord.f247d;
                    fragment.f145P = backStackRecord.f250g;
                    this.f253a.m295d(fragment, FragmentManager.m257c(this.f261i), this.f262j);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + backStackRecord.f246c);
            }
        }
        if (z) {
            this.f253a.m267a(this.f253a.f301n, FragmentManager.m257c(this.f261i), this.f262j, true);
        }
        if (this.f267o >= 0) {
            this.f253a.m283b(this.f267o);
            this.f267o = -1;
        }
    }

    public FragmentTransaction m222c(Fragment fragment) {
        BackStackRecord backStackRecord = new BackStackRecord();
        backStackRecord.f246c = 7;
        backStackRecord.f247d = fragment;
        m213a(backStackRecord);
        return this;
    }

    public String m223c() {
        return this.f265m;
    }

    public void run() {
        if (FragmentManager.f287a) {
            Log.v("FragmentManager", "Run: " + this);
        }
        if (!this.f263k || this.f267o >= 0) {
            m220b(1);
            for (BackStackRecord backStackRecord = this.f254b; backStackRecord != null; backStackRecord = backStackRecord.f244a) {
                Fragment fragment;
                switch (backStackRecord.f246c) {
                    case VideoSize.CIF /*1*/:
                        fragment = backStackRecord.f247d;
                        fragment.f145P = backStackRecord.f248e;
                        this.f253a.m275a(fragment, false);
                        break;
                    case VideoSize.HVGA /*2*/:
                        Fragment fragment2;
                        fragment = backStackRecord.f247d;
                        if (this.f253a.f294g != null) {
                            fragment2 = fragment;
                            for (int i = 0; i < this.f253a.f294g.size(); i++) {
                                fragment = (Fragment) this.f253a.f294g.get(i);
                                if (FragmentManager.f287a) {
                                    Log.v("FragmentManager", "OP_REPLACE: adding=" + fragment2 + " old=" + fragment);
                                }
                                if (fragment2 == null || fragment.f136G == fragment2.f136G) {
                                    if (fragment == fragment2) {
                                        fragment2 = null;
                                        backStackRecord.f247d = null;
                                    } else {
                                        if (backStackRecord.f252i == null) {
                                            backStackRecord.f252i = new ArrayList();
                                        }
                                        backStackRecord.f252i.add(fragment);
                                        fragment.f145P = backStackRecord.f249f;
                                        if (this.f263k) {
                                            fragment.f130A++;
                                            if (FragmentManager.f287a) {
                                                Log.v("FragmentManager", "Bump nesting of " + fragment + " to " + fragment.f130A);
                                            }
                                        }
                                        this.f253a.m273a(fragment, this.f261i, this.f262j);
                                    }
                                }
                            }
                        } else {
                            fragment2 = fragment;
                        }
                        if (fragment2 == null) {
                            break;
                        }
                        fragment2.f145P = backStackRecord.f248e;
                        this.f253a.m275a(fragment2, false);
                        break;
                    case Version.API03_CUPCAKE_15 /*3*/:
                        fragment = backStackRecord.f247d;
                        fragment.f145P = backStackRecord.f249f;
                        this.f253a.m273a(fragment, this.f261i, this.f262j);
                        break;
                    case Version.API04_DONUT_16 /*4*/:
                        fragment = backStackRecord.f247d;
                        fragment.f145P = backStackRecord.f249f;
                        this.f253a.m285b(fragment, this.f261i, this.f262j);
                        break;
                    case Version.API05_ECLAIR_20 /*5*/:
                        fragment = backStackRecord.f247d;
                        fragment.f145P = backStackRecord.f248e;
                        this.f253a.m291c(fragment, this.f261i, this.f262j);
                        break;
                    case Version.API06_ECLAIR_201 /*6*/:
                        fragment = backStackRecord.f247d;
                        fragment.f145P = backStackRecord.f249f;
                        this.f253a.m295d(fragment, this.f261i, this.f262j);
                        break;
                    case Version.API07_ECLAIR_21 /*7*/:
                        fragment = backStackRecord.f247d;
                        fragment.f145P = backStackRecord.f248e;
                        this.f253a.m297e(fragment, this.f261i, this.f262j);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown cmd: " + backStackRecord.f246c);
                }
            }
            this.f253a.m267a(this.f253a.f301n, this.f261i, this.f262j, true);
            if (this.f263k) {
                this.f253a.m286b(this);
                return;
            }
            return;
        }
        throw new IllegalStateException("addToBackStack() called after commit()");
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("BackStackEntry{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.f267o >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.f267o);
        }
        if (this.f265m != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.f265m);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
