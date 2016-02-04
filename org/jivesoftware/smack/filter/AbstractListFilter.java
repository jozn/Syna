package org.jivesoftware.smack.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.jivesoftware.smack.util.Objects;

public abstract class AbstractListFilter implements StanzaFilter {
    protected final List<StanzaFilter> filters;

    protected AbstractListFilter() {
        this.filters = new ArrayList();
    }

    protected AbstractListFilter(StanzaFilter... stanzaFilterArr) {
        Objects.m5847a(stanzaFilterArr, "Parameter must not be null.");
        for (Object a : stanzaFilterArr) {
            Objects.m5847a(a, "Parameter must not be null.");
        }
        this.filters = new ArrayList(Arrays.asList(stanzaFilterArr));
    }

    public void addFilter(StanzaFilter stanzaFilter) {
        Objects.m5847a(stanzaFilter, "Parameter must not be null.");
        this.filters.add(stanzaFilter);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(": (");
        Iterator it = this.filters.iterator();
        while (it.hasNext()) {
            stringBuilder.append(((StanzaFilter) it.next()).toString());
            if (it.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
