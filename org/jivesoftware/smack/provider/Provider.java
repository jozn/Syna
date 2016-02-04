package org.jivesoftware.smack.provider;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.util.ParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: org.jivesoftware.smack.provider.a */
public abstract class Provider<E extends Element> {
    public final E parse(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, SmackException {
        ParserUtils.assertAtStartTag(xmlPullParser);
        int depth = xmlPullParser.getDepth();
        E parse = parse(xmlPullParser, depth);
        ParserUtils.forwardToEndTagOfDepth(xmlPullParser, depth);
        return parse;
    }

    public abstract E parse(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException;
}
