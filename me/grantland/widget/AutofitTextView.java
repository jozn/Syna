package me.grantland.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.devspark.robototextview.widget.RobotoTextView;
import me.grantland.widget.AutofitHelper.AutofitHelper;

public class AutofitTextView extends RobotoTextView implements AutofitHelper {
    private AutofitHelper f3215a;

    public AutofitTextView(Context context) {
        super(context);
        m4057a(context, null, 0);
    }

    public AutofitTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m4057a(context, attributeSet, 0);
    }

    public AutofitTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m4057a(context, attributeSet, i);
    }

    private void m4057a(Context context, AttributeSet attributeSet, int i) {
        this.f3215a = AutofitHelper.m4062a(this, attributeSet, i).m4073a((AutofitHelper) this);
    }

    public void m4058a(float f, float f2) {
    }

    public void setLines(int i) {
        super.setLines(i);
        if (this.f3215a != null) {
            this.f3215a.m4071a(i);
        }
    }

    public void setMaxLines(int i) {
        super.setMaxLines(i);
        if (this.f3215a != null) {
            this.f3215a.m4071a(i);
        }
    }

    public void setMaxTextSize(float f) {
        this.f3215a.m4078c(f);
    }

    public void setMaxTextSize(int i, float f) {
        this.f3215a.m4077b(i, f);
    }

    public void setMinTextSize(int i) {
        this.f3215a.m4072a(2, (float) i);
    }

    public void setMinTextSize(int i, float f) {
        this.f3215a.m4072a(i, f);
    }

    public void setPrecision(float f) {
        this.f3215a.m4070a(f);
    }

    public void setSizeToFit() {
        setSizeToFit(true);
    }

    public void setSizeToFit(boolean z) {
        this.f3215a.m4074a(z);
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        if (this.f3215a != null) {
            this.f3215a.m4080c(i, f);
        }
    }
}
