package org.catrobat.paintroid.p081c;

import java.util.EnumSet;
import org.catrobat.paintroid.R.R;
import org.catrobat.paintroid.p081c.Tool.Tool;

/* renamed from: org.catrobat.paintroid.c.c */
public enum ToolType {
    ELLIPSE(R.button_ellipse, R.icon_menu_ellipse, R.help_content_ellipse, true, EnumSet.of(Tool.ALL)),
    ZOOM(R.button_zoom, R.icon_menu_zoom, R.help_content_zoom, false, EnumSet.of(Tool.ALL)),
    PIPETTE(R.button_pipette, R.icon_menu_pipette, R.help_content_eyedropper, false, EnumSet.of(Tool.ALL)),
    BRUSH(R.button_brush, R.icon_menu_brush, R.help_content_brush, true, EnumSet.of(Tool.ALL)),
    UNDO(R.button_undo, R.icon_menu_undo, R.help_content_undo, false, EnumSet.of(Tool.ALL)),
    REDO(R.button_redo, R.icon_menu_redo, R.help_content_redo, false, EnumSet.of(Tool.ALL)),
    NONE(0, 0, 0, false, EnumSet.of(Tool.ALL)),
    FILL(R.button_fill, R.icon_menu_bucket, R.help_content_fill, true, EnumSet.of(Tool.ALL)),
    STAMP(R.button_stamp, R.icon_menu_stamp, R.help_content_stamp, false, EnumSet.of(Tool.ALL)),
    LINE(R.button_line, R.icon_menu_straight_line, R.help_content_line, true, EnumSet.of(Tool.ALL)),
    CURSOR(R.button_cursor, R.icon_menu_cursor, R.help_content_cursor, true, EnumSet.of(Tool.ALL)),
    IMPORTPNG(R.button_import_image, R.icon_menu_import_image, R.help_content_import_png, false, EnumSet.of(Tool.ALL)),
    CROP(R.button_crop, R.icon_menu_crop, R.help_content_crop, false, EnumSet.of(Tool.RESET_INTERNAL_STATE, Tool.NEW_IMAGE_LOADED)),
    ERASER(R.button_eraser, R.icon_menu_eraser, R.help_content_eraser, false, EnumSet.of(Tool.ALL)),
    FLIP(R.button_flip, R.icon_menu_flip_horizontal, R.help_content_flip, false, EnumSet.of(Tool.ALL)),
    RECT(R.button_rectangle, R.icon_menu_rectangle, R.help_content_rectangle, true, EnumSet.of(Tool.ALL)),
    MOVE(R.button_move, R.icon_menu_move, R.help_content_move, false, EnumSet.of(Tool.ALL)),
    ROTATE(R.button_rotate, R.icon_menu_rotate_left, R.help_content_rotate, false, EnumSet.of(Tool.ALL));
    
    private int f4427s;
    private int f4428t;
    private int f4429u;
    private boolean f4430v;
    private EnumSet<Tool> f4431w;

    private ToolType(int i, int i2, int i3, boolean z, EnumSet<Tool> enumSet) {
        this.f4427s = i;
        this.f4428t = i2;
        this.f4429u = i3;
        this.f4430v = z;
        this.f4431w = enumSet;
    }

    public int m5716a() {
        return this.f4427s;
    }

    public boolean m5717a(Tool tool) {
        return this.f4431w.contains(Tool.ALL) || this.f4431w.contains(tool);
    }

    public int m5718b() {
        return this.f4428t;
    }

    public boolean m5719c() {
        return this.f4430v;
    }
}
