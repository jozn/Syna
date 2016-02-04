package android.support.v4.app;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.app.r */
public abstract class FragmentPagerAdapter extends PagerAdapter {
    private final FragmentManager f320a;
    private FragmentTransaction f321b;
    private Fragment f322c;

    public FragmentPagerAdapter(FragmentManager fragmentManager) {
        this.f321b = null;
        this.f322c = null;
        this.f320a = fragmentManager;
    }

    private static String m337a(int i, long j) {
        return "android:switcher:" + i + ":" + j;
    }

    public Parcelable m338a() {
        return null;
    }

    public abstract Fragment m339a(int i);

    public Object m340a(ViewGroup viewGroup, int i) {
        if (this.f321b == null) {
            this.f321b = this.f320a.m249a();
        }
        long b = m345b(i);
        Fragment a = this.f320a.m248a(FragmentPagerAdapter.m337a(viewGroup.getId(), b));
        if (a != null) {
            this.f321b.m204c(a);
        } else {
            a = m339a(i);
            this.f321b.m198a(viewGroup.getId(), a, FragmentPagerAdapter.m337a(viewGroup.getId(), b));
        }
        if (a != this.f322c) {
            a.m82d(false);
            a.m86e(false);
        }
        return a;
    }

    public void m341a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void m342a(ViewGroup viewGroup) {
    }

    public void m343a(ViewGroup viewGroup, int i, Object obj) {
        if (this.f321b == null) {
            this.f321b = this.f320a.m249a();
        }
        this.f321b.m203b((Fragment) obj);
    }

    public boolean m344a(View view, Object obj) {
        return ((Fragment) obj).m101o() == view;
    }

    public long m345b(int i) {
        return (long) i;
    }

    public void m346b(ViewGroup viewGroup) {
        if (this.f321b != null) {
            this.f321b.m201b();
            this.f321b = null;
            this.f320a.m252b();
        }
    }

    public void m347b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.f322c) {
            if (this.f322c != null) {
                this.f322c.m82d(false);
                this.f322c.m86e(false);
            }
            if (fragment != null) {
                fragment.m82d(true);
                fragment.m86e(true);
            }
            this.f322c = fragment;
        }
    }
}
