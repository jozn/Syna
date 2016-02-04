package android.support.v7.internal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.v4.p005a.p006a.SupportMenu;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuItemWrapperICS;
import android.support.v7.p010a.R.R;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Method;
import org.jivesoftware.smackx.bytestreams.ibb.InBandBytestreamManager;

/* renamed from: android.support.v7.internal.view.d */
public class SupportMenuInflater extends MenuInflater {
    private static final Class<?>[] f827a;
    private static final Class<?>[] f828b;
    private final Object[] f829c;
    private final Object[] f830d;
    private Context f831e;
    private Object f832f;

    /* renamed from: android.support.v7.internal.view.d.a */
    private static class SupportMenuInflater implements OnMenuItemClickListener {
        private static final Class<?>[] f798a;
        private Object f799b;
        private Method f800c;

        static {
            f798a = new Class[]{MenuItem.class};
        }

        public SupportMenuInflater(Object obj, String str) {
            this.f799b = obj;
            Class cls = obj.getClass();
            try {
                this.f800c = cls.getMethod(str, f798a);
            } catch (Throwable e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f800c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f800c.invoke(this.f799b, new Object[]{menuItem})).booleanValue();
                }
                this.f800c.invoke(this.f799b, new Object[]{menuItem});
                return true;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* renamed from: android.support.v7.internal.view.d.b */
    private class SupportMenuInflater {
        final /* synthetic */ SupportMenuInflater f801a;
        private Menu f802b;
        private int f803c;
        private int f804d;
        private int f805e;
        private int f806f;
        private boolean f807g;
        private boolean f808h;
        private boolean f809i;
        private int f810j;
        private int f811k;
        private CharSequence f812l;
        private CharSequence f813m;
        private int f814n;
        private char f815o;
        private char f816p;
        private int f817q;
        private boolean f818r;
        private boolean f819s;
        private boolean f820t;
        private int f821u;
        private int f822v;
        private String f823w;
        private String f824x;
        private String f825y;
        private ActionProvider f826z;

        public SupportMenuInflater(SupportMenuInflater supportMenuInflater, Menu menu) {
            this.f801a = supportMenuInflater;
            this.f802b = menu;
            m1602a();
        }

        private char m1598a(String str) {
            return str == null ? '\u0000' : str.charAt(0);
        }

        private <T> T m1600a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                return this.f801a.f831e.getClassLoader().loadClass(str).getConstructor(clsArr).newInstance(objArr);
            } catch (Throwable e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e);
                return null;
            }
        }

        private void m1601a(MenuItem menuItem) {
            boolean z = true;
            menuItem.setChecked(this.f818r).setVisible(this.f819s).setEnabled(this.f820t).setCheckable(this.f817q >= 1).setTitleCondensed(this.f813m).setIcon(this.f814n).setAlphabeticShortcut(this.f815o).setNumericShortcut(this.f816p);
            if (this.f821u >= 0) {
                MenuItemCompat.m1042a(menuItem, this.f821u);
            }
            if (this.f825y != null) {
                if (this.f801a.f831e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new SupportMenuInflater(this.f801a.f832f, this.f825y));
            }
            if (menuItem instanceof MenuItemImpl) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
            }
            if (this.f817q >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl) menuItem).m1804a(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).m1829a(true);
                }
            }
            if (this.f823w != null) {
                MenuItemCompat.m1040a(menuItem, (View) m1600a(this.f823w, SupportMenuInflater.f827a, this.f801a.f829c));
            } else {
                z = false;
            }
            if (this.f822v > 0) {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    MenuItemCompat.m1043b(menuItem, this.f822v);
                }
            }
            if (this.f826z != null) {
                MenuItemCompat.m1039a(menuItem, this.f826z);
            }
        }

        public void m1602a() {
            this.f803c = 0;
            this.f804d = 0;
            this.f805e = 0;
            this.f806f = 0;
            this.f807g = true;
            this.f808h = true;
        }

        public void m1603a(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = this.f801a.f831e.obtainStyledAttributes(attributeSet, R.MenuGroup);
            this.f803c = obtainStyledAttributes.getResourceId(1, 0);
            this.f804d = obtainStyledAttributes.getInt(3, 0);
            this.f805e = obtainStyledAttributes.getInt(4, 0);
            this.f806f = obtainStyledAttributes.getInt(5, 0);
            this.f807g = obtainStyledAttributes.getBoolean(2, true);
            this.f808h = obtainStyledAttributes.getBoolean(0, true);
            obtainStyledAttributes.recycle();
        }

        public void m1604b() {
            this.f809i = true;
            m1601a(this.f802b.add(this.f803c, this.f810j, this.f811k, this.f812l));
        }

        public void m1605b(AttributeSet attributeSet) {
            int i = 1;
            TypedArray obtainStyledAttributes = this.f801a.f831e.obtainStyledAttributes(attributeSet, R.MenuItem);
            this.f810j = obtainStyledAttributes.getResourceId(2, 0);
            this.f811k = (obtainStyledAttributes.getInt(5, this.f804d) & -65536) | (obtainStyledAttributes.getInt(6, this.f805e) & InBandBytestreamManager.MAXIMUM_BLOCK_SIZE);
            this.f812l = obtainStyledAttributes.getText(7);
            this.f813m = obtainStyledAttributes.getText(8);
            this.f814n = obtainStyledAttributes.getResourceId(0, 0);
            this.f815o = m1598a(obtainStyledAttributes.getString(9));
            this.f816p = m1598a(obtainStyledAttributes.getString(10));
            if (obtainStyledAttributes.hasValue(11)) {
                this.f817q = obtainStyledAttributes.getBoolean(11, false) ? 1 : 0;
            } else {
                this.f817q = this.f806f;
            }
            this.f818r = obtainStyledAttributes.getBoolean(3, false);
            this.f819s = obtainStyledAttributes.getBoolean(4, this.f807g);
            this.f820t = obtainStyledAttributes.getBoolean(1, this.f808h);
            this.f821u = obtainStyledAttributes.getInt(13, -1);
            this.f825y = obtainStyledAttributes.getString(12);
            this.f822v = obtainStyledAttributes.getResourceId(14, 0);
            this.f823w = obtainStyledAttributes.getString(15);
            this.f824x = obtainStyledAttributes.getString(16);
            if (this.f824x == null) {
                i = 0;
            }
            if (i != 0 && this.f822v == 0 && this.f823w == null) {
                this.f826z = (ActionProvider) m1600a(this.f824x, SupportMenuInflater.f828b, this.f801a.f830d);
            } else {
                if (i != 0) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f826z = null;
            }
            obtainStyledAttributes.recycle();
            this.f809i = false;
        }

        public SubMenu m1606c() {
            this.f809i = true;
            SubMenu addSubMenu = this.f802b.addSubMenu(this.f803c, this.f810j, this.f811k, this.f812l);
            m1601a(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean m1607d() {
            return this.f809i;
        }
    }

    static {
        f827a = new Class[]{Context.class};
        f828b = f827a;
    }

    public SupportMenuInflater(Context context) {
        super(context);
        this.f831e = context;
        this.f832f = context;
        this.f829c = new Object[]{context};
        this.f830d = this.f829c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1609a(org.xmlpull.v1.XmlPullParser r11, android.util.AttributeSet r12, android.view.Menu r13) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        r10 = this;
        r4 = 0;
        r1 = 1;
        r6 = 0;
        r7 = new android.support.v7.internal.view.d$b;
        r7.<init>(r10, r13);
        r0 = r11.getEventType();
    L_0x000c:
        r2 = 2;
        if (r0 != r2) goto L_0x004a;
    L_0x000f:
        r0 = r11.getName();
        r2 = "menu";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0031;
    L_0x001b:
        r0 = r11.next();
    L_0x001f:
        r2 = r4;
        r5 = r6;
        r3 = r0;
        r0 = r6;
    L_0x0023:
        if (r0 != 0) goto L_0x00e1;
    L_0x0025:
        switch(r3) {
            case 1: goto L_0x00d9;
            case 2: goto L_0x0051;
            case 3: goto L_0x0087;
            default: goto L_0x0028;
        };
    L_0x0028:
        r3 = r5;
    L_0x0029:
        r5 = r11.next();
        r9 = r3;
        r3 = r5;
        r5 = r9;
        goto L_0x0023;
    L_0x0031:
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Expecting menu, got ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
    L_0x004a:
        r0 = r11.next();
        if (r0 != r1) goto L_0x000c;
    L_0x0050:
        goto L_0x001f;
    L_0x0051:
        if (r5 == 0) goto L_0x0055;
    L_0x0053:
        r3 = r5;
        goto L_0x0029;
    L_0x0055:
        r3 = r11.getName();
        r8 = "group";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0066;
    L_0x0061:
        r7.m1603a(r12);
        r3 = r5;
        goto L_0x0029;
    L_0x0066:
        r8 = "item";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0073;
    L_0x006e:
        r7.m1605b(r12);
        r3 = r5;
        goto L_0x0029;
    L_0x0073:
        r8 = "menu";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0084;
    L_0x007b:
        r3 = r7.m1606c();
        r10.m1609a(r11, r12, r3);
        r3 = r5;
        goto L_0x0029;
    L_0x0084:
        r2 = r3;
        r3 = r1;
        goto L_0x0029;
    L_0x0087:
        r3 = r11.getName();
        if (r5 == 0) goto L_0x0096;
    L_0x008d:
        r8 = r3.equals(r2);
        if (r8 == 0) goto L_0x0096;
    L_0x0093:
        r2 = r4;
        r3 = r6;
        goto L_0x0029;
    L_0x0096:
        r8 = "group";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x00a3;
    L_0x009e:
        r7.m1602a();
        r3 = r5;
        goto L_0x0029;
    L_0x00a3:
        r8 = "item";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x00cd;
    L_0x00ab:
        r3 = r7.m1607d();
        if (r3 != 0) goto L_0x0028;
    L_0x00b1:
        r3 = r7.f826z;
        if (r3 == 0) goto L_0x00c7;
    L_0x00b7:
        r3 = r7.f826z;
        r3 = r3.m992g();
        if (r3 == 0) goto L_0x00c7;
    L_0x00c1:
        r7.m1606c();
        r3 = r5;
        goto L_0x0029;
    L_0x00c7:
        r7.m1604b();
        r3 = r5;
        goto L_0x0029;
    L_0x00cd:
        r8 = "menu";
        r3 = r3.equals(r8);
        if (r3 == 0) goto L_0x0028;
    L_0x00d5:
        r0 = r1;
        r3 = r5;
        goto L_0x0029;
    L_0x00d9:
        r0 = new java.lang.RuntimeException;
        r1 = "Unexpected end of document";
        r0.<init>(r1);
        throw r0;
    L_0x00e1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.internal.view.d.a(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    public void inflate(int i, Menu menu) {
        if (menu instanceof SupportMenu) {
            XmlResourceParser xmlResourceParser = null;
            try {
                xmlResourceParser = this.f831e.getResources().getLayout(i);
                m1609a(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            } catch (Throwable e) {
                throw new InflateException("Error inflating menu XML", e);
            } catch (Throwable e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (Throwable th) {
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            }
        } else {
            super.inflate(i, menu);
        }
    }
}
