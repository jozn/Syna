package me.grantland.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.devspark.robototextview.widget.RobotoEditText;
import me.grantland.widget.AutofitHelper.AutofitHelper;

public class AutofitEditText extends RobotoEditText implements AutofitHelper {
    private AutofitHelper f3210a;

    public AutofitEditText(Context context) {
        super(context);
        m4053a(context, null, 0);
    }

    public AutofitEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m4053a(context, attributeSet, 0);
    }

    public AutofitEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m4053a(context, attributeSet, i);
    }

    private void m4053a(Context context, AttributeSet attributeSet, int i) {
        this.f3210a = AutofitHelper.m4062a(this, attributeSet, i).m4073a((AutofitHelper) this);
    }

    public void m4054a() {
        if (this.f3210a != null) {
            this.f3210a.m4079c();
        }
    }

    public void m4055a(float f, float f2) {
    }

    public void setLines(int i) {
        super.setLines(i);
        if (this.f3210a != null) {
            this.f3210a.m4071a(i);
        }
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        if (this.f3210a != null) {
            this.f3210a.m4071a(i);
        }
    }

    public void setMaxTextSize(float f) {
        this.f3210a.m4078c(f);
    }

    public void setMaxTextSize(int i, float f) {
        this.f3210a.m4077b(i, f);
    }

    public void setMinTextSize(int i) {
        this.f3210a.m4072a(2, (float) i);
    }

    public void setMinTextSize(int i, float f) {
        this.f3210a.m4072a(i, f);
    }

    public void setPrecision(float f) {
        this.f3210a.m4070a(f);
    }

    public void setSizeToFit() {
        setSizeToFit(true);
    }

    public void setSizeToFit(boolean z) {
        this.f3210a.m4074a(z);
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        if (this.f3210a != null) {
            this.f3210a.m4080c(i, f);
        }
    }
}
