package com.devspark.robototextview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.devspark.robototextview.p013a.RobotoTextViewUtils;
import mobi.mmdt.ott.core.p051a.p053b.Farsi;
import mobi.mmdt.p041a.HasVersion;

public class RobotoCheckBox extends CheckBox {
    public RobotoCheckBox(Context context) {
        this(context, null);
    }

    public RobotoCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            RobotoTextViewUtils.m2214a(this, context, attributeSet);
        }
    }

    public RobotoCheckBox(Context context, AttributeSet attributeSet, int i) {
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
