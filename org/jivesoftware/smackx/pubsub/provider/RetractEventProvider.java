package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.RetractItem;

public class RetractEventProvider extends EmbeddedExtensionProvider<RetractItem> {
    protected RetractItem m5996a(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new RetractItem((String) map.get("id"));
    }

    protected /* synthetic */ ExtensionElement createReturnExtension(String str, String str2, Map map, List list) {
        return m5996a(str, str2, map, list);
    }
}
