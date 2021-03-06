package org.jivesoftware.smackx.pubsub.provider;

import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.EmbeddedExtensionProvider;
import org.jivesoftware.smackx.pubsub.ItemsExtension;
import org.jivesoftware.smackx.pubsub.ItemsExtension.ItemsElementType;

public class ItemsProvider extends EmbeddedExtensionProvider<ItemsExtension> {
    protected ItemsExtension m5994a(String str, String str2, Map<String, String> map, List<? extends ExtensionElement> list) {
        return new ItemsExtension(ItemsElementType.items, (String) map.get("node"), (List) list);
    }

    protected /* synthetic */ ExtensionElement createReturnExtension(String str, String str2, Map map, List list) {
        return m5994a(str, str2, map, list);
    }
}
