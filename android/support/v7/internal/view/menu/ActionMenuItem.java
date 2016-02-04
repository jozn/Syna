package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.p005a.p006a.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.internal.view.menu.a */
public class ActionMenuItem implements SupportMenuItem {
    private final int f930a;
    private final int f931b;
    private final int f932c;
    private final int f933d;
    private CharSequence f934e;
    private CharSequence f935f;
    private Intent f936g;
    private char f937h;
    private char f938i;
    private Drawable f939j;
    private int f940k;
    private Context f941l;
    private OnMenuItemClickListener f942m;
    private int f943n;

    public ActionMenuItem(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.f940k = 0;
        this.f943n = 16;
        this.f941l = context;
        this.f930a = i2;
        this.f931b = i;
        this.f932c = i3;
        this.f933d = i4;
        this.f934e = charSequence;
    }

    public SupportMenuItem m1725a(int i) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem m1726a(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem m1727a(View view) {
        throw new UnsupportedOperationException();
    }

    public SupportMenuItem m1728b(int i) {
        setShowAsAction(i);
        return this;
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public char getAlphabeticShortcut() {
        return this.f938i;
    }

    public int getGroupId() {
        return this.f931b;
    }

    public Drawable getIcon() {
        return this.f939j;
    }

    public Intent getIntent() {
        return this.f936g;
    }

    public int getItemId() {
        return this.f930a;
    }

    public ContextMenuInfo getMenuInfo() {
        return null;
    }

    public char getNumericShortcut() {
        return this.f937h;
    }

    public int getOrder() {
        return this.f933d;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public CharSequence getTitle() {
        return this.f934e;
    }

    public CharSequence getTitleCondensed() {
        return this.f935f;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        return (this.f943n & 1) != 0;
    }

    public boolean isChecked() {
        return (this.f943n & 2) != 0;
    }

    public boolean isEnabled() {
        return (this.f943n & 16) != 0;
    }

    public boolean isVisible() {
        return (this.f943n & 8) == 0;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public /* synthetic */ MenuItem setActionView(int i) {
        return m1725a(i);
    }

    public /* synthetic */ MenuItem setActionView(View view) {
        return m1727a(view);
    }

    public MenuItem setAlphabeticShortcut(char c) {
        this.f938i = c;
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.f943n = (z ? 1 : 0) | (this.f943n & -2);
        return this;
    }

    public MenuItem setChecked(boolean z) {
        this.f943n = (z ? 2 : 0) | (this.f943n & -3);
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        this.f943n = (z ? 16 : 0) | (this.f943n & -17);
        return this;
    }

    public MenuItem setIcon(int i) {
        this.f940k = i;
        this.f939j = this.f941l.getResources().getDrawable(i);
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.f939j = drawable;
        this.f940k = 0;
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.f936g = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        this.f937h = c;
        return this;
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.f942m = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c, char c2) {
        this.f937h = c;
        this.f938i = c2;
        return this;
    }

    public void setShowAsAction(int i) {
    }

    public /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        return m1728b(i);
    }

    public MenuItem setTitle(int i) {
        this.f934e = this.f941l.getResources().getString(i);
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.f934e = charSequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f935f = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        this.f943n = (z ? 0 : 8) | (this.f943n & 8);
        return this;
    }
}
