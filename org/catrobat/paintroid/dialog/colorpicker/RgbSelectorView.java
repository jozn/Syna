package org.catrobat.paintroid.dialog.colorpicker;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/* renamed from: org.catrobat.paintroid.dialog.colorpicker.g */
class RgbSelectorView implements OnSeekBarChangeListener {
    final /* synthetic */ RgbSelectorView f4478a;

    RgbSelectorView(RgbSelectorView rgbSelectorView) {
        this.f4478a = rgbSelectorView;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        this.f4478a.m5751c();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
