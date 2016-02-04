package org.p075c.p076a;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: org.c.a.a */
public class XmppDateTime {
    private static final XmppDateTime f4154a;
    private static final Pattern f4155b;
    private static final XmppDateTime f4156c;
    private static final Pattern f4157d;
    private static final XmppDateTime f4158e;
    private static final Pattern f4159f;
    private static final XmppDateTime f4160g;
    private static final Pattern f4161h;
    private static final XmppDateTime f4162i;
    private static final Pattern f4163j;
    private static final XmppDateTime f4164k;
    private static final Pattern f4165l;
    private static final XmppDateTime f4166m;
    private static final Pattern f4167n;
    private static final DateFormat f4168o;
    private static final DateFormat f4169p;
    private static final DateFormat f4170q;
    private static final DateFormat f4171r;
    private static final Pattern f4172s;
    private static final List<XmppDateTime> f4173t;
    private static final Pattern f4174u;

    /* renamed from: org.c.a.a.a */
    public enum XmppDateTime {
        XEP_0082_DATE_PROFILE("yyyy-MM-dd"),
        XEP_0082_DATETIME_PROFILE("yyyy-MM-dd'T'HH:mm:ssZ"),
        XEP_0082_DATETIME_MILLIS_PROFILE("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
        XEP_0082_TIME_PROFILE("hh:mm:ss"),
        XEP_0082_TIME_ZONE_PROFILE("hh:mm:ssZ"),
        XEP_0082_TIME_MILLIS_PROFILE("hh:mm:ss.SSS"),
        XEP_0082_TIME_MILLIS_ZONE_PROFILE("hh:mm:ss.SSSZ"),
        XEP_0091_DATETIME("yyyyMMdd'T'HH:mm:ss");
        
        private final String f4139i;
        private final DateFormat f4140j;
        private final boolean f4141k;
        private final boolean f4142l;

        private XmppDateTime(String str) {
            this.f4139i = str;
            this.f4140j = new SimpleDateFormat(this.f4139i);
            this.f4140j.setTimeZone(TimeZone.getTimeZone("UTC"));
            this.f4141k = str.charAt(str.length() + -1) == 'Z';
            this.f4142l = str.contains("SSS");
        }

        public String m5378a(Date date) {
            String format;
            synchronized (this.f4140j) {
                format = this.f4140j.format(date);
            }
            return this.f4141k ? XmppDateTime.m5393d(format) : format;
        }

        public Date m5379a(String str) throws ParseException {
            Date parse;
            if (this.f4141k) {
                str = XmppDateTime.m5392c(str);
            }
            if (this.f4142l) {
                str = XmppDateTime.m5395f(str);
            }
            synchronized (this.f4140j) {
                parse = this.f4140j.parse(str);
            }
            return parse;
        }
    }

    /* renamed from: org.c.a.a.b */
    private static class XmppDateTime {
        final Pattern f4143a;
        final XmppDateTime f4144b;

        public XmppDateTime(Pattern pattern, XmppDateTime xmppDateTime) {
            this.f4143a = pattern;
            this.f4144b = xmppDateTime;
        }
    }

    static {
        f4154a = XmppDateTime.XEP_0082_DATE_PROFILE;
        f4155b = Pattern.compile("^\\d+-\\d+-\\d+$");
        f4156c = XmppDateTime.XEP_0082_TIME_MILLIS_ZONE_PROFILE;
        f4157d = Pattern.compile("^(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))$");
        f4158e = XmppDateTime.XEP_0082_TIME_MILLIS_PROFILE;
        f4159f = Pattern.compile("^(\\d+:){2}\\d+.\\d+$");
        f4160g = XmppDateTime.XEP_0082_TIME_ZONE_PROFILE;
        f4161h = Pattern.compile("^(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))$");
        f4162i = XmppDateTime.XEP_0082_TIME_PROFILE;
        f4163j = Pattern.compile("^(\\d+:){2}\\d+$");
        f4164k = XmppDateTime.XEP_0082_DATETIME_MILLIS_PROFILE;
        f4165l = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+.\\d+(Z|([+-](\\d+:\\d+)))?$");
        f4166m = XmppDateTime.XEP_0082_DATETIME_PROFILE;
        f4167n = Pattern.compile("^\\d+(-\\d+){2}+T(\\d+:){2}\\d+(Z|([+-](\\d+:\\d+)))?$");
        f4168o = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ss");
        f4169p = new SimpleDateFormat("yyyyMd'T'HH:mm:ss");
        f4170q = new SimpleDateFormat("yyyyMdd'T'HH:mm:ss");
        f4171r = new SimpleDateFormat("yyyyMMd'T'HH:mm:ss");
        f4172s = Pattern.compile("^\\d+T\\d+:\\d+:\\d+$");
        f4173t = new ArrayList();
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        f4168o.setTimeZone(timeZone);
        f4169p.setTimeZone(timeZone);
        f4170q.setTimeZone(timeZone);
        f4170q.setLenient(false);
        f4171r.setTimeZone(timeZone);
        f4171r.setLenient(false);
        f4173t.add(new XmppDateTime(f4155b, f4154a));
        f4173t.add(new XmppDateTime(f4165l, f4164k));
        f4173t.add(new XmppDateTime(f4167n, f4166m));
        f4173t.add(new XmppDateTime(f4157d, f4156c));
        f4173t.add(new XmppDateTime(f4159f, f4158e));
        f4173t.add(new XmppDateTime(f4161h, f4160g));
        f4173t.add(new XmppDateTime(f4163j, f4162i));
        f4174u = Pattern.compile(".*\\.(\\d{1,})(Z|((\\+|-)\\d{4}))");
    }

    public static String m5384a(Date date) {
        String a;
        synchronized (f4164k) {
            a = f4164k.m5378a(date);
        }
        return a;
    }

    public static String m5385a(TimeZone timeZone) {
        int rawOffset = timeZone.getRawOffset();
        rawOffset = Math.abs((rawOffset / 60000) - ((rawOffset / 3600000) * 60));
        return String.format("%+d:%02d", new Object[]{Integer.valueOf(r1), Integer.valueOf(rawOffset)});
    }

    private static Calendar m5386a(String str, DateFormat dateFormat) {
        try {
            Calendar calendar;
            synchronized (dateFormat) {
                dateFormat.parse(str);
                calendar = dateFormat.getCalendar();
            }
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    private static Calendar m5387a(Calendar calendar, List<Calendar> list) {
        Collections.sort(list, new XmppDateTime(calendar));
        return (Calendar) list.get(0);
    }

    public static Date m5388a(String str) throws ParseException {
        Date a;
        for (XmppDateTime xmppDateTime : f4173t) {
            if (xmppDateTime.f4143a.matcher(str).matches()) {
                return xmppDateTime.f4144b.m5379a(str);
            }
        }
        synchronized (f4166m) {
            a = f4166m.m5379a(str);
        }
        return a;
    }

    private static Date m5389a(String str, int i) throws ParseException {
        if (i == 6) {
            Date parse;
            synchronized (f4169p) {
                parse = f4169p.parse(str);
            }
            return parse;
        }
        Calendar instance = Calendar.getInstance();
        Calendar a = XmppDateTime.m5386a(str, f4170q);
        Calendar a2 = XmppDateTime.m5386a(str, f4171r);
        List a3 = XmppDateTime.m5390a(instance, a, a2);
        return !a3.isEmpty() ? XmppDateTime.m5387a(instance, a3).getTime() : null;
    }

    private static List<Calendar> m5390a(Calendar calendar, Calendar... calendarArr) {
        List<Calendar> arrayList = new ArrayList();
        for (Calendar calendar2 : calendarArr) {
            if (calendar2 != null && calendar2.before(calendar)) {
                arrayList.add(calendar2);
            }
        }
        return arrayList;
    }

    public static Date m5391b(String str) throws ParseException {
        if (f4172s.matcher(str).matches()) {
            int length = str.split("T")[0].length();
            Date a;
            if (length < 8) {
                a = XmppDateTime.m5389a(str, length);
                if (a != null) {
                    return a;
                }
            }
            synchronized (f4168o) {
                a = f4168o.parse(str);
            }
            return a;
        }
        return XmppDateTime.m5388a(str);
    }

    public static String m5392c(String str) {
        return str.charAt(str.length() + -1) == 'Z' ? str.replace("Z", "+0000") : str.replaceAll("([\\+\\-]\\d\\d):(\\d\\d)", "$1$2");
    }

    public static String m5393d(String str) {
        int length = str.length();
        return (str.substring(0, length - 2) + ':') + str.substring(length - 2, length);
    }

    private static String m5395f(String str) {
        Matcher matcher = f4174u.matcher(str);
        if (!matcher.matches()) {
            return str;
        }
        int length = matcher.group(1).length();
        if (length == 3) {
            return str;
        }
        int indexOf = str.indexOf(".");
        StringBuilder stringBuilder = new StringBuilder((str.length() - length) + 3);
        if (length > 3) {
            stringBuilder.append(str.substring(0, indexOf + 4));
        } else {
            stringBuilder.append(str.substring(0, (indexOf + length) + 1));
            for (int i = length; i < 3; i++) {
                stringBuilder.append('0');
            }
        }
        stringBuilder.append(str.substring((indexOf + length) + 1));
        return stringBuilder.toString();
    }
}
