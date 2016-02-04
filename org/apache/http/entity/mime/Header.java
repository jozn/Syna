package org.apache.http.entity.mime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Header implements Iterable<MinimalField> {
    private final Map<String, List<MinimalField>> fieldMap;
    private final List<MinimalField> fields;

    public Header() {
        this.fields = new LinkedList();
        this.fieldMap = new HashMap();
    }

    public void addField(MinimalField minimalField) {
        if (minimalField != null) {
            String toLowerCase = minimalField.getName().toLowerCase(Locale.ENGLISH);
            List list = (List) this.fieldMap.get(toLowerCase);
            if (list == null) {
                list = new LinkedList();
                this.fieldMap.put(toLowerCase, list);
            }
            list.add(minimalField);
            this.fields.add(minimalField);
        }
    }

    public MinimalField getField(String str) {
        if (str == null) {
            return null;
        }
        List list = (List) this.fieldMap.get(str.toLowerCase(Locale.ENGLISH));
        return (list == null || list.isEmpty()) ? null : (MinimalField) list.get(0);
    }

    public List<MinimalField> getFields() {
        return new ArrayList(this.fields);
    }

    public List<MinimalField> getFields(String str) {
        if (str == null) {
            return null;
        }
        List list = (List) this.fieldMap.get(str.toLowerCase(Locale.ENGLISH));
        return (list == null || list.isEmpty()) ? Collections.emptyList() : new ArrayList(list);
    }

    public Iterator<MinimalField> iterator() {
        return Collections.unmodifiableList(this.fields).iterator();
    }

    public int removeFields(String str) {
        if (str == null) {
            return 0;
        }
        List list = (List) this.fieldMap.remove(str.toLowerCase(Locale.ENGLISH));
        if (list == null || list.isEmpty()) {
            return 0;
        }
        this.fields.removeAll(list);
        return list.size();
    }

    public void setField(MinimalField minimalField) {
        if (minimalField != null) {
            List list = (List) this.fieldMap.get(minimalField.getName().toLowerCase(Locale.ENGLISH));
            if (list == null || list.isEmpty()) {
                addField(minimalField);
                return;
            }
            list.clear();
            list.add(minimalField);
            Iterator it = this.fields.iterator();
            int i = 0;
            int i2 = -1;
            while (it.hasNext()) {
                if (((MinimalField) it.next()).getName().equalsIgnoreCase(minimalField.getName())) {
                    it.remove();
                    if (i2 == -1) {
                        i2 = i;
                    }
                }
                i++;
            }
            this.fields.add(i2, minimalField);
        }
    }

    public String toString() {
        return this.fields.toString();
    }
}