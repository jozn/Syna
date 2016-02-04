package android.support.v7.internal.view.menu;

import android.support.v4.view.ActionProvider.ActionProvider;
import android.support.v7.internal.view.menu.MenuItemWrapperICS.MenuItemWrapperICS;
import android.view.ActionProvider.VisibilityListener;
import android.view.MenuItem;
import android.view.View;

/* renamed from: android.support.v7.internal.view.menu.m */
class MenuItemWrapperJB extends MenuItemWrapperICS {

    /* renamed from: android.support.v7.internal.view.menu.m.a */
    class MenuItemWrapperJB extends MenuItemWrapperICS implements ActionProvider {
        VisibilityListener f1018c;
        final /* synthetic */ MenuItemWrapperJB f1019d;

        public MenuItemWrapperJB(MenuItemWrapperJB menuItemWrapperJB, android.support.v4.view.ActionProvider actionProvider) {
            this.f1019d = menuItemWrapperJB;
            super(menuItemWrapperJB, actionProvider);
        }

        public void m1835a(boolean z) {
            if (this.f1018c != null) {
                this.f1018c.onActionProviderVisibilityChanged(z);
            }
        }

        public boolean isVisible() {
            return this.a.m989d();
        }

        public View onCreateActionView(MenuItem menuItem) {
            return this.a.m982a(menuItem);
        }

        public boolean overridesItemVisibility() {
            return this.a.m988c();
        }

        public void refreshVisibility() {
            this.a.m990e();
        }

        public void setVisibilityListener(VisibilityListener visibilityListener) {
            ActionProvider actionProvider;
            this.f1018c = visibilityListener;
            android.support.v4.view.ActionProvider actionProvider2 = this.a;
            if (visibilityListener == null) {
                actionProvider = null;
            }
            actionProvider2.m984a(actionProvider);
        }
    }

    MenuItemWrapperJB(MenuItem menuItem) {
        super(menuItem, false);
    }

    MenuItemWrapperICS m1836b(android.support.v4.view.ActionProvider actionProvider) {
        return new MenuItemWrapperJB(this, actionProvider);
    }
}
