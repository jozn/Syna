package android.support.v4.view;

import android.content.Context;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

/* renamed from: android.support.v4.view.v */
class PagerTitleStripIcs {

    /* renamed from: android.support.v4.view.v.a */
    private static class PagerTitleStripIcs extends SingleLineTransformationMethod {
        private Locale f566a;

        public PagerTitleStripIcs(Context context) {
            this.f566a = context.getResources().getConfiguration().locale;
        }

        public CharSequence getTransformation(CharSequence charSequence, View view) {
            CharSequence transformation = super.getTransformation(charSequence, view);
            return transformation != null ? transformation.toString().toUpperCase(this.f566a) : null;
        }
    }

    public static void m1079a(TextView textView) {
        textView.setTransformationMethod(new PagerTitleStripIcs(textView.getContext()));
    }
}
