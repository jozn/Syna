package com.devspark.robototextview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Switch;
import com.devspark.robototextview.p013a.RobotoTextViewUtils;
import mobi.mmdt.ott.core.p051a.p053b.Farsi;
import mobi.mmdt.p041a.HasVersion;

@TargetApi(14)
public class RobotoSwitch extends Switch {
    public RobotoSwitch(Context context) {
        this(context, null);
    }

    public RobotoSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            RobotoTextViewUtils.m2214a(this, context, attributeSet);
        }
    }

    public RobotoSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            RobotoTextViewUtils.m2214a(this, context, attributeSet);
        }
    }

    protected void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (HasVersion.m4102e()) {
            charSequence = Farsi.m4404a(charSequence.toString());
        }
        super.onTextChanged(charSequence, i, i2, i3);
    }
}
