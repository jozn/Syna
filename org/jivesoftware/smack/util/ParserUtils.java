package org.jivesoftware.smack.util;

import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ParserUtils {
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !ParserUtils.class.desiredAssertionStatus();
    }

    public static void assertAtEndTag(XmlPullParser xmlPullParser) throws XmlPullParserException {
        if (!$assertionsDisabled && xmlPullParser.getEventType() != 3) {
            throw new AssertionError();
        }
    }

    public static void assertAtStartTag(XmlPullParser xmlPullParser) throws XmlPullParserException {
        if (!$assertionsDisabled && xmlPullParser.getEventType() != 2) {
            throw new AssertionError();
        }
    }

    public static void forwardToEndTagOfDepth(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || xmlPullParser.getDepth() != i) {
                eventType = xmlPullParser.next();
            } else {
                return;
            }
        }
    }

    public static Boolean getBooleanAttribute(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        if (attributeValue == null) {
            return null;
        }
        attributeValue = attributeValue.toLowerCase(Locale.US);
        return (attributeValue.equals("true") || attributeValue.equals("0")) ? Boolean.valueOf(true) : Boolean.valueOf(false);
    }

    public static boolean getBooleanAttribute(XmlPullParser xmlPullParser, String str, boolean z) {
        Boolean booleanAttribute = getBooleanAttribute(xmlPullParser, str);
        return booleanAttribute == null ? z : booleanAttribute.booleanValue();
    }

    public static int getIntegerAttribute(XmlPullParser xmlPullParser, String str, int i) {
        Integer integerAttribute = getIntegerAttribute(xmlPullParser, str);
        return integerAttribute == null ? i : integerAttribute.intValue();
    }

    public static Integer getIntegerAttribute(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        return attributeValue == null ? null : Integer.valueOf(attributeValue);
    }

    public static int getIntegerFromNextText(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return Integer.valueOf(xmlPullParser.nextText()).intValue();
    }

    public static long getLongAttribute(XmlPullParser xmlPullParser, String str, long j) {
        Long longAttribute = getLongAttribute(xmlPullParser, str);
        return longAttribute == null ? j : longAttribute.longValue();
    }

    public static Long getLongAttribute(XmlPullParser xmlPullParser, String str) {
        String attributeValue = xmlPullParser.getAttributeValue("", str);
        return attributeValue == null ? null : Long.valueOf(attributeValue);
    }
}
