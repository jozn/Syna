package org.catrobat.paintroid.ui.button;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p081c.ToolType;

/* renamed from: org.catrobat.paintroid.ui.button.a */
public class ToolsAdapter extends BaseAdapter {
    private Context f4529a;
    private ArrayList<ToolType> f4530b;

    public ToolsAdapter(Context context, boolean z) {
        this.f4529a = context;
        m5792a(z);
    }

    private void m5792a(boolean z) {
        this.f4530b = new ArrayList();
        this.f4530b.add(ToolType.BRUSH);
        this.f4530b.add(ToolType.CURSOR);
        this.f4530b.add(ToolType.PIPETTE);
        this.f4530b.add(ToolType.FILL);
        this.f4530b.add(ToolType.STAMP);
        this.f4530b.add(ToolType.RECT);
        this.f4530b.add(ToolType.ELLIPSE);
        this.f4530b.add(ToolType.IMPORTPNG);
        this.f4530b.add(ToolType.CROP);
        this.f4530b.add(ToolType.ERASER);
        this.f4530b.add(ToolType.FLIP);
        this.f4530b.add(ToolType.MOVE);
        this.f4530b.add(ToolType.ZOOM);
        this.f4530b.add(ToolType.ROTATE);
        this.f4530b.add(ToolType.LINE);
    }

    public ToolType m5793a(int i) {
        return (ToolType) this.f4530b.get(i);
    }

    public int getCount() {
        return this.f4530b.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view != null) {
            return view;
        }
        view = ((Activity) this.f4529a).getLayoutInflater().inflate(R.tool_button, null);
        ((ImageView) view.findViewById(R.tool_button_image)).setImageResource(((ToolType) this.f4530b.get(i)).m5718b());
        ((TextView) view.findViewById(R.tool_button_text)).setText(((ToolType) this.f4530b.get(i)).m5716a());
        return view;
    }
}
