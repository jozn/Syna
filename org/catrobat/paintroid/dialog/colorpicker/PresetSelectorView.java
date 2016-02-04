package org.catrobat.paintroid.dialog.colorpicker;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: org.catrobat.paintroid.dialog.colorpicker.f */
class PresetSelectorView implements OnClickListener {
    final /* synthetic */ PresetSelectorView f4477a;

    PresetSelectorView(PresetSelectorView presetSelectorView) {
        this.f4477a = presetSelectorView;
    }

    public void onClick(View view) {
        this.f4477a.f4452b = this.f4477a.f4453c.getColor(view.getId(), 0);
        this.f4477a.m5745b();
    }
}
