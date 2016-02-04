package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.Affiliation;
import org.jivesoftware.smackx.pubsub.Affiliation.Type;

public class AffiliationProvider extends EmbeddedExtensionProvider<Affiliation> {
    protected Affiliation m5988a(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new Affiliation((String) map.get("node"), Type.valueOf((String) map.get("affiliation")));
    }

    protected /* synthetic */ ExtensionElement createReturnExtension(String str, String str2, Map map, List list) {
        return m5988a(str, str2, map, list);
    }
}
