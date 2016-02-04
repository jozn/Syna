package com.mmdt.syna.view.conversation.conversationpage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.devspark.robototextview.p013a.RobotoTextViewUtils;
import com.devspark.robototextview.p013a.RobotoTypefaceManager;
import com.devspark.robototextview.widget.RobotoTextView;
import com.mmdt.syna.view.components.pulltorefresh.PullToRefreshListView;
import com.mmdt.syna.view.more.settings.profile.ad;
import com.mmdt.syna.view.stickermarket.StickerDetailsActivity;
import com.mmdt.syna.view.tools.DpPixelConvertor;
import com.mmdt.syna.view.tools.TimeUtils;
import com.mmdt.syna.view.tools.p020a.p021a.ImageLoader;
import com.mmdt.syna.view.tools.p020a.p035b.StickerImageLoader;
import com.mmdt.syna.view.tools.p020a.p036c.TextLoader;
import com.mmdt.syna.view.tools.p037b.EmoticonManager;
import com.mmdt.syna.view.tools.p038c.StickerManager;
import com.squareup.picasso.Picasso;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import mobi.mmdt.ott.core.model.database.ContactShow;
import mobi.mmdt.ott.core.model.database.p061b.SynaContacts;
import mobi.mmdt.ott.core.model.database.p065f.Messages;
import mobi.mmdt.ott.core.model.database.p065f.TextMessageDataHolder;
import mobi.mmdt.ott.core.model.database.p067g.StickerPackages;
import mobi.mmdt.ott.core.model.p058a.AppSettings;
import mobi.mmdt.p041a.DateAndTime;
import mobi.mmdt.p041a.HasVersion;
import org.catrobat.paintroid.R.R;
import org.jivesoftware.smack.sm.packet.StreamManagement.AckRequest;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.search.UserSearch;
import org.linphone.C0282R;
import org.linphone.core.VideoSize;
import org.linphone.mediastream.Version;

@SuppressLint({"ValidFragment"})
/* renamed from: com.mmdt.syna.view.conversation.conversationpage.b */
public class ConversationThreadFragment extends ListFragment implements LoaderManager<Cursor>, OnItemClickListener {
    private ImageLoader f2189Y;
    private StickerImageLoader f2190Z;
    private ConversationThreadFragment aa;
    private ImageLoader ab;
    private String ac;
    private ContactShow ad;
    private ConversationThreadFragment ae;
    private boolean af;
    private View ag;
    private int ah;
    private String ai;
    private ControlTransmitListener aj;
    private int ak;
    private boolean al;
    private PullToRefreshListView am;
    private ConversationThreadFragment an;
    private ad ao;
    private String ap;
    private ImageLoader f2191i;

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.d */
    public interface ConversationThreadFragment {
        void m2966a(int i);

        void m2967a(int i, String str, String str2);

        void m2968a(String str, String str2);

        void a_(String str);

        void m2969b();

        void m2970b(String str);
    }

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.a */
    private class ConversationThreadFragment extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ ConversationThreadFragment f2127a;

        private ConversationThreadFragment(ConversationThreadFragment conversationThreadFragment) {
            this.f2127a = conversationThreadFragment;
        }

        protected Void m3109a(Void... voidArr) {
            Thread.currentThread().setPriority(5);
            if (!isCancelled()) {
                try {
                    ArrayList b = Messages.m5108b(this.f2127a.m91h(), this.f2127a.ai, this.f2127a.ak);
                    if (b.size() == 0) {
                        this.f2127a.m91h().runOnUiThread(new ConversationThreadFragment(this));
                    } else {
                        Iterator it = b.iterator();
                        while (it.hasNext()) {
                            TextMessageDataHolder textMessageDataHolder = (TextMessageDataHolder) it.next();
                            TextLoader.m4005a(this.f2127a.m91h()).m4015a(textMessageDataHolder.m5124b() + "_" + textMessageDataHolder.m5125c(), EmoticonManager.m4022a(this.f2127a.m91h()).m4024a(this.f2127a.m91h(), new String(textMessageDataHolder.m5123a()), false));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void m3110a(Void voidR) {
            if (this.f2127a.an != ConversationThreadFragment.FIRST_TIME_LOADING) {
                this.f2127a.an = ConversationThreadFragment.AFTER_CACHE_LOADING;
            }
            if (this.f2127a.m97k()) {
                this.f2127a.m100n().m146b(0, null, this.f2127a);
                ConversationThreadFragment conversationThreadFragment = this.f2127a;
                conversationThreadFragment.ak = conversationThreadFragment.ak + 30;
            }
            this.f2127a.am.m2811q();
            super.onPostExecute(voidR);
        }

        protected /* synthetic */ Object doInBackground(Object... objArr) {
            return m3109a((Void[]) objArr);
        }

        protected void onCancelled() {
            this.f2127a.am.m2811q();
            super.onCancelled();
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m3110a((Void) obj);
        }
    }

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.b */
    private class ConversationThreadFragment extends CursorAdapter {
        private static /* synthetic */ int[] f2152c;
        final /* synthetic */ ConversationThreadFragment f2153a;
        private LayoutInflater f2154b;

        /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.b.a */
        private class ConversationThreadFragment {
            RelativeLayout f2128a;
            RelativeLayout f2129b;
            RelativeLayout f2130c;
            RelativeLayout f2131d;
            RelativeLayout f2132e;
            RelativeLayout f2133f;
            LinearLayout f2134g;
            LinearLayout f2135h;
            LinearLayout f2136i;
            TextView f2137j;
            TextView f2138k;
            TextView f2139l;
            TextView f2140m;
            TextView f2141n;
            ImageView f2142o;
            ImageView f2143p;
            ImageView f2144q;
            ProgressBar f2145r;
            ImageView f2146s;
            ImageView f2147t;
            ImageView f2148u;
            TextView f2149v;
            boolean f2150w;
            final /* synthetic */ ConversationThreadFragment f2151x;

            private ConversationThreadFragment(ConversationThreadFragment conversationThreadFragment) {
                this.f2151x = conversationThreadFragment;
                this.f2150w = false;
            }
        }

        public ConversationThreadFragment(ConversationThreadFragment conversationThreadFragment, Context context) {
            this.f2153a = conversationThreadFragment;
            super(context, null, 0);
            this.f2154b = LayoutInflater.from(context);
        }

        private void m3112a(TextView textView, TextView textView2, int i, String str, String str2) {
            if (!this.f2153a.al || i == 0) {
                textView.setText(str2.trim());
                return;
            }
            textView.setText(str2.trim());
            textView2.setText(str.trim());
        }

        static /* synthetic */ int[] m3113a() {
            int[] iArr = f2152c;
            if (iArr == null) {
                iArr = new int[ConversationThreadFragment.values().length];
                try {
                    iArr[ConversationThreadFragment.AFTER_CACHE_LOADING.ordinal()] = 3;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[ConversationThreadFragment.FIRST_TIME_LOADING.ordinal()] = 1;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[ConversationThreadFragment.ON_NEW_MESSAGE.ordinal()] = 2;
                } catch (NoSuchFieldError e3) {
                }
                f2152c = iArr;
            }
            return iArr;
        }

        @SuppressLint({"ClickableViewAccessibility"})
        public void bindView(View view, Context context, Cursor cursor) {
            int i;
            RelativeLayout relativeLayout;
            ImageView imageView;
            LinearLayout linearLayout;
            TextView textView;
            ConversationThreadFragment conversationThreadFragment = (ConversationThreadFragment) view.getTag();
            int i2 = cursor.getInt(cursor.getColumnIndex("file_type"));
            int i3 = cursor.getInt(cursor.getColumnIndex("_id"));
            String string = cursor.getString(cursor.getColumnIndex("phonenumber_message"));
            int i4 = cursor.getInt(cursor.getColumnIndex("type"));
            int i5 = cursor.getInt(cursor.getColumnIndex(Status.ELEMENT));
            long a = DateAndTime.m4085a(cursor.getLong(cursor.getColumnIndex("send_date")));
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT));
            String string2 = cursor.getString(cursor.getColumnIndex("local_avatar_address"));
            int i6 = (i2 == 1 && StickerManager.m4025a(new String(blob))) ? 5 : i2;
            TextView textView2 = null;
            ImageView imageView2 = conversationThreadFragment.f2144q;
            TextView textView3 = conversationThreadFragment.f2149v;
            TextView textView4;
            LinearLayout linearLayout2;
            if (i4 == 1) {
                textView4 = conversationThreadFragment.f2137j;
                textView2 = conversationThreadFragment.f2140m;
                linearLayout2 = conversationThreadFragment.f2134g;
                ImageView imageView3 = conversationThreadFragment.f2142o;
                RelativeLayout relativeLayout2 = conversationThreadFragment.f2128a;
                i2 = i6 == 5 ? C0282R.color.transparent : 2130838409;
                if (this.f2153a.al) {
                    textView3.setVisibility(0);
                    i = i2;
                    relativeLayout = relativeLayout2;
                    imageView = imageView3;
                    linearLayout = linearLayout2;
                    textView = textView4;
                } else {
                    textView3.setVisibility(8);
                    i = i2;
                    relativeLayout = relativeLayout2;
                    imageView = imageView3;
                    linearLayout = linearLayout2;
                    textView = textView4;
                }
            } else if (i4 == 0) {
                textView4 = conversationThreadFragment.f2138k;
                textView2 = conversationThreadFragment.f2141n;
                i = i6 == 5 ? C0282R.color.transparent : 2130838411;
                relativeLayout = conversationThreadFragment.f2129b;
                imageView = conversationThreadFragment.f2143p;
                linearLayout = conversationThreadFragment.f2135h;
                textView = textView4;
            } else {
                textView4 = conversationThreadFragment.f2139l;
                linearLayout2 = conversationThreadFragment.f2136i;
                i = 0;
                relativeLayout = conversationThreadFragment.f2130c;
                imageView = null;
                linearLayout = linearLayout2;
                textView = textView4;
            }
            if (i4 == 1) {
                conversationThreadFragment.f2132e.setVisibility(8);
                conversationThreadFragment.f2133f.setVisibility(8);
                conversationThreadFragment.f2131d.setVisibility(0);
            } else if (i4 == 0) {
                conversationThreadFragment.f2131d.setVisibility(8);
                conversationThreadFragment.f2133f.setVisibility(8);
                conversationThreadFragment.f2132e.setVisibility(0);
            } else {
                conversationThreadFragment.f2131d.setVisibility(8);
                conversationThreadFragment.f2132e.setVisibility(8);
                conversationThreadFragment.f2133f.setVisibility(0);
            }
            String string3 = cursor.getString(cursor.getColumnIndex("nick_name"));
            if (string3 == null) {
                m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a));
            } else {
                m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a));
            }
            if (i4 == 1) {
                conversationThreadFragment.f2145r.setVisibility(8);
                conversationThreadFragment.f2148u.setVisibility(8);
            } else if (i4 == 0) {
                if (i5 == 0) {
                    conversationThreadFragment.f2145r.setVisibility(8);
                    conversationThreadFragment.f2148u.setVisibility(8);
                    imageView2.setVisibility(0);
                    imageView2.setImageResource(2130838057);
                } else if (i5 == 1) {
                    conversationThreadFragment.f2145r.setVisibility(8);
                    if (i6 == 5 || i6 == 1) {
                        conversationThreadFragment.f2148u.setVisibility(0);
                    } else {
                        conversationThreadFragment.f2148u.setVisibility(8);
                    }
                    imageView2.setVisibility(8);
                } else if (i5 == 2 || i5 == 2) {
                    conversationThreadFragment.f2145r.setVisibility(8);
                    conversationThreadFragment.f2148u.setVisibility(8);
                    imageView2.setVisibility(0);
                    if (this.f2153a.al) {
                        imageView2.setImageResource(2130838057);
                    } else {
                        imageView2.setImageResource(2130838056);
                    }
                } else if (i5 == 5) {
                    conversationThreadFragment.f2148u.setVisibility(8);
                    conversationThreadFragment.f2145r.setVisibility(0);
                    imageView2.setVisibility(8);
                }
            }
            textView2.setVisibility(8);
            i2 = cursor.getPosition();
            conversationThreadFragment.f2146s.setOnClickListener(new ConversationThreadFragment(this, cursor, i2));
            conversationThreadFragment.f2147t.setOnClickListener(new ConversationThreadFragment(this));
            conversationThreadFragment.f2131d.setOnLongClickListener(new ConversationThreadFragment(this));
            conversationThreadFragment.f2132e.setOnLongClickListener(new ConversationThreadFragment(this));
            conversationThreadFragment.f2131d.setOnClickListener(new ConversationThreadFragment(this, cursor, i2));
            conversationThreadFragment.f2132e.setOnClickListener(new ConversationThreadFragment(this, cursor, i2));
            conversationThreadFragment.f2133f.setOnClickListener(new ConversationThreadFragment(this));
            imageView.setVisibility(0);
            relativeLayout.setBackgroundResource(i);
            View robotoTextView;
            if (i6 == 1) {
                robotoTextView = new RobotoTextView(this.f2153a.m91h());
                RobotoTextViewUtils.m2215a(robotoTextView, RobotoTypefaceManager.m2217a(context, 4));
                robotoTextView.setTextColor(this.f2153a.m93i().getColor(R.text_color));
                robotoTextView.setPadding((int) DpPixelConvertor.m4026a(this.f2153a.m91h(), 5.0f), 0, (int) DpPixelConvertor.m4026a(this.f2153a.m91h(), 5.0f), (int) DpPixelConvertor.m4026a(this.f2153a.m91h(), 5.0f));
                robotoTextView.setTextSize(this.f2153a.m93i().getDimension(2131296290) / this.f2153a.m93i().getDisplayMetrics().density);
                TextLoader.m4005a(this.f2153a.m91h()).m4016a(new StringBuilder(String.valueOf(i3)).append("_").append(a).toString(), blob, robotoTextView, false, true);
                robotoTextView.setOnLongClickListener(new ConversationThreadFragment(this, conversationThreadFragment));
                robotoTextView.setOnTouchListener(new ConversationThreadFragment(this, conversationThreadFragment));
                linearLayout.removeAllViews();
                linearLayout.addView(robotoTextView);
            } else if (i6 == 5) {
                ImageView imageView4 = new ImageView(this.f2153a.m91h());
                imageView4.setAdjustViewBounds(true);
                imageView4.setScaleType(ScaleType.FIT_CENTER);
                String str = new String(blob);
                this.f2153a.f2190Z.m3982a(this.f2153a.m3161a(str), this.f2153a.m3167b(str), "1", string, imageView4);
                linearLayout.removeAllViews();
                linearLayout.addView(imageView4);
                imageView.setVisibility(4);
            } else if (i6 == 2 || i6 == 4) {
                LayoutParams layoutParams;
                int i7 = cursor.getInt(cursor.getColumnIndex("filestatus"));
                r10 = cursor.getInt(cursor.getColumnIndex("filelength"));
                r10 = r10 != 0 ? r10 / 1024 : 0;
                robotoTextView = new RelativeLayout(this.f2153a.m91h());
                ImageView imageView5 = new ImageView(this.f2153a.m91h());
                imageView5.setId(2147482647);
                imageView5.setAdjustViewBounds(true);
                imageView5.setScaleType(ScaleType.FIT_CENTER);
                if (i6 == 4) {
                    this.f2153a.f2191i.m2484a(cursor.getString(cursor.getColumnIndex("thumbnailaddress")), imageView5);
                } else {
                    this.f2153a.f2189Y.m2484a(cursor.getString(cursor.getColumnIndex("thumbnailaddress")), imageView5);
                }
                Display defaultDisplay = ((WindowManager) this.f2153a.m91h().getSystemService("window")).getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getSize(point);
                i2 = (point.x * 50) / 100;
                if (this.f2153a.m93i().getConfiguration().orientation == 2) {
                    i2 = (point.y * 50) / 100;
                }
                imageView5.setLayoutParams(new LayoutParams(i2, i2));
                robotoTextView.addView(imageView5);
                if (new String(blob).trim().isEmpty()) {
                    textView2.setVisibility(8);
                } else {
                    textView2.setVisibility(0);
                    TextView robotoTextView2 = new RobotoTextView(this.f2153a.m91h());
                    RobotoTextViewUtils.m2215a(robotoTextView2, RobotoTypefaceManager.m2217a(context, 4));
                    robotoTextView2.setTextColor(this.f2153a.m93i().getColor(R.text_color));
                    robotoTextView2.setPadding((int) DpPixelConvertor.m4026a(this.f2153a.m91h(), 5.0f), 0, (int) DpPixelConvertor.m4026a(this.f2153a.m91h(), 5.0f), (int) DpPixelConvertor.m4026a(this.f2153a.m91h(), 5.0f));
                    robotoTextView2.setTextSize(this.f2153a.m93i().getDimension(2131296290) / this.f2153a.m93i().getDisplayMetrics().density);
                    TextLoader.m4005a(this.f2153a.m91h()).m4016a(new StringBuilder(String.valueOf(i3)).append("_").append(a).toString(), blob, textView2, true, true);
                }
                View imageView6;
                switch (i7) {
                    case VideoSize.CIF /*1*/:
                        imageView6 = new ImageView(this.f2153a.m91h());
                        imageView6.setScaleType(ScaleType.CENTER_INSIDE);
                        imageView6.setImageResource(i4 == 1 ? 2130838095 : 2130838096);
                        robotoTextView.addView(imageView6);
                        layoutParams = (LayoutParams) imageView6.getLayoutParams();
                        layoutParams.addRule(13, -1);
                        imageView6.setLayoutParams(layoutParams);
                        if (i4 != 0) {
                            if (string3 != null) {
                                m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a));
                                break;
                            } else {
                                m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a));
                                break;
                            }
                        } else if (string3 != null) {
                            m3112a(textView, textView3, i4, string3, context.getString(2131493667));
                            break;
                        } else {
                            m3112a(textView, textView3, i4, string, context.getString(2131493667));
                            break;
                        }
                    case VideoSize.HVGA /*2*/:
                        imageView6 = new ImageView(this.f2153a.m91h());
                        imageView6.setScaleType(ScaleType.CENTER_INSIDE);
                        imageView6.setImageResource(2130838097);
                        robotoTextView.addView(imageView6);
                        layoutParams = (LayoutParams) imageView6.getLayoutParams();
                        layoutParams.addRule(13, -1);
                        imageView6.setLayoutParams(layoutParams);
                        i2 = cursor.getInt(cursor.getColumnIndex("progress"));
                        if (i2 > 100) {
                            i2 = 100;
                        }
                        if (i4 != 0) {
                            if (string3 != null) {
                                m3112a(textView, textView3, i4, string3, context.getString(2131493669) + "  " + i2 + "%");
                                break;
                            } else {
                                m3112a(textView, textView3, i4, string, context.getString(2131493669) + "  " + i2 + "%");
                                break;
                            }
                        } else if (string3 != null) {
                            m3112a(textView, textView3, i4, string3, context.getString(2131493668) + "  " + i2 + "%");
                            break;
                        } else {
                            m3112a(textView, textView3, i4, string, context.getString(2131493668) + "  " + i2 + "%");
                            break;
                        }
                    case Version.API03_CUPCAKE_15 /*3*/:
                        if (i6 == 2) {
                            imageView6 = new ImageView(this.f2153a.m91h());
                            imageView6.setScaleType(ScaleType.CENTER_INSIDE);
                            imageView6.setImageResource(2130838085);
                            robotoTextView.addView(imageView6);
                            layoutParams = (LayoutParams) imageView6.getLayoutParams();
                            layoutParams.addRule(13, -1);
                            imageView6.setLayoutParams(layoutParams);
                        }
                        if (i4 != 0) {
                            if (string3 != null) {
                                m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a));
                                break;
                            } else {
                                m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a));
                                break;
                            }
                        } else if (string3 != null) {
                            m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a));
                            break;
                        } else {
                            m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a));
                            break;
                        }
                    case Version.API04_DONUT_16 /*4*/:
                        imageView6 = new ImageView(this.f2153a.m91h());
                        imageView6.setScaleType(ScaleType.CENTER_INSIDE);
                        imageView6.setImageResource(i4 == 1 ? 2130838095 : 2130838096);
                        robotoTextView.addView(imageView6);
                        layoutParams = (LayoutParams) imageView6.getLayoutParams();
                        layoutParams.addRule(13, -1);
                        imageView6.setLayoutParams(layoutParams);
                        if (i4 != 0) {
                            if (string3 != null) {
                                m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a) + ", " + context.getString(2131493671));
                                break;
                            } else {
                                m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a) + ", " + context.getString(2131493671));
                                break;
                            }
                        } else if (string3 != null) {
                            m3112a(textView, textView3, i4, string3, context.getString(2131493670));
                            break;
                        } else {
                            m3112a(textView, textView3, i4, string, context.getString(2131493670));
                            break;
                        }
                    case Version.API05_ECLAIR_20 /*5*/:
                        imageView6 = new ImageView(this.f2153a.m91h());
                        imageView6.setScaleType(ScaleType.CENTER_INSIDE);
                        imageView6.setImageResource(i4 == 1 ? 2130838095 : 2130838096);
                        robotoTextView.addView(imageView6);
                        layoutParams = (LayoutParams) imageView6.getLayoutParams();
                        layoutParams.addRule(13, -1);
                        imageView6.setLayoutParams(layoutParams);
                        if (i4 != 0) {
                            if (string3 != null) {
                                m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a));
                                break;
                            } else {
                                m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a));
                                break;
                            }
                        } else if (string3 != null) {
                            m3112a(textView, textView3, i4, string3, context.getString(2131493667));
                            break;
                        } else {
                            m3112a(textView, textView3, i4, string, context.getString(2131493667));
                            break;
                        }
                }
                View robotoTextView3 = new RobotoTextView(this.f2153a.m91h());
                RobotoTextViewUtils.m2215a(robotoTextView3, RobotoTypefaceManager.m2217a(context, 4));
                robotoTextView3.setText(new StringBuilder(String.valueOf(r10)).append("KB").toString());
                robotoTextView3.setSingleLine(true);
                robotoTextView3.setTextSize(11.0f);
                robotoTextView3.setTextColor(this.f2153a.m93i().getColor(2131230794));
                robotoTextView3.setPadding(10, 0, 10, 0);
                robotoTextView3.setShadowLayer(10.0f, 0.0f, 0.0f, -16777216);
                robotoTextView.addView(robotoTextView3);
                layoutParams = (LayoutParams) robotoTextView3.getLayoutParams();
                layoutParams = (LayoutParams) robotoTextView3.getLayoutParams();
                layoutParams.addRule(8, imageView5.getId());
                layoutParams.addRule(7, imageView5.getId());
                robotoTextView3.setLayoutParams(layoutParams);
                linearLayout.removeAllViews();
                linearLayout.addView(robotoTextView);
            } else if (i6 == 3) {
                i2 = cursor.getInt(cursor.getColumnIndex("filestatus"));
                r10 = cursor.getInt(cursor.getColumnIndex("multimediaduration"));
                robotoTextView = new ImageView(this.f2153a.m91h());
                View robotoTextView4 = new RobotoTextView(this.f2153a.m91h());
                RobotoTextViewUtils.m2215a(robotoTextView4, RobotoTypefaceManager.m2217a(context, 4));
                robotoTextView4.setTextSize(16.0f);
                robotoTextView4.setTextColor(this.f2153a.m93i().getColor(R.color_chooser_orange1));
                switch (i2) {
                    case VideoSize.CIF /*1*/:
                        if (i4 == 1) {
                            robotoTextView4.setText(this.f2153a.m91h().getString(2131493672));
                        } else {
                            robotoTextView4.setText(this.f2153a.m91h().getString(2131493673));
                        }
                        robotoTextView.setVisibility(8);
                        break;
                    case VideoSize.HVGA /*2*/:
                        robotoTextView.setVisibility(8);
                        robotoTextView4.setText(this.f2153a.m3165b(r10));
                        if (i4 != 0) {
                            if (string3 != null) {
                                m3112a(textView, textView3, i4, string3, context.getString(2131493669));
                                break;
                            } else {
                                m3112a(textView, textView3, i4, string, context.getString(2131493669));
                                break;
                            }
                        } else if (string3 != null) {
                            m3112a(textView, textView3, i4, string3, context.getString(2131493668));
                            break;
                        } else {
                            m3112a(textView, textView3, i4, string, context.getString(2131493668));
                            break;
                        }
                    case Version.API03_CUPCAKE_15 /*3*/:
                        robotoTextView.setVisibility(0);
                        if (i4 == 0) {
                            if (string3 == null) {
                                m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a));
                            } else {
                                m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a));
                            }
                            textView.setText(TimeUtils.m4028a(context, a));
                        } else {
                            if (string3 == null) {
                                m3112a(textView, textView3, i4, string, TimeUtils.m4028a(context, a));
                            } else {
                                m3112a(textView, textView3, i4, string3, TimeUtils.m4028a(context, a));
                            }
                            textView.setText(TimeUtils.m4028a(context, a));
                        }
                        switch (cursor.getInt(cursor.getColumnIndex("audio_state"))) {
                            case VideoSize.CIF /*1*/:
                                robotoTextView.setImageResource(2130838051);
                                robotoTextView4.setText(this.f2153a.m91h().getString(2131493674));
                                break;
                            case VideoSize.HVGA /*2*/:
                                robotoTextView.setImageResource(2130838052);
                                robotoTextView4.setText(this.f2153a.m3165b(r10));
                                break;
                            case Version.API03_CUPCAKE_15 /*3*/:
                                robotoTextView.setImageResource(2130838052);
                                robotoTextView4.setText(this.f2153a.m91h().getString(2131493675));
                                break;
                            case Version.API04_DONUT_16 /*4*/:
                                robotoTextView.setImageResource(2130838052);
                                robotoTextView4.setText(this.f2153a.m3165b(r10));
                                break;
                            default:
                                break;
                        }
                    case Version.API04_DONUT_16 /*4*/:
                        if (i4 == 1) {
                            robotoTextView4.setText(this.f2153a.m91h().getString(2131493672));
                        } else {
                            robotoTextView4.setText(this.f2153a.m91h().getString(2131493673));
                        }
                        robotoTextView.setVisibility(8);
                        break;
                    case Version.API05_ECLAIR_20 /*5*/:
                        if (i4 == 1) {
                            robotoTextView4.setText(this.f2153a.m91h().getString(2131493672));
                        } else {
                            robotoTextView4.setText(this.f2153a.m91h().getString(2131493673));
                        }
                        robotoTextView.setVisibility(8);
                        break;
                }
                linearLayout.removeAllViews();
                linearLayout.addView(robotoTextView);
                linearLayout.addView(robotoTextView4);
            }
            conversationThreadFragment.f2148u.setOnClickListener(new ConversationThreadFragment(this, i6, blob, i3));
            i2 = cursor.getCount();
            if (this.f2153a.ah < i2) {
                int j = (i2 - this.f2153a.ah) + 1;
                this.f2153a.ah = i2;
                switch (ConversationThreadFragment.m3113a()[this.f2153a.an.ordinal()]) {
                    case VideoSize.CIF /*1*/:
                        this.f2153a.m3193a(false);
                        break;
                    case VideoSize.HVGA /*2*/:
                        break;
                    case Version.API03_CUPCAKE_15 /*3*/:
                        this.f2153a.an = ConversationThreadFragment.ON_NEW_MESSAGE;
                        new Handler().postDelayed(new ConversationThreadFragment(this, j), 10);
                        break;
                }
                cursor.moveToLast();
                i2 = cursor.getInt(cursor.getColumnIndex("type"));
                this.f2153a.an = ConversationThreadFragment.ON_NEW_MESSAGE;
                if (i2 != 1) {
                    this.f2153a.m3193a(false);
                } else if (this.f2153a.m3186a().getCount() - this.f2153a.m3186a().getFirstVisiblePosition() <= 10) {
                    this.f2153a.m3193a(false);
                } else {
                    this.f2153a.ae.m2966a(0);
                }
                new Thread(new ConversationThreadFragment(this)).start();
            }
            if (i4 != 1) {
                this.f2153a.ab.m2484a(this.f2153a.ap, conversationThreadFragment.f2147t);
            } else if (this.f2153a.al || this.f2153a.ai.matches("[0-9]+")) {
                this.f2153a.ab.m2484a((Object) string2, conversationThreadFragment.f2146s);
            } else {
                Picasso.with(context).load(2130838100).into(conversationThreadFragment.f2146s);
            }
        }

        public void changeCursor(Cursor cursor) {
            super.changeCursor(cursor);
        }

        public int getCount() {
            return getCursor() == null ? 0 : super.getCount();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            return super.getView(i, view, viewGroup);
        }

        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            View inflate = this.f2154b.inflate(2130903040, null);
            ConversationThreadFragment conversationThreadFragment = new ConversationThreadFragment();
            conversationThreadFragment.f2134g = (LinearLayout) inflate.findViewById(2131427407);
            conversationThreadFragment.f2135h = (LinearLayout) inflate.findViewById(2131427416);
            conversationThreadFragment.f2136i = (LinearLayout) inflate.findViewById(2131427425);
            conversationThreadFragment.f2128a = (RelativeLayout) inflate.findViewById(2131427405);
            conversationThreadFragment.f2129b = (RelativeLayout) inflate.findViewById(2131427415);
            conversationThreadFragment.f2130c = (RelativeLayout) inflate.findViewById(2131427424);
            conversationThreadFragment.f2149v = (TextView) inflate.findViewById(2131427406);
            conversationThreadFragment.f2137j = (TextView) inflate.findViewById(2131427408);
            conversationThreadFragment.f2138k = (TextView) inflate.findViewById(2131427420);
            conversationThreadFragment.f2139l = (TextView) inflate.findViewById(2131427426);
            conversationThreadFragment.f2140m = (TextView) inflate.findViewById(2131427409);
            conversationThreadFragment.f2141n = (TextView) inflate.findViewById(2131427421);
            conversationThreadFragment.f2131d = (RelativeLayout) inflate.findViewById(2131427401);
            conversationThreadFragment.f2132e = (RelativeLayout) inflate.findViewById(2131427410);
            conversationThreadFragment.f2133f = (RelativeLayout) inflate.findViewById(2131427422);
            conversationThreadFragment.f2146s = (ImageView) inflate.findViewById(2131427402);
            conversationThreadFragment.f2147t = (ImageView) inflate.findViewById(2131427411);
            conversationThreadFragment.f2148u = (ImageView) inflate.findViewById(2131427414);
            conversationThreadFragment.f2142o = (ImageView) inflate.findViewById(2131427403);
            conversationThreadFragment.f2143p = (ImageView) inflate.findViewById(2131427413);
            conversationThreadFragment.f2144q = (ImageView) inflate.findViewById(2131427418);
            conversationThreadFragment.f2145r = (ProgressBar) inflate.findViewById(2131427419);
            inflate.setTag(conversationThreadFragment);
            if (!(this.f2153a.ad == null || this.f2153a.ad.m5061b() == null)) {
                this.f2153a.ab.m2484a(this.f2153a.ad.m5061b(), conversationThreadFragment.f2146s);
            }
            if (this.f2153a.ao != null) {
                this.f2153a.ab.m2484a(this.f2153a.ao.m3629a(), conversationThreadFragment.f2146s);
            }
            return inflate;
        }

        public Cursor swapCursor(Cursor cursor) {
            return super.swapCursor(cursor);
        }
    }

    /* renamed from: com.mmdt.syna.view.conversation.conversationpage.b.c */
    private enum ConversationThreadFragment {
        FIRST_TIME_LOADING,
        ON_NEW_MESSAGE,
        AFTER_CACHE_LOADING
    }

    public ConversationThreadFragment() {
        this.ah = 0;
        this.ai = "";
        this.ak = 20;
        this.al = false;
        this.an = ConversationThreadFragment.FIRST_TIME_LOADING;
    }

    public ConversationThreadFragment(String str, ControlTransmitListener controlTransmitListener, boolean z) {
        this.ah = 0;
        this.ai = "";
        this.ak = 20;
        this.al = false;
        this.an = ConversationThreadFragment.FIRST_TIME_LOADING;
        if (str != null) {
            this.ai = str;
        }
        this.al = z;
        this.ad = SynaContacts.m5002a((Context) controlTransmitListener, str, false);
        this.aj = controlTransmitListener;
    }

    private void m3155E() {
        this.am = (PullToRefreshListView) this.ag.findViewById(2131427621);
        this.am.setOnRefreshListener(new ConversationThreadFragment(this));
    }

    private int m3156F() {
        TypedValue typedValue = new TypedValue();
        m91h().getTheme().resolveAttribute(16842829, typedValue, true);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        m91h().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) typedValue.getDimension(displayMetrics);
    }

    private Bitmap m3158a(String str, int i) {
        FileNotFoundException e;
        Throwable th;
        Bitmap bitmap = null;
        if (m97k() && m91h() != null) {
            AssetFileDescriptor openAssetFileDescriptor;
            try {
                openAssetFileDescriptor = m91h().getContentResolver().openAssetFileDescriptor(HasVersion.m4098a() ? Uri.parse(str) : Uri.parse(str), AckRequest.ELEMENT);
                try {
                    FileDescriptor fileDescriptor = openAssetFileDescriptor.getFileDescriptor();
                    if (fileDescriptor != null) {
                        bitmap = ImageLoader.m2471a(fileDescriptor, i, i);
                        if (openAssetFileDescriptor != null) {
                            try {
                                openAssetFileDescriptor.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (openAssetFileDescriptor != null) {
                        try {
                            openAssetFileDescriptor.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (FileNotFoundException e3) {
                    e = e3;
                    try {
                        Log.d("ConversationThreadFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
                        if (openAssetFileDescriptor != null) {
                            try {
                                openAssetFileDescriptor.close();
                            } catch (IOException e222) {
                                e222.printStackTrace();
                            }
                        }
                        return bitmap;
                    } catch (Throwable th2) {
                        th = th2;
                        if (openAssetFileDescriptor != null) {
                            try {
                                openAssetFileDescriptor.close();
                            } catch (IOException e2222) {
                                e2222.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                openAssetFileDescriptor = null;
                Log.d("ConversationThreadFragment", "Contact photo thumbnail not found for contact " + str + ": " + e.toString());
                if (openAssetFileDescriptor != null) {
                    openAssetFileDescriptor.close();
                }
                return bitmap;
            } catch (Throwable th3) {
                openAssetFileDescriptor = null;
                th = th3;
                if (openAssetFileDescriptor != null) {
                    openAssetFileDescriptor.close();
                }
                throw th;
            }
        }
        return bitmap;
    }

    private String m3161a(String str) {
        return str.substring(2, 5);
    }

    private String m3165b(int i) {
        if (i < 0) {
            return "00:00";
        }
        Object obj;
        String str = "";
        int i2 = i / 1000;
        int i3 = i2 / 60;
        int i4 = i2 % 60;
        if (i3 > 9) {
            obj = i3;
        } else {
            String str2 = "0" + i3;
        }
        return new StringBuilder(String.valueOf(obj)).append(":").append(i4 > 9 ? i4 : "0" + i4).toString();
    }

    private String m3167b(String str) {
        return str.substring(14, 16);
    }

    public void m3183D() {
        this.aj.b_();
    }

    public Loader<Cursor> m3184a(int i, Bundle bundle) {
        return Messages.m5102a(m91h(), this.ai, this.ak);
    }

    public View m3185a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ag = layoutInflater.inflate(2130903116, viewGroup, false);
        m3155E();
        return this.ag;
    }

    public ListView m3186a() {
        return (ListView) this.am.m2805k();
    }

    public void m3187a(long j, String str) {
        this.aj.m2963a(j, str);
    }

    public void m3188a(Activity activity) {
        super.m57a(activity);
        try {
            this.ae = (ConversationThreadFragment) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnContactsInteractionListener");
        }
    }

    public void m3189a(Bundle bundle) {
        super.m61a(bundle);
        this.af = m93i().getBoolean(2131165250);
        this.aa = new ConversationThreadFragment(this, m91h());
        this.ao = new ad(m91h());
        if (AppSettings.m4867a(m91h()).m4887e() != null) {
            this.ap = AppSettings.m4867a(m91h()).m4887e().toString();
        } else {
            this.ap = null;
        }
        if (bundle != null) {
            this.ac = bundle.getString(UserSearch.ELEMENT);
        }
        this.f2189Y = new ConversationThreadFragment(this, m91h(), 480);
        this.f2191i = new ConversationThreadFragment(this, m91h(), 480);
        this.ab = new ConversationThreadFragment(this, m91h(), m3156F());
        this.f2190Z = new StickerImageLoader(m91h(), 320);
        this.f2189Y.m2482a(2130837748);
        this.f2191i.m2482a(2130837746);
        this.ab.m2482a(2130838061);
        this.f2190Z.m3980a(2130838054);
        this.f2189Y.m2483a(m91h().getFragmentManager(), 0.1f);
        this.f2191i.m2483a(m91h().getFragmentManager(), 0.1f);
        this.ab.m2483a(m91h().getFragmentManager(), 0.1f);
        this.f2190Z.m3981a(m91h().getFragmentManager(), 0.1f);
        TextLoader.m4005a(m91h()).m4014a(m91h().getFragmentManager(), 0.5f);
    }

    public void m3190a(Loader<Cursor> loader) {
        this.aa.swapCursor(null);
    }

    public void m3191a(Loader<Cursor> loader, Cursor cursor) {
        this.aa.swapCursor(cursor);
    }

    public void m3193a(boolean z) {
        new Handler().postDelayed(new ConversationThreadFragment(this, z), 50);
    }

    public boolean m3194b(MenuItem menuItem) {
        AdapterContextMenuInfo adapterContextMenuInfo = (AdapterContextMenuInfo) menuItem.getMenuInfo();
        int itemId = menuItem.getItemId();
        Cursor cursor = (Cursor) m368b().getItem(adapterContextMenuInfo.position - 1);
        int i = cursor.getInt(cursor.getColumnIndex("file_type"));
        switch (itemId) {
            case VideoSize.CIF /*1*/:
                if (i != 1) {
                    this.ae.m2968a(new String(cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT))), Uri.parse(cursor.getString(cursor.getColumnIndex("fileaddress"))).getPath());
                    break;
                }
                this.ae.a_(new String(cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT))));
                break;
            case VideoSize.HVGA /*2*/:
                String str = new String(cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT)));
                if (i != 1) {
                    this.ae.m2967a(i, str, Uri.parse(cursor.getString(cursor.getColumnIndex("fileaddress"))).getPath());
                    break;
                }
                this.ae.m2967a(i, str, null);
                break;
            case Version.API03_CUPCAKE_15 /*3*/:
                this.ae.m2970b(new String(cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT))));
                break;
            case Version.API04_DONUT_16 /*4*/:
                Messages.m5096a(m91h(), (long) cursor.getInt(cursor.getColumnIndex("_id")));
                break;
            case Version.API05_ECLAIR_20 /*5*/:
                String a = m3161a(new String(cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT))));
                Intent intent = new Intent(m91h(), StickerDetailsActivity.class);
                intent.putExtra("StickerDetailsActivity.KEY_PACKAGE_ID", a);
                m59a(intent);
                m91h().overridePendingTransition(0, 0);
                break;
        }
        return super.m74b(menuItem);
    }

    public void m3195d(Bundle bundle) {
        super.m80d(bundle);
        m366a(this.aa);
        m3186a().setOnScrollListener(new ConversationThreadFragment(this));
        m3186a().setDividerHeight(1);
        new ConversationThreadFragment().execute(new Void[0]);
        m3186a().setOnItemClickListener(new ConversationThreadFragment(this));
        m3186a().setOnTouchListener(new ConversationThreadFragment(this));
        m65a(m3186a());
    }

    public void m3196e(Bundle bundle) {
        super.m85e(bundle);
        if (!TextUtils.isEmpty(this.ac)) {
            bundle.putString(UserSearch.ELEMENT, this.ac);
            bundle.putInt("com.example.android.conversationthread.ui.SELECTED_ITEM", m3186a().getCheckedItemPosition());
        }
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        if (view.getId() == 16908298) {
            Cursor cursor = (Cursor) m368b().getItem(((AdapterContextMenuInfo) contextMenuInfo).position - 1);
            int i = cursor.getInt(cursor.getColumnIndex("type"));
            String[] stringArray;
            if (cursor.getInt(cursor.getColumnIndex("file_type")) == 1) {
                stringArray = m93i().getStringArray(2131623945);
                String str = new String(cursor.getBlob(cursor.getColumnIndex(DataPacketExtension.ELEMENT)));
                if (StickerManager.m4025a(str)) {
                    contextMenu.setHeaderTitle(m91h().getString(2131493665));
                    if (i == 0) {
                        contextMenu.add(0, 1, 0, stringArray[0]);
                        contextMenu.add(0, 2, 1, stringArray[1]);
                        if (!this.al) {
                            contextMenu.add(0, 4, 2, stringArray[3]);
                        }
                    } else {
                        contextMenu.add(0, 2, 0, stringArray[1]);
                        if (!StickerPackages.m5132a(m91h(), m3161a(str))) {
                            contextMenu.add(0, 5, 1, stringArray[4]);
                        }
                        if (!this.al) {
                            contextMenu.add(0, 4, 2, stringArray[3]);
                        }
                    }
                } else {
                    contextMenu.setHeaderTitle(EmoticonManager.m4022a(m91h()).m4024a(m91h(), str, false));
                    if (i == 0) {
                        contextMenu.add(0, 1, 0, stringArray[0]);
                        contextMenu.add(0, 2, 1, stringArray[1]);
                        contextMenu.add(0, 3, 2, stringArray[2]);
                        if (!this.al) {
                            contextMenu.add(0, 4, 3, stringArray[3]);
                        }
                    } else {
                        contextMenu.add(0, 2, 0, stringArray[1]);
                        contextMenu.add(0, 3, 1, stringArray[2]);
                        if (!this.al) {
                            contextMenu.add(0, 4, 2, stringArray[3]);
                        }
                    }
                }
            } else {
                contextMenu.setHeaderTitle(cursor.getString(cursor.getColumnIndex("filename")));
                stringArray = m93i().getStringArray(2131623945);
                if (i != 0 || cursor.getInt(cursor.getColumnIndex("filestatus")) == 2) {
                    contextMenu.add(0, 2, 0, stringArray[1]);
                    if (!this.al) {
                        contextMenu.add(0, 4, 1, stringArray[3]);
                    }
                } else {
                    contextMenu.add(0, 1, 0, stringArray[0]);
                    contextMenu.add(0, 2, 1, stringArray[1]);
                    if (!this.al) {
                        contextMenu.add(0, 4, 2, stringArray[3]);
                    }
                }
            }
        }
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.aa.getCursor().moveToPosition(i);
    }

    public void m3197q() {
        super.m103q();
        this.ab.m2486b(false);
        this.f2191i.m2486b(false);
        this.f2189Y.m2486b(false);
        this.f2190Z.m3983a(false);
        TextLoader.m4005a(m91h()).m4017a(false);
    }
}
