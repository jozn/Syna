package com.mmdt.syna.view.more.settings.nightmode;

import android.app.TimePickerDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;
import com.viewpagerindicator.R.R;
import java.lang.reflect.Field;
import java.util.Calendar;

/* renamed from: com.mmdt.syna.view.more.settings.nightmode.e */
class NightModeFragment implements OnClickListener {
    final /* synthetic */ NightModeFragment f2707a;

    NightModeFragment(NightModeFragment nightModeFragment) {
        this.f2707a = nightModeFragment;
    }

    public void onClick(View view) {
        Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this.f2707a.getActivity(), new NightModeFragment(this), this.f2707a.f2702o, this.f2707a.f2703p, true);
        timePickerDialog.setTitle(this.f2707a.getString(2131493716));
        timePickerDialog.show();
        timePickerDialog.findViewById(timePickerDialog.getContext().getResources().getIdentifier("android:id/titleDivider", null, null)).setBackgroundColor(this.f2707a.getResources().getColor(R.my_yellow));
        ((TextView) timePickerDialog.findViewById(timePickerDialog.getContext().getResources().getIdentifier("android:id/alertTitle", null, null))).setTextColor(this.f2707a.getResources().getColor(R.my_yellow));
        try {
            Field declaredField = TimePickerDialog.class.getDeclaredField("mTimePicker");
            declaredField.setAccessible(true);
            this.f2707a.m3601a((TimePicker) declaredField.get(timePickerDialog));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }
}
