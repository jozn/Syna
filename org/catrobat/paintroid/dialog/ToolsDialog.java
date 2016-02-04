package org.catrobat.paintroid.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import org.catrobat.paintroid.PaintMainActivity;
import org.catrobat.paintroid.PaintroidApplication;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.ui.button.ToolsAdapter;

/* renamed from: org.catrobat.paintroid.dialog.f */
public class ToolsDialog extends BaseDialog implements OnItemClickListener, OnItemLongClickListener {
    private static ToolsDialog f4487a;
    private ToolsAdapter f4488b;
    private PaintMainActivity f4489c;

    private ToolsDialog(Context context) {
        super(context);
        this.f4489c = (PaintMainActivity) context;
        this.f4488b = new ToolsAdapter(context, PaintroidApplication.f4196f);
    }

    public static ToolsDialog m5768a() {
        if (f4487a != null) {
            return f4487a;
        }
        throw new IllegalStateException("BrushPickerDialog has not been initialized. Call init() first!");
    }

    public static void m5769a(PaintMainActivity paintMainActivity) {
        f4487a = new ToolsDialog(paintMainActivity);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.tools_menu);
        setTitle(R.dialog_tools_title);
        setCanceledOnTouchOutside(true);
        GridView gridView = (GridView) findViewById(R.gridview_tools_menu);
        gridView.setAdapter(this.f4488b);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f4489c.m5434a(this.f4488b.m5793a(i));
        dismiss();
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        return true;
    }
}
