package com.mmdt.syna.view.tools.p037b;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.mmdt.syna.view.tools.b.a */
public class EmoticonManager {
    public static final HashMap<String, Integer> f3155a;
    public static final HashMap<String, Integer> f3156b;
    public static final HashMap<String, Integer> f3157c;
    private static EmoticonManager f3158d;
    private Pattern f3159e;

    static {
        f3155a = new HashMap();
        f3155a.put(" :@", Integer.valueOf(2130838430));
        f3155a.put(" :D", Integer.valueOf(2130838432));
        f3155a.put(" %)", Integer.valueOf(2130838434));
        f3155a.put(" :-S", Integer.valueOf(2130838436));
        f3155a.put(" :X", Integer.valueOf(2130838442));
        f3155a.put(" :-(", Integer.valueOf(2130838444));
        f3155a.put(" :c", Integer.valueOf(2130838445));
        f3155a.put(" :(", Integer.valueOf(2130838447));
        f3155a.put(" >:c", Integer.valueOf(2130838449));
        f3155a.put(" >:p", Integer.valueOf(2130838452));
        f3155a.put(" :$", Integer.valueOf(2130838454));
        f3155a.put(" :)", Integer.valueOf(2130838460));
        f3155a.put(" )", Integer.valueOf(2130838461));
        f3155a.put(" D", Integer.valueOf(2130838463));
        f3155a.put(":-@", Integer.valueOf(2130838468));
        f3155a.put(":-&", Integer.valueOf(2130838456));
        f3155a.put(":-p", Integer.valueOf(2130838466));
        f3155a.put(" :-/", Integer.valueOf(2130838472));
        f3155a.put(" :P", Integer.valueOf(2130838474));
        f3155a.put(" ;)", Integer.valueOf(2130838476));
        f3155a.put(" >:)", Integer.valueOf(2130838438));
        f3155a.put(" :-O", Integer.valueOf(2130838470));
        f3155a.put(" I-o", Integer.valueOf(2130838458));
        f3156b = new HashMap();
        f3156b.put(" :@", Integer.valueOf(2130838430));
        f3156b.put(" :-||", Integer.valueOf(2130838430));
        f3156b.put(" >:(", Integer.valueOf(2130838430));
        f3156b.put(" :D", Integer.valueOf(2130838432));
        f3156b.put(" :d", Integer.valueOf(2130838432));
        f3156b.put(" :-D", Integer.valueOf(2130838432));
        f3156b.put(" :-d", Integer.valueOf(2130838432));
        f3156b.put(" =D", Integer.valueOf(2130838432));
        f3156b.put(" =d", Integer.valueOf(2130838432));
        f3156b.put(" =-D", Integer.valueOf(2130838432));
        f3156b.put(" =-d", Integer.valueOf(2130838432));
        f3156b.put(" %)", Integer.valueOf(2130838434));
        f3156b.put(" %-)", Integer.valueOf(2130838434));
        f3156b.put(" :S", Integer.valueOf(2130838436));
        f3156b.put(" :-S", Integer.valueOf(2130838436));
        f3156b.put(" :s", Integer.valueOf(2130838436));
        f3156b.put(" :-s", Integer.valueOf(2130838436));
        f3156b.put(" :G", Integer.valueOf(2130837798));
        f3156b.put(" :-G", Integer.valueOf(2130837798));
        f3156b.put(" :-g", Integer.valueOf(2130837798));
        f3156b.put(" :g", Integer.valueOf(2130837798));
        f3156b.put(" :-F", Integer.valueOf(2130837800));
        f3156b.put(" :F", Integer.valueOf(2130837800));
        f3156b.put(" :-f", Integer.valueOf(2130837800));
        f3156b.put(" :f", Integer.valueOf(2130837800));
        f3156b.put(" <3", Integer.valueOf(2130837802));
        f3156b.put(" :H", Integer.valueOf(2130837804));
        f3156b.put(" :-H", Integer.valueOf(2130837804));
        f3156b.put(" :h", Integer.valueOf(2130837804));
        f3156b.put(" :-h", Integer.valueOf(2130837804));
        f3156b.put(" :X", Integer.valueOf(2130838442));
        f3156b.put(" :-X", Integer.valueOf(2130838442));
        f3156b.put(" :x", Integer.valueOf(2130838442));
        f3156b.put(" :-x", Integer.valueOf(2130838442));
        f3156b.put(" :-(", Integer.valueOf(2130838444));
        f3156b.put(" :-<", Integer.valueOf(2130838444));
        f3156b.put(" :<", Integer.valueOf(2130838444));
        f3156b.put(" :-[", Integer.valueOf(2130838444));
        f3156b.put(" :[", Integer.valueOf(2130838444));
        f3156b.put(" :{", Integer.valueOf(2130838444));
        f3156b.put(" >:[", Integer.valueOf(2130838444));
        f3156b.put(" :C", Integer.valueOf(2130838445));
        f3156b.put(" :c", Integer.valueOf(2130838445));
        f3156b.put(" :-C", Integer.valueOf(2130838445));
        f3156b.put(" :-c", Integer.valueOf(2130838445));
        f3156b.put(" :(", Integer.valueOf(2130838447));
        f3156b.put(" >:c", Integer.valueOf(2130838449));
        f3156b.put(" >:p", Integer.valueOf(2130838452));
        f3156b.put(" :$", Integer.valueOf(2130838454));
        f3156b.put(" :-$", Integer.valueOf(2130838454));
        f3156b.put(" :)", Integer.valueOf(2130838460));
        f3156b.put(" :-)", Integer.valueOf(2130838460));
        f3156b.put(" =)", Integer.valueOf(2130838460));
        f3156b.put(" :>", Integer.valueOf(2130838460));
        f3156b.put(" :]", Integer.valueOf(2130838460));
        f3156b.put(" :o)", Integer.valueOf(2130838460));
        f3156b.put(" )", Integer.valueOf(2130838461));
        f3156b.put(" D", Integer.valueOf(2130838463));
        f3156b.put(":-p", Integer.valueOf(2130838466));
        f3156b.put(":-@", Integer.valueOf(2130838468));
        f3156b.put(":-&", Integer.valueOf(2130838456));
        f3156b.put(" :|", Integer.valueOf(2130837814));
        f3156b.put(" :-|", Integer.valueOf(2130837814));
        f3156b.put(" :-/", Integer.valueOf(2130838472));
        f3156b.put(" :/", Integer.valueOf(2130838472));
        f3156b.put(" :?", Integer.valueOf(2130838472));
        f3156b.put(" :-?", Integer.valueOf(2130838472));
        f3156b.put(" :P", Integer.valueOf(2130838474));
        f3156b.put(" :p", Integer.valueOf(2130838474));
        f3156b.put(" :-P", Integer.valueOf(2130838474));
        f3156b.put(" :-p", Integer.valueOf(2130838474));
        f3156b.put(" ;)", Integer.valueOf(2130838476));
        f3156b.put(" ;-)", Integer.valueOf(2130838476));
        f3156b.put(" *-)", Integer.valueOf(2130838476));
        f3156b.put(" *)", Integer.valueOf(2130838476));
        f3156b.put(" ;-]", Integer.valueOf(2130838476));
        f3156b.put(" ;]", Integer.valueOf(2130838476));
        f3156b.put(" ;D", Integer.valueOf(2130838476));
        f3156b.put(" ;^)", Integer.valueOf(2130838476));
        f3156b.put(" >:)", Integer.valueOf(2130838438));
        f3156b.put(" :-O", Integer.valueOf(2130838470));
        f3156b.put(" I-o", Integer.valueOf(2130838458));
        f3157c = new HashMap();
        f3157c.put(" :@", Integer.valueOf(2130838431));
        f3157c.put(" :-||", Integer.valueOf(2130838431));
        f3157c.put(" >:(", Integer.valueOf(2130838431));
        f3157c.put(" :D", Integer.valueOf(2130838433));
        f3157c.put(" :d", Integer.valueOf(2130838433));
        f3157c.put(" :-D", Integer.valueOf(2130838433));
        f3157c.put(" :-d", Integer.valueOf(2130838433));
        f3157c.put(" =D", Integer.valueOf(2130838433));
        f3157c.put(" =d", Integer.valueOf(2130838433));
        f3157c.put(" =-D", Integer.valueOf(2130838433));
        f3157c.put(" =-d", Integer.valueOf(2130838433));
        f3157c.put(" %)", Integer.valueOf(2130838435));
        f3157c.put(" %-)", Integer.valueOf(2130838435));
        f3157c.put(" :S", Integer.valueOf(2130838437));
        f3157c.put(" :-S", Integer.valueOf(2130838437));
        f3157c.put(" :s", Integer.valueOf(2130838437));
        f3157c.put(" :-s", Integer.valueOf(2130838437));
        f3157c.put(" :G", Integer.valueOf(2130837799));
        f3157c.put(" :-G", Integer.valueOf(2130837799));
        f3157c.put(" :-g", Integer.valueOf(2130837799));
        f3157c.put(" :g", Integer.valueOf(2130837799));
        f3157c.put(" :-F", Integer.valueOf(2130837801));
        f3157c.put(" :F", Integer.valueOf(2130837801));
        f3157c.put(" :-f", Integer.valueOf(2130837801));
        f3157c.put(" :f", Integer.valueOf(2130837801));
        f3157c.put(" <3", Integer.valueOf(2130837803));
        f3157c.put(" :H", Integer.valueOf(2130837805));
        f3157c.put(" :-H", Integer.valueOf(2130837805));
        f3157c.put(" :h", Integer.valueOf(2130837805));
        f3157c.put(" :-h", Integer.valueOf(2130837805));
        f3157c.put(" :X", Integer.valueOf(2130838443));
        f3157c.put(" :-X", Integer.valueOf(2130838443));
        f3157c.put(" :x", Integer.valueOf(2130838443));
        f3157c.put(" :-x", Integer.valueOf(2130838443));
        f3157c.put(" :-(", Integer.valueOf(2130838451));
        f3157c.put(" :<", Integer.valueOf(2130838451));
        f3157c.put(" :-<", Integer.valueOf(2130838451));
        f3157c.put(" :-[", Integer.valueOf(2130838451));
        f3157c.put(" :[", Integer.valueOf(2130838451));
        f3157c.put(" :{", Integer.valueOf(2130838451));
        f3157c.put(" :C", Integer.valueOf(2130838446));
        f3157c.put(" :c", Integer.valueOf(2130838446));
        f3157c.put(" :-C", Integer.valueOf(2130838446));
        f3157c.put(" :-c", Integer.valueOf(2130838446));
        f3157c.put(" :(", Integer.valueOf(2130838448));
        f3157c.put(" >:c", Integer.valueOf(2130838450));
        f3157c.put(" >:p", Integer.valueOf(2130838453));
        f3157c.put(" :$", Integer.valueOf(2130838455));
        f3157c.put(" :-$", Integer.valueOf(2130838455));
        f3157c.put(" :)", Integer.valueOf(2130838465));
        f3157c.put(" :-)", Integer.valueOf(2130838465));
        f3157c.put(" =)", Integer.valueOf(2130838465));
        f3157c.put(" :>", Integer.valueOf(2130838465));
        f3157c.put(" :]", Integer.valueOf(2130838465));
        f3157c.put(" :o)", Integer.valueOf(2130838465));
        f3157c.put(" )", Integer.valueOf(2130838462));
        f3157c.put(" D", Integer.valueOf(2130838464));
        f3157c.put(":-p", Integer.valueOf(2130838467));
        f3157c.put(":-@", Integer.valueOf(2130838469));
        f3157c.put(":-&", Integer.valueOf(2130838457));
        f3157c.put(" :|", Integer.valueOf(2130837815));
        f3157c.put(" :-|", Integer.valueOf(2130837815));
        f3157c.put(" :-/", Integer.valueOf(2130838473));
        f3157c.put(" :/", Integer.valueOf(2130838473));
        f3157c.put(" :?", Integer.valueOf(2130838473));
        f3157c.put(" :-?", Integer.valueOf(2130838473));
        f3157c.put(" :P", Integer.valueOf(2130838475));
        f3157c.put(" :p", Integer.valueOf(2130838475));
        f3157c.put(" :-P", Integer.valueOf(2130838475));
        f3157c.put(" :-p", Integer.valueOf(2130838475));
        f3157c.put(" ;)", Integer.valueOf(2130838477));
        f3157c.put(" ;-)", Integer.valueOf(2130838477));
        f3157c.put(" *-)", Integer.valueOf(2130838477));
        f3157c.put(" *)", Integer.valueOf(2130838477));
        f3157c.put(" ;-]", Integer.valueOf(2130838477));
        f3157c.put(" ;]", Integer.valueOf(2130838477));
        f3157c.put(" ;D", Integer.valueOf(2130838477));
        f3157c.put(" ;^)", Integer.valueOf(2130838477));
        f3157c.put(" >:)", Integer.valueOf(2130838439));
        f3157c.put(" :-O", Integer.valueOf(2130838471));
        f3157c.put(" I-o", Integer.valueOf(2130838459));
    }

    public EmoticonManager(Context context) {
        this.f3159e = m4023a();
    }

    public static EmoticonManager m4022a(Context context) {
        if (f3158d == null) {
            f3158d = new EmoticonManager(context);
        }
        return f3158d;
    }

    private Pattern m4023a() {
        Set<String> keySet = f3156b.keySet();
        StringBuilder stringBuilder = new StringBuilder(keySet.size() * 4);
        stringBuilder.append('(');
        for (String quote : keySet) {
            stringBuilder.append(Pattern.quote(quote));
            stringBuilder.append('|');
        }
        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), ")");
        return Pattern.compile(stringBuilder.toString());
    }

    public Spannable m4024a(Context context, CharSequence charSequence, boolean z) {
        HashMap hashMap = z ? f3157c : f3156b;
        Spannable spannableStringBuilder = new SpannableStringBuilder(charSequence);
        Matcher matcher = this.f3159e.matcher(charSequence);
        while (matcher.find()) {
            spannableStringBuilder.setSpan(new ImageSpan(context, ((Integer) hashMap.get(matcher.group())).intValue()), matcher.start(), matcher.end(), 33);
        }
        return spannableStringBuilder;
    }
}
