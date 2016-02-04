package android.support.v4.app;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/* renamed from: android.support.v4.app.y */
class ListFragment implements OnItemClickListener {
    final /* synthetic */ ListFragment f340a;

    ListFragment(ListFragment listFragment) {
        this.f340a = listFragment;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f340a.m367a((ListView) adapterView, view, i, j);
    }
}
 renamed from: android.support.v4.app.w */
public class ListFragment extends Fragment {
    private final Runnable f328Y;
    private final OnItemClickListener f329Z;
    ListAdapter f330a;
    ListView f331b;
    View f332c;
    TextView f333d;
    View f334e;
    View f335f;
    CharSequence f336g;
    boolean f337h;
    private final Handler f338i;

    public ListFragment() {
        this.f338i = new Handler();
        this.f328Y = new ListFragment(this);
        this.f329Z = new ListFragment(this);
    }

    private void m361D() {
        if (this.f331b == null) {
            View o = m101o();
            if (o == null) {
                throw new IllegalStateException("Content view not yet created");
            }
            if (o instanceof ListView) {
                this.f331b = (ListView) o;
            } else {
                this.f333d = (TextView) o.findViewById(16711681);
                if (this.f333d == null) {
                    this.f332c = o.findViewById(16908292);
                } else {
                    this.f333d.setVisibility(8);
                }
                this.f334e = o.findViewById(16711682);
                this.f335f = o.findViewById(16711683);
                o = o.findViewById(16908298);
                if (o instanceof ListView) {
                    this.f331b = (ListView) o;
                    if (this.f332c != null) {
                        this.f331b.setEmptyView(this.f332c);
                    } else if (this.f336g != null) {
                        this.f333d.setText(this.f336g);
                        this.f331b.setEmptyView(this.f333d);
                    }
                } else if (o == null) {
                    throw new RuntimeException("Your content must have a ListView whose id attribute is 'android.R.id.list'");
                } else {
                    throw new RuntimeException("Content has view with id attribute 'android.R.id.list' that is not a ListView class");
                }
            }
            this.f337h = true;
            this.f331b.setOnItemClickListener(this.f329Z);
            if (this.f330a != null) {
                ListAdapter listAdapter = this.f330a;
                this.f330a = null;
                m366a(listAdapter);
            } else if (this.f334e != null) {
                m362a(false, false);
            }
            this.f338i.post(this.f328Y);
        }
    }

    private void m362a(boolean z, boolean z2) {
        m361D();
        if (this.f334e == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        } else if (this.f337h != z) {
            this.f337h = z;
            if (z) {
                if (z2) {
                    this.f334e.startAnimation(AnimationUtils.loadAnimation(m91h(), 17432577));
                    this.f335f.startAnimation(AnimationUtils.loadAnimation(m91h(), 17432576));
                } else {
                    this.f334e.clearAnimation();
                    this.f335f.clearAnimation();
                }
                this.f334e.setVisibility(8);
                this.f335f.setVisibility(0);
                return;
            }
            if (z2) {
                this.f334e.startAnimation(AnimationUtils.loadAnimation(m91h(), 17432576));
                this.f335f.startAnimation(AnimationUtils.loadAnimation(m91h(), 17432577));
            } else {
                this.f334e.clearAnimation();
                this.f335f.clearAnimation();
            }
            this.f334e.setVisibility(0);
            this.f335f.setVisibility(8);
        }
    }

    public View m363a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Context h = m91h();
        View frameLayout = new FrameLayout(h);
        View linearLayout = new LinearLayout(h);
        linearLayout.setId(16711682);
        linearLayout.setOrientation(1);
        linearLayout.setVisibility(8);
        linearLayout.setGravity(17);
        linearLayout.addView(new ProgressBar(h, null, 16842874), new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout, new LayoutParams(-1, -1));
        linearLayout = new FrameLayout(h);
        linearLayout.setId(16711683);
        View textView = new TextView(m91h());
        textView.setId(16711681);
        textView.setGravity(17);
        linearLayout.addView(textView, new LayoutParams(-1, -1));
        textView = new ListView(m91h());
        textView.setId(16908298);
        textView.setDrawSelectorOnTop(false);
        linearLayout.addView(textView, new LayoutParams(-1, -1));
        frameLayout.addView(linearLayout, new LayoutParams(-1, -1));
        frameLayout.setLayoutParams(new LayoutParams(-1, -1));
        return frameLayout;
    }

    public ListView m364a() {
        m361D();
        return this.f331b;
    }

    public void m365a(View view, Bundle bundle) {
        super.m66a(view, bundle);
        m361D();
    }

    public void m366a(ListAdapter listAdapter) {
        boolean z = false;
        boolean z2 = this.f330a != null;
        this.f330a = listAdapter;
        if (this.f331b != null) {
            this.f331b.setAdapter(listAdapter);
            if (!this.f337h && !z2) {
                if (m101o().getWindowToken() != null) {
                    z = true;
                }
                m362a(true, z);
            }
        }
    }

    public void m367a(ListView listView, View view, int i, long j) {
    }

    public ListAdapter m368b() {
        return this.f330a;
    }

    public void m369f() {
        this.f338i.removeCallbacks(this.f328Y);
        this.f331b = null;
        this.f337h = false;
        this.f335f = null;
        this.f334e = null;
        this.f332c = null;
        this.f333d = null;
        super.m87f();
    }
}
