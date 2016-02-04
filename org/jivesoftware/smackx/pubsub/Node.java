package org.jivesoftware.smackx.pubsub;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.delay.DelayInformationManager;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.pubsub.p086a.ItemDeleteListener;
import org.jivesoftware.smackx.pubsub.p086a.ItemEventListener;
import org.jivesoftware.smackx.pubsub.p086a.NodeConfigListener;
import org.jivesoftware.smackx.pubsub.p087b.NodeUtils;
import org.jivesoftware.smackx.pubsub.packet.PubSub;
import org.jivesoftware.smackx.pubsub.packet.PubSubNamespace;
import org.jivesoftware.smackx.shim.packet.Header;
import org.jivesoftware.smackx.shim.packet.HeadersExtension;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.packet.DataForm.Item;

public abstract class Node {
    protected XMPPConnection con;
    protected ConcurrentHashMap<NodeConfigListener, StanzaListener> configEventToListenerMap;
    protected String id;
    protected ConcurrentHashMap<ItemDeleteListener, StanzaListener> itemDeleteToListenerMap;
    protected ConcurrentHashMap<ItemEventListener<Item>, StanzaListener> itemEventToListenerMap;
    protected String to;

    class EventContentFilter implements StanzaFilter {
        private String firstElement;
        private String secondElement;

        EventContentFilter(String str) {
            this.firstElement = str;
        }

        EventContentFilter(String str, String str2) {
            this.firstElement = str;
            this.secondElement = str2;
        }

        public boolean accept(Stanza stanza) {
            if (!(stanza instanceof Message)) {
                return false;
            }
            EventElement eventElement = (EventElement) stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns());
            if (eventElement == null) {
                return false;
            }
            NodeExtension event = eventElement.getEvent();
            if (event == null) {
                return false;
            }
            if (event.getElementName().equals(this.firstElement)) {
                if (!event.getNode().equals(Node.this.getId())) {
                    return false;
                }
                if (this.secondElement == null) {
                    return true;
                }
                if (event instanceof EmbeddedPacketExtension) {
                    List extensions = ((EmbeddedPacketExtension) event).getExtensions();
                    if (extensions.size() > 0 && ((ExtensionElement) extensions.get(0)).getElementName().equals(this.secondElement)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public class ItemDeleteTranslator implements StanzaListener {
        private ItemDeleteListener listener;

        public ItemDeleteTranslator(ItemDeleteListener itemDeleteListener) {
            this.listener = itemDeleteListener;
        }

        public void processPacket(Stanza stanza) {
            EventElement eventElement = (EventElement) stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns());
            if (((ExtensionElement) eventElement.getExtensions().get(0)).getElementName().equals(PubSubElementType.PURGE_EVENT.getElementName())) {
                this.listener.m5983a();
                return;
            }
            ItemsExtension itemsExtension = (ItemsExtension) eventElement.getEvent();
            Collection<RetractItem> items = itemsExtension.getItems();
            List arrayList = new ArrayList(items.size());
            for (RetractItem id : items) {
                arrayList.add(id.getId());
            }
            this.listener.m5984a(new ItemDeleteEvent(itemsExtension.getNode(), arrayList, Node.getSubscriptionIds(stanza)));
        }
    }

    public class ItemEventTranslator implements StanzaListener {
        private ItemEventListener listener;

        public ItemEventTranslator(ItemEventListener itemEventListener) {
            this.listener = itemEventListener;
        }

        public void processPacket(Stanza stanza) {
            ItemsExtension itemsExtension = (ItemsExtension) ((EventElement) stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent();
            this.listener.m5985a(new ItemPublishEvent(itemsExtension.getNode(), itemsExtension.getItems(), Node.getSubscriptionIds(stanza), DelayInformationManager.getDelayTimestamp(stanza)));
        }
    }

    public class NodeConfigTranslator implements StanzaListener {
        private NodeConfigListener listener;

        public NodeConfigTranslator(NodeConfigListener nodeConfigListener) {
            this.listener = nodeConfigListener;
        }

        public void processPacket(Stanza stanza) {
            this.listener.m5986a((ConfigurationEvent) ((EventElement) stanza.getExtension("event", PubSubNamespace.EVENT.getXmlns())).getEvent());
        }
    }

    Node(XMPPConnection xMPPConnection, String str) {
        this.itemEventToListenerMap = new ConcurrentHashMap();
        this.itemDeleteToListenerMap = new ConcurrentHashMap();
        this.configEventToListenerMap = new ConcurrentHashMap();
        this.con = xMPPConnection;
        this.id = str;
    }

    private static List<String> getSubscriptionIds(Stanza stanza) {
        HeadersExtension headersExtension = (HeadersExtension) stanza.getExtension(HeadersExtension.ELEMENT, HeadersExtension.NAMESPACE);
        if (headersExtension == null) {
            return null;
        }
        List<String> arrayList = new ArrayList(headersExtension.getHeaders().size());
        for (Header value : headersExtension.getHeaders()) {
            arrayList.add(value.getValue());
        }
        return arrayList;
    }

    private List<Subscription> getSubscriptions(List<ExtensionElement> list, Collection<ExtensionElement> collection, PubSubNamespace pubSubNamespace) throws NoResponseException, XMPPErrorException, NotConnectedException {
        PubSub createPubsubPacket = createPubsubPacket(Type.get, new NodeExtension(PubSubElementType.SUBSCRIPTIONS, getId()), pubSubNamespace);
        if (list != null) {
            for (ExtensionElement addExtension : list) {
                createPubsubPacket.addExtension(addExtension);
            }
        }
        PubSub sendPubsubPacket = sendPubsubPacket(createPubsubPacket);
        if (collection != null) {
            collection.addAll(sendPubsubPacket.getExtensions());
        }
        return ((SubscriptionsExtension) sendPubsubPacket.getExtension(PubSubElementType.SUBSCRIPTIONS)).getSubscriptions();
    }

    public void addConfigurationListener(NodeConfigListener nodeConfigListener) {
        StanzaListener nodeConfigTranslator = new NodeConfigTranslator(nodeConfigListener);
        this.configEventToListenerMap.put(nodeConfigListener, nodeConfigTranslator);
        this.con.addSyncStanzaListener(nodeConfigTranslator, new EventContentFilter(EventElementType.configuration.toString()));
    }

    public void addItemDeleteListener(ItemDeleteListener itemDeleteListener) {
        StanzaListener itemDeleteTranslator = new ItemDeleteTranslator(itemDeleteListener);
        this.itemDeleteToListenerMap.put(itemDeleteListener, itemDeleteTranslator);
        EventContentFilter eventContentFilter = new EventContentFilter(EventElementType.items.toString(), "retract");
        EventContentFilter eventContentFilter2 = new EventContentFilter(EventElementType.purge.toString());
        this.con.addSyncStanzaListener(itemDeleteTranslator, new OrFilter(eventContentFilter, eventContentFilter2));
    }

    public void addItemEventListener(ItemEventListener itemEventListener) {
        StanzaListener itemEventTranslator = new ItemEventTranslator(itemEventListener);
        this.itemEventToListenerMap.put(itemEventListener, itemEventTranslator);
        this.con.addSyncStanzaListener(itemEventTranslator, new EventContentFilter(EventElementType.items.toString(), Item.ELEMENT));
    }

    protected PubSub createPubsubPacket(Type type, ExtensionElement extensionElement) {
        return createPubsubPacket(type, extensionElement, null);
    }

    protected PubSub createPubsubPacket(Type type, ExtensionElement extensionElement, PubSubNamespace pubSubNamespace) {
        return PubSub.createPubsubPacket(this.to, type, extensionElement, pubSubNamespace);
    }

    public DiscoverInfo discoverInfo() throws NoResponseException, XMPPErrorException, NotConnectedException {
        IQ discoverInfo = new DiscoverInfo();
        discoverInfo.setTo(this.to);
        discoverInfo.setNode(getId());
        return (DiscoverInfo) this.con.createPacketCollectorAndSend(discoverInfo).nextResultOrThrow();
    }

    public List<Affiliation> getAffiliations() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getAffiliations(null, null);
    }

    public List<Affiliation> getAffiliations(List<ExtensionElement> list, Collection<ExtensionElement> collection) throws NoResponseException, XMPPErrorException, NotConnectedException {
        PubSub createPubsubPacket = createPubsubPacket(Type.get, new NodeExtension(PubSubElementType.AFFILIATIONS, getId()));
        if (list != null) {
            for (ExtensionElement addExtension : list) {
                createPubsubPacket.addExtension(addExtension);
            }
        }
        PubSub sendPubsubPacket = sendPubsubPacket(createPubsubPacket);
        if (collection != null) {
            collection.addAll(sendPubsubPacket.getExtensions());
        }
        return ((AffiliationsExtension) sendPubsubPacket.getExtension(PubSubElementType.AFFILIATIONS)).getAffiliations();
    }

    public String getId() {
        return this.id;
    }

    public ConfigureForm getNodeConfiguration() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return NodeUtils.m5987a(sendPubsubPacket(createPubsubPacket(Type.get, new NodeExtension(PubSubElementType.CONFIGURE_OWNER, getId()), PubSubNamespace.OWNER)), PubSubElementType.CONFIGURE_OWNER);
    }

    public SubscribeForm getSubscriptionOptions(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getSubscriptionOptions(str, null);
    }

    public SubscribeForm getSubscriptionOptions(String str, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return new SubscribeForm(((FormNode) sendPubsubPacket(createPubsubPacket(Type.get, new OptionsExtension(str, getId(), str2))).getExtension(PubSubElementType.OPTIONS)).getForm());
    }

    public List<Subscription> getSubscriptions() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getSubscriptions(null, null);
    }

    public List<Subscription> getSubscriptions(List<ExtensionElement> list, Collection<ExtensionElement> collection) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getSubscriptions(list, collection, null);
    }

    public List<Subscription> getSubscriptionsAsOwner() throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getSubscriptionsAsOwner(null, null);
    }

    public List<Subscription> getSubscriptionsAsOwner(List<ExtensionElement> list, Collection<ExtensionElement> collection) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return getSubscriptions(list, collection, PubSubNamespace.OWNER);
    }

    public void removeConfigurationListener(NodeConfigListener nodeConfigListener) {
        StanzaListener stanzaListener = (StanzaListener) this.configEventToListenerMap.remove(nodeConfigListener);
        if (stanzaListener != null) {
            this.con.removeSyncStanzaListener(stanzaListener);
        }
    }

    public void removeItemDeleteListener(ItemDeleteListener itemDeleteListener) {
        StanzaListener stanzaListener = (StanzaListener) this.itemDeleteToListenerMap.remove(itemDeleteListener);
        if (stanzaListener != null) {
            this.con.removeSyncStanzaListener(stanzaListener);
        }
    }

    public void removeItemEventListener(ItemEventListener itemEventListener) {
        StanzaListener stanzaListener = (StanzaListener) this.itemEventToListenerMap.remove(itemEventListener);
        if (stanzaListener != null) {
            this.con.removeSyncStanzaListener(stanzaListener);
        }
    }

    public void sendConfigurationForm(Form form) throws NoResponseException, XMPPErrorException, NotConnectedException {
        this.con.createPacketCollectorAndSend(createPubsubPacket(Type.set, new FormNode(FormNodeType.CONFIGURE_OWNER, getId(), form), PubSubNamespace.OWNER)).nextResultOrThrow();
    }

    protected PubSub sendPubsubPacket(PubSub pubSub) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return PubSubManager.sendPubsubPacket(this.con, pubSub);
    }

    void setTo(String str) {
        this.to = str;
    }

    public Subscription subscribe(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        return (Subscription) sendPubsubPacket(createPubsubPacket(Type.set, new SubscribeExtension(str, getId()))).getExtension(PubSubElementType.SUBSCRIPTION);
    }

    public Subscription subscribe(String str, SubscribeForm subscribeForm) throws NoResponseException, XMPPErrorException, NotConnectedException {
        PubSub createPubsubPacket = createPubsubPacket(Type.set, new SubscribeExtension(str, getId()));
        createPubsubPacket.addExtension(new FormNode(FormNodeType.OPTIONS, subscribeForm));
        return (Subscription) PubSubManager.sendPubsubPacket(this.con, createPubsubPacket).getExtension(PubSubElementType.SUBSCRIPTION);
    }

    public String toString() {
        return super.toString() + " " + getClass().getName() + " id: " + this.id;
    }

    public void unsubscribe(String str) throws NoResponseException, XMPPErrorException, NotConnectedException {
        unsubscribe(str, null);
    }

    public void unsubscribe(String str, String str2) throws NoResponseException, XMPPErrorException, NotConnectedException {
        sendPubsubPacket(createPubsubPacket(Type.set, new UnsubscribeExtension(str, getId(), str2)));
    }
}
