package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.p010a.R.R;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* renamed from: android.support.v7.widget.m */
class SuggestionsAdapter extends ResourceCursorAdapter implements OnClickListener {
    private SearchManager f1375j;
    private SearchView f1376k;
    private SearchableInfo f1377l;
    private Context f1378m;
    private WeakHashMap<String, ConstantState> f1379n;
    private boolean f1380o;
    private int f1381p;
    private ColorStateList f1382q;
    private int f1383r;
    private int f1384s;
    private int f1385t;
    private int f1386u;
    private int f1387v;
    private int f1388w;

    /* renamed from: android.support.v7.widget.m.a */
    private static final class SuggestionsAdapter {
        public final TextView f1370a;
        public final TextView f1371b;
        public final ImageView f1372c;
        public final ImageView f1373d;
        public final ImageView f1374e;

        public SuggestionsAdapter(View view) {
            this.f1370a = (TextView) view.findViewById(16908308);
            this.f1371b = (TextView) view.findViewById(16908309);
            this.f1372c = (ImageView) view.findViewById(16908295);
            this.f1373d = (ImageView) view.findViewById(16908296);
            this.f1374e = (ImageView) view.findViewById(R.edit_query);
        }
    }

    public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, ConstantState> weakHashMap) {
        super(context, R.abc_search_dropdown_item_icons_2line, null, true);
        this.f1380o = false;
        this.f1381p = 1;
        this.f1383r = -1;
        this.f1384s = -1;
        this.f1385t = -1;
        this.f1386u = -1;
        this.f1387v = -1;
        this.f1388w = -1;
        this.f1375j = (SearchManager) this.d.getSystemService("search");
        this.f1376k = searchView;
        this.f1377l = searchableInfo;
        this.f1378m = context;
        this.f1379n = weakHashMap;
    }

    private Drawable m2173a(ComponentName componentName) {
        Object obj = null;
        String flattenToShortString = componentName.flattenToShortString();
        if (this.f1379n.containsKey(flattenToShortString)) {
            ConstantState constantState = (ConstantState) this.f1379n.get(flattenToShortString);
            return constantState == null ? null : constantState.newDrawable(this.f1378m.getResources());
        } else {
            Drawable b = m2180b(componentName);
            if (b != null) {
                obj = b.getConstantState();
            }
            this.f1379n.put(flattenToShortString, obj);
            return b;
        }
    }

    private Drawable m2174a(String str) {
        if (str == null || str.length() == 0 || "0".equals(str)) {
            return null;
        }
        Drawable b;
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f1378m.getPackageName() + "/" + parseInt;
            b = m2182b(str2);
            if (b != null) {
                return b;
            }
            b = this.f1378m.getResources().getDrawable(parseInt);
            m2179a(str2, b);
            return b;
        } catch (NumberFormatException e) {
            b = m2182b(str);
            if (b != null) {
                return b;
            }
            b = m2181b(Uri.parse(str));
            m2179a(str, b);
            return b;
        } catch (NotFoundException e2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        }
    }

    private static String m2175a(Cursor cursor, int i) {
        String str = null;
        if (i != -1) {
            try {
                str = cursor.getString(i);
            } catch (Throwable e) {
                Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            }
        }
        return str;
    }

    public static String m2176a(Cursor cursor, String str) {
        return SuggestionsAdapter.m2175a(cursor, cursor.getColumnIndex(str));
    }

    private void m2177a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void m2178a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private void m2179a(String str, Drawable drawable) {
        if (drawable != null) {
            this.f1379n.put(str, drawable.getConstantState());
        }
    }

    private Drawable m2180b(ComponentName componentName) {
        PackageManager packageManager = this.d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (NameNotFoundException e) {
            Log.w("SuggestionsAdapter", e.toString());
            return null;
        }
    }

    private Drawable m2181b(Uri uri) {
        InputStream openInputStream;
        try {
            if ("android.resource".equals(uri.getScheme())) {
                return m2190a(uri);
            }
            openInputStream = this.f1378m.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
            try {
                openInputStream.close();
                return createFromStream;
            } catch (Throwable e) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e);
                return createFromStream;
            }
        } catch (NotFoundException e2) {
            throw new FileNotFoundException("Resource does not exist: " + uri);
        } catch (FileNotFoundException e3) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
            return null;
        } catch (Throwable th) {
            try {
                openInputStream.close();
            } catch (Throwable e4) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e4);
            }
        }
    }

    private Drawable m2182b(String str) {
        ConstantState constantState = (ConstantState) this.f1379n.get(str);
        return constantState == null ? null : constantState.newDrawable();
    }

    private CharSequence m2183b(CharSequence charSequence) {
        if (this.f1382q == null) {
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(R.textColorSearchUrl, typedValue, true);
            this.f1382q = this.d.getResources().getColorStateList(typedValue.resourceId);
        }
        CharSequence spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f1382q, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private void m2184d(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null && !extras.getBoolean("in_progress")) {
        }
    }

    private Drawable m2185e(Cursor cursor) {
        if (this.f1386u == -1) {
            return null;
        }
        Drawable a = m2174a(cursor.getString(this.f1386u));
        return a == null ? m2187g(cursor) : a;
    }

    private Drawable m2186f(Cursor cursor) {
        return this.f1387v == -1 ? null : m2174a(cursor.getString(this.f1387v));
    }

    private Drawable m2187g(Cursor cursor) {
        Drawable a = m2173a(this.f1377l.getSearchActivity());
        return a != null ? a : this.d.getPackageManager().getDefaultActivityIcon();
    }

    Cursor m2188a(SearchableInfo searchableInfo, String str, int i) {
        if (searchableInfo == null) {
            return null;
        }
        String suggestAuthority = searchableInfo.getSuggestAuthority();
        if (suggestAuthority == null) {
            return null;
        }
        String[] strArr;
        Builder fragment = new Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
            strArr = null;
        }
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
    }

    public Cursor m2189a(CharSequence charSequence) {
        String obj = charSequence == null ? "" : charSequence.toString();
        if (this.f1376k.getVisibility() != 0 || this.f1376k.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor a = m2188a(this.f1377l, obj, 50);
            if (a != null) {
                a.getCount();
                return a;
            }
        } catch (Throwable e) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
        }
        return null;
    }

    Drawable m2190a(Uri uri) throws FileNotFoundException {
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.d.getPackageManager().getResourcesForApplication(authority);
            List pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    size = Integer.parseInt((String) pathSegments.get(0));
                } catch (NumberFormatException e) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else if (size == 2) {
                size = resourcesForApplication.getIdentifier((String) pathSegments.get(1), (String) pathSegments.get(0), authority);
            } else {
                throw new FileNotFoundException("More than two path segments: " + uri);
            }
            if (size != 0) {
                return resourcesForApplication.getDrawable(size);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (NameNotFoundException e2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    public View m2191a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.m1351a(context, cursor, viewGroup);
        a.setTag(new SuggestionsAdapter(a));
        return a;
    }

    public void m2192a(int i) {
        this.f1381p = i;
    }

    public void m2193a(Cursor cursor) {
        if (this.f1380o) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.m1309a(cursor);
            if (cursor != null) {
                this.f1383r = cursor.getColumnIndex("suggest_text_1");
                this.f1384s = cursor.getColumnIndex("suggest_text_2");
                this.f1385t = cursor.getColumnIndex("suggest_text_2_url");
                this.f1386u = cursor.getColumnIndex("suggest_icon_1");
                this.f1387v = cursor.getColumnIndex("suggest_icon_2");
                this.f1388w = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Throwable e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    public void m2194a(View view, Context context, Cursor cursor) {
        SuggestionsAdapter suggestionsAdapter = (SuggestionsAdapter) view.getTag();
        int i = this.f1388w != -1 ? cursor.getInt(this.f1388w) : 0;
        if (suggestionsAdapter.f1370a != null) {
            m2178a(suggestionsAdapter.f1370a, SuggestionsAdapter.m2175a(cursor, this.f1383r));
        }
        if (suggestionsAdapter.f1371b != null) {
            CharSequence a = SuggestionsAdapter.m2175a(cursor, this.f1385t);
            a = a != null ? m2183b(a) : SuggestionsAdapter.m2175a(cursor, this.f1384s);
            if (TextUtils.isEmpty(a)) {
                if (suggestionsAdapter.f1370a != null) {
                    suggestionsAdapter.f1370a.setSingleLine(false);
                    suggestionsAdapter.f1370a.setMaxLines(2);
                }
            } else if (suggestionsAdapter.f1370a != null) {
                suggestionsAdapter.f1370a.setSingleLine(true);
                suggestionsAdapter.f1370a.setMaxLines(1);
            }
            m2178a(suggestionsAdapter.f1371b, a);
        }
        if (suggestionsAdapter.f1372c != null) {
            m2177a(suggestionsAdapter.f1372c, m2185e(cursor), 4);
        }
        if (suggestionsAdapter.f1373d != null) {
            m2177a(suggestionsAdapter.f1373d, m2186f(cursor), 8);
        }
        if (this.f1381p == 2 || (this.f1381p == 1 && (i & 1) != 0)) {
            suggestionsAdapter.f1374e.setVisibility(0);
            suggestionsAdapter.f1374e.setTag(suggestionsAdapter.f1370a.getText());
            suggestionsAdapter.f1374e.setOnClickListener(this);
            return;
        }
        suggestionsAdapter.f1374e.setVisibility(8);
    }

    public CharSequence m2195c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String a = SuggestionsAdapter.m2176a(cursor, "suggest_intent_query");
        if (a != null) {
            return a;
        }
        if (this.f1377l.shouldRewriteQueryFromData()) {
            a = SuggestionsAdapter.m2176a(cursor, "suggest_intent_data");
            if (a != null) {
                return a;
            }
        }
        if (!this.f1377l.shouldRewriteQueryFromText()) {
            return null;
        }
        a = SuggestionsAdapter.m2176a(cursor, "suggest_text_1");
        return a != null ? a : null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (Throwable e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View a = m2191a(this.d, this.c, viewGroup);
            if (a != null) {
                ((SuggestionsAdapter) a.getTag()).f1370a.setText(e.toString());
            }
            return a;
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        m2184d(m1305a());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        m2184d(m1305a());
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f1376k.m2169a((CharSequence) tag);
        }
    }
}
