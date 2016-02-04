package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* renamed from: android.support.v4.app.t */
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private final FragmentManager f323a;
    private FragmentTransaction f324b;
    private ArrayList<SavedState> f325c;
    private ArrayList<Fragment> f326d;
    private Fragment f327e;

    public FragmentStatePagerAdapter(FragmentManager fragmentManager) {
        this.f324b = null;
        this.f325c = new ArrayList();
        this.f326d = new ArrayList();
        this.f327e = null;
        this.f323a = fragmentManager;
    }

    public Parcelable m350a() {
        Bundle bundle = null;
        if (this.f325c.size() > 0) {
            bundle = new Bundle();
            Parcelable[] parcelableArr = new SavedState[this.f325c.size()];
            this.f325c.toArray(parcelableArr);
            bundle.putParcelableArray("states", parcelableArr);
        }
        Parcelable parcelable = bundle;
        for (int i = 0; i < this.f326d.size(); i++) {
            Fragment fragment = (Fragment) this.f326d.get(i);
            if (fragment != null) {
                if (parcelable == null) {
                    parcelable = new Bundle();
                }
                this.f323a.m251a(parcelable, "f" + i, fragment);
            }
        }
        return parcelable;
    }

    public abstract Fragment m351a(int i);

    public Object m352a(ViewGroup viewGroup, int i) {
        if (this.f326d.size() > i) {
            Fragment fragment = (Fragment) this.f326d.get(i);
            if (fragment != null) {
                return fragment;
            }
        }
        if (this.f324b == null) {
            this.f324b = this.f323a.m249a();
        }
        Fragment a = m351a(i);
        if (this.f325c.size() > i) {
            SavedState savedState = (SavedState) this.f325c.get(i);
            if (savedState != null) {
                a.m62a(savedState);
            }
        }
        while (this.f326d.size() <= i) {
            this.f326d.add(null);
        }
        a.m82d(false);
        a.m86e(false);
        this.f326d.set(i, a);
        this.f324b.m197a(viewGroup.getId(), a);
        return a;
    }

    public void m353a(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.f325c.clear();
            this.f326d.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.f325c.add((SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith("f")) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment a = this.f323a.m247a(bundle, str);
                    if (a != null) {
                        while (this.f326d.size() <= parseInt) {
                            this.f326d.add(null);
                        }
                        a.m82d(false);
                        this.f326d.set(parseInt, a);
                    } else {
                        Log.w("FragmentStatePagerAdapter", "Bad fragment at key " + str);
                    }
                }
            }
        }
    }

    public void m354a(ViewGroup viewGroup) {
    }

    public void m355a(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.f324b == null) {
            this.f324b = this.f323a.m249a();
        }
        while (this.f325c.size() <= i) {
            this.f325c.add(null);
        }
        this.f325c.set(i, this.f323a.m246a(fragment));
        this.f326d.set(i, null);
        this.f324b.m199a(fragment);
    }

    public boolean m356a(View view, Object obj) {
        return ((Fragment) obj).m101o() == view;
    }

    public void m357b(ViewGroup viewGroup) {
        if (this.f324b != null) {
            this.f324b.m201b();
            this.f324b = null;
            this.f323a.m252b();
        }
    }

    public void m358b(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (fragment != this.f327e) {
            if (this.f327e != null) {
                this.f327e.m82d(false);
                this.f327e.m86e(false);
            }
            if (fragment != null) {
                fragment.m82d(true);
                fragment.m86e(true);
            }
            this.f327e = fragment;
        }
    }
}
