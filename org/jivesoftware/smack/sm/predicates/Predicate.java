package org.jivesoftware.smack.sm.predicates;

import org.jivesoftware.smack.filter.StanzaFilter;

/* renamed from: org.jivesoftware.smack.sm.predicates.a */
public class Predicate {
    public static StanzaFilter m5833a() {
        return new ForMatchingPredicateOrAfterXStanzas(ForEveryMessage.INSTANCE, 5);
    }
}
