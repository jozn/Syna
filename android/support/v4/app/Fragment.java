package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.p008c.DebugUtils;
import android.support.v4.p008c.SimpleArrayMap;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class Fragment implements ComponentCallbacks, OnCreateContextMenuListener {
    private static final SimpleArrayMap<String, Class<?>> f129a;
    int f130A;
    FragmentManager f131B;
    FragmentActivity f132C;
    FragmentManager f133D;
    Fragment f134E;
    int f135F;
    int f136G;
    String f137H;
    boolean f138I;
    boolean f139J;
    boolean f140K;
    boolean f141L;
    boolean f142M;
    boolean f143N;
    boolean f144O;
    int f145P;
    ViewGroup f146Q;
    View f147R;
    View f148S;
    boolean f149T;
    boolean f150U;
    aa f151V;
    boolean f152W;
    boolean f153X;
    int f154j;
    View f155k;
    int f156l;
    Bundle f157m;
    SparseArray<Parcelable> f158n;
    int f159o;
    String f160p;
    Bundle f161q;
    Fragment f162r;
    int f163s;
    int f164t;
    boolean f165u;
    boolean f166v;
    boolean f167w;
    boolean f168x;
    boolean f169y;
    boolean f170z;

    public static class SavedState implements Parcelable {
        public static final Creator<SavedState> CREATOR;
        final Bundle f128a;

        static {
            CREATOR = new Fragment();
        }

        SavedState(Bundle bundle) {
            this.f128a = bundle;
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            this.f128a = parcel.readBundle();
            if (classLoader != null && this.f128a != null) {
                this.f128a.setClassLoader(classLoader);
            }
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeBundle(this.f128a);
        }
    }

    /* renamed from: android.support.v4.app.Fragment.a */
    public static class C0000a extends RuntimeException {
        public C0000a(String str, Exception exception) {
            super(str, exception);
        }
    }

    static {
        f129a = new SimpleArrayMap();
    }

    public Fragment() {
        this.f154j = 0;
        this.f159o = -1;
        this.f163s = -1;
        this.f143N = true;
        this.f150U = true;
    }

    public static Fragment m46a(Context context, String str) {
        return m47a(context, str, null);
    }

    public static Fragment m47a(Context context, String str, Bundle bundle) {
        try {
            Class cls = (Class) f129a.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                f129a.put(str, cls);
            }
            Fragment fragment = (Fragment) cls.newInstance();
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.f161q = bundle;
            }
            return fragment;
        } catch (Exception e) {
            throw new C0000a("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e);
        } catch (Exception e2) {
            throw new C0000a("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e2);
        } catch (Exception e22) {
            throw new C0000a("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an" + " empty constructor that is public", e22);
        }
    }

    static boolean m48b(Context context, String str) {
        try {
            Class cls = (Class) f129a.get(str);
            if (cls == null) {
                cls = context.getClassLoader().loadClass(str);
                f129a.put(str, cls);
            }
            return Fragment.class.isAssignableFrom(cls);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    void m49A() {
        if (this.f133D != null) {
            this.f133D.m311p();
        }
        if (this.f152W) {
            this.f152W = false;
            if (!this.f153X) {
                this.f153X = true;
                this.f151V = this.f132C.m115a(this.f160p, this.f152W, false);
            }
            if (this.f151V == null) {
                return;
            }
            if (this.f132C.f184h) {
                this.f151V.m157d();
            } else {
                this.f151V.m156c();
            }
        }
    }

    void m50B() {
        if (this.f133D != null) {
            this.f133D.m312q();
        }
        this.f144O = false;
        m87f();
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onDestroyView()");
        } else if (this.f151V != null) {
            this.f151V.m159f();
        }
    }

    void m51C() {
        if (this.f133D != null) {
            this.f133D.m313r();
        }
        this.f144O = false;
        m104r();
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onDestroy()");
        }
    }

    public View m52a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return null;
    }

    public Animation m53a(int i, boolean z, int i2) {
        return null;
    }

    public final String m54a(int i) {
        return m93i().getString(i);
    }

    public void m55a(int i, int i2, Intent intent) {
    }

    final void m56a(int i, Fragment fragment) {
        this.f159o = i;
        if (fragment != null) {
            this.f160p = fragment.f160p + ":" + this.f159o;
        } else {
            this.f160p = "android:fragment:" + this.f159o;
        }
    }

    public void m57a(Activity activity) {
        this.f144O = true;
    }

    public void m58a(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.f144O = true;
    }

    public void m59a(Intent intent) {
        if (this.f132C == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.f132C.m117a(this, intent, -1);
    }

    void m60a(Configuration configuration) {
        onConfigurationChanged(configuration);
        if (this.f133D != null) {
            this.f133D.m270a(configuration);
        }
    }

    public void m61a(Bundle bundle) {
        this.f144O = true;
    }

    public void m62a(SavedState savedState) {
        if (this.f159o >= 0) {
            throw new IllegalStateException("Fragment already active");
        }
        Bundle bundle = (savedState == null || savedState.f128a == null) ? null : savedState.f128a;
        this.f157m = bundle;
    }

    public void m63a(Menu menu) {
    }

    public void m64a(Menu menu, MenuInflater menuInflater) {
    }

    public void m65a(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public void m66a(View view, Bundle bundle) {
    }

    public void m67a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.f135F));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.f136G));
        printWriter.print(" mTag=");
        printWriter.println(this.f137H);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f154j);
        printWriter.print(" mIndex=");
        printWriter.print(this.f159o);
        printWriter.print(" mWho=");
        printWriter.print(this.f160p);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.f130A);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f165u);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f166v);
        printWriter.print(" mResumed=");
        printWriter.print(this.f167w);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.f168x);
        printWriter.print(" mInLayout=");
        printWriter.println(this.f169y);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.f138I);
        printWriter.print(" mDetached=");
        printWriter.print(this.f139J);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.f143N);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.f142M);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.f140K);
        printWriter.print(" mRetaining=");
        printWriter.print(this.f141L);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.f150U);
        if (this.f131B != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.f131B);
        }
        if (this.f132C != null) {
            printWriter.print(str);
            printWriter.print("mActivity=");
            printWriter.println(this.f132C);
        }
        if (this.f134E != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.f134E);
        }
        if (this.f161q != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f161q);
        }
        if (this.f157m != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f157m);
        }
        if (this.f158n != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f158n);
        }
        if (this.f162r != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(this.f162r);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f164t);
        }
        if (this.f145P != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(this.f145P);
        }
        if (this.f146Q != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.f146Q);
        }
        if (this.f147R != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.f147R);
        }
        if (this.f148S != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.f147R);
        }
        if (this.f155k != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(this.f155k);
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(this.f156l);
        }
        if (this.f151V != null) {
            printWriter.print(str);
            printWriter.println("Loader Manager:");
            this.f151V.m152a(str + "  ", fileDescriptor, printWriter, strArr);
        }
        if (this.f133D != null) {
            printWriter.print(str);
            printWriter.println("Child " + this.f133D + ":");
            this.f133D.m278a(str + "  ", fileDescriptor, printWriter, strArr);
        }
    }

    public boolean m68a(MenuItem menuItem) {
        return false;
    }

    public LayoutInflater m69b(Bundle bundle) {
        return this.f132C.getLayoutInflater();
    }

    View m70b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.f133D != null) {
            this.f133D.m304i();
        }
        return m52a(layoutInflater, viewGroup, bundle);
    }

    public void m71b(Menu menu) {
    }

    public void m72b(boolean z) {
    }

    boolean m73b(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.f138I) {
            return false;
        }
        if (this.f142M && this.f143N) {
            z = true;
            m64a(menu, menuInflater);
        }
        return this.f133D != null ? z | this.f133D.m281a(menu, menuInflater) : z;
    }

    public boolean m74b(MenuItem menuItem) {
        return false;
    }

    public void m75c() {
        this.f144O = true;
    }

    public void m76c(boolean z) {
        if (this.f142M != z) {
            this.f142M = z;
            if (m97k() && !m99m()) {
                this.f132C.e_();
            }
        }
    }

    boolean m77c(Menu menu) {
        boolean z = false;
        if (this.f138I) {
            return false;
        }
        if (this.f142M && this.f143N) {
            z = true;
            m63a(menu);
        }
        return this.f133D != null ? z | this.f133D.m280a(menu) : z;
    }

    boolean m78c(MenuItem menuItem) {
        if (!this.f138I) {
            if (this.f142M && this.f143N && m68a(menuItem)) {
                return true;
            }
            if (this.f133D != null && this.f133D.m282a(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void m79d() {
        this.f144O = true;
        if (!this.f152W) {
            this.f152W = true;
            if (!this.f153X) {
                this.f153X = true;
                this.f151V = this.f132C.m115a(this.f160p, this.f152W, false);
            }
            if (this.f151V != null) {
                this.f151V.m155b();
            }
        }
    }

    public void m80d(Bundle bundle) {
        this.f144O = true;
    }

    void m81d(Menu menu) {
        if (!this.f138I) {
            if (this.f142M && this.f143N) {
                m71b(menu);
            }
            if (this.f133D != null) {
                this.f133D.m287b(menu);
            }
        }
    }

    public void m82d(boolean z) {
        if (this.f143N != z) {
            this.f143N = z;
            if (this.f142M && m97k() && !m99m()) {
                this.f132C.e_();
            }
        }
    }

    boolean m83d(MenuItem menuItem) {
        if (!this.f138I) {
            if (m74b(menuItem)) {
                return true;
            }
            if (this.f133D != null && this.f133D.m289b(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void m84e() {
        this.f144O = true;
    }

    public void m85e(Bundle bundle) {
    }

    public void m86e(boolean z) {
        if (!this.f150U && z && this.f154j < 4) {
            this.f131B.m284b(this);
        }
        this.f150U = z;
        this.f149T = !z;
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void m87f() {
        this.f144O = true;
    }

    final void m88f(Bundle bundle) {
        if (this.f158n != null) {
            this.f148S.restoreHierarchyState(this.f158n);
            this.f158n = null;
        }
        this.f144O = false;
        m89g(bundle);
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public void m89g(Bundle bundle) {
        this.f144O = true;
    }

    final boolean m90g() {
        return this.f130A > 0;
    }

    public final FragmentActivity m91h() {
        return this.f132C;
    }

    void m92h(Bundle bundle) {
        if (this.f133D != null) {
            this.f133D.m304i();
        }
        this.f144O = false;
        m61a(bundle);
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onCreate()");
        } else if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            if (parcelable != null) {
                if (this.f133D == null) {
                    m107u();
                }
                this.f133D.m272a(parcelable, null);
                this.f133D.m305j();
            }
        }
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final Resources m93i() {
        if (this.f132C != null) {
            return this.f132C.getResources();
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    void m94i(Bundle bundle) {
        if (this.f133D != null) {
            this.f133D.m304i();
        }
        this.f144O = false;
        m80d(bundle);
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onActivityCreated()");
        } else if (this.f133D != null) {
            this.f133D.m306k();
        }
    }

    public final FragmentManager m95j() {
        return this.f131B;
    }

    void m96j(Bundle bundle) {
        m85e(bundle);
        if (this.f133D != null) {
            Parcelable h = this.f133D.m303h();
            if (h != null) {
                bundle.putParcelable("android:support:fragments", h);
            }
        }
    }

    public final boolean m97k() {
        return this.f132C != null && this.f165u;
    }

    public final boolean m98l() {
        return this.f139J;
    }

    public final boolean m99m() {
        return this.f138I;
    }

    public LoaderManager m100n() {
        if (this.f151V != null) {
            return this.f151V;
        }
        if (this.f132C == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.f153X = true;
        this.f151V = this.f132C.m115a(this.f160p, this.f152W, true);
        return this.f151V;
    }

    public View m101o() {
        return this.f147R;
    }

    public void onConfigurationChanged(Configuration configuration) {
        this.f144O = true;
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        m91h().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onLowMemory() {
        this.f144O = true;
    }

    public void m102p() {
        this.f144O = true;
    }

    public void m103q() {
        this.f144O = true;
    }

    public void m104r() {
        this.f144O = true;
        if (!this.f153X) {
            this.f153X = true;
            this.f151V = this.f132C.m115a(this.f160p, this.f152W, false);
        }
        if (this.f151V != null) {
            this.f151V.m161h();
        }
    }

    void m105s() {
        this.f159o = -1;
        this.f160p = null;
        this.f165u = false;
        this.f166v = false;
        this.f167w = false;
        this.f168x = false;
        this.f169y = false;
        this.f170z = false;
        this.f130A = 0;
        this.f131B = null;
        this.f132C = null;
        this.f135F = 0;
        this.f136G = 0;
        this.f137H = null;
        this.f138I = false;
        this.f139J = false;
        this.f141L = false;
        this.f151V = null;
        this.f152W = false;
        this.f153X = false;
    }

    public void m106t() {
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        DebugUtils.m419a(this, stringBuilder);
        if (this.f159o >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.f159o);
        }
        if (this.f135F != 0) {
            stringBuilder.append(" id=0x");
            stringBuilder.append(Integer.toHexString(this.f135F));
        }
        if (this.f137H != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.f137H);
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }

    void m107u() {
        this.f133D = new FragmentManager();
        this.f133D.m276a(this.f132C, new Fragment(this), this);
    }

    void m108v() {
        if (this.f133D != null) {
            this.f133D.m304i();
            this.f133D.m298e();
        }
        this.f144O = false;
        m79d();
        if (this.f144O) {
            if (this.f133D != null) {
                this.f133D.m307l();
            }
            if (this.f151V != null) {
                this.f151V.m160g();
                return;
            }
            return;
        }
        throw new ae("Fragment " + this + " did not call through to super.onStart()");
    }

    void m109w() {
        if (this.f133D != null) {
            this.f133D.m304i();
            this.f133D.m298e();
        }
        this.f144O = false;
        m102p();
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onResume()");
        } else if (this.f133D != null) {
            this.f133D.m308m();
            this.f133D.m298e();
        }
    }

    void m110x() {
        onLowMemory();
        if (this.f133D != null) {
            this.f133D.m314s();
        }
    }

    void m111y() {
        if (this.f133D != null) {
            this.f133D.m309n();
        }
        this.f144O = false;
        m103q();
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    void m112z() {
        if (this.f133D != null) {
            this.f133D.m310o();
        }
        this.f144O = false;
        m84e();
        if (!this.f144O) {
            throw new ae("Fragment " + this + " did not call through to super.onStop()");
        }
    }
}
