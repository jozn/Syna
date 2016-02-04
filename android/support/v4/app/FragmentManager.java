package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.p008c.DebugUtils;
import android.support.v4.p008c.LogWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

/* renamed from: android.support.v4.app.m */
final class FragmentManager extends FragmentManager {
    static final Interpolator f284A;
    static final Interpolator f285B;
    static final Interpolator f286C;
    static boolean f287a;
    static final boolean f288b;
    static final Interpolator f289z;
    ArrayList<Runnable> f290c;
    Runnable[] f291d;
    boolean f292e;
    ArrayList<Fragment> f293f;
    ArrayList<Fragment> f294g;
    ArrayList<Integer> f295h;
    ArrayList<BackStackRecord> f296i;
    ArrayList<Fragment> f297j;
    ArrayList<BackStackRecord> f298k;
    ArrayList<Integer> f299l;
    ArrayList<FragmentManager> f300m;
    int f301n;
    FragmentActivity f302o;
    FragmentManager f303p;
    Fragment f304q;
    boolean f305r;
    boolean f306s;
    boolean f307t;
    String f308u;
    boolean f309v;
    Bundle f310w;
    SparseArray<Parcelable> f311x;
    Runnable f312y;

    static {
        boolean z = false;
        f287a = false;
        if (VERSION.SDK_INT >= 11) {
            z = true;
        }
        f288b = z;
        f289z = new DecelerateInterpolator(2.5f);
        f284A = new DecelerateInterpolator(1.5f);
        f285B = new AccelerateInterpolator(2.5f);
        f286C = new AccelerateInterpolator(1.5f);
    }

    FragmentManager() {
        this.f301n = 0;
        this.f310w = null;
        this.f311x = null;
        this.f312y = new FragmentManager(this);
    }

    static Animation m253a(Context context, float f, float f2) {
        Animation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(f284A);
        alphaAnimation.setDuration(220);
        return alphaAnimation;
    }

    static Animation m254a(Context context, float f, float f2, float f3, float f4) {
        Animation animationSet = new AnimationSet(false);
        Animation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(f289z);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        scaleAnimation = new AlphaAnimation(f3, f4);
        scaleAnimation.setInterpolator(f284A);
        scaleAnimation.setDuration(220);
        animationSet.addAnimation(scaleAnimation);
        return animationSet;
    }

    private void m255a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        if (this.f302o != null) {
            try {
                this.f302o.dump("  ", null, printWriter, new String[0]);
            } catch (Throwable e) {
                Log.e("FragmentManager", "Failed dumping state", e);
            }
        } else {
            try {
                m278a("  ", null, printWriter, new String[0]);
            } catch (Throwable e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
            }
        }
        throw runtimeException;
    }

    public static int m256b(int i, boolean z) {
        switch (i) {
            case 4097:
                return z ? 1 : 2;
            case 4099:
                return z ? 5 : 6;
            case 8194:
                return z ? 3 : 4;
            default:
                return -1;
        }
    }

    public static int m257c(int i) {
        switch (i) {
            case 4097:
                return 8194;
            case 4099:
                return 4099;
            case 8194:
                return 4097;
            default:
                return 0;
        }
    }

    private void m258t() {
        if (this.f306s) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        } else if (this.f308u != null) {
            throw new IllegalStateException("Can not perform this action inside of " + this.f308u);
        }
    }

    public int m259a(BackStackRecord backStackRecord) {
        int size;
        synchronized (this) {
            if (this.f299l == null || this.f299l.size() <= 0) {
                if (this.f298k == null) {
                    this.f298k = new ArrayList();
                }
                size = this.f298k.size();
                if (f287a) {
                    Log.v("FragmentManager", "Setting back stack index " + size + " to " + backStackRecord);
                }
                this.f298k.add(backStackRecord);
            } else {
                size = ((Integer) this.f299l.remove(this.f299l.size() - 1)).intValue();
                if (f287a) {
                    Log.v("FragmentManager", "Adding back stack index " + size + " with " + backStackRecord);
                }
                this.f298k.set(size, backStackRecord);
            }
        }
        return size;
    }

    public SavedState m260a(Fragment fragment) {
        if (fragment.f159o < 0) {
            m255a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        if (fragment.f154j <= 0) {
            return null;
        }
        Bundle g = m301g(fragment);
        return g != null ? new SavedState(g) : null;
    }

    public Fragment m261a(int i) {
        int size;
        Fragment fragment;
        if (this.f294g != null) {
            for (size = this.f294g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f294g.get(size);
                if (fragment != null && fragment.f135F == i) {
                    return fragment;
                }
            }
        }
        if (this.f293f != null) {
            for (size = this.f293f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f293f.get(size);
                if (fragment != null && fragment.f135F == i) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public Fragment m262a(Bundle bundle, String str) {
        int i = bundle.getInt(str, -1);
        if (i == -1) {
            return null;
        }
        if (i >= this.f293f.size()) {
            m255a(new IllegalStateException("Fragement no longer exists for key " + str + ": index " + i));
        }
        Fragment fragment = (Fragment) this.f293f.get(i);
        if (fragment != null) {
            return fragment;
        }
        m255a(new IllegalStateException("Fragement no longer exists for key " + str + ": index " + i));
        return fragment;
    }

    public Fragment m263a(String str) {
        int size;
        Fragment fragment;
        if (!(this.f294g == null || str == null)) {
            for (size = this.f294g.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f294g.get(size);
                if (fragment != null && str.equals(fragment.f137H)) {
                    return fragment;
                }
            }
        }
        if (!(this.f293f == null || str == null)) {
            for (size = this.f293f.size() - 1; size >= 0; size--) {
                fragment = (Fragment) this.f293f.get(size);
                if (fragment != null && str.equals(fragment.f137H)) {
                    return fragment;
                }
            }
        }
        return null;
    }

    public FragmentTransaction m264a() {
        return new BackStackRecord(this);
    }

    Animation m265a(Fragment fragment, int i, boolean z, int i2) {
        Animation a = fragment.m53a(i, z, fragment.f145P);
        if (a != null) {
            return a;
        }
        if (fragment.f145P != 0) {
            a = AnimationUtils.loadAnimation(this.f302o, fragment.f145P);
            if (a != null) {
                return a;
            }
        }
        if (i == 0) {
            return null;
        }
        int b = FragmentManager.m256b(i, z);
        if (b < 0) {
            return null;
        }
        switch (b) {
            case VideoSize.CIF /*1*/:
                return FragmentManager.m254a(this.f302o, 1.125f, 1.0f, 0.0f, 1.0f);
            case VideoSize.HVGA /*2*/:
                return FragmentManager.m254a(this.f302o, 1.0f, 0.975f, 1.0f, 0.0f);
            case Version.API03_CUPCAKE_15 /*3*/:
                return FragmentManager.m254a(this.f302o, 0.975f, 1.0f, 0.0f, 1.0f);
            case Version.API04_DONUT_16 /*4*/:
                return FragmentManager.m254a(this.f302o, 1.0f, 1.075f, 1.0f, 0.0f);
            case Version.API05_ECLAIR_20 /*5*/:
                return FragmentManager.m253a(this.f302o, 0.0f, 1.0f);
            case Version.API06_ECLAIR_201 /*6*/:
                return FragmentManager.m253a(this.f302o, 1.0f, 0.0f);
            default:
                if (i2 == 0 && this.f302o.getWindow() != null) {
                    i2 = this.f302o.getWindow().getAttributes().windowAnimations;
                }
                return i2 == 0 ? null : null;
        }
    }

    public void m266a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("Bad id: " + i);
        }
        m277a(new FragmentManager(this, i, i2), false);
    }

    void m267a(int i, int i2, int i3, boolean z) {
        if (this.f302o == null && i != 0) {
            throw new IllegalStateException("No activity");
        } else if (z || this.f301n != i) {
            this.f301n = i;
            if (this.f293f != null) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < this.f293f.size()) {
                    int a;
                    Fragment fragment = (Fragment) this.f293f.get(i4);
                    if (fragment != null) {
                        m274a(fragment, i, i2, i3, false);
                        if (fragment.f151V != null) {
                            a = i5 | fragment.f151V.m153a();
                            i4++;
                            i5 = a;
                        }
                    }
                    a = i5;
                    i4++;
                    i5 = a;
                }
                if (i5 == 0) {
                    m293d();
                }
                if (this.f305r && this.f302o != null && this.f301n == 5) {
                    this.f302o.e_();
                    this.f305r = false;
                }
            }
        }
    }

    public void m268a(int i, BackStackRecord backStackRecord) {
        synchronized (this) {
            if (this.f298k == null) {
                this.f298k = new ArrayList();
            }
            int size = this.f298k.size();
            if (i < size) {
                if (f287a) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + backStackRecord);
                }
                this.f298k.set(i, backStackRecord);
            } else {
                while (size < i) {
                    this.f298k.add(null);
                    if (this.f299l == null) {
                        this.f299l = new ArrayList();
                    }
                    if (f287a) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.f299l.add(Integer.valueOf(size));
                    size++;
                }
                if (f287a) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + backStackRecord);
                }
                this.f298k.add(backStackRecord);
            }
        }
    }

    void m269a(int i, boolean z) {
        m267a(i, 0, 0, z);
    }

    public void m270a(Configuration configuration) {
        if (this.f294g != null) {
            for (int i = 0; i < this.f294g.size(); i++) {
                Fragment fragment = (Fragment) this.f294g.get(i);
                if (fragment != null) {
                    fragment.m60a(configuration);
                }
            }
        }
    }

    public void m271a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.f159o < 0) {
            m255a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putInt(str, fragment.f159o);
    }

    void m272a(Parcelable parcelable, ArrayList<Fragment> arrayList) {
        if (parcelable != null) {
            FragmentManagerState fragmentManagerState = (FragmentManagerState) parcelable;
            if (fragmentManagerState.f190a != null) {
                int i;
                Fragment fragment;
                int i2;
                if (arrayList != null) {
                    for (i = 0; i < arrayList.size(); i++) {
                        fragment = (Fragment) arrayList.get(i);
                        if (f287a) {
                            Log.v("FragmentManager", "restoreAllState: re-attaching retained " + fragment);
                        }
                        FragmentState fragmentState = fragmentManagerState.f190a[fragment.f159o];
                        fragmentState.f203k = fragment;
                        fragment.f158n = null;
                        fragment.f130A = 0;
                        fragment.f169y = false;
                        fragment.f165u = false;
                        fragment.f162r = null;
                        if (fragmentState.f202j != null) {
                            fragmentState.f202j.setClassLoader(this.f302o.getClassLoader());
                            fragment.f158n = fragmentState.f202j.getSparseParcelableArray("android:view_state");
                        }
                    }
                }
                this.f293f = new ArrayList(fragmentManagerState.f190a.length);
                if (this.f295h != null) {
                    this.f295h.clear();
                }
                for (i2 = 0; i2 < fragmentManagerState.f190a.length; i2++) {
                    FragmentState fragmentState2 = fragmentManagerState.f190a[i2];
                    if (fragmentState2 != null) {
                        Fragment a = fragmentState2.m122a(this.f302o, this.f304q);
                        if (f287a) {
                            Log.v("FragmentManager", "restoreAllState: active #" + i2 + ": " + a);
                        }
                        this.f293f.add(a);
                        fragmentState2.f203k = null;
                    } else {
                        this.f293f.add(null);
                        if (this.f295h == null) {
                            this.f295h = new ArrayList();
                        }
                        if (f287a) {
                            Log.v("FragmentManager", "restoreAllState: avail #" + i2);
                        }
                        this.f295h.add(Integer.valueOf(i2));
                    }
                }
                if (arrayList != null) {
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        fragment = (Fragment) arrayList.get(i3);
                        if (fragment.f163s >= 0) {
                            if (fragment.f163s < this.f293f.size()) {
                                fragment.f162r = (Fragment) this.f293f.get(fragment.f163s);
                            } else {
                                Log.w("FragmentManager", "Re-attaching retained fragment " + fragment + " target no longer exists: " + fragment.f163s);
                                fragment.f162r = null;
                            }
                        }
                    }
                }
                if (fragmentManagerState.f191b != null) {
                    this.f294g = new ArrayList(fragmentManagerState.f191b.length);
                    for (i = 0; i < fragmentManagerState.f191b.length; i++) {
                        fragment = (Fragment) this.f293f.get(fragmentManagerState.f191b[i]);
                        if (fragment == null) {
                            m255a(new IllegalStateException("No instantiated fragment for index #" + fragmentManagerState.f191b[i]));
                        }
                        fragment.f165u = true;
                        if (f287a) {
                            Log.v("FragmentManager", "restoreAllState: added #" + i + ": " + fragment);
                        }
                        if (this.f294g.contains(fragment)) {
                            throw new IllegalStateException("Already added!");
                        }
                        this.f294g.add(fragment);
                    }
                } else {
                    this.f294g = null;
                }
                if (fragmentManagerState.f192c != null) {
                    this.f296i = new ArrayList(fragmentManagerState.f192c.length);
                    for (i2 = 0; i2 < fragmentManagerState.f192c.length; i2++) {
                        BackStackRecord a2 = fragmentManagerState.f192c[i2].m45a(this);
                        if (f287a) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + a2.f267o + "): " + a2);
                            a2.m215a("  ", new PrintWriter(new LogWriter("FragmentManager")), false);
                        }
                        this.f296i.add(a2);
                        if (a2.f267o >= 0) {
                            m268a(a2.f267o, a2);
                        }
                    }
                    return;
                }
                this.f296i = null;
            }
        }
    }

    public void m273a(Fragment fragment, int i, int i2) {
        if (f287a) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.f130A);
        }
        boolean z = !fragment.m90g();
        if (!fragment.f139J || z) {
            if (this.f294g != null) {
                this.f294g.remove(fragment);
            }
            if (fragment.f142M && fragment.f143N) {
                this.f305r = true;
            }
            fragment.f165u = false;
            fragment.f166v = true;
            m274a(fragment, z ? 0 : 1, i, i2, false);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void m274a(android.support.v4.app.Fragment r10, int r11, int r12, int r13, boolean r14) {
        /*
        r9 = this;
        r8 = 4;
        r6 = 3;
        r3 = 0;
        r5 = 1;
        r7 = 0;
        r0 = r10.f165u;
        if (r0 == 0) goto L_0x000d;
    L_0x0009:
        r0 = r10.f139J;
        if (r0 == 0) goto L_0x0010;
    L_0x000d:
        if (r11 <= r5) goto L_0x0010;
    L_0x000f:
        r11 = r5;
    L_0x0010:
        r0 = r10.f166v;
        if (r0 == 0) goto L_0x001a;
    L_0x0014:
        r0 = r10.f154j;
        if (r11 <= r0) goto L_0x001a;
    L_0x0018:
        r11 = r10.f154j;
    L_0x001a:
        r0 = r10.f149T;
        if (r0 == 0) goto L_0x0025;
    L_0x001e:
        r0 = r10.f154j;
        if (r0 >= r8) goto L_0x0025;
    L_0x0022:
        if (r11 <= r6) goto L_0x0025;
    L_0x0024:
        r11 = r6;
    L_0x0025:
        r0 = r10.f154j;
        if (r0 >= r11) goto L_0x0240;
    L_0x0029:
        r0 = r10.f168x;
        if (r0 == 0) goto L_0x0032;
    L_0x002d:
        r0 = r10.f169y;
        if (r0 != 0) goto L_0x0032;
    L_0x0031:
        return;
    L_0x0032:
        r0 = r10.f155k;
        if (r0 == 0) goto L_0x0040;
    L_0x0036:
        r10.f155k = r7;
        r2 = r10.f156l;
        r0 = r9;
        r1 = r10;
        r4 = r3;
        r0.m274a(r1, r2, r3, r4, r5);
    L_0x0040:
        r0 = r10.f154j;
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0126;
            case 2: goto L_0x01ef;
            case 3: goto L_0x01ef;
            case 4: goto L_0x0210;
            default: goto L_0x0045;
        };
    L_0x0045:
        r10.f154j = r11;
        goto L_0x0031;
    L_0x0048:
        r0 = f287a;
        if (r0 == 0) goto L_0x0064;
    L_0x004c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0064:
        r0 = r10.f157m;
        if (r0 == 0) goto L_0x009d;
    L_0x0068:
        r0 = r10.f157m;
        r1 = "android:view_state";
        r0 = r0.getSparseParcelableArray(r1);
        r10.f158n = r0;
        r0 = r10.f157m;
        r1 = "android:target_state";
        r0 = r9.m262a(r0, r1);
        r10.f162r = r0;
        r0 = r10.f162r;
        if (r0 == 0) goto L_0x008a;
    L_0x0080:
        r0 = r10.f157m;
        r1 = "android:target_req_state";
        r0 = r0.getInt(r1, r3);
        r10.f164t = r0;
    L_0x008a:
        r0 = r10.f157m;
        r1 = "android:user_visible_hint";
        r0 = r0.getBoolean(r1, r5);
        r10.f150U = r0;
        r0 = r10.f150U;
        if (r0 != 0) goto L_0x009d;
    L_0x0098:
        r10.f149T = r5;
        if (r11 <= r6) goto L_0x009d;
    L_0x009c:
        r11 = r6;
    L_0x009d:
        r0 = r9.f302o;
        r10.f132C = r0;
        r0 = r9.f304q;
        r10.f134E = r0;
        r0 = r9.f304q;
        if (r0 == 0) goto L_0x00d9;
    L_0x00a9:
        r0 = r9.f304q;
        r0 = r0.f133D;
    L_0x00ad:
        r10.f131B = r0;
        r10.f144O = r3;
        r0 = r9.f302o;
        r10.m57a(r0);
        r0 = r10.f144O;
        if (r0 != 0) goto L_0x00de;
    L_0x00ba:
        r0 = new android.support.v4.app.ae;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onAttach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x00d9:
        r0 = r9.f302o;
        r0 = r0.f178b;
        goto L_0x00ad;
    L_0x00de:
        r0 = r10.f134E;
        if (r0 != 0) goto L_0x00e7;
    L_0x00e2:
        r0 = r9.f302o;
        r0.m116a(r10);
    L_0x00e7:
        r0 = r10.f141L;
        if (r0 != 0) goto L_0x00f0;
    L_0x00eb:
        r0 = r10.f157m;
        r10.m92h(r0);
    L_0x00f0:
        r10.f141L = r3;
        r0 = r10.f168x;
        if (r0 == 0) goto L_0x0126;
    L_0x00f6:
        r0 = r10.f157m;
        r0 = r10.m69b(r0);
        r1 = r10.f157m;
        r0 = r10.m70b(r0, r7, r1);
        r10.f147R = r0;
        r0 = r10.f147R;
        if (r0 == 0) goto L_0x0239;
    L_0x0108:
        r0 = r10.f147R;
        r10.f148S = r0;
        r0 = r10.f147R;
        r0 = android.support.v4.app.ad.m185a(r0);
        r10.f147R = r0;
        r0 = r10.f138I;
        if (r0 == 0) goto L_0x011f;
    L_0x0118:
        r0 = r10.f147R;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x011f:
        r0 = r10.f147R;
        r1 = r10.f157m;
        r10.m66a(r0, r1);
    L_0x0126:
        if (r11 <= r5) goto L_0x01ef;
    L_0x0128:
        r0 = f287a;
        if (r0 == 0) goto L_0x0144;
    L_0x012c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0144:
        r0 = r10.f168x;
        if (r0 != 0) goto L_0x01df;
    L_0x0148:
        r0 = r10.f136G;
        if (r0 == 0) goto L_0x0397;
    L_0x014c:
        r0 = r9.f303p;
        r1 = r10.f136G;
        r0 = r0.m240a(r1);
        r0 = (android.view.ViewGroup) r0;
        if (r0 != 0) goto L_0x019b;
    L_0x0158:
        r1 = r10.f170z;
        if (r1 != 0) goto L_0x019b;
    L_0x015c:
        r1 = new java.lang.IllegalArgumentException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "No view found for id 0x";
        r2 = r2.append(r3);
        r3 = r10.f136G;
        r3 = java.lang.Integer.toHexString(r3);
        r2 = r2.append(r3);
        r3 = " (";
        r2 = r2.append(r3);
        r3 = r10.m93i();
        r4 = r10.f136G;
        r3 = r3.getResourceName(r4);
        r2 = r2.append(r3);
        r3 = ") for fragment ";
        r2 = r2.append(r3);
        r2 = r2.append(r10);
        r2 = r2.toString();
        r1.<init>(r2);
        r9.m255a(r1);
    L_0x019b:
        r10.f146Q = r0;
        r1 = r10.f157m;
        r1 = r10.m69b(r1);
        r2 = r10.f157m;
        r1 = r10.m70b(r1, r0, r2);
        r10.f147R = r1;
        r1 = r10.f147R;
        if (r1 == 0) goto L_0x023d;
    L_0x01af:
        r1 = r10.f147R;
        r10.f148S = r1;
        r1 = r10.f147R;
        r1 = android.support.v4.app.ad.m185a(r1);
        r10.f147R = r1;
        if (r0 == 0) goto L_0x01cd;
    L_0x01bd:
        r1 = r9.m265a(r10, r12, r5, r13);
        if (r1 == 0) goto L_0x01c8;
    L_0x01c3:
        r2 = r10.f147R;
        r2.startAnimation(r1);
    L_0x01c8:
        r1 = r10.f147R;
        r0.addView(r1);
    L_0x01cd:
        r0 = r10.f138I;
        if (r0 == 0) goto L_0x01d8;
    L_0x01d1:
        r0 = r10.f147R;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x01d8:
        r0 = r10.f147R;
        r1 = r10.f157m;
        r10.m66a(r0, r1);
    L_0x01df:
        r0 = r10.f157m;
        r10.m94i(r0);
        r0 = r10.f147R;
        if (r0 == 0) goto L_0x01ed;
    L_0x01e8:
        r0 = r10.f157m;
        r10.m88f(r0);
    L_0x01ed:
        r10.f157m = r7;
    L_0x01ef:
        if (r11 <= r6) goto L_0x0210;
    L_0x01f1:
        r0 = f287a;
        if (r0 == 0) goto L_0x020d;
    L_0x01f5:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x020d:
        r10.m108v();
    L_0x0210:
        if (r11 <= r8) goto L_0x0045;
    L_0x0212:
        r0 = f287a;
        if (r0 == 0) goto L_0x022e;
    L_0x0216:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "moveto RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x022e:
        r10.f167w = r5;
        r10.m109w();
        r10.f157m = r7;
        r10.f158n = r7;
        goto L_0x0045;
    L_0x0239:
        r10.f148S = r7;
        goto L_0x0126;
    L_0x023d:
        r10.f148S = r7;
        goto L_0x01df;
    L_0x0240:
        r0 = r10.f154j;
        if (r0 <= r11) goto L_0x0045;
    L_0x0244:
        r0 = r10.f154j;
        switch(r0) {
            case 1: goto L_0x024b;
            case 2: goto L_0x02cb;
            case 3: goto L_0x02aa;
            case 4: goto L_0x0289;
            case 5: goto L_0x0265;
            default: goto L_0x0249;
        };
    L_0x0249:
        goto L_0x0045;
    L_0x024b:
        if (r11 >= r5) goto L_0x0045;
    L_0x024d:
        r0 = r9.f307t;
        if (r0 == 0) goto L_0x025c;
    L_0x0251:
        r0 = r10.f155k;
        if (r0 == 0) goto L_0x025c;
    L_0x0255:
        r0 = r10.f155k;
        r10.f155k = r7;
        r0.clearAnimation();
    L_0x025c:
        r0 = r10.f155k;
        if (r0 == 0) goto L_0x0338;
    L_0x0260:
        r10.f156l = r11;
        r11 = r5;
        goto L_0x0045;
    L_0x0265:
        r0 = 5;
        if (r11 >= r0) goto L_0x0289;
    L_0x0268:
        r0 = f287a;
        if (r0 == 0) goto L_0x0284;
    L_0x026c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom RESUMED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0284:
        r10.m111y();
        r10.f167w = r3;
    L_0x0289:
        if (r11 >= r8) goto L_0x02aa;
    L_0x028b:
        r0 = f287a;
        if (r0 == 0) goto L_0x02a7;
    L_0x028f:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STARTED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02a7:
        r10.m112z();
    L_0x02aa:
        if (r11 >= r6) goto L_0x02cb;
    L_0x02ac:
        r0 = f287a;
        if (r0 == 0) goto L_0x02c8;
    L_0x02b0:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom STOPPED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02c8:
        r10.m49A();
    L_0x02cb:
        r0 = 2;
        if (r11 >= r0) goto L_0x024b;
    L_0x02ce:
        r0 = f287a;
        if (r0 == 0) goto L_0x02ea;
    L_0x02d2:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom ACTIVITY_CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x02ea:
        r0 = r10.f147R;
        if (r0 == 0) goto L_0x02fd;
    L_0x02ee:
        r0 = r9.f302o;
        r0 = r0.isFinishing();
        if (r0 != 0) goto L_0x02fd;
    L_0x02f6:
        r0 = r10.f158n;
        if (r0 != 0) goto L_0x02fd;
    L_0x02fa:
        r9.m300f(r10);
    L_0x02fd:
        r10.m50B();
        r0 = r10.f147R;
        if (r0 == 0) goto L_0x0330;
    L_0x0304:
        r0 = r10.f146Q;
        if (r0 == 0) goto L_0x0330;
    L_0x0308:
        r0 = r9.f301n;
        if (r0 <= 0) goto L_0x0394;
    L_0x030c:
        r0 = r9.f307t;
        if (r0 != 0) goto L_0x0394;
    L_0x0310:
        r0 = r9.m265a(r10, r12, r3, r13);
    L_0x0314:
        if (r0 == 0) goto L_0x0329;
    L_0x0316:
        r1 = r10.f147R;
        r10.f155k = r1;
        r10.f156l = r11;
        r1 = new android.support.v4.app.p;
        r1.<init>(r9, r10);
        r0.setAnimationListener(r1);
        r1 = r10.f147R;
        r1.startAnimation(r0);
    L_0x0329:
        r0 = r10.f146Q;
        r1 = r10.f147R;
        r0.removeView(r1);
    L_0x0330:
        r10.f146Q = r7;
        r10.f147R = r7;
        r10.f148S = r7;
        goto L_0x024b;
    L_0x0338:
        r0 = f287a;
        if (r0 == 0) goto L_0x0354;
    L_0x033c:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "movefrom CREATED: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x0354:
        r0 = r10.f141L;
        if (r0 != 0) goto L_0x035b;
    L_0x0358:
        r10.m51C();
    L_0x035b:
        r10.f144O = r3;
        r10.m75c();
        r0 = r10.f144O;
        if (r0 != 0) goto L_0x0383;
    L_0x0364:
        r0 = new android.support.v4.app.ae;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Fragment ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r2 = " did not call through to super.onDetach()";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0383:
        if (r14 != 0) goto L_0x0045;
    L_0x0385:
        r0 = r10.f141L;
        if (r0 != 0) goto L_0x038e;
    L_0x0389:
        r9.m296e(r10);
        goto L_0x0045;
    L_0x038e:
        r10.f132C = r7;
        r10.f131B = r7;
        goto L_0x0045;
    L_0x0394:
        r0 = r7;
        goto L_0x0314;
    L_0x0397:
        r0 = r7;
        goto L_0x019b;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.m.a(android.support.v4.app.Fragment, int, int, int, boolean):void");
    }

    public void m275a(Fragment fragment, boolean z) {
        if (this.f294g == null) {
            this.f294g = new ArrayList();
        }
        if (f287a) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        m294d(fragment);
        if (!fragment.f139J) {
            if (this.f294g.contains(fragment)) {
                throw new IllegalStateException("Fragment already added: " + fragment);
            }
            this.f294g.add(fragment);
            fragment.f165u = true;
            fragment.f166v = false;
            if (fragment.f142M && fragment.f143N) {
                this.f305r = true;
            }
            if (z) {
                m290c(fragment);
            }
        }
    }

    public void m276a(FragmentActivity fragmentActivity, FragmentManager fragmentManager, Fragment fragment) {
        if (this.f302o != null) {
            throw new IllegalStateException("Already attached");
        }
        this.f302o = fragmentActivity;
        this.f303p = fragmentManager;
        this.f304q = fragment;
    }

    public void m277a(Runnable runnable, boolean z) {
        if (!z) {
            m258t();
        }
        synchronized (this) {
            if (this.f307t || this.f302o == null) {
                throw new IllegalStateException("Activity has been destroyed");
            }
            if (this.f290c == null) {
                this.f290c = new ArrayList();
            }
            this.f290c.add(runnable);
            if (this.f290c.size() == 1) {
                this.f302o.f177a.removeCallbacks(this.f312y);
                this.f302o.f177a.post(this.f312y);
            }
        }
    }

    public void m278a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int i;
        Fragment fragment;
        int i2 = 0;
        String str2 = str + "    ";
        if (this.f293f != null) {
            size = this.f293f.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.print("Active Fragments in ");
                printWriter.print(Integer.toHexString(System.identityHashCode(this)));
                printWriter.println(":");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f293f.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment);
                    if (fragment != null) {
                        fragment.m67a(str2, fileDescriptor, printWriter, strArr);
                    }
                }
            }
        }
        if (this.f294g != null) {
            size = this.f294g.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Added Fragments:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f294g.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.f297j != null) {
            size = this.f297j.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Fragments Created Menus:");
                for (i = 0; i < size; i++) {
                    fragment = (Fragment) this.f297j.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(fragment.toString());
                }
            }
        }
        if (this.f296i != null) {
            size = this.f296i.size();
            if (size > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack:");
                for (i = 0; i < size; i++) {
                    BackStackRecord backStackRecord = (BackStackRecord) this.f296i.get(i);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i);
                    printWriter.print(": ");
                    printWriter.println(backStackRecord.toString());
                    backStackRecord.m214a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        synchronized (this) {
            if (this.f298k != null) {
                int size2 = this.f298k.size();
                if (size2 > 0) {
                    printWriter.print(str);
                    printWriter.println("Back Stack Indices:");
                    for (i = 0; i < size2; i++) {
                        backStackRecord = (BackStackRecord) this.f298k.get(i);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i);
                        printWriter.print(": ");
                        printWriter.println(backStackRecord);
                    }
                }
            }
            if (this.f299l != null && this.f299l.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.f299l.toArray()));
            }
        }
        if (this.f290c != null) {
            i = this.f290c.size();
            if (i > 0) {
                printWriter.print(str);
                printWriter.println("Pending Actions:");
                while (i2 < i) {
                    Runnable runnable = (Runnable) this.f290c.get(i2);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i2);
                    printWriter.print(": ");
                    printWriter.println(runnable);
                    i2++;
                }
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mActivity=");
        printWriter.println(this.f302o);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.f303p);
        if (this.f304q != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.f304q);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.f301n);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.f306s);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.f307t);
        if (this.f305r) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.f305r);
        }
        if (this.f308u != null) {
            printWriter.print(str);
            printWriter.print("  mNoTransactionsBecause=");
            printWriter.println(this.f308u);
        }
        if (this.f295h != null && this.f295h.size() > 0) {
            printWriter.print(str);
            printWriter.print("  mAvailIndices: ");
            printWriter.println(Arrays.toString(this.f295h.toArray()));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    boolean m279a(android.os.Handler r9, java.lang.String r10, int r11, int r12) {
        /*
        r8 = this;
        r2 = 1;
        r3 = 0;
        r0 = r8.f296i;
        if (r0 != 0) goto L_0x0007;
    L_0x0006:
        return r3;
    L_0x0007:
        if (r10 != 0) goto L_0x0029;
    L_0x0009:
        if (r11 >= 0) goto L_0x0029;
    L_0x000b:
        r0 = r12 & 1;
        if (r0 != 0) goto L_0x0029;
    L_0x000f:
        r0 = r8.f296i;
        r0 = r0.size();
        r0 = r0 + -1;
        if (r0 < 0) goto L_0x0006;
    L_0x0019:
        r1 = r8.f296i;
        r0 = r1.remove(r0);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        r0.m221b(r2);
        r8.m299f();
    L_0x0027:
        r3 = r2;
        goto L_0x0006;
    L_0x0029:
        r0 = -1;
        if (r10 != 0) goto L_0x002e;
    L_0x002c:
        if (r11 < 0) goto L_0x007d;
    L_0x002e:
        r0 = r8.f296i;
        r0 = r0.size();
        r1 = r0 + -1;
    L_0x0036:
        if (r1 < 0) goto L_0x004c;
    L_0x0038:
        r0 = r8.f296i;
        r0 = r0.get(r1);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        if (r10 == 0) goto L_0x0073;
    L_0x0042:
        r4 = r0.m223c();
        r4 = r10.equals(r4);
        if (r4 == 0) goto L_0x0073;
    L_0x004c:
        if (r1 < 0) goto L_0x0006;
    L_0x004e:
        r0 = r12 & 1;
        if (r0 == 0) goto L_0x007c;
    L_0x0052:
        r1 = r1 + -1;
    L_0x0054:
        if (r1 < 0) goto L_0x007c;
    L_0x0056:
        r0 = r8.f296i;
        r0 = r0.get(r1);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        if (r10 == 0) goto L_0x006a;
    L_0x0060:
        r4 = r0.m223c();
        r4 = r10.equals(r4);
        if (r4 != 0) goto L_0x0070;
    L_0x006a:
        if (r11 < 0) goto L_0x007c;
    L_0x006c:
        r0 = r0.f267o;
        if (r11 != r0) goto L_0x007c;
    L_0x0070:
        r1 = r1 + -1;
        goto L_0x0054;
    L_0x0073:
        if (r11 < 0) goto L_0x0079;
    L_0x0075:
        r0 = r0.f267o;
        if (r11 == r0) goto L_0x004c;
    L_0x0079:
        r1 = r1 + -1;
        goto L_0x0036;
    L_0x007c:
        r0 = r1;
    L_0x007d:
        r1 = r8.f296i;
        r1 = r1.size();
        r1 = r1 + -1;
        if (r0 == r1) goto L_0x0006;
    L_0x0087:
        r5 = new java.util.ArrayList;
        r5.<init>();
        r1 = r8.f296i;
        r1 = r1.size();
        r1 = r1 + -1;
    L_0x0094:
        if (r1 <= r0) goto L_0x00a2;
    L_0x0096:
        r4 = r8.f296i;
        r4 = r4.remove(r1);
        r5.add(r4);
        r1 = r1 + -1;
        goto L_0x0094;
    L_0x00a2:
        r0 = r5.size();
        r6 = r0 + -1;
        r4 = r3;
    L_0x00a9:
        if (r4 > r6) goto L_0x00dd;
    L_0x00ab:
        r0 = f287a;
        if (r0 == 0) goto L_0x00cb;
    L_0x00af:
        r0 = "FragmentManager";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r7 = "Popping back stack state: ";
        r1 = r1.append(r7);
        r7 = r5.get(r4);
        r1 = r1.append(r7);
        r1 = r1.toString();
        android.util.Log.v(r0, r1);
    L_0x00cb:
        r0 = r5.get(r4);
        r0 = (android.support.v4.app.BackStackRecord) r0;
        if (r4 != r6) goto L_0x00db;
    L_0x00d3:
        r1 = r2;
    L_0x00d4:
        r0.m221b(r1);
        r0 = r4 + 1;
        r4 = r0;
        goto L_0x00a9;
    L_0x00db:
        r1 = r3;
        goto L_0x00d4;
    L_0x00dd:
        r8.m299f();
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.m.a(android.os.Handler, java.lang.String, int, int):boolean");
    }

    public boolean m280a(Menu menu) {
        if (this.f294g == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.f294g.size(); i++) {
            Fragment fragment = (Fragment) this.f294g.get(i);
            if (fragment != null && fragment.m77c(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean m281a(Menu menu, MenuInflater menuInflater) {
        boolean z;
        Fragment fragment;
        int i = 0;
        ArrayList arrayList = null;
        if (this.f294g != null) {
            int i2 = 0;
            z = false;
            while (i2 < this.f294g.size()) {
                fragment = (Fragment) this.f294g.get(i2);
                if (fragment != null && fragment.m73b(menu, menuInflater)) {
                    z = true;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                }
                i2++;
                z = z;
            }
        } else {
            z = false;
        }
        if (this.f297j != null) {
            while (i < this.f297j.size()) {
                fragment = (Fragment) this.f297j.get(i);
                if (arrayList == null || !arrayList.contains(fragment)) {
                    fragment.m106t();
                }
                i++;
            }
        }
        this.f297j = arrayList;
        return z;
    }

    public boolean m282a(MenuItem menuItem) {
        if (this.f294g == null) {
            return false;
        }
        for (int i = 0; i < this.f294g.size(); i++) {
            Fragment fragment = (Fragment) this.f294g.get(i);
            if (fragment != null && fragment.m78c(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void m283b(int i) {
        synchronized (this) {
            this.f298k.set(i, null);
            if (this.f299l == null) {
                this.f299l = new ArrayList();
            }
            if (f287a) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.f299l.add(Integer.valueOf(i));
        }
    }

    public void m284b(Fragment fragment) {
        if (!fragment.f149T) {
            return;
        }
        if (this.f292e) {
            this.f309v = true;
            return;
        }
        fragment.f149T = false;
        m274a(fragment, this.f301n, 0, 0, false);
    }

    public void m285b(Fragment fragment, int i, int i2) {
        if (f287a) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (!fragment.f138I) {
            fragment.f138I = true;
            if (fragment.f147R != null) {
                Animation a = m265a(fragment, i, false, i2);
                if (a != null) {
                    fragment.f147R.startAnimation(a);
                }
                fragment.f147R.setVisibility(8);
            }
            if (fragment.f165u && fragment.f142M && fragment.f143N) {
                this.f305r = true;
            }
            fragment.m72b(true);
        }
    }

    void m286b(BackStackRecord backStackRecord) {
        if (this.f296i == null) {
            this.f296i = new ArrayList();
        }
        this.f296i.add(backStackRecord);
        m299f();
    }

    public void m287b(Menu menu) {
        if (this.f294g != null) {
            for (int i = 0; i < this.f294g.size(); i++) {
                Fragment fragment = (Fragment) this.f294g.get(i);
                if (fragment != null) {
                    fragment.m81d(menu);
                }
            }
        }
    }

    public boolean m288b() {
        return m298e();
    }

    public boolean m289b(MenuItem menuItem) {
        if (this.f294g == null) {
            return false;
        }
        for (int i = 0; i < this.f294g.size(); i++) {
            Fragment fragment = (Fragment) this.f294g.get(i);
            if (fragment != null && fragment.m83d(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void m290c(Fragment fragment) {
        m274a(fragment, this.f301n, 0, 0, false);
    }

    public void m291c(Fragment fragment, int i, int i2) {
        if (f287a) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.f138I) {
            fragment.f138I = false;
            if (fragment.f147R != null) {
                Animation a = m265a(fragment, i, true, i2);
                if (a != null) {
                    fragment.f147R.startAnimation(a);
                }
                fragment.f147R.setVisibility(0);
            }
            if (fragment.f165u && fragment.f142M && fragment.f143N) {
                this.f305r = true;
            }
            fragment.m72b(false);
        }
    }

    public boolean m292c() {
        m258t();
        m288b();
        return m279a(this.f302o.f177a, null, -1, 0);
    }

    void m293d() {
        if (this.f293f != null) {
            for (int i = 0; i < this.f293f.size(); i++) {
                Fragment fragment = (Fragment) this.f293f.get(i);
                if (fragment != null) {
                    m284b(fragment);
                }
            }
        }
    }

    void m294d(Fragment fragment) {
        if (fragment.f159o < 0) {
            if (this.f295h == null || this.f295h.size() <= 0) {
                if (this.f293f == null) {
                    this.f293f = new ArrayList();
                }
                fragment.m56a(this.f293f.size(), this.f304q);
                this.f293f.add(fragment);
            } else {
                fragment.m56a(((Integer) this.f295h.remove(this.f295h.size() - 1)).intValue(), this.f304q);
                this.f293f.set(fragment.f159o, fragment);
            }
            if (f287a) {
                Log.v("FragmentManager", "Allocated fragment index " + fragment);
            }
        }
    }

    public void m295d(Fragment fragment, int i, int i2) {
        if (f287a) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (!fragment.f139J) {
            fragment.f139J = true;
            if (fragment.f165u) {
                if (this.f294g != null) {
                    if (f287a) {
                        Log.v("FragmentManager", "remove from detach: " + fragment);
                    }
                    this.f294g.remove(fragment);
                }
                if (fragment.f142M && fragment.f143N) {
                    this.f305r = true;
                }
                fragment.f165u = false;
                m274a(fragment, 1, i, i2, false);
            }
        }
    }

    void m296e(Fragment fragment) {
        if (fragment.f159o >= 0) {
            if (f287a) {
                Log.v("FragmentManager", "Freeing fragment index " + fragment);
            }
            this.f293f.set(fragment.f159o, null);
            if (this.f295h == null) {
                this.f295h = new ArrayList();
            }
            this.f295h.add(Integer.valueOf(fragment.f159o));
            this.f302o.m118a(fragment.f160p);
            fragment.m105s();
        }
    }

    public void m297e(Fragment fragment, int i, int i2) {
        if (f287a) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.f139J) {
            fragment.f139J = false;
            if (!fragment.f165u) {
                if (this.f294g == null) {
                    this.f294g = new ArrayList();
                }
                if (this.f294g.contains(fragment)) {
                    throw new IllegalStateException("Fragment already added: " + fragment);
                }
                if (f287a) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                this.f294g.add(fragment);
                fragment.f165u = true;
                if (fragment.f142M && fragment.f143N) {
                    this.f305r = true;
                }
                m274a(fragment, this.f301n, i, i2, false);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m298e() {
        /*
        r6 = this;
        r0 = 1;
        r2 = 0;
        r1 = r6.f292e;
        if (r1 == 0) goto L_0x000e;
    L_0x0006:
        r0 = new java.lang.IllegalStateException;
        r1 = "Recursive entry to executePendingTransactions";
        r0.<init>(r1);
        throw r0;
    L_0x000e:
        r1 = android.os.Looper.myLooper();
        r3 = r6.f302o;
        r3 = r3.f177a;
        r3 = r3.getLooper();
        if (r1 == r3) goto L_0x0024;
    L_0x001c:
        r0 = new java.lang.IllegalStateException;
        r1 = "Must be called from main thread of process";
        r0.<init>(r1);
        throw r0;
    L_0x0024:
        r1 = r2;
    L_0x0025:
        monitor-enter(r6);
        r3 = r6.f290c;	 Catch:{ all -> 0x0097 }
        if (r3 == 0) goto L_0x0032;
    L_0x002a:
        r3 = r6.f290c;	 Catch:{ all -> 0x0097 }
        r3 = r3.size();	 Catch:{ all -> 0x0097 }
        if (r3 != 0) goto L_0x005a;
    L_0x0032:
        monitor-exit(r6);	 Catch:{ all -> 0x0097 }
        r0 = r6.f309v;
        if (r0 == 0) goto L_0x00a5;
    L_0x0037:
        r3 = r2;
        r4 = r2;
    L_0x0039:
        r0 = r6.f293f;
        r0 = r0.size();
        if (r3 >= r0) goto L_0x009e;
    L_0x0041:
        r0 = r6.f293f;
        r0 = r0.get(r3);
        r0 = (android.support.v4.app.Fragment) r0;
        if (r0 == 0) goto L_0x0056;
    L_0x004b:
        r5 = r0.f151V;
        if (r5 == 0) goto L_0x0056;
    L_0x004f:
        r0 = r0.f151V;
        r0 = r0.m153a();
        r4 = r4 | r0;
    L_0x0056:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0039;
    L_0x005a:
        r1 = r6.f290c;	 Catch:{ all -> 0x0097 }
        r3 = r1.size();	 Catch:{ all -> 0x0097 }
        r1 = r6.f291d;	 Catch:{ all -> 0x0097 }
        if (r1 == 0) goto L_0x0069;
    L_0x0064:
        r1 = r6.f291d;	 Catch:{ all -> 0x0097 }
        r1 = r1.length;	 Catch:{ all -> 0x0097 }
        if (r1 >= r3) goto L_0x006d;
    L_0x0069:
        r1 = new java.lang.Runnable[r3];	 Catch:{ all -> 0x0097 }
        r6.f291d = r1;	 Catch:{ all -> 0x0097 }
    L_0x006d:
        r1 = r6.f290c;	 Catch:{ all -> 0x0097 }
        r4 = r6.f291d;	 Catch:{ all -> 0x0097 }
        r1.toArray(r4);	 Catch:{ all -> 0x0097 }
        r1 = r6.f290c;	 Catch:{ all -> 0x0097 }
        r1.clear();	 Catch:{ all -> 0x0097 }
        r1 = r6.f302o;	 Catch:{ all -> 0x0097 }
        r1 = r1.f177a;	 Catch:{ all -> 0x0097 }
        r4 = r6.f312y;	 Catch:{ all -> 0x0097 }
        r1.removeCallbacks(r4);	 Catch:{ all -> 0x0097 }
        monitor-exit(r6);	 Catch:{ all -> 0x0097 }
        r6.f292e = r0;
        r1 = r2;
    L_0x0086:
        if (r1 >= r3) goto L_0x009a;
    L_0x0088:
        r4 = r6.f291d;
        r4 = r4[r1];
        r4.run();
        r4 = r6.f291d;
        r5 = 0;
        r4[r1] = r5;
        r1 = r1 + 1;
        goto L_0x0086;
    L_0x0097:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0097 }
        throw r0;
    L_0x009a:
        r6.f292e = r2;
        r1 = r0;
        goto L_0x0025;
    L_0x009e:
        if (r4 != 0) goto L_0x00a5;
    L_0x00a0:
        r6.f309v = r2;
        r6.m293d();
    L_0x00a5:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.app.m.e():boolean");
    }

    void m299f() {
        if (this.f300m != null) {
            for (int i = 0; i < this.f300m.size(); i++) {
                ((FragmentManager) this.f300m.get(i)).m245a();
            }
        }
    }

    void m300f(Fragment fragment) {
        if (fragment.f148S != null) {
            if (this.f311x == null) {
                this.f311x = new SparseArray();
            } else {
                this.f311x.clear();
            }
            fragment.f148S.saveHierarchyState(this.f311x);
            if (this.f311x.size() > 0) {
                fragment.f158n = this.f311x;
                this.f311x = null;
            }
        }
    }

    Bundle m301g(Fragment fragment) {
        Bundle bundle;
        if (this.f310w == null) {
            this.f310w = new Bundle();
        }
        fragment.m96j(this.f310w);
        if (this.f310w.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.f310w;
            this.f310w = null;
        }
        if (fragment.f147R != null) {
            m300f(fragment);
        }
        if (fragment.f158n != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.f158n);
        }
        if (!fragment.f150U) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.f150U);
        }
        return bundle;
    }

    ArrayList<Fragment> m302g() {
        ArrayList<Fragment> arrayList = null;
        if (this.f293f != null) {
            for (int i = 0; i < this.f293f.size(); i++) {
                Fragment fragment = (Fragment) this.f293f.get(i);
                if (fragment != null && fragment.f140K) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(fragment);
                    fragment.f141L = true;
                    fragment.f163s = fragment.f162r != null ? fragment.f162r.f159o : -1;
                    if (f287a) {
                        Log.v("FragmentManager", "retainNonConfig: keeping retained " + fragment);
                    }
                }
            }
        }
        return arrayList;
    }

    Parcelable m303h() {
        BackStackState[] backStackStateArr = null;
        m298e();
        if (f288b) {
            this.f306s = true;
        }
        if (this.f293f == null || this.f293f.size() <= 0) {
            return null;
        }
        int size = this.f293f.size();
        FragmentState[] fragmentStateArr = new FragmentState[size];
        int i = 0;
        boolean z = false;
        while (i < size) {
            boolean z2;
            Fragment fragment = (Fragment) this.f293f.get(i);
            if (fragment != null) {
                if (fragment.f159o < 0) {
                    m255a(new IllegalStateException("Failure saving state: active " + fragment + " has cleared index: " + fragment.f159o));
                }
                FragmentState fragmentState = new FragmentState(fragment);
                fragmentStateArr[i] = fragmentState;
                if (fragment.f154j <= 0 || fragmentState.f202j != null) {
                    fragmentState.f202j = fragment.f157m;
                } else {
                    fragmentState.f202j = m301g(fragment);
                    if (fragment.f162r != null) {
                        if (fragment.f162r.f159o < 0) {
                            m255a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.f162r));
                        }
                        if (fragmentState.f202j == null) {
                            fragmentState.f202j = new Bundle();
                        }
                        m271a(fragmentState.f202j, "android:target_state", fragment.f162r);
                        if (fragment.f164t != 0) {
                            fragmentState.f202j.putInt("android:target_req_state", fragment.f164t);
                        }
                    }
                }
                if (f287a) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + fragmentState.f202j);
                }
                z2 = true;
            } else {
                z2 = z;
            }
            i++;
            z = z2;
        }
        if (z) {
            int[] iArr;
            int i2;
            FragmentManagerState fragmentManagerState;
            if (this.f294g != null) {
                i = this.f294g.size();
                if (i > 0) {
                    iArr = new int[i];
                    for (i2 = 0; i2 < i; i2++) {
                        iArr[i2] = ((Fragment) this.f294g.get(i2)).f159o;
                        if (iArr[i2] < 0) {
                            m255a(new IllegalStateException("Failure saving state: active " + this.f294g.get(i2) + " has cleared index: " + iArr[i2]));
                        }
                        if (f287a) {
                            Log.v("FragmentManager", "saveAllState: adding fragment #" + i2 + ": " + this.f294g.get(i2));
                        }
                    }
                    if (this.f296i != null) {
                        i = this.f296i.size();
                        if (i > 0) {
                            backStackStateArr = new BackStackState[i];
                            for (i2 = 0; i2 < i; i2++) {
                                backStackStateArr[i2] = new BackStackState(this, (BackStackRecord) this.f296i.get(i2));
                                if (f287a) {
                                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f296i.get(i2));
                                }
                            }
                        }
                    }
                    fragmentManagerState = new FragmentManagerState();
                    fragmentManagerState.f190a = fragmentStateArr;
                    fragmentManagerState.f191b = iArr;
                    fragmentManagerState.f192c = backStackStateArr;
                    return fragmentManagerState;
                }
            }
            iArr = null;
            if (this.f296i != null) {
                i = this.f296i.size();
                if (i > 0) {
                    backStackStateArr = new BackStackState[i];
                    for (i2 = 0; i2 < i; i2++) {
                        backStackStateArr[i2] = new BackStackState(this, (BackStackRecord) this.f296i.get(i2));
                        if (f287a) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f296i.get(i2));
                        }
                    }
                }
            }
            fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.f190a = fragmentStateArr;
            fragmentManagerState.f191b = iArr;
            fragmentManagerState.f192c = backStackStateArr;
            return fragmentManagerState;
        } else if (!f287a) {
            return null;
        } else {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return null;
        }
    }

    public void m304i() {
        this.f306s = false;
    }

    public void m305j() {
        this.f306s = false;
        m269a(1, false);
    }

    public void m306k() {
        this.f306s = false;
        m269a(2, false);
    }

    public void m307l() {
        this.f306s = false;
        m269a(4, false);
    }

    public void m308m() {
        this.f306s = false;
        m269a(5, false);
    }

    public void m309n() {
        m269a(4, false);
    }

    public void m310o() {
        this.f306s = true;
        m269a(3, false);
    }

    public void m311p() {
        m269a(2, false);
    }

    public void m312q() {
        m269a(1, false);
    }

    public void m313r() {
        this.f307t = true;
        m298e();
        m269a(0, false);
        this.f302o = null;
        this.f303p = null;
        this.f304q = null;
    }

    public void m314s() {
        if (this.f294g != null) {
            for (int i = 0; i < this.f294g.size(); i++) {
                Fragment fragment = (Fragment) this.f294g.get(i);
                if (fragment != null) {
                    fragment.m110x();
                }
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("FragmentManager{");
        stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder.append(" in ");
        if (this.f304q != null) {
            DebugUtils.m419a(this.f304q, stringBuilder);
        } else {
            DebugUtils.m419a(this.f302o, stringBuilder);
        }
        stringBuilder.append("}}");
        return stringBuilder.toString();
    }
}
