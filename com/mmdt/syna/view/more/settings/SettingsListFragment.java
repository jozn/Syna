package com.mmdt.syna.view.more.settings;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/* renamed from: com.mmdt.syna.view.more.settings.a */
public class SettingsListFragment extends Fragment implements OnItemClickListener {
    private View f2679a;
    private ListView f2680b;
    private SettingsListFragment f2681c;
    private SettingsListFragment f2682d;

    /* renamed from: com.mmdt.syna.view.more.settings.a.a */
    public interface SettingsListFragment {
        void m3515a(int i);
    }

    /* renamed from: com.mmdt.syna.view.more.settings.a.b */
    public class SettingsListFragment extends ArrayAdapter<SettingsListItemDataHolder> {
        final /* synthetic */ SettingsListFragment f2676a;
        private final Activity f2677b;
        private final SettingsListItemDataHolder[] f2678c;

        /* renamed from: com.mmdt.syna.view.more.settings.a.b.a */
        class SettingsListFragment {
            public TextView f2673a;
            public ImageView f2674b;
            final /* synthetic */ SettingsListFragment f2675c;

            SettingsListFragment(SettingsListFragment settingsListFragment) {
                this.f2675c = settingsListFragment;
            }
        }

        public SettingsListFragment(SettingsListFragment settingsListFragment, Activity activity, SettingsListItemDataHolder[] settingsListItemDataHolderArr) {
            this.f2676a = settingsListFragment;
            super(activity, 2130903191, settingsListItemDataHolderArr);
            this.f2677b = activity;
            this.f2678c = settingsListItemDataHolderArr;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.f2677b.getLayoutInflater().inflate(2130903191, null);
                SettingsListFragment settingsListFragment = new SettingsListFragment(this);
                settingsListFragment.f2673a = (TextView) view.findViewById(2131427467);
                settingsListFragment.f2674b = (ImageView) view.findViewById(2131427533);
                view.setTag(settingsListFragment);
            }
            SettingsListFragment settingsListFragment2 = (SettingsListFragment) view.getTag();
            settingsListFragment2.f2673a.setText(this.f2678c[i].m3597b());
            settingsListFragment2.f2674b.setImageResource(this.f2678c[i].m3596a());
            return view;
        }
    }

    public SettingsListFragment(Activity activity, SettingsListItemDataHolder[] settingsListItemDataHolderArr) {
        this.f2681c = new SettingsListFragment(this, activity, settingsListItemDataHolderArr);
    }

    private void m3594a() {
        this.f2680b = (ListView) this.f2679a.findViewById(2131427808);
    }

    private ListView m3595b() {
        return this.f2680b;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f2680b.setAdapter(this.f2681c);
        m3595b().setOnItemClickListener(this);
        m3595b().setDividerHeight(1);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f2682d = (SettingsListFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnInteractionListener");
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f2679a = layoutInflater.inflate(2130903192, viewGroup, false);
        m3594a();
        return this.f2679a;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f2682d.m3515a(((SettingsListItemDataHolder) this.f2681c.getItem(i)).m3598c());
    }
}
