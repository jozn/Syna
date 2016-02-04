package com.felipecsl.asymmetricgridview.library.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

@TargetApi(11)
public class NineLinearLayout extends LinearLayout {
    private final AnimatorProxy f1535a;

    public NineLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1535a = AnimatorProxy.f1552a ? AnimatorProxy.m2337a((View) this) : null;
    }

    public float getAlpha() {
        return AnimatorProxy.f1552a ? this.f1535a.m2342a() : super.getAlpha();
    }

    public float getTranslationX() {
        return AnimatorProxy.f1552a ? this.f1535a.m2344b() : super.getTranslationX();
    }

    public void setAlpha(float f) {
        if (AnimatorProxy.f1552a) {
            this.f1535a.m2343a(f);
        } else {
            super.setAlpha(f);
        }
    }

    public void setTranslationX(float f) {
        if (AnimatorProxy.f1552a) {
            this.f1535a.m2345b(f);
        } else {
            super.setTranslationX(f);
        }
    }

    public void setVisibility(int i) {
        if (this.f1535a != null) {
            if (i == 8) {
                clearAnimation();
            } else if (i == 0) {
                setAnimation(this.f1535a);
            }
        }
        super.setVisibility(i);
    }
}
